package com.rockstar.o2o.util.file;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;



/**
 * 图片缩率处理类
 * @author xc
 * 
 */
public class FilethumbUtil {
	
	/**
	 * 图片缩率
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		 // targetW，targetH分别表示目标长和宽
		 int type = source.getType();
		 BufferedImage target = null;
		 double sx = (double) targetW / source.getWidth();
		 double sy = (double) targetH / source.getHeight();
		 //这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		 //则将下面的if else语句注释即可
		 if(sx>sy)
		 {
		 sx = sy;
		 targetW = (int)(sx * source.getWidth());
		 }else{
		 sy = sx;
		 targetH = (int)(sy * source.getHeight());
		 }
		 if (type == BufferedImage.TYPE_CUSTOM) { 
		 ColorModel cm = source.getColorModel();
		 WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
		 boolean alphaPremultiplied = cm.isAlphaPremultiplied();
		 target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		 } else
		 target = new BufferedImage(targetW, targetH, type);
		 Graphics2D g = target.createGraphics();
		 g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		 g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		 g.dispose();
		 return target;
		 }
	
	
	    /**
	     * 读取本地文件
	     * @param fromFileStr
	     * @param saveToFileStr
	     * @param width
	     * @param hight
	     * @throws Exception
	     */
		 public static void saveImageAsJpgByLocal (String fromFileStr,String saveToFileStr,int width,int hight)
		 throws Exception {
		 BufferedImage srcImage;
		 String imgType = "JPEG";
		 if (fromFileStr.toLowerCase().endsWith(".png")) {
		 imgType = "PNG";
		 }
		 File saveFile=new File(saveToFileStr);
		 File fromFile=new File(fromFileStr);
		 srcImage = ImageIO.read(fromFile);
		 if(width > 0 || hight > 0)
		 {
		 srcImage = resize(srcImage, width, hight);
		 }
		 ImageIO.write(srcImage, imgType, saveFile);

		 }
		 
		     /**
		     * 读取远程文件,转换成流
		     * @param fromFileStr
		     * @param saveToFileStr
		     * @param width
		     * @param hight
		     * @throws Exception
		     */
			 public static InputStream  saveImageAsJpgByStream (InputStream inputStream,String saveToFileStr,int width,int hight)
			 throws Exception {
			 BufferedImage srcImage;
			 String imgType = "JPEG";
			 srcImage = ImageIO.read(inputStream);
			 if(width > 0 || hight > 0)
			 {
			 srcImage = resize(srcImage, width, hight);
			 }
			 return ImageToInputStream(srcImage,imgType);
			 }
		
		
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
		     * 根据远程地址读取图片的流信息
		     * @param fileurl
		     * @return
		   */
	    public static InputStream readFileInfoAsStream(String fileurl){
		    	  InputStream in=null;
		    	  try{		
		    	  	 	//创建连接
		    	        URL url = new URL(fileurl);  
		    	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
		    	        //设置超时
		    	        conn.setConnectTimeout(5 * 1000);  
		    	        //设置请求方式
		    	        conn.setRequestMethod("GET");  
		    	        conn.setDoOutput(true);  
		    	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		 		       //获取响应数据流
		 		        in=conn.getInputStream();
		    	  	}catch(Exception e){
		    	  		e.printStackTrace();
		    	  	}
		    	  	return in;
		    }
		     
}
