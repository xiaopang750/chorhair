package com.rockstar.o2o.weixin.message.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.weixin.controller.BaseController;
import com.rockstar.o2o.weixin.util.RequestUtil;

/**
 * 消息类
 * @author xc
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{

	  private static final Logger logger = Logger.getLogger(MessageController.class);
 
	   /**
	    * 根据用户ID发送消息
	    * @param request
	    * @param response
	    */
	  @RequestMapping("/pushmsgbyid")
	  public void pushCustomMsgByOpenId(HttpServletRequest request,HttpServletResponse response) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String jsonData=obj.toString();
			
		    StringBuffer sb =new StringBuffer().append(wxsysConf.getBaseCustomSendMsgUrl());
			   
		    //获取AccessToken
	        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

		    sb.append("access_token=" + accessToken);
		    
		    String uri=sb.toString();
		    
		    String jsonResult = this.httpservice.sendPostRequset(uri, jsonData);
		    
		    if ((jsonResult != null) && (!jsonResult.equals(""))) {
		        logger.debug("json is : " + jsonResult);
	            JSONObject returnobj=JSONObject.fromObject(jsonResult);
		        String errmsg =returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
		        if (errmsg != null)
		          if ((errmsg.equals("access_token expired")) || 
		            (errmsg.equals("invalid credential"))) {
		            logger.debug("accessToken已过期，需要重新申请");
		            accessToken = accesstokenhandle.applyAccessToken();
		            sb=new StringBuffer().append(wxsysConf.getBaseCustomSendMsgUrl()).append("access_token=" + accessToken);
		            uri=sb.toString();
		            jsonResult = this.httpservice.sendPostRequset(uri,jsonData);
		            returnobj=JSONObject.fromObject(jsonResult);;
		            errmsg = returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
		              if (errmsg.equals("ok")) {
		                outputstr("", "发送客服消息成功", true);
		              }else{
		            	outputstr("", "主动推送信息出错,errmsg is :"+ errmsg, false);
		              }
		          } else if(errmsg.equals("ok")){		           
		        	  outputstr("", "发送客服消息成功", true);
		          }else{
		        	  outputstr("", "主动推送信息出错,errmsg is :"+ errmsg, false);
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
	   * 根据OpenID群发消息
	   * @param request
	   * @param response
	   */
	  @RequestMapping("/pushgroupmsgbyid")
	  public void sendMsgToGroupByOpenid(HttpServletRequest request,HttpServletResponse response) {
		  
		  try {
		        JSONObject obj=JSONObject.fromObject(request);
		        
		        String postData=obj.toString();
		        
			    //获取AccessToken
		        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

			    logger.debug("postData is : " + postData);
			    
			    StringBuffer postUrl=new StringBuffer().append(wxsysConf.getBaseSendNewsBylistUrl()).append("access_token=").append(accessToken);
			   
			    String jsonResult= httpservice.sendPostRequset(postUrl.toString(), postData);
			 
			    if ((jsonResult != null) && (!jsonResult.equals(""))) {
			        logger.debug("json is : " + jsonResult);
		            JSONObject returnobj=JSONObject.fromObject(jsonResult);
			        String errmsg =returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
			        if (errmsg != null)
			          if ((errmsg.equals("access_token expired")) || 
			            (errmsg.equals("invalid credential"))) {
			            logger.debug("accessToken已过期，需要重新申请");
			            //AccessToken失效，重新获取
			            accessToken = accesstokenhandle.applyAccessToken();
			            postUrl=new StringBuffer().append(wxsysConf.getBaseSendNewsBylistUrl()).append("access_token=").append(accessToken);
			            jsonResult = this.httpservice.sendPostRequset(postUrl.toString(),postData);
			            returnobj=JSONObject.fromObject(jsonResult);;
			            errmsg = returnobj.get("errmsg")==null?null:returnobj.getString("errmsg");
			              if (errmsg.equals("ok")) {
			                outputstr("", "群发消息成功", true);
			              }else{
			            	outputstr("", "群发消息出错,errmsg is :"+ errmsg, false);
			              }
			          } else {		           
			            	outputstr("", "群发消息出错,errmsg is :"+ errmsg, false);
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
	     * 上传本地文件
	     * @author xc
	     * @param accessToken 接口访问凭证 
	     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file) 
	     * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息 
	     * @param mediaFileUrl 媒体文件的url 
	     * 上传的媒体文件限制 
	       * 图片（image）:1MB，支持JPG格式 
	       * 语音（voice）：2MB，播放长度不超过60s，支持AMR格式 
	       * 视频（video）：10MB，支持MP4格式 
	       * 普通文件（file）：10MB 
	     * */  
	  	protected static String uploadlocalmedia(String url, File file) throws Exception {	  		
	  	   URL urlObj = new URL(url);   
	       HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();   
	       con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式   
	       con.setDoInput(true);   
	       con.setDoOutput(true);   
	       con.setUseCaches(false); // post方式不能使用缓存   
	      // 设置请求头信息   
	       con.setRequestProperty("Connection", "Keep-Alive");   
	      con.setRequestProperty("Charset", "UTF-8");   
	      // 设置边界   
	      String BOUNDARY = "----------" + System.currentTimeMillis();   
	      con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);   
	      // 请求正文信息   
	      StringBuilder sb = new StringBuilder();   
	      sb.append("--"); // 必须多两道线   
	      sb.append(BOUNDARY);   
	      sb.append("\r\n");   
	      sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");   
	      sb.append("Content-Type:application/octet-stream\r\n\r\n");   
	      byte[] head = sb.toString().getBytes("utf-8");   

	      // 获得输出流   
	      OutputStream out = new DataOutputStream(con.getOutputStream());   
	      // 输出表头   
	      out.write(head);   
	      // 文件正文部分   
	      // 把文件已流文件的方式 推入到url中   
	      DataInputStream in = new DataInputStream(new FileInputStream(file));   
	      int bytes = 0;   
	      byte[] bufferOut = new byte[1024];   
	      while ((bytes = in.read(bufferOut)) != -1) {   
	      out.write(bufferOut, 0, bytes);   
	      }   

	      in.close();   
	      // 结尾部分   
	      byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线   
	      out.write(foot);   
	      out.flush();   
	      out.close();   
	      StringBuffer buffer = new StringBuffer();   
	      BufferedReader reader = null;   

	      try {   

	      // 定义BufferedReader输入流来读取URL的响应   
	      reader = new BufferedReader(new InputStreamReader(con.getInputStream()));   
	      String line = null;   
	      while ((line = reader.readLine()) != null) {   
	      buffer.append(line);   
	      }   
	       return buffer.toString();
	      } catch (IOException e) {   
	      e.printStackTrace();     
	    
	       } finally {   
	      if(reader!=null){   
	      reader.close();   
	       }   
	      }   
	       return "";
	  	}
	  	
	  	  /** 
	       * 上传远程文件
	       * @author xc
	       * @param accessToken 接口访问凭证 
	       * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video），普通文件(file) 
	       * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息 
	       * @param mediaFileUrl 媒体文件的url 
	       * 上传的媒体文件限制 
	         * 图片（image）:1MB，支持JPG格式 
	         * 语音（voice）：2MB，播放长度不超过60s，支持AMR格式 
	         * 视频（video）：10MB，支持MP4格式 
	         * 普通文件（file）：10MB 
	        * */  
		public static String uploadlongmedia(String uploadMediaUrl, String mediaFileUrl) {
			// 定义数据分隔符
			String boundary = "----------" + System.currentTimeMillis();
			try {
				URL uploadUrl = new URL(uploadMediaUrl);
				HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
						.openConnection();
				uploadConn.setDoOutput(true);
				uploadConn.setDoInput(true);
				uploadConn.setRequestMethod("POST");
				// 设置请求头Content-Type
				uploadConn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
				// 获取媒体文件上传的输出流（往微信服务器写数据）
				OutputStream outputStream = uploadConn.getOutputStream();

				URL mediaUrl = new URL(mediaFileUrl);
				HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
						.openConnection();
				meidaConn.setDoOutput(true);
				meidaConn.setRequestMethod("GET");

				// 从请求头中获取内容类型
				// 根据内容类型判断文件扩展名
				String fileExt = mediaFileUrl.substring(mediaFileUrl.lastIndexOf("."));
				// 请求体开始
				outputStream.write(("--" + boundary + "\r\n").getBytes());
				outputStream
						.write(String
								.format("Content-Disposition: form-data; name=\"media\"; filename=\"normal%s\"\r\n",
										fileExt).getBytes());
				outputStream.write(String.format("Content-Type:application/octet-stream\r\n\r\n").getBytes());

				// 获取媒体文件的输入流（读取文件）
				BufferedInputStream bis = new BufferedInputStream(
						meidaConn.getInputStream());
				byte[] buf = new byte[8096];
				int size = 0;
				while ((size = bis.read(buf)) != -1) {
					// 将媒体文件写到输出流（往微信服务器写数据）
					outputStream.write(buf, 0, size);
				}
				// 请求体结束
				outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
				outputStream.close();
				bis.close();
				meidaConn.disconnect();

				// 获取媒体文件上传的输入流（从微信服务器读数据）
				InputStream inputStream = uploadConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				StringBuffer buffer = new StringBuffer();
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				// 释放资源
				inputStream.close();
				inputStream = null;
				uploadConn.disconnect();

				return buffer.toString();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "";
		}
		
	  	
	  /**
	   * 上传临时素材
	   * @param request
	   * @param response
	   */
	  @RequestMapping("/uploadtmpmedia")
	  public void uploadtmpmedia(HttpServletRequest request,HttpServletResponse response) {
		  try {
			 JSONObject getobj=RequestUtil.getPostString(request);
			 String type=getobj.getString("type");
			 String media=getobj.getString("media");
		     //获取AccessToken
	         String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();	   
			 String  url= new StringBuffer().append(wxsysConf.getBaseUploadMediaUrl()).append(
			     "access_token=").append(accessToken).append("&type=").append(type).toString();
			   String result = "";   
			    //上传本地文件
		        if(!media.startsWith("http")){
		        File file = new File(media);   
		        if (!file.exists() || !file.isFile()) {   
		        	throw new Exception("文件不存在");  
		        }  
		        result=uploadlocalmedia(url, file);
		        }else{
		        //上传远程文件
		        result=uploadlongmedia(url,media);
		        }
		       
			   if ((result != null) && (!result.equals(""))) {
			   logger.debug("json is : " + result);
			   JSONObject returnjson=JSONObject.fromObject(result);
			    String errmsg = returnjson.get("errmsg")==null?null:returnjson.getString("errmsg");
			    if (errmsg != null) {
			        if ((errmsg.equals("access_token expired")) || 
			          (errmsg.equals("invalid credential"))) {
			          logger.debug("accessToken失效");
			          accessToken = accesstokenhandle.applyAccessToken();
			          url=new StringBuffer().append(this.wxsysConf.getBaseUploadMediaUrl()).append(
					     "access_token=").append(accessToken).append("&type=").append(type).toString();
			          
			             //上传本地文件
			            if(!media.startsWith("http")){
					        File file = new File(media);   
					        if (!file.exists() || !file.isFile()) {   
					            throw new IOException("文件不存在");   
					        }  
					        result=uploadlocalmedia(url, file);
					        }else{
					        //上传远程文件
					        result=uploadlongmedia(url,media);
					        }
			          
			           returnjson=JSONObject.fromObject(result);
			           String media_id="";
				      if(returnjson.get("media_id")!=null){
				    	   media_id=(String) returnjson.getString("media_id");
				      }else if(returnjson.get("thumb_media_id")!=null){
				    	   media_id=(String) returnjson.getString("thumb_media_id");
				      }
				      
				      JSONObject returnobj=new JSONObject();
				      returnobj.accumulate("media_id", media_id);
				      
				      
				      outputstr(returnobj.toString(), "上传文件成功", true);
			        }else{
			           outputstr("", "上传文件失败，errmsg is : " + errmsg, false);
			        }
			      }else{
			    	  String media_id="";
				      if(returnjson.get("media_id")!=null){
				    	   media_id=(String) returnjson.getString("media_id");
				      }else{
				    	   media_id=(String) returnjson.getString("thumb_media_id");
				      }				      
				      JSONObject returnobj=new JSONObject();
				      returnobj.accumulate("media_id", media_id);
				      
				      outputstr(returnobj.toString(), "上传文件成功", true);
				      
			      }
			    
			  }else{
				   outputstr("", "上传文件失败", false); 
			  }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		  output(response, pojo);  
	  }
		
	   
	  /**
	   * 上传永久素材多图文
	   * @param request
	   * @param response
	   * @throws Exception
	   */
		public void uploadlongnews(HttpServletRequest request,HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			try {
				String jsonData=RequestUtil.getPostString(request).toString();
		    	String media_id="";
			     //获取AccessToken
		         String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();	   
				 String  url= new StringBuffer().append(wxsysConf.getBaseUploadNewsUrl()).append(
				     "access_token=").append(accessToken).toString();
			    String jsonResult = this.httpservice.sendPostRequset(url, jsonData);
			    if ((jsonResult != null) && (!jsonResult.equals(""))) {
			      logger.debug("json is : " + jsonResult);
			      JSONObject returnjson = JSONObject.fromObject(jsonResult);
			      String errmsg = (String)returnjson.get("errmsg");
			      media_id = (String)returnjson.get("media_id");
			      if (errmsg != null){
			        if ((errmsg.equals("access_token expired")) || 
			          (errmsg.equals("invalid credential"))) {
			          logger.debug("accessToken已过期，需要重新申请");
			          accessToken = accesstokenhandle.applyAccessToken();
				      url= new StringBuffer().append(wxsysConf.getBaseUploadNewsUrl()).append(
					     "access_token=").append(accessToken).toString();
					   jsonResult = this.httpservice.sendPostRequset(url, jsonData);
					    returnjson = JSONObject.fromObject(jsonResult);
					    errmsg = (String)returnjson.get("errmsg");
					     media_id = (String)returnjson.get("media_id");				    	
				    	  if(media_id!=null){
				    		  JSONObject returnobj=new JSONObject();
					    	  returnobj.accumulate("media_id", media_id);
					    	  outputstr(returnobj.toString(), "上传多图文永久素材成功", true);   
				    	  }else{
				    		  outputstr("", "上传多图文永久素材失败,errmsg is :"+ errmsg, false);  
				    	  }
	
			        } else {
			         outputstr("", "上传多图文永久素材失败,errmsg is :"+ errmsg, false);
			        }
			      } else{
			    	  JSONObject returnobj=new JSONObject();
			    	  returnobj.accumulate("media_id", media_id);
			    	  outputstr(returnobj.toString(), "上传多图文永久素材成功", true);
			      }
			    }else{
			    	 outputstr("", "上传失败", false);
			    }
				
			} catch (Exception e) {
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			}
          output(response, pojo);
		}
		
		/**
		 * 获取素材列表
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		@RequestMapping("/getmateriallist")
		public void getmateriallist(HttpServletRequest request,HttpServletResponse response) throws Exception {
			try {	
				 String jsonData=RequestUtil.getPostString(request).toString();								
				 //获取AccessToken
		         String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();	   
				 String  url= new StringBuffer().append(wxsysConf.getBaseGetLongMaterialUrl()).append(
				     "access_token=").append(accessToken).toString();
			     String jsonResult = this.httpservice.sendPostRequset(url, jsonData.toString());
			     jsonResult=new String(jsonResult.getBytes("ISO-8859-1"), "utf-8");
			     if ((jsonResult != null) && (!jsonResult.equals(""))) {
				      logger.debug("json is : " + jsonResult);
				      JSONObject returnjson = JSONObject.fromObject(jsonResult);
				      String errmsg = (String)returnjson.get("errmsg");
				      Integer errcode = (Integer)returnjson.get("errcode");
				      if (errmsg != null){
				        if ((errmsg.equals("access_token expired")) || 
				          (errmsg.equals("invalid credential"))||errcode==42001) {
				          logger.debug("accessToken已过期，需要重新申请");
				          accessToken = accesstokenhandle.applyAccessToken();
					      url= new StringBuffer().append(wxsysConf.getBaseGetLongMaterialUrl()).append(
						     "access_token=").append(accessToken).toString();
						   jsonResult = this.httpservice.sendPostRequset(url, jsonData);
						   jsonResult=new String(jsonResult.getBytes("ISO-8859-1"), "utf-8");
						    returnjson = JSONObject.fromObject(jsonResult);
						    errmsg = (String)returnjson.get("errmsg");		
						    if(errmsg!=null){
						      outputstr("", "获取素材失败,errmsg is :"+ errmsg, false);
						    }else{						   	
						      outputstr(jsonResult, "查询素材成功", true);
						      }		
				            } else {
				           outputstr("", "获取素材失败,,errmsg is :"+ errmsg, false);
				          }
				        } else{
				         outputstr(jsonResult, "查询素材成功", true);
				      }
				    }
			} catch (Exception e) {
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
		}
}
