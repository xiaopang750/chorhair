package com.rockstar.o2o.weixin.menu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.weixin.controller.BaseController;
import com.rockstar.o2o.weixin.util.RequestUtil;

/**
 * 微信菜单服务
 * @author xc
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

	  private static final Logger logger = Logger.getLogger(MenuController.class);
	  
	  /**
	   * 创建微信自定义菜单
	   * @param request
	   * @param response
	   */
	  @RequestMapping("createmenu")
	  public void createmenu(HttpServletRequest request,HttpServletResponse response) {
	     try {
			JSONObject obj=RequestUtil.getPostString(request);
	        String jsonData=obj.toString();
	        
		    //获取AccessToken
	        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

		    logger.debug("postData is : " + jsonData);
		    
		    StringBuffer postUrl=new StringBuffer().append(wxsysConf.getBaseCreateMenuUrl()).append("access_token=").append(accessToken);
		   
		    String jsonResult= httpservice.sendPostRequset(postUrl.toString(), jsonData);
		 
		    if ((jsonResult != null) && (!jsonResult.equals(""))) {
		        logger.debug("json is : " + jsonResult);
	            JSONObject returnobj=JSONObject.fromObject(jsonResult);
		        String errmsg =(String)returnobj.get("errmsg");
		        if (errmsg != null)
		          if ((errmsg.equals("access_token expired")) || 
		            (errmsg.equals("invalid credential"))) {
		            logger.debug("accessToken已过期，需要重新申请");
		            accessToken = accesstokenhandle.applyAccessToken();
		            postUrl=new StringBuffer().append(wxsysConf.getBaseCreateMenuUrl()).append("access_token=" + accessToken);		            
		            jsonResult = this.httpservice.sendPostRequset(postUrl.toString(),jsonData);
		            returnobj=JSONObject.fromObject(jsonResult);;
		            errmsg = (String)returnobj.get("errmsg");
		              if (errmsg.equals("ok")) {
		                outputstr("", "创建自定义菜单成功", true);
		              }else{
		            	outputstr("", "创建自定义菜单失败,errmsg is :"+ errmsg, false);
		              }
		          } else if(errmsg.equals("ok")){		           
		        	  outputstr("", "创建自定义菜单成功", true);
		          }else{
		        	  outputstr("", "创建自定义菜单失败,errmsg is :"+ errmsg, false);
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
	   * 删除微信自定义菜单
	   * @param request
	   * @param response
	   */
	  @RequestMapping("deletemenu")
	  public void deletemenu(HttpServletRequest request,HttpServletResponse response) {
	     try {
			JSONObject obj=RequestUtil.getPostString(request);
	        String jsonData=obj.toString();
	        
		    //获取AccessToken
	        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

		    logger.debug("postData is : " + jsonData);
		    
		    StringBuffer postUrl=new StringBuffer().append(wxsysConf.getBaseDeleteMenuUrl()).append("access_token=").append(accessToken);
		   
		    String jsonResult= httpservice.sendPostRequset(postUrl.toString(), jsonData);
		 
		    if ((jsonResult != null) && (!jsonResult.equals(""))) {
		        logger.debug("json is : " + jsonResult);
	            JSONObject returnobj=JSONObject.fromObject(jsonResult);
		        String errmsg =(String)returnobj.get("errmsg");
		        if (errmsg != null)
		          if ((errmsg.equals("access_token expired")) || 
		            (errmsg.equals("invalid credential"))) {
		            logger.debug("accessToken已过期，需要重新申请");
		            accessToken = accesstokenhandle.applyAccessToken();
		            postUrl=new StringBuffer().append(wxsysConf.getBaseDeleteMenuUrl()).append("access_token=" + accessToken);		            
		            jsonResult = this.httpservice.sendPostRequset(postUrl.toString(),jsonData);
		            returnobj=JSONObject.fromObject(jsonResult);;
		            errmsg = (String)returnobj.get("errmsg");
		              if (errmsg.equals("ok")) {
		                outputstr("", "删除自定义菜单成功", true);
		              }else{
		            	outputstr("", "删除自定义菜单失败,errmsg is :"+ errmsg, false);
		              }
		          } else if(errmsg.equals("ok")){		           
		        	  outputstr("", "删除自定义菜单成功", true);
		          }else{
		        	  outputstr("", "删除自定义菜单失败,errmsg is :"+ errmsg, false);
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
	   * 查询微信自定义菜单
	   * @param request
	   * @param response
	   */
	  @RequestMapping("querymenu")
	  public void querymenu(HttpServletRequest request,HttpServletResponse response) {
		  try {
			    //获取AccessToken
		        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();
			    
			    StringBuffer postUrl=new StringBuffer().append(wxsysConf.getBaseQueryMenuUrl()).append("access_token=").append(accessToken);
			   
			    String jsonResult= httpservice.sendGetRequset(postUrl.toString());
			 
			    if ((jsonResult != null) && (!jsonResult.equals(""))) {
			        logger.debug("json is : " + jsonResult);
		            JSONObject returnobj=JSONObject.fromObject(jsonResult);
			        String errmsg =(String)returnobj.get("errmsg");
			        JSONObject menu =(JSONObject)returnobj.get("menu");
			        if (errmsg != null)
			          if ((errmsg.equals("access_token expired")) || 
			            (errmsg.equals("invalid credential"))) {
			            logger.debug("accessToken已过期，需要重新申请");
			            accessToken = accesstokenhandle.applyAccessToken();
			            postUrl=new StringBuffer().append(wxsysConf.getBaseQueryMenuUrl()).append("access_token=" + accessToken);		            
			            jsonResult = this.httpservice.sendGetRequset(postUrl.toString());
			            returnobj=JSONObject.fromObject(jsonResult);;
			            errmsg = (String)returnobj.get("errmsg");
			            menu =(JSONObject)returnobj.get("menu");
			              if (menu!=null) {
			                outputstr(returnobj.toString(), "查询自定义菜单成功", true);
			              }else{
			            	outputstr("", "查询自定义菜单失败,errmsg is :"+ errmsg, false);
			              }
			          }else{
			        	  outputstr("", "查询自定义菜单失败,errmsg is :"+ errmsg, false);
			          }
			        else
			        	outputstr(returnobj.toString(), "查询自定义菜单成功", true);  
			    }
		   } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		  }
		  output(response, pojo);
	  }
	  
}
