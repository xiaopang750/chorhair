package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.PlatformArea;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 区域操作
 * @author XC
 *
 */
@Controller
@RequestMapping("area")
public class PlatformAreaController extends BaseController{

	
   /**
   * 查询区域,根据级别和父级区域查询
   * @param request
   * @param response
   * @param model
   */
	@RequestMapping("/queryarea")
    public void queryarea(HttpServletRequest request,HttpServletResponse response,Model model){
	try {
	     
		JSONObject obj=RequestUtil.getPostString(request);
		
		String pkFather=obj.get("pkFather")==null?"":obj.getString("pkFather");
		String belonglevel=obj.get("belonglevel")==null?"":obj.getString("belonglevel");
		
		if(!belonglevel.equals("")){
			ArrayList<PlatformArea> arealist=(ArrayList<PlatformArea>) platformareaService.QueryByLevel(belonglevel);
		    if(arealist.size()>0){
		    	String data=JSONArray.fromObject(arealist).toString();
		    	outputstr(data, "查询区域成功", true, null);
		    }
		}else if(!pkFather.equals("")){
			ArrayList<PlatformArea> arealist=(ArrayList<PlatformArea>) platformareaService.QueryByFather(pkFather);
		    if(arealist.size()>0){
		    	String data=JSONArray.fromObject(arealist).toString();
		    	outputstr(data, "查询区域成功", true, null);
		    }
		
		}		
	  } catch (Exception e) {
		// TODO: handle exception
		  dealexception(e);
		  outputexceptionstr();
    	}	
	  
	     output(response, pojo);
      }
}
