package com.rockstar.o2o.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.pojo.PlatUser;
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
@RequestMapping("/platuser")
public class PlatUserController extends BaseController{
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
			String loginpassword=obj.getString("loginpassword");
			String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
			//校验用户的账号密码是否正确
			PlatUser haveuser=platuserService.login(usercode,secretpassword);
			if(haveuser==null){
			 	outputstr("", "用户名或密码错误", false, null);
			}else{
				if(haveuser.getIslock()!=null&&haveuser.getIslock().equals("Y")){
					outputstr("", "用户已被锁定", false, null);
				}else{
					 JSONObject successstr=new JSONObject();
					 successstr.accumulate("cellphone", haveuser.getCellphone());
					 successstr.accumulate("pkUser", EncodeUtil.encode((haveuser.getUsertype().equals("2")?"plat":"shop")+":"+haveuser.getPkPlatuser().toString()));
					 successstr.accumulate("username", haveuser.getPlatusername());
					 successstr.accumulate("usertype", haveuser.getUsertype());
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
			String pkUser= (String)obj.get("pkUser");
			//校验用户的账号密码是否正确
			PlatUser haveuser=platuserService.getUserById(Long.parseLong(pkUser));
			if(haveuser==null){
				 outputstr("", "用户名或密码错误", false, null);
			}else{
				if(haveuser.getIslock()!=null&&haveuser.getIslock().equals("Y")){
					outputstr("", "用户已被锁定", false, null);
				}else{
					 JSONObject successstr=new JSONObject();
					 successstr.accumulate("cellphone", haveuser.getCellphone());
					 successstr.accumulate("pkUser", haveuser.getPkPlatuser());
					 successstr.accumulate("username", haveuser.getPlatusername());
					 successstr.accumulate("usertype", haveuser.getUsertype());
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
			String usercode="";
			String platcode="plat";
	        boolean flag =PatternUtil.validatenum(loginpassword); 
	        if(flag){
	        	outputstr("", "密码不能全是数字", false, null);
	        }else{
		        flag = PatternUtil.validatelength(loginpassword); 
		        if(flag){
					//查询店铺信息SQL
					String sql=UserCodeUtil.execute(UserGroupObject.GROUP_FOUR[0], platcode,3);
					String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
					//获取用户编码
					List<Object> objectlist=platuserService.querybysql(sql);
					if(objectlist.get(0)==null){
						usercode=platcode+UserGroupObject.GROUP_FOUR[0]+"001";
					}else{
						usercode=platcode+UserGroupObject.GROUP_FOUR[0]+objectlist.get(0).toString();	
					}
					PlatUser user=new PlatUser();
					user.setPlatusercode(usercode);
					user.setPlatusername(username);
					user.setCellphone(cellphone);
					user.setUsertype("2");
					user.setLoginpassword(secretpassword);
					user.setDr((short)0);
					user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));					
					platuserService.save(user);
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
			//手机号
			String cellphone=obj.getString("cellphone");
			//用户名
			String username=obj.getString("username");
			//登录密码
			String loginpassword=obj.getString("loginpassword");
			//平台用户主键
			String pkPlatuser=obj.getString("pkUser");
			//密码加密
			String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
			//根据平台用户主键查询用户
			PlatUser user=platuserService.getUserById(Long.parseLong(pkPlatuser));
			
			user.setPlatusername(username);
			user.setCellphone(cellphone);
			user.setLoginpassword(secretpassword);
			user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			
			int result=platuserService.updateUser(user);
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
			//旧密码
			String oldpassword=obj.getString("oldpassword");
			//新密码
			String newpassword=obj.getString("newpassword");
			//用户主键
			String pkPlatuser=obj.getString("pkUser");
			//旧密码加密
			String secretoldpassword=MD5Util.MD5Encode(oldpassword, "utf-8");
			
			PlatUser user=platuserService.getUserById(Long.parseLong(pkPlatuser));
			  
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
						int result=platuserService.updateUser(user);
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
