package com.rockstar.o2o.controller.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.CustomerStatus;
import com.rockstar.o2o.constant.QrcodeOperateobject;
import com.rockstar.o2o.constant.UserFrom;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.constant.VerificationMode;
import com.rockstar.o2o.controller.BaseController;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.UserList;
import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.pojo.WxQrcodeScancount;
import com.rockstar.o2o.pojo.WxQrcodeScancountB;
import com.rockstar.o2o.pojo.WxUser;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.ReturnPojo;
import com.rockstar.o2o.util.WxserviceUtil;
import com.rockstar.o2o.util.usercode.UserCodeUtil;
/**
 * 二维码服务
 * @author hxx
 *
 */
@Controller
@RequestMapping("/weixinqrcode")
public class WxqrcodeController extends BaseController {
	
	/**
	 * 消费者二维码,生成的是临时二维码
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/customerqrcode")
	public void customerqrcode(HttpServletRequest request,HttpServletResponse response,Model model) {   
		try {
		JSONObject obj=RequestUtil.getPostString(request);  
		String pkUser=obj.getString("pkUser");
		String openid=obj.getString("openid");
   	    JSONObject jsonobj=new JSONObject();
	    jsonobj.accumulate("pkKey", pkUser);
	    jsonobj.accumulate("openid", openid);
	    jsonobj.accumulate("codeobject", QrcodeOperateobject.OPERATEOBJECT_CUSTOMER[0]);
	   //读取配置文件参数
	   String url = WxserviceUtil.GetUrl("createqrurl");
	   //获取返回值
       String jsonResult = this.httpservice.sendPostRequset(url, jsonobj.toString());    
       JSONObject returnobj=JSONObject.fromObject(jsonResult);      
       ReturnPojo pojo=(ReturnPojo) JSONObject.toBean(returnobj,ReturnPojo.class);      
       if(pojo.isIssuccess()){
       returnobj=JSONObject.fromObject(returnobj.get("data"));
       String sceneid=(String)returnobj.get("sceneid");
       String actionname=(String)returnobj.get("actionname");
       String qrcodeurl=(String)returnobj.get("qrcodeurl");     
		//查询表中是否已经存在此二维码统计信息
     
	    WxQrcodeScancount wxQrcodeScancount = wxQrcodeScancountService.getScancountInfo(account_id,pkUser, QrcodeOperateobject.OPERATEOBJECT_CUSTOMER[0]);	
	    if(wxQrcodeScancount!=null){
		       wxQrcodeScancount.setQrcodeUrls(qrcodeurl);
		       if(wxQrcodeScancount.getUserDefined()==null||wxQrcodeScancount.getUserDefined().equals("")){
		    	   ArrayList<Object> wxuser=(ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ? ", openid);
		    	   wxQrcodeScancount.setUserDefined(wxuser.size()>0?(((WxUser)wxuser.get(0)).getNickname()):null);
		       }
		       wxQrcodeScancountService.updateScancount(wxQrcodeScancount);
			   JSONObject json=new JSONObject();
			   json.accumulate("qrcodeurl", qrcodeurl);
			   outputstr(json.toString(), "生成二维码成功", true,null);
	        }else{	        	
				   //保存二维码统计信息
			       WxQrcodeScancount wxScancount =new WxQrcodeScancount();
			       wxScancount.setSceneId(sceneid);
			       wxScancount.setActionName(actionname);
			       wxScancount.setQrcodeUrls(qrcodeurl);
			       if(wxScancount.getUserDefined()==null||wxScancount.getUserDefined().equals("")){
			    	   ArrayList<Object> wxuser=(ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ? ", openid);
			    	   wxScancount.setUserDefined(wxuser.size()>0?(((WxUser)wxuser.get(0)).getNickname()):null);
			       }			     
				   wxScancount.setPkKey(Long.parseLong(pkUser));			   
				   wxScancount.setOperateobject(QrcodeOperateobject.OPERATEOBJECT_CUSTOMER[0]); 
				   wxScancount.setCount((long)0);
				   wxScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				   wxScancount.setDr((short)0);
				   wxScancount.setAccountId(account_id);
				   wxScancount.setOpenid(openid);
				   wxQrcodeScancountService.save(wxScancount);
				   JSONObject json=new JSONObject();
				   json.accumulate("qrcodeurl", qrcodeurl);
				   outputstr(json.toString(), "生成二维码成功", true,null);
	            }
              }else{
    	      outputstr("", "生成二维码失败", false,null);
            }	
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	    
	}
	
	
	/**
	 *创建二维码 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/createqrcode")
	public void createQrcode(HttpServletRequest request,HttpServletResponse response,Model model) {   
		String jsonResult ="";
    	try {	
			   JSONObject obj=RequestUtil.getPostString(request);  
			   String codeobject=obj.getString("codeobject");
			   String pkkey="";
			   if(codeobject.equals("4")){
				  // pkkey=(String)obj.get("pkKey");
				  // pkkey=(String)obj.get("pkShop");
				   pkkey=codeobject;
			   }else if(codeobject.equals("5")||codeobject.equals("6")){
				   pkkey=obj.getString("pkShop");
			   }else{
				   pkkey=obj.getString("pkKey");  
			   }
			   String userDefined=(String)obj.get("userDefined");
			   
			   JSONObject jsonobj=new JSONObject();
			   jsonobj.accumulate("pkKey", pkkey);
			   jsonobj.accumulate("codeobject", codeobject);
			   //读取配置文件参数
			   String url = WxserviceUtil.GetUrl("createqrurl");
			   
			   WxQrcodeScancount wxQrcodeScancount=null;
			   if(codeobject.equals("1")||codeobject.equals("5")){
				   //查询表中是否已经存在此二维码统计信息
		 		   wxQrcodeScancount = wxQrcodeScancountService.getScancountInfo(account_id,pkkey,codeobject);
			   }
			   //生成店铺/消费者/理发师 二维码
			   if(codeobject.equals("1")){
	 		       //不存在需要创建二维码 和统计信息
	 			   if(wxQrcodeScancount==null){
	 				   //获取返回值
	 	 		       jsonResult = this.httpservice.sendPostRequset(url, jsonobj.toString());
	 	 		       JSONObject returnobj=JSONObject.fromObject(jsonResult);      
	 	 		       ReturnPojo pojo=(ReturnPojo) JSONObject.toBean(returnobj,ReturnPojo.class);   
	 	 		       if(pojo.isIssuccess()){
			 		       returnobj=JSONObject.fromObject(returnobj.get("data"));
			 		       String sceneid=(String)returnobj.get("sceneid");
			 		       String actionname=(String)returnobj.get("actionname");
			 		       String qrcodeurl=(String)returnobj.get("qrcodeurl");	 
			 		       
		 				   //保存二维码统计信息
		 	 		       WxQrcodeScancount wxScancount =new WxQrcodeScancount();
		 	 		       wxScancount.setSceneId(sceneid);
		 	 		       wxScancount.setActionName(actionname);
		 	 		       wxScancount.setQrcodeUrls(qrcodeurl);
		 				   wxScancount.setPkKey(Long.parseLong(pkkey));			   
		 				   wxScancount.setOperateobject(codeobject); 
		 				   wxScancount.setCount((long)0);
		 				   wxScancount.setAccountId(account_id);
		 				   wxScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		 				   wxScancount.setDr((short)0);
		 				   
		 				   ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkkey));
		 				   wxScancount.setUserDefined((userDefined==null||userDefined.equals(""))?info.getShopname()+"(店铺二维码)":userDefined);
		 				   wxQrcodeScancountService.save(wxScancount);
		 				   
		 				   info.setShopqrcodeurl(qrcodeurl);
		 				   shopinfoService.updateGroup(info);
		 				   
		 				   JSONObject json=new JSONObject();
		 				   json.accumulate("qrcodeurl", qrcodeurl);
		 	 			   outputstr(json.toString(), "生成二维码成功", true,null);
	 	 		       }else{
			 		       outputstr("", "生成二维码失败", false,null); 
			 		   }
	 		       }else{
	 		    	   outputstr("", "二维码已经存在", false,null);
	 		       }
	 			
	 			//生成店铺其他二维码,固定二维码
			   }else if(codeobject.equals("6")){
	 		       //查询表中是否已经存在此二维码统计信息
	 		       WxQrcodeScancount wxQrScancount = wxQrcodeScancountService.getCountInfoby(account_id,pkkey,codeobject,userDefined);
	 		       if(wxQrScancount==null){
					   //获取返回值
		 		       jsonResult = this.httpservice.sendPostRequset(url, jsonobj.toString());
		 		       JSONObject returnobj=JSONObject.fromObject(jsonResult);      
		 		       ReturnPojo pojo=(ReturnPojo) JSONObject.toBean(returnobj,ReturnPojo.class);   
		 		       if(pojo.isIssuccess()){
						   returnobj=JSONObject.fromObject(returnobj.get("data"));
			 		       String sceneid=(String)returnobj.get("sceneid");
			 		       String actionname=(String)returnobj.get("actionname");
			 		       String qrcodeurl=(String)returnobj.get("qrcodeurl");
	
		 				   //保存二维码统计信息
		 	 		       WxQrcodeScancount wxScancount =new WxQrcodeScancount();
		 	 		       wxScancount.setSceneId(sceneid);
		 	 		       wxScancount.setActionName(actionname);
		 	 		       wxScancount.setQrcodeUrls(qrcodeurl);
		 				   wxScancount.setPkKey(Long.parseLong(pkkey));			   
		 				   wxScancount.setOperateobject(codeobject); 
		 				   wxScancount.setUserDefined(userDefined);//自定义
		 				   wxScancount.setCount((long)0);
		 				   wxScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		 				   wxScancount.setDr((short)0);
		 				   wxScancount.setAccountId(account_id);
		 				   wxQrcodeScancountService.save(wxScancount);
		 				   JSONObject json=new JSONObject();
		 				   json.accumulate("qrcodeurl", qrcodeurl);
		 	 			   outputstr(json.toString(), "生成二维码成功", true,null);
		 		       }else{
			 		       outputstr("", "生成二维码失败", false,null); 
			 		  }
	 		       }else{
	 		    	   outputstr("", "二维码信息录入重复", false,null);  
	 		       }
 				  				 
				 //生成订单二维码,固定二维码
			   	 }else if(codeobject.equals("5")){		
			   		 if(wxQrcodeScancount==null){
					   //获取返回值
		 		       jsonResult = this.httpservice.sendPostRequset(url, jsonobj.toString());
		 		       JSONObject returnobj=JSONObject.fromObject(jsonResult);      
		 		       ReturnPojo pojo=(ReturnPojo) JSONObject.toBean(returnobj,ReturnPojo.class);  
		 		       if(pojo.isIssuccess()){
			 		       returnobj=JSONObject.fromObject(returnobj.get("data"));
			 		       String sceneid=(String)returnobj.get("sceneid");
			 		       String actionname=(String)returnobj.get("actionname");
			 		       String qrcodeurl=(String)returnobj.get("qrcodeurl");
	
		 				   //保存二维码统计信息
		 	 		       WxQrcodeScancount wxScancount =new WxQrcodeScancount();
		 	 		       wxScancount.setSceneId(sceneid);
		 	 		       wxScancount.setActionName(actionname);
		 	 		       wxScancount.setQrcodeUrls(qrcodeurl);
		 				   wxScancount.setPkKey(Long.parseLong(pkkey));			   
		 				   wxScancount.setOperateobject(codeobject); 
		 				   wxScancount.setUserDefined(userDefined);//自定义
		 				   wxScancount.setCount((long)0);
		 				   wxScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		 				   wxScancount.setDr((short)0);
		 				   wxScancount.setAccountId(account_id);
		 				  
		 				   
		 				   ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkkey));
	
		 				   wxScancount.setUserDefined(info.getShopname()+"(订单二维码)");
		 				   wxQrcodeScancountService.save(wxScancount);
		 				   
		 				   info.setOrderqrcodeurl(qrcodeurl);
		 				   shopinfoService.updateGroup(info);
		 				   
		 				   JSONObject json=new JSONObject();
		 				   json.accumulate("qrcodeurl", qrcodeurl);
		 	 			   outputstr(json.toString(), "生成二维码成功", true,null);
		 		       }else{
			 		       outputstr("", "生成二维码失败", false,null); 
			 		   }
	 		       }else{
	 		    	   JSONObject json=new JSONObject();
	 				   json.accumulate("qrcodeurl", wxQrcodeScancount.getQrcodeUrls());
	 	 			   outputstr(json.toString(), "查询二维码成功", true,null);
	 		       }				  
			  //生成平台二维码
		      }else if(codeobject.equals("4")){
	 		     //查询表中是否已经存在此二维码统计信息
	 		     WxQrcodeScancount oldwxQrScancount = wxQrcodeScancountService.getCountInfoby(account_id,pkkey,codeobject,userDefined);
	 		     if(oldwxQrScancount==null){
					 //获取返回值
		 		     jsonResult = this.httpservice.sendPostRequset(url, jsonobj.toString());
		 		     JSONObject returnobj=JSONObject.fromObject(jsonResult);      
		 		     ReturnPojo pojo=(ReturnPojo) JSONObject.toBean(returnobj,ReturnPojo.class);   		 		     
		 		     if(pojo.isIssuccess()){
						 returnobj=JSONObject.fromObject(returnobj.get("data"));
			 		     String sceneid=(String)returnobj.get("sceneid");
			 		     String actionname=(String)returnobj.get("actionname");
			 		     String qrcodeurl=(String)returnobj.get("qrcodeurl");
		 		         //查询表中是否已经存在此二维码统计信息
			 		     WxQrcodeScancount wxQrScancount = wxQrcodeScancountService.getCountInfo(account_id,sceneid, actionname);
			 		     if(wxQrScancount==null){
			 				   //保存二维码统计信息
			 	 		       WxQrcodeScancount wxScancount =new WxQrcodeScancount();
			 	 		       wxScancount.setSceneId(sceneid);
			 	 		       wxScancount.setActionName(actionname);
			 	 		       wxScancount.setQrcodeUrls(qrcodeurl);
			 				   wxScancount.setPkKey(Long.parseLong(pkkey));			   
			 				   wxScancount.setOperateobject(codeobject); 
			 				   wxScancount.setUserDefined(userDefined);//自定义
			 				   wxScancount.setCount((long)0);
			 				   wxScancount.setAccountId(account_id);
			 				   wxScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			 				   wxScancount.setDr((short)0);
			 				   wxQrcodeScancountService.save(wxScancount);
			 		      }
		 				  JSONObject json=new JSONObject();
		 				  json.accumulate("qrcodeurl", qrcodeurl);
		 	 			  outputstr(json.toString(), "生成二维码成功", true,null); 
		 		     }else{
		 		    	 outputstr("", "生成二维码失败", false,null); 
		 		     }
	 		     }else{
	 		    	 outputstr("", "二维码信息录入重复", false,null); 
	 		     }	
		      }	 
 
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}

	
	/**
	 * 二维码统计信息更新/保存---用户扫描二维码时调用
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/updatescancount")
	public void updateQrcodeScanCount(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		   JSONObject obj=RequestUtil.getPostString(request);
		   
		   //从请求中获取sceneid
 		   String sceneid=obj.getString("sceneid");
 		   //从请求中获取actionname
		   String actionname=obj.getString("actionname");
		   //获取用户id
		   String scanUserId=obj.getString("openid");
		   //获取用户关注状态
		   String isOlduser=(String)obj.get("isold");
		   //获取accountid
		   String accountid=obj.getString("accountid");
		   
		   //获取二维码扫描统计信息
		   WxQrcodeScancount wxQrcodeScancount = wxQrcodeScancountService.getCountInfo(accountid,sceneid, actionname);
		   if(wxQrcodeScancount!=null && !"".equals(wxQrcodeScancount)){
			   
			   //店铺订单下单二维码
			   if(wxQrcodeScancount.getOperateobject().equals("5")){
				   ArrayList<Object> modes=( ArrayList<Object>) basicservice.query(UserVerificationmode.class, " openid = ?  and verificationType = ? ", scanUserId,VerificationMode.MODE_TWO[0]);
			       //用户已经绑定
				   if(modes.size()>0){
			    	 Long pkUser=((UserVerificationmode)modes.get(0)).getPkUser();
			    	 ArrayList<Object> customers=( ArrayList<Object>) basicservice.query(CustomerInfo.class, " pkUser = ?  and pkShop = ? ", pkUser,wxQrcodeScancount.getPkKey());
			    	 //老会员扫码下单 
			    	 if(customers.size()>0){
			    		  CustomerInfo info=(CustomerInfo)customers.get(0);
			    		  info.setCustomerstatus(CustomerStatus.SCANORDER[0]);
			    		  basicservice.update(info);
			    	  }else{
			    		  UserList olduser=userlistService.getUserById(pkUser);
			    		  //判断手机号是否在本店已经存在
			    		  ArrayList<Object> oldcustomers=( ArrayList<Object>) basicservice.query(CustomerInfo.class, " cellphone = ?  and pkShop = ? ", olduser.getCellphone(),wxQrcodeScancount.getPkKey()); 
			    	      if(oldcustomers.size()>0){
			    	    	  CustomerInfo info=(CustomerInfo)oldcustomers.get(0);
				    		  info.setCustomerstatus(CustomerStatus.SCANORDER[0]);
				    		  info.setPkUser(pkUser);
				    		  basicservice.update(info);
			    	      }else{
			    	    	  updatemember(pkUser.toString(),olduser.getCellphone(),scanUserId,wxQrcodeScancount.getPkKey());
			    	      }
			    	  }
			       }else{
			    	         updatemember(null,null,scanUserId,wxQrcodeScancount.getPkKey());
			       }
			   }
			   
			   //获取统计表主键
			   long pkScancountid = wxQrcodeScancount.getPkScancountid();
			   //根据用户id查询用户信息是否存在
			   if(!wxQrcodeScancount.getOperateobject().equals("5")){
				   boolean iscontinue=true;
				 if(wxQrcodeScancount.getOperateobject().equals(QrcodeOperateobject.OPERATEOBJECT_CUSTOMER[0])){
					  if(wxQrcodeScancount.getOpenid()!=null&&wxQrcodeScancount.getOpenid().equals(scanUserId)){ 
						  iscontinue=false;
						  outputstr("", "自己扫描二维码，数量不做变化", false,null);
					  }
				  }
				 if(isOlduser!=null&&isOlduser.equals("Y")){
					  iscontinue=false;
					  outputstr("", "已关注会员扫描，次数不做变化", false,null); 
				 }
				 
				 if(iscontinue){
			   WxQrcodeScancountB wxScanB= wxQrcodeScancountBService.queryByUserid(scanUserId);
			   if(wxScanB==null){ 	   
				   //保存扫描统计详情
				   WxQrcodeScancountB scancountB =new WxQrcodeScancountB();
				   scancountB.setPkScancountid(pkScancountid);
				   scancountB.setScanUserid(scanUserId);
				   scancountB.setIsOlduser("");
				   scancountB.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				   scancountB.setDr((short)0);
				   wxQrcodeScancountBService.save(scancountB);
				   
				   //更新扫描统计表---扫描数
				   long count= wxQrcodeScancountBService.getCountByPkScancountid(pkScancountid);				  
				   wxQrcodeScancount.setCount(count);
				   //更新扫描统计表---扫描并关注数
				   long attentionCount= wxQrcodeScancountBService.getAttentionCount(pkScancountid); 
				   wxQrcodeScancount.setAttentionCount(attentionCount);
				   
				   wxQrcodeScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				   int result=wxQrcodeScancountService.updateScancount(wxQrcodeScancount);
				   if(result==0){
					   outputstr("", "更新二维码扫描统计详情成功", true,null);
				   }else{
					   outputstr("", "更新二维码统计信息失败", false,null);
				   }   
			   }else{
					outputstr("", "此用户二维码扫描记录已经存在", false,null);
			      }
				 }
			   }else{
				   //订单扫码的完全统计
				   WxQrcodeScancountB scancountB =new WxQrcodeScancountB();
				   scancountB.setPkScancountid(pkScancountid);
				   scancountB.setScanUserid(scanUserId);
				   scancountB.setIsOlduser("");
				   scancountB.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				   scancountB.setDr((short)0);
				   wxQrcodeScancountBService.save(scancountB);
				   
				   //更新扫描统计表---扫描数
				   long count= wxQrcodeScancountBService.getCountByPkScancountid(pkScancountid);				  
				   wxQrcodeScancount.setCount(count);
				   //更新扫描统计表---扫描并关注数
				   long attentionCount= wxQrcodeScancountBService.getAttentionCount(pkScancountid); 
				   wxQrcodeScancount.setAttentionCount(attentionCount);
				   
				   wxQrcodeScancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				   int result=wxQrcodeScancountService.updateScancount(wxQrcodeScancount);
				   if(result==0){
					   outputstr("", "更新二维码扫描统计详情成功", true,null);
				   }else{
					   outputstr("", "更新二维码统计信息失败", false,null);
				   }   
			   }
		   }else{
				outputstr("", "二维码信息不存在", false,null);
		   }	       
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 更新会员状态和信息
	 */
	public void updatemember(String pkuser,String cellphone,String openid,Long pkshop){
		 ArrayList<Object> wxusers=( ArrayList<Object>) basicservice.query(WxUser.class, " wxId = ?  ", openid); 
  	   //新会员扫码下单
  	   if(wxusers.size()>0){
  		   WxUser user=(WxUser)wxusers.get(0);
  	       ArrayList<Object> oldwxcustomer=( ArrayList<Object>) basicservice.query(CustomerInfo.class, " wxopenid = ? and pkShop = ?  ", openid,pkshop);
  	       //判断会员是否已经存在
  	    	  if(oldwxcustomer.size()>0){
  	    		       CustomerInfo oldwxuser=(CustomerInfo)oldwxcustomer.get(0);
  	    		       oldwxuser.setProvince(user.getProvince());
  	    		       oldwxuser.setCity(user.getCity());
  	    		       oldwxuser.setCustomername(user.getNickname());
  	    		       oldwxuser.setSex(oldwxuser.getSex()!=null?oldwxuser.getSex():user.getSex());
  	    		       oldwxuser.setCellphone(cellphone==null?oldwxuser.getCellphone():cellphone);
  	    		       oldwxuser.setPkUser(pkuser==null?null:Long.parseLong(pkuser));
		    		   oldwxuser.setHeadimageurl(user.getHeadImageUrl());
		    		   oldwxuser.setPy(CharUtil.chineseToPingyin(user.getNickname()));
		    		   oldwxuser.setShortpy(CharUtil.getHeadChar(user.getNickname()));
		    		   oldwxuser.setCustomerstatus(CustomerStatus.SCANORDER[0]);
		    		   basicservice.update(oldwxuser);
  	    	  }else{
  	           ShopInfo shopinfo=shopinfoService.getShopById(pkshop);
			  String customercode="WX"+UUID.randomUUID();
			  if(shopinfo!=null){
				 String shopcode=shopinfo.getShopshortcode();
				  //生成会员编号
				  String sql=UserCodeUtil.execute(UserGroupObject.GROUP_ONE[0],shopcode,5);
				  List<Object> getobj=customerinfoService.querybysql(sql);
					if(getobj.get(0)==null){
						customercode=shopcode+UserGroupObject.GROUP_ONE[0]+"00001";
					}else{
						customercode=shopcode+UserGroupObject.GROUP_ONE[0]+getobj.get(0).toString();	
				   }
			  }
  		   CustomerInfo newinfo=new CustomerInfo();
  		   newinfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
  		   newinfo.setCustomercode(customercode);
  		   newinfo.setPkShop(pkshop);
  		   newinfo.setDr((short)0);
  		   newinfo.setPkUser(pkuser==null?null:Long.parseLong(pkuser));
  		   newinfo.setCellphone(cellphone);
  		   newinfo.setProvince(user.getProvince());
  		   newinfo.setCity(user.getCity());
  		   newinfo.setCustomername(user.getNickname());
  		   newinfo.setNickname(user.getNickname());
  		   newinfo.setSex(user.getSex());
  		   newinfo.setCustomerfrom(UserFrom.WEIXIN[0]);
  		   newinfo.setHeadimageurl(user.getHeadImageUrl());
  		   newinfo.setPy(CharUtil.chineseToPingyin(user.getNickname()));
  		   newinfo.setShortpy(CharUtil.getHeadChar(user.getNickname()));
  		   newinfo.setRegistertime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
  		   newinfo.setCustomerstatus(CustomerStatus.SCANORDER[0]);
  		   newinfo.setWxopenid(openid);
  		   newinfo.setPkUser(pkuser==null?null:Long.parseLong(pkuser));
  		   basicservice.save(newinfo);
  	    	  }
  	   }
	}
	
	
	/**
	 * 店铺/消费者/理发师---二维码统计信息列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querylist")
	public void queryScancountList(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		   JSONObject obj=RequestUtil.getPostString(request);
		   String pkKey=obj.getString("pkKey");
		   String codeobject=obj.getString("codeobject");
		   String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
		   String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");		   

		    ArrayList<WxQrcodeScancount> infos=new ArrayList<WxQrcodeScancount>();
			ArrayList<WxQrcodeScancount> totalinfos=new ArrayList<WxQrcodeScancount>();
			if(!curpage.equals("")){			
				 infos=(ArrayList<WxQrcodeScancount>) wxQrcodeScancountService.querycancountList(pkKey,codeobject,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				 totalinfos=(ArrayList<WxQrcodeScancount>) wxQrcodeScancountService.querycancountList(pkKey,codeobject,null,null);
			}else{
				infos=(ArrayList<WxQrcodeScancount>) wxQrcodeScancountService.querycancountList(pkKey,codeobject,null,null);	
			}
			if(infos.size()>0){
				String data=JSONArray.fromObject(infos).toString();
			    if(!curpage.equals("")){
			    	outputstr(data, "查询二维码统计信息成功", true,totalinfos.size());
			    }else{
			    	outputstr(data, "查询二维码统计信息成功", true,infos.size());	
			    }
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
	 * 平台---二维码统计信息列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryplatlist")
	public void queryPlatScancountList(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		    JSONObject obj=RequestUtil.getPostString(request);
		    String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
		    String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");		   

		    ArrayList<WxQrcodeScancount> infos=new ArrayList<WxQrcodeScancount>();
			ArrayList<WxQrcodeScancount> totalinfos=new ArrayList<WxQrcodeScancount>();
			if(!curpage.equals("")){			
				 infos=(ArrayList<WxQrcodeScancount>) platWxQrcodeScancountService.queryPlatScancountList(Integer.parseInt(curpage),Integer.parseInt(pagesize));
				 totalinfos=(ArrayList<WxQrcodeScancount>) platWxQrcodeScancountService.queryPlatScancountList(null,null);
			}else{
				infos=(ArrayList<WxQrcodeScancount>) platWxQrcodeScancountService.queryPlatScancountList(null,null);	
			}
			if(infos.size()>0){
				String data=JSONArray.fromObject(infos).toString();
			    if(!curpage.equals("")){
			    	outputstr(data, "平台查询二维码统计信息成功", true,totalinfos.size());
			    }else{
			    	outputstr(data, "平台查询二维码统计信息成功", true,infos.size());	
			    }
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
	 * 店铺---二维码统计信息列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryshoplist")
	public void queryshoplist(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		    JSONObject obj=RequestUtil.getPostString(request);
		    String pkShop=obj.getString("pkShop");
		    String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
		    String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");		   

		    ArrayList<WxQrcodeScancount> infos=new ArrayList<WxQrcodeScancount>();
			ArrayList<WxQrcodeScancount> totalinfos=new ArrayList<WxQrcodeScancount>();
			if(!curpage.equals("")){			
				 infos=(ArrayList<WxQrcodeScancount>) wxQrcodeScancountService.querycanbyShop(pkShop, Integer.parseInt(curpage),Integer.parseInt(pagesize));
				 totalinfos=(ArrayList<WxQrcodeScancount>) wxQrcodeScancountService.querycanbyShop(pkShop,null,null);
			}else{
				infos=(ArrayList<WxQrcodeScancount>) wxQrcodeScancountService.querycanbyShop(pkShop,null,null);	
			}
			if(infos.size()>0){
				String data=JSONArray.fromObject(infos).toString();
			    if(!curpage.equals("")){
			    	outputstr(data, "查询店铺二维码成功", true,totalinfos.size());
			    }else{
			    	outputstr(data, "查询店铺二维码成功", true,infos.size());	
			    }
		    }else{
			 outputstr("", "暂无店铺二维码", true,null);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	
	/**
	 * 二维码统计信息详情
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getscandetail")
	public void getScancountDetail(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		   JSONObject obj=RequestUtil.getPostString(request);
		   String pkScancountid=obj.getString("pkScancountid");
		   String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
		   String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");		   

		    ArrayList<Object> infos=new ArrayList<Object>();
			ArrayList<Object> totalinfos=new ArrayList<Object>();
			if(!curpage.equals("")){			
				 infos=(ArrayList<Object>) wxQrcodeScancountBService.getScancountdetail(pkScancountid,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				 totalinfos=(ArrayList<Object>) wxQrcodeScancountBService.getScancountdetail(pkScancountid,null,null);
			}else{
				infos=(ArrayList<Object>) wxQrcodeScancountBService.getScancountdetail(pkScancountid,null,null);	
			}
			if(infos.size()>0){
				String data=JSONArray.fromObject(infos).toString();
			    if(!curpage.equals("")){
			    	outputstr(data, "查询二维码统计详情成功", true,totalinfos.size());
			    }else{
			    	outputstr(data, "查询二维码统计详情成功", true,infos.size());	
			    }
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
	 * 查询消费者推广记录
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querycodescan")
	public void querycodescan(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		   JSONObject obj=RequestUtil.getPostString(request);
		   String pkUser=obj.getString("pkUser");   
		   ArrayList<Object> qrcode=(ArrayList<Object>) basicservice.query(WxQrcodeScancount.class, " pkKey = ? and operateobject = ? ", Long.parseLong(pkUser),QrcodeOperateobject.OPERATEOBJECT_CUSTOMER[0]);
		   
		   if(qrcode.size()>0){
			   WxQrcodeScancount scan=(WxQrcodeScancount) qrcode.get(0);
			   JSONObject returnobj=new JSONObject();
			   returnobj.accumulate("count", scan.getCount());
			   outputstr(returnobj.toString(), "查询推广记录成功", true, null);
		   }else{
			   outputstr("", "暂无推广记录", true, null);
		   }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
}

    	