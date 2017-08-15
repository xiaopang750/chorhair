package com.rockstar.o2o.util.file;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


/**
 * 图片裁剪处理类
 * @author xc
 * 
 */

public class FileCutUtil {
	 
	    // ===源图片路径名称如：c:\1.jpg
	 
	    private static String srcpath;
	 
	    // ===剪切图片存放路径名称。如：c:\2.jpg
	 
	    private static String subpath;
	 
	    // ===剪切点x坐标
	 
	    private static int x;
	 
	    private static int y;
	 
	    // ===剪切点宽度
	 
	    private static int width;
	 
	    private static int height;
	 
	    public FileCutUtil() {
	 
	    }
	 
	    
	    public static String getSrcpath() {
			return srcpath;
		}


		public static void setSrcpath(String srcpath) {
			FileCutUtil.srcpath = srcpath;
		}


		public static String getSubpath() {
			return subpath;
		}


		public static void setSubpath(String subpath) {
			FileCutUtil.subpath = subpath;
		}


		public static int getX() {
			return x;
		}


		public static void setX(int x) {
			FileCutUtil.x = x;
		}


		public static int getY() {
			return y;
		}


		public static void setY(int y) {
			FileCutUtil.y = y;
		}


		public static int getWidth() {
			return width;
		}


		public static void setWidth(int width) {
			FileCutUtil.width = width;
		}


		public static int getHeight() {
			return height;
		}


		public static void setHeight(int height) {
			FileCutUtil.height = height;
		}


		public FileCutUtil(int x, int y, int width, int height) {
	 
	        this.x = x;
	 
	        this.y = y;
	 
	        this.width = width;
	 
	        this.height = height;
	 
	    }
	 
	    /**
	     * 
	     * 对图片裁剪，并把裁剪完的新图片保存 。
	     * 
	     */
	 
	    public static BufferedImage cut(String srcpath,InputStream stream) throws IOException {
	 
	        FileInputStream is = null;
	 
	        ImageInputStream iis = null;
	 
	        try {
	 
	            // 读取图片文件
	            if(srcpath!=null&&!srcpath.equals("")){
	            	 is = new FileInputStream(srcpath);
	            }else{
	            	 is=(FileInputStream) stream;
	            }
	           
	 
	            /**
	             * 
	             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
	             * 
	             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
	             * 
	             * (例如 "jpeg" 或 "tiff")等 。
	             * 
	             */
	 
	            Iterator<ImageReader> it = ImageIO
	                    .getImageReadersByFormatName(" jpg ");
	 
	            ImageReader reader = it.next();
	 
	            // 获取图片流
	 
	            iis = ImageIO.createImageInputStream(is);
	 
	            /**
	             * 
	             * <p>
	             * iis:读取源。true:只向前搜索
	             * </p>
	             * .将它标记为 ‘只向前搜索’。
	             * 
	             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
	             * 
	             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
	             * 
	             */
	 
	            reader.setInput(iis, true);
	 
	            /**
	             * 
	             * <p>
	             * 描述如何对流进行解码的类
	             * <p>
	             * .用于指定如何在输入时从 Java Image I/O
	             * 
	             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
	             * 
	             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
	             * 
	             * ImageReadParam 的实例。
	             * 
	             */
	 
	            ImageReadParam param = reader.getDefaultReadParam();
	 
	            /**
	             * 
	             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
	             * 
	             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
	             * 
	             */
	 
	            Rectangle rect = new Rectangle(x, y, width, height);
	 
	            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
	 
	            param.setSourceRegion(rect);
	 
	            /**
	             * 
	             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
	             * 
	             * 它作为一个完整的 BufferedImage 返回。
	             * 
	             */
	 
	            BufferedImage bi = reader.read(0, param);
	 
	            return bi;
	 
	        } finally {
	 
	            if (is != null)
	 
	                is.close();
	 
	            if (iis != null)
	 
	                iis.close();
	 
	        }
	 
	    }
	
	 
	    
	    /**
	     * 读取本地文件,将裁剪后的图片保存到本地
	     * @param fromFileStr
	     * @param saveToFileStr
	     * @param width
	     * @param hight
	     * @throws Exception
	     */
		 public  static void saveImageAsJpgByLocal (String fromFileStr,String saveToFileStr)
		 throws Exception {
		 BufferedImage srcImage;
		 String imgType = "JPG";
		 if (fromFileStr.toLowerCase().endsWith(".png")) {
		 imgType = "PNG";
		 }
		 File saveFile=new File(saveToFileStr);
		 srcImage = cut(fromFileStr,null);
		 ImageIO.write(srcImage, imgType, saveFile);
		 }
		 
	    /**
	     * 读取远程文件,将裁剪后的图片转换成流
	     * @param fromFileStr
	     * @param saveToFileStr
	     * @param width
	     * @param hight
	     * @throws Exception
	     */
		 public static  void saveImageAsJpgByStream (InputStream inputStream,String saveToFileStr,int width,int hight)
		 throws Exception {
		 BufferedImage srcImage = null;
		 String imgType = "JPG";
		 if(width > 0 || hight > 0)
		 {
		 srcImage = cut(null,inputStream);
		 }
		 ImageToInputStream(srcImage,imgType);
		 }
	
	
	/**
	* 将输出图片的转换成流信息
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
