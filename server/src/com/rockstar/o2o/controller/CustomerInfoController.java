package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.CustomerStatus;
import com.rockstar.o2o.constant.UserFrom;
import com.rockstar.o2o.constant.UserGroup;
import com.rockstar.o2o.constant.VerificationMode;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.MessageGroupMember;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.UserList;
import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.CodeUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MD5Util;
import com.rockstar.o2o.util.PatternUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.usercode.UserCodeUtil;


@Controller
@RequestMapping("/customer")
public class CustomerInfoController extends BaseController{
	
	/**
	 * 会员注册时录入手机号的时候进行手机号查询是否已经存在,如果在本店已经存在,则强制提醒不允许继续录入
	 * 如果只是在别的店有手机信息,则只是进行提示。
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("checkphone")
	public void checkphone(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
		JSONObject receiveobj=RequestUtil.getPostString(request);
		String cellphone=receiveobj.getString("cellphone");
		String pkShop=receiveobj.getString("pkShop");

		//检查当前手机号在本店是否已经存在
		CustomerInfo customerinfo=customerinfoService.QueryByCellandShop(cellphone, pkShop);
		if(customerinfo!=null){
		outputstr("", "该手机号的持有者已经是本店会员,请直接查询会员进行操作", false,null);
		}else{
		//检查当前手机号在所有店铺中是否存在
		 CustomerInfo cellphoneinfo=customerinfoService.QueryByCellphone(cellphone);
		 if(cellphoneinfo!=null){
			 outputstr("", "该手机号已经注册过", true,null);
	 	  }else{
			 outputstr("", "该手机号尚未注册", true,null);
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
	 * 自动注册app账号
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerapp")
	public void registerapp(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCustomer=obj.getString("pkCustomer");
			
			CustomerInfo info=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));
			String cellphone=info.getCellphone();
			
			if(cellphone==null||cellphone.equals("")){
				outputstr("", "手机号码不能为空", false,null);
			}else{
				boolean flag=PatternUtil.validatecellphone(cellphone);
				if(!flag){
					outputstr("", "手机号码格式不正确", false,null);
				}else{
			UserList user=userlistService.findbycellandgroup(cellphone, UserGroup.GROUP_ONE[0]);
			
			//用户表已经存在,则说明这个手机号已经注册过app
			if(user!=null){
				if(info.getPkUser()==null||info.getPkUser().equals("")){
					info.setPkUser(user.getPkUser());
					customerinfoService.updateGroup(info);
				}				
				outputstr("", "注册成功", true,null);
			}else{
			    //没有注册的,生成随机密码,登录号为手机号
				UserList newuser=new UserList();
				newuser.setCellphone(cellphone);
				newuser.setUsername(info.getCustomername());
				newuser.setUsergroup(UserGroup.GROUP_ONE[0]);
				newuser.setNickname(info.getCustomername());
				newuser.setSex(info.getSex());
				//生成随机登录密码
				String code=CodeUtil.getRandomStr(6);
				//密码加密
				String secretpassword=MD5Util.MD5Encode(code, "utf-8");				
			
				newuser.setLoginpassword(secretpassword);				
				newuser.setDr((short)0);
				newuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				newuser=userlistService.save(newuser);	
				info.setPkUser(newuser.getPkUser());
				customerinfoService.updateGroup(info);
			
				outputstr("", "注册成功", true,null);
				
//				   HashMap<String, String> resultMap=new  HashMap<String, String>();
//		    	   resultMap.put("code", code);
//		           String content=TemplateUtil.invokeTpl(resultMap, "template/message", "registerapp.ftl");
//		    	   obj.accumulate("code", code);
//		    	   obj.accumulate("messagetype", MessageType.TYPE_THREE);
//		    	   obj.accumulate("content", content);
//				   obj.accumulate("receiver", cellphone);
//				   obj.accumulate("usergroup", UserGroup.GROUP_ONE[0]);
//		    	   String result=MessageUtil.execute(obj,messageservice,messagelistService);
//		           if(result.equals("success")){
//					outputstr("", "注册成功,账号和密码已发送到手机!", true,null);
//		           }else{
//					outputstr("", "发送短信失败", false,null);
//		             }
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
	 * 保存会员信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public void saveinfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject receiveobj=RequestUtil.getPostString(request);
		String pkShop=receiveobj.getString("pkShop");
		String username=receiveobj.getString("username");
		String cellphone=receiveobj.getString("cellphone");
		String fixphone=receiveobj.get("fixphone")==null?null:receiveobj.getString("fixphone");
		String sex=receiveobj.getString("sex");
		String birthday=receiveobj.get("birthday")==null?"":receiveobj.getString("birthday");
		String note=receiveobj.get("note")==null?"":receiveobj.getString("note");
		String pkUser=receiveobj.getString("pkUser");
		boolean iscontinue=true;
		if(cellphone!=null&&!cellphone.trim().equals("")){
			boolean flag=PatternUtil.validatecellphone(cellphone);
			if(!flag){
				iscontinue=false;
				outputstr("", "手机号码格式不正确", false,null);
			}
		}
		if(fixphone!=null&&!fixphone.trim().equals("")){
			boolean flag=PatternUtil.validatefixphone(fixphone);
			if(!flag){
				iscontinue=false;
				outputstr("", "固定号码格式不正确", false,null);
			}
		}
		if(username!=null&&!username.trim().equals("")){
			boolean flag=PatternUtil.validatename(username);
			if(!flag){
				iscontinue=false;
				outputstr("", "姓名不允许包含特殊字符", false,null);
			}
		}
		
		
		if(iscontinue){
		if(username.trim().equals("")){
			outputstr("", "会员姓名不能为空", false,null);
		}else{
		//检查当前手机号在本店是否已经存在
		CustomerInfo customerinfo=customerinfoService.QueryByCellandShop(cellphone, pkShop);
		if(customerinfo!=null){
			outputstr("", "该手机号的持有者已经是本店会员,请直接查询会员进行操作", false,null);
		}else{				
			  CustomerInfo newinfo=new CustomerInfo();
			  newinfo.setCustomername(username);
			  newinfo.setCellphone(cellphone);
			  newinfo.setPkShop(Long.parseLong(pkShop));
			  newinfo.setCustomerfrom(UserFrom.SHOP[0]);
			  newinfo.setOperator(Long.parseLong(pkUser));
			  newinfo.setOperatime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			  newinfo.setSex(sex);
			  newinfo.setBirthday(birthday);
			  newinfo.setRegistertime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			  newinfo.setNote(note);
			  newinfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			  newinfo.setDr((short)0);
			  newinfo.setFixphone(fixphone);
			  newinfo.setPy(CharUtil.chineseToPingyin(username));//拼音全称
			  newinfo.setShortpy(CharUtil.getHeadChar(username));//首字母
			  String shopcode="";
			  //查询店铺编码
			  ShopInfo shopinfo=shopinfoService.getShopById(Long.parseLong(pkShop));
			  if(shopinfo!=null){
				  shopcode=shopinfo.getShopshortcode();
				  String customercode="";
				  //生成会员编号
				  String sql=UserCodeUtil.execute(UserGroup.GROUP_ONE[0],shopcode,5);
				  List<Object> obj=customerinfoService.querybysql(sql);
					if(obj.get(0)==null){
						customercode=shopcode+UserGroup.GROUP_ONE[0]+"00001";
					}else{
						customercode=shopcode+UserGroup.GROUP_ONE[0]+obj.get(0).toString();	
				   }
				  newinfo.setCustomercode(customercode);
				  //保存新会员
				  newinfo = customerinfoService.save(newinfo);
				  
				 // outputstr(JSONObject.fromObject(newinfo).toString(), "新增会员成功", true,null);
				  String data=JSONObject.fromObject(newinfo).toString();
				  //注册完成后自动发送账号和初始密码给消费者
				  UserList user=userlistService.findbycellandgroup(cellphone, UserGroup.GROUP_ONE[0]);
					
					//用户表已经存在,则说明这个手机号已经注册过app
					if(user!=null){
						if(newinfo.getPkUser()==null||newinfo.getPkUser().equals("")){
							newinfo.setPkUser(user.getPkUser());
							customerinfoService.updateGroup(newinfo);
						}
						
						outputstr(data, "注册成功", true,null);
					}else{
					    //没有注册的,生成随机密码,登录号为手机号
						UserList newuser=new UserList();
						newuser.setCellphone(cellphone);
						newuser.setUsername(newinfo.getCustomername());
						newuser.setUsergroup(UserGroup.GROUP_ONE[0]);
						newuser.setNickname(newinfo.getCustomername());
						newuser.setSex(newinfo.getSex());
						//生成随机登录密码
						String code=CodeUtil.getRandomStr(6);
						//密码加密
						String secretpassword=MD5Util.MD5Encode(code, "utf-8");				
					
						newuser.setLoginpassword(secretpassword);				
						newuser.setDr((short)0);
						newuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						newuser=userlistService.save(newuser);	
						newinfo.setPkUser(newuser.getPkUser());
						customerinfoService.updateGroup(newinfo);
						
						outputstr(data, "注册成功", true,null);
						
//						   HashMap<String, String> resultMap=new  HashMap<String, String>();
//						   JSONObject messageobj=new JSONObject();
//				    	   resultMap.put("code", code);
//				           String content=TemplateUtil.invokeTpl(resultMap, "template/message", "registerapp.ftl");
//				           messageobj.accumulate("code", code);
//				           messageobj.accumulate("messagetype", MessageType.TYPE_THREE);
//				           messageobj.accumulate("content", content);
//				           messageobj.accumulate("receiver", cellphone);
//				           messageobj.accumulate("usergroup", UserGroup.GROUP_ONE[0]);
//				    	   String result=MessageUtil.execute(messageobj,messageservice,messagelistService);
//				           if(result.equals("success")){
//							outputstr(data, "注册成功,账号和密码已发送到手机!", true,null);
//				           }else{
//							outputstr(data, "发送注册短信失败", false,null);
//				           }
					   }
					
			  }else{
				  outputstr("", "店铺信息不存在,请查正后录入", false,null);
			  }			
		   }
		 }
		}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		    //返回内容给前台
		 output(response, pojo);
	}
	
	
	/**
	 * 维护会员基本信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
		JSONObject receiveobj=RequestUtil.getPostString(request);
		String pkShop=receiveobj.getString("pkShop");
		String username=receiveobj.getString("username");
		String cellphone=receiveobj.getString("cellphone");
		String fixphone=receiveobj.get("fixphone")==null?null:receiveobj.getString("fixphone");
		String sex=receiveobj.getString("sex");
		String birthday=receiveobj.get("birthday")==null?"":receiveobj.getString("birthday");
		String note=receiveobj.get("note")==null?"":receiveobj.getString("note");
		String pkCustomer=receiveobj.getString("pkCustomer");
		
		boolean iscontinue=true;
		if(cellphone!=null&&!cellphone.trim().equals("")){
			boolean flag=PatternUtil.validatecellphone(cellphone);
			if(!flag){
				iscontinue=false;
				outputstr("", "手机号码格式不正确", false,null);
			}
		}
		if(fixphone!=null&&!fixphone.trim().equals("")){
			boolean flag=PatternUtil.validatefixphone(fixphone);
			if(!flag){
				iscontinue=false;
				outputstr("", "固定号码格式不正确", false,null);
			}
		}
		if(username!=null&&!username.trim().equals("")){
			boolean flag=PatternUtil.validatename(username);
			if(!flag){
				iscontinue=false;
				outputstr("", "姓名不允许包含特殊字符", false,null);
			}
		}
		if(iscontinue){
		if(username.trim().equals("")){
			outputstr("", "会员姓名不能为空", false,null);
		}else{
		     CustomerInfo customerinfo=customerinfoService.QueryByCellandShop(cellphone,pkShop);
		     CustomerInfo thiscustomerinfo=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));
		     ArrayList<Object>  updatelist=new ArrayList<Object>();
		     ArrayList<Object>  addlist=new ArrayList<Object>();
		     
		        //修改手机号则更新沟通组的用户号码
				if(!cellphone.equalsIgnoreCase(thiscustomerinfo.getCellphone())){
                  ArrayList<Object> obj=(ArrayList<Object>) basicservice.query(MessageGroupMember.class, " memberphone = ? and pkMember = ?", thiscustomerinfo.getCellphone(),Long.parseLong(pkCustomer));
				  if(obj.size()>0){
					  for(int i=0;i<obj.size();i++){
						  MessageGroupMember oldmember=(MessageGroupMember) obj.get(i);  
						  oldmember.setMemberphone(cellphone);
						  updatelist.add(oldmember);
					  }
				   }
				}
				
		    //查询修改后的会员在本店是否存在
			if(customerinfo!=null&&!customerinfo.getPkCustomer().equals(Long.parseLong(pkCustomer))){
				if(thiscustomerinfo.getCustomerfrom()!=null&&thiscustomerinfo.getCustomerfrom().equals(UserFrom.WEIXIN[0])){
				  //将微信用户删除
				  thiscustomerinfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				  thiscustomerinfo.setDr((short)1);
				  
				  updatelist.add(thiscustomerinfo);
				  
				  //更新原来用户
				  customerinfo.setCustomername(thiscustomerinfo.getCustomername());

				  updatelist.add(customerinfo);

				  //微信会员更新信息
					 UserList user=userlistService.findbycellandgroup(cellphone, UserGroup.GROUP_ONE[0]);
					 ArrayList<Object> oldmodes=(ArrayList<Object>) basicservice.query(UserVerificationmode.class, " openid = ?  and verificationType  = ?  ", thiscustomerinfo.getWxopenid(),VerificationMode.MODE_TWO[0]);
					 if(user!=null){
						 
						   customerinfo.setPkUser(user.getPkUser());
						   updatelist.add(customerinfo);
						   
						  if(oldmodes.size()>0){
							  UserVerificationmode getmode = (UserVerificationmode)oldmodes.get(0);
							  getmode.setPkUser(user.getPkUser());
							  updatelist.add(getmode);
						  }else{
			    		   UserVerificationmode newmode=new UserVerificationmode();
			    		   newmode.setDr((short)0);
			    		   newmode.setPkUser(user.getPkUser());
			    		   newmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    		   newmode.setSex(customerinfo.getSex());
			    		   newmode.setOpenid(thiscustomerinfo.getWxopenid());
			    		   newmode.setVerificationType(VerificationMode.MODE_TWO[0]);
			    		   addlist.add(newmode);
						  }
					  }else{				  
						  //插入UserList表
						  UserList newuser=new UserList();
						  newuser.setCellphone(cellphone);
						  newuser.setDr((short)0);
						  newuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						  newuser.setUsername(username);
						  newuser.setNickname(customerinfo.getCustomername());
						  newuser.setUserstatus("001");
						  newuser.setUsergroup(UserGroup.GROUP_ONE[0]);
						  String code=CodeUtil.getRandomStr(6);
						  //密码加密
						  String secretpassword=MD5Util.MD5Encode(code, "utf-8");		
						  newuser.setLoginpassword(secretpassword);
						  newuser.setSex(customerinfo.getSex());
						  newuser=userlistService.save(newuser);
						  
						   customerinfo.setPkUser(newuser.getPkUser());
						   updatelist.add(customerinfo);
						   
						  if(oldmodes.size()>0){
							  UserVerificationmode getmode = (UserVerificationmode)oldmodes.get(0);
							  getmode.setPkUser(newuser.getPkUser());
							  updatelist.add(getmode);
						  }else{
			    		   UserVerificationmode newmode=new UserVerificationmode();
			    		   newmode.setDr((short)0);
			    		   newmode.setPkUser(newuser.getPkUser());
			    		   newmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			    		   newmode.setSex(customerinfo.getSex());
			    		   newmode.setOpenid(thiscustomerinfo.getWxopenid());
			    		   newmode.setVerificationType(VerificationMode.MODE_TWO[0]);
			    		   addlist.add(newmode);
						  }
					  }
					
				  
				  basicservice.batchoperate(addlist, updatelist);
				  
				  outputstr("", "会员信息已经存在，已经完成合并",false,null);
				  
				}else{
			      outputstr("", "该手机号的持有者已经是本店会员,请查正", false,null);
				}
			}else{		
				customerinfo=thiscustomerinfo;
				customerinfo.setCustomername(username);
				customerinfo.setPy(CharUtil.chineseToPingyin(username));//拼音全称
				customerinfo.setShortpy(CharUtil.getHeadChar(username));//首字母
				String oldcellphone=customerinfo.getCellphone();
				customerinfo.setCellphone(cellphone);
				customerinfo.setNote(note);
				customerinfo.setSex(sex);
				customerinfo.setFixphone(fixphone);
				customerinfo.setBirthday(birthday);
				
				updatelist.add(customerinfo);

				//判断是否更新手机号，如果更新则更新userlist中的手机信息
				if(!cellphone.equalsIgnoreCase(oldcellphone)){
					if(customerinfo.getPkUser()!=null){
						UserList user=userlistService.getUserById(customerinfo.getPkUser());
						user.setCellphone(cellphone);
						updatelist.add(user);
					}
				}
				
				//微信会员更新信息
				if(cellphone!=null&&(oldcellphone==null||!oldcellphone.equalsIgnoreCase(cellphone))&&customerinfo.getCustomerfrom()!=null&&customerinfo.getCustomerfrom().equals(UserFrom.WEIXIN[0])){
				 UserList user=userlistService.findbycellandgroup(cellphone, UserGroup.GROUP_ONE[0]);
				 ArrayList<Object> oldmodes=(ArrayList<Object>) basicservice.query(UserVerificationmode.class, " openid = ?  and verificationType  = ?  ", customerinfo.getWxopenid(),VerificationMode.MODE_TWO[0]);
				 if(user!=null){
					   customerinfo.setPkUser(user.getPkUser());
					   updatelist.add(customerinfo);
					  if(oldmodes.size()>0){
						  UserVerificationmode getmode = (UserVerificationmode)oldmodes.get(0);
						  getmode.setPkUser(user.getPkUser());
						  updatelist.add(getmode);
					  }else{
		    		   UserVerificationmode newmode=new UserVerificationmode();
		    		   newmode.setDr((short)0);
		    		   newmode.setPkUser(user.getPkUser());
		    		   newmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    		   newmode.setSex(customerinfo.getSex());
		    		   newmode.setOpenid(customerinfo.getWxopenid());
		    		   newmode.setVerificationType(VerificationMode.MODE_TWO[0]);
		    		   addlist.add(newmode);
					  }
				  }else{				  
					  //插入UserList表
					  UserList newuser=new UserList();
					  newuser.setCellphone(cellphone);
					  newuser.setDr((short)0);
					  newuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					  newuser.setUsername(username);
					  newuser.setNickname(customerinfo.getCustomername());
					  newuser.setUserstatus("001");
					  newuser.setUsergroup(UserGroup.GROUP_ONE[0]);
					  String code=CodeUtil.getRandomStr(6);
					  //密码加密
					  String secretpassword=MD5Util.MD5Encode(code, "utf-8");		
					  newuser.setLoginpassword(secretpassword);
					  newuser.setSex(customerinfo.getSex());
					  newuser=userlistService.save(newuser);
					  
					  customerinfo.setPkUser(newuser.getPkUser());
					  updatelist.add(customerinfo);
					   
					  if(oldmodes.size()>0){
						  UserVerificationmode getmode = (UserVerificationmode)oldmodes.get(0);
						  getmode.setPkUser(newuser.getPkUser());
						  updatelist.add(getmode);
					  }else{
		    		   UserVerificationmode newmode=new UserVerificationmode();
		    		   newmode.setDr((short)0);
		    		   newmode.setPkUser(newuser.getPkUser());
		    		   newmode.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		    		   newmode.setSex(customerinfo.getSex());
		    		   newmode.setOpenid(customerinfo.getWxopenid());
		    		   newmode.setVerificationType(VerificationMode.MODE_TWO[0]);
		    		   addlist.add(newmode);
					  }
				  }
				}
				 
				    basicservice.batchoperate(addlist, updatelist);
					outputstr("", "会员信息维护成功", true,null);
				
		    	}
		   }
		}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		 //返回内容给前台
		output(response, pojo);
	}
	
	/**
	 * 根据店铺查询所有消费者
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/querybyshop")
	public void querybyshop(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");			
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<CustomerInfo> list=new ArrayList<CustomerInfo>();			
			if(!curpage.equals("")){			
				list=(ArrayList<CustomerInfo>) customerinfoService.QueryByShop(pkShop,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				}else{
				list=(ArrayList<CustomerInfo>) customerinfoService.QueryByShop(pkShop,null,null);
				}			
		    if(list.size()>0){
		    String data=JSONArray.fromObject(list).toString();
		     outputstr(data, "查询消费者成功", true,null);
		    }else{
			 outputstr("", "暂无消费者", true,null);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
             outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	@RequestMapping("/query")
	public void query(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String likecontent=obj.getString("likecontent");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<CustomerInfo> infos=new ArrayList<CustomerInfo>();
			ArrayList<CustomerInfo> totalinfos=new ArrayList<CustomerInfo>();
			if(!curpage.equals("")){			
			 infos=(ArrayList<CustomerInfo>) customerinfoService.querybynameandcell(likecontent, pkShop,Integer.parseInt(curpage),Integer.parseInt(pagesize));
			 totalinfos=(ArrayList<CustomerInfo>) customerinfoService.querybynameandcell(likecontent, pkShop,null,null);
			}else{
			 infos=(ArrayList<CustomerInfo>) customerinfoService.querybynameandcell(likecontent, pkShop,null,null);	
			}
			if(infos.size()>0){
		    String data=JSONArray.fromObject(infos).toString();
		    if(!curpage.equals("")){
		     outputstr(data, "查询用户成功", true,totalinfos.size());
		    }else{
		     outputstr(data, "查询用户成功", true,infos.size());	
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
	
	
	@RequestMapping("/querybyid")
	public void querybyid(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			String pkCustomer=receiveobj.getString("pkCustomer");			
			CustomerInfo info=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));
		    if(info!=null){
		    JSONObject obj=JSONObject.fromObject(info);
		    ArrayList<Object> list=(ArrayList<Object>) orderbeautyrecordService.QueryByPkcustomer(pkCustomer);
		    if(list.size()>0){
		    	obj.accumulate("records", JSONArray.fromObject(list).toString());
		    }
		    String data=obj.toString();
		    outputstr(data, "查询用户信息成功", true,null);
		    }else{
		    outputstr("", "会员不存在", false,null);
		    }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 查询扫描下单用户
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryscan")
	public void queryscan(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			String pkShop=receiveobj.getString("pkShop");						
			ArrayList<Object> customerinfo=(ArrayList<Object>) basicservice.query(CustomerInfo.class, " pkShop = ? and customerstatus = ? ", Long.parseLong(pkShop),CustomerStatus.SCANORDER[0]);		
			if(customerinfo.size()>0){	
				String data=JSONArray.fromObject(customerinfo).toString();
				outputstr(data, "查询扫描单成功", true, customerinfo.size());
			}else{
				outputstr("", "暂无扫描下单", true, null);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 取消微信重复会员
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/cancelwx")
	public void cancelwx(HttpServletRequest request,HttpServletResponse response,Model model) {	
		try {
			JSONObject receiveobj=RequestUtil.getPostString(request);
			String pkCustomer=receiveobj.getString("pkCustomer");						
			
			CustomerInfo info=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));
			
			if(info!=null&&info.getCustomerfrom()!=null&&info.getCustomerfrom().equals(UserFrom.WEIXIN[0])){
				customerinfoService.deleteCustomerById(Long.parseLong(pkCustomer));
				outputstr("", "取消微信会员成功", true, null);
			}else{
				outputstr("", "会员不存在或不能取消", false, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
}
