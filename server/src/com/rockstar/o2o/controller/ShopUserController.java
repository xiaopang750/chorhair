package com.rockstar.o2o.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.UserGroup;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MD5Util;
import com.rockstar.o2o.util.PatternUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.des.EncodeUtil;
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
	 * 用户登录验证
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/login")
	public void userlogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String usercode=obj.getString("usercode");
			String usergroup=obj.getString("usergroup");
			String loginpassword=obj.getString("loginpassword");
			String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
			//校验用户的账号密码是否正确
			ShopUser haveuser=shopuserService.login(usercode,secretpassword,usergroup);
			if(haveuser==null){
			 	outputstr("", "用户名或密码错误", false, null);
			}else{
				if(haveuser.getIslock()!=null&&haveuser.getIslock().equals("Y")){
					outputstr("", "用户已被锁定", false, null);
				}else{
				 JSONObject successstr=new JSONObject();
				 successstr.accumulate("cellphone", haveuser.getCellphone());
				 successstr.accumulate("pkUser", EncodeUtil.encode((haveuser.getUsertype().equals("1")?"shop":"plat")+":"+haveuser.getPkShopuser().toString()));
				 successstr.accumulate("username", haveuser.getShopusername());
				 successstr.accumulate("pkShop", haveuser.getPkShop());
				 successstr.accumulate("usertype", haveuser.getUsertype());
				 ShopInfo info=shopinfoService.getShopById(haveuser.getPkShop());
				 successstr.accumulate("shopname",info.getShopname());
				 outputstr(successstr.toString(), "登陆成功", true, null);
				  }
		    	}
			
			} catch (JSONException e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response,pojo);
	    }
	
	
	/**
	 * 记住账号
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/saveaccount")
	public void saveaccount(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser= obj.getString("pkUser");

			//校验用户的账号密码是否正确
			ShopUser haveuser=shopuserService.getUserById(Long.parseLong(pkUser));
			if(haveuser==null){
				 outputstr("", "用户名或密码错误", false, null);
			}else{
				if(haveuser.getIslock()!=null&&haveuser.getIslock().equals("Y")){

				 outputstr("", "用户已被锁定", false, null);
				}else{
				
				 JSONObject successstr=new JSONObject();
				 successstr.accumulate("cellphone", haveuser.getCellphone());
				 successstr.accumulate("pkUser", haveuser.getPkShopuser());
				 successstr.accumulate("pkShop", haveuser.getPkShop());
				 successstr.accumulate("username", haveuser.getShopusername());
				 successstr.accumulate("usertype", haveuser.getUsertype());
				 ShopInfo info=shopinfoService.getShopById(haveuser.getPkShop());
				 successstr.accumulate("shopname",info.getShopname());
				 outputstr(successstr.toString(), "登录成功", true, null);
				  }
		    	}
			
			} catch (JSONException e) {
				dealexception(e);
				outputexceptionstr();
			}
			output(response,pojo);
	    }
	
	
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
			String cellphone=obj.getString("cellphone");
			String username=obj.getString("username");
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
				shopcode=info.getShopshortcode();
			}
			//查询店铺信息SQL
			String sql=UserCodeUtil.execute(UserGroup.GROUP_THREE[0], shopcode,3);
			
			String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
			
			//获取用户编码
			List<Object> objectlist=shopuserService.querybysql(sql);
			if(objectlist.get(0)==null){
				usercode=shopcode+UserGroup.GROUP_THREE[0]+"001";
			}else{
				usercode=shopcode+UserGroup.GROUP_THREE[0]+objectlist.get(0).toString();	
			}
			ShopUser user=new ShopUser();
			user.setShopusercode(usercode);
			user.setShopusername(username);
			user.setCellphone(cellphone);
			user.setLoginpassword(secretpassword);
			user.setPkShop(info.getPkShop());
			user.setDr((short)0);
			user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			
			user=shopuserService.save(user);
			
			 outputstr("", "新增用户成功", true, null);
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
	 * 用户信息修改
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			String cellphone=obj.getString("cellphone");
			String username=obj.getString("username");
			String loginpassword=obj.getString("loginpassword");
			String pkShopuser=obj.getString("pkShopuser");
			
			String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
			
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkShopuser));
			user.setShopusername(username);
			user.setCellphone(cellphone);
			user.setLoginpassword(secretpassword);
			user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			
			int result=shopuserService.updateUser(user);
			if(result==0){
				outputstr("", "修改信息成功", true, null);
			}else{
				outputstr("", "修改信息失败", false, null);
			  }			
			} catch (JSONException e) {
				dealexception(e);
				outputexceptionstr();
			}
			
			output(response,pojo);
	    }
	
	
	/**
	 * 用户密码自行修改
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editpassword")
	public void editpassword(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			String oldpassword=obj.getString("oldpassword");
			String newpassword=obj.getString("newpassword");
			String pkShopuser=obj.getString("pkUser");
			
			String secretoldpassword=MD5Util.MD5Encode(oldpassword, "utf-8");
			
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkShopuser));
			  
			//校验原密码是否正确,正确的话修改新密码,不正确则提示
			if(secretoldpassword.equals(user.getLoginpassword())){
				   boolean flag =PatternUtil.validatenum(newpassword); 
			        if(flag){
			        	outputstr("", "密码不能全是数字", false, null);
			        }else{
			        flag = PatternUtil.validatelength(newpassword); 
			        if(flag){
				String secretnewpassword=MD5Util.MD5Encode(newpassword, "utf-8");
				user.setLoginpassword(secretnewpassword);
				user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				int result=shopuserService.updateUser(user);
				if(result==0){
					outputstr("", "密码修改成功,请重新登录", true, null);
				}else{
					outputstr("", "密码修改失败", false, null);
				  }	
			        }else{
			        	outputstr("", "密码长度必须在6到18位", false, null);
			        }
			        }
			    }else{
				outputstr("", "原密码输入有误,请重新输入", false, null);
			   }
			} catch (JSONException e) {
				dealexception(e);
				outputexceptionstr();
			}
			
			output(response,pojo);
	    }
	
}
