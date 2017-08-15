package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.util.RequestUtil;

@Controller
@RequestMapping("shopaddition")
public class ShopAdditionController extends  BaseController{

	
	/**
	 * 根据套餐的附加项目组查询附加项目
	 * @param request
	 * @param response
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/querybycombo")
    public void query(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
		JSONObject obj=RequestUtil.getPostString(request);		
		String pkShopCombo=obj.getString("pkShopCombo");
		String pkShop=obj.getString("pkShop");
		
		ArrayList<Object> additions=(ArrayList<Object>) shopadditionService.QueryBycombo(pkShopCombo);
		if(additions.size()>0){	
			ArrayList<Object> newobjlist=new ArrayList<Object>();
			for(int i=0;i<additions.size();i++){
				HashMap add=(HashMap) additions.get(i);
				JSONObject oneobj=JSONObject.fromObject(add);
				//查询附加项目对应的提成项
				ArrayList<ShopFaireraward> awards=(ArrayList<ShopFaireraward>) shopfairerawardService.queryvalidate(pkShop, oneobj.getString("pkAddition"), "ADDITION");			  
				if(awards.size()>0){
				   oneobj.accumulate("awards", JSONArray.fromObject(awards).toString());
			   }
			   newobjlist.add(oneobj);
			}
			String data=JSONArray.fromObject(newobjlist).toString();

			outputstr(data, "查询附加项目成功", true, null);
		    }else{
			outputstr("", "无附加项目", true, null);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	
}
