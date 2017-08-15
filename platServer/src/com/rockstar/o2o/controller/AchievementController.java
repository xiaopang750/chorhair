package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.util.RequestUtil;

@Controller
@RequestMapping("/achievement")
public class AchievementController extends BaseController{
	/**
	  * 查询店铺业绩--店面收入--柱状图1（Y）
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("queryincome")
	 public void queryIncome(HttpServletRequest request,HttpServletResponse response,Model model){				 
		 try {
			JSONObject obj=RequestUtil.getPostString(request);	
			//店铺主键
			String pkShop=(String)obj.get("pkShop");			
			//月份
			String month=(String)obj.get("month");

            ArrayList<Object> arraylist=(ArrayList<Object>) achievementService.queryIncome(pkShop,month);
            
            if(arraylist.size()>0){	
            	String data=JSONArray.fromObject(arraylist).toString();
          	   	outputstr(data, "查询店铺本周收入成功", true,null);
            }else{
            	outputstr("", "暂无业绩收入", true,null);
            }
                  
	    } catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
	    }
		output(response, pojo);
	 }
	 /**
	  * 查询店铺业绩--店面收入--柱状图2（Y）
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("queryavgincome")
	 public void queryavgincome(HttpServletRequest request,HttpServletResponse response,Model model){				 
		 try {
			 JSONObject obj=RequestUtil.getPostString(request);	
			 //店铺主键
			 String pkShop=(String)obj.get("pkShop");			
			 //月份
			 String month=(String)obj.get("month");
			 ArrayList<Object> arraylist=(ArrayList<Object>) achievementService.queryavgincome(pkShop,month);
			 if(arraylist.size()>0){	
				 String data=JSONArray.fromObject(arraylist).toString();
          	   	outputstr(data, "查询店铺本周平均收入成功", true,null);
			 }else{
				 outputstr("", "暂无业绩收入", true,null);
             }
		 } catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
		 }
		 output(response, pojo);
	 }
	 
	 /**
	  * 查询店铺业绩--店面人流情况（Y）
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("querypeoplecount")
	 public void queryPeopleCount(HttpServletRequest request,HttpServletResponse response,Model model){				 
		 try {
			 JSONObject obj=RequestUtil.getPostString(request);	
			 //店铺主键
			 String pkShop=(String)obj.get("pkShop");			
			 //月份
			 String month=(String)obj.get("month");
			 ArrayList<Object> arraylist=(ArrayList<Object>) achievementService.queryPeopleCount(pkShop,month);
			 if(arraylist.size()>0){	
				 String data=JSONArray.fromObject(arraylist).toString();
          	   	outputstr(data, "查询店铺人流情况成功", true,null);
			 }else{
				 outputstr("", "暂无业绩收入", true,null);
             }
		 } catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
		 }
		 output(response, pojo);
	 }
	
}
