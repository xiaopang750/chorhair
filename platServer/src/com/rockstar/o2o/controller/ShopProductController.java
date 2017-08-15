package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.ShopProductInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 店铺商品
 * @author xc
 *
 */
@Controller
@RequestMapping("/shopproduct")
public class ShopProductController extends BaseController{

	
	/**
	 * 修改店铺商品信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String pkShopProdcut=obj.getString("pkShopProdcut");
			String pkUser=obj.getString("pkUser");
			ShopProductInfo info=shopproductService.getInfoById(Long.parseLong(pkShopProdcut));
			
			ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
			if(user!=null&&user.getPkShop().equals(info.getPkShop())){
			{
				if(info.getPkShop()!=null&&info.getPkShop().equals(Long.parseLong(pkShop))){
					String price=obj.getString("shopprice");
					info.setShopprice(Double.parseDouble(price));
					info.setPy(CharUtil.chineseToPingyin(info.getProductname()));
					info.setShortpy(CharUtil.getHeadChar(info.getProductname()));
					shopproductService.updateInfo(info);
					outputstr("", "修改商品信息成功", true, null);
				}else{
				outputstr("", "商品和店铺信息不匹配", false, null);	
				}
			}
			}else{
				 outputstr("", "用户不存在或没有权限", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			 outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 查询店铺下的产品
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/findbyshop")
	public void findbyshop(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			if(obj.get("pkShop")==null||obj.get("pkShop").equals("")){
				outputstr("", "请选择店铺", false,null);		
			}else{
			String pkShop=obj.getString("pkShop");
			String content=obj.get("productname")==null?"":obj.getString("productname");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<ShopProductInfo> infos=new ArrayList<ShopProductInfo>();
			ArrayList<ShopProductInfo> totalinfos=new ArrayList<ShopProductInfo>();
			//分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<ShopProductInfo>) shopproductService.QueryByShop(pkShop, content,Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<ShopProductInfo>) shopproductService.QueryByShop(pkShop,content, null,  null); 
			    if(infos.size()>0){
			    	outputstr(JSONArray.fromObject(infos).toString(), "查询店铺商品成功", true, totalinfos.size());
			    }else{
			    	outputstr("", "暂无商品", true,null);	
			    }
		    }else{//全部查询
		    	totalinfos=(ArrayList<ShopProductInfo>) shopproductService.QueryByShop(pkShop, content,null,  null); 
		    	if(totalinfos.size()>0){
		    	   outputstr(JSONArray.fromObject(totalinfos).toString(), "查询店铺商品成功", true, totalinfos.size());
		    	}else{
		    	   outputstr("", "暂无商品", true,null);		 
		    	}
		    }
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
	      output(response, pojo);
	}
	
}
