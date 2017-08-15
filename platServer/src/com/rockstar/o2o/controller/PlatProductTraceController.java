package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.PlatProductTrace;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 平台商品轨迹
 *
 */
@Controller
@RequestMapping("/platproducttrace")
public class PlatProductTraceController extends BaseController{
	
	/**
	 * 查询平台商品修改轨迹信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/gettrace")
	public void gettrace(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//商品id
			String pkProduct=obj.getString("pkProduct");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<PlatProductTrace> infos=new ArrayList<PlatProductTrace>();
			ArrayList<PlatProductTrace> totalinfos=new ArrayList<PlatProductTrace>();
			 //分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<PlatProductTrace>) platProductTraceService.gettrace(pkProduct,Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<PlatProductTrace>) platProductTraceService.gettrace(pkProduct, null,  null); 
			    if(infos.size()>0){
			    	outputstr(JSONArray.fromObject(infos).toString(), "查询平台商品修改轨迹成功", true, totalinfos.size());
			    }else{
			    	outputstr("", "暂无商品修改轨迹", false,null);	
			    }
		    }else{//全部查询
		    	totalinfos=(ArrayList<PlatProductTrace>) platProductTraceService.gettrace(pkProduct,null,  null); 
		    	if(totalinfos.size()>0){
		    		outputstr(JSONArray.fromObject(totalinfos).toString(), "查询平台商品修改轨迹成功", true, totalinfos.size());
		    	}else{
		    		outputstr("", "暂无商品修改轨迹", false,null);		 
		    	}
		    }
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
	      output(response, pojo);
	}
}
