package com.rockstar.o2o.util.file;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.rockstar.o2o.util.PubUtil;


/**
 * 文件处理类
 * @author xc
 * 
 */
public class FileManegeUtil {
    
    private static StorageClient storageClient=null;
    private static TrackerClient trackerClient=null;
    private static TrackerServer trackerServer=null;
    private static final Logger logger = Logger.getLogger(FileManegeUtil.class);
	private static String endPoint =System.getenv("CHORHAIR")==null?PubUtil.getServerURL("localfileurl")
			:(System.getenv("CHORHAIR").equals("test")?PubUtil.getServerURL("testfileurl"):PubUtil.getServerURL("buildfileurl"));

	/**
	 * 获取文件服务器连接
	 * @author xc
	 *
	 */
    private static void getfilesystem(){
		try {
		  String classPath = new File(FileManegeUtil.class.getResource("/").getFile()).getCanonicalPath();		

 		  String configFilePath = classPath + File.separator ;  
 		  String filename = System.getenv("CHORHAIR")==null?"fdfs_client.conf"
 				:(System.getenv("CHORHAIR").equals("test")?"fdfs_client.conf":"fdfs_client_pro.conf");
 		  
 		  configFilePath=configFilePath+filename;
 		  
 		  configFilePath=URLDecoder.decode(configFilePath, "UTF-8");
 		   		 		  
 		  ClientGlobal.init(configFilePath);
 		  trackerClient = new TrackerClient();
 	      trackerServer = trackerClient.getConnection();

 	      StorageServer storageServer = null;
 	      storageClient = new StorageClient(trackerServer, storageServer);
 	      
		 } catch (Exception e) {
			e.printStackTrace();
		 }
    }
	
       
      /**
  	 * 文件删除
  	 * @author xc
  	 *
  	 */
      public static String deletefile(String group_name,String remote_filename){
     		
     	getfilesystem();
  	    try {
 			storageClient.delete_file(group_name, remote_filename);
 		} catch (Exception e) {
 			e.printStackTrace();
 			return "{result:false,message:\"删除文件失败\"}";
 		}
  	       return "{result:true,message:\"删除文件成功\"}";
      }
      /**
       * 根据文件路径读取文件内容
       * @param filePath
       * @return
       */
      public static String readFileInfo(String filePath){
    	  String returnValue="";
    	  try{
    	  	//1.请求部分
    	  	 	//创建连接
    	        URL url = new URL(filePath);  
    	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
    	        //设置超时
    	        conn.setConnectTimeout(5 * 1000);  
    	        //设置请求方式
    	        conn.setRequestMethod("GET");  
    	        conn.setDoOutput(true);  
    	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	        
    	      //2.响应部分
 		       //获取响应数据流
 		        InputStream in=conn.getInputStream();
 		        StringBuffer sbuf=new StringBuffer();
 		        InputStreamReader reader=new InputStreamReader(in,"utf-8");
 		        Reader buf=new BufferedReader(reader);
 		        int ch;
 		        while((ch=buf.read())>-1){
 		      	  sbuf.append((char)ch);
 		        }
 		         buf.close();
 		         returnValue=sbuf.toString();
 		         sbuf.delete(0,sbuf.length());
    	  	}catch(Exception e){
    	  		e.printStackTrace();
    	  	}
    	 
    	  return returnValue;
      }
      
      /**
       * 根据文件路径读取文件的流信息
       * @param filePath
       * @return
       */
      public static InputStream readFileInfoAsStream(String filePath){
    	  InputStream in=null;
    	  try{
    	  	//1.请求部分
    	  	 	//创建连接
    	        URL url = new URL(filePath);  
    	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
    	        //设置超时
    	        conn.setConnectTimeout(5 * 1000);  
    	        //设置请求方式
    	        conn.setRequestMethod("GET");  
    	        conn.setDoOutput(true);  
    	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	        
    	      //2.响应部分
 		       //获取响应数据流
 		        in=conn.getInputStream();
    	  	}catch(Exception e){
    	  		e.printStackTrace();
    	  	}
    	  	return in;
      }
      /**
       *  创建文件
       * @param content 内容  必填
       * @param fileType 文件类型 如：txt,jpg...必填
       * @param belong 内容所属
       * @param storekey 数据库关联主键  可为空
       * @return
       */
      public static String writeFile(String content,String fileType,String belong,String storekey){
    	  String visiturl=null;
    	  try{
    			getfilesystem();
    			
    			byte[] file_buff=content.getBytes("utf-8");
    			NameValuePair[] meta_list = new NameValuePair[3];
    		    meta_list[0] = new NameValuePair("width", "120");
    		    meta_list[1] = new NameValuePair("heigth", "120");
    		    meta_list[2] = new NameValuePair("author", "xc");
    		    String group_name = null;
    	  	    String[] results = storageClient.upload_file(file_buff, fileType, meta_list);
    	  	    group_name = results[0];
    	  	    String remote_filename = results[1];
    	  	      ServerInfo[] servers = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
    	  	    if (servers == null){
    	  	    	logger.debug("获取存储服务失败,错误编码: " + trackerClient.getErrorCode());
    	  	    } else {
    	  	   
    	  	    	for (int k = 0; k < servers.length; k++){
    	  	    			visiturl=endPoint+"/"+group_name+"/"+remote_filename;
    	  	      	}
    	  	        }
    	  			logger.debug((new String(("["+belong+":"+storekey+"]").getBytes("utf-8"),"utf-8"))+visiturl);
    	  	       }
    	            catch (Exception e) {
    	  			e.printStackTrace();
    	  			logger.debug("上传失败");
    	  		   }
    	            if(visiturl!=null){ 
    	            
    	            	return visiturl;
    	            }
    	            else return null;
      }
      
      /**
       * 上传本地文件
       * @param file
       * @param fileType
       * @return
       */
      public static String writeFileByLocal(File file,String fileType){
    	  String visiturl=null;
  		try {
  		getfilesystem();	    
  	    NameValuePair[] meta_list = new NameValuePair[3];
  	    meta_list[0] = new NameValuePair("width", "120");
  	    meta_list[1] = new NameValuePair("heigth", "120");
  	    meta_list[2] = new NameValuePair("author", "xc");
  	    FileInputStream fis = new FileInputStream(file);
  	    byte[] file_buff = null;
  	    if(fis != null){
  	    	int len = fis.available();
  	    	file_buff = new byte[len];
  	    	fis.read(file_buff);
  	    }
  	    String group_name = null;
  	    String[] results = storageClient.upload_file(file_buff, fileType, meta_list);
  	    group_name = results[0];
  	    String remote_filename = results[1];
  	      ServerInfo[] servers = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
  	    if (servers == null){
  	    	logger.debug("获取存储服务失败,错误编码: " + trackerClient.getErrorCode());
  	    } else {
  	   
  	    	for (int k = 0; k < servers.length; k++){
  	    			visiturl=endPoint+"/"+group_name+"/"+remote_filename;
  	      	}
  	        }
  	       }
            catch (Exception e) {
            	e.printStackTrace();
	  			logger.debug("上传失败");
  		   }
            if(visiturl!=null) return visiturl;
            else return null;
      }
      
      /**
       * 以流的形式上传文件
       * @param in
       * @param fileType
       * @return
       */
      public static String writeFileByStream(InputStream in,String fileType){
    	  String visiturl=null;
    		try {
    		getfilesystem();	    
    	 
    	    byte[] file_buff = null;
    	    if(in != null){
    	    	int len = in.available();
    	    	file_buff = new byte[len];
    	    	in.read(file_buff);
    	    }
    	    String group_name = null;
    	    String[] results = storageClient.upload_file(file_buff, fileType, null);
    	    group_name = results[0];
    	    String remote_filename = results[1];
    	      ServerInfo[] servers = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
    	    if (servers == null){
    	    	logger.debug("获取存储服务失败,错误编码: " + trackerClient.getErrorCode());
    	    } else {
    	   
    	    	for (int k = 0; k < servers.length; k++){
    	    			visiturl=endPoint+"/"+group_name+"/"+remote_filename;
    	      	}
    	        }
    	       }
              catch (Exception e) {
              	e.printStackTrace();
  	  			logger.debug("上传失败");
    		   }
              System.out.println(visiturl);
              if(visiturl!=null) return visiturl;
              else return null;
      }
      
     /**
      * 获取文件大小
      * @param filepath本地路径
      * @param fileurl远程路径
      * @return
      */
      public static JSONObject getsize(String filepath,String fileurl){
		  JSONObject obj=new JSONObject();
			try {
		  //本地文件
    	  if(filepath!=null&&!filepath.equals("")){   	
		     File file = new File(filepath);  		     
		     BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));	
		     obj.accumulate("size", String.format("%.1f",file.length()/1024.0));
		     obj.accumulate("width", sourceImg.getWidth());
		     obj.accumulate("height", sourceImg.getHeight());	
    	  }else {
    		    //远程文件	
				URL url = new URL(fileurl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	  			conn.setConnectTimeout(10 * 1000);  
    	        conn.setRequestMethod("GET");  
    	        conn.setDoOutput(true);  
    	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
 		        InputStream in=conn.getInputStream();
 		        
 		        BufferedImage sourceImg = ImageIO.read(in);	
	  			String length=conn.getHeaderField("Content-Length");
	  			obj.accumulate("size", String.format("%.1f",Long.parseLong(length)/1024.0));
	  			obj.accumulate("width", sourceImg.getWidth());
	  			obj.accumulate("height", sourceImg.getHeight());	
    	        }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	return obj;
      }
 }

