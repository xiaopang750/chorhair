package com.rockstar.o2o.controller.qq;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.Share;
import com.qq.connect.api.qzone.Topic;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.GeneralResultBean;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;
import com.rockstar.o2o.constant.VerificationMode;
import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.PubUtil;

@Controller
@RequestMapping("/qq")
public class QQController extends BaseController{	
	private static Logger log = Logger.getLogger(QQController.class);
	
	/**
	 * qq授权登录
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public void qqlogin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
		response.setContentType("text/html;charset=utf-8");
        try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * qq授权登录回调
	 * @param model
	 * @return
	 */
	@RequestMapping("/afterlogin")
	public String afterlogin(HttpServletRequest request,HttpServletResponse response,Model model)throws IOException  {		
		   String redirecturl="";
		   try {
	       response.setContentType("text/html; charset=utf-8");
		   AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);	
           String accessToken   = null;
           String  openID       = null;
           long tokenExpireIn = 0L;    
           if (accessTokenObj.getAccessToken().equals("")) {
             log.info("没有获取到响应参数");
             redirecturl="/qq/login.php";
         } else {
             accessToken = accessTokenObj.getAccessToken();
             tokenExpireIn = accessTokenObj.getExpireIn();
             request.getSession().setAttribute("qq_access_token", accessToken);
             request.getSession().setAttribute("qq_token_expirein", String.valueOf(tokenExpireIn));
             OpenID openIDObj =  new OpenID(accessToken);
             openID = openIDObj.getUserOpenID();
             request.getSession().setAttribute("qq_openid", openID);    
             UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
             UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
             UserVerificationmode oldmode=verificationservice.QueryByTwo(openID, VerificationMode.MODE_ONE[0]);
             if (userInfoBean.getRet() == 0) {                       
                 if(oldmode==null){
                	  UserVerificationmode mode=new UserVerificationmode();
                	  mode.setOpenid(openID);
                	  mode.setNickname(userInfoBean.getNickname() );
                	  mode.setSex(userInfoBean.getGender());
                	  mode.setVerificationType(VerificationMode.MODE_ONE[0]);
                	  mode.setDr((short)0);
                	  mode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
                	  mode.setHeadurl(userInfoBean.getAvatar().getAvatarURL30());
                	  mode=verificationservice.save(mode);
                	  if(mode.getPkVerification()!=null){
                	  JSONObject obj=new JSONObject();
                	  obj.accumulate("issuccess", "true");
                	  obj.accumulate("msg", "验证成功");
                	 // output(response, obj.toString());
                	  }
                 }else{
                	  oldmode.setOpenid(openID);
                	  oldmode.setNickname(userInfoBean.getNickname() );
                	  oldmode.setSex(userInfoBean.getGender());
                	  oldmode.setVerificationType(VerificationMode.MODE_ONE[0]);
                	  oldmode.setDr((short)0);
                	  oldmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
                	  oldmode.setHeadurl(userInfoBean.getAvatar().getAvatarURL30());
                	  int result=verificationservice.updateGroup(oldmode);
                	  if(result==0){
                    	  JSONObject obj=new JSONObject();
                    	  obj.accumulate("issuccess", "true");
                    	  obj.accumulate("msg", "验证成功");
                    	//  output(response, obj.toString());
                    	}else{
                    	  JSONObject obj=new JSONObject();
                       	  obj.accumulate("issuccess", "false");
                       	  obj.accumulate("msg", "验证失败");
                      // 	  output(response, obj.toString());	
                    	}
                 }
             } else {
            	 JSONObject obj=new JSONObject();
           	     obj.accumulate("issuccess", "false");
           	     obj.accumulate("msg", "很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
           	     //output(response, obj.toString());
                 log.info("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
             }
             
             redirecturl=PubUtil.getServerURL("weburl")+"/views/customer/user/info?type=001&openid="+openID;
             /**
              * 数据库存主要信息，其他信息暂时存在日志当中
              */
             JSONObject obj=new JSONObject();
             obj.accumulate("type", "QQ");
             obj.accumulate("openid", openID);
             PageFans pageFansObj = new PageFans(accessToken, openID);
             PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
             if (pageFansBean.getRet() == 0) {
            	 obj.accumulate("isfans", (pageFansBean.isFans() ? "是" : "不是")  + "QQ空间97700000官方认证空间的粉丝");
             } else {
            	 obj.accumulate("qqfailmsg","很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg());
             }
   
             com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
             com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
             if (weiboUserInfoBean.getRet() == 0) {
            	 obj.accumulate("txweiboAvatarURL", weiboUserInfoBean.getAvatar().getAvatarURL30());
            	 obj.accumulate("txweiboAvatarURL50", weiboUserInfoBean.getAvatar().getAvatarURL50());
            	 obj.accumulate("txweiboAvatarURL100", weiboUserInfoBean.getAvatar().getAvatarURL100());
            	 obj.accumulate("birthday", weiboUserInfoBean.getBirthday().getYear()
                         +  "年" + weiboUserInfoBean.getBirthday().getMonth() + "月" +
                         weiboUserInfoBean.getBirthday().getDay() + "日");
                 obj.accumulate("location", weiboUserInfoBean.getCountryCode() + "-" + weiboUserInfoBean.getProvinceCode() + "-" + weiboUserInfoBean.getCityCode()
                         + weiboUserInfoBean.getLocation());

                 ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
                 StringBuffer sb=new StringBuffer();
                 if (companies.size() > 0) {
                     //有公司信息
                     for (int i=0, j=companies.size(); i<j; i++) {
                         sb.append("<p>曾服役过的公司：公司ID-" + companies.get(i).getID() + " 名称-" +
                         companies.get(i).getCompanyName() + " 部门名称-" + companies.get(i).getDepartmentName() + " 开始工作年-" +
                         companies.get(i).getBeginYear() + " 结束工作年-" + companies.get(i).getEndYear());
                     }
                     obj.accumulate("companys", sb.toString());
                 } else {
                     //没有公司信息
                 }

             } else {
            	 obj.accumulate("txfailmsg","很抱歉，我们没能正确获取到您腾讯微博的信息，原因是： " + pageFansBean.getMsg());
             }
             
             if(oldmode!=null){
            	 oldmode.setOtherjson(obj.toString());
            	 verificationservice.updateGroup(oldmode);
             }
                if(!obj.isEmpty()){
            	 log.fatal(obj.toString());
                 }
              }
	        } catch (QQConnectException e) {
			// TODO Auto-generated catch block
	        redirecturl="/qq/login.php";
	     	}
	        
			if(redirecturl.equals("")){
				redirecturl="/qq/login.php";
				return "redirect:"+redirecturl;
			}
	 	    return "redirect:"+redirecturl;
	}
	

	/**
	 * qq发表说说
	 * @param model
	 * @return
	 */
	@RequestMapping("/shuoshuo")
	public void shuoshuo(HttpServletRequest request,HttpServletResponse response,Model model)throws IOException  {				
		    response.setContentType("text/html;charset=utf-8");
	        request.setCharacterEncoding("utf-8");
	        String con = request.getParameter("con");
	        HttpSession session = request.getSession();
	        String accessToken = (String)session.getAttribute("qq_access_token");
	        String openID = (String)session.getAttribute("qq_openid");
	        System.out.println(accessToken);
	        System.out.println(openID);
	        if (con != "") {
	            Topic topic = new Topic(accessToken, openID);
	            try {
	                GeneralResultBean grb = topic.addTopic(con);
	                if (grb.getRet() == 0) {
	                    response.getWriter().println("说说已发表成功，请登录Qzone查看</a>");
	                } else {
	                    response.getWriter().println("很遗憾的通知您，发表说说失败！原因： " + grb.getMsg());
	                }
	            } catch (QQConnectException e) {
	                System.out.println("抛异常了");
	            }
	        } else {
	            System.out.println("获取到的值为空");
	        }
		
	}
	
	/**
	 * qq分享空间
	 * @param model
	 * @return
	 */
	@RequestMapping("/share")
	public void share(HttpServletRequest request,HttpServletResponse response,Model model)throws IOException  {				
		    response.setContentType("text/html;charset=utf-8");
	        request.setCharacterEncoding("utf-8");
	        String title = request.getParameter("title");
	        String url = request.getParameter("url");
	        String site = request.getParameter("site");
	        String fromUrl = request.getParameter("fromUrl");
	        HttpSession session = request.getSession();
	        String accessToken = (String)session.getAttribute("qq_access_token");
	        String openID = (String)session.getAttribute("qq_openid");
	        if (title != "" && url != "" && site != "" && fromUrl != "") {
	            Share topic = new Share(accessToken, openID);
	            try {
	                GeneralResultBean grb = topic.addShare(title, url, site, fromUrl, "");
	                if (grb.getRet() == 0) {
	                    response.getWriter().println("qq分享成功!");
	                } else {
	                    response.getWriter().println("qq分享失败!原因： " + grb.getMsg());
	                }
	            } catch (QQConnectException e) {
	               e.printStackTrace();
	            }
	        } else {
	            System.out.println("获取到的值为空!");
	        }
		
	}
	
}
