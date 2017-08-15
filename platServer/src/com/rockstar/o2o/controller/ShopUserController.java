package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MD5Util;
import com.rockstar.o2o.util.PatternUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.usercode.UserCodeUtil;

/**
 * 店铺用户操作
 * @author XC
 *
 */
@Controller
@RequestMapping("/shopuser")
public class ShopUserController extends BaseController{
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/register")
	public void saveuser(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			
			if(obj.get("pkShop")==null||obj.get("pkShop").equals("")){
				outputstr("", "请选择店铺", false,null);		
			}else{
				String cellphone=obj.getString("cellphone");
				String shopusername=obj.getString("shopusername");
				String loginpassword=obj.getString("loginpassword");
				String pkShop=obj.getString("pkShop");			
				String usercode="";
				String shopcode="";
		        boolean flag =PatternUtil.validatenum(loginpassword); 
		        if(flag){
		        	outputstr("", "密码不能全是数字", false, null);
		        }else{
			        flag = PatternUtil.validatelength(loginpassword); 
			        if(flag){
						//获取店铺信息
						ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
						if(info==null){
							shopcode="AU";
						}else{
							shopcode=info.getShopcode();
						}
						//查询店铺信息SQL
						String sql=UserCodeUtil.execute(UserGroupObject.GROUP_THREE[0], shopcode,3);
						//获取用户编码
						List<Object> objectlist=shopuserService.querybysql(sql);
						if(objectlist.get(0)==null){
							usercode=shopcode+UserGroupObject.GROUP_THREE[0]+"001";
						}else{
							usercode=shopcode+UserGroupObject.GROUP_THREE[0]+objectlist.get(0).toString();	
						}
						//加密密码
						String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
						ShopUser user=new ShopUser();
						user.setShopusercode(usercode);
						user.setShopusername(shopusername);
						user.setCellphone(cellphone);
						user.setLoginpassword(secretpassword);
						user.setPkShop(info.getPkShop());
						user.setUsertype("1");
						user.setRegistertime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						user.setDr((short)0);
						user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						user=shopuserService.save(user);
						outputstr("", "新增店铺管理人员成功", true, null);
			        }else{
			        	outputstr("", "密码长度必须在6到18位", false, null);	
			        }
		        }
			}
		} catch (JSONException e) {
			dealexception(e);
            outputexceptionstr();
		}
		output(response,pojo);
    }
	
	/**
	 * 根据ID获取用户信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/finduserbyid")
	public void finduserbyid(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			String pkShopuser=obj.getString("pkShopuser");
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkShopuser));
			if(user!=null){
				JSONObject returnobj=JSONObject.fromObject(user);
			    outputstr(returnobj.toString(), "查询店铺管理员详情信息成功", true,null);
			}else{
				outputstr("", "无此管理员详情信息", true,null);
			}		
		} catch (JSONException e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response,pojo);
    }
	
	/**
	 * 店铺管理人员信息修改
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			String cellphone=obj.getString("cellphone");
			String shopusername=obj.getString("shopusername");
			String loginpassword=obj.getString("loginpassword");
			String pkShopuser=obj.getString("pkShopuser");
			boolean flag =PatternUtil.validatenum(loginpassword); 
	        if(flag){
	    	    outputstr("", "密码不能全是数字", false, null);
	        }else{
	        	flag = PatternUtil.validatelength(loginpassword); 
		        if(flag){
					String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
					ShopUser user=shopuserService.getUserById(Long.parseLong(pkShopuser));
					user.setShopusername(shopusername);
					user.setCellphone(cellphone);
					user.setLoginpassword(secretpassword);
					user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					int result=shopuserService.updateUser(user);
					if(result==0){
						outputstr("", "修改店铺管理人员信息成功", true, null);
					}else{
						outputstr("", "修改店铺管理人员信息失败", false, null);
					}
		        }else{
		        	outputstr("", "密码长度必须在6到18位", false, null);	
		        }
	        }
		} catch (JSONException e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response,pojo);
    }
	
	
	/**
	 * 查询店铺管理人员信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/shopuserlist")
	public void shopuserlist(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//省
			String province=(String)obj.get("province");
			//市
			String city=(String)obj.get("city");
			//区
			String county=(String)obj.get("county");
			//店铺主键
			String pkShop=(String)obj.get("pkShop");
			
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			//根据条件查询
			List<Object> list=new ArrayList<Object>();
			List<Object> totalList=new ArrayList<Object>();
			if(!curpage.equals("")){
				list=(ArrayList<Object>) shopuserService.getUserByCon(province,city,county,pkShop,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				totalList=(ArrayList<Object>) shopuserService.getUserByCon(province,city,county,pkShop,null,null);
			}else{
				list=(ArrayList<Object>) shopuserService.getUserByCon(province,city,county,pkShop,null,null);
			}
			if(list.size()>0){
				String data=JSONArray.fromObject(list).toString();
				if(!curpage.equals("")){			
					outputstr(data, "查询店铺管理人员信息成功", true,totalList.size());
				}else{
					outputstr(data, "查询店铺管理人员信息成功", true,list.size());	
				}
			}else{
				outputstr("", "暂无店铺管理人员信息", true,null);
			}
		 } catch (JSONException e) {
			 dealexception(e);
			 outputexceptionstr();
		 }
		 output(response,pojo);
	 }
	
}
