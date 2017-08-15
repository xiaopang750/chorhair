package com.rockstar.o2o.util.message;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rockstar.o2o.constant.MessageType;
import com.rockstar.o2o.pojo.MessageList;
import com.rockstar.o2o.pojo.MessageRecord;
import com.rockstar.o2o.service.MessageListService;
import com.rockstar.o2o.service.MessageRecordService;
import com.rockstar.o2o.util.DateUtil;

/**
 * 消息接口
 * @author xc
 * 短信消息，普通会话消息
 */
public class MessageUtil {

	private static String content="";//消息内容
	private static String messagegroup="";//消息者归属分组
	private static String messagetype="";//消息类型		
	private static String sender="ROCKSTAR";//发送方
	private static String receiver="";//接收方
	private static String randomcode="";//验证码
	private static String pkShop="";//店铺
	private static String RRSULTSUCCESS="Y";//成功
	private static ShortMessageUtil util;

	//消息发送及保存接口
	public  static String execute(JSONObject data,MessageRecordService messageservice,MessageListService messagelistService) {
		// TODO Auto-generated method stub
		try {
			 content=data.getString("content");//消息内容
			 messagegroup=data.getString("usergroup");//消息者归属分组
			 pkShop=data.get("pkShop")==null?"":data.getString("pkShop");//店铺
			 messagetype=data.getString("messagetype");//消息类型	
			// String sendgroup=data.get("sendgroup")==null?"shop":data.getString("sendgroup");//消息发送方，店铺和平台
			 sender="ROCKSTAR";//发送方
			 receiver=data.get("receiver")==null?data.getString("cellphone"):data.getString("receiver");//接收方
			 util = new ShortMessageUtil();
			//发送的是短信验证码消息
			if(messagetype.equals(MessageType.TYPE_ONE)||messagetype.equals(MessageType.TYPE_TWO)||messagetype.equals(MessageType.TYPE_THREE)
					||messagetype.equals(MessageType.WXBIND)){
				  randomcode=data.getString("code");		
				  String result=util.mt(receiver, content,"", "", "");
				  if(!result.equalsIgnoreCase("")){
					    MessageRecord record=createbean();
					    if(!messagetype.equals(MessageType.TYPE_THREE)){
                            //将原来的验证码失效
					        messageservice.UpdateOldMessage(receiver, messagegroup, messagetype);
					    }					   
	                        //保存验证码
				        messageservice.save(record);
				        return "success";
				    }else{
					   return "fail";
				   }
			}else if(messagetype.equals(MessageType.TYPE_FOUR)){
				    //普通会话消息
				    sender=data.getString("sender");
				    
				    MessageRecord record=createbean();
				    //保存消息记录
			        messageservice.save(record);
		
		  	        return "success";
		  	        
			       }else if(messagetype.equals(MessageType.TYPE_PLAT)){
			    	      //平台推送的消息
			    		  ArrayList<MessageRecord> recordlist=new ArrayList<MessageRecord>();
			    	      if(receiver.contains(",")){
			    		  String[] allreceiver= receiver.split(",");			    	
			    		        //保存消息列表
			    		        MessageList  message=new MessageList();
			    		        message.setSender(sender);
			    		        message.setMessagetype(messagetype);
			    		        message.setReceiver(receiver);
			    		        message.setMessagegroup(messagegroup);
			    		        message.setLastmessage(content);
			    		        message.setSendtype("HAND");
			    		        message.setPkShop(pkShop.equals("")?null:Long.parseLong(pkShop));
			    		        message.setMessagestatus("001");
			    		        message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    		        message.setDr((short)0);
			    		        message=messagelistService.save(message);
			    		        			    		        
			    				for(int j=0;j<allreceiver.length;j++){
					    			    MessageRecord record=new MessageRecord();					    			 
					    		        record.setSender(sender);
					    		        record.setPkMessagelist(message.getPkMessagelist());
					    		        record.setSendtime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					    		        record.setSendresult(RRSULTSUCCESS);
					    		        record.setContent(content);
					    		        record.setMessagestatus("001");
					    		        record.setReceiver(allreceiver[j]);   
					    		        record.setMessagegroup(messagegroup);
					    		        record.setMessagetype(messagetype);
					    		        record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					    		        record.setDr((short)0);
					    		        recordlist.add(record);  					    		        					    		       
					    		 }	
			    			
			    	     }else{
			    	    	  //保存消息列表
			    		        MessageList  message=new MessageList();
			    		        message.setSender(sender);
			    		        message.setMessagetype(messagetype);
			    		        message.setReceiver(receiver);
			    		        message.setLastmessage(content);
			    		        message.setSendtype("HAND");
			    		        message.setPkShop(pkShop.equals("")?null:Long.parseLong(pkShop));
			    		        message.setMessagegroup(messagegroup);
			    		        message.setMessagestatus("001");
			    		        message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    		        
			    		        message=messagelistService.save(message);
			    		        //保存消息明细
			    	    	    MessageRecord record=createbean();
			    	    	    record.setPkMessagelist(message.getPkMessagelist());			    	    	    
			    	    	    recordlist.add(record);			    	    	    			    	    	  			    		        
			    	      }
			    	    if(recordlist.size()>0){
			    	      //批量保存消息记录
			    		   messageservice.savelist(recordlist);
			    	    }
			    	    return "success";
			         }
			     } catch (Exception e) {
				  return  "fail";
			  }
		     return "fail";
	}
	
	
	/**
	 * 生成消息保存类
	 * @return ShortmessageRecord
	 */
	public static MessageRecord createbean(){		
	    MessageRecord record=new MessageRecord();
        record.setSender(sender);
        record.setSendtime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        record.setSendresult(RRSULTSUCCESS);
        record.setContent(content);
        record.setReceiver(receiver);
        record.setMessagestatus("001");
        record.setCode(randomcode);   
        record.setMessagegroup(messagegroup);
        record.setMessagetype(messagetype);
        record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        record.setDr((short)0);
        return record;
	}
	

   /**
    * 两个人之间的会话记录
    * @param data
    * @param messageservice
    * @return
    */
	@SuppressWarnings("rawtypes")
	public static String search(JSONObject data,MessageRecordService messageservice) {
		// TODO Auto-generated method stub
		try {
			   String sender=data.getString("sender");
			   String receiver=data.getString("receiver");
			   String usergroup=data.getString("usergroup");
			   ArrayList list=(ArrayList) messageservice.querybysenderandreceiver(sender, receiver, usergroup);
			  if(list.size()>0){
				return  JSONArray.fromObject(list).toString();
			   }else{			  
		         return "null";
			     }
			   } catch (Exception e) {
			   return  "fail";
			  }
	  }


    /**
     * 当前会话人所有的会话人列表
     * @param data
     * @param messageservice
     * @return
     */
	@SuppressWarnings("rawtypes")
	public static String searchlist(JSONObject data,MessageRecordService messageservice) {
		// TODO Auto-generated method stub
		try {
			   String sender=data.getString("sender");
			   String receiver=data.getString("receiver");
			   String usergroup=data.getString("usergroup");
			   ArrayList list=(ArrayList) messageservice.querybysender(sender, receiver, usergroup);
			  if(list.size()>0){
				return  JSONArray.fromObject(list).toString();
			  }else{			  
		         return "null";
			     }
			   } catch (Exception e) {
			   return  "fail";
			  }
	  }
}