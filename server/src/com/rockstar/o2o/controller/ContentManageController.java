package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.mongodb.pojo.CollectList;
import com.rockstar.o2o.mongodb.pojo.CommentList;
import com.rockstar.o2o.pojo.ContentCollect;
import com.rockstar.o2o.pojo.ContentHottype;
import com.rockstar.o2o.pojo.ContentManage;
import com.rockstar.o2o.pojo.ContentPhoto;
import com.rockstar.o2o.pojo.ContentTag;
import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.pojo.FairerSkill;
import com.rockstar.o2o.pojo.PlatformCombo;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RedisUtils;
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
	 * 保存内容信息
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
			String pkShop=obj.getString("pkShop");
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
			content.setPkShop(Long.parseLong(pkShop));
			
			content=contentmanagerService.save(content);
			
			ArrayList<Object> addlist=new ArrayList<Object>();
			ArrayList<Object> updatelist=new ArrayList<Object>();
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
					  updatelist.add(content);
					}
					newphoto.setPhotourl(photourl);
					newphoto.setPhotoshorturl(photoshorturl);
					newphoto.setWidth(Integer.parseInt(width));
					newphoto.setHeight(Integer.parseInt(height));
					newphoto.setShortheigth(Integer.parseInt(shortheight));
					newphoto.setShortwidth(Integer.parseInt(shortwidth));
					newphoto.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					newphoto.setDr((short)0);
					
					addlist.add(newphoto);
					
					//保存附件上传记录
					UserFile file=new UserFile();
					file.setBemodel("平台上传内容管理图片");
					file.setFilepath(photourl);
					file.setThumbnail(photoshorturl);
					//file.setFilesize(FileManegeUtil.getsize("", photourl).getString("size"));
					//file.setPkUser(info.getPkFairer());
					file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					file.setDr((short)0);
					addlist.add(file);
				
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
					addlist.add(newtag);
				}
			}
									
		     basicservice.batchoperate(addlist, updatelist);
		    outputstr("", "保存内容成功", true,null);
		} catch (Exception e) {
			// TODO: handle exception
			  dealexception(e);
			  outputexceptionstr();
		}
		
		    output(response, pojo);
	}
	
	
	
	 /**
	 * 修改内容信息
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
			
			ArrayList<Object> addlist=new ArrayList<Object>();
			ArrayList<Object> updatelist=new ArrayList<Object>();
			
			//保存修改的内容
			updatelist.add(content);

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
					if(isfirst.equals("Y")){
						  content.setFirstpage(photoshorturl);
						  content.setWidth(Integer.parseInt(shortwidth));
						  content.setHeight(Integer.parseInt(shortheight));
						  updatelist.add(content);
						}
					
					if(!isdelete.equals("")&&isdelete.equals("Y")){
						ContentPhoto photo=contentphotoService.getPhotoById(Long.parseLong(pkPhoto));
						photo.setDr((short)1);
						photo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						updatelist.add(photo);
					}else{
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
							
							addlist.add(newphoto);
							
							//保存附件上传记录
							UserFile file=new UserFile();
							file.setBemodel("平台上传内容管理图片");
							file.setFilepath(photourl);
							file.setThumbnail(photoshorturl);
							//file.setFilesize(FileManegeUtil.getsize("", photourl).getString("size"));
							//file.setPkUser(info.getPkFairer());
							file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							file.setDr((short)0);
							addlist.add(file);
							
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
						ContentTag tag=contenttagService.getTagById(Long.parseLong(pkTag));
						tag.setDr((short)1);
						tag.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						updatelist.add(tag);
					}else{
					if(pkTag.equals("")){
					ContentTag newtag=new ContentTag();
					newtag.setPkContent(content.getPkContent());
					newtag.setTagname(tagname);
					newtag.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					newtag.setDr((short)0);
					addlist.add(newtag);
						}
					}
				}
			}
									
		     basicservice.batchoperate(addlist, updatelist);
			
		    outputstr("", "维护内容成功", true,null);
		} catch (Exception e) {
			// TODO: handle exception
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
//			returnobj.accumulate("contenttype", content.getContenttype());
//			returnobj.accumulate("pkType", content.getPkType());
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
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		    output(response, pojo);			
	  }
	
	
	
	/**
	 * 更新发布状态
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
			// TODO: handle exception
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
		  System.out.println(url+"lucene/searchindex.php");
		  System.out.println(obj.toString());
		  //开始检索
		  String luceneresult=httpservice.sendPostRequset(url+"lucene/searchindex.php", obj.toString());
	
		   
		   pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(luceneresult),ReturnPojo.class);
		  
		 } catch (Exception e) {
			// TODO: handle exception
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
	@RequestMapping("/list")
		public void list(HttpServletRequest request,HttpServletResponse response,Model model) {
		  try {
		  JSONObject obj=RequestUtil.getPostString(request);		  
		  String pkUser=obj.getString("pkUser");
		  ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
		  String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
		  String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
		  ArrayList<ContentManage>  managers=new ArrayList<ContentManage>();
		  ArrayList<ContentManage>  totalmanagers=new ArrayList<ContentManage>();
		  if(user.getUsertype().equals("2")){
			  if(!curpage.equals("")){
				     managers=(ArrayList<ContentManage>) contentmanagerService.getAllContent("",Integer.parseInt(curpage),Integer.parseInt(pagesize));
				     totalmanagers=(ArrayList<ContentManage>) contentmanagerService.getAllContent("",null,null);
					}else{
					 managers=(ArrayList<ContentManage>) contentmanagerService.getAllContent("",null,null);
						}	
		  }else{
			  String pkshop=obj.getString("pkShop");
			  if(!curpage.equals("")){
				     managers=(ArrayList<ContentManage>) contentmanagerService.getAllContent(pkshop,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				     totalmanagers=(ArrayList<ContentManage>) contentmanagerService.getAllContent(pkshop,null,null);
					}else{
					 managers=(ArrayList<ContentManage>) contentmanagerService.getAllContent(pkshop,null,null);
			}	 
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
			// TODO: handle exception
			 dealexception(e);
              outputexceptionstr();
		  }
		    output(response, pojo);
			
      }
	
	
	
	/**
	 * 查询热门搜索
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
		// TODO: handle exception
	      dealexception(e);
		  outputexceptionstr();
	     }
	    output(response, pojo);
		
  }
	
	/**
	 * 根据类型主键和类型查询
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
			// TODO: handle exception
			  dealexception(e);
			  outputexceptionstr();
	 	}
		  output(response, pojo);
	 }
	
	/**
	 * 关注,取消关注
	 * @param request
	 * @param response
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/collect")
	public void collect(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			final String pkContent=obj.getString("pkContent");
			final String iscollect=obj.getString("iscollect");
			final String collecttype=obj.getString("collecttype");
			final String collectfrom=obj.getString("collectfrom");
			final String collectid=obj.getString("collectid");
			final String pkUser=obj.get("pkUser")==null?null:obj.getString("pkUser");
			
			final ContentManage content=contentmanagerService.getContentById(Long.parseLong(pkContent));
			Integer count=0;
			try {
				    count=Integer.parseInt(RedisUtils.getObject("collect:id:"+pkContent)==null?"0":RedisUtils.getObject("collect:id:"+pkContent).toString());
					if(iscollect.equals("N")){
						RedisUtils.setKey("collect:id:"+pkContent,count==null||count==0?count:count-1);
						ArrayList<Object> list=(ArrayList<Object>) RedisUtils.getObject("collect:id:"+pkContent+":"+"totalcount");
						if(list!=null&&list.size()>0){
							list.remove(collectid);
							RedisUtils.setKey("collect:id:"+pkContent+":"+"totalcount", list);
						}

					}else{
						RedisUtils.setKey("collect:id:"+pkContent,count+1);
						ArrayList<Object> list=(ArrayList<Object>) RedisUtils.getObject("collect:id:"+pkContent+":"+"totalcount");
						if(list!=null&&list.size()>0){
							list.add(collectid);
							RedisUtils.setKey("collect:id:"+pkContent+":"+"totalcount", list);
						}else{
							list=new ArrayList<Object>();
							list.add(collectid);
							RedisUtils.setKey("collect:id:"+pkContent+":"+"totalcount", list);	
						}

					}
			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();
			}
			
			if(iscollect.equals("N")){
				outputstr("", "取消收藏成功", true, null);
			}else{
				outputstr("", "收藏成功", true, null);
			}
			//异步数据存储
			final ArrayList<Object> addlist=new ArrayList<Object>();
			final ArrayList<Object> updatelist=new ArrayList<Object>();
		    new Thread(){
		    	public void run(){
					if(iscollect.equals("N")){
						 content.setCollectcount(content.getCollectcount()==null||content.getCollectcount()<=0?0:content.getCollectcount()-1);
					}else{
				 		   ContentCollect collect =new ContentCollect();
						   collect.setCollectfrom(collectfrom);
						   collect.setCollectorid(collectid);
						   collect.setCollecttype(collecttype);
						   collect.setCollecttime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						   collect.setPkContent(Long.parseLong(pkContent));
						   collect.setPkUser(pkUser==null||pkUser.equals("")?null:Long.parseLong(pkUser));
						   collect.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						   collect.setIscollect("Y");
						   collect.setDr((short)0);
						   
						   addlist.add(collect);
						   
						  content.setCollectcount(content.getCollectcount()==null||content.getCollectcount()<=0?1:content.getCollectcount()+1);
					}
					
					updatelist.add(content);
					
					basicservice.batchoperate(addlist, updatelist);
					
					//保存关注人到mongodb中
					CollectList collectlist=new CollectList();
					collectlist.setCollectfrom(collectfrom);
					collectlist.setCollectorid(collectid);
					collectlist.setCollecttype(collecttype);
					collectlist.setCollecttime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					collectlist.setPkContent(Long.parseLong(pkContent));
					collectlist.setPkUser(pkUser==null||pkUser.equals("")?null:Long.parseLong(pkUser));
                   
					mongoservice.save(collectlist);
				  
		    	}
		    }.start();
		    

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);
	     }
	
	
	 /**
	  * 查询评论详情
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("querycomment")
	 public void querycomment(HttpServletRequest request,HttpServletResponse response,Model model){	
		 
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String contenttype=obj.getString("contenttype");
			String pk=obj.getString("pk");
			String pagesize=obj.getString("pagesize");
			String curpage=obj.getString("curpage");
			
			HashMap<String, Object> map=new HashMap<String, Object>();
			boolean iscontinue=true;
			if(contenttype.equals("combo")){
			map.put("pkCombo", Long.parseLong(pk));	
			}else if(contenttype.equals("shop")){
			map.put("pkShop", Long.parseLong(pk));		
			}else if(contenttype.equals("fairer")){
			map.put("pkFairer", Long.parseLong(pk));			
			}else{
				iscontinue=false;
				outputstr("", "查询内容不存在",false, null);
			}
			
			if(iscontinue){
			ArrayList<Object> getobj=(ArrayList<Object>) mongoservice.pageQuery(CommentList.class, Integer.parseInt(pagesize), Integer.parseInt(curpage), map);
			ArrayList<Object> allobj=(ArrayList<Object>) mongoservice.pageQuery(CommentList.class, null, null, map);
			
			  if(getobj.size()>0){
				   String data=JSONArray.fromObject(getobj).toString();
				   outputstr(data, "查询评价成功", true, allobj.size());
			  }else{
				  outputstr("", "暂无评价", true, null);
			  }
		
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		 output(response, pojo);
	 }
	 
	
}
