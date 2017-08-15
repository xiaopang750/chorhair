package com.rockstar.o2o.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.WxMenu;
import com.rockstar.o2o.pojo.WxOauthEvent;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.ReturnPojo;
import com.rockstar.o2o.util.WxserviceUtil;

/**
 * 微信自定义菜单
 * @author hp
 *
 */
@Controller
@RequestMapping("/wxmenu")
public class WxMenuController extends BaseController{

    /**
     * 创建微信事件
     * @param request
     * @param response
     * @param model
     * @throws IOException
     */
	@RequestMapping("/createevent")
	public void createevent(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		try {
			JSONObject obj=	RequestUtil.getPostString(request);
			String pkMenu=obj.getString("pkMenu");
			WxMenu menu=(WxMenu) basicservice.querybyid(WxMenu.class, Long.parseLong(pkMenu));
			ArrayList<Object> addlist=new ArrayList<Object>();
			ArrayList<Object> updatelist=new ArrayList<Object>();
			
			WxOauthEvent event=new WxOauthEvent();
			event.setAccountId(account_id);
			event.setInvalidFlag("0");
			event.setDr((short)0);
			event.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			event.setEventId(pkMenu);
			event.setRedirectUrl(menu.getUrl());
			
			ArrayList<Object> eventobj=(ArrayList<Object>) basicservice.query(WxOauthEvent.class, " accountId = ? and eventId = ? ", account_id,menu.getMenukey());
			if(eventobj.size()>0){
				WxOauthEvent oldevent=(WxOauthEvent) eventobj.get(0);
				oldevent.setRedirectUrl(menu.getUrl());
				updatelist.add(oldevent);
			}else{
				event.setAccountId(account_id);
				event.setInvalidFlag("0");
				event.setDr((short)0);
				event.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				event.setEventId(pkMenu);
				event.setRedirectUrl(menu.getUrl());
				addlist.add(event);
			}
						
			String result=httpservice.sendPostRequset(WxserviceUtil.GetUrl("createurl"), JSONObject.fromObject(event).toString());

            pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(result),ReturnPojo.class);
            
            if(pojo.isIssuccess()){
            	menu.setWxurl(JSONObject.fromObject(pojo.getData()).getString("redirecturl"));
            	updatelist.add(menu);
            }
            
            basicservice.batchoperate(addlist, updatelist);
            
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	@RequestMapping("/createwxurl")
	public void createwxurl(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		try {
			JSONObject obj=	RequestUtil.getPostString(request);
			String eventid=obj.getString("eventid");
			String url=obj.getString("url");
			ArrayList<Object> addlist=new ArrayList<Object>();
			ArrayList<Object> updatelist=new ArrayList<Object>();
			
			WxOauthEvent event=new WxOauthEvent();
			event.setAccountId(account_id);
			event.setInvalidFlag("0");
			event.setDr((short)0);
			event.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			event.setEventId(eventid);
			event.setRedirectUrl(url);
			
			ArrayList<Object> eventobj=(ArrayList<Object>) basicservice.query(WxOauthEvent.class, " accountId = ? and eventId = ? ", account_id,eventid);
			if(eventobj.size()>0){
				WxOauthEvent oldevent=(WxOauthEvent) eventobj.get(0);
				oldevent.setRedirectUrl(url);
				updatelist.add(oldevent);
			}else{
				event.setAccountId(account_id);
				event.setInvalidFlag("0");
				event.setDr((short)0);
				event.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				event.setEventId(eventid);
				event.setRedirectUrl(url);
				addlist.add(event);
			}
						
			String result=httpservice.sendPostRequset(WxserviceUtil.GetUrl("createurl"), JSONObject.fromObject(event).toString());

            pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(result),ReturnPojo.class);
            
            basicservice.batchoperate(addlist, updatelist);
            
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 创建微信菜单
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/createmenu")
	public void create(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {			
		try {
		JSONObject wxjsonobject=new JSONObject();
		ArrayList<Object> menus=(ArrayList<Object>) basicservice.query(WxMenu.class, " accountId = ? and (pkParent is null or pkParent = '') order by menuorder", account_id);
	    
		if(menus.size()>0){
			ArrayList<JSONObject> buttons=new ArrayList<JSONObject>();
			for(int i=0;i<menus.size();i++){
				JSONObject obj=new JSONObject();
				WxMenu menu=(WxMenu) menus.get(i);
				ArrayList<Object> childmenus=(ArrayList<Object>) basicservice.query(WxMenu.class, " pkParent = ?  ", menu.getPkMenu());
			    if(childmenus.size()>0){
			    	obj.accumulate("name", menu.getMenuname());
			    	ArrayList<JSONObject> menulists=new ArrayList<JSONObject>();
                    for(int j=0;j<childmenus.size();j++){
                    	JSONObject childobj=new JSONObject();
                    	WxMenu childmenu=(WxMenu) childmenus.get(j); 	
                    	childobj.accumulate("name", childmenu.getMenuname());
                    	childobj.accumulate("type", childmenu.getMenutype());
    			    	if(childmenu.getWxurl()!=null&&!childmenu.getWxurl().equals("")){
    			    		childobj.accumulate("url", childmenu.getWxurl());	
    			    	}else if(childmenu.getUrl()!=null&&!childmenu.getUrl().equals("")){
    			    		childobj.accumulate("url", childmenu.getUrl());
    			    	}
    			    	
    			    	if(childmenu.getMenutype().equals("click")&&childmenu.getMenukey()!=null&&!childmenu.getMenukey().equals("")){
    			    		childobj.accumulate("key", childmenu.getMenukey());	
    				    }
    			    	menulists.add(childobj);
                    }
                    obj.accumulate("sub_button", JSONArray.fromObject(menulists).toString());
			    }else{
			    	obj.accumulate("name", menu.getMenuname());
			    	obj.accumulate("type", menu.getMenutype());
			    	if(menu.getWxurl()!=null&&!menu.getWxurl().equals("")){
			    	obj.accumulate("url", menu.getWxurl());	
			    	}else if(menu.getUrl()!=null&&!menu.getUrl().equals("")){
			    	obj.accumulate("url", menu.getUrl());
			    	}
			    	
			    	if(menu.getMenutype().equals("click")&&menu.getMenukey()!=null&&!menu.getMenukey().equals("")){
				    	obj.accumulate("key", menu.getMenukey());	
				    }
			    }
			    buttons.add(obj);
			}
			
			wxjsonobject.accumulate("button", JSONArray.fromObject(buttons).toString());
			
			String result=httpservice.sendPostRequset(WxserviceUtil.GetUrl("createmenu"), wxjsonobject.toString());

             pojo=(ReturnPojo) JSONObject.toBean(JSONObject.fromObject(result),ReturnPojo.class);
		}else{
			outputstr("", "无微信菜单", false, null);
		}
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
}
