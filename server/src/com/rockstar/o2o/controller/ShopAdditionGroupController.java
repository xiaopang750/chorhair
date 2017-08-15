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
import com.rockstar.o2o.pojo.ShopAddition;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopGroup;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.jsonvalidator.Validator;


@Controller
@RequestMapping("/additiongroup")
public class ShopAdditionGroupController extends BaseController{

	
	   /**
	   * 保存附加项目组对应的价附加项目及提成项
	   */
	   @RequestMapping("/saveaddition")
		public void saveprice(HttpServletRequest request,HttpServletResponse response,Model model) {
		   try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShop=obj.getString("pkShop");
				String pkUser=obj.getString("pkUser");
				String pkShopGroup=obj.getString("pkShopGroup");
				String additionname=obj.getString("additionname");
				
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
				ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
				if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
																					
						ShopAddition newaddition=new ShopAddition();
						newaddition.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						newaddition.setDr((short)0);
						newaddition.setPkAdditionGroup(Long.parseLong(pkShopGroup));
						newaddition.setAdditionname(additionname);
						newaddition=shopadditionService.save(newaddition);
						
						ArrayList<ShopFaireraward> awardlist=new ArrayList<ShopFaireraward>();
						if(!awards.equals("")){
							JSONArray awardarr=JSONArray.fromObject(awards);
							Iterator<?> iter=awardarr.iterator();
							while(iter.hasNext()){
								JSONObject oneobj=(JSONObject) iter.next();
								String awardname=oneobj.getString("awardname");//提成名称
								String israte=oneobj.getString("israte");//提成方式
								String awardmoney=oneobj.getString("awardmoney");//提成值
								
								ShopFaireraward award=new ShopFaireraward();
								award.setAwardtype("ADDITION");
								award.setIsvalidate("Y");
								award.setPkKey(newaddition.getPkAddition());
								award.setPkShop(Long.parseLong(pkShop));
								award.setIsrate(israte);
								award.setAwardname(awardname);
								award.setAwardmoney(Double.parseDouble(awardmoney));
								award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								award.setDr((short)0);
								award.setOperatorid(Long.parseLong(pkUser));
								award.setOperatename(user.getShopusername());
								award.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								
								awardlist.add(award);
							}
							
							
							//批量保存提成项
							if(awardlist.size()>0){
								shopfairerawardService.savelist(awardlist);
							}
						}
						
					    outputstr(JSONObject.fromObject(newaddition).toString(), "新增附加项目及提成项成功", true, null);
					
				 }else{
					outputstr("", "信息有误", false, null);
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
		   * 修改附加项目组对应的价格及提成项
		   */
		   @RequestMapping("/editaddition")
			public void editprice(HttpServletRequest request,HttpServletResponse response,Model model) {
			   try {
					JSONObject obj=RequestUtil.getPostString(request);
					String pkShop=obj.getString("pkShop");
					String pkUser=obj.getString("pkUser");
					String pkAddition=obj.getString("pkAddition");//附加项目主键
					String additionname=obj.getString("additionname");
					
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
					ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
					if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
						
							ShopAddition oldaddition=shopadditionService.getAddtionById(Long.parseLong(pkAddition));		
							if(oldaddition!=null){
							 oldaddition.setAdditionname(additionname);
							 shopadditionService.updateAddition(oldaddition);
							 
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
										String dr=oneobj.get("dr")==null?"0":oneobj.getString("dr");//删除标志								//修改提成项
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
											award.setAwardtype("ADDITION");
											award.setPkShop(Long.parseLong(pkShop));
											award.setPkKey(Long.parseLong(pkAddition));
											award.setIsrate(israte);
											award.setIsvalidate("Y");
											award.setAwardname(awardname);
											award.setAwardmoney(Double.parseDouble(awardmoney));
											award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
											award.setDr((short)0);
											award.setOperatorid(Long.parseLong(pkUser));
											award.setOperatename(user.getShopusername());
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
							
							 outputstr(pklist.size()>0?JSONArray.fromObject(pklist).toString():"", "维护附加项目及提成项成功", true, null);
							 
							}else{
								
							outputstr("", "信息不存在", false, null);	
							
							}
					 }else{
						outputstr("", "输入有误", false, null);
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
	  * 保存店铺附加项目组
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
			
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
				ShopGroup group=new ShopGroup();
				group.setGroupname(groupname);
				group.setPkShop(Long.parseLong(pkShop));
				group.setGrouptype("ADDITION");
				group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				group.setDr((short)0);
				group.setOperatorid(Long.parseLong(pkUser));
				group.setOperatorname(user.getShopusername());
				group.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				group=shopgroupService.save(group);				
				
				outputstr(JSONObject.fromObject(group).toString(), "新增附加项目组成功", true, null);
				
			}else{
				outputstr("", "输入有误", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
				String pkShop=obj.getString("pkShop");
				String pkUser=obj.getString("pkUser");
				String groupname=obj.getString("groupname");
				String pkShopGroup=obj.getString("pkShopGroup");
				
				ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
				if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
					ShopGroup oldgroup=shopgroupService.getGroupById(Long.parseLong(pkShopGroup));
					if(oldgroup!=null){																	
						//更新主表内容
						oldgroup.setGroupname(groupname);
						shopgroupService.updateGroup(oldgroup);
						
						outputstr("", "维护附加项目组成功", true, null);
					}else{
						outputstr("", "当前附加项目组不存在", false, null);
					}
					
				}else{
					outputstr("", "输入有误", false, null);
				}
			} catch (Exception e) {
				// TODO: handle exception
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
				ArrayList<ShopGroup> groups=(ArrayList<ShopGroup>) shopgroupService.QueryByShop(pkShop,"ADDITION");
				if(groups.size()>0){
					String data=JSONArray.fromObject(groups).toString();
					outputstr(data, "查询附加项目组成功", true, null);
				}else{
					outputstr("", "暂无附加项目组", true, null);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
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
		   @RequestMapping("/findaddition")
			public void findaddition(HttpServletRequest request,HttpServletResponse response,Model model) {
			   try {
				JSONObject obj=RequestUtil.getPostString(request);
				//String pkShop=obj.getString("pkShop");
				String pkShopGroup=obj.getString("pkShopGroup");
				ArrayList<ShopAddition> additions=(ArrayList<ShopAddition>) shopadditionService.QueryBygroup(pkShopGroup);
				if(additions.size()>0){
					String data=JSONArray.fromObject(additions).toString();
					outputstr(data, "查询附加项目成功", true, null);
				}else{
					outputstr("", "暂无附加项目", true, null);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			}
			 output(response, pojo);
		   }
		   
		   /**
		    * 根据附加项目查询附加项目的提成项
		    * @param request
		    * @param response
		    * @param model
		    */
		   @RequestMapping("/findaward")
			public void findaward(HttpServletRequest request,HttpServletResponse response,Model model) {
			   try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShop=obj.getString("pkShop");
				String pkKey=obj.getString("pkAddition");
				ArrayList<ShopFaireraward> list=(ArrayList<ShopFaireraward>) shopfairerawardService.querybyshopandtype(pkShop, pkKey, "ADDITION");
			    if(list.size()>0){
			    	String data=JSONArray.fromObject(list).toString();
			    	outputstr(data, "查询提成项成功", true, null);
			    }else{
			    	outputstr("", "暂无提成项", false, null);
			    }
			   
			   } catch (Exception e) {
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			}
			 output(response, pojo);
			   
		   }
		   
		   
		    /**
		     * 禁用提成项目
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
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			    }
			 output(response, pojo);
		   }
		   
}
