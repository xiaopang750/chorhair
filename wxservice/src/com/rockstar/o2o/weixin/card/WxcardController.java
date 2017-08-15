package com.rockstar.o2o.weixin.card;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.weixin.controller.BaseController;
import com.rockstar.o2o.weixin.util.FileuploadUtil;
import com.rockstar.o2o.weixin.util.RequestUtil;

/**
 * 微信卡券
 * @author xucheng
 *
 */
@Controller
@RequestMapping("/wxcard")
public class WxcardController extends BaseController{

	/**
	 * 上传卡券LOGO
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/uploadlogo")
	public void uploadlogo(HttpServletRequest request,HttpServletResponse response,Model model) {  
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String filefrom=getobj.getString("filefrom");
			String filepath=getobj.getString("filepath");
			boolean islocal=true;
			if(filefrom.equals("local")){
				
			}else{
				islocal=false;
			}
			
			StringBuffer buffer=new StringBuffer();
			String logourl=wxsysConf.getBaseCarduploadlogoUrl();
			
			  //获取AccessToken
	        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();
	        
			buffer.append(logourl+accessToken);
			String jsonResult="";
			if(islocal){
				  jsonResult = FileuploadUtil.uploadlocalmedia(buffer.toString(), new File(filepath));	
			}else{
				jsonResult = FileuploadUtil.uploadlongmedia(buffer.toString(), filepath);
			}
			
			 System.out.println(jsonResult);
			 if ((jsonResult != null) && (!jsonResult.equals(""))) {
				   JSONObject returnjson=JSONObject.fromObject(jsonResult);
				    String errmsg = returnjson.get("errmsg")==null?null:returnjson.getString("errmsg");
				    if (errmsg != null) {
				    	 if ((errmsg.equals("access_token expired")) || 
						          (errmsg.equals("invalid credential"))) {
				    		  accessToken = accesstokenhandle.applyAccessToken();
					          buffer=new StringBuffer().append(this.wxsysConf.getBaseCarduploadlogoUrl()).append(accessToken);					          
					          if(islocal){
								  jsonResult = FileuploadUtil.uploadlocalmedia(buffer.toString(), new File(filepath));	
							    }else{
								jsonResult = FileuploadUtil.uploadlongmedia(buffer.toString(), filepath);
							   }
					          
					     	 JSONObject returnobj=JSONObject.fromObject(jsonResult);
					     	 outputstr(returnobj.toString(), "上传logo成功", true);
				    	 }else{
				    		 JSONObject returnobj=JSONObject.fromObject(jsonResult);
					     	 outputstr(returnobj.toString(), "上传logo失败", false);
				    	 }
				      }else{
				    	  JSONObject returnobj=JSONObject.fromObject(jsonResult);
				    	  outputstr(returnobj.toString(), "上传logo成功", true);
				      }
				    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);		
		
	}
	
	/**
	 * 获取卡券颜色列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getcolorlist")
	public void getcolorlist(HttpServletRequest request,HttpServletResponse response,Model model) {  
		try {
		
			StringBuffer buffer=new StringBuffer();
			String colorurl=wxsysConf.getBaseCardcolorlistUrl();
			
			  //获取AccessToken
	        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();
	        
			buffer.append(colorurl+accessToken);
			
			 String jsonResult =httpservice.sendGetRequset(buffer.toString());
			 
			 if ((jsonResult != null) && (!jsonResult.equals(""))) {
				   JSONObject returnjson=JSONObject.fromObject(jsonResult);
				    String errmsg = returnjson.get("errmsg")==null?null:returnjson.getString("errmsg");
				    if (errmsg != null) {
				    	 if ((errmsg.equals("access_token expired")) || 
						          (errmsg.equals("invalid credential"))) {
				    		   accessToken = accesstokenhandle.applyAccessToken();
					          buffer=new StringBuffer().append(this.wxsysConf.getBaseCarduploadlogoUrl()).append(accessToken);
					          
					          jsonResult =httpservice.sendGetRequset(buffer.toString());
					     	 JSONObject returnobj=JSONObject.fromObject(jsonResult);
					     	 outputstr(returnobj.toString(), "查询颜色列表成功", true);
				    	 }else if(errmsg.equals("ok")){
				    		 JSONObject returnobj=JSONObject.fromObject(jsonResult);
					     	 outputstr(returnobj.toString(), "查询颜色列表成功", true);
				    	 }else{
				    		 JSONObject returnobj=JSONObject.fromObject(jsonResult);
					     	 outputstr(returnobj.toString(), "查询颜色列表失败", false);
				    	 }
				      }else{
				    	  JSONObject returnobj=JSONObject.fromObject(jsonResult);
				    	  outputstr(returnobj.toString(), "查询颜色列表成功", true);
				      }
				    }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);		
		
	}
}
