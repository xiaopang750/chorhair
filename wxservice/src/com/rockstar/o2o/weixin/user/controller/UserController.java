package com.rockstar.o2o.weixin.user.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.weixin.controller.BaseController;
import com.rockstar.o2o.weixin.pojo.WxUser;
import com.rockstar.o2o.weixin.util.DateUtil;
import com.rockstar.o2o.weixin.util.RequestUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	  private static final Logger logger = Logger.getLogger(UserController.class);
	  
	  /**
	   * 根据OpenID获取用户信息
	   * @param request
	   * @param response
	   */
	  @RequestMapping("/getuserinfo")
	  public void getuserinfo(HttpServletRequest request,HttpServletResponse response){
		  try {
			    JSONObject getobj=RequestUtil.getPostString(request);
			    String openId = getobj.getString("openId");
			    //获取AccessToken
		        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

			    StringBuffer sb = null;
			    sb = new StringBuffer().append(this.wxsysConf.getBaseUserinfoUrl()).append("access_token=" + accessToken + 
			      "&openid=" + openId + "&lang=zh_CN");

			    String json = this.httpservice.sendGetRequset(sb.toString());
			    if ((json != null) && (!json.equals(""))) {
			       JSONObject obj=JSONObject.fromObject(json);
			       String errmsg = (String)obj.get("errmsg");
			      if (errmsg != null) {
			        if ((errmsg.equals("access_token expired")) || 
			          (errmsg.equals("invalid credential"))) {
			          accessToken = accesstokenhandle.applyAccessToken();
			            sb = new StringBuffer().append(this.wxsysConf.getBaseUserinfoUrl()).append("access_token=" + accessToken + 
			  			      "&openid=" + openId + "&lang=zh_CN");
			            json = this.httpservice.sendGetRequset(sb.toString());
			            obj=JSONObject.fromObject(json);
			            outputstr(json, "查询用户信息成功", true);
			        } else{
			        	outputstr("", "查询用户信息失败,errmsg is :"+ errmsg, false);
			        }
			      }else{
			    	  outputstr(json, "查询用户信息成功", true);
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
	   * 获取用户列表
	   * @param request
	   * @param response
	   */
	@RequestMapping("/getuserlist")
	  public void getuserlist(HttpServletRequest request,HttpServletResponse response){
		  try {
			    JSONObject getobj=RequestUtil.getPostString(request);
			    String nextOpenId =(String)getobj.get("nextOpenId");
			    //获取AccessToken
		        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

		        StringBuffer sb = new StringBuffer().append(wxsysConf.getBaseSubscribeUserListUrl()).append(
		          "access_token=").append(accessToken);

			    if ((nextOpenId != null) && (!nextOpenId.equals(""))) {
			        logger.debug("关注用户多于10000条，需要多次取。");
			        sb.append("&next_openid=").append(nextOpenId);
			      }
			    
			    String json = this.httpservice.sendGetRequset(sb.toString());
			    if ((json != null) && (!json.equals(""))) {
			       JSONObject obj=JSONObject.fromObject(json);
			       String errmsg = (String)obj.get("errmsg");
			      if (errmsg != null) {
			        if ((errmsg.equals("access_token expired")) || 
			          (errmsg.equals("invalid credential"))) {
			          accessToken = accesstokenhandle.applyAccessToken();
			            sb =  new StringBuffer().append(wxsysConf.getBaseSubscribeUserListUrl()).append(
				          "access_token=").append(accessToken);

					    if ((nextOpenId != null) && (!nextOpenId.equals(""))) {
					        logger.debug("关注用户多于10000条，需要多次取。");
					        sb.append("&next_openid=").append(nextOpenId);
					      };
			            json = this.httpservice.sendGetRequset(sb.toString());
			              obj=JSONObject.fromObject(json);
				    	   JSONObject wxobj=JSONObject.fromObject(obj).getJSONObject("data");
				    	   final JSONArray openid=(JSONArray)wxobj.get("openid");
				            	ArrayList<WxUser> userlist=new ArrayList<WxUser>();
					            for(int i=0;i<openid.size();i++){
					            	if(basicservice.query(WxUser.class, " wxId = ? ", openid.get(i).toString()).size()<=0){
					            		WxUser user=new WxUser();
					            		user.setWxId(openid.get(i).toString());
					            		user.setAccountId(account_id);
					            		user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					            		user.setDr((short)0);
					            		userlist.add(user);
					            	}
					            }
					            //批量插入会员信息
					            if(userlist.size()>0){
					            	basicservice.batchoperate(userlist, null);
					            }
					            
					    	    //异步消息处理
							    new Thread(){
							    	public void run(){
							    		getuserinfo(openid);
							    	}
							    }.start();
			            
			            outputstr(json, "查询用户列表成功", true);
			        } else{
			        	outputstr("", "查询用户列表失败,errmsg is :"+ errmsg, false);
			        }
			      }else{
			    	   JSONObject wxobj=JSONObject.fromObject(obj).getJSONObject("data");
			            final JSONArray openid=(JSONArray)wxobj.get("openid");
			            	ArrayList<WxUser> userlist=new ArrayList<WxUser>();
				            for(int i=0;i<openid.size();i++){
				            	if(basicservice.query(WxUser.class, " wxId = ? ", openid.get(i).toString()).size()<=0){
				            		WxUser user=new WxUser();
				            		user.setWxId(openid.get(i).toString());
				            		user.setAccountId(account_id);
				            		user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				            		user.setDr((short)0);
				            		userlist.add(user);
				            	}
				            }
				            //批量插入会员信息
				            if(userlist.size()>0){
				            	basicservice.batchoperate(userlist, null);
				            }
			            
				    	    //异步消息处理
						    new Thread(){
						    	public void run(){
						    		getuserinfo(openid);
						    	}
						    }.start();
						    
			    	  outputstr(json, "查询用户列表成功", true);
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
	   * 获取关注会员信息
	   * @param request
	   * @param response
	   */
	  public void getuserinfo(JSONArray arr){
		  try {
			  
			  ArrayList<WxUser> addlist=new ArrayList<WxUser>();
			  ArrayList<WxUser> updatelist=new ArrayList<WxUser>();
			   for(int i=0;i<arr.size();i++){
			    String openId = arr.get(i).toString();
			    //获取AccessToken
		        String accessToken=accesstokenhandle.GetFirstAccessToken().equals("")?accesstokenhandle.applyAccessToken():accesstokenhandle.GetFirstAccessToken();

			    StringBuffer sb = null;
			    sb = new StringBuffer().append(this.wxsysConf.getBaseUserinfoUrl()).append("access_token=" + accessToken + 
			      "&openid=" + openId + "&lang=zh_CN");

			    String json = this.httpservice.sendGetRequset(sb.toString());
			    if ((json != null) && (!json.equals(""))) {
			       JSONObject userobj=JSONObject.fromObject(json);
			       String errmsg = (String)userobj.get("errmsg");
			      if (errmsg != null) {
			        if ((errmsg.equals("access_token expired")) || 
			          (errmsg.equals("invalid credential"))) {
			          accessToken = accesstokenhandle.applyAccessToken();
			            sb = new StringBuffer().append(this.wxsysConf.getBaseUserinfoUrl()).append("access_token=" + accessToken + 
			  			      "&openid=" + openId + "&lang=zh_CN");
			            json = this.httpservice.sendGetRequset(sb.toString());
			            userobj=JSONObject.fromObject(json);
			            WxUser user=updateuser(openId, userobj);
			            if(user!=null){
			            if(user.getPkUser()!=null&&!user.getPkUser().equals("")){
			            	updatelist.add(user);
			            }else{
			            	addlist.add(user);
			            }
			            }
			          } 
			       }else{
			    	    WxUser user=updateuser(openId, userobj);
			    	    if(user!=null){

                         if(user.getPkUser()!=null&&!user.getPkUser().equals("")){
                        	 updatelist.add(user);
			            }else{
			            	addlist.add(user);
			            }
			    	    	
			    	    }
			         }
			      }
			   }
			   
			     //批量更新会员信息
			     basicservice.batchoperate(addlist, updatelist);
		   } catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		   }
	  }
	  
	  /**
	   * 更新信息
	   */
	  public WxUser updateuser(String openid,JSONObject userobj){
		  try {
			  Integer subscribe=(Integer)userobj.get("subscribe");//1关注,2未关注
		    	String nickname=(String)userobj.get("nickname");
		    	//nickname = nickname.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", "");
		        if(StringUtils.isNotBlank(nickname)){  
		        	nickname = nickname.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");  
		        }
		    	Integer sex=(Integer)userobj.get("sex");//1为男2为女
		    	String language=(String)userobj.get("language");
		    	String city=(String)userobj.get("city");
		    	String province=(String)userobj.get("province");
		    	String country=(String)userobj.get("country");
		    	String headimgurl=(String)userobj.get("headimgurl");
		    	Integer subscribe_time=(Integer)userobj.get("subscribe_time");
		    	String remark=(String)userobj.get("remark");
		    	//判断是否是老会员,是的话更新会员信息,不是得话插入User表中
		    	ArrayList<Object> queryobj=(ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ? ", openid);
		        if(queryobj.size()>0){
		        	WxUser getuser=(WxUser) queryobj.get(0);
		        	getuser.setSex(sex.toString());
		        	getuser.setSubscribeFlag(subscribe.toString());
		        	getuser.setNickname(nickname);
		        	getuser.setCity(city);
		        	getuser.setProvince(province);
		        	getuser.setCountry(country);
		        	getuser.setHeadImageUrl(headimgurl);
		        	getuser.setSubscribeTime(subscribe_time.toString());
			    	getuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    	getuser.setDr((short)0);
 
			    	return getuser;
		        }else{				    	
			    	WxUser user=new WxUser();
			    	user.setSex(sex.toString());
			    	user.setWxId(openid);
			    	user.setSubscribeFlag(subscribe.toString());
			    	user.setNickname(nickname);
			    	user.setCity(city);
			    	user.setProvince(province);
			    	user.setCountry(country);
			    	user.setHeadImageUrl(headimgurl);
			    	user.setSubscribeTime(subscribe_time.toString());
			    	user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    	user.setDr((short)0);
			    	
			    	return user;
		        }
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	  }
}
