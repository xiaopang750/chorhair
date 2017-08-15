package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.OrderFrom;
import com.rockstar.o2o.pojo.PlatUser;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopGroup;
import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.jsonvalidator.Validator;


/**
 * 店铺套餐价格组维护
 * @author xc
 *
 */
@Controller
@RequestMapping("/pricegroup")
public class ShopPriceGroupController extends BaseController {
	   /**
	   * 保存价格组对应的价格及提成项
	   */
	   @RequestMapping("/saveprice")
		public void saveprice(HttpServletRequest request,HttpServletResponse response,Model model) {
		   try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShop=obj.getString("pkShop");
				String pkUser=obj.getString("pkUser");
				String pkShopGroup=obj.getString("pkShopGroup");
				String price=obj.getString("price");
				String servicerank=obj.getString("servicerank");
				String awards=obj.get("awards")==null?"":obj.getString("awards");
				boolean iscontinue=true;
				if(awards!=null&&!awards.equals("")){
			    	Validator validator=new Validator(Validator.class.getResourceAsStream("fairerawardsplit"));
			    	int i=validator.validate(awards);		    	
			    	if(i>0){
			    		outputstr("", "提成项目录入有误或超过4项", false, null);
			    		iscontinue=false;
			    	}
				}
				if(iscontinue){
					PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
					if(user!=null&&user.getUsertype().equals("2")){
						//店铺价格表维护
						ShopPrice newprice=new ShopPrice();
						newprice.setPrice(Double.parseDouble(price));
						newprice.setServicerank(servicerank);
						newprice.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						newprice.setDr((short)0);
						newprice.setPkShopPricegroup(Long.parseLong(pkShopGroup));
						newprice=shoppriceService.save(newprice);
						ArrayList<ShopFaireraward> awardlist=new ArrayList<ShopFaireraward>();
						if(!awards.equals("")){
							JSONArray awardarr=JSONArray.fromObject(awards);
							Iterator<?> iter=awardarr.iterator();
							while(iter.hasNext()){
								JSONObject oneobj=(JSONObject) iter.next();
								String awardname=oneobj.getString("awardname");//提成名称
								String israte=oneobj.getString("israte");//提成方式
								String awardmoney=oneobj.getString("awardmoney");//提成值
								//理发师提成
								ShopFaireraward award=new ShopFaireraward();
								award.setAwardtype("SERVICE");
								award.setPkKey(newprice.getPkPrice());
								award.setPkShop(Long.parseLong(pkShop));
								award.setIsrate(israte);
								award.setAwardname(awardname);
								award.setIsvalidate("Y");
								award.setAwardmoney(Double.parseDouble(awardmoney));
								award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								award.setDr((short)0);
								award.setOperatorid(Long.parseLong(pkUser));
								award.setOperatename(user.getPlatusername());
								award.setOperatorfrom(OrderFrom.FROM_PLAT[0]);
								award.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								awardlist.add(award);
							}
							//批量保存提成项
							if(awardlist.size()>0){
								shopfairerawardService.savelist(awardlist);
							}
						}
					    outputstr(JSONObject.fromObject(newprice).toString(), "新增价格及提成项成功", true, null);
					 }else{
						outputstr("", "信息有误", false, null);
					 }
				}
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			 }
			 output(response, pojo);
	   }
	 
	   	/**
        * 修改价格组对应的价格及提成项
	   	*/
	   	@RequestMapping("/editprice")
		public void editprice(HttpServletRequest request,HttpServletResponse response,Model model) {
		   try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShop=obj.getString("pkShop");
				String pkUser=obj.getString("pkUser");
				String price=obj.getString("price");
				String servicerank=obj.getString("servicerank");
				String pkPrice=obj.getString("pkPrice");//价格主键
				String awards=obj.get("awards")==null?"":obj.getString("awards");//提成项目组
				
				boolean iscontinue=true;
				if(awards!=null&&!awards.equals("")){
			    	Validator validator=new Validator(Validator.class.getResourceAsStream("fairerawardsplit"));
			    	int i=validator.validate(awards);		    	
			    	if(i>0){
			    		outputstr("", "提成项目录入有误或超过4项", false, null);
			    		iscontinue=false;
			    	}
				}
				if(iscontinue){
					PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
					if(user!=null&&user.getUsertype().equals("2")){
						//服务等级价格
						ShopPrice oldprice=shoppriceService.getPriceById(Long.parseLong(pkPrice));		
						if(oldprice!=null){
							 oldprice.setPrice(Double.parseDouble(price));
							 oldprice.setServicerank(servicerank);	
							 shoppriceService.updatePrice(oldprice);
							 ArrayList<Object> pklist=new ArrayList<Object>();
							 if(!awards.equals("")){
								 JSONArray awardarr=JSONArray.fromObject(awards);
								 Iterator<?> iter=awardarr.iterator();
								 ArrayList<ShopFaireraward> addlist=new ArrayList<ShopFaireraward>();
								 ArrayList<ShopFaireraward> updatelist=new ArrayList<ShopFaireraward>();
								 while(iter.hasNext()){
									JSONObject oneobj=(JSONObject) iter.next();
									String awardname=oneobj.getString("awardname");//提成名称
									String israte=oneobj.getString("israte");//提成方式
									String awardmoney=oneobj.getString("awardmoney");//提成值
									String pkFaireraward=oneobj.get("pkFaireraward")==null?"":oneobj.getString("pkFaireraward");//主键
									String dr=oneobj.get("dr")==null?"0":oneobj.getString("dr");//删除标志
									//修改提成项
									if(!pkFaireraward.equals("")){
										ShopFaireraward oldaward=shopfairerawardService.getawardById(Long.parseLong(pkFaireraward));
										oldaward.setAwardmoney(Double.parseDouble(awardmoney));
										oldaward.setIsrate(israte);
										oldaward.setAwardname(awardname);
										oldaward.setDr(Short.parseShort(dr));
										updatelist.add(oldaward);
									}else{
										//新增提成项
										ShopFaireraward award=new ShopFaireraward();
										award.setAwardtype("SERVICE");
										award.setPkKey(Long.parseLong(pkPrice));
										award.setPkShop(Long.parseLong(pkShop));
										award.setIsrate(israte);
										award.setAwardname(awardname);
										award.setIsvalidate("Y");
										award.setAwardmoney(Double.parseDouble(awardmoney));
										award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
										award.setDr((short)0);
										award.setOperatorid(Long.parseLong(pkUser));
										award.setOperatename(user.getPlatusername());
										award.setOperatorfrom(OrderFrom.FROM_PLAT[0]);
										award.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
										addlist.add(award);
									}
								}
								if(updatelist.size()>0){
									shopfairerawardService.updatelist(updatelist);
								}
								if(addlist.size()>0){
									pklist=(ArrayList<Object>) shopfairerawardService.savelists(addlist);	
								}
							 }
							 outputstr(pklist.size()>0?JSONArray.fromObject(pklist).toString():"", "维护价格及提成项成功", true, null);
						}else{
							outputstr("", "信息不存在", false, null);	
						}
				 }else{
					outputstr("", "输入有误", false, null);
				 }
				 }
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
	   }
		   
	  /**
	  * 保存店铺价格组
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
				String groupname=obj.getString("groupname");
				PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
				if(user!=null&&user.getUsertype().equals("2")){
					ShopGroup group=new ShopGroup();
					group.setGroupname(groupname);
					group.setPkShop(Long.parseLong(pkShop));
					group.setGrouptype("SERVICE");
					group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					group.setDr((short)0);
					group.setOperatorid(Long.parseLong(pkUser));
					group.setOperatorname(user.getPlatusername());
					group.setOperatorfrom(OrderFrom.FROM_PLAT[0]);
					group.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					group=shopgroupService.save(group);				
					outputstr(JSONObject.fromObject(group).toString(), "新增价格组成功", true, null);
				}else{
					outputstr("", "输入有误", false, null);
				}
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
	   }
	   
	   
	   /**
	  * 修改店铺价格组
	  * @param request
	  * @param response
	  * @param model
	  */
	   @RequestMapping("/edit")
		public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {
			try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkUser=obj.getString("pkUser");
				String groupname=obj.getString("groupname");
				String pkShopGroup=obj.getString("pkShopGroup");
				PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
				if(user!=null&&user.getUsertype().equals("2")){
					ShopGroup oldgroup=shopgroupService.getGroupById(Long.parseLong(pkShopGroup));
					if(oldgroup!=null){																	
						//更新主表内容
						oldgroup.setGroupname(groupname);
						shopgroupService.updateGroup(oldgroup);
						outputstr("", "维护价格组成功", true, null);
					}else{
						outputstr("", "当前价格组不存在", false, null);
					}
				}else{
					outputstr("", "输入有误", false, null);
				}
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			 }
			 output(response, pojo);
		}
		   
	   
	      /**
		  * 根据店铺查询价格组
		  * @param request
		  * @param response
		  * @param model
		  */
	   	@RequestMapping("/findbyshop")
		public void findbyshop(HttpServletRequest request,HttpServletResponse response,Model model) {
		   try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShop=obj.getString("pkShop");
				ArrayList<ShopGroup> groups=(ArrayList<ShopGroup>) shopgroupService.QueryByShop(pkShop,"SERVICE");
				if(groups.size()>0){
					String data=JSONArray.fromObject(groups).toString();
					outputstr(data, "查询价格组成功", true, null);
				}else{
					outputstr("", "暂无价格组", true, null);
				}
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
	   }
		 
      /**
	  * 根据价格组查询子集列表
	  * @param request
	  * @param response
	  * @param model
	  */
	   @RequestMapping("/findprice")
		public void findprice(HttpServletRequest request,HttpServletResponse response,Model model) {
			try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShopGroup=obj.getString("pkShopGroup");
				ArrayList<ShopPrice> prices=(ArrayList<ShopPrice>) shoppriceService.QueryBygroup(pkShopGroup);
				if(prices.size()>0){
					String data=JSONArray.fromObject(prices).toString();
					outputstr(data, "查询价格成功", true, null);
				}else{
					outputstr("", "暂无价格", true, null);
				}
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
	   }
		   
	   /**
	    * 根据价格查询附价格的提成项
	    * @param request
	    * @param response
	    * @param model
	    */
	   @RequestMapping("/findaward")
		public void findaward(HttpServletRequest request,HttpServletResponse response,Model model) {
			try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShop=obj.getString("pkShop");
				String pkKey=obj.getString("pkPrice");
				ArrayList<ShopFaireraward> list=(ArrayList<ShopFaireraward>) shopfairerawardService.querybyshopandtype(pkShop, pkKey, "SERVICE");
			    if(list.size()>0){
			    	String data=JSONArray.fromObject(list).toString();
			    	outputstr(data, "查询提成项成功", true, null);
			    }else{
			    	outputstr("", "暂无提成项", false, null);
			    }
			} catch (Exception e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
	   }
		   
	   /**
	     * 启用或者禁用提成项目
	     * @param request
	     * @param response
	     * @param model
	     */
	   @RequestMapping("/validate")
		public void stop(HttpServletRequest request,HttpServletResponse response,Model model) {
		   try {
			   JSONObject obj=RequestUtil.getPostString(request);
			   String pkFaireraward=obj.getString("pkFaireraward");
			   String isvalidate=obj.getString("isvalidate");
			   ShopFaireraward award=shopfairerawardService.getawardById(Long.parseLong(pkFaireraward));
		       if(award!=null){
		    	   award.setIsvalidate(isvalidate);
		    	   shopfairerawardService.updateaward(award);
		    	   outputstr("", isvalidate.equals("Y")?"启用成功":"禁用成功", true, null);
		       }else{
		    	   outputstr("", "无此提成项", false, null);   
		       }
		   } catch (Exception e) {
			   dealexception(e);
			   outputexceptionstr();
		   }
		   output(response, pojo);
	   }
}
