package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.MessageType;
import com.rockstar.o2o.pojo.MessageList;
import com.rockstar.o2o.pojo.MessageRecord;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.message.MessageUtil;

/**
 * 消息controller
 * @author xc
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController{
	
	private static Logger log = Logger.getLogger(MessageController.class);
	
	/**
	 * 发送消息
	 * @param model
	 * @return
	 */
	@RequestMapping("/send")
	public void saveinfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String result=MessageUtil.execute(obj, messageservice,messagelistService);
		 if(result.equals("success")){
			outputstr("", "发送成功", true,null);
		  }else{
			  outputstr("", "发送失败", false,null);
		  }
		} catch (Exception e) {
			// TODO: handle exception
            dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}	
	

	/**
	 * 更新消息状态
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatestatus")
	public void updatestatus(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String pkMessage=obj.getString("pkMessage");//消息id
		String messagestatus=obj.getString("messagestatus");//001代表未读,002代表已读,003代表忽略,004代表删除
		MessageRecord message=messageservice.getMessageById(Long.parseLong(pkMessage));
		if(message!=null){
			    message.setMessagestatus(messagestatus);
			    if(messagestatus.equals("004")){
			    message.setDr((short)1);
			    message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    }
			   //更新消息状态
			     messageservice.updateGroup(message);
			     outputstr("", "成功", true,null);
		    }else{
				 outputstr("", "消息不存在", false,null);
		    }
		  } catch (Exception e) {
			// TODO: handle exception
			   outputexceptionstr();
			  log.error(e.getMessage());
		   }
		    output(response, pojo);
	  }
	
	
	 /**
	 * 查询未读系统消息
	 * @param model
	 * @return
	 */
	@RequestMapping("/querymessage")
	public void queryunread(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String pkUser=obj.getString("pkUser");
		String messagestatus=obj.get("messagestatus")==null?"":obj.getString("messagestatus");
		String messagetype=MessageType.TYPE_PLAT;
		String begintime=obj.get("begintime")==null?"":obj.getString("begintime");
		String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
		
		ArrayList<Object> list=new ArrayList<Object>();
		if(!messagestatus.equals("")){
		list=(ArrayList<Object>) messageservice.querybyuser(pkUser, messagetype, messagestatus);		
		 if(list.size()>0){
			 pojo.setData(JSONArray.fromObject(list).toString());
             outputstr(JSONArray.fromObject(list).toString(), "查询消息成功", true,null);
		  }else{
			 outputstr("", "暂无消息", true,null);
		  }
		 }else{
		  list=(ArrayList<Object>) messageservice.queryBytime(pkUser, messagetype, begintime, endtime);	
		  if(list.size()>0){
				 pojo.setData(JSONArray.fromObject(list).toString());
	             outputstr(JSONArray.fromObject(list).toString(), "查询消息成功", true,null);
			  }else{
				 outputstr("", "暂无消息", true,null);
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
	 * 根据时间段系统消息
	 * @param model
	 * @return
	 */
	@RequestMapping("/querybytime")
	public void querybytime(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String pkUser=obj.getString("pkUser");
		String begintime=obj.get("begintime")==null?"":obj.getString("begintime");
		String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
		String messagetype=MessageType.TYPE_PLAT;
		
		ArrayList<Object> list=(ArrayList<Object>) messageservice.queryBytime(pkUser, messagetype, begintime, endtime);		
		 if(list.size()>0){
			 pojo.setData(JSONArray.fromObject(list).toString());
			 pojo.setIssuccess(true);
			 pojo.setMsg("查询消息成功");
		  }else{
			  pojo.setData("");
			  pojo.setIssuccess(true);
			  pojo.setMsg("暂无消息");
		  }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			pojo.setData("");
			pojo.setIssuccess(false);
			pojo.setMsg("网络异常");
		  }
		  output(response, pojo);
	  }
	
	 /**
	 * 根据店铺查询所有系统发送消息记录
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryplat")
	public void queryplat(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String pkShop=obj.getString("pkShop");
		String messagetype=MessageType.TYPE_PLAT;		
		ArrayList<MessageList> messagelist=(ArrayList<MessageList>) messagelistService.queryallplat(messagetype, pkShop);
		if(messagelist.size()>0){
			String data=JSONArray.fromObject(messagelist).toString();
			outputstr(data, "查询历史消息成功", true,null);
		  }else{
			outputstr("", "暂无历史消息", true,null);
		  }
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		  }
		  output(response, pojo);
	  }
	
}
