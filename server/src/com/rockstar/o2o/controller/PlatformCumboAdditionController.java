package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.PlatformCumboAddition;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 套餐附加项目维护
 * @author xc
 *
 */
@Controller
@RequestMapping("addition")
public class PlatformCumboAdditionController extends BaseController{
	/**
	 * 根据套餐查询附加项目
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querybycombo")
    public void query(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
		JSONObject obj=RequestUtil.getPostString(request);		
		String pkShopCombo=obj.getString("pkShopCombo");
		ShopCombo combo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));
		String pkCombo=combo.getPkCombo().toString();
		
		ArrayList<PlatformCumboAddition> additions=(ArrayList<PlatformCumboAddition>) platformadditionService.QueryByPkCombo(pkCombo);	    
		if(additions.size()>0){
			String data=JSONArray.fromObject(additions).toString();

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
