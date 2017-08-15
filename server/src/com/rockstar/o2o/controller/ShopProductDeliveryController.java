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
import com.rockstar.o2o.pojo.ProductInfo;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopProductDelivery;
import com.rockstar.o2o.pojo.ShopProductDeliveryB;
import com.rockstar.o2o.pojo.ShopProductInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 商品出库
 * @author xc
 *
 */
@Controller
@RequestMapping("delivery")
public class ShopProductDeliveryController extends BaseController{

	
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
		String deliverymoney=obj.getString("deliverymoney");
		String note=obj.get("note")==null?null:obj.getString("note");
		String products=obj.getString("products");
		ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
		if(user!=null){
		if(user.getPkShop()!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
		//生成订货单编号
		String date=DateUtil.getCurrDate(DateUtil.LONG_DATE_NOFORMAT);
		ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
		String code="CK"+info.getShopshortcode()+date;
		List<Object> oo=shopproductdeliveryService.queryMaxNo(code, pkShop);
		if(oo.get(0)==null){
			code=code+"0001";
		}else{
			code=code+oo.get(0).toString();
		}
		
		ShopProductDelivery delivery=new ShopProductDelivery();
		delivery.setDeliveryno(code);
		delivery.setDeliverymoney(Double.parseDouble(deliverymoney));
		delivery.setDr((short)0);
		delivery.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		delivery.setDeliverytime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		delivery.setOperatorid(Long.parseLong(pkUser));
		delivery.setOperatorname(user.getShopusername());
		delivery.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		delivery.setPkShop(Long.parseLong(pkShop));
		delivery.setVbillstatus(Vbillstatus.MAKE_BILL[0]);
		delivery.setNote(note);
		
		//保存订货主表
		delivery=shopproductdeliveryService.save(delivery);
		
		ArrayList<ShopProductDeliveryB> list=new ArrayList<ShopProductDeliveryB>();
		
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
				ShopProductDeliveryB deliveryb=new ShopProductDeliveryB();
				deliveryb.setPkProduct(Long.parseLong(pkProduct));
				deliveryb.setPkProductDelivery(delivery.getPkProductDelivery());
				deliveryb.setProductname(productname);
				deliveryb.setProductnum(Integer.parseInt(productnum));
				deliveryb.setProductprice(Double.parseDouble(productprice));
				deliveryb.setProductunit(productunit);
				deliveryb.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				deliveryb.setDr((short)0);
				list.add(deliveryb);
			}
		}
		
		  //批量保存订单明细
		 if(list.size()>0){
			shopproductdeliverybService.savelist(list);
		 }
		
		    outputstr("", "出库单录入成功", true,null);
		  }else{
			 outputstr("", "无操作权限", false,null);
		   }
		  }else{
			 outputstr("", "当前操作者不存在", false,null);	
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
			String pkProductDelivery=obj.getString("pkProductDelivery");
			String note=obj.get("note")==null?null:obj.getString("note");
			String pkUser=obj.getString("pkUser");
			String delivermoney=obj.getString("deliverymoney");
			String products=obj.getString("products");
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
			if(user.getPkShop()!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){				
			ShopProductDelivery delivery=shopproductdeliveryService.getDeliveryById(Long.parseLong(pkProductDelivery));
			if(delivery==null){
				outputstr("", "出库单信息有误", false, null);
			}else{				
		    if(delivery.getVbillstatus()!=null&&delivery.getVbillstatus().equalsIgnoreCase(Vbillstatus.CONFIRM_GOOD[0])){
		    	outputstr("", "已经确认出库,不允许进行编辑!", false, null);
		    }else{
			ArrayList<Object> addlist=new ArrayList<Object>();
			ArrayList<Object> updatelist=new ArrayList<Object>();	
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
					String pkProductDeliveryB=jsonobj.get("pkProductDeliveryB")==null?null:jsonobj.getString("pkProductDeliveryB");//主键
					String dr=jsonobj.get("dr")==null?null:jsonobj.getString("dr");//删除标志
					//新增商品
					if(pkProductDeliveryB==null){
					ShopProductDeliveryB deliveryb=new ShopProductDeliveryB();
					deliveryb.setPkProduct(Long.parseLong(pkProduct));
					deliveryb.setPkProductDelivery(Long.parseLong(pkProductDelivery));
					deliveryb.setProductname(productname);
					deliveryb.setProductnum(Integer.parseInt(productnum));
					deliveryb.setProductprice(Double.parseDouble(productprice));
					deliveryb.setProductunit(productunit);
					deliveryb.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					deliveryb.setDr((short)0);
					addlist.add(deliveryb);
					}else{
						//更新商品
						ShopProductDeliveryB olddelivery=shopproductdeliverybService.getDeliveryById(Long.parseLong(pkProductDeliveryB));
						olddelivery.setPkProduct(Long.parseLong(pkProduct));
						olddelivery.setProductname(productname);
						olddelivery.setProductnum(Integer.parseInt(productnum));
						olddelivery.setProductprice(Double.parseDouble(productprice));
						olddelivery.setProductunit(productunit);
						olddelivery.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						olddelivery.setDr(dr==null?(short)0:Short.parseShort(dr));
						updatelist.add(olddelivery);
					}
				}
			}
			
           	delivery.setNote(note);
			delivery.setDeliverymoney(Double.parseDouble(delivermoney));

			updatelist.add(delivery);
			
			basicservice.batchoperate(addlist, updatelist);
			
		    outputstr("", "出库维护成功", true,null);
			   }
			}
				}else{
					 outputstr("", "无操作权限", false,null);	
				}
			  }else{
				 outputstr("", "当前操作者不存在", false,null);	
			  }
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	
	/**
	 * 根据店铺查询出库单据
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
			
			 ArrayList<ShopProductDelivery> infos=new ArrayList<ShopProductDelivery>();
			 ArrayList<ShopProductDelivery> totalinfos=new ArrayList<ShopProductDelivery>();
			 //分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<ShopProductDelivery>) shopproductdeliveryService.QueryByShop(pkShop, Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<ShopProductDelivery>) shopproductdeliveryService.QueryByShop(pkShop, null,  null); 
			    if(infos.size()>0){
			    outputstr(JSONArray.fromObject(infos).toString(), "查询出库单据成功", true, totalinfos.size());
			    }else{
			    outputstr("", "暂无出库单据", true,null);	
			    }
		    }else{//全部查询
		    	 totalinfos=(ArrayList<ShopProductDelivery>) shopproductdeliveryService.QueryByShop(pkShop, null,  null); 
		    	 if(totalinfos.size()>0){
		    	   outputstr(JSONArray.fromObject(totalinfos).toString(), "查询出库单据成功", true, totalinfos.size());
		    	 }else{
		    	   outputstr("", "暂无出库单据", true,null);		 
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
			String pkProductBook=obj.getString("pkProductDelivery");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			//查询出库单
			ShopProductDelivery shopBook=shopproductdeliveryService.getDeliveryById(Long.parseLong(pkProductBook));
			if(shopBook!=null){
				JSONObject returnobj=JSONObject.fromObject(shopBook);
				ArrayList<ShopProductDeliveryB> infos=new ArrayList<ShopProductDeliveryB>();
				ArrayList<ShopProductDeliveryB> totalinfos=new ArrayList<ShopProductDeliveryB>();
				//分页查询
			    if(!curpage.equals("")){
				    infos=(ArrayList<ShopProductDeliveryB>) shopproductdeliverybService.QueryByPkBook(pkProductBook, Integer.parseInt(curpage),  Integer.parseInt(pagesize));
				    totalinfos=(ArrayList<ShopProductDeliveryB>) shopproductdeliverybService.QueryByPkBook(pkProductBook, null,  null); 
				    if(infos.size()>0){
				    	JSONArray array=JSONArray.fromObject(infos);
				    	returnobj.element("deliverydetail",array);
				    	returnobj.element("totalcount",totalinfos.size());
				    	outputstr(returnobj.toString(), "查询出库明细成功", true, totalinfos.size());
				    }else{
				    	outputstr("", "暂无出库明细", true,null);	
				    }
			    }else{//全部查询
			    	totalinfos=(ArrayList<ShopProductDeliveryB>) shopproductdeliverybService.QueryByPkBook(pkProductBook, null,  null); 
			    	if(totalinfos.size()>0){
			    		JSONArray array=JSONArray.fromObject(totalinfos);
				    	returnobj.element("deliverydetail",array);
				    	returnobj.element("totalcount",totalinfos.size());
			    		outputstr(returnobj.toString(), "查询出库明细成功", true, totalinfos.size());
			    	}else{
			    		outputstr("", "暂无出库明细", true,null);		 
			    	}
			    }
			}
		 } catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		 }
		output(response, pojo);
	}
	

	
	/**
	 * 确认出库
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/confirmdelivery")
	public void confirmdelivery(HttpServletRequest request,HttpServletResponse response,Model model) {		  		  
		try {
			   JSONObject obj=RequestUtil.getPostString(request);
			   String pkUser=obj.getString("pkUser");
			   String pkShop=obj.getString("pkShop");
			   String pkProductDelivery=obj.getString("pkProductDelivery");
			   ShopProductDelivery delivery=shopproductdeliveryService.getDeliveryById(Long.parseLong(pkProductDelivery));
			   if(delivery!=null){
				   if(delivery.getVbillstatus()!=null&&delivery.getVbillstatus().equals(Vbillstatus.MAKE_BILL[0])){
				   //查询当前用户是否存在权限
				   ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
				   if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
				   boolean issuccess=true;
				   ArrayList<ShopProductDeliveryB> deliverys=(ArrayList<ShopProductDeliveryB>)shopproductdeliverybService.QueryByPkBook(pkProductDelivery,null,null);	
			       if(deliverys.size()>0){				    
			       ArrayList<Object> updatelist=new ArrayList<Object>();
				   for(ShopProductDeliveryB deliveryb:deliverys){
						Long pkproduct=deliveryb.getPkProduct();
						ShopProductInfo info=shopproductService.getInfoById(pkproduct);
						if(info !=null){
							ProductInfo platproduct=(ProductInfo) basicservice.querybyid(ProductInfo.class, info.getPkProduct());
							if(info!=null&&platproduct!=null){
								//更新商品数量
								if((info.getProductnum()==null?0:info.getProductnum())-(deliveryb.getProductnum()==null?0:deliveryb.getProductnum())<0){
									 issuccess=false;
								}else if((platproduct.getProductnum()==null?0:platproduct.getProductnum())-(deliveryb.getProductnum()==null?0:deliveryb.getProductnum())<0){
									 issuccess=false;
								 }else {
								info.setProductnum((info.getProductnum()==null?0:info.getProductnum())-(deliveryb.getProductnum()==null?0:deliveryb.getProductnum()));
								updatelist.add(info);
								platproduct.setProductnum((platproduct.getProductnum()==null?0:platproduct.getProductnum())-(deliveryb.getProductnum()==null?0:deliveryb.getProductnum()));
								updatelist.add(platproduct);
								}
							}else{
								 issuccess=false;
							}
						}else{
							 issuccess=false;
						}
				    }
				    if(issuccess){				    	
				    	delivery.setVapprovorid(user.getPkShopuser());
				    	delivery.setApprovename(user.getShopusername());
				    	delivery.setApprovetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				    	delivery.setVbillstatus(Vbillstatus.CONFIRM_GOOD[0]);

				    	updatelist.add(delivery);
				    	
				    	//批量更新数据
				    	if(updatelist.size()>0){
					    	basicservice.batchoperate(null, updatelist);			    							    			
					    }
				    	outputstr("", "出库完成", true, null);	
				    }else{
				    	outputstr("", "商品信息有误或商品数量不足", false, null);		
				    }
			        }else{
			        	outputstr("", "出库完成", true, null);	
			        }
				  }else{
					  outputstr("", "当前用户不存在或没有权限", false, null);	  
				  }
			      }else{
			    	  outputstr("", "当前单据状态不允许确认出库", false, null);	 
			      }
			   }else{
				   outputstr("", "出货单不存在", false, null);		 
			   }
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	

	
}
