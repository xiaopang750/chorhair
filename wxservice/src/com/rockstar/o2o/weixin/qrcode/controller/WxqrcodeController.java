package com.rockstar.o2o.weixin.qrcode.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.weixin.controller.BaseController;
import com.rockstar.o2o.weixin.qrcode.pojo.WxQrcodeDetail;
import com.rockstar.o2o.weixin.qrcode.pojo.invoke.ActionInfo;
import com.rockstar.o2o.weixin.qrcode.pojo.invoke.BaseQrcode;
import com.rockstar.o2o.weixin.qrcode.pojo.invoke.Scene;
import com.rockstar.o2o.weixin.util.DateUtil;
import com.rockstar.o2o.weixin.util.RedisUtils;
import com.rockstar.o2o.weixin.util.RequestUtil;


/**
 * 二维码服务
 * @author hp
 *
 */
@Controller
@RequestMapping("/wxqrcode")
public class WxqrcodeController extends BaseController{

	public static final String QR_LIMIT_SCENE ="QR_LIMIT_SCENE";
	public static final String QR_SCENE = "QR_SCENE";

	
    @RequestMapping("/create")
	public void createqrcode(HttpServletRequest request,HttpServletResponse response,Model model) {   	
    	try {
			   JSONObject obj=RequestUtil.getPostString(request);
			   String pkkey=obj.getString("pkKey");
			   String codeobject=obj.getString("codeobject");
			   
			    //获取AccessToken
		       String token=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();
		       
			 //生成对象是店铺,生成永久二维码
			 if(codeobject.equals("1")||codeobject.equals("5")){	
				  boolean isexist=false;
			      String sceneid="";
				  ArrayList<Object> objects=(ArrayList<Object>) basicservice.query(WxQrcodeDetail.class, "  accountId = ? and actionName = ? and pkKey = ? and operateobject = ? ", 
						  account_id,QR_LIMIT_SCENE,Long.parseLong(pkkey),codeobject);
				  if(objects.size()>0){
					  WxQrcodeDetail olddetail=(WxQrcodeDetail) objects.get(0);
					  sceneid = olddetail.getSceneId();
					  isexist=true;
				  }else{
					  ArrayList<Object> object=(ArrayList<Object>) basicservice.query(WxQrcodeDetail.class, "  accountId = ? and actionName = ? order by pkDetail DESC LIMIT 0,1 ", 
							  account_id,QR_LIMIT_SCENE); 
					  if(object.size()>0){
						  WxQrcodeDetail olddetail=(WxQrcodeDetail) object.get(0);
						  sceneid = String.valueOf((Integer.parseInt(olddetail.getSceneId())+1));
					  }else{
						  sceneid="1";
					  }
				  }
				  
				  Scene sene=new Scene();
				  sene.setScene_id(sceneid);
				  
    		      ActionInfo info=new ActionInfo(sene);
    		      BaseQrcode qrcode=new BaseQrcode();
    		      qrcode.setAction_name(QR_LIMIT_SCENE);
    		      qrcode.setAction_info(info);
    		      String jsonData = JSONObject.fromObject(qrcode).toString();
    		      StringBuffer sb = getBaseUri(this.wxsysConf.getBaseQrcodeUrl());
    		      String uri = "access_token=" + token;
    		      uri=sb.append(uri).toString();
    		      String jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
    		      System.out.println(jsonResult);
    		      if ((jsonResult != null) && (!jsonResult.equals(""))) {
    		          JSONObject returnobj=JSONObject.fromObject(jsonResult);
    		          String ticket =returnobj.get("ticket")==null?null:returnobj.getString("ticket");
    		          if(ticket!=null&&!ticket.equals("")){
    		        	  String qrcodeurl=this.wxsysConf.getBaseQrcodePicUrl() + ticket; 
    		        	  if(!isexist){
	    		    	  saveqrcde(QR_LIMIT_SCENE, codeobject, sceneid, ticket, qrcodeurl, pkkey);
    		        	  }
	    		    	  JSONObject newobj=new JSONObject();
	    		    	  newobj.accumulate("qrcodeurl", qrcodeurl);
  	    		    	  newobj.accumulate("actionname", QR_LIMIT_SCENE);
	    		    	  newobj.accumulate("sceneid", sceneid);
	    		    	  newobj.accumulate("accountid", account_id);
	    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
    		          }else{
    		          String errmsg =returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
    		          //AccessToken失效,重新获取
    		          if(errmsg!=null&&((errmsg.equals("access_token expired")) || 
    		                  (errmsg.equals("invalid credential")))){
    		        	   token=accesstokenhandle.applyAccessToken();
    				       sb = getBaseUri(this.wxsysConf.getBaseQrcodeUrl());
    	    		       uri = "access_token=" + token;
    	    		       uri=sb.append(uri).toString();
    	    		       jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
    	    		       System.out.println(jsonResult);
    	    		      
    	    		      returnobj=JSONObject.fromObject(jsonResult);
    	    		       ticket =returnobj.get("ticket")==null?null:returnobj.getString("ticket");
 
    	    		      if(ticket!=null&&!ticket.equals("")){
    	    		    	  String qrcodeurl=this.wxsysConf.getBaseQrcodePicUrl() + ticket; 
    	    		    	  if(!isexist){
    	    		    	   saveqrcde(QR_LIMIT_SCENE, codeobject, sceneid, ticket, qrcodeurl, pkkey);
    	    		    	  }
    	    		    	  JSONObject newobj=new JSONObject();
    	    		    	  newobj.accumulate("qrcodeurl", qrcodeurl);
    	    		    	  newobj.accumulate("actionname", QR_LIMIT_SCENE);
    	    		    	  newobj.accumulate("sceneid", sceneid);
    	    		    	  newobj.accumulate("pkKey", pkkey);
    	    		    	  newobj.accumulate("codeobject", codeobject);
    	    		    	  newobj.accumulate("accountid", account_id);
    	    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
    	    		          }
    	    		       } else{
    	    		    	   outputstr("", "生成带参数二维码出错，errmsg is : " + errmsg, false);   
    	    		       }
    		           }
    		      }
    		 //生成对象是消费者,生成临时二维码
			}else if(codeobject.equals("3")){
				  boolean isexist=false;
			      String sceneid="";
			      WxQrcodeDetail olddetail=null;
				  ArrayList<Object> objects=(ArrayList<Object>) basicservice.query(WxQrcodeDetail.class, "  accountId = ? and actionName = ? and pkKey = ? and operateobject = ? ", 
						  account_id,QR_SCENE,Long.parseLong(pkkey),codeobject);
				  if(objects.size()>0){
					   olddetail=(WxQrcodeDetail) objects.get(0);
					  sceneid = olddetail.getSceneId();
					  isexist=true;
				  }else{
					  ArrayList<Object> object=(ArrayList<Object>) basicservice.query(WxQrcodeDetail.class, "  accountId = ? and actionName = ? order by pkDetail DESC LIMIT 0,1 ", 
							  account_id,QR_SCENE); 
					  if(object.size()>0){
						   WxQrcodeDetail lastdetail=(WxQrcodeDetail) object.get(0);
						   sceneid = String.valueOf((Integer.parseInt(lastdetail.getSceneId())+1));
					  }else{
						   sceneid="100001";
					  }
				  }
				  boolean isredis=true;
				  String oldqrcodeurl="";
				  try {
					  oldqrcodeurl =  (String) RedisUtils.getObject(account_id+":"+"customer:"+sceneid+":"+QR_SCENE);
				   } catch (Exception e) {
					// TODO: handle exception
					   isredis=false;
				  }
				 if(isredis&&oldqrcodeurl!=null&&!oldqrcodeurl.equals("")){
	 		    	  JSONObject newobj=new JSONObject();
    		    	  newobj.accumulate("qrcodeurl", oldqrcodeurl);
	    		      newobj.accumulate("actionname", QR_SCENE);
    		    	  newobj.accumulate("sceneid", sceneid);
    		    	  newobj.accumulate("accountid", account_id);
    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
				 }else{
				  Scene sene=new Scene();
				  sene.setScene_id(sceneid);
				  
    		      ActionInfo info=new ActionInfo(sene);
    		      BaseQrcode qrcode=new BaseQrcode();
    		      qrcode.setAction_name(QR_SCENE);
    		      qrcode.setAction_info(info);
    		      qrcode.setExpire_seconds(604800);
    		      String jsonData = JSONObject.fromObject(qrcode).toString();
    		      StringBuffer sb = getBaseUri(this.wxsysConf.getBaseQrcodeUrl());
    		      String uri = "access_token=" + token;
    		      uri=sb.append(uri).toString();
    		      String jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
    		      System.out.println(jsonResult);
    		      if ((jsonResult != null) && (!jsonResult.equals(""))) {
    		          JSONObject returnobj=JSONObject.fromObject(jsonResult);
    		          String ticket =returnobj.get("ticket")==null?null:returnobj.getString("ticket");
    		          if(ticket!=null&&!ticket.equals("")){
    		        	  String qrcodeurl=this.wxsysConf.getBaseQrcodePicUrl() + ticket; 
    		        	  if(!isexist){
	    		    	  saveqrcde(QR_SCENE, codeobject, sceneid, ticket, qrcodeurl, pkkey);
    		        	  }else{
    		        	   updateqrcde(olddetail,ticket,qrcodeurl) ;
    		        	  }
	    		    	  JSONObject newobj=new JSONObject();
	    		    	  newobj.accumulate("qrcodeurl", qrcodeurl);
  	    		    	  newobj.accumulate("actionname", QR_SCENE);
	    		    	  newobj.accumulate("sceneid", sceneid);
	    		    	  newobj.accumulate("accountid", account_id);
	    		    	  
	    		    	  RedisUtils.setKeyBytime(account_id+":"+"customer:"+sceneid+":"+QR_SCENE, qrcodeurl,60*10000);
	    		    	  
	    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
    		          }else{
    		          String errmsg =returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
    		          //AccessToken失效,重新获取
    		          if(errmsg!=null&&((errmsg.equals("access_token expired")) || 
    		                  (errmsg.equals("invalid credential")))){
    		        	   token=accesstokenhandle.applyAccessToken();
    				       sb = getBaseUri(this.wxsysConf.getBaseQrcodeUrl());
    	    		       uri = "access_token=" + token;
    	    		       uri=sb.append(uri).toString();
    	    		       jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
    	    		       System.out.println(jsonResult);
    	    		      
    	    		      returnobj=JSONObject.fromObject(jsonResult);
    	    		       ticket =returnobj.get("ticket")==null?null:returnobj.getString("ticket");
 
    	    		      if(ticket!=null&&!ticket.equals("")){
    	    		    	  String qrcodeurl=this.wxsysConf.getBaseQrcodePicUrl() + ticket; 
    	    		    	  if(!isexist){
    	    		    	   saveqrcde(QR_SCENE, codeobject, sceneid, ticket, qrcodeurl, pkkey);
    	    		    	  }else{
    	    		    	   updateqrcde(olddetail,ticket,qrcodeurl) ;
    	    		    	  }
    	    		    	  JSONObject newobj=new JSONObject();
    	    		    	  newobj.accumulate("qrcodeurl", qrcodeurl);
    	    		    	  newobj.accumulate("actionname", QR_SCENE);
    	    		    	  newobj.accumulate("sceneid", sceneid);
    	    		    	  newobj.accumulate("accountid", account_id);
    	    		    	  
    	    		    	  RedisUtils.setKeyBytime(account_id+":"+"customer:"+sceneid+":"+QR_SCENE, qrcodeurl,60*10000);    	    		    	  
    	    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
    	    		          }
    	    		       } else{
    	    		    	   outputstr("", "生成带参数二维码出错，errmsg is : " + errmsg, false);   
    	    		       }
    		           }
    		      }
			}
    		   //如果是平台或者店铺其他二维码,生成永久二维码
			}else if(codeobject.equals("4")||codeobject.equals("6")){

				 boolean isexist=false;
			      String sceneid="";
					  ArrayList<Object> object=(ArrayList<Object>) basicservice.query(WxQrcodeDetail.class, "  accountId = ? and actionName = ? order by pkDetail DESC LIMIT 0,1 ", 
							  account_id,QR_LIMIT_SCENE); 
					  if(object.size()>0){
						  WxQrcodeDetail olddetail=(WxQrcodeDetail) object.get(0);
						  sceneid = String.valueOf((Integer.parseInt(olddetail.getSceneId())+1));
					  }else{
						  sceneid="1";
					  }
				  
				  Scene sene=new Scene();
				  sene.setScene_id(sceneid);
				  
   		      ActionInfo info=new ActionInfo(sene);
   		      BaseQrcode qrcode=new BaseQrcode();
   		      qrcode.setAction_name(QR_LIMIT_SCENE);
   		      qrcode.setAction_info(info);
   		      String jsonData = JSONObject.fromObject(qrcode).toString();
   		      StringBuffer sb = getBaseUri(this.wxsysConf.getBaseQrcodeUrl());
   		      String uri = "access_token=" + token;
   		      uri=sb.append(uri).toString();
   		      String jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
   		      System.out.println(jsonResult);
   		      if ((jsonResult != null) && (!jsonResult.equals(""))) {
   		          JSONObject returnobj=JSONObject.fromObject(jsonResult);
   		          String ticket =returnobj.get("ticket")==null?null:returnobj.getString("ticket");
   		          if(ticket!=null&&!ticket.equals("")){
   		        	  String qrcodeurl=this.wxsysConf.getBaseQrcodePicUrl() + ticket; 
   		        	  if(!isexist){
	    		    	  saveqrcde(QR_LIMIT_SCENE, codeobject, sceneid, ticket, qrcodeurl, pkkey);
   		        	  }
	    		    	  JSONObject newobj=new JSONObject();
	    		    	  newobj.accumulate("qrcodeurl", qrcodeurl);
 	    		    	  newobj.accumulate("actionname", QR_LIMIT_SCENE);
	    		    	  newobj.accumulate("sceneid", sceneid);
	    		    	  newobj.accumulate("accountid", account_id);
	    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
   		          }else{
   		          String errmsg =returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
   		          //AccessToken失效,重新获取
   		          if(errmsg!=null&&((errmsg.equals("access_token expired")) || 
   		                  (errmsg.equals("invalid credential")))){
   		        	   token=accesstokenhandle.applyAccessToken();
   				       sb = getBaseUri(this.wxsysConf.getBaseQrcodeUrl());
   	    		       uri = "access_token=" + token;
   	    		       uri=sb.append(uri).toString();
   	    		       jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
   	    		       System.out.println(jsonResult);
   	    		      
   	    		      returnobj=JSONObject.fromObject(jsonResult);
   	    		       ticket =returnobj.get("ticket")==null?null:returnobj.getString("ticket");

   	    		      if(ticket!=null&&!ticket.equals("")){
   	    		    	  String qrcodeurl=this.wxsysConf.getBaseQrcodePicUrl() + ticket; 
   	    		    	  if(!isexist){
   	    		    	   saveqrcde(QR_LIMIT_SCENE, codeobject, sceneid, ticket, qrcodeurl, pkkey);
   	    		    	  }
   	    		    	  JSONObject newobj=new JSONObject();
   	    		    	  newobj.accumulate("qrcodeurl", qrcodeurl);
   	    		    	  newobj.accumulate("actionname", QR_LIMIT_SCENE);
   	    		    	  newobj.accumulate("sceneid", sceneid);
   	    		    	  newobj.accumulate("pkKey", pkkey);
   	    		    	  newobj.accumulate("codeobject", codeobject);
   	    		    	  newobj.accumulate("accountid", account_id);
   	    		    	  outputstr(newobj.toString(), "生成二维码成功", true);
   	    		          }
   	    		       } else{
   	    		    	   outputstr("", "生成带参数二维码出错，errmsg is : " + errmsg, false);   
   	    		       }
   		           }
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
     * 保存二维码信息
     * @param actionname
     * @param object
     * @param sceneid
     * @param ticket
     * @param qrcodeurl
     * @param pkkey
     */
    private void saveqrcde(String actionname,String object,String sceneid,String ticket,String qrcodeurl,String pkkey) {
    	  WxQrcodeDetail detail=new WxQrcodeDetail();
    	  detail.setAccountId(account_id);
    	  detail.setActionName(actionname);
    	  detail.setDr((short)0);
    	  detail.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
    	  detail.setOperateobject(object);
    	  detail.setOperatetype(object);
    	  detail.setPkKey(Long.parseLong(pkkey));
    	  detail.setSceneId(sceneid);
    	  detail.setTicket(ticket);
    	  detail.setQrcodeUrl(qrcodeurl);
    	  basicservice.save(detail);
      }
    
    
    /**
     * 更新二维码信息
     * @param actionname
     * @param object
     * @param sceneid
     * @param ticket
     * @param qrcodeurl
     * @param pkkey
     */
    private void updateqrcde(WxQrcodeDetail qrcode,String ticket,String qrcodeurl) {
    	qrcode.setTicket(ticket);
    	qrcode.setQrcodeUrl(qrcodeurl);
    	qrcode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
  	    basicservice.update(qrcode);
    }
    
    
    private StringBuffer getBaseUri(String baseUri) {
        return new StringBuffer(baseUri);
      }
    
     
}
