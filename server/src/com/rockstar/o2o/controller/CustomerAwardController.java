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
import com.rockstar.o2o.pojo.BdCustomerAward;
import com.rockstar.o2o.pojo.BdCustomerAwardLimit;
import com.rockstar.o2o.pojo.ShopGroup;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 消费者奖励定义
 * @author xc
 *
 */
@Controller
@RequestMapping("customeraward")
public class CustomerAwardController extends BaseController {

		  		   
	/**
	 * 保存抵用券
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/save")
	public void saveinfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkFrom=(String)obj.get("pkShop");	
			String vouchers=obj.getString("vouchers");
			String groupname=obj.getString("groupname");
			String pkUser=obj.getString("pkUser");
			
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			
			if(user!=null&&user.getPkShop().equals(Long.parseLong(pkFrom))){
			ShopGroup group=new ShopGroup();
			group.setGroupname(groupname);
			group.setPkShop((pkFrom!=null&&!pkFrom.equals(""))?Long.parseLong(pkFrom):null);
			group.setGrouptype("VOUCHER");
			group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			group.setDr((short)0);
			group.setOperatorid(Long.parseLong(pkUser));
			group.setOperatorname(user.getShopusername());
			group.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			group=shopgroupService.save(group);				
			
			
			JSONArray arr=JSONArray.fromObject(vouchers);
			Iterator<?> iter=arr.iterator();
			ArrayList<BdCustomerAward> awards=new ArrayList<BdCustomerAward>();
			while(iter.hasNext()){
				JSONObject getobj=(JSONObject) iter.next();
				String fairtype=getobj.get("fairtype")==null?null:getobj.getString("fairtype");
				String faittypename=getobj.get("faittypename")==null?null:getobj.getString("faittypename");
				String awardfrom=getobj.get("awardfrom")==null?"PLAT":getobj.getString("awardfrom");
				String awardtype=getobj.get("awardtype")==null?"VOUCHERS":getobj.getString("awardtype");
				String awardname=getobj.getString("awardname");//抵用券名称
				String awardvalue=getobj.getString("awardvalue");//抵用券金额
				
				BdCustomerAward award=new BdCustomerAward();
				award.setAwardfrom(awardfrom);
				award.setPkShopGroup(group.getPkShopGroup());
				award.setAwardtype(awardtype);
				award.setAwardname(awardname);
				award.setAwardvalue(awardvalue);
				award.setAwardstatus("001");
				award.setFairtype(fairtype);
				award.setFaittypename(faittypename);
				award.setPkFrom(pkFrom==null?null:Long.parseLong(pkFrom));
				award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				award.setDr((short)0);
				
				awards.add(award);	
			}
			
			if(awards.size()>0){
				customerawardService.savelist(awards);
			
			}
			
			outputstr(JSONObject.fromObject(group).toString(), "新增抵用券成功", true, null);	
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
	 * 修改抵用券信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void editinfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String pkUser=obj.getString("pkUser");;
			String groupname=obj.getString("groupname");
			String pkShopGroup=obj.getString("pkShopGroup");//价格主键
			
			String awards=obj.get("vouchers")==null?"":obj.getString("vouchers");//提成项目组
			
			
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null&&user.getPkShop().equals(Long.parseLong(pkShop))){
				
					ShopGroup oldgroup=shopgroupService.getGroupById(Long.parseLong(pkShopGroup));		
					if(oldgroup!=null){
                     oldgroup.setGroupname(groupname);	
                     shopgroupService.updateGroup(oldgroup);
                     ArrayList<Object> addobj=new ArrayList<Object>();
                     
					 if(!awards.equals("")){
						 JSONArray awardarr=JSONArray.fromObject(awards);
						 Iterator<?> iter=awardarr.iterator();
						 ArrayList<BdCustomerAward> addlist=new ArrayList<BdCustomerAward>();
						 ArrayList<BdCustomerAward> updatelist=new ArrayList<BdCustomerAward>();
						
							while(iter.hasNext()){
								JSONObject getobj=(JSONObject) iter.next();
								String fairtype=getobj.get("fairtype")==null?null:getobj.getString("fairtype");
								String faittypename=getobj.get("faittypename")==null?null:getobj.getString("faittypename");
								String awardfrom=getobj.get("awardfrom")==null?"PLAT":getobj.getString("awardfrom");
								String awardtype=getobj.get("awardtype")==null?"VOUCHERS":getobj.getString("awardtype");
								String pkCustomeraward=getobj.get("pkCustomeraward")==null?"":getobj.getString("pkCustomeraward");
								String awardname=getobj.getString("awardname");//抵用券名称
								String awardvalue=getobj.getString("awardvalue");//抵用券金额
								String dr=getobj.get("dr")==null?"0":getobj.getString("dr");//删除标志
								
								//修改提成项
								if(!pkCustomeraward.equals("")){
									BdCustomerAward oldaward=customerawardService.getAwardById(Long.parseLong(pkCustomeraward));
									oldaward.setAwardtype(awardtype);
									oldaward.setAwardname(awardname);
									oldaward.setAwardvalue(awardvalue);
									oldaward.setFairtype(fairtype);
									oldaward.setFaittypename(faittypename);
									oldaward.setDr(Short.parseShort(dr));
									updatelist.add(oldaward);
									
								}else{
									//新增提成项
									BdCustomerAward award=new BdCustomerAward();
									award.setAwardfrom(awardfrom);
									award.setPkShopGroup(Long.parseLong(pkShopGroup));
									award.setAwardtype(awardtype);
									award.setAwardname(awardname);
									award.setAwardvalue(awardvalue);
									award.setFairtype(fairtype);
									award.setAwardstatus("001");
									award.setFaittypename(faittypename);
									award.setPkFrom(Long.parseLong(pkShop));
									award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									award.setDr((short)0);
									
									addlist.add(award);
								}
								
							}
							
							if(updatelist.size()>0){
								customerawardService.updatelist(updatelist);
							}
							
							if(addlist.size()>0){
							addobj=(ArrayList<Object>) customerawardService.savelists(addlist);	
							}
					  }
					
					 outputstr(addobj.size()>0?JSONArray.fromObject(addobj).toString():"", "维护价格及提成项成功", true, null);
					 
					}else{
						
					outputstr("", "信息不存在", false, null);	
					
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
	 * 保存奖励限制表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/savelimit")
	public void savelimit(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCustomeraward=obj.getString("pkCustomeraward");//主表主键
			String combos=obj.getString("combos");//适用的套餐
			
			JSONArray arr=JSONArray.fromObject(combos);
			Iterator<?> iter=arr.iterator();
			ArrayList<BdCustomerAwardLimit> limitlists=new ArrayList<BdCustomerAwardLimit>();
			while(iter.hasNext()){
				JSONObject oneobj=(JSONObject) iter.next();
				String pk_combo=oneobj.getString("pk_combo");
				String comboname=oneobj.getString("comboname");
				
				BdCustomerAwardLimit limit=new BdCustomerAwardLimit();
				limit.setCombofrom("PLAT");
				limit.setComboname(comboname);
				limit.setPkCombo(Long.parseLong(pk_combo));
				limit.setPkCustomeraward(Long.parseLong(pkCustomeraward));
				limit.setDr((short)0);
				limit.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				
				limitlists.add(limit);
			}
			
			if(limitlists.size()>0){
				customerawardlimitService.savelist(limitlists);
			}
			
			outputstr("", "保存适用套餐成功", true, null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 保存奖励限制表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editlimit")
	public void editlimit(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCustomeraward=obj.getString("pkCustomeraward");//主表主键
			String combos=obj.getString("combos");//适用的套餐
			
			JSONArray arr=JSONArray.fromObject(combos);
			Iterator<?> iter=arr.iterator();
			ArrayList<BdCustomerAwardLimit> addlimitlists=new ArrayList<BdCustomerAwardLimit>();
			ArrayList<BdCustomerAwardLimit> updatelimitlists=new ArrayList<BdCustomerAwardLimit>();
			while(iter.hasNext()){
				JSONObject oneobj=(JSONObject) iter.next();
				String pk_combo=oneobj.get("pkCombo")==null?null:oneobj.getString("pkCombo");
				String comboname=oneobj.get("comboname")==null?null:oneobj.getString("comboname");
				String dr=oneobj.get("dr")==null?"0":oneobj.getString("dr");
				String pkCustomerawardLimit=oneobj.get("pkCustomerawardLimit")==null?"":oneobj.getString("pkCustomerawardLimit");
				
				
				if(pkCustomerawardLimit.equals("")){
					BdCustomerAwardLimit limit=new BdCustomerAwardLimit();
					limit.setCombofrom("PLAT");
					limit.setComboname(comboname);
					limit.setPkCombo(Long.parseLong(pk_combo));
					limit.setPkCustomeraward(Long.parseLong(pkCustomeraward));
					limit.setDr((short)0);
					limit.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					
					addlimitlists.add(limit);
				}else{
					BdCustomerAwardLimit limit=customerawardlimitService.getAwardById(Long.parseLong(pkCustomerawardLimit));					
					limit.setDr(Short.parseShort(dr));
					updatelimitlists.add(limit);
				}
				
			}
			
			if(addlimitlists.size()>0){
				customerawardlimitService.savelist(addlimitlists);
			} 
			
			if(updatelimitlists.size()>0){
				customerawardlimitService.updatelist(updatelimitlists);
			}
			
			
			outputstr("", "修改适用套餐成功", true, null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);		
	}
	
	
	/**
	 * 查询抵用券
	 * @param request
	 * @param response
	 * @param model
	 */
		@RequestMapping("/findvoucher")
		public void findvoucher(HttpServletRequest request,HttpServletResponse response,Model model) {
		 try {
			 JSONObject obj=RequestUtil.getPostString(request);
			 String fairtype=obj.get("fairtype")==null?"":obj.getString("fairtype");
			 
			ArrayList<BdCustomerAward> arrlist=(ArrayList<BdCustomerAward>) customerawardService.QueryBypkShop("", fairtype);
		    
			if(arrlist.size()>0){
				String data=JSONArray.fromObject(arrlist).toString();
				outputstr(data, "查询抵用券成功", true, null);
			}else{
				outputstr("", "暂无抵用券", true, null);
			}
		 
		 } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);		
	 }
	
		/**
		 * 根据抵用券查询适用套餐
		 * @param request
		 * @param response
		 * @param model
		 */
			@RequestMapping("/findlimit")
			public void findlimit(HttpServletRequest request,HttpServletResponse response,Model model) {
			 try {
				 JSONObject obj=RequestUtil.getPostString(request);
				String pkCustomeraward=obj.getString("pkCustomeraward");//主表主键
				 
				ArrayList<Object> arrlist=(ArrayList<Object>) customerawardService.queryselect(pkCustomeraward);
			    
				if(arrlist.size()>0){
					String data=JSONArray.fromObject(arrlist).toString();
					outputstr(data, "查询抵用券适用套餐成功", true, null);
				}else{
					outputstr("", "暂无抵用券适用套餐", true, null);
				}
			 
			 } catch (Exception e) {
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);		
		 }
			
			/**
			 * 查询抵用券组
			 * @param request
			 * @param response
			 * @param model
			 */
			@RequestMapping("/findgroup")
			public void findgroup(HttpServletRequest request,HttpServletResponse response,Model model) {
				try {
					JSONObject obj=RequestUtil.getPostString(request);
					String pkShop=obj.getString("pkShop");
					
					ArrayList<ShopGroup> groups=(ArrayList<ShopGroup>) shopgroupService.QueryByShop(pkShop, "VOUCHER");
				 
					if(groups.size()>0){
						outputstr(JSONArray.fromObject(groups).toString(), "查询抵用券组成功", true, null);
					}else{
						outputstr("", "暂无抵用券组", true, null);
					}
				
				} catch (Exception e) {
					// TODO: handle exception
					dealexception(e);
					outputexceptionstr();
				}
				output(response, pojo);		
			}
			
			/**
			 * 根据组查询抵用券
			 * @param request
			 * @param response
			 * @param model
			 */
			@RequestMapping("/findbygroup")
			public void findbygroup(HttpServletRequest request,HttpServletResponse response,Model model) {
				try {
					JSONObject obj=RequestUtil.getPostString(request);
					String pkShopGroup=obj.getString("pkShopGroup");
				
					ArrayList<BdCustomerAward> awards=(ArrayList<BdCustomerAward>) customerawardService.QueryByGroup(pkShopGroup);
					if(awards.size()>0){
						String data=JSONArray.fromObject(awards).toString();
						outputstr(data, "查询抵用券成功", true, null);	
					}else{
						outputstr("", "暂无抵用券", true, null);
					}
				} catch (Exception e) {
					// TODO: handle exception
					dealexception(e);
					outputexceptionstr();
				}
				output(response, pojo);		
			}
}
