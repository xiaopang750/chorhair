package com.rockstar.o2o.controller.weixin;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.file.FileManegeUtil;
import com.rockstar.o2o.util.file.FilethumbUtil;


@Controller
@RequestMapping("/wxqrcode")
public class WxqrcodeDownController extends BaseController{

	/**
	 * 二维码下载
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/postdownload")
	public void createQrcode(HttpServletRequest request,HttpServletResponse response,Model model) {  
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			String qrcodeUrl=receiveobj.getString("qrcodeUrl");
			String width=receiveobj.getString("width");
			String heigth=receiveobj.getString("height");
			
			//读取原二维码地址,转成流
			InputStream oldstream=FilethumbUtil.readFileInfoAsStream(qrcodeUrl);
			//生成相应尺寸的流信息
			InputStream newstream=FilethumbUtil.saveImageAsJpgByStream(oldstream, "", Integer.parseInt(width), Integer.parseInt(heigth));
		    //生成新的尺寸的二维码
			String srcurl=FileManegeUtil.writeFileByStream(newstream,"jpg");
			
			if(srcurl!=null){
				JSONObject obj=new JSONObject();
				obj.accumulate("qrcodeurl", srcurl);
				//保存附件上传记录
				UserFile file=new UserFile();
				file.setBemodel("生成"+width+"*"+heigth+"二维码");
				file.setFilepath(srcurl);
				file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				file.setDr((short)0);
				fileservice.save(file);
				
				 response.setContentType("application/octet-stream");
				 response.addHeader("Access-Control-Allow-Origin", "*");
				 response.setHeader("Content-disposition", "attachment; filename="+UUID.randomUUID().toString()+".jpg");	 
				 URL mediaUrl = new URL(srcurl);
					HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
							.openConnection();
					meidaConn.setDoOutput(true);
					meidaConn.setRequestMethod("GET");
					// 获取媒体文件的输入流（读取文件）
					DataInputStream bis = new DataInputStream(
							meidaConn.getInputStream());
					byte[] buf = new byte[8096];
					int size = 0;
					while ((size = bis.read(buf)) != -1) {
						// 将图片文件写到输出流
						response.getOutputStream().write(buf, 0, size);
					}
					// 请求体结束
					response.getOutputStream().close();
					bis.close();			
					response.getOutputStream().flush();
			   }else{
				outputstr("", "下载出错", false, null);
			}	
		   } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}

	}
	
	
	/**
	 * 二维码下载
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getdownload")
	public void getdownload(HttpServletRequest request,HttpServletResponse response,Model model) {  
		try {
			String qrcodeUrl=request.getParameter("qrcodeUrl");
			String width=request.getParameter("width");
			String heigth=request.getParameter("height");
			
			//读取原二维码地址,转成流
			InputStream oldstream=FilethumbUtil.readFileInfoAsStream(qrcodeUrl);
			//生成相应尺寸的流信息
			InputStream newstream=FilethumbUtil.saveImageAsJpgByStream(oldstream, "", Integer.parseInt(width), Integer.parseInt(heigth));
		    //生成新的尺寸的二维码
			String srcurl=FileManegeUtil.writeFileByStream(newstream,"jpg");
			
			if(srcurl!=null){
				JSONObject obj=new JSONObject();
				obj.accumulate("qrcodeurl", srcurl);
				//保存附件上传记录
				UserFile file=new UserFile();
				file.setBemodel("生成"+width+"*"+heigth+"二维码");
				file.setFilepath(srcurl);
				file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				file.setDr((short)0);
				fileservice.save(file);
				
				 response.setContentType("application/octet-stream");
				 response.addHeader("Access-Control-Allow-Origin", "*");
				 response.setHeader("Content-disposition", "attachment; filename="+UUID.randomUUID().toString()+".jpg");	 
				 URL mediaUrl = new URL(srcurl);
					HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
							.openConnection();
					meidaConn.setDoOutput(true);
					meidaConn.setRequestMethod("GET");
					// 获取媒体文件的输入流（读取文件）
					DataInputStream bis = new DataInputStream(
							meidaConn.getInputStream());
					byte[] buf = new byte[8096];
					int size = 0;
					while ((size = bis.read(buf)) != -1) {
						// 将图片文件写到输出流
						response.getOutputStream().write(buf, 0, size);
					}
					// 请求体结束
					response.getOutputStream().close();
					bis.close();			
					response.getOutputStream().flush();
			   }else{
				outputstr("", "下载出错", false, null);
			}	
		   } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}

	}
	
}
