package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.Vbillstatus;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopProductBook;
import com.rockstar.o2o.pojo.ShopProductBookB;
import com.rockstar.o2o.pojo.ShopProductInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 订货管理
 * @author xc
 *
 */
@Controller
@RequestMapping("book333")
public class ShopProductBookController extends BaseController{

	/**
	 * 保存订货单
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response,Model model) {			
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String pkShop=obj.getString("pkShop");
		String pkUser=obj.getString("pkUser");
		String bookmoney=obj.getString("bookmoney");
		String note=obj.get("note")==null?null:obj.getString("note");
		String products=obj.getString("products");
		String principal=obj.get("principal")==null?null:obj.getString("principal");
		ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
		if(user!=null){
		if(user.getPkShop()!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
		//生成订货单编号
		String date=DateUtil.getCurrDate(DateUtil.LONG_DATE_NOFORMAT);
		ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
		String code="DH"+info.getShopshortcode()+date;
		List<Object> oo=shopproductbookService.queryMaxNo(code, pkShop);
		if(oo.get(0)==null){
			code=code+"0001";
		}else{
			code=code+oo.get(0).toString();
		}
		
		ShopProductBook book=new ShopProductBook();
		book.setBookno(code);
		book.setBookmoney(Double.parseDouble(bookmoney));
		book.setDr((short)0);
		book.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		book.setPrincipal(principal==null?user.getShopusername():principal);
		book.setBooktime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		book.setOperatorid(Long.parseLong(pkUser));
		book.setOperatorname(user.getShopusername());
		book.setOperatortime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		book.setPkShop(Long.parseLong(pkShop));
		book.setVbillstatus(Vbillstatus.MAKE_BILL[0]);
		book.setNote(note);
		
		//保存订货主表
		book=shopproductbookService.save(book);
		
		ArrayList<ShopProductBookB> list=new ArrayList<ShopProductBookB>();
		
		//保存订货明细表
		if(!products.equals("")){
			
			JSONArray productarr=JSONArray.fromObject(products);
			Iterator<?> iter=productarr.iterator();
			while(iter.hasNext()){
				JSONObject jsonobj=(JSONObject) iter.next();
				String pkProduct=jsonobj.getString("pkProduct");//商品主键
				String productname=jsonobj.getString("productname");//商品名称
				String productnum=jsonobj.getString("productnum");//商品数量
				String productprice=jsonobj.getString("productprice");//单价
				String productunit=jsonobj.get("productunit")==null?null:jsonobj.getString("productunit");//单位
				String productcapacity=jsonobj.get("productcapacity")==null?null:jsonobj.getString("productcapacity");//容量
				ShopProductBookB bookb=new ShopProductBookB();
				bookb.setPkProduct(Long.parseLong(pkProduct));
				bookb.setPkProductBook(book.getPkProductBook());
				bookb.setProductname(productname);
				bookb.setProductnum(Integer.parseInt(productnum));
				bookb.setRealnum(Integer.parseInt(productnum));
				bookb.setApprovenum(Integer.parseInt(productnum));
				bookb.setProductprice(Double.parseDouble(productprice));
				bookb.setProductunit(productunit);
				bookb.setProductcapacity(productcapacity);
				bookb.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				bookb.setDr((short)0);
				list.add(bookb);
			}
		}
		
		  //批量保存订单明细
		 if(list.size()>0){
			shopproductbookbService.savelist(list);
		 }
		
		    outputstr("", "订货录入成功", true,null);
		  }else{
			 outputstr("", "无操作权限", false,null);
		   }
		  }else{
			 outputstr("", "当前操作者不存在", true,null);	
		  }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 编辑订货单
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String pkProductBook=obj.getString("pkProductBook");
			String note=obj.get("note")==null?null:obj.getString("note");
			String pkUser=obj.getString("pkUser");
			String bookmoney=obj.getString("bookmoney");
			String products=obj.getString("products");
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
			if(user.getPkShop()!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){				
			ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
			if(book==null){
				outputstr("", "订货单信息有误", false, null);
			}else{
				if(book.getVbillstatus()!=null&&(book.getVbillstatus().equalsIgnoreCase(Vbillstatus.MAKE_BILL[0])||
						book.getVbillstatus().equalsIgnoreCase(Vbillstatus.REJECT_BILL[0]))){
			ArrayList<ShopProductBookB> addlist=new ArrayList<ShopProductBookB>();
			ArrayList<ShopProductBookB> updatelist=new ArrayList<ShopProductBookB>();	
			//保存订货明细表
			if(!products.equals("")){
				JSONArray productarr=JSONArray.fromObject(products);
				Iterator<?> iter=productarr.iterator();
				while(iter.hasNext()){
					JSONObject jsonobj=(JSONObject) iter.next();
					String pkProduct=jsonobj.getString("pkProduct");//商品主键
					String productname=jsonobj.getString("productname");//商品名称
					String productnum=jsonobj.getString("productnum");//商品数量
					String productprice=jsonobj.getString("productprice");//单价
					String productunit=jsonobj.get("productunit")==null?null:jsonobj.getString("productunit");//单位
					String productcapacity=jsonobj.get("productcapacity")==null?null:jsonobj.getString("productcapacity");//容量
					String pkProductBookB=jsonobj.get("pkProductBookB")==null?null:jsonobj.getString("pkProductBookB");//主键
					String dr=jsonobj.get("dr")==null?null:jsonobj.getString("dr");//删除标志
					//新增商品
					if(pkProductBookB==null){
					ShopProductBookB bookb=new ShopProductBookB();
					bookb.setPkProduct(Long.parseLong(pkProduct));
					bookb.setPkProductBook(Long.parseLong(pkProductBook));
					bookb.setProductname(productname);
					bookb.setProductnum(Integer.parseInt(productnum));
					bookb.setRealnum(Integer.parseInt(productnum));
					bookb.setApprovenum(Integer.parseInt(productnum));
					bookb.setProductprice(Double.parseDouble(productprice));
					bookb.setProductunit(productunit);
					bookb.setProductcapacity(productcapacity);
					bookb.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					bookb.setDr((short)0);
					addlist.add(bookb);
					}else{
						//更新商品
						ShopProductBookB oldbook=shopproductbookbService.getBookById(Long.parseLong(pkProductBookB));
						oldbook.setPkProduct(Long.parseLong(pkProduct));
						oldbook.setProductname(productname);
						oldbook.setProductnum(Integer.parseInt(productnum));
						oldbook.setRealnum(Integer.parseInt(productnum));
						oldbook.setApprovenum(Integer.parseInt(productnum));
						oldbook.setProductprice(Double.parseDouble(productprice));
						oldbook.setProductunit(productunit);
						oldbook.setProductcapacity(productcapacity);
						oldbook.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						oldbook.setDr(dr==null?(short)0:Short.parseShort(dr));
						updatelist.add(oldbook);
					}
				}
			}
			
			//批量保存订单明细
			if(addlist.size()>0){
				shopproductbookbService.savelist(addlist);
			}
			
			//批量更新订单明细
			if(updatelist.size()>0){
				shopproductbookbService.updatelist(updatelist);
			}
			
			book.setNote(note);
			book.setVbillstatus(Vbillstatus.MAKE_BILL[0]);
			book.setBookmoney(Double.parseDouble(bookmoney));
			shopproductbookService.updateInfo(book);
			
			       outputstr("", "订货维护成功", true,null);
				}else{
				  outputstr("", "当前单据状态不允许编辑", false,null);	
				}
			}
				}else{
					 outputstr("", "无操作权限", false,null);	
				}
			  }else{
				 outputstr("", "当前操作者不存在", true,null);	
			  }
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	
	/**
	 * 根据店铺查询订货单据
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/findbyshop")
	public void finfbyshop(HttpServletRequest request,HttpServletResponse response,Model model) {			
		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			
			 ArrayList<ShopProductBook> infos=new ArrayList<ShopProductBook>();
			 ArrayList<ShopProductBook> totalinfos=new ArrayList<ShopProductBook>();
			 //分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<ShopProductBook>) shopproductbookService.QueryByShop(pkShop, Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<ShopProductBook>) shopproductbookService.QueryByShop(pkShop, null,  null); 
			    if(infos.size()>0){
			    outputstr(JSONArray.fromObject(infos).toString(), "查询订货单据成功", true, totalinfos.size());
			    }else{
			    outputstr("", "暂无订货单据", true,null);	
			    }
		    }else{//全部查询
		    	totalinfos=(ArrayList<ShopProductBook>) shopproductbookService.QueryByShop(pkShop, null,  null); 
		    	 if(totalinfos.size()>0){
		    	   outputstr(JSONArray.fromObject(totalinfos).toString(), "查询订货单据成功", true, totalinfos.size());
		    	 }else{
		    	   outputstr("", "暂无订货单据", true,null);		 
		    	 }
		    }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
	    	output(response, pojo);
	}
	
	/**
	 * 根据订货主表查询子表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querydetail")
	public void querydetail(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkProductBook=obj.getString("pkProductBook");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			
			ArrayList<ShopProductBookB> infos=new ArrayList<ShopProductBookB>();
			ArrayList<ShopProductBookB> totalinfos=new ArrayList<ShopProductBookB>();
			
			 //分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<ShopProductBookB>) shopproductbookbService.QueryByPkBook(pkProductBook, Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<ShopProductBookB>) shopproductbookbService.QueryByPkBook(pkProductBook, null,  null); 
			    if(infos.size()>0){
			    outputstr(JSONArray.fromObject(infos).toString(), "查询订货明细成功", true, totalinfos.size());
			    }else{
			    outputstr("", "暂无订货明细", true,null);	
			    }
		    }else{//全部查询
		    	totalinfos=(ArrayList<ShopProductBookB>) shopproductbookbService.QueryByPkBook(pkProductBook, null,  null); 
		    	 if(totalinfos.size()>0){
		    	   outputstr(JSONArray.fromObject(totalinfos).toString(), "查询订货明细成功", true, totalinfos.size());
		    	 }else{
		    	   outputstr("", "暂无订货明细", true,null);		 
		    	 }
		    }
		 } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		 }
		output(response, pojo);
	}
	
	/**
	 * 平台修改审批数量
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/plateditnum")
	public void plateditnum(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkProductBookB=obj.getString("pkProductBookB");
			String pkProductBook=obj.getString("pkProductBook");
			String approvenum=obj.getString("approvenum");
			String bookmoney=obj.getString("bookmoney");
			String pkUser=obj.getString("pkUser");
			//查询当前操作者
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));		
			if(user!=null&&user.getUsertype()!=null&&user.getUsertype().equals("2")){
			ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
			if(book!=null){
				if(book.getVbillstatus().equalsIgnoreCase(Vbillstatus.COMMIT_BILL[0])){
				ShopProductBookB bookb=shopproductbookbService.getBookById(Long.parseLong(pkProductBookB));
				if(bookb!=null){
					bookb.setRealnum(Integer.parseInt(approvenum));
					bookb.setApprovenum(Integer.parseInt(approvenum));
					shopproductbookbService.updateBook(bookb);
					book.setBookmoney(Double.parseDouble(bookmoney));
					shopproductbookService.updateInfo(book);
					outputstr("", "维护发货数量成功", true, null);
				 }else{
					outputstr("", "明细不存在!", false, null);
				 }
				}else{
					outputstr("", "只有提交的单据才允许修改发货数量!", false, null);
				}
			}else{
			 outputstr("", "订货单不存在!", false, null);	
			}
			}else{
				outputstr("", "当前用户无修改权限", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 店铺修改实际收货数量
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/shopeditnum")
	public void editnum(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkProductBookB=obj.getString("pkProductBookB");
			String pkProductBook=obj.getString("pkProductBook");
			String realnum=obj.getString("realnum");
			String bookmoney=obj.getString("bookmoney");
			String pkUser=obj.getString("pkUser");
			String pkShop=obj.getString("pkShop");
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
			ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
			if(book!=null){
				if(book.getVbillstatus().equalsIgnoreCase(Vbillstatus.APPROVED_BILL[0])){
				ShopProductBookB bookb=shopproductbookbService.getBookById(Long.parseLong(pkProductBookB));
				if(bookb!=null){
					bookb.setRealnum(Integer.parseInt(realnum));
					shopproductbookbService.updateBook(bookb);
					book.setBookmoney(Double.parseDouble(bookmoney));
					shopproductbookService.updateInfo(book);
					outputstr("", "维护收货数量成功", true, null);
				 }else{
					outputstr("", "明细不存在!", false, null);
				 }
				}else{
					outputstr("", "只有审批通过的单子才允许修改收货数量!", false, null);
				}
			}else{
			 outputstr("", "订货单不存在!", false, null);	
			}
			}else{
				outputstr("", "当前用户不存在或无权限", false, null);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/confirmgood")
	public void confirmgood(HttpServletRequest request,HttpServletResponse response,Model model) {		  		  
		try {
			   JSONObject obj=RequestUtil.getPostString(request);
			   String pkUser=obj.getString("pkUser");
			   String pkShop=obj.getString("pkShop");
			   String pkProductBook=obj.getString("pkProductBook");
			   ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
			   if(book!=null){
				  //查询当前用户是否存在权限
				  ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
				  if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
                 boolean issuccess=true;
				 //确认收货的前提必须是单子是审批通过的
				 if(book.getVbillstatus().equalsIgnoreCase(Vbillstatus.WAITING_GOOD[0])){
				 ArrayList<ShopProductBookB> books=(ArrayList<ShopProductBookB>)shopproductbookbService.QueryByPkBook(pkProductBook,null,null);	
			     if(books.size()>0){				    
			    	ArrayList<ShopProductInfo> infolist=new ArrayList<ShopProductInfo>();
				    for(ShopProductBookB bookb:books){
					Long pkproduct=bookb.getPkProduct();
					ShopProductInfo info=shopproductService.getInfoById(pkproduct);
					if(info!=null){
						//更新商品数量
						info.setProductnum((info.getProductnum()==null?0:info.getProductnum())+(bookb.getRealnum()==null?0:bookb.getRealnum()));
						infolist.add(info);
					}else{
						 issuccess=false;
					 }
				   }
				    if(issuccess){
				    	if(infolist.size()>0){
				    	shopproductService.updatelist(infolist);				    							    			
				    	}
				    	book.setVbillstatus(Vbillstatus.CONFIRM_GOOD[0]);
				    	shopproductbookService.updateInfo(book);
				    	outputstr("", "收货完成", true, null);	
				    }else{
				      outputstr("", "商品信息不完整", false, null);		
				      }
			        }else{
			        	outputstr("", "收货完成", true, null);	
			        }
			      }else{
				      outputstr("", "当前单据状态不允许确认收货", false, null);		
			        }
				  }else{
					  outputstr("", "当前用户不存在或没有权限", false, null);	  
				  }
			   }else{
				   outputstr("", "订货单不存在", false, null);		 
			   }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 订货单提交
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/commit")
	public void commit(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser=obj.getString("pkUser");
			String pkShop=obj.getString("pkShop");
			String pkProductBook=obj.getString("pkProductBook"); 
			
			//查询当前用户是否存在权限
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
				if(user.getPkShop()!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
					ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
					if(book!=null){
						if(book.getVbillstatus()!=null&&book.getVbillstatus().equals(Vbillstatus.MAKE_BILL[0])){
					    //更新单据状态
						book.setVbillstatus(Vbillstatus.COMMIT_BILL[0]);
						shopproductbookService.updateInfo(book);	
						outputstr("", "提交成功", true, null);		
						}else{
						outputstr("", "当前单据状态不允许提交", false, null);		
						}
					}else{
					outputstr("", "当前单据不存在", false, null);	
					}		
				}else{
					outputstr("", "当前用户没有提交权限", false, null);
				}
			}else{
				outputstr("", "用户不存在", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 订货单审批
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/approve")
	public void approve(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkProductBook=obj.getString("pkProductBook");
			String pkUser=obj.getString("pkUser");
			String ispass=obj.getString("ispass");
			String note=obj.get("note")==null?null:obj.getString("note");
			
			//查询当前用户是否存在权限
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
				if(user.getUsertype()!=null&&user.getUsertype().equals("2")){
				ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
				if(book!=null){
					if(book.getVbillstatus()!=null&&book.getVbillstatus().equals(Vbillstatus.COMMIT_BILL[0])){
						 //更新单据状态
						book.setVapprovid(Long.parseLong(pkUser));	
						book.setApprovename(user.getShopusername());
						book.setApprovetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						book.setVbillstatus(ispass.equals("Y")?Vbillstatus.APPROVED_BILL[0]:Vbillstatus.REJECT_BILL[0]);
						book.setApprivenote(note);
						shopproductbookService.updateInfo(book);					
						outputstr("", "审批成功", true, null);	
					}else{
						outputstr("", "当前单据状态不允许审批", true, null);	
					}
				   
				}else{
				outputstr("", "当前单据不存在", false, null);	
				}
				}else{
				outputstr("", "当前用户无审批权限", false, null);	
				}
			}else{
				outputstr("", "当前用户不存在", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	 }
	
	/**
	 * 根据平台查询
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/platquery")
	public void platquery(HttpServletRequest request,HttpServletResponse response,Model model) {			
		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser=obj.getString("pkUser");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			
			 ArrayList<Object> infos=new ArrayList<Object>();
			 ArrayList<Object> totalinfos=new ArrayList<Object>();
			 
			 ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			 if(user!=null&&user.getUsertype().equals("2")){
			 //分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<Object>) shopproductbookService.Querywaitapprove(Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<Object>) shopproductbookService.Querywaitapprove(null,  null); 
			    if(infos.size()>0){
			    outputstr(JSONArray.fromObject(infos).toString(), "查询订货单据成功", true, totalinfos.size());
			    }else{
			    outputstr("", "暂无订货单据", true,null);	
			    }
		    }else{//全部查询
		    	totalinfos=(ArrayList<Object>) shopproductbookService.Querywaitapprove(null,  null); 
		    	 if(totalinfos.size()>0){
		    	   outputstr(JSONArray.fromObject(totalinfos).toString(), "查询订货单据成功", true, totalinfos.size());
		    	 }else{
		    	   outputstr("", "暂无订货单据", true,null);		 
		    	 }
		    }
			 }else{
				 outputstr("", "无查询权限", false,null);		 
			 }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
	    	output(response, pojo);
	}
	
	
}
