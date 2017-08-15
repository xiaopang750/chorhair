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
			Integer curpage=obj.get("curpage")==null?null:Integer.parseInt(obj.getString("curpage"));
			Integer pagesize=obj.get("pagesize")==null?null:Integer.parseInt(obj.getString("pagesize"));
			
			ArrayList<Object> records=(ArrayList<Object>) basicservice.pageQuery(ShopComboEditrecord.class, " pkShopCombo = ? order by ts desc",pagesize,curpage, Long.parseLong(pkShopCombo));
			
			if(records.size()>0){
				String data=JSONArray.fromObject(records).toString();
				if(curpage!=null&&pagesize!=null){
					ArrayList<Object> totalrecords=(ArrayList<Object>) basicservice.pageQuery(ShopComboEditrecord.class, " pkShopCombo = ? ",null,null, Long.parseLong(pkShopCombo));					
					outputstr(data, "查询修改记录成功", true, totalrecords.size());
				}else{
					outputstr(data, "查询修改记录成功", true, null);
				}
				
			}else{
				outputstr("", "暂无套餐修改记录", true, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
}
