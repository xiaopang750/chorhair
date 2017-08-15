package com.rockstar.o2o.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.lucene.IndexFile;
import com.rockstar.o2o.lucene.IndexSearch;
import com.rockstar.o2o.lucene.ReturnPojo;
import com.rockstar.o2o.util.MailUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * lucene服务，查询，生成和删除索引
 * @author xc
 *
 */
@Controller
@RequestMapping("lucene")
public class LuceneController {

	protected ReturnPojo pojo=new ReturnPojo();
	
	/**
	 * 创建索引
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/createindex")
	public void createindex(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			JSONObject content=(JSONObject)receiveobj.get("content");
			JSONObject data=receiveobj.get("data")==null?null:(JSONObject)receiveobj.get("data");
			String result=IndexFile.init(content.toString(), data==null?null:data.toString());
			if(result.equals("success")){
				outputstr("", "创建索引成功", true);
			}else{
				outputstr("", "创建索引失败", false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		
		output(response, pojo);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/deleteindex")
	public void deleteindex(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			String id=(String)receiveobj.get("id");
			String result=IndexFile.delete(id);
			if(result.equals("success")){
				outputstr("", "删除索引成功", true);
			}else{
				outputstr("", "删除索引失败", false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		
		output(response, pojo);
	}
	
	/**
	 * 查询索引
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/searchindex")
	public void searchindex(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			String keyword=(String)receiveobj.get("keyword");
			String combotype=(String)receiveobj.get("combotype");
			String contenttype=(String)receiveobj.get("contenttype");
			String fairtype=(String)receiveobj.get("fairtype");
			String pkShop=receiveobj.get("pkShop")==null?null:receiveobj.getString("pkShop");
			String curpage=(String)receiveobj.get("curpage");
			String pageSize=(String)receiveobj.get("pagesize");
			String collectid=receiveobj.get("openid")==null?null:receiveobj.getString("openid");
			
			pojo=IndexSearch.search(collectid,keyword, combotype, contenttype, fairtype, pkShop, Integer.parseInt(curpage), Integer.parseInt(pageSize));
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		
		output(response, pojo);
	}
	
	
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void output(HttpServletResponse response, ReturnPojo pojo) {
		try {
			String str=JSONObject.fromObject(pojo).toString();
			response.setContentType("text/html; charset=utf-8");
			response.addHeader("Access-Control-Allow-Origin", "*");
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
	public void dealexception(final Exception e) {
        e.printStackTrace();
	    //异步消息处理
	    new Thread(){
	    	public void run(){
	    		sendmail( e);
	    	}
	    }.start();
	}
	
	public void sendmail(Exception e){
		StackTraceElement[] elements=e.getStackTrace();
        StringBuffer buffer=new StringBuffer();
        buffer.append(e.getLocalizedMessage());
    	buffer.append("\n");
        for(StackTraceElement element:elements){
        	
        	buffer.append("classname:"+element.getClassName()+",methodname:"+element.getMethodName()+",linenumber:"+element.getLineNumber());
        	buffer.append("\n");
        }
       MailUtil.sendexception("LECENE项目:"+"虫二管理系统出现bug,请查看,并及时修改", buffer.toString());
	}
	
}
