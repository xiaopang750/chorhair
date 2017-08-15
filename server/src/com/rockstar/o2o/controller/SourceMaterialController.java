package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.SourceMaterial;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.jsonvalidator.Validator;

@Controller
@RequestMapping("sourcematerial")
public class SourceMaterialController extends BaseController{

	/**
	 * 新增素材
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=JSONObject.fromObject(request);
			String pkShop=receiveobj.getString("pkShop");
			String materialtype=receiveobj.getString("materialtype");
			String pkUser=receiveobj.getString("pkUser");
			
			if(materialtype.equals("news")){
				String articles=receiveobj.getString("articles");
		    	Validator validator=new Validator(Validator.class.getResourceAsStream("articles"));
		    	int i=validator.validate(articles);		    	
		    	if(i>0){
		    		outputstr("", "传入参数不正确", false, null);
		    	}else{
		    		SourceMaterial material=new SourceMaterial();
		    		material.setMaterialtype(materialtype);
		    		material.setPkShop(Long.parseLong(pkShop));
		    		material.setMaterialcontent(articles);
		    		material.setCreatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    		material.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    		material.setDr((short)0);
		    		material.setCreatorid(Long.parseLong(pkUser));
		    		basicservice.save(material);
		    		outputstr("", "新增图文素材成功", true, null);
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
	 * 修改素材
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=JSONObject.fromObject(request);
			String materialtype=receiveobj.getString("materialtype");
			String pkSourceMaterial=receiveobj.getString("pkSourceMaterial");
			SourceMaterial material=(SourceMaterial) basicservice.querybyid(SourceMaterial.class, Long.parseLong(pkSourceMaterial));
			if(material!=null){
			if(materialtype.equals("news")){
				String articles=receiveobj.getString("articles");
		    	Validator validator=new Validator(Validator.class.getResourceAsStream("articles"));
		    	int i=validator.validate(articles);		    	
		    	if(i>0){
		    		outputstr("", "传入参数不正确", false, null);
		    	}else{
		    		material.setMaterialcontent(articles);
		    		material.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    		material.setDr((short)0);
		    		basicservice.update(material);
		    		outputstr("", "修改图文素材成功", true, null);
		    	}
			  }
			}else{
				outputstr("", "当前素材不存在", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 查询列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querylist")
	public void querylist(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=JSONObject.fromObject(request);
			String pkShop=receiveobj.getString("pkShop");
			String materialtype=receiveobj.getString("materialtype");
			String curpage=receiveobj.getString("curpage");
			String pagesize=receiveobj.getString("pagesize");
			
			ArrayList<Object> objects=(ArrayList<Object>) basicservice.pageQuery(SourceMaterial.class, " pkShop = ? and materialtype = ?  ",Integer.parseInt(curpage),Integer.parseInt(pagesize),Long.parseLong(pkShop),materialtype);
		
			if(objects.size()>0){
				String datas=JSONArray.fromObject(objects).toString();
				outputstr(datas, "查询素材成功", true, null);
			}else{
				outputstr("", "暂无素材", true, null);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 根据主键列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querybyid")
	public void querybyid(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject receiveobj=JSONObject.fromObject(request);
			String pkSourceMaterial=receiveobj.getString("pkSourceMaterial");
			
			SourceMaterial material=(SourceMaterial) basicservice.querybyid(SourceMaterial.class, Long.parseLong(pkSourceMaterial));
			
			if(material!=null){
				outputstr(JSONObject.fromObject(material).toString(), "素材信息不存在", false, null);
			}else{
				outputstr("", "素材信息不存在", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
}
