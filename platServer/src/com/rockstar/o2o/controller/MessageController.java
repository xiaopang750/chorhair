package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.MessageType;
import com.rockstar.o2o.pojo.MessageGroup;
import com.rockstar.o2o.pojo.MessageGroupMember;
import com.rockstar.o2o.pojo.MessageList;
import com.rockstar.o2o.pojo.MessageRecord;
import com.rockstar.o2o.pojo.MessageSendRecord;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.message.MessageUtil;
import com.rockstar.o2o.util.message.ShortMessageUtil;

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
	@RequestMapping("/querymessages")
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
			 outputstr(JSONArray.fromObject(list).toString(), "查询消息成功", true, null);
		  }else{
			 outputstr("", "暂无消息", true, null);
		  }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
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
	
	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/sendmessage")
	public void sendmessage(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			
			final String pkGroup=obj.get("pkGroup")==null?null:obj.getString("pkGroup");
			
			final String groupname=obj.get("groupname")==null?null:obj.getString("groupname");
			
			final String pkMember=obj.get("pkMember")==null?null:obj.getString("pkMember");
			
			final String membername=obj.get("membername")==null?null:obj.getString("membername");
			
			final String cellphone=obj.get("cellphone")==null?null:obj.getString("cellphone");
			
			final String sendfrom=obj.get("sendfrom")==null?null:obj.getString("sendfrom");
			
			final String usergroup=obj.get("usergroup")==null?null:obj.getString("usergroup");
			
			final String content=obj.getString("content");
						
			final String pkUser=obj.getString("pkUser");
			
			ShortMessageUtil util=new ShortMessageUtil();
			
			StringBuffer buffer=new StringBuffer();
			
			if(pkGroup!=null&&!pkGroup.equals("")){
				ArrayList<Object> list=(ArrayList<Object>) basicservice.query(MessageGroupMember.class, " pkGroup = ? ", Long.parseLong(pkGroup));
			    
				HashMap<String, Object> map=new HashMap<String, Object>();
			
				for(int i=0;i<list.size();i++){
			    	MessageGroupMember member=(MessageGroupMember) list.get(i);	
			    	if(member.getMemberphone()!=null&&!member.getMemberphone().equals("")){
			    	if(!map.containsKey(member.getMemberphone())){
			    		if(list.size()>1){
			    			if(i<list.size()-1){
			    				buffer.append(member.getMemberphone().toString()+",");
					    		map.put(member.getMemberphone(), member.getMemberphone());
			    			}else{
			    				buffer.append(member.getMemberphone().toString());
					    		map.put(member.getMemberphone(), member.getMemberphone());
			    			}
			    		}else{
				    		buffer.append(member.getMemberphone().toString());
				    		map.put(member.getMemberphone(), member.getMemberphone());
			    		}
			    	  }		
			    	}
			       }
			    }else{
				    buffer.append(cellphone);
		     	}
			
			final String cellphonelist=buffer.toString();
			
			if(cellphonelist.length()<=0){
				outputstr("", "手机号码为空", false, null);
			}else{
			String result=util.mt(cellphonelist, content+"【虫二美发】", "", "", "");
			
			if(result!=null&&!result.equals("")){

				outputstr("", "发送短信成功", true, null);
				
				//异步存储
				new Thread(){					
					public void run(){			
						MessageSendRecord record=new MessageSendRecord();
						record.setContent(content);
						record.setMessagetype(MessageType.SENDMESSAGE);
				        record.setSendfrom(sendfrom);;
				        record.setSendtime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				        record.setSendresult("Y");
				        record.setMembername(membername);
				        record.setCellphone(cellphone==null||cellphone.equals("")?cellphonelist:cellphone);
				        record.setGroupname(groupname);
				        record.setMessagegroup(usergroup);
				        record.setRecordstatus("001");
				        record.setPkGroup(pkGroup==null||pkGroup.equals("")?null:Long.parseLong(pkGroup));
				        record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				        record.setDr((short)0);
				        record.setPkSender(Long.parseLong(pkUser));
				        record.setPkMember(pkMember==null||pkMember.equals("")?null:Long.parseLong(pkMember));
					
					    basicservice.save(record);
					}
				}.start();
				
				
			}else{				
				outputstr("", "发送短信失败", false, null);
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 查询短信发送记录
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querymessage")
	public void querymessage(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			  JSONObject obj=RequestUtil.getPostString(request);
			  String pkGroup=obj.get("pkGroup")==null?null:obj.getString("pkGroup");
			  String pkMember=obj.get("pkMember")==null?null:obj.getString("pkMember");
			  String usergroup=(String)obj.get("usergroup");
			  Integer curpage=obj.get("curpage")==null?null:Integer.parseInt(obj.getString("curpage"));
			  Integer pagesize=obj.get("pagesize")==null?null:Integer.parseInt(obj.getString("pagesize"));
			  
			  String begintime=(String)obj.get("begintime");
			  
			  String endtime=(String)obj.get("endtime");
			  
			  ArrayList<Object> alllist=new ArrayList<Object>();
			  
			  HashMap<String, Object> map=new HashMap<String, Object>();
			  
			  StringBuffer buffer=new StringBuffer();
			  
			  if(pkGroup!=null&&!pkGroup.equals("")){
				  buffer.append(" and pkGroup = :pkGroup");
				  map.put("pkGroup", Long.parseLong(pkGroup));
			  }
			
			  
			  if(pkMember!=null&&!pkMember.equals("")){
				  buffer.append(" and pkMember = :pkMember");
				  map.put("pkMember", Long.parseLong(pkMember));
			  }
			  
			  
			  if(usergroup!=null&&!usergroup.equals("")){
				  buffer.append(" and messagegroup = :messagegroup");
				  map.put("messagegroup", usergroup);
			  }
			  
			  
			  if(begintime!=null&&!begintime.equals("")){
				  buffer.append(" and sendtime >= :begintime");
				  map.put("begintime", begintime);
			  }
			  
			  if(endtime!=null&&!endtime.equals("")){
				  buffer.append(" and sendtime <= :endtime");
				  map.put("endtime", endtime);
			  }
			  
			  buffer.append(" and messagetype = :messagetype");
			  map.put("messagetype", MessageType.SENDMESSAGE);
			  
			  alllist=(ArrayList<Object>) basicservice.queryByMap(MessageSendRecord.class, buffer.toString(), curpage, pagesize, map);					  
			  
			  if(alllist.size()>0){
				  outputstr(JSONArray.fromObject(alllist).toString(), "查询发送记录成功", true, null);
			  }else{
				  outputstr("", "暂无发送记录", true, null);
			  }
			  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 新建沟通组
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/creategroup")
	public void creategroup(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String groupname=getobj.getString("groupname");
			String groupmember=getobj.getString("groupmember");
			String groupfrom=getobj.getString("groupfrom");
			String pkUser=getobj.getString("pkUser");
			
			JSONArray arr=JSONArray.fromObject(groupmember);
			
			//新增沟通组
			MessageGroup newgroup=new MessageGroup();
			newgroup.setCreatorid(Long.parseLong(pkUser));
			newgroup.setCreatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			newgroup.setDr((short)0);
			newgroup.setGroupfrom(groupfrom);
			newgroup.setGroupname(groupname);
			newgroup.setGroupnum(arr.size());
 
			Object pk=basicservice.save(newgroup);
			
			Iterator<?> iter=arr.iterator();
			ArrayList<Object> addlist=new ArrayList<Object>();
			//组成员
			while(iter.hasNext()){
				JSONObject obj=JSONObject.fromObject(iter.next());
				String membername=obj.getString("membername");
				String membertype=MessageType.SENDMESSAGE;
				String pkMember=obj.getString("pkMember");
				String memberphone=obj.getString("memberphone");
                
				MessageGroupMember newmember=new MessageGroupMember();
				newmember.setDr((short)0);
				newmember.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				newmember.setMembername(membername);
				newmember.setMemberphone(memberphone);
				newmember.setMembertype(membertype);
				newmember.setPkMember(Long.parseLong(pkMember));
				newmember.setPkGroup(Long.parseLong(pk.toString()));
				
				addlist.add(newmember);
							
			}
			
			//批量保存组会员
             basicservice.batchoperate(addlist, null);
             
             outputstr("", "新增沟通组成功", true, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
        output(response, pojo);	
	}
	
	
	/**
	 *删除沟通组,删除沟通组及下面的沟通组会员
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delgroup")
	public void delgroup(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkGroup=obj.getString("pkGroup");
			
			MessageGroup group=(MessageGroup) basicservice.querybyid(MessageGroup.class, Long.parseLong(pkGroup));
		
			group.setDr((short)1);
			group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			
			ArrayList<Object> groupmembers=(ArrayList<Object>) basicservice.query(MessageGroupMember.class, " pkGroup = ? ", Long.parseLong(pkGroup));
			
			ArrayList<Object> dellist=new ArrayList<Object>();
			
			dellist.add(group);
			
			for(int i=0;i<groupmembers.size();i++){
				
				MessageGroupMember member=(MessageGroupMember) groupmembers.get(i);
				
				member.setDr((short)1);
				member.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				dellist.add(member);
				
			}
			
			basicservice.batchoperate(null, dellist);
			
			outputstr("", "删除分组成功", true, null);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 编辑沟通组
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editgroup")
	public void editgroup(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String groupname=getobj.getString("groupname");
			String groupmember=getobj.getString("groupmember");
			String pkGroup=getobj.getString("pkGroup");
						
			JSONArray arr=JSONArray.fromObject(groupmember);			
		
			ArrayList<Object> addlist=new ArrayList<Object>();
			ArrayList<Object> updatelist=new ArrayList<Object>();
			
			MessageGroup group=(MessageGroup) basicservice.querybyid(MessageGroup.class, Long.parseLong(pkGroup));
			
			group.setGroupname(groupname);
			
			
			Iterator<?> iter=arr.iterator();
			int delnum=0;
			//组成员
			while(iter.hasNext()){
				JSONObject obj=JSONObject.fromObject(iter.next());	
				
				String pkGroupMember=obj.get("pkGroupMember")==null?null:obj.getString("pkGroupMember");
				String isdelete=obj.get("isdelete")==null?null:obj.getString("isdelete");
				
				if(pkGroupMember!=null&&!pkGroupMember.equals("")){
					if(isdelete!=null&&isdelete.equals("Y")){
						MessageGroupMember oldmessagemember=(MessageGroupMember) basicservice.querybyid(MessageGroupMember.class, Long.parseLong(pkGroupMember));
						oldmessagemember.setDr((short)1);
						oldmessagemember.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						updatelist.add(oldmessagemember);
						delnum++;
					}
				}else{
				String membername=obj.getString("membername");
				String membertype=MessageType.SENDMESSAGE;
				String pkMember=obj.getString("pkMember");
				String memberphone=obj.getString("memberphone");
				MessageGroupMember newmember=new MessageGroupMember();
				newmember.setDr((short)0);
				newmember.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				newmember.setMembername(membername);
				newmember.setMemberphone(memberphone);
				newmember.setMembertype(membertype);
				newmember.setPkMember(Long.parseLong(pkMember));
				newmember.setPkGroup(Long.parseLong(pkGroup));
				
				addlist.add(newmember);
				}
							
			}
			
            group.setGroupnum(arr.size()-delnum);
			
			updatelist.add(group);
			
			//批量保存组会员
             basicservice.batchoperate(addlist, updatelist);
             
             outputstr("", "维护沟通组成功", true, null);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
        output(response, pojo);	
	}
	
	
	/**
	 * 查询讨论组具体内容
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querygroupbyid")
	public void querygroupbyid(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			
			String pkGroup=obj.getString("pkGroup");
			
			MessageGroup group=(MessageGroup) basicservice.querybyid(MessageGroup.class, Long.parseLong(pkGroup));
			
			JSONObject returnobj=JSONObject.fromObject(group);
			
			
			ArrayList<Object> groupmembers=(ArrayList<Object>) basicservice.query(MessageGroupMember.class, " pkGroup = ? ", Long.parseLong(pkGroup));
			
			returnobj.accumulate("groupmembers", groupmembers.size()>0?JSONArray.fromObject(groupmembers).toString():"");
			
			
			outputstr(returnobj.toString(), "查询讨论组信息成功", true, null);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);
	}

	/**
	 * 查询沟通组
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querygroup")
	public void querygroup(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			  JSONObject obj=RequestUtil.getPostString(request);
			  Integer curpage=obj.get("curpage")==null?null:Integer.parseInt(obj.getString("curpage"));
			  Integer pagesize=obj.get("pagesize")==null?null:Integer.parseInt(obj.getString("pagesize"));
			  
			  
			  ArrayList<Object> alllist=(ArrayList<Object>) basicservice.pageQuery(MessageGroup.class, " IFNULL(dr,0)=0 ", pagesize, curpage);			  
			
			  if(alllist.size()>0){
				  String data=JSONArray.fromObject(alllist).toString();
				  if(curpage!=null&&pagesize!=null){
					  ArrayList<Object> totalist=(ArrayList<Object>) basicservice.pageQuery(MessageGroup.class, " IFNULL(dr,0)=0 ", null, null);			  						 
					  outputstr(data, "查询讨论组成功", true, totalist.size());
				  }else{
					  outputstr(data, "查询讨论组成功", true, null); 
				  }
				 
			  }else{
				  outputstr("", "暂无讨论组", true, null);
			  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			outputexceptionstr();
		}
		output(response, pojo);
		  
	}
	
}
