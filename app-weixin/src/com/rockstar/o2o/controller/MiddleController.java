package com.rockstar.o2o.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.http.IHttpClientService;
import com.rockstar.o2o.util.PubUtil;
import com.rockstar.o2o.util.RequestUtil;


@Controller
@RequestMapping("middle")
public class MiddleController {
    private static String pathurl=System.getenv("CHORHAIR")==null?PubUtil.getServerURL("localserver")
			:(System.getenv("CHORHAIR").equals("test")?PubUtil.getServerURL("testserver"):PubUtil.getServerURL("buildserver"));
    
	@Resource
	private IHttpClientService httpservice;
	
	@RequestMapping("prehandle")
	public void prehandle(HttpServletRequest request,HttpServletResponse response){
		JSONObject obj=RequestUtil.getPostString(request);
		String data=obj.getString("data");
		String url=obj.getString("url");
		try {
			String returnndata=httpservice.sendPostRequset(pathurl+url, data);
			 response.setContentType("text/html; charset=utf-8");
			 response.getOutputStream().write(returnndata.getBytes("utf-8"));
			 response.addHeader("Access-Control-Allow-Origin", "*");
			 response.getOutputStream().flush();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
