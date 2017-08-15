package com.rockstar.o2o.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.message.ShortMessageUtil;


@Controller
@RequestMapping("/shortmessage")
public class ShortMessageController extends BaseController{

	
	
	@RequestMapping("/send")
	public void userlogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String message=obj.getString("message");
			String receiver=obj.getString("receiver").trim();
			
			ShortMessageUtil util=new ShortMessageUtil();
			 String result=util.mt(receiver, message,"", "", "");
			 
			 JSONObject reobj=new JSONObject();
			 obj.accumulate("result", result);
			 
			 if(!result.equalsIgnoreCase("")){
				 outputstr(reobj.toString(), "短信发送成功", true, null);
			 }else{
				 outputstr(reobj.toString(), "短信发送失败", false, null); 
			 }
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
}
