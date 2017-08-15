package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.FairType;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 店铺基本费用操作
 * @author hp
 *
 */
@Controller
@RequestMapping("/shopprice")
public class ShopPriceController extends BaseController{

	/**
	 * 保存店铺基本价格
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/save")
		public void save(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String servicerank=obj.getString("servicerank");
		String price=obj.getString("price");
		String note=obj.get("note")==null?"":obj.getString("note");
		
		ShopPrice shopprice=new ShopPrice();
		shopprice.setServicerank(servicerank);
		shopprice.setPrice(Double.parseDouble(price));
		shopprice.setNote(note);
		shopprice.setDr((short)0);
		shopprice.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		
		
		 shopprice=shoppriceService.save(shopprice);
		
		 outputstr("", "保存成功", true, null);
		
		 } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
		    outputexceptionstr();
		 }
		   output(response, pojo);
	    }
	
	
	/**
	 * 修改店铺基本价格
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {		
	try {
	JSONObject obj=RequestUtil.getPostString(request);
	String pkPrice=obj.getString("pkPrice");
	String servicerank=obj.getString("servicerank");
	String price=obj.getString("price");
	String note=obj.get("note")==null?"":obj.getString("note");
	
	ShopPrice shopprice=new ShopPrice();
	shopprice.setPkPrice(Long.parseLong(pkPrice));
	shopprice.setServicerank(servicerank);
	shopprice.setPrice(Double.parseDouble(price));
	shopprice.setNote(note);
	shopprice.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	
	
	int result=shoppriceService.updatePrice(shopprice);
	
	if(result==0){
		outputstr("", "修改价格成功", true, null);
	  }else{
		outputstr("", "修改价格失败", false, null);
	   }
	
	} catch (Exception e) {
		// TODO: handle exception
		dealexception(e);
		outputexceptionstr();
      	}
	output(response, pojo);
    }
	
	/**
	 * 根据店铺查询基本价格列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querybyshop")
	public void querybyshop(HttpServletRequest request,HttpServletResponse response,Model model) {		
	try {
	JSONObject obj=RequestUtil.getPostString(request);
	String pkShop=obj.getString("pkShop");
	String pkShopCombo=obj.getString("pkShopCombo");
	
	ShopCombo combo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));

	if(combo.getPkServicegroup()==null||combo.getPkServicegroup().equals("")){		
		outputstr("", "没有维护对应的价格组", false, null);		
	 }else{
	 ArrayList<ShopPrice> pricelists=(ArrayList<ShopPrice>) shoppriceService.QueryBygroup(combo.getPkServicegroup().toString());
    
	 //查询理发的价格
	 String fairtype=FairType.TYPE_FIVE[0];	
	 ArrayList<ShopPrice> fairpricelists=(ArrayList<ShopPrice>) shoppriceService.QueryByShop(pkShop,fairtype);
	
	 if(pricelists.size()>0){
		ArrayList<Object> list=new ArrayList<Object> ();
		for(int i=0;i<pricelists.size();i++){
			ShopPrice price=pricelists.get(i);
			JSONObject returnobj=JSONObject.fromObject(price);
			returnobj.accumulate("fairprice", fairpricelists.size()==pricelists.size()?fairpricelists.get(i).getPrice():"0");
			list.add(returnobj);
		}
    	outputstr(JSONArray.fromObject(list).toString(), "查询成功", true, null);
       }else{
       	outputstr("", "暂无价格信息", true, null);
       }
	}
	} catch (Exception e) {
		// TODO: handle exception
		dealexception(e);
		outputexceptionstr();
      	}
	   output(response, pojo);
    }
	
	
	/**
	 * 查询服务的提成项
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryaward")
	public void queryaward(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String pkPrice=obj.getString("pkPrice");
			
		   ArrayList<ShopFaireraward> awards=(ArrayList<ShopFaireraward>) shopfairerawardService.querybyshopandtype(pkShop, pkPrice, "SERVICE");
		   
		   
		   if(awards.size()>0){
			   String data=JSONArray.fromObject(awards).toString();
			   outputstr(data, "查询服务提成项成功", true, null);
		   }else{
			   outputstr("", "暂无服务提成项", true, null);  
		   }
		   
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		  output(response, pojo);
	}
	/**
	 * 根据店铺、理发类型查询基本价格列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querybytype")
	public void querybytype(HttpServletRequest request,HttpServletResponse response,Model model) {		
	try {
	JSONObject obj=RequestUtil.getPostString(request);
	String pkShop=obj.getString("pkShop");
	String fairtype=FairType.TYPE_FIVE[0];
	
	ArrayList<ShopPrice> pricelists=(ArrayList<ShopPrice>) shoppriceService.QueryByShop(pkShop,fairtype);
    if(pricelists.size()>0){
    	outputstr(JSONArray.fromObject(pricelists).toString(), "查询成功", true, null);
       }else{
       	outputstr("", "暂无价格信息", true, null);
       }
	
	} catch (Exception e) {
		// TODO: handle exception
		dealexception(e);
		outputexceptionstr();
      	}
	output(response, pojo);
    }
	
	
	/**
	 * 查询服务的提成项
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryvalidate")
	public void queryvalidate(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String pkPrice=obj.getString("pkPrice");
			
		   ArrayList<ShopFaireraward> awards=(ArrayList<ShopFaireraward>) shopfairerawardService.queryvalidate(pkShop, pkPrice, "SERVICE");
		   
		   
		   if(awards.size()>0){
			   String data=JSONArray.fromObject(awards).toString();
			   outputstr(data, "查询服务提成项成功", true, null);
		   }else{
			   outputstr("", "暂无服务提成项", true, null);  
		   }
		   
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		  output(response, pojo);
	}
	
}
