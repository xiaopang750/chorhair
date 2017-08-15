package com.rockstar.o2o.weixin.controller;

import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.rockstar.o2o.weixin.conf.SysConf;
import com.rockstar.o2o.weixin.handle.AccessTokenHandle;
import com.rockstar.o2o.weixin.http.service.IHttpClientService;
import com.rockstar.o2o.weixin.pojo.ReturnPojo;
import com.rockstar.o2o.weixin.service.BasicService;


@Controller
public class BaseController {
	
	protected ReturnPojo pojo=new ReturnPojo();
	
	private static  String env = System.getenv("CHORHAIR");
	 
	public static  ResourceBundle bundle = ResourceBundle.getBundle("wx");
	public static  String app_id =(env==null||env.equals("test"))?bundle.getString("test_app_id") :
		bundle.getString("build_app_id");
	public static  String app_secret = (env==null||env.equals("test"))?bundle.getString("test_app_secret") :
		bundle.getString("build_app_secret"); 
	public static  String account_id = (env==null||env.equals("test"))?bundle.getString("test_account_id") :
		bundle.getString("build_account_id") ; 
	public static  String token = (env==null||env.equals("test"))?bundle.getString("test_token") :
		bundle.getString("build_token");  
	@Resource
	protected AccessTokenHandle accesstokenhandle;
	
	@Resource
	protected BasicService basicservice;
	
	@Resource
	protected IHttpClientService httpservice;
	
	@Resource
	protected SysConf wxsysConf;
	
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void output(HttpServletResponse response, ReturnPojo pojo) {
		try {
			String str=JSONObject.fromObject(pojo).toString();
			response.setContentType("text/html; charset=utf-8");
			// response.setCharacterEncoding("UTF-8");
		    response.getOutputStream().write(str.getBytes("UTF-8") );
		    response.getOutputStream().flush();	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成pojo
	 * @param response
	 * @param str
	 */
	public ReturnPojo outputstr(String data,String msg,boolean issuccess) {
           pojo.setData(data);
           pojo.setMsg(msg);
           pojo.setIssuccess(issuccess);
           return pojo;
	}
	
	
	/**
	 * 生成pojo
	 * @param response
	 * @param str
	 */
	public ReturnPojo outputexceptionstr() {
           pojo.setData("");
           pojo.setMsg("传入参数有误");
           pojo.setIssuccess(false);
           return pojo;
	}
	
    /**
     * 异常捕获
     * @param e
     */
	public void dealexception(Exception e) {
        e.printStackTrace();
//        StackTraceElement[] elements=e.getStackTrace();
//        StringBuffer buffer=new StringBuffer();
//        buffer.append(e.getLocalizedMessage());
//    	buffer.append("\n");
//        for(StackTraceElement element:elements){
//        	
//        	buffer.append("classname:"+element.getClassName()+",methodname:"+element.getMethodName()+",linenumber:"+element.getLineNumber());
//        	buffer.append("\n");
//        }
//       MailUtil.sendexception("虫二管理系统出现bug,请查看,并及时修改", buffer.toString());
	}
	
}
