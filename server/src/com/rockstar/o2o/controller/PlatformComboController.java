package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.pojo.ComboAward;
import com.rockstar.o2o.pojo.PlatformCombo;
import com.rockstar.o2o.pojo.PlatformComboProduct;
import com.rockstar.o2o.pojo.PlatformComboService;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.jsonvalidator.Validator;


@Controller
@RequestMapping("/platcombo")
public class PlatformComboController extends BaseController{

	
	  /**
     * 查询平台套餐
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("/query")
    public void query(HttpServletRequest request,HttpServletResponse response,Model model){
	try {
	 ArrayList<PlatformCombo> combos = (ArrayList<PlatformCombo>) platformcomboService.QueryAll();	
	 if(combos.size()>0){
		 String data=JSONArray.fromObject(combos).toString();
		 
		 outputstr(data, "查询成功", true, null);
	 }else{

		 outputstr("", "暂无平台套餐信息", true, null);
	    }	
	  } catch (Exception e) {
		// TODO: handle exception
		  dealexception(e);
		  outputexceptionstr();
      	}
	  output(response, pojo);
    }
	
	 /**
     * 查询平台服务和产品
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("/queryother")
    public void queryproductandservice(HttpServletRequest request,HttpServletResponse response,Model model){
	try {
	 JSONObject obj=RequestUtil.getPostString(request);	 
	 String  pkCombo=obj.getString("pkCombo");
	 ArrayList<PlatformComboProduct> products = (ArrayList<PlatformComboProduct>) platcomboprodcutService.QueryByCombo(pkCombo);
	 ArrayList<PlatformComboService> services = (ArrayList<PlatformComboService>) platcomboserviceService.QueryByCombo(pkCombo);
	 
	 JSONObject returnobj=new JSONObject();
	 if(products.size()>0){
		 String productdata=JSONArray.fromObject(products).toString();
		 returnobj.accumulate("products", productdata); 

	 }	 
	 if(services.size()>0){
		 String servicedata=JSONArray.fromObject(services).toString();
		 returnobj.accumulate("services", servicedata); 
	 }
	 
	   if(returnobj.size()>0){
			outputstr(returnobj.toString(), "查询产品和服务成功", true, null);   
	    }else{
		   outputstr("", "无产品和服务信息", false, null);   
	    }
	  } catch (Exception e) {
		// TODO: handle exception
		  dealexception(e);
		  outputexceptionstr();
      	}
	  output(response, pojo);
    }
	
	/**
	 * 查询
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryaward")
    public void queryaward(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCombo=obj.getString("pkCombo");
			ArrayList<Object> awards=(ArrayList<Object>)comboawardService.queryselect(pkCombo);
		    if(awards.size()>0){
		    	String data=JSONArray.fromObject(awards).toString();
		    	outputstr(data, "查询可用抵用券", true, null);
		    }else{
		    	outputstr("", "暂无可用抵用券", true, null);
		    }
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 保存抵用券
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/saveaward")
    public void saveaward(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCombo=obj.getString("pkCombo");//套餐主键
			String awards=obj.getString("awards");//抵用券
			
			JSONArray arr=JSONArray.fromObject(awards);
			Iterator<?> iter=arr.iterator();
			ArrayList<ComboAward> addlimitlists=new ArrayList<ComboAward>();
			ArrayList<ComboAward> updatelimitlists=new ArrayList<ComboAward>();
			while(iter.hasNext()){
				JSONObject oneobj=(JSONObject) iter.next();
				String dr=oneobj.get("dr")==null?"0":oneobj.getString("dr");
				String pkComboAward=oneobj.get("pkComboAward")==null?"":oneobj.getString("pkComboAward");
								
				if(pkComboAward.equals("")){
					String pkCustomeraward=oneobj.getString("pkCustomeraward");
					String awardname=oneobj.getString("awardname");
					String awardvalue=oneobj.getString("awardvalue");
					ComboAward award=new ComboAward();
					award.setCombofrom("PLAT");
					award.setAwardname(awardname);
					award.setAwardvalue(awardvalue);
					award.setPkCombo(Long.parseLong(pkCombo));
					award.setPkCustomeraward(Long.parseLong(pkCustomeraward));
					award.setDr((short)0);
					award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					
					addlimitlists.add(award);
				}else{
					ComboAward award=comboawardService.getAwardById(Long.parseLong(pkComboAward));					
					award.setDr(Short.parseShort(dr));
					award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					updatelimitlists.add(award);
				}
				
			}
			
			if(addlimitlists.size()>0){
				comboawardService.savelist(addlimitlists);
			} 
			
			if(updatelimitlists.size()>0){
				comboawardService.updatelist(updatelimitlists);
			}
			
			
			outputstr("", "维护可发放抵用券成功", true, null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);		
		
	}
	
	

	/**
	 * 新增套餐
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/add")
    public void add(HttpServletRequest request,HttpServletResponse response,Model model){
		
		 PlatformCombo combo=new PlatformCombo();
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String combotype=obj.getString("combotype");
			String fairtype=obj.getString("fairtype");
			String combocode=obj.getString("combocode");
			String comboname=obj.getString("comboname");
			String fitgroup=obj.getString("fitgroup");
			String combomoney=obj.getString("combomoney");
			String combonote=(String)obj.get("combonote");
		    String combocount=null;
			
			if(combotype.equals("2")){
				 combocount="1";
			}else if(combotype.equals("3")){
				 combocount=obj.getString("combocount");
			}
			
            combo.setCombocode(combocode);
            combo.setComboname(comboname);
			combo.setCombomoney(Double.parseDouble(combomoney));
			combo.setCombocount(Integer.parseInt(combocount));
			combo.setfairtype(fairtype);
			combo.setCombotype(combotype);
			combo.setFitgroup(fitgroup);		
			combo.setCombospy(CharUtil.getHeadChar(comboname));
			combo.setCombopy(CharUtil.chineseToPingyin(comboname));
			combo.setCombonote(combonote);
			combo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			combo.setDr((short)0);
			
			
			combo=platformcomboService.save(combo);
			outputstr(JSONObject.fromObject(combo).toString(), "新增套餐成功", true, null);
	    	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(combo!=null&&combo.getPkCombo()!=null){
				platformcomboService.deleteComboById(combo.getPkCombo());
			}
		    outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	@RequestMapping("/setshop")
    public void setshop(HttpServletRequest request,HttpServletResponse response,Model model){
	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCombo=obj.getString("pkCombo");			
			String pkShop=(String)obj.get("pkShop");
			
	      PlatformCombo combo=platformcomboService.getComboById(Long.parseLong(pkCombo));

		ShopCombo newcombo=new ShopCombo();
		newcombo.setCombocode(combo.getCombocode());
		newcombo.setComboname(combo.getComboname());
		newcombo.setStandardmoney(combo.getCombomoney());
		newcombo.setShopmoney(combo.getCombomoney());
		newcombo.setCurrentmoney(combo.getCombomoney());
		newcombo.setCombocount(combo.getCombocount());
		newcombo.setFairtype(combo.getfairtype());
		newcombo.setCombotype(combo.getCombotype());
		newcombo.setFitgroup(combo.getFitgroup());		
		newcombo.setCombospy(combo.getCombospy());
		newcombo.setCombopy(combo.getCombopy());
		newcombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		newcombo.setDr((short)0);
		newcombo.setPkShop(Long.parseLong(pkShop));
		newcombo.setPkCombo(combo.getPkCombo());			
	
	  //批量保存店铺套餐
	  shopcomboService.save(newcombo);
	  outputstr("", "分配店铺成功", true, null);
	
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	outputexceptionstr();
}
		output(response, pojo);
	}
	
}
