package com.rockstar.o2o.controller.sinaweibo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.rockstar.o2o.constant.VerificationMode;
import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.PubUtil;

@Controller
@RequestMapping("/sina")
public class SinaWeiboController extends BaseController{
	private static Logger log = Logger.getLogger(SinaWeiboController.class);

	/**
	 * 微博授权登录
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public void sinalogin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
		response.setContentType("text/html;charset=utf-8");
        try {
			response.sendRedirect(new Oauth().authorizebymobile("code"));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 微博回调地址
	 * @param model
	 * @return
	 */
	@RequestMapping("/afterlogin")
	public String afterlogin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {	
		   String redirecturl="";
		   try {
			response.setContentType("text/html; charset=utf-8");
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByCode(request.getParameter("code"));
			String accesstoken=accessTokenObj.getAccessToken();
			//String tokenExpireIn=accessTokenObj.getExpireIn();
			String openID=accessTokenObj.getUid();
			Users users=new Users(accesstoken);
			User user=users.showUserById(openID);
			  UserVerificationmode oldmode=verificationservice.QueryByTwo(openID, VerificationMode.MODE_THREE[0]);
              if(oldmode==null){
             	  UserVerificationmode mode=new UserVerificationmode();
             	  mode.setOpenid(openID);
				  mode.setNickname(user.getName());				
             	  mode.setSex(user.getGender().equals("m")?"男":(user.getGender().equals("n")?"女":"未知"));
             	  mode.setVerificationType(VerificationMode.MODE_THREE[0]);
             	  mode.setDr((short)0);
             	  mode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
             	  mode.setHeadurl(user.getProfileImageUrl());
				  JSONObject objjj=JSONObject.fromObject(user);
				  objjj.accumulate("type", "SINA");
             	  mode.setOtherjson(objjj.toString());
             	  mode=verificationservice.save(mode);
             	  if(mode.getPkVerification()!=null){
             	  JSONObject obj=new JSONObject();
             	  obj.accumulate("issuccess", "true");
             	  obj.accumulate("msg", "验证成功");
             	//  output(response, obj.toString());
             	
             	  }
             	 
              }else{
             	  oldmode.setOpenid(openID);
             	  oldmode.setNickname(user.getName());				
             	  oldmode.setSex(user.getGender().equals("m")?"男":(user.getGender().equals("n")?"女":"未知"));
				  oldmode.setVerificationType(VerificationMode.MODE_THREE[0]);
				  oldmode.setDr((short)0);
				  oldmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				  oldmode.setHeadurl(user.getProfileImageUrl());
				  JSONObject objjj=JSONObject.fromObject(user);
				  objjj.accumulate("type", "SINA");
				  oldmode.setOtherjson(objjj.toString());
             	  int result=verificationservice.updateGroup(oldmode);
             	  if(result==0){
                 	  JSONObject obj=new JSONObject();
                 	  obj.accumulate("issuccess", "true");
                 	  obj.accumulate("msg", "验证成功");
                 	 // output(response, obj.toString());
                 	}else{
                 	  JSONObject obj=new JSONObject();
                      obj.accumulate("issuccess", "false");
                      obj.accumulate("msg", "验证失败");
                   //   output(response, obj.toString());	
                 	}
             	
              }
              redirecturl=PubUtil.getServerURL("weburl")+"/views/customer/user/info?type=002&openid="+openID;
              /**
               * 数据库存主要信息，其他信息暂时存在日志当中
               */
              JSONObject obj=JSONObject.fromObject(user);
              log.fatal(obj.toString());
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			redirecturl="/sina/login.php";
			
		}
		if(redirecturl.equals("")){
			redirecturl="/sina/login.php";
			 return "redirect:"+redirecturl;
		}
		 return "redirect:"+redirecturl;
	}
	
}
