package com.rockstar.o2o.weixin.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rockstar.o2o.weixin.http.service.IHttpClientService;
import com.rockstar.o2o.weixin.message.bean.WxReplyMsg;
import com.rockstar.o2o.weixin.pojo.UserVerificationmode;
import com.rockstar.o2o.weixin.pojo.WxMenu;
import com.rockstar.o2o.weixin.pojo.WxQrcodeScan;
import com.rockstar.o2o.weixin.pojo.WxReceiveMsg;
import com.rockstar.o2o.weixin.pojo.WxUser;
import com.rockstar.o2o.weixin.service.BasicService;
import com.rockstar.o2o.weixin.util.DateUtil;
import com.rockstar.o2o.weixin.util.ReadPropertyUtil;
import com.rockstar.o2o.weixin.util.TemplateUtil;
import com.rockstars.o2o.weixin.util.encrypt.EncryptUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;


@Controller
public class MessageController {


	public static final String QR_LIMIT_SCENE ="QR_LIMIT_SCENE";
	public static final String QR_SCENE = "QR_SCENE";
	
	  private static final Logger logger = Logger.getLogger(MessageController.class);
	  
	  private static  String env = System.getenv("CHORHAIR");
	  
	  @Resource 
	  private BasicService baseservice;
	  
	  @Resource 
	  private IHttpClientService httpservice;
	  
	   /**
	    * 微信接口接入测试
	    * @param signature
	    * @param timestamp
	    * @param nonce
	    * @param echo
	    * @return
	    */
	  @RequestMapping(value={"/message"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseBody
	  public String contact(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echo) {
		if (authenticate(signature, timestamp, nonce))
	    {
	      logger.debug("帐号接入测试成功");
	      return echo;
	    }
	     logger.debug("帐号接入测试失败！");
	     return "error";
	  }
	  
	  /**
	   * 微信消息接收
	   * @param request
	   * @param response
	   * @throws ServletException
	   * @throws IOException
	   */
	  @RequestMapping(value={"/message"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public void getMessage(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	  {
		    response.setContentType("text/xml;charset=UTF-8");
		    String wxMsgXml = IOUtils.toString(request.getInputStream(), "utf-8");
		    System.out.println(wxMsgXml);
		    logger.debug("receive msg xml is : " + wxMsgXml);
		    PrintWriter pw = response.getWriter();
		    
		    final WxReceiveMsg baseMsg =  getWeixinMessage(wxMsgXml, 
		    	      new Class[] { WxReceiveMsg.class });
		    baseMsg.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    baseMsg.setDr((short)0);
		    baseservice.save(baseMsg);
		    		    
		   try {
			   WxReceiveMsg reply=new WxReceiveMsg();
			   reply.setWxId(baseMsg.getAccountId());
			   reply.setAccountId(baseMsg.getWxId());
			   
			   String content="欢迎您的光临";
			   String wxreplymsg="";
			   //事件消息
			   if(baseMsg.getMsgType().equals("event")){	
				   //未关注的进行关注
				  if(baseMsg.getWxevent().equals("subscribe")){
				   ArrayList<Object> objects=(ArrayList<Object>) baseservice.query(UserVerificationmode.class, "  openid = ? and verificationType  = ? ", baseMsg.getWxId(),"WEIXIN");				  
				   if(objects.size()>0){
					   UserVerificationmode mode=(UserVerificationmode) objects.get(0);
					   //已经绑定的会员关注
					   if(mode.getPkUser()!=null&&!mode.getPkUser().equals("")){
						   content = TemplateUtil.invokeTpl(null, "template/message", "bindsubscribe.ftl");     
					   }else{
						   String url=(env==null||env.equals("test"))?TemplateUtil.invokeTpl(null, "template/message", "testsubscribeurl.ftl"):
							   TemplateUtil.invokeTpl(null, "template/message", "buildsubscribeurl.ftl");
						   HashMap map=new HashMap();
						   map.put("url", url);
						   content = TemplateUtil.invokeTpl(map, "template/message", "subscribe.ftl");   
					   }
				   }else{
					   String url=(env==null||env.equals("test"))?TemplateUtil.invokeTpl(null, "template/message", "testsubscribeurl.ftl"):
						   TemplateUtil.invokeTpl(null, "template/message", "buildsubscribeurl.ftl");
					   HashMap map=new HashMap();
					   map.put("url", url);
					   content = TemplateUtil.invokeTpl(map, "template/message", "subscribe.ftl"); 
				   }				 
			      }else if(baseMsg.getWxevent().equals("CLICK")){
			    	    //菜单推送事件
					    ArrayList<Object> objectlist=(ArrayList<Object>) baseservice.query(WxMenu.class, " accountId = ? and menukey = ? ", baseMsg.getAccountId(),baseMsg.getEventkey());   
					    if(objectlist.size()>0){
					    	WxMenu menu=(WxMenu) objectlist.get(0);
					    	String keycontent=menu.getKeycontent();
					    	WxReplyMsg replymsg=getWeixinReplyMessage(keycontent,  new Class[] { WxReplyMsg.class });
					    	replymsg.setToUserName(baseMsg.getWxId());
					    	replymsg.setCreateTime(baseMsg.getCreatetime());
					    	replymsg.setFromUserName(baseMsg.getAccountId());
					        wxreplymsg=processReplyMessage(replymsg, new Class[] { WxReplyMsg.class });
					     }				  
			        }
				  //文字消息
			   }else if(baseMsg.getMsgType().equals("text")){
				   String ftl="";
				   if(baseMsg.getContent().equals("1")||baseMsg.getContent().equals("2")||baseMsg.getContent().equals("3")){					 
					if(baseMsg.getContent().equals("1")){
						ftl="price.ftl";
						content = TemplateUtil.invokeTpl(null, "template/message", ftl);     
				    	WxReplyMsg replymsg=getWeixinReplyMessage(content,  new Class[] { WxReplyMsg.class });
				    	replymsg.setToUserName(baseMsg.getWxId());
				    	replymsg.setCreateTime(baseMsg.getCreatetime());
				    	replymsg.setFromUserName(baseMsg.getAccountId());					   
					
				       wxreplymsg=processReplyMessage(replymsg, new Class[] { WxReplyMsg.class });
					}else if(baseMsg.getContent().equals("2")){
						ftl="shop.ftl";	
						content = TemplateUtil.invokeTpl(null, "template/message", ftl);    
					}else if(baseMsg.getContent().equals("3")){						
						ftl="customerphone.ftl";	
						content = TemplateUtil.invokeTpl(null, "template/message", ftl);    
					}
				   }else if(baseMsg.getContent()!=null&&baseMsg.getContent().trim().equals("虫二")){
					    ftl="chorhair.ftl";
						content = TemplateUtil.invokeTpl(null, "template/message", ftl);     
				    	WxReplyMsg replymsg=getWeixinReplyMessage(content,  new Class[] { WxReplyMsg.class });
				    	replymsg.setToUserName(baseMsg.getWxId());
				    	replymsg.setCreateTime(baseMsg.getCreatetime());
				    	replymsg.setFromUserName(baseMsg.getAccountId());					   					
				       wxreplymsg=processReplyMessage(replymsg, new Class[] { WxReplyMsg.class });  
				   //其他
			   }else{
				    content = TemplateUtil.invokeTpl(null, "template/message", "auto.ftl");     
			      }

			   
			   }
			   if(!wxreplymsg.equals("")){
				   pw.println(wxreplymsg);
			   }else{
			   reply.setContent(content);
			   reply.setMsgType("text");
			   reply.setCreatetime(baseMsg.getCreatetime());
			   String returnXml = processReplyMessage("text", 
					   reply, new Class[] { WxReceiveMsg.class });
			   pw.println(returnXml);
			   }
			   
			    //异步消息处理
			    new Thread(){
			    	public void run(){
			    		dealMsg(baseMsg);
			    	}
			    }.start();
			  
			    
		   } catch (Exception e) {
		 	// TODO: handle exception
			   e.printStackTrace();
		   }finally{
		 	pw.close();
		 }	
		   
	  }
	 
	  /**
	   * 消息处理
	   * @param baseMsg
	   */
	  public void dealMsg(WxReceiveMsg baseMsg){
		  try {
		  if(baseMsg.getMsgType().equals("event")){		    	
		    	if(baseMsg.getWxevent()!=null&&!baseMsg.getWxevent().equals("")){
		    		if(baseMsg.getWxevent().equalsIgnoreCase("subscribe")){
		    		String eventKey=baseMsg.getEventkey();
		    		if (eventKey.indexOf("qrscene_") != -1){
		    			eventKey = eventKey.replace("qrscene_", "");
		    		    }else {
		    	       
		    	      }
		    		//普通关注
		    		if(eventKey==null||eventKey.equals("")){
		    			String openid=baseMsg.getWxId();
		    			String accountid=baseMsg.getAccountId();
		    		    saveuser(openid, accountid);
		    		}
		    		//扫描带参数的二维码关注,统计扫描并关注次数
		    		else {
		    			String openid=baseMsg.getWxId();
		    			String accountid=baseMsg.getAccountId();
		    			//保存会员
		    		    saveuser(openid, accountid);
		    		    //保存二维码扫描记录
		    		    saveQrcodeScan(baseMsg);

		        		    //二维码扫描关注后需要调用server的接口做业务数据统计处理,统计扫描并关注次数
			    		    JSONObject obj=new JSONObject();
			    		    obj.accumulate("sceneid", eventKey);
			    		    if(Integer.parseInt(eventKey)>=1&&Integer.parseInt(eventKey)<=100000){
			    		    	 obj.accumulate("actionname", QR_LIMIT_SCENE);
			    		    }else{
			    		    	 obj.accumulate("actionname", QR_SCENE);
			    		    }			    		  
			    		    obj.accumulate("openid", openid);	
			    		    obj.accumulate("accountid", accountid);	
			    		    obj.accumulate("isold", "N");
			    		    
							String result = httpservice.sendPostRequset(ReadPropertyUtil.getServerURL("wxservice", "qrcodedeal"), obj.toString());
						   	logger.info(result);
		    		  }
		    	    }else if(baseMsg.getWxevent().equalsIgnoreCase("unsubscribe")){
		    	    	String openid=baseMsg.getWxId();
		    			String accountid=baseMsg.getAccountId();
		    		    //取消关注更新会员状态
		    	    	updateuser(openid, accountid);
		    	    	
		    	    	//已关注的会员扫描后只统计扫描次数
		    	   }else if(baseMsg.getWxevent().equalsIgnoreCase("SCAN")){
		       		    String eventKey=baseMsg.getEventkey();
		    			eventKey = eventKey.replace("qrscene_", "");			    			
		    			String openid=baseMsg.getWxId();
		    			String accountid=baseMsg.getAccountId();	
		    			//保存会员
		    		    saveuser(openid, accountid);
		    		    //保存二维码扫描记录
		    		    saveQrcodeScan(baseMsg);
		    		    //二维码扫描关注后需要调用server的接口做业务数据统计处理,统计扫描并关注次数
		    		    JSONObject obj=new JSONObject();
		    		    obj.accumulate("sceneid", eventKey);
		    		    if(Integer.parseInt(eventKey)>=1&&Integer.parseInt(eventKey)<=100000){
		    		    	 obj.accumulate("actionname", QR_LIMIT_SCENE);
		    		    }else{
		    		    	 obj.accumulate("actionname", QR_SCENE);
		    		    }	
		    		    obj.accumulate("isold", "Y");	
		    		    obj.accumulate("openid", openid);	
		    		    obj.accumulate("accountid", accountid);	
						String result = httpservice.sendPostRequset(ReadPropertyUtil.getServerURL("wxservice", "qrcodedeal"), obj.toString());
					   	logger.info(result);
		    	   }
		    	}
		   }
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	  }
	  
	  /**
	   * 取消关注时更新会员状态
	   * @param receivemsg
	   * @return
	   */
	  public void updateuser(String openid,String accountid){
		  ArrayList<Object> queryobj=(ArrayList<Object>) baseservice.query(WxUser.class, " wxId = ? ", openid);
		  if(queryobj.size()>0){
	        	WxUser getuser=(WxUser) queryobj.get(0);
	        	getuser.setUnsubscribeTime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        	getuser.setSubscribeFlag("2");
		    	getuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    	getuser.setDr((short)0);
		    	//更新会员信息
		    	baseservice.update(getuser);
	        }else{				    	
		    	WxUser user=new WxUser();
		    	user.setUnsubscribeTime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    	user.setSubscribeFlag("2");
		    	user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    	user.setDr((short)0);
		    	user.setWxId(openid);
		    	user.setAccountId(accountid);
		    	//新增会员信息
		    	baseservice.save(user);
	        }
	  }
	  /**
	   * 保存二维码扫描记录
	   * @param openid
	   * @param accountid
	   * @return
	   */
	  public String saveQrcodeScan(WxReceiveMsg receivemsg){
		  try {
		   WxQrcodeScan scan=new WxQrcodeScan();
		   scan.setAccountId(receivemsg.getAccountId());
		   String sceneid=receivemsg.getEventkey().replace("qrscene_", "");
		   scan.setSceneId(sceneid);
		   scan.setWxId(receivemsg.getWxId());
		   scan.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		   scan.setDr((short)0);
		   baseservice.save(scan);
			return "success";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "error";
			}		  
	  }
	  /**
	   * 保存微信会员信息
	   * @param openid
	   * @param accountid
	   * @return
	   */
	  public String saveuser(String openid,String accountid){
			JSONObject obj=new JSONObject();
			obj.accumulate("openId", openid);
			try {
				String jsondata=httpservice.sendPostRequset(ReadPropertyUtil.getServerURL("wxservice", "getuserinfo"), obj.toString());
			    JSONObject getobj=JSONObject.fromObject(jsondata);
			    String data=(String)getobj.get("data");
			    if(data!=null&&!data.equals("")){
			    	JSONObject userobj=JSONObject.fromObject(data);
			    	Integer subscribe=(Integer)userobj.get("subscribe");//1关注,2未关注
			    	String nickname=(String)userobj.get("nickname");
			        if(StringUtils.isNotBlank(nickname)){  
			        	nickname = nickname.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");  
			        }
			    	Integer sex=(Integer)userobj.get("sex");//1为男2为女
			    	String language=(String)userobj.get("language");
			    	String city=(String)userobj.get("city");
			    	String province=(String)userobj.get("province");
			    	String country=(String)userobj.get("country");
			    	String headimgurl=(String)userobj.get("headimgurl");
			    	Integer subscribe_time=(Integer)userobj.get("subscribe_time");
			    	String remark=(String)userobj.get("remark");
			    	//判断是否是老会员,是的话更新会员信息,不是得话插入User表中
			    	ArrayList<Object> queryobj=(ArrayList<Object>) baseservice.query(WxUser.class, " wxId = ? ", openid);
			        if(queryobj.size()>0){
			        	WxUser getuser=(WxUser) queryobj.get(0);
			        	getuser.setSex(sex.toString());
			        	getuser.setSubscribeFlag(subscribe.toString());
			        	getuser.setNickname(nickname);
			        	getuser.setCity(city);
			        	getuser.setProvince(province);
			        	getuser.setCountry(country);
			        	getuser.setHeadImageUrl(headimgurl);
			        	getuser.setSubscribeTime(subscribe_time.toString());
				    	getuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				    	getuser.setDr((short)0);
				    	//更新会员信息
				    	baseservice.update(getuser);
			        }else{				    	
				    	WxUser user=new WxUser();
				    	user.setSex(sex.toString());
				    	user.setWxId(openid);
				    	user.setAccountId(accountid);
				    	user.setSubscribeFlag(subscribe.toString());
				    	user.setNickname(nickname);
				    	user.setCity(city);
				    	user.setProvince(province);
				    	user.setCountry(country);
				    	user.setHeadImageUrl(headimgurl);
				    	user.setSubscribeTime(subscribe_time.toString());
				    	user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				    	user.setDr((short)0);
				    	//新增会员信息
				    	baseservice.save(user);
			        }
			    }
			    return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
	  }
	  
	  /**
	   * 回复消息格式处理
	   * @param replyMessageType
	   * @param message
	   * @param types
	   * @return
	   */
	  public String processReplyMessage(Object message, Class<?>[] types)
	  {
	    XStream xstream = new XStream(new DomDriver("UTF-8", 
	      new XmlFriendlyNameCoder("-_", "_")));
	    xstream.autodetectAnnotations(true);
	    xstream.processAnnotations(types);
	    return xstream.toXML(message);
	  }
	  
	  /**
	   * 回复消息格式处理
	   * @param replyMessageType
	   * @param message
	   * @param types
	   * @return
	   */
	  public String processReplyMessage(String replyMessageType, Object message, Class<?>[] types)
	  {
	    XStream xstream = new XStream(new DomDriver("UTF-8", 
	      new XmlFriendlyNameCoder("-_", "_")));
	    xstream.autodetectAnnotations(true);
	    xstream.processAnnotations(types);
	    WxReceiveMsg text = (WxReceiveMsg)message;
	    return xstream.toXML(text);
	  }
	  
	  /**
	   * 回复的消息转换成实体类
	   * @param xml
	   * @param clazz
	   * @return
	   */
	  private WxReplyMsg getWeixinReplyMessage(String xml, Class<?>[] clazz)
	  {
	    XStream xstream = new XStream(new DomDriver("UTF-8", 
	      new XmlFriendlyNameCoder("-_", "_")));
	    xstream.autodetectAnnotations(true);
	    xstream.processAnnotations(clazz);
	    return (WxReplyMsg)xstream.fromXML(xml);
	  }
	  
	  /**
	   * 接收到的消息转换成实体类
	   * @param xml
	   * @param clazz
	   * @return
	   */
	  private WxReceiveMsg getWeixinMessage(String xml, Class<?>[] clazz)
	  {
	    XStream xstream = new XStream(new DomDriver("UTF-8", 
	      new XmlFriendlyNameCoder("-_", "_")));
	    xstream.autodetectAnnotations(true);
	    xstream.processAnnotations(clazz);
	    return (WxReceiveMsg)xstream.fromXML(xml);
	  }
	  
	  /**
	   * 微信消息校验
	   * @param signature
	   * @param timestamp
	   * @param nonce
	   * @return
	   */
	  private boolean authenticate(String signature, String timestamp, String nonce)
	  {
		    ResourceBundle bundle = ResourceBundle.getBundle("wx");
			String token = (env==null||env.equals("test"))?bundle.getString("test_token") :
				bundle.getString("build_token") ; 
	        String[] array = { token, timestamp, nonce };
	        Arrays.sort(array);
	        String result = "";

	        for (String i : array) {
	          result = result + i;
	        }
	        String ret = "";
	        try
	        {
	          ret = EncryptUtil.SHAsum(result.getBytes());
	          if (ret.equalsIgnoreCase(signature))
	            return true;
	        }
	        catch (NoSuchAlgorithmException ex) {
	          ex.printStackTrace();
	        }
	    return false;
	  }
	  
}
