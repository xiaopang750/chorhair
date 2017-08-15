package com.rockstar.o2o.controller.weixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.MessageType;
import com.rockstar.o2o.constant.UserFrom;
import com.rockstar.o2o.constant.UserGroup;
import com.rockstar.o2o.constant.VerificationMode;
import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.MessageRecord;
import com.rockstar.o2o.pojo.UserList;
import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.pojo.WxUser;
import com.rockstar.o2o.util.CodeUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RedisUtils;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.TemplateUtil;
import com.rockstar.o2o.util.des.EncodeUtil;
import com.rockstar.o2o.util.message.MessageUtil;

@Controller
@RequestMapping("/weixin")
public class WeiXinController extends BaseController{

	/**
	 * 微信绑定老会员
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/bind")
	public void bind(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String account_id=obj.getString("accountid");
			String openId=obj.getString("openid");
			String membername=(String)obj.get("membername");
			String cellphone=obj.getString("cellphone");
			String usergroup=obj.getString("usergroup");
			String code=obj.getString("code");	
		
	        MessageRecord record=messageservice.QueryBysql(cellphone, usergroup, MessageType.WXBIND,code);
			 if(record!=null){
		        	String sendtime=record.getSendtime();
		        	String nowtime=DateUtil.getCurrDate(DateUtil.FORMAT_ONE);
		        	long diff=DateUtil.getSecond(sendtime, nowtime);
		            if(diff>60*5){
		    			outputstr("", "验证码已失效,请重新获取", false,null);
		            }else{			            	
		            	ArrayList<Object> users=(ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ?  ", openId);
		            	if(users.size()>0){
		            		
	    				ArrayList<Object> addlist=new ArrayList<Object>();
	    				ArrayList<Object> updatelist=new ArrayList<Object>();
	    				
		            	WxUser wxuser=(WxUser) users.get(0);
		            	UserList user=userlistService.findbycellandgroup(cellphone, usergroup);		
		    			if(user!=null){
		    				ArrayList<Object> object = (ArrayList<Object>) basicservice.query(UserVerificationmode.class, " openid = ? ", openId);				
		    				if(object.size()>0){
		    					UserVerificationmode mode=(UserVerificationmode) object.get(0);
		    					UserList getuser=userlistService.getUserById(mode.getPkUser());
		    					if(getuser.getCellphone().equalsIgnoreCase(cellphone)){
		    						JSONObject returnobj=new JSONObject();
									returnobj.accumulate("pkUser", EncodeUtil.encode("wxcustomer"+":"+user.getPkUser().toString()));
		    						outputstr(returnobj.toString(), "已经绑定过,无需重复绑定", false, null);
		    					}else{
		    						outputstr("", "该微信号已经绑定其他手机", false, null);
		    					}
		    				}else{

		    					UserVerificationmode newmode=new UserVerificationmode();
								newmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								newmode.setDr((short)0);
								newmode.setVerificationType(VerificationMode.MODE_TWO[0]);
								newmode.setOpenid(openId);
								newmode.setPkUser(user.getPkUser());
								
								addlist.add(newmode);

								//判断是否在店铺中已经存在该手机号，存在的话则pkUser赋值
								ArrayList<Object> infos=(ArrayList<Object>) basicservice.query(CustomerInfo.class, " cellphone = ? ", cellphone);
				    			if(infos.size()>0){
				    				
				    				for(Object info:infos){
				    					CustomerInfo oldinfo=(CustomerInfo) info;
				    					oldinfo.setPkUser(user.getPkUser());
				    					updatelist.add(oldinfo);
				    				}
				    			
				    			}
				    			
				    			basicservice.batchoperate(addlist, updatelist);
				    			
								JSONObject returnobj=new JSONObject();
								returnobj.accumulate("pkUser", EncodeUtil.encode("wxcustomer"+":"+user.getPkUser().toString()));
								outputstr(returnobj.toString(), "绑定成功", true,null); 
		    				}
		    			}else{
		    				UserList newuser=new UserList();
		    				newuser.setCellphone(cellphone);
		    				newuser.setUsergroup(UserGroup.GROUP_ONE[0]);
		    				newuser.setDr((short)0);
		    				newuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    				newuser.setNickname(wxuser.getNickname());
		    				newuser.setUsername(wxuser.getNickname());
		    				newuser.setCity(wxuser.getCity());
		    				newuser.setProvince(wxuser.getProvince());
		    				newuser.setSex(wxuser.getSex());
		    				newuser.setUserfrom(UserFrom.WEIXIN[0]);
		    				newuser.setHeadurl(wxuser.getHeadImageUrl());
		    				newuser.setHeadshorturl(wxuser.getHeadImageUrl());
		    				Object pk=basicservice.save(newuser);
		    				
	    					UserVerificationmode newmode=new UserVerificationmode();
							newmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							newmode.setDr((short)0);
							newmode.setVerificationType(VerificationMode.MODE_TWO[0]);
							newmode.setOpenid(openId);
							newmode.setPkUser(Long.parseLong(pk.toString()));
							addlist.add(newmode);
		
							//判断是否在店铺中已经存在该手机号，存在的话则pkUser赋值
							ArrayList<Object> infos=(ArrayList<Object>) basicservice.query(CustomerInfo.class, " cellphone = ? ", cellphone);
			    			if(infos.size()>0){
			    				for(Object info:infos){
			    					CustomerInfo oldinfo=(CustomerInfo) info;
			    					oldinfo.setPkUser(Long.parseLong(pk.toString()));
			    					updatelist.add(oldinfo);
			    				}
			    			
			    			}
			    			
			    			basicservice.batchoperate(addlist, updatelist);
			    			
							JSONObject returnobj=new JSONObject();
							returnobj.accumulate("pkUser", EncodeUtil.encode("wxcustomer"+":"+pk.toString()));						
			    			outputstr(returnobj.toString(), "绑定成功", true,null); 
		    			}
		               }else{
		            		outputstr("", "您输入的信息不正确",false,null); 
		               }
		            }
		 
		        }else{
					outputstr("", "验证码输入有误",false,null);
		        }
			 
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		
		}
		
		output(response, pojo);
	}
	
	/**
	 * 获取微信验证码
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/getcode")
	public void getcode(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String accountId=obj.getString("accountid");
			String openId=obj.getString("openid");
			String usergroup=obj.getString("usergroup");
			String cellphone=obj.getString("cellphone");			
			
        	ArrayList<Object> users=(ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ?  ", openId);
        	if(users.size()>0){
        		boolean isredis=true;
        		Integer count=0;
        		try {
        			Object redisobj = RedisUtils.getObject("wx:bind:accountid:"+accountId+":openid:"+openId);
        			count=Integer.parseInt((redisobj==null||redisobj.equals(""))?"0":redisobj.toString());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("redis出错");
					isredis=false;
				}        				
				ArrayList<Object> object = (ArrayList<Object>) basicservice.query(UserVerificationmode.class, " openid = ? ", openId);				
				if(object.size()>0){
					UserVerificationmode mode=(UserVerificationmode) object.get(0);
					UserList getuser=userlistService.getUserById(mode.getPkUser());
					if(getuser.getCellphone().equalsIgnoreCase(cellphone)){
						JSONObject returnobj=new JSONObject();
						returnobj.accumulate("pkUser", EncodeUtil.encode("wxcustomer"+":"+mode.getPkUser().toString()));
						outputstr(returnobj.toString(), "已经绑定过,无需重复绑定", false, null);
					}else{
						outputstr("", "该微信号已经绑定其他手机", false, null);
					 }
				}else{					
					  if(!isredis||(isredis&&count<=2)){
			           String randomcode=CodeUtil.getRandomStr(4);
			      	   HashMap<String, String> resultMap=new  HashMap<String, String>();
			      	   ArrayList<Object> returnobj=(ArrayList<Object>) basicservice.query(WxUser.class, "  wxId = ? ",openId);
			    	   if(returnobj.size()>0){
			    		   resultMap.put("nickname", ((WxUser)returnobj.get(0)).getNickname());   
			    	   }else{
			    		   resultMap.put("nickname", "微信");    
			    	   }
			      	   resultMap.put("code", randomcode);
			           String content=TemplateUtil.invokeTpl(resultMap, "template/message", "wxbind.ftl");
			    	   obj.accumulate("code", randomcode);
			    	   obj.accumulate("messagetype", MessageType.WXBIND);
			    	   obj.accumulate("content", content);
			    	   String result=MessageUtil.execute(obj,messageservice,messagelistService);
			           if(result.equals("success")){
			        		try {
			        			Object redisobj = RedisUtils.getObject("wx:bind:accountid:"+accountId+":openid:"+openId);
			        			Integer newcount=Integer.parseInt((redisobj==null||redisobj.equals(""))?"0":redisobj.toString())+1;
			        			RedisUtils.delKey("wx:bind:accountid:"+accountId+":openid:"+openId);
			        			RedisUtils.setKeyByTime("wx:bind:accountid:"+accountId+":openid:"+openId, newcount, 60*60*24);
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("redis出错");
							}
							
			        	   outputstr("", "短信验证码发送成功", true, null);
			        	   
			           }else{
			        	   outputstr("", "短信验证码发送失败", false, null);	
			            }
					  }else{
						  outputstr("", "24小时内只允许获取3次验证码", false, null);	
					  }
				   }
        	}else{
				  outputstr("", "您输入的信息不正确",false,null); 
			   }
	       } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();		
		  }		
		output(response, pojo);
	}
	
	/**
	 * 微信授权登录
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public void weixinlogin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
		response.setContentType("text/html;charset=utf-8");
        try {
			response.sendRedirect(new Oauth().openwx());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 微信授权登录后回调
	 * @param model
	 * @return
	 */
	@RequestMapping("/afterlogin")
	public void afterlogin(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {		
		response.setContentType("text/html;charset=utf-8");
        try {
			response.sendRedirect(new Oauth().openwx());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 获取微信信息
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("/userinfo")
	public void userinfo(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		 try {
				JSONObject obj=RequestUtil.getPostString(request);
				String openid=obj.getString("openid");
				String accountid=obj.getString("accountid");
				ArrayList<Object> users=(ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ? and accountId = ? ", openid,accountid);
	       
		       if(users.size()>0){
		    	   JSONObject reobj=new JSONObject();
		    	   WxUser user=(WxUser) users.get(0);
		    	   reobj.accumulate("nickname", user.getNickname());
		    	   reobj.accumulate("headimageurl", user.getHeadImageUrl());
		    	   reobj.accumulate("addr", user.getProvince()+user.getCity());
		    	   ArrayList<Object> olduser=(ArrayList<Object>) basicservice.query(UserList.class, " EXISTS (select 1 from UserVerificationmode  where openid = ? and  verificationType = ? and pkUser = w.pkUser )", openid,"WEIXIN");
		    	   if(olduser.size()>0){
		    		reobj.accumulate("cellphone", ((UserList)olduser.get(0)).getCellphone());	   
		    	   }
		    	   outputstr(reobj.toString(), "查询用户信息成功", true, null);
		       }else{
		    	   outputstr("", "暂无用户信息", false, null);  
		       }
		 } catch (Exception e) {
	           dealexception(e);
	           outputexceptionstr();
	        }
	        output(response, pojo);
	}
}
