package com.rockstar.o2o.controller.weixin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.WxAutoreply;
import com.rockstar.o2o.pojo.WxAutoreplyKeyword;
import com.rockstar.o2o.pojo.WxAutoreplyMessage;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RedisUtils;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.vo.WxAutoreplyVo;
/**
 * 微信自动回复
 * @author hxx
 *
 */
@Controller
@RequestMapping("wxautoreply")
public class WxAutoreplyController extends BaseController {
	
	/**
	 * 获取微信自动回复信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("getautoreply")
	public void getAutoreplyInfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try{
			JSONObject obj=RequestUtil.getPostString(request); 
			//获取自动回复类型
			String replyType=obj.getString("replyType");
			ArrayList<WxAutoreply> reply=(ArrayList<WxAutoreply>) wxAutoreplyService.getInfoByReplytype(replyType);
			if(reply!=null){
				String data="";
				List<Object> list =new ArrayList<Object>();
				if(replyType.equals("3")){
					for(WxAutoreply wx:reply){
						WxAutoreplyVo vo =new WxAutoreplyVo();
						vo.setAutoreplyId(wx.getPkAutoreplyId());
						vo.setReplyType(wx.getReplyType());
						vo.setRuleName(wx.getRuleName());
						vo.setReplyMode(wx.getReplyMode());
						ArrayList<WxAutoreplyKeyword> keywordData=(ArrayList<WxAutoreplyKeyword>) wxAutoreplyKeywordService.getKeywordInfo(wx.getPkAutoreplyId());
						vo.setKeywordData(keywordData);
						ArrayList<WxAutoreplyMessage> messageData=(ArrayList<WxAutoreplyMessage>) wxAutoreplyMessageService.getMessageInfo(wx.getPkAutoreplyId());
						vo.setMessageData(messageData);
						vo.setTs(wx.getTs());
						list.add(vo);
					}
					data=JSONArray.fromObject(list).toString();
				}else{
					data=JSONArray.fromObject(reply.get(0)).toString();
				}
			    outputstr(data, "获取微信自动回复信息成功", true,null);	
		    }else{
		    	outputstr("", "没有对应的搜索内容", true,null);
		    }			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 保存微信自动回复信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("saveautoreply")
	public void saveAutoreply(HttpServletRequest request,HttpServletResponse response,Model model) {
		try{
			JSONObject obj=RequestUtil.getPostString(request); 
			//自动回复类型
			String replyType=obj.getString("replyType");
			
			WxAutoreply wxAutoreply =new WxAutoreply();
			wxAutoreply.setReplyType(replyType);
			
			if(replyType.equals("3")){
				//规则名称
				String ruleName=obj.getString("ruleName");
				//回复模式（ALL代表全部回复，ONE代表随机）
				String replyMode=obj.getString("replyMode");
				//获取关键字数组
				String keywordData=obj.getString("keywordData");
				//获取关键字回复内容数组
				String messageData=obj.getString("messageData");

				wxAutoreply.setRuleName(ruleName);
				wxAutoreply.setReplyMode(replyMode);
				wxAutoreply.setDr((short)0);
				wxAutoreply.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));			
				WxAutoreply reply=wxAutoreplyService.save(wxAutoreply);
				
				JSONArray jsonArray=JSONArray.fromObject(keywordData);
				int iSize = jsonArray.size();
				System.out.println("Size:" + iSize);
				for (int i = 0; i < iSize; i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					WxAutoreplyKeyword autoKeyword= new WxAutoreplyKeyword();
					autoKeyword.setPkAutoreplyId(reply.getPkAutoreplyId());
					autoKeyword.setKeyword((String) jsonObj.get("keyword"));
					autoKeyword.setMatchMode((String) jsonObj.get("matchMode"));
					autoKeyword.setDr((short)0);
					autoKeyword.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					wxAutoreplyKeywordService.saveKeywordInfo(autoKeyword);
				}
				
				JSONArray array = JSONArray.fromObject(messageData);
				int size = jsonArray.size();
				System.out.println("Size:" + iSize);
				for (int i = 0; i < size; i++) {
					JSONObject json = array.getJSONObject(i);
					WxAutoreplyMessage messageInfo= new WxAutoreplyMessage();
					messageInfo.setPkAutoreplyId(reply.getPkAutoreplyId());
					messageInfo.setMsgType((String) json.get("msgType"));
					messageInfo.setContent((String) json.get("content"));
					messageInfo.setDr((short)0);
					messageInfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					wxAutoreplyMessageService.saveMessageInfo(messageInfo);
				}
				outputstr("", "保存微信自动回复信息成功", true,null);
			}else{
				//消息类型(   TEXT :文字 , IMG:图片 , VOICE：语音 ， VIDEO:视频   ,NEWS:图文
				String msgType=obj.getString("msgType");
				//文本是：内容；图片、语音、视频类型是：路径
				String content=obj.getString("content");
				wxAutoreply.setMsgType(msgType);
				wxAutoreply.setContent(content);
				wxAutoreply.setDr((short)0);
				wxAutoreply.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));			
				WxAutoreply reply=wxAutoreplyService.save(wxAutoreply);
				if(reply!=null){
					//String data=JSONArray.fromObject(reply).toString();
				    outputstr("", "保存微信自动回复信息成功", true,null);	
			    }else{
			    	outputstr("", "保存微信自动回复信息失败", false,null);
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
	 * 更新微信自动回复信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("updateautoreply")
	public void updateAutoreply(HttpServletRequest request,HttpServletResponse response,Model model) {
		try{
			JSONObject obj=RequestUtil.getPostString(request); 
			//自动回复类型
			String replyType=obj.getString("replyType");
			String autoreplyId=obj.getString("autoreplyId");
			
			WxAutoreply wxAutoreply =wxAutoreplyService.getInfoById(Long.parseLong(autoreplyId));
			if(replyType.equals("3")){
				//规则名称
				String ruleName=obj.getString("ruleName");
				//回复模式（ALL代表全部回复，ONE代表随机）
				String replyMode=obj.getString("replyMode");
				//获取关键字数组
				String keywordData=obj.getString("keywordData");
				//获取关键字回复内容数组
				String messageData=obj.getString("messageData");

				wxAutoreply.setRuleName(ruleName);
				wxAutoreply.setReplyMode(replyMode);
				wxAutoreply.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));			
				wxAutoreplyService.update(wxAutoreply);
				
				JSONArray jsonArray=JSONArray.fromObject(keywordData);
				int iSize = jsonArray.size();
				for (int i = 0; i < iSize; i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					WxAutoreplyKeyword autoKeyword= wxAutoreplyKeywordService.getInfoById((String)jsonObj.get("pkKeywordId"));
					autoKeyword.setKeyword((String) jsonObj.get("keyword"));
					autoKeyword.setMatchMode((String) jsonObj.get("matchMode"));
					autoKeyword.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					wxAutoreplyKeywordService.update(autoKeyword);
				}
				
				JSONArray array = JSONArray.fromObject(messageData);
				int size = array.size();
				for (int i = 0; i < size; i++) {
					JSONObject json = array.getJSONObject(i);
					WxAutoreplyMessage messageInfo= wxAutoreplyMessageService.getInfoById((String)json.get("pkReplymsgId"));
					messageInfo.setMsgType((String) json.get("msgType"));
					messageInfo.setContent((String) json.get("content"));
					messageInfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					wxAutoreplyMessageService.update(messageInfo);
				}
				outputstr("", "更新微信自动回复信息成功", true,null);
			}else{
				//消息类型(   TEXT :文字 , IMG:图片 , VOICE：语音 ， VIDEO:视频   ,NEWS:图文
				String msgType=obj.getString("msgType");
				//文本是：内容；图片、语音、视频类型是：路径
				String content=obj.getString("content");
				if(msgType.equals(wxAutoreply.getMsgType())){
					wxAutoreply.setMsgType(msgType);
					wxAutoreply.setContent(content);
					wxAutoreply.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					int result=wxAutoreplyService.update(wxAutoreply);
					if(result==0){
					    outputstr("", "更新微信自动回复信息成功", true,null);	
				    }else{
				    	outputstr("", "更新微信自动回复信息失败", false,null);
				    }	
				}else{
					wxAutoreplyService.deleteById(Long.parseLong(autoreplyId));
					WxAutoreply autoreply =new WxAutoreply();
					autoreply.setReplyType(replyType);
					autoreply.setMsgType(msgType);
					autoreply.setContent(content);
					autoreply.setDr((short)0);
					autoreply.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));			
					WxAutoreply reply=wxAutoreplyService.save(autoreply);
					if(reply!=null){
					    outputstr("", "保存微信自动回复信息成功", true,null);	
				    }else{
				    	outputstr("", "保存微信自动回复信息失败", false,null);
				    }
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
	 * 删除微信自动回复信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("deleteautoreply")
	public void deleteAutoreply(HttpServletRequest request,HttpServletResponse response,Model model) {
		try{
			JSONObject obj=RequestUtil.getPostString(request);
			String autoreplyId=obj.getString("autoreplyId");
			//自动回复类型
			String replyType=obj.getString("replyType");
			if(replyType.equals("3")){
				ArrayList<WxAutoreplyKeyword> keywordData=(ArrayList<WxAutoreplyKeyword>) wxAutoreplyKeywordService.getKeywordInfo(Long.parseLong(autoreplyId));
				for(WxAutoreplyKeyword k: keywordData){
					wxAutoreplyKeywordService.deleteById(k.getPkKeywordId());
				}
				ArrayList<WxAutoreplyMessage> messageData=(ArrayList<WxAutoreplyMessage>) wxAutoreplyMessageService.getMessageInfo(Long.parseLong(autoreplyId));
				for(WxAutoreplyMessage m: messageData){
					wxAutoreplyMessageService.deleteById(m.getPkReplymsgId());
				}
				//关键字回复删除
				wxAutoreplyService.deleteById(Long.parseLong(autoreplyId));
				outputstr("", "删除微信自动回复信息成功", true,null);
			}else{
				wxAutoreplyService.deleteById(Long.parseLong(autoreplyId));
				outputstr("", "删除微信自动回复信息成功", true,null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 根据客户发送的关键字返回对应信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("getcontentbykeyword")
	public void getContentByKeyword(HttpServletRequest request,HttpServletResponse response,Model model) {
		try{
			JSONObject obj=RequestUtil.getPostString(request);
			String keyword=obj.getString("keyword");
			String openId=obj.getString("openid");
			WxAutoreplyKeyword autoKeyword=wxAutoreplyKeywordService.getInfoByKeyword(keyword);
			if(autoKeyword !=null){
				WxAutoreply wxAutoreply =wxAutoreplyService.getInfoById(autoKeyword.getPkAutoreplyId());
				if(wxAutoreply.getReplyMode().equals("ALL")){
					ArrayList<WxAutoreplyMessage> messageData=(ArrayList<WxAutoreplyMessage>) wxAutoreplyMessageService.getMessageInfo(wxAutoreply.getPkAutoreplyId());
					if(messageData !=null){
						String data=JSONArray.fromObject(messageData).toString();
						outputstr(data, "发送微信自动回复信息成功", true,null);
					}else{						
						 boolean isredis=true;
						 String content="";
						 try {
							 content=(String)RedisUtils.getObject("wx:"+openId+":keywordreply:");
						 } catch (Exception e) {
							// TODO: handle exception
							 isredis=false;
						 }
						 if(isredis&&content!=null&&!content.equals("")){
			 		    	  JSONObject newobj=new JSONObject();		    		    	 
		    		    	  newobj.accumulate("content", content);
		    		    	  outputstr(newobj.toString(), "发送微信自动回复信息成功", true,null);
						 }else{
							 ArrayList<WxAutoreply> reply=(ArrayList<WxAutoreply>) wxAutoreplyService.getInfoByReplytype("2");
							 if(reply!=null){
								 content=reply.get(0).getContent();
								 JSONObject newobj=new JSONObject();		    		    	 
			    		    	 newobj.accumulate("content", content);
								 RedisUtils.setKeyByTime("wx:"+openId+":keywordreply:",content,60*60);
			    		    	 outputstr(newobj.toString(), "发送微信自动回复信息成功", true,null);
							 }
						 }
					}
				}else{
					WxAutoreplyMessage messageData=wxAutoreplyMessageService.getOneMessageInfo(wxAutoreply.getPkAutoreplyId());
					if(messageData !=null){
						String data=JSONArray.fromObject(messageData).toString();
						outputstr(data, "发送微信自动回复信息成功", true,null);
					}else{
						boolean isredis=true;
						 String content="";
						 try {
							 content=(String)RedisUtils.getObject("wx:"+openId+":keywordreply:");
						 } catch (Exception e) {
							// TODO: handle exception
							 isredis=false;
						 }
						 if(isredis&&content!=null&&!content.equals("")){
			 		    	  JSONObject newobj=new JSONObject();		    		    	 
		    		    	  newobj.accumulate("content", content);
		    		    	  outputstr(newobj.toString(), "发送微信自动回复信息成功", true,null);
						 }else{
							 ArrayList<WxAutoreply> reply=(ArrayList<WxAutoreply>) wxAutoreplyService.getInfoByReplytype("2");
							 if(reply!=null){
								 content=reply.get(0).getContent();
								 JSONObject newobj=new JSONObject();		    		    	 
			    		    	 newobj.accumulate("content", content);
								 RedisUtils.setKeyByTime("wx:"+openId+":keywordreply:",content,60*60);
			    		    	 outputstr(newobj.toString(), "发送微信自动回复信息成功", true,null);
							 }
						 }
					}
				}
			}else{
				boolean isredis=true;
				 String content="";
				 try {
					 content=(String)RedisUtils.getObject("wx:"+openId+":keywordreply:");
				 } catch (Exception e) {
					// TODO: handle exception
					 isredis=false;
				 }
				 if(isredis&&content!=null&&!content.equals("")){
	 		    	  JSONObject newobj=new JSONObject();		    		    	 
	 		    	  newobj.accumulate("content", content);
	 		    	  outputstr(newobj.toString(), "发送微信自动回复信息成功", true,null);
				 }else{
					 ArrayList<WxAutoreply> reply=(ArrayList<WxAutoreply>) wxAutoreplyService.getInfoByReplytype("2");
					 if(reply!=null){
						 content=reply.get(0).getContent();
						 JSONObject newobj=new JSONObject();		    		    	 
	    		    	 newobj.accumulate("content", content);
						 RedisUtils.setKeyByTime("wx:"+openId+":keywordreply:",content,60*60);
	    		    	 outputstr(newobj.toString(), "发送微信自动回复信息成功", true,null);
					 }
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
