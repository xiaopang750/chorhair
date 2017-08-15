package com.rockstar.o2o.weixin.controller;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.weixin.util.RedisUtils;
import com.rockstars.o2o.weixin.util.encrypt.EncryptUtil;


@Controller
@RequestMapping("/wxjs")
public class WxjsController extends BaseController{

	/**
	 * 微信js
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getinfo")
	public void getinfo(HttpServletRequest request,HttpServletResponse response,Model model) {  
		try {
			//JSONObject obj=RequestUtil.getPostString(request);
			String noncestr=request.getParameter("noncestr");
			String timestamp=request.getParameter("timestamp");
			String geturl=request.getParameter("url");
			String jsonp=request.getParameter("jsonpcallback");
						
			StringBuffer buffer=new StringBuffer();
			buffer.append("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=");;
		    //获取AccessToken
	        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();
	         boolean isredis=true;
			 String oldjsapi_ticket=null;
			try {
				oldjsapi_ticket=RedisUtils.getObject(account_id+":"+"jsapi_ticket")==null?null:RedisUtils.getObject(account_id+":"+"jsapi_ticket").toString();
			} catch (Exception e) {
				// TODO: handle exception
				isredis=false;
			}
			
			if(isredis&&oldjsapi_ticket!=null){
	             String sign=Getsign(noncestr, oldjsapi_ticket, timestamp, geturl);
	             System.out.println(oldjsapi_ticket);
	        	 JSONObject wxreturnobj=new JSONObject();
	        	 wxreturnobj.accumulate("sign", sign);
	        	 String returnstr = jsonp + "(" +wxreturnobj.toString()+")";
	             response.setCharacterEncoding("UTF-8");
	             PrintWriter out = response.getWriter();
	             out.println(returnstr);
	             out.close();
			}else{
	        buffer.append(accessToken+"&type=jsapi");
	        
	        String url=buffer.toString();
	        
            String jsonResult = this.httpservice.sendGetRequset(url);
		    
		    if ((jsonResult != null) && (!jsonResult.equals(""))) {
	            JSONObject returnobj=JSONObject.fromObject(jsonResult);
		        String errmsg =(String)returnobj.get("errmsg");
		        if (errmsg != null){
		          if ((errmsg.equals("access_token expired")) || 
		            (errmsg.equals("invalid credential"))) {
		            accessToken = accesstokenhandle.applyAccessToken();
		            buffer=new StringBuffer().append("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=");;
			        buffer.append(accessToken+"&type=jsapi");
			        
		            jsonResult = this.httpservice.sendGetRequset(url);
		            returnobj=JSONObject.fromObject(jsonResult);;
		            String jsapi_ticket=returnobj.getString("ticket");
		            System.out.println(oldjsapi_ticket);
		            try {
		            	 RedisUtils.setKeyBytime(account_id+":"+"jsapi_ticket",jsapi_ticket,7200);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("redis报错");
					}		         	            
		            String sign=Getsign(noncestr, jsapi_ticket, timestamp, geturl);
		        	JSONObject wxreturnobj=new JSONObject();
		        	wxreturnobj.accumulate("sign", sign);
		        	String returnstr = jsonp + "(" +wxreturnobj.toString()+")";
		             response.setCharacterEncoding("UTF-8");
		             PrintWriter out = response.getWriter();
		             out.println(returnstr);
		             out.close();
		          } else if(errmsg.equals("ok")){
			            String jsapi_ticket=returnobj.getString("ticket");
			            System.out.println(oldjsapi_ticket);
			            try {
			            	 RedisUtils.setKeyBytime(account_id+":"+"jsapi_ticket",jsapi_ticket,7200);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("redis报错");
						}	
			            String sign=Getsign(noncestr, jsapi_ticket, timestamp, geturl);
			        	JSONObject wxreturnobj=new JSONObject();
			        	wxreturnobj.accumulate("sign", sign);
			        	String returnstr = jsonp + "(" +wxreturnobj.toString()+")";
			             response.setCharacterEncoding("UTF-8");
			             PrintWriter out = response.getWriter();
			             out.println(returnstr);
			             out.close();
			        	//outputstr(wxreturnobj.toString(), "生成签名成功", true);
			        	
		          }else{
		        	  outputstr("", "生成签名失败,errmsg is :"+ errmsg, false);
		          }
		        }
		      }
			}
		 } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
           outputexceptionstr();
           output(response, pojo);
		 }
		
	}
		
	
	 /**
	   * 微信消息校验
	   * @param signature
	   * @param timestamp
	   * @param nonce
	   * @return
	   */
	  private String Getsign(String noncestr, String jsapi_ticket, String timestamp,String url)
	  {
//	        String[] array = {jsapi_ticket,noncestr,timestamp,url};
//	        Arrays.sort(array);
//	        String result = "";
//
//	        for (String i : array) {
//	          result = result + i;
//	        }
	        String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket,
	                "timestamp=" + timestamp, "noncestr=" + noncestr, "url=" + url };
	        Arrays.sort(paramArr);
	      // 将排序后的结果拼接成一个字符串
	       String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
	                .concat("&"+paramArr[3]);
	       System.out.println("str:"+content);
	        String ret="";
	        try
	        {
	           ret = EncryptUtil.SHAsum(content.getBytes());
	           return ret;
	        } catch (NoSuchAlgorithmException ex) {
	          ex.printStackTrace();
	        }
            return "";
	  }

}
