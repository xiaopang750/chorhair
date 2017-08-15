package com.rockstar.o2o.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.pojo.ClientInfo;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;


/**
 * 设备信息controller
 * @author xc
 *
 */
@Controller
@RequestMapping("/clientinfo")
public class ClientInfoController extends BaseController {
	private static Logger log = Logger.getLogger(ClientInfoController.class);
	
	/**
	 * 保存信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
	public void saveinfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		//客户端信息
		 String devicetype=obj.get("devicetype")==null?"":obj.get("devicetype").toString();
		 String devicefactory=obj.get("devicefactory")==null?"":obj.get("devicefactory").toString();
		 String deviceimei=obj.get("deviceimei")==null?"":obj.get("deviceimei").toString();
		 String deviceuuid=obj.get("deviceuuid")==null?"":obj.get("deviceuuid").toString();
		 String deviceimsi=obj.get("deviceimsi")==null?"":obj.get("deviceimsi").toString();
		 String devicescreenfbl=obj.get("devicescreenfbl")==null?"":obj.get("devicescreenfbl").toString();
		 String systemname=obj.get("systemname")==null?"":obj.get("systemname").toString();
		 String systemversion=obj.get("systemversion")==null?"":obj.get("systemversion").toString();
		 String systemlanguage=obj.get("systemlanguage")==null?"":obj.get("systemlanguage").toString();
		 String systemfactory=obj.get("systemfactory")==null?"":obj.get("systemfactory").toString();
		 String token=obj.get("token")==null?"":obj.get("token").toString();
		 String clientid=obj.get("clientid")==null?"":obj.get("clientid").toString();
		 String appid=obj.get("appid")==null?"":obj.get("appid").toString();
		 String appkey=obj.get("appkey")==null?"":obj.get("appkey").toString();
		//保存客户端设备相关信息
		ClientInfo info=new  ClientInfo();
		info.setAppid(appid);
        info.setAppkey(appkey);
        info.setClientid(clientid);
        info.setDevicefactory(devicefactory);
        info.setDeviceimei(deviceimei);
        info.setDeviceimsi(deviceimsi);
        info.setDevicescreenfbl(devicescreenfbl);
        info.setDevicetype(devicetype);
        info.setDeviceuuid(deviceuuid);
        info.setSystemfactory(systemfactory);
        info.setSystemlanguage(systemlanguage);
        info.setSystemname(systemname);
        info.setSystemversion(systemversion);
        info.setToken(token);
        info.setDr((short)0);
        info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        clientinfoservice.save(info);
		
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
		}
	}	
}
