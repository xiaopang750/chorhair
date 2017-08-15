package com.rockstar.o2o.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.util.file.FileManegeUtil;
import com.rockstar.o2o.util.file.FilethumbUtil;


@Controller
@RequestMapping("/wxqrcode")
public class WxqrcodeDownController{
	 
	
	/**
	* 将输出的转换成流信息
	* @param srcImage
	* @param imgType
	* @return
	*/
	public static InputStream ImageToInputStream(BufferedImage srcImage,String imgType){
				ByteArrayOutputStream out = new ByteArrayOutputStream(); 
				InputStream is=null;
			try {
				ImageIO.write(srcImage, imgType, out);
				is=new ByteArrayInputStream(out.toByteArray()); 
				} catch (IOException e) {
					e.printStackTrace();
			 }		
		return is;
	}
	
	 /**
	 *@param photopath : 原图存放的路径
	 *@param logopath : logo图像存放的路径
	 *@param savepath : 目标输出保存的路径
	 *@param x : logo图像在合并到原图中的显示位置x座标
	 *@param y : logo图像在合并到原图中的显示位置y座标
	 */
	  public static InputStream addImageLogo(InputStream stream,File file,int x,int y)
	  throws IOException,FileNotFoundException{
	 
	  Image image=ImageIO.read(stream);
	  int pwidth=image.getWidth(null);
	  int pheight=image.getHeight(null);
	 
	  BufferedImage buffimage=new BufferedImage(pwidth,pheight,BufferedImage.TYPE_INT_BGR);
	  Graphics g=buffimage.createGraphics();
	  g.drawImage(image,0,0,pwidth,pheight,null);
	  
	  Image logo=ImageIO.read(file);
	  int lwidth=logo.getWidth(null);
	  int lheight=logo.getHeight(null);
	  g.drawImage(logo,x,y,lwidth,lheight,null);
//	  g.setFont(new Font("宋体",Font.BOLD,20));
//	  g.setColor(Color.pink);
//	  g.drawString("虫二美发", x+5, y+lheight+15);
	 
	  g.dispose();

      return ImageToInputStream(buffimage, "jpg");
	  }
	  
	/**
	 * 二维码下载
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getdownload")
	public void getdownload(HttpServletRequest request,HttpServletResponse response,Model model) {  
		JSONObject returnobj=new JSONObject();
		try {
			String qrcodeUrl=request.getParameter("qrcodeUrl");
			String width=request.getParameter("width");
			String heigth=request.getParameter("height");
			
			String classPath = WxqrcodeDownController.class.getResource("").getPath(); 
			String filename = "logo_"+width+".png";		  
			String FilePath=classPath+filename;
			 
			File fromFile=new File(FilePath);
			BufferedImage srcImage = ImageIO.read(fromFile);
			int logox=srcImage.getWidth();
			int logoy=srcImage.getHeight();
			
			int locationx=(Integer.parseInt(width)-logox)/2;
			int locationy=(Integer.parseInt(heigth)-logoy)/2;
			
			//读取原二维码地址,转成流
			InputStream oldstream=FilethumbUtil.readFileInfoAsStream(qrcodeUrl);
			//生成相应尺寸的流信息
			InputStream newstream=FilethumbUtil.saveImageAsJpgByStream(oldstream, "", Integer.parseInt(width), Integer.parseInt(heigth));
		    //加入logo
			InputStream logostream=addImageLogo(newstream, fromFile,locationx, locationy);
			//生成新的尺寸的二维码
			String srcurl=FileManegeUtil.writeFileByStream(logostream,"jpg");
			
			if(srcurl!=null){
				JSONObject obj=new JSONObject();
				obj.accumulate("qrcodeurl", srcurl);
				
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
				returnobj.accumulate("msg", "下载出错");
				returnobj.accumulate("issuccess", false);
				output(response, returnobj.toString());
			}	
		   } catch (Exception e) {
			// TODO: handle exception
		    e.printStackTrace();
		    returnobj.accumulate("msg", "数据异常");
			returnobj.accumulate("issuccess", false);
			output(response, returnobj.toString());
		   }
	}

	
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void output(HttpServletResponse response, String str) {
		try {
			response.setContentType("text/html; charset=utf-8");
		    response.getOutputStream().write(str.getBytes("UTF-8") );
		    response.getOutputStream().flush();	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
		
}
