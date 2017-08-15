package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.Vbillstatus;
import com.rockstar.o2o.pojo.PlatUser;
import com.rockstar.o2o.pojo.ShopProductBook;
import com.rockstar.o2o.pojo.ShopProductBookB;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 平台订货管理
 *
 */
@Controller
@RequestMapping("platbook")
public class PlatProductBookController extends BaseController{
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
			//省
			String province=(String)obj.get("province");
			//市
			String city=(String)obj.get("city");
			//区
			String county=(String)obj.get("county");
			//店铺主键
			String pkShop=(String)obj.get("pkShop");
			
			String begintime=obj.get("begintime")==null?"":obj.getString("begintime");	
			String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
			//状态
			String vbillstatus=obj.get("vbillstatus")==null?"":obj.getString("vbillstatus");
			
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<Object> infos=new ArrayList<Object>();
			ArrayList<Object> totalinfos=new ArrayList<Object>();
			 
			PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null&&user.getUsertype().equals("2")){
				//分页查询
			    if(!curpage.equals("")){
				    infos=(ArrayList<Object>) shopproductbookService.Queryapprove(province,city,county,pkShop,begintime,endtime,vbillstatus,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				    totalinfos=(ArrayList<Object>) shopproductbookService.Queryapprove(province,city,county,pkShop,begintime,endtime,vbillstatus,null,null); 
				    if(infos.size()>0){
				    	outputstr(JSONArray.fromObject(infos).toString(), "查询订货单据成功", true, totalinfos.size());
				    }else{
				    	outputstr("", "暂无订货单据", true,null);	
				    }
			    }else{
			    	//全部查询
			    	totalinfos=(ArrayList<Object>) shopproductbookService.Queryapprove(province,city,county,pkShop,begintime,endtime,vbillstatus,null,null); 
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
			
			ShopProductBook shopBook=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
			
			if(shopBook!=null){
				JSONObject returnobj=JSONObject.fromObject(shopBook);
				ArrayList<ShopProductBookB> infos=new ArrayList<ShopProductBookB>();
				ArrayList<ShopProductBookB> totalinfos=new ArrayList<ShopProductBookB>();
				 //分页查询
			    if(!curpage.equals("")){
				    infos=(ArrayList<ShopProductBookB>) shopproductbookbService.QueryByPkBook(pkProductBook, Integer.parseInt(curpage),  Integer.parseInt(pagesize));
				    totalinfos=(ArrayList<ShopProductBookB>) shopproductbookbService.QueryByPkBook(pkProductBook, null,  null); 
				    if(infos.size()>0){
				    	JSONArray array=JSONArray.fromObject(infos);
				    	returnobj.element("bookdetail",array);
				    	returnobj.element("totalcount",totalinfos.size());
				    	outputstr(returnobj.toString(), "查询订货明细成功", true, totalinfos.size());
				    }else{
				    	outputstr("", "暂无订货明细", true,null);	
				    }
			    }else{//全部查询
			    	totalinfos=(ArrayList<ShopProductBookB>) shopproductbookbService.QueryByPkBook(pkProductBook, null,  null); 
			    	if(totalinfos.size()>0){
			    		JSONArray array=JSONArray.fromObject(totalinfos);
				    	returnobj.element("bookdetail",array);
				    	returnobj.element("totalcount",totalinfos.size());
			    	    outputstr(returnobj.toString(), "查询订货明细成功", true, totalinfos.size());
			    	}else{
			    	    outputstr("", "暂无订货明细", true,null);		 
			    	}
			    }
			}else{
				outputstr("", "订货单不存在", false,null);	
			}
		 } catch (Exception e) {
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
			PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null&&user.getUsertype()!=null&&user.getUsertype().equals("2")){
				ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
				if(book!=null){
					if(book.getVbillstatus().equalsIgnoreCase(Vbillstatus.COMMIT_BILL[0])||book.getVbillstatus().equalsIgnoreCase(Vbillstatus.APPROVED_BILL[0])){
						ShopProductBookB bookb=shopproductbookbService.getBookById(Long.parseLong(pkProductBookB));
						if(bookb!=null){
							//bookb.setRealnum(Integer.parseInt(approvenum));
							bookb.setApprovenum(Integer.parseInt(approvenum));
							int result=shopproductbookbService.updateBook(bookb);
							if(result==0){
								book.setBookmoney(Double.parseDouble(bookmoney));
								shopproductbookService.updateInfo(book);
								outputstr("", "维护发货数量成功", true, null);
							}else{
								outputstr("", "维护发货数量失败", false, null);
							}
						 }else{
							outputstr("", "明细不存在!", false, null);
						 }
					}else{
						outputstr("", "只有提交和审批通过的单据才允许修改发货数量!", false, null);
					}
				}else{
				 outputstr("", "订货单不存在!", false, null);	
				}
			}else{
				outputstr("", "当前用户无修改权限", false, null);
			}
		} catch (Exception e) {
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
			PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
				if(user.getUsertype()!=null&&user.getUsertype().equals("2")){
					ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
					if(book!=null){
						if(book.getVbillstatus()!=null&&book.getVbillstatus().equals(Vbillstatus.COMMIT_BILL[0])){
							//更新单据状态
							book.setVapprovid(Long.parseLong(pkUser));	
							book.setApprovename(user.getPlatusername());
							book.setApprovetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							book.setVbillstatus(ispass.equals("Y")?Vbillstatus.APPROVED_BILL[0]:Vbillstatus.REJECT_BILL[0]);
							book.setApprivenote(note);
							int result=shopproductbookService.updateInfo(book);	
							if(result==0){
								outputstr("", "审批成功", true, null);
							}else{
								outputstr("", "审批失败", false, null);
							}
						}else{
							outputstr("", "当前单据状态不允许审批", false, null);	
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
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	 }
	
	/**
	 * 平台发货
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/sendgoods")
	public void sendgoods(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkProductBook=obj.getString("pkProductBook");
			String pkUser=obj.getString("pkUser");
			//查询当前用户是否存在权限
			PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
				if(user.getUsertype()!=null&&user.getUsertype().equals("2")){
					ShopProductBook book=shopproductbookService.getBookById(Long.parseLong(pkProductBook));
					if(book!=null){
						if(book.getVbillstatus()!=null&&book.getVbillstatus().equals(Vbillstatus.APPROVED_BILL[0])){
							 boolean issuccess=true;
							 ArrayList<ShopProductBookB> books=(ArrayList<ShopProductBookB>)shopproductbookbService.QueryByPkBook(pkProductBook,null,null);	
							 if(books.size()>0){
								 ArrayList<ShopProductBookB> infolist=new ArrayList<ShopProductBookB>();
								 for(ShopProductBookB bookb:books){
									 if(bookb!=null){
										 //更新实收商品数量
										 bookb.setRealnum(bookb.getApprovenum());
										 infolist.add(bookb);
									 }else{
										 issuccess=false;
									 }
								 }
								 if(issuccess){
									 if(infolist.size()>0){
										 shopproductbookbService.updatelist(infolist);				    							    			
									 }
									 //更新单据状态
									 book.setVbillstatus(Vbillstatus.WAITING_GOOD[0]);
									 int result=shopproductbookService.updateInfo(book);	
									 if(result==0){
										 outputstr("", "提交成功", true, null);	
									 }else{
										 outputstr("", "提交失败", false, null);
									 }
								 }else{
									 outputstr("", "商品信息不完整", false, null);		
								 }
							 }else{
								 outputstr("", "明细不存在！", false, null);	
							 }	
						}else{
							outputstr("", "未通过审批的订单不允许发货", false, null);	
						}
					}else{
						outputstr("", "当前订单不存在", false, null);	
					}
				}else{
					outputstr("", "当前用户无发货权限", false, null);	
				}
			}else{
				outputstr("", "当前用户不存在", false, null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	 }
}
