package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.ContentHottype;
import com.rockstar.o2o.pojo.ContentManage;
import com.rockstar.o2o.pojo.ContentPhoto;
import com.rockstar.o2o.pojo.ContentTag;
import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.pojo.FairerSkill;
import com.rockstar.o2o.pojo.PlatformCombo;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.ReturnPojo;
import com.rockstar.o2o.util.file.FileManegeUtil;

@Controller
@RequestMapping("content")
public class ContentManageController extends BaseController{

	static  ResourceBundle bundle = ResourceBundle.getBundle("luceneurl");
	static  String url = System.getenv("CHORHAIR")==null?bundle.getString("local.lucene"):
			(System.getenv("CHORHAIR").equals("test")?bundle.getString("test.lucene"):bundle.getString("build.lucene")); 
	
	
	/**
	 * 保存内容信息(Y)
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
	public void saveinfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String contenttopic=obj.getString("contenttopic");
			String contenttype=obj.get("contenttype")==null?"":obj.getString("contenttype");
			String pkType=obj.get("pkType")==null?"":obj.getString("pkType");
			String pkShop=obj.get("pkShop")==null?null:obj.getString("pkShop");
			String contentbody=obj.getString("contentbody");
			String contenturl=obj.getString("contenturl");
			String photolist=obj.get("photolist")==null?"":obj.getString("photolist");
			String taglist=obj.get("taglist")==null?"":obj.getString("taglist");;
			
			ContentManage content=new ContentManage();
			//内容描述保存到文件服务器
			String url=FileManegeUtil.writeFile(contentbody, "json", "", "");
			if(url!=null){
				content.setContentbody(url);
			}
			content.setContenttopic(contenttopic);
			content.setContenttype(contenttype);
			content.setContenturl(contenturl);
			content.setPkType(pkType.equals("")?null:Long.parseLong(pkType));
			content.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			content.setDr((short)0);
			content.setContentstatus("002");
			if(pkShop!=null&&!pkShop.equals("")){
			 content.setPkShop(Long.parseLong(pkShop));	
			}
			
			content=contentmanagerService.save(content);
			
			ArrayList<ContentPhoto> photoslist=new ArrayList<ContentPhoto>();
			ArrayList<ContentTag> tagslist=new ArrayList<ContentTag>();
			ArrayList<UserFile> filelist=new ArrayList<UserFile>();
			//保存图片信息
			if(!photolist.equals("")){				
				JSONArray arr=JSONArray.fromObject(photolist);
				Iterator<?> iter=arr.iterator();
				while(iter.hasNext()){
					JSONObject ontobj=(JSONObject) iter.next();
					String photourl=ontobj.getString("url");
					String photoshorturl=ontobj.getString("shorturl");
					String shortwidth=ontobj.getString("shortwidth");
					String shortheight=ontobj.getString("shortheight");
					String width=ontobj.getString("width");
					String height=ontobj.getString("height");
					String isfirst=ontobj.get("isfirst")==null?"":ontobj.getString("isfirst");		
					ContentPhoto newphoto=new ContentPhoto();
					newphoto.setPkContent(content.getPkContent());
					if(!isfirst.equals("")){
					  content.setFirstpage(photoshorturl);
					  content.setWidth(Integer.parseInt(shortwidth));
					  content.setHeight(Integer.parseInt(shortheight));
					  contentmanagerService.updatecontent(content);
					}
					newphoto.setPhotourl(photourl);
					newphoto.setPhotoshorturl(photoshorturl);
					newphoto.setWidth(Integer.parseInt(width));
					newphoto.setHeight(Integer.parseInt(height));
					newphoto.setShortheigth(Integer.parseInt(shortheight));
					newphoto.setShortwidth(Integer.parseInt(shortwidth));
					newphoto.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					newphoto.setDr((short)0);
					
					photoslist.add(newphoto);
					
					//保存附件上传记录
					UserFile file=new UserFile();
					file.setBemodel("平台上传内容管理图片");
					file.setFilepath(photourl);
					file.setThumbnail(photoshorturl);
					//file.setFilesize(FileManegeUtil.getsize("", photourl).getString("size"));
					//file.setPkUser(info.getPkFairer());
					file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					file.setDr((short)0);
					filelist.add(file);
				}
			}
			//保存标签信息
			if(!taglist.equals("")){				
				JSONArray arr=JSONArray.fromObject(taglist);
				Iterator<?> iter=arr.iterator();			
				while(iter.hasNext()){
					JSONObject ontobj=(JSONObject) iter.next();
					String tagname=ontobj.getString("tagname");
					ContentTag newtag=new ContentTag();
					newtag.setPkContent(content.getPkContent());
					newtag.setTagname(tagname);
					newtag.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					newtag.setDr((short)0);
					tagslist.add(newtag);
				}
			}
			//保存图片信息
			if(photoslist.size()>0){
				contentphotoService.savelist(photoslist);
			}
			//保存文件记录
			if(filelist.size()>0){
				userfileService.batchsave(filelist);
			}
			//保存标签信息
		    if(tagslist.size()>0){
		    	contenttagService.savelist(tagslist);
			}
		    outputstr("", "保存内容成功", true,null);
		} catch (Exception e) {
			  dealexception(e);
			  outputexceptionstr();
		}
		output(response, pojo);
	}

	
	 /**
	 * 修改内容信息（Y）
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String contenttopic=obj.getString("contenttopic");
			String pkContent=obj.getString("pkContent");
			String contentbody=obj.getString("contentbody");
			String contenturl=obj.getString("contenturl");
			String photolist=obj.get("photolist")==null?"":obj.getString("photolist");
			String taglist=obj.get("taglist")==null?"":obj.getString("taglist");;
			
			ContentManage content=contentmanagerService.getContentById(Long.parseLong(pkContent));
			//内容描述保存到文件服务器
			String url=FileManegeUtil.writeFile(contentbody, "json", "", "");
			if(url!=null){
				content.setContentbody(url);
			}
			content.setContenturl(contenturl);
			content.setContenttopic(contenttopic);
			content.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			//保存修改的内容
			contentmanagerService.updatecontent(content);
			ArrayList<ContentPhoto> photoslist=new ArrayList<ContentPhoto>();
			ArrayList<UserFile> filelist=new ArrayList<UserFile>();
			ArrayList<ContentTag> tagslist=new ArrayList<ContentTag>();
			//保存图片信息
			if(!photolist.equals("")){				
				JSONArray arr=JSONArray.fromObject(photolist);
				Iterator<?> iter=arr.iterator();
				while(iter.hasNext()){
					JSONObject ontobj=(JSONObject) iter.next();
					String photourl=ontobj.get("url")==null?"":ontobj.getString("url");
					String photoshorturl=ontobj.get("shorturl")==null?"":ontobj.getString("shorturl");
					String shortwidth=ontobj.get("shortwidth")==null?"":ontobj.getString("shortwidth");
					String shortheight=ontobj.get("shortheight")==null?"":ontobj.getString("shortheight");
					String width=ontobj.get("width")==null?"":ontobj.getString("width");
					String height=ontobj.get("height")==null?"":ontobj.getString("height");
					String isfirst=ontobj.get("isfirst")==null?"":ontobj.getString("isfirst");	
					String isdelete=ontobj.get("isdelete")==null?"":ontobj.getString("isdelete");
					String pkPhoto=ontobj.get("pkPhoto")==null?"":ontobj.getString("pkPhoto");
					if(!isfirst.equals("")){
						  content.setFirstpage(photoshorturl);
						  content.setWidth(Integer.parseInt(shortwidth));
						  content.setHeight(Integer.parseInt(shortheight));
						  contentmanagerService.updatecontent(content);
					}
					if(!isdelete.equals("")&&isdelete.equals("Y")){
						contentphotoService.deletephotoById(Long.parseLong(pkPhoto));
					}else{
						if(isfirst.equals("Y")){
							content.setFirstpage(photoshorturl);
							contentmanagerService.updatecontent(content);
							if(pkPhoto.equals("")){
								ContentPhoto newphoto=new ContentPhoto();
								newphoto.setPkContent(content.getPkContent());
								newphoto.setPhotourl(photourl);
								newphoto.setPhotoshorturl(photoshorturl);
								newphoto.setWidth(Integer.parseInt(width));
								newphoto.setHeight(Integer.parseInt(height));
								newphoto.setShortheigth(Integer.parseInt(shortheight));
								newphoto.setShortwidth(Integer.parseInt(shortwidth));
								newphoto.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								newphoto.setDr((short)0);
								//加入批量操作
								photoslist.add(newphoto);
								//保存附件上传记录
								UserFile file=new UserFile();
								file.setBemodel("平台上传内容管理图片");
								file.setFilepath(photourl);
								file.setThumbnail(photoshorturl);
								//file.setFilesize(FileManegeUtil.getsize("", photourl).getString("size"));
								//file.setPkUser(info.getPkFairer());
								file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								file.setDr((short)0);
								filelist.add(file);
							}
						}else{
							ContentPhoto newphoto=new ContentPhoto();
							newphoto.setPkContent(content.getPkContent());
							newphoto.setPhotourl(photourl);
							newphoto.setPhotoshorturl(photoshorturl);
							newphoto.setWidth(Integer.parseInt(width));
							newphoto.setHeight(Integer.parseInt(height));
							newphoto.setShortheigth(Integer.parseInt(shortheight));
							newphoto.setShortwidth(Integer.parseInt(shortwidth));
							newphoto.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							newphoto.setDr((short)0);
							photoslist.add(newphoto);
							//保存附件上传记录
							UserFile file=new UserFile();
							file.setBemodel("平台上传内容管理图片");
							file.setFilepath(photourl);
							file.setThumbnail(photoshorturl);
							//file.setFilesize(FileManegeUtil.getsize("", photourl).getString("size"));
							//file.setPkUser(info.getPkFairer());
							file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							file.setDr((short)0);
							filelist.add(file);
					   }
					}
				}
			}
			//保存图片信息
			if(!taglist.equals("")){				
				JSONArray arr=JSONArray.fromObject(taglist);
				Iterator<?> iter=arr.iterator();			
				while(iter.hasNext()){
					JSONObject ontobj=(JSONObject) iter.next();
					String tagname=ontobj.getString("tagname");
					String isdelete=ontobj.get("isdelete")==null?"":ontobj.getString("isdelete");
					String pkTag=ontobj.get("pkTag")==null?"":ontobj.getString("pkTag");
					if(!isdelete.equals("")&&isdelete.equals("Y")){
					contenttagService.deletetagById(Long.parseLong(pkTag));
					}else{
					ContentTag newtag=new ContentTag();
					newtag.setPkContent(content.getPkContent());
					newtag.setTagname(tagname);
					newtag.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					newtag.setDr((short)0);
					tagslist.add(newtag);
					}
				}
			}
			//保存图片信息
			if(photoslist.size()>0){
				contentphotoService.savelist(photoslist);
			}
			//保存文件记录
			if(filelist.size()>0){
				userfileService.batchsave(filelist);
			}
			//保存标签信息
		    if(tagslist.size()>0){
		    	contenttagService.savelist(tagslist);
			}
		    outputstr("", "维护内容成功", true,null);
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 查询内容具体内容（Y）
	 * @param model
	 * @return
	 */
	@RequestMapping("/findbypk")
	public void findbypk(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkContent=obj.getString("pkContent");
			JSONObject returnobj=new JSONObject();
			ContentManage content=contentmanagerService.getContentById(Long.parseLong(pkContent));
			
			if(content!=null){
				String conentbody=FileManegeUtil.readFileInfo(content.getContentbody());			
				returnobj.accumulate("contenttopic", content.getContenttopic());
				returnobj.accumulate("contentbody", conentbody);
				returnobj.accumulate("contentstatus", content.getContentstatus());
				returnobj.accumulate("contenturl", content.getContenturl());
				returnobj.accumulate("firstpage", content.getFirstpage());
			     //查询内容图片
				ArrayList<ContentPhoto> photolist=(ArrayList<ContentPhoto>) contentphotoService.querybypkContent(pkContent);
				if(photolist.size()>0){
					returnobj.accumulate("photolist", JSONArray.fromObject(photolist));				
				 }			
				 //查询内容标签
				ArrayList<ContentTag> taglist=(ArrayList<ContentTag>) contenttagService.querybypkContent(pkContent);
				if(taglist.size()>0){
					returnobj.accumulate("taglist", JSONArray.fromObject(taglist));				
				}
				outputstr(returnobj.toString(), "查询内容成功", true,null);
			}else{
				outputstr("", "查询内容不存在", true,null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		    output(response, pojo);			
	  }
	
	/**
	 * 更新发布状态（Y）
	 * @param model
	 * @return
	 */
	@RequestMapping("/release")
	public void release(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkContent=obj.getString("pkContent");//内容主键
			String status=obj.getString("status");//001代表发布,002代表取消发布
			ContentManage content=contentmanagerService.getContentById(Long.parseLong(pkContent));
			//更新内容状态,已发布的不允许编辑
			content.setContentstatus(status);
			content.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			//查询所有的标签
			ArrayList<ContentTag> tags=(ArrayList<ContentTag>) contenttagService.querybypkContent(pkContent);
		     //发布的时候将主题和标签加入到LUCENE索引中
			if(status.equals("001")){
			       JSONObject luceneobj=new JSONObject();
					//内容信息
					JSONObject contentobj=JSONObject.fromObject(content);
					if(tags.size()>0){
						contentobj.accumulate("tags", JSONArray.fromObject(tags).toString());
					}
					luceneobj.accumulate("content", contentobj.toString());
				if(content.getContenttype().equals("combo")){
					//套餐信息		
					PlatformCombo combo=platformcomboService.getComboById(content.getPkType());									
					JSONObject contentdata=JSONObject.fromObject(combo);
					luceneobj.accumulate("data", contentdata.toString());
				 }else if(content.getContenttype().equals("fairer")){
					   //理发师信息	
						FairerInfo fairinfo=fairerinfoService.getFairerById(content.getPkType());
						//理发师技能
						ArrayList<FairerSkill> skills = (ArrayList<FairerSkill>) fairerskillService.QueryByFairer(content.getPkType().toString());
						JSONObject getobj=JSONObject.fromObject(fairinfo);
						if(skills.size()>0){
							getobj.accumulate("skills", JSONArray.fromObject(skills).toString());
						}
						luceneobj.accumulate("data", getobj.toString());
				 }else if(content.getContenttype().equals("shop")){
				        //店铺信息
					    ShopInfo info=shopinfoService.getShopById(content.getPkType());
						JSONObject contentdata=JSONObject.fromObject(info);
						luceneobj.accumulate("data", contentdata.toString()); 	
				 }else{
					 
				 }
				   String luceneresult=httpservice.sendPostRequset(url+"lucene/createindex.php", luceneobj.toString());
				   pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(luceneresult),ReturnPojo.class);
				if(pojo.isIssuccess()){					
					contentmanagerService.updatecontent(content);
					outputstr("", "发布成功", true,null);
			    }else{
					outputstr("", "发布失败", false,null);
			    }
			}else if(status.equals("002")){
				//取消发布的时候将对应的索引删除,不允许搜索
		        JSONObject luceneobj=new JSONObject();
		        luceneobj.accumulate("id", pkContent);
		        String luceneresult=httpservice.sendPostRequset(url+"lucene/deleteindex.php", luceneobj.toString());
		        pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(luceneresult),ReturnPojo.class);
				if(pojo.isIssuccess()){
					outputstr("", "取消发布成功", true,null);			
					contentmanagerService.updatecontent(content);				
			    }else{
			    	outputstr("", "取消发布失败", false,null);					
			    }
			}
		 } catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
		 }
		     output(response, pojo);			
	  }
	
	 /**
	  * 查询内容具体内容
	  * @param model
	  * @return
	  */
	 @RequestMapping("/querybykey")
	 public void querybykey(HttpServletRequest request,HttpServletResponse response,Model model) {
		 try {
			 JSONObject obj=RequestUtil.getPostString(request);
			 //开始检索
			 String luceneresult=httpservice.sendPostRequset(url+"lucene/searchindex.php", obj.toString());
			 pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(luceneresult),ReturnPojo.class);
		 } catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
		 }
		 output(response, pojo);
	 }
	
	 /**
	  * 内容列表（Y）
	  * @param model
	  * @return
	  */
	 @RequestMapping("/list")
	 public void list(HttpServletRequest request,HttpServletResponse response,Model model) {
		  try {
			  JSONObject obj=RequestUtil.getPostString(request);
			  String begintime=obj.get("begintime")==null?"":obj.getString("begintime");
			  String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
			  String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			  String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			  ArrayList<ContentManage>  managers=new ArrayList<ContentManage>();
			  ArrayList<ContentManage>  totalmanagers=new ArrayList<ContentManage>();
			  if(!curpage.equals("")){
				  managers=(ArrayList<ContentManage>) contentmanagerService.getAllContent("",begintime,endtime,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				  totalmanagers=(ArrayList<ContentManage>) contentmanagerService.getAllContent("",begintime,endtime,null,null);
			  }else{
				  managers=(ArrayList<ContentManage>) contentmanagerService.getAllContent("",begintime,endtime,null,null);
			  }			
			  if(managers.size()>0){
				  String data=JSONArray.fromObject(managers).toString();
				  if(!curpage.equals("")){	
					  outputstr(data,"查询成功", true,totalmanagers.size());		
				  }else{
					  outputstr(data,"查询成功", true,managers.size());			
				  }
			 }else{
				  outputstr("","无内容管理信息", false,null);			
			 }
		 } catch (Exception e) {
			 dealexception(e);
             outputexceptionstr();
		 }
		 output(response, pojo);
      }

	/**
	 * 查询热门搜索(平台现没用到，暂时保留)
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryhot")
	public void queryhot(HttpServletRequest request,HttpServletResponse response,Model model) {
	   try {
		   ArrayList<ContentHottype> types = (ArrayList<ContentHottype>) hottypeService.getAllContent();
		   ArrayList<JSONObject> returnobj=new ArrayList<JSONObject>();
		   if(types.size()>0){
			   for(int  i=0;i<types.size();i++){
				   ContentHottype type=types.get(i);	 
				   JSONObject obj=new JSONObject();
				   obj.accumulate("type", type.getHottype());	    		 
				   ArrayList<Object> searchs=(ArrayList<Object>) hotsearchService.getbytype(type.getPkHottype().toString());
				   if(searchs.size()>0){
					   String words=JSONArray.fromObject(searchs).toString(); 
					   obj.accumulate("words", words);	    
				   }
				   returnobj.add(obj);
			   }
			   if(returnobj.size()>0){
				   String data=JSONArray.fromObject(returnobj).toString();
				   outputstr(data,"查询热词成功", true,null);
			   }
		   }else{
			   outputstr("", "暂无搜索热词", true,null);
		   }
       } catch (Exception e) {
	      dealexception(e);
		  outputexceptionstr();
       }
	   output(response, pojo);
	}
	
	/**
	 * 根据类型主键和类型查询（Y）
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querybytype")
	public void querybytype(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		   JSONObject obj=RequestUtil.getPostString(request);
		   String pkType=obj.getString("pkType");
		   String contenttype=obj.getString("contenttype");		   
		   ArrayList<ContentManage> manager=(ArrayList<ContentManage>) contentmanagerService.querybytype(pkType, contenttype);				  
		   if(manager.size()>0){			   
				String pkContent=manager.get(0).getPkContent().toString();				
				JSONObject returnobj=new JSONObject();			
				ContentManage content=contentmanagerService.getContentById(Long.parseLong(pkContent));
				if(content!=null){
					String conentbody=FileManegeUtil.readFileInfo(content.getContentbody());			
					returnobj.accumulate("contenttopic", content.getContenttopic());
					returnobj.accumulate("contenttype", content.getContenttype());
					returnobj.accumulate("pkType", content.getPkType());
					returnobj.accumulate("contentbody", conentbody);
					returnobj.accumulate("contentstatus", content.getContentstatus());
					returnobj.accumulate("contenturl", content.getContenturl());
					returnobj.accumulate("firstpage", content.getFirstpage());
					returnobj.accumulate("pkContent", content.getPkContent());
				    //查询内容图片
					ArrayList<ContentPhoto> photolist=(ArrayList<ContentPhoto>) contentphotoService.querybypkContent(pkContent);
					if(photolist.size()>0){
						returnobj.accumulate("photolist", JSONArray.fromObject(photolist));				
					}			
					//查询内容标签
					ArrayList<ContentTag> taglist=(ArrayList<ContentTag>) contenttagService.querybypkContent(pkContent);
					if(taglist.size()>0){
						returnobj.accumulate("taglist", JSONArray.fromObject(taglist));				
					}
					outputstr(returnobj.toString(), "查询内容成功", true,null);			
				 }else{
					outputstr("", "无内容", true,null); 
				 }
		   	}else{
		   		 outputstr("", "无内容", true,null);			  
		   	}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
	 	}
		  	output(response, pojo);
	 }
	
}
