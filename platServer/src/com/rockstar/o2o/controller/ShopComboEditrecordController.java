package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.ShopComboEditrecord;
import com.rockstar.o2o.util.RequestUtil;


@Controller
@RequestMapping("editrecord")
public class ShopComboEditrecordController extends BaseController{

	
	@RequestMapping("/findbycombo")
	public void findbyshop(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//String pkShop=obj.getString("pkShop");
			String pkShopCombo=obj.getString("pkShopCombo");
			
			ArrayList<ShopComboEditrecord> records=(ArrayList<ShopComboEditrecord>) comboeditrecordService.QueryByCombo(pkShopCombo);
			if(records.size()>0){
				String data=JSONArray.fromObject(records).toString();
				outputstr(data, "查询修改记录成功", true, null);
			}else{
				outputstr("", "暂无套餐修改记录", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
}
