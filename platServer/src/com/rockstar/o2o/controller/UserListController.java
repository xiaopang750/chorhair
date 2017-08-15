package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.MessageType;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.constant.VerificationMode;
import com.rockstar.o2o.pojo.ClientInfo;
import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.pojo.FairerSkill;
import com.rockstar.o2o.pojo.MessageRecord;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.pojo.UserList;
import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.util.CodeUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MD5Util;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.ReturnPojo;
import com.rockstar.o2o.util.TemplateUtil;
import com.rockstar.o2o.util.des.EncodeUtil;
import com.rockstar.o2o.util.message.MessageUtil;

/**
 * 用户controller
 * @author xc
 *
 */
@Controller
@RequestMapping("/user")
public class UserListController extends BaseController{
    
    public UserListController(){
    	 pojo=new ReturnPojo();
    }
    
    /**
     * 修改用户信息
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping("/editinfo")
	public void editinfo(HttpServletRequest request,HttpServletResponse response,Model model) {   	
    	 try {
			 JSONObject obj=RequestUtil.getPostString(request);
			 String pkUser=obj.getString("pkUser");
			 String nickname=obj.get("nickname")==null?"":obj.getString("nickname");//昵称
			 String headurl=obj.get("headurl")==null?"":obj.getString("headurl");//头像
			 String headshorturl=obj.get("headshorturl")==null?"":obj.getString("headshorturl");//头像缩略图
			 
			 UserList user=userlistService.getUserById(Long.parseLong(pkUser));
			 if(user!=null){
				 if(!nickname.equals("")){
					 user.setNickname(nickname);
				 }
				 if(!headurl.equals("")){
					 user.setHeadurl(headurl);
					 user.setHeadshorturl(headshorturl);
						
				 }			
				 int result=userlistService.updateUser(user);
				 
				 if(result==0){
					 outputstr("", "修改成功", true,null);
					 if(!headurl.equals("")){				
					    //保存美丽纪录
						UserFile file=new UserFile();
						file.setBemodel("APP头像修改");
						file.setFilepath(headurl);
						file.setThumbnail(headshorturl);
						file.setPkUser(Long.parseLong(pkUser));
						//file.setFilesize(FileManegeUtil.getsize("", headurl).getString("size"));
						file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						file.setDr((short)0);
						//file.setFilesize(FileManegeUtil.getsize("", headurl).getString("size"));
						userfileService.save(file);
					 }	
				 }else{
					 outputstr("", "修改失败", false,null);
				 }
			 }else{
				      outputstr("", "无此用户", false,null);
			 }
		 } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		  output(response, pojo);
     }
    
	/**
	 * 用户登录
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public void UserLogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String cellphone=obj.getString("cellphone");
		String usergroup=obj.getString("usergroup");
		String loginpassword=obj.getString("loginpassword");
		String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
		//校验用户的账号密码是否正确
		UserList haveuser=userlistService.login(cellphone, secretpassword,usergroup);
		if(haveuser==null){
			pojo.setMsg("用户名或密码错误");
			pojo.setIssuccess(false);
		}else{
			 if(haveuser.getUserstatus()==null||haveuser.getUserstatus().equals("")
					 ||haveuser.getUserstatus().equals("001")){

			 JSONObject successstr=new JSONObject();
			 successstr.accumulate("cellphone", haveuser.getCellphone());
			 String pk="";
			 if(usergroup.equals(UserGroupObject.GROUP_ONE[0])){
				 pk =EncodeUtil.encode("appcustomer"+":"+haveuser.getPkUser().toString());
			 }else if(usergroup.equals(UserGroupObject.GROUP_TWO[0])){
				 pk =EncodeUtil.encode("appfairer"+":"+haveuser.getPkUser().toString()); 
			 }
			 successstr.accumulate("pkUser", pk);
			 successstr.accumulate("nickname", haveuser.getNickname());
			 successstr.accumulate("username", haveuser.getUsername());

			 outputstr(successstr.toString(), "登录成功", true,null);
			 }else{

			 outputstr("", "您的账户已经禁止使用,请联系客服", false,null);
			 }
		}
		
		} catch (JSONException e) {
			dealexception(e);
            outputexceptionstr();
		}
		   output(response,pojo);
	}
	
	
	/**
	 * 手机号账号验证码验证
	 * @param model
	 * @return
	 */
	@RequestMapping("/phone")
	public void CheckPhone(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		   JSONObject obj=RequestUtil.getPostString(request);
		   String cellphone=obj.getString("receiver");
		   String usergroup=obj.getString("usergroup");
		   obj.accumulate("receiver", cellphone);
		   obj.accumulate("usergroup", usergroup);
		   UserList user=userlistService.findbycellandgroup(cellphone, usergroup);
		   if(user!=null){
   			   outputstr("", "该手机号已经注册过,请直接登录", false,null);   			   
		   }else{
           String randomcode=CodeUtil.getRandomStr(6);
      	   HashMap<String, String> resultMap=new  HashMap<String, String>();
    	   resultMap.put("code", randomcode);
           String content=TemplateUtil.invokeTpl(resultMap, "template/message", "mobilecheck.ftl");
    	   obj.accumulate("code", randomcode);
    	   obj.accumulate("messagetype", MessageType.TYPE_TWO);
    	   obj.accumulate("content", content);
    	   String result=MessageUtil.execute(obj,messageservice,messagelistService);
           if(result.equals("success")){
   			  outputstr("", "发送验证码成功", true,null);   		
           }else{
        	  outputstr("", "发送验证码失败", false,null);   		
            }
		   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dealexception(e);
            outputexceptionstr();
		}
		   output(response,pojo);
	}
	
	/**
	 * 检查手机号账号验证码是否有效,超过一分钟就失效
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkphonecode")
	public void checkphone(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String cellphone=obj.getString("cellphone");
		String messagegroup=obj.getString("usergroup");	
		String code=obj.getString("code");	
        MessageRecord record=messageservice.QueryBysql(cellphone, messagegroup, MessageType.TYPE_TWO,code);
        if(record!=null){
        	String sendtime=record.getSendtime();
        	String nowtime=DateUtil.getCurrDate(DateUtil.FORMAT_ONE);
        	long diff=DateUtil.getSecond(sendtime, nowtime);
            if(diff>60*5){
    			outputstr("", "验证码已失效,请重新获取", false,null);
            }else{
    			outputstr("", "验证成功", true,null);
            }
 
        }else{
			outputstr("", "验证码输入有误",false,null);
        }
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
			
		}
		output(response,pojo);
	}
	
	/**
	 * 忘记密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/forget")
	public void ForgetSecret(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String cellphone=obj.getString("cellphone");
		String messagegroup=obj.getString("usergroup");	     
        UserList user=userlistService.findbycellandgroup(cellphone,messagegroup);
        if(user==null){
			outputstr("", "该手机号码尚未注册", false,null);
        }else{
        	 String randomcode=CodeUtil.getRandomStr(6);
        	 HashMap<String, String> resultMap=new  HashMap<String, String>();
        	 resultMap.put("code", randomcode);
             String content=TemplateUtil.invokeTpl(resultMap, "template/message", "forget.ftl");
      	     obj.accumulate("code", randomcode);
    	     obj.accumulate("messagetype", MessageType.TYPE_ONE);
    	     obj.accumulate("content", content);
    	     String result=MessageUtil.execute(obj,messageservice,messagelistService);
            if(result.equals("success")){
   			    outputstr("", "发送验证码成功", true,null);
            }else{
            	outputstr("", "发送验证码失败", false,null);
             }		
            }
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			  dealexception(e);
              outputexceptionstr();
		  }
			output(response,pojo);
	}
		
	/**
	 * 检查忘记密码验证码是否有效,超过一分钟就失效
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkcode")
	public void checkcode(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String cellphone=obj.getString("cellphone");
		String messagegroup=obj.getString("usergroup");	
		String code=obj.getString("code");	
        MessageRecord record=messageservice.QueryBysql(cellphone, messagegroup, MessageType.TYPE_ONE,code);
        if(record!=null){
        	String sendtime=record.getSendtime();
        	String nowtime=DateUtil.getCurrDate(DateUtil.FORMAT_ONE);
        	long diff=DateUtil.getSecond(sendtime, nowtime);
            if(diff>60*5){
	    		outputstr("", "验证码已失效,请重新获取", false,null);
            }else{
	    		outputstr("", "验证成功", true,null);
            }
 
        }else{
    		outputstr("", "验证码输入有误", false,null);
        }
		
		} catch (Exception e) {
			dealexception(e);
            outputexceptionstr();
        }
		   output(response,pojo);
	}
	
	/**
	 * 忘记密码验证通过后修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/resetpassword")
	public void resetpassword(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String cellphone=obj.getString("cellphone");
		String messagegroup=obj.getString("usergroup");	
		String newpassword=obj.getString("newpassword");	
        UserList user=userlistService.findbycellandgroup(cellphone,messagegroup);
        if(user!=null){
    		String secretoldpassword=MD5Util.MD5Encode(newpassword, "utf-8");
        	user.setLoginpassword(secretoldpassword);
        	int i=userlistService.updateUser(user);
        	if(i==0){
        		outputstr("", "密码重置成功", true,null);
        	}else{
        		outputstr("", "密码重置失败", false,null);
        	}
        }else{
    		outputstr("", "手机号尚未注册", false,null);
        }
		
		} catch (Exception e) {
			dealexception(e);
            outputexceptionstr();  
		}
		output(response,pojo);
	}
	
	
	/**
	 * 修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/editpassword")
	public void EditPassword(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String oldpassword=obj.getString("oldpassword");
		String newpassword=obj.getString("newpassword");	
		String cellphone=obj.getString("cellphone");
		String usergroup=obj.getString("usergroup");
		String secretoldpassword=MD5Util.MD5Encode(oldpassword, "utf-8");
		UserList haveuser=userlistService.login(cellphone, secretoldpassword,usergroup);
		if(haveuser==null){
    		outputstr("", "原密码输入有误", false,null);
		}else{
			String secretnewpassword=MD5Util.MD5Encode(newpassword, "utf-8");
			haveuser.setLoginpassword(secretnewpassword);
			int result=userlistService.updateUser(haveuser);
			if(result==0){
        		outputstr("", "密码修改成功,请重新登录", true,null);
			}else{
        		outputstr("", "密码修改失败", false,null);
			}
		}
		
		} catch (Exception e) {
			dealexception(e);
		    outputexceptionstr();
		}
		
		output(response,pojo);
	}
	
	
	/**
	 * 注册用户,分为第三方绑定注册和手机号注册
	 * 第三方绑定注册的，需要验证手机号是否存在，如果存在，则直接关联已有用户，如果没有则新增用户信息，并且绑定第三方验证方式
	 * 注册的时候还需将设备信息存下，消息推送使用
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public void saveUser(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String openid=obj.get("openid").toString();
		UserList user = new UserList();
		user.setUsergroup(obj.getString("usergroup"));
		user.setNickname(obj.get("nickname")==null?"":obj.getString("nickname"));
		user.setCellphone(obj.getString("cellphone"));	
		String oldpassword=obj.getString("loginpassword");
		String newpaswword=MD5Util.MD5Encode(oldpassword, "utf-8");
		user.setLoginpassword(newpaswword);
		user.setSex(obj.get("sex")==null?"":obj.getString("sex"));
		user.setProvince(obj.get("province")==null?"":obj.getString("province"));
		user.setCity(obj.get("city")==null?"":obj.getString("city"));
		user.setCounty(obj.get("county")==null?"":obj.getString("county"));
		user.setAddr(obj.get("addr")==null?"":obj.getString("addr"));
		user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));		
		user.setDr((short)0);	
		
		//客户端信息
		 String devicetype=obj.get("devicetype")==null?"":obj.getString("devicetype");
		 String devicefactory=obj.get("devicefactory")==null?"":obj.getString("devicefactory");
		 String deviceimei=obj.get("deviceimei")==null?"":obj.getString("deviceimei");
		 String deviceuuid=obj.get("deviceuuid")==null?"":obj.getString("deviceuuid");
		 String deviceimsi=obj.get("deviceimsi")==null?"":obj.getString("deviceimsi");
		 String devicescreenfbl=obj.get("devicescreenfbl")==null?"":obj.getString("devicescreenfbl");
		 String systemname=obj.get("systemname")==null?"":obj.getString("systemname");
		 String systemversion=obj.get("systemversion")==null?"":obj.getString("systemversion");
		 String systemlanguage=obj.get("systemlanguage")==null?"":obj.getString("systemlanguage");
		 String systemfactory=obj.get("systemfactory")==null?"":obj.getString("systemfactory");
		 String token=obj.get("token")==null?"":obj.getString("token");
		 String clientid=obj.get("clientid")==null?"":obj.getString("clientid");
		 String appid=obj.get("appid")==null?"":obj.getString("appid");
		 String appkey=obj.get("appkey")==null?"":obj.getString("appkey");
		
		//OPENID不为空，说明来自第三方登录绑定账号
		if(openid!=null){
		 String type=obj.get("type").toString();
		 if(type.equals("001")){
			 type=VerificationMode.MODE_ONE[0];
		 }else if(type.equals("002")){
			 type=VerificationMode.MODE_THREE[0]; 
		 }			
		 //根据OPENID和类型查询验证方式是否存在
		UserVerificationmode mode=verificationservice.QueryByTwo(openid, type);
		if(mode!=null){
			//查询当前手机号是否已经存在
			UserList olduser=userlistService.findbycellandgroup(obj.getString("cellphone"), obj.getString("usergroup"));
			if(olduser!=null){
				//手机号已存在的话，则更新验证方式表的用户主键
				mode.setPkUser(olduser.getPkUser());
				verificationservice.updateGroup(mode);
	    		JSONObject successobj=new JSONObject();
	    		successobj.accumulate("pkUser", olduser.getPkUser());
	    		successobj.accumulate("cellphone", olduser.getCellphone());
	    		outputstr(successobj.toString(), "信息完善成功", true,null);
			}else{
				user=userlistService.save(user);
				mode.setPkUser(user.getPkUser());
				verificationservice.updateGroup(mode);
	    		JSONObject successobj=new JSONObject();
	    		successobj.accumulate("pkUser", user.getPkUser());
	    		successobj.accumulate("cellphone", user.getCellphone());
	    		outputstr(successobj.toString(), "信息完善成功", true,null);
			}	
		}
		}else{
	    //普通手机号注册
		UserList olduser=userlistService.findbycellandgroup(obj.getString("cellphone"), obj.getString("usergroup"));
		if(olduser!=null){
    		outputstr("", "该手机号已经注册过,请直接登录", false,null);
		}else{
			user=userlistService.save(user);
    		JSONObject successobj=new JSONObject();
    		successobj.accumulate("pkUser", user.getPkUser());
    		successobj.accumulate("cellphone", user.getPkUser());
    		outputstr(successobj.toString(), "注册成功", true,null);
		    }
		  }
		
		//保存客户端设备相关信息
		if(!appid.equals("")){
		ArrayList<ClientInfo> oldinfo=(ArrayList<ClientInfo>) clientinfoservice.FindByuuid(deviceuuid);
		if(oldinfo.size()<=0){
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
		  }
		 }
		} catch (Exception e) {
			dealexception(e);
		    outputexceptionstr();
		}
		
		output(response,pojo);
	}
	
	/**
	 * 修改用户
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public void EditUser(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
		UserList user = new UserList();
		JSONObject obj=RequestUtil.getPostString(request);
		user.setPkUser(Long.parseLong(obj.getString("pkUser")));
		user.setUsername(obj.getString("username"));
		user.setNickname(obj.getString("nickname"));
		user.setCellphone(obj.getString("cellphone"));	
		user.setLoginpassword(obj.getString("loginpassword"));
		user.setSex(obj.getString("cellphone"));
		user.setProvince(obj.getString("province"));
		user.setCity(obj.getString("city"));
		user.setCounty(obj.getString("county"));
		user.setAddr(obj.getString("addr"));
		user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));		
		user.setDr((short)0);			
		userlistService.updateUser(user);
		outputstr("", "用户信息修改成功", true,null);
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response,pojo);
	}
	
	/**
	 * 根据ID，获取一个用户，以json格式输出到页面
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/showById")
	public void getUserById(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {

		JSONObject obj=RequestUtil.getPostString(request);
		String sid=obj.getString("pkUser");
		String usergroup=obj.get("usergroup")==null?"":obj.getString("usergroup");	
		Long id = Long.parseLong(sid);
		UserList user = userlistService.getUserById(id);
		if(user!=null){
	    JSONObject returnobj=new JSONObject();
	    returnobj.accumulate("nickname", user.getNickname());
	    returnobj.accumulate("username", user.getUsername());
	    returnobj.accumulate("cellphone", user.getCellphone());
	    returnobj.accumulate("pkUser", user.getPkUser());
	    returnobj.accumulate("sex", user.getSex());
	    returnobj.accumulate("headurl", user.getHeadurl());
	    returnobj.accumulate("headshorturl", user.getHeadshorturl());
		if(!usergroup.equals("")&&usergroup.equals("002")){						  											
			 	ArrayList<FairerInfo> info=(ArrayList<FairerInfo>) fairerinfoService.QueryByPkuser(sid);
			 	ShopInfo shop=shopinfoService.getShopById(info.get(0).getPkShop());			
				if(info.size()>0){
				returnobj.accumulate("fairercode", info.get(0).getFairercode());
				returnobj.accumulate("rankname", info.get(0).getRankname());
				returnobj.accumulate("accountmoney", info.get(0).getAccountmoney());
				returnobj.accumulate("servicemoney", info.get(0).getServicemoney());
				returnobj.accumulate("servicenum", info.get(0).getServicenum());
				returnobj.accumulate("shopname", shop.getShopname());
				ArrayList<FairerSkill> skills=(ArrayList<FairerSkill>) fairerskillService.QueryByFairer(info.get(0).getPkFairer().toString());
				if(skills.size()>0){
				String skilldata=JSONArray.fromObject(skills).toString();	
				returnobj.accumulate("skills", skilldata);
				   }
				}
		}
		outputstr(returnobj.toString(), "获取信息成功", true,null);
		}else{
		outputstr("", "无此用户信息", false,null);
		}
		
		} catch (Exception e) {
		    dealexception(e);
		    outputexceptionstr();
		}
		output(response,pojo);
	}
	
	/**
	 * 统计二维码扫描次数
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/calculatescan")
	public void calculatescan(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			String pkUser=obj.getString("pkUser");			
			UserList user=userlistService.getUserById(Long.parseLong(pkUser));
			if(user!=null){
				user.setScancount((user.getScancount()==null?0:user.getScancount())+1);//扫描次数加1
				userlistService.updateUser(user);
				outputstr("", "统计扫描次数成功", true,null);
			}else{
				outputstr("", "用户不存在", false,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 查询理发师的基本信息
	 * @param request
	 * @param response
	 * @param model
	 */
	 @RequestMapping("/querybyuser")
	 public void querybyuser(HttpServletRequest request,HttpServletResponse response,Model model) {
		 try {
				JSONObject obj=RequestUtil.getPostString(request);			
				String pkUser=obj.getString("pkUser");
				String usergroup=obj.getString("usergroup");		
				UserList user=userlistService.getUserById(Long.parseLong(pkUser));
				if(user!=null){
				if(usergroup.equals("002")){						  
					  JSONObject returnobj=new JSONObject();											
						returnobj.accumulate("nickname", user.getNickname());
						returnobj.accumulate("headurl", user.getHeadurl());
						returnobj.accumulate("headshorturl", user.getHeadshorturl());
						returnobj.accumulate("cellphone", user.getCellphone());
						returnobj.accumulate("sex", user.getSex());		
					 	ArrayList<FairerInfo> info=(ArrayList<FairerInfo>) fairerinfoService.QueryByPkuser(pkUser);
					 	ShopInfo shop=shopinfoService.getShopById(info.get(0).getPkShop());			
						if(info.size()>0){
						returnobj.accumulate("fairercode", info.get(0).getFairercode());
						returnobj.accumulate("rankname", info.get(0).getRankname());
						returnobj.accumulate("accountmoney", info.get(0).getAccountmoney());
						returnobj.accumulate("servicemoney", info.get(0).getServicemoney());
						returnobj.accumulate("servicenum", info.get(0).getServicenum());
						returnobj.accumulate("shopname", shop.getShopname());
						ArrayList<FairerSkill> skills=(ArrayList<FairerSkill>) fairerskillService.QueryByFairer(info.get(0).getPkFairer().toString());
						if(skills.size()>0){
						String skilldata=JSONArray.fromObject(skills).toString();	
						returnobj.accumulate("skills", skilldata);
						   }
						}
					}
				
				}else{
					outputstr("", "用户不存在", false,null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				dealexception(e);
				outputexceptionstr();
			}
			output(response, pojo);
		
	 }
}
