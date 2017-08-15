package com.rockstar.o2o.weixin.oauth.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rockstar.o2o.weixin.conf.SysConf;
import com.rockstar.o2o.weixin.http.service.IHttpClientService;
import com.rockstar.o2o.weixin.pojo.UserVerificationmode;
import com.rockstar.o2o.weixin.pojo.WxOauthEvent;
import com.rockstar.o2o.weixin.service.BasicService;
import com.rockstar.o2o.weixin.util.RedisUtils;
import com.rockstar.o2o.weixin.util.des.EncodeUtil;

@Controller
public class OAuthController
{
	
  @Resource
  private SysConf wxchannelsysConf;
  
  @Resource
  private IHttpClientService httpservice;
  
  @Resource
  private BasicService baseservice;
  
  private static  String env = System.getenv("CHORHAIR");
  
	static  ResourceBundle bundle = ResourceBundle.getBundle("wx");
	static  String app_id =(env==null||env.equals("test"))?bundle.getString("test_app_id") :
		bundle.getString("build_app_id");
	static  String app_secret = (env==null||env.equals("test"))?bundle.getString("test_app_secret") :
		bundle.getString("build_app_secret"); 
	static  String account_id = (env==null||env.equals("test"))?bundle.getString("test_account_id") :
		bundle.getString("build_account_id") ; 
	static  String token = (env==null||env.equals("test"))?bundle.getString("test_token") :
		bundle.getString("build_token"); 

	private static final Logger logger = Logger.getLogger(OAuthController.class);
  /**
   * 获取用户OpenId,带到原请求网址
   * @param code
   * @param state
   * @param accountId
   * @param eventId
   * @param token
   * @param p1
   * @param p2
   * @param p3
   * @param p4
   * @param p5
   * @return
   */
  @RequestMapping({"/oauth/redirect"})
  public ModelAndView redirect(@RequestParam("code") String code, @RequestParam("state") String state, @RequestParam(value="accountId", required=true) String accountId, @RequestParam(value="eventId", required=true) String eventId, @RequestParam(value="token", required=false) String token, @RequestParam(value="p1", required=false) String p1, @RequestParam(value="p2", required=false) String p2, @RequestParam(value="p3", required=false) String p3, @RequestParam(value="p4", required=false) String p4, @RequestParam(value="p5", required=false) String p5)
  {
    logger.debug("state is : " + state);
    logger.debug("code is : " + code);
    String openId = null;
    String redirectUrl = null;
    ModelAndView view = null;
    if ((code != null) && (!code.equals(""))) {
      try {
         String  url=getOAuthOpenIdUrl(accountId, code);
         openId=getOpenIdFromOAuth(accountId, url);
        if (openId != null) {
          logger.debug("获取openId成功！openId为:" + openId);
          logger.debug("获取openId成功！自定义参数为:：" + state);
          boolean isredis=true;
          Object eventobj="";
          try {
        	  eventobj = RedisUtils.getObject(accountId+":"+"oauth:"+eventId);  
		   } catch (Exception e) {
			// TODO: handle exception
			   isredis=false;
			   e.printStackTrace();
		  }
          WxOauthEvent event=null;
          if(isredis&&eventobj!=null&&!eventobj.equals("")){
        	  event=(WxOauthEvent) eventobj;
          }else{
           List<Object> events =  this.baseservice.query(WxOauthEvent.class, " accountId = ? and eventId = ? and invalidFlag = ? ", accountId,eventId,"0");
            if(events.size()>0){
        	   event=(WxOauthEvent) events.get(0); 
               try {
            	   RedisUtils.setKey(accountId+":"+"oauth:"+eventId, event);
     		   } catch (Exception e) {
     			// TODO: handle exception
     			   e.printStackTrace();
     		  }
        	 
            }
          }
          if (event!=null) {       	  
   
        	  String secretopenid=EncodeUtil.encode("secret:"+openId);
        	  String secretaccountid=EncodeUtil.encode("secret:"+event.getAccountId());
        	  redirectUrl = event.getRedirectUrl() + "openid=" + 
        	  secretopenid + "&accountid=" + secretaccountid + 
              "&state=" + state + "&r=" + 
              new Random().nextDouble();

        	  //如果用户已经绑定过,则直接在url将pkUser加上
         	  ArrayList<Object> obj=(ArrayList<Object>) this.baseservice.query(UserVerificationmode.class, " openid = ? and verificationType = ? ", openId,"WEIXIN");
              String pkUser="";
        	  if(obj.size()>0){
        		  pkUser=((UserVerificationmode)obj.get(0)).getPkUser().toString();
              }
        	  if(!pkUser.equals("")){
        		 redirectUrl=redirectUrl+ "&pkUser=" + EncodeUtil.encode("wxcustomer"+":"+pkUser); 
        	  }
        	  
            if (p1 != null) {
              redirectUrl = redirectUrl + "&" + event.getParam1Name() + "=" + 
                p1;
            }
            else if (event.getParam1Value() != null) {
              redirectUrl = redirectUrl + "&" + 
                event.getParam1Name() + 
                "=" + 
                getConstParam(event.getParam1Value(), 
                openId, token);
            }

            if (p2 != null) {
              redirectUrl = redirectUrl + "&" + event.getParam2Name() + "=" + 
                p2;
            }
            else if (event.getParam2Value() != null) {
              redirectUrl = redirectUrl + "&" + 
                event.getParam2Name() + 
                "=" + 
                getConstParam(event.getParam2Value(), 
                openId, token);
            }

            if (p3 != null) {
              redirectUrl = redirectUrl + "&" + event.getParam3Name() + "=" + 
                p3;
            }
            else if (event.getParam3Value() != null) {
              redirectUrl = redirectUrl + "&" + 
                event.getParam3Name() + 
                "=" + 
                getConstParam(event.getParam3Value(), 
                openId,token);
            }

            if (p4 != null) {
              redirectUrl = redirectUrl + "&" + event.getParam4Name() + "=" + 
                p4;
            }
            else if (event.getParam4Value() != null) {
              redirectUrl = redirectUrl + "&" + 
                event.getParam4Name() + 
                "=" + 
                getConstParam(event.getParam4Value(), 
                openId,token);
            }

            if (p5 != null) {
              redirectUrl = redirectUrl + "&" + event.getParam5Name() + "=" + 
                p5;
            }
            else if (event.getParam5Value() != null) {
              redirectUrl = redirectUrl + "&" + 
                event.getParam5Name() + 
                "=" + 
                getConstParam(event.getParam5Value(), 
                openId, token);
            }

            view = new ModelAndView("redirect:" + redirectUrl);
          } else {
            logger.error("OAuth跳转连接已经失效或不可用，跳转至错误页面！");
            view = new ModelAndView("error/500");
          }
        } else {
          logger.error("获取openId失败！");
          view = new ModelAndView("error/500");
        }
      } catch (Exception e) {
        logger.debug("获取openId失败！");
        e.printStackTrace();
      }
    }
    logger.debug("最终跳转URL为:　" + redirectUrl);
    return view;
  }

  
  /**
   * 获取openID的URL
   * @param accountId
   * @param code
   * @return
   */
 private String getOAuthOpenIdUrl(String accountId, String code)
 {
     return this.wxchannelsysConf.getBaseOAuthTokenUrl() + 
       "appId=" + app_id + "&secret=" + 
       app_secret + "&code=" + code + 
       "&grant_type=authorization_code";
 }
 
 
   /**
    * 获取OpenId
    * @param accountId
    * @param url
    * @return
    * @throws Exception
    */
   public String getOpenIdFromOAuth(String accountId, String url)
   throws Exception
 {
   String openId = null;
   String json = this.httpservice.sendGetRequset(url);
   if ((json != null) && (!json.equals(""))) {
     logger.debug("json is : " + json);
     JSONObject mapper = JSONObject.fromObject(json);
     String errmsg = (String)mapper.get("errmsg");
     if (errmsg != null) {
       logger.debug("OAuth获取OpenId失败");
     } else {
       openId = (String)mapper.get("openid");
       logger.debug("OAuth获取OpenId：" + openId + "成功");
     }
     return openId;
   }
   
   
   logger.error("OAuth获取OpenId失败！");
   throw new Exception("OAuth获取OpenId失败！");
 }
 
  @RequestMapping({"/oauth/result?{param}"})
  public ModelAndView result(@PathVariable("param") String param) {
    ModelAndView view = new ModelAndView("oauth/result");
    return view;
  }

  private String getConstParam(String param, String openId,String token)
  {
    if (param.contains("#OPEN_ID"))
      param = openId;
    else if (param.contains("#TIMESTAMP_S"))
      param = String.valueOf(new Date().getTime() / 1000L);
    else if (param.contains("#TIMESTAMP_MS"))
      param = String.valueOf(new Date().getTime());
    else if (param.contains("#ENCODE")) {
    }
    logger.debug("param is : " + param);
    return param;
  }
}