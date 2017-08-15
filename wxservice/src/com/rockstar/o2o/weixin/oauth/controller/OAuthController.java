package com.rockstar.o2o.weixin.oauth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.weixin.controller.BaseController;
import com.rockstar.o2o.weixin.oauth.pojo.WxOauthEvent;
import com.rockstar.o2o.weixin.util.RedisUtils;
import com.rockstar.o2o.weixin.util.RequestUtil;

@Controller
@RequestMapping("/wxoauth")
public class OAuthController extends BaseController
{	

	private static  String env = System.getenv("CHORHAIR");
	 
  /**
   * 生成oauth地址获取用户信息
   * @param request
   * @param response
   * @return
   */
  @RequestMapping("/createurl")
  public void generateOAuthUrl(HttpServletRequest request,HttpServletResponse response){
	  try {
	  JSONObject jsonString=RequestUtil.getPostString(request);
	  String state=jsonString.get("state")!=null?jsonString.getString("state"):"rock";
	  WxOauthEvent event=(WxOauthEvent)JSONObject.toBean(jsonString, WxOauthEvent.class);
	  String redirectUrl = null;
	   String url= (env==null||env.equals("test"))?this.wxsysConf.getBasetestLocalOAuthRedirectUrl():
		   this.wxsysConf.getBasebuildLocalOAuthRedirectUrl();
      redirectUrl = url + 
        "%3FeventId=" + event.getEventId() + "%26accountId=" + 
        account_id;
      if (event.getParam1Name() != null && !event.getParam1Name() .equals("")) {
        redirectUrl = redirectUrl + "%26" + event.getParam1Name() + "=" + 
          event.getParam1Value();
      }
      if (event.getParam2Name() != null && !event.getParam2Name() .equals("")) {
        redirectUrl = redirectUrl + "%26" + event.getParam2Name() + "=" + 
          event.getParam2Value();
      }
      if (event.getParam3Name() != null && !event.getParam3Name() .equals("")) {
        redirectUrl = redirectUrl + "%26" + event.getParam3Name() + "=" + 
          event.getParam3Value();
      }
      if (event.getParam4Name() != null && !event.getParam4Name() .equals("")) {
        redirectUrl = redirectUrl + "%26" + event.getParam4Name() + "=" + 
          event.getParam4Value();
      }
      if (event.getParam5Name() != null && !event.getParam4Name() .equals("")) {
        redirectUrl = redirectUrl + "%26" + event.getParam5Name() + "=" + 
          event.getParam5Value();
      }
      
      String str=this.wxsysConf.getBaseOAuthUrl() + "appId=" + 
        app_id + "&response_type=code" + 
        "&scope=snsapi_base" + "&redirect_uri=" + 
        redirectUrl + "&state=" + state + 
        "#wechat_redirect";
       JSONObject obj=new JSONObject();
       obj.accumulate("redirecturl", str);
       RedisUtils.delKey(account_id+":"+event.getEventId());
       RedisUtils.setKey(account_id+":"+event.getEventId(), event);
       outputstr(obj.toString(), "生成微信地址成功", true);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();  
		}
       output(response, pojo);
  }
  
  

}