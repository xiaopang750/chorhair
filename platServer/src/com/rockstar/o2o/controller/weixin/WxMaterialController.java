package com.rockstar.o2o.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.WxSourceMaterial;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.WxserviceUtil;
import com.rockstar.o2o.util.file.FileManegeUtil;


@Controller
@RequestMapping("/wxmaterial")
public class WxMaterialController extends BaseController{
	
	
	@RequestMapping("/getlist")
	public void createevent(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		try {
			JSONObject obj=RequestUtil.getOldPostString(request);
			//图片（image）、视频（video）、语音 （voice）、图文（news）
			String type=obj.getString("type");
			//读取配置文件参数
			String url = WxserviceUtil.GetUrl("getmaterial");	
			//获取返回值
			String jsonResult = this.httpservice.sendPostRequset(url, obj.toString()); 
			ArrayList<WxSourceMaterial> addlist=new ArrayList<WxSourceMaterial>();
			ArrayList<WxSourceMaterial> updatelist=new ArrayList<WxSourceMaterial>();
			if(type.equals("news")){
				
			    JSONObject getobj=JSONObject.fromObject(jsonResult);
			    if(getobj.getBoolean("issuccess")){
				 JSONObject data=JSONObject.fromObject(getobj.getString("data")); 
				  Integer item_count=data.getInt("item_count");
				  Integer total_count=data.getInt("total_count");
				  String item=data.getString("item");
	
				  JSONArray arr=JSONArray.fromObject(item);
				  Iterator<?> iter=arr.iterator();
				  while(iter.hasNext()){
					  JSONObject newobj=(JSONObject) iter.next();
					  String mediaid=newobj.getString("media_id");
					  ArrayList<Object> objects=(ArrayList<Object>) basicservice.query(WxSourceMaterial.class, " mediaId = ? ", mediaid);
					  if(objects.size()>0){
						  String jsonurl=FileManegeUtil.writeFile(newobj.toString(), "json", "", "");
						  WxSourceMaterial material=(WxSourceMaterial) objects.get(0);
						  material.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						  material.setDr((short)0);
						  material.setItem(jsonurl);
						  updatelist.add(material); 
					  }else{	
					  String jsonurl=FileManegeUtil.writeFile(newobj.toString(), "json", "", "");
					  WxSourceMaterial material=new WxSourceMaterial();
					  material.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					  material.setDr((short)0);
					  material.setMaterialtype(type);
					  material.setItem(jsonurl);
					  material.setMediaId(mediaid);
					  addlist.add(material);					  
					  }
				  }				  
				    basicservice.batchoperate(addlist, updatelist);
				    
				    obj.clear();
				    obj.accumulate("item_count", item_count);
				    obj.accumulate("total_count", total_count);
				    
				    outputstr(obj.toString(), "", true, total_count);
				    
			  }else{
				outputstr("", "获取失败", false, null);
			  }
			}else {
			    JSONObject getobj=JSONObject.fromObject(jsonResult);
			    if(getobj.getBoolean("issuccess")){
				 JSONObject data=JSONObject.fromObject(getobj.getString("data")); 
				  Integer item_count=data.getInt("item_count");
				  Integer total_count=data.getInt("total_count");
				  String item=data.getString("item");
	
				  JSONArray arr=JSONArray.fromObject(item);
				  Iterator<?> iter=arr.iterator();
				  while(iter.hasNext()){
					  JSONObject newobj=(JSONObject) iter.next();
					  String mediaid=newobj.getString("media_id");
					  ArrayList<Object> objects=(ArrayList<Object>) basicservice.query(WxSourceMaterial.class, " mediaId = ? ", mediaid);
					  if(objects.size()>0){
						  WxSourceMaterial material=(WxSourceMaterial) objects.get(0);
						  material.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						  material.setDr((short)0);
						  material.setItem(newobj.toString());
						  updatelist.add(material); 
					  }else{	
				        WxSourceMaterial material=new WxSourceMaterial();
					    material.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					    material.setDr((short)0);
					    material.setMaterialtype(type);
					    material.setMediaId(mediaid);
					    material.setItem(newobj.toString());
					    addlist.add(material);					  
					  }
				  }				  
				    basicservice.batchoperate(addlist, updatelist);
				    
				    obj.clear();
				    obj.accumulate("item_count", item_count);
				    obj.accumulate("total_count", total_count);
				    
				    outputstr(obj.toString(), "", true, total_count);
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
