package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.MessageType;
import com.rockstar.o2o.constant.UserFrom;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.pojo.FairerAttachment;
import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.pojo.FairerSkill;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.pojo.UserList;
import com.rockstar.o2o.util.CodeUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MD5Util;
import com.rockstar.o2o.util.PatternUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.TemplateUtil;
import com.rockstar.o2o.util.message.MessageUtil;
import com.rockstar.o2o.util.usercode.UserCodeUtil;


/**
 * 理发师信息维护
 * @author xc
 *
 */
@Controller
@RequestMapping("fairer")
public class FairerInfoController extends BaseController{
	/**
	 * 自动注册APP账号
	 * @param model
	 * @return
	 */
	@RequestMapping("/registerapp")
	public void registerapp(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkFairer=obj.getString("pkFairer");
			
			FairerInfo info=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
			String cellphone=info.getCellphone();
			
			UserList user=userlistService.findbycellandgroup(cellphone, UserGroupObject.GROUP_TWO[0]);
			
			//用户表已经存在,则说明这个手机号已经注册过APP
			if(user!=null){
				if(info.getPkUser()==null||info.getPkUser().equals("")){
					info.setPkUser(user.getPkUser());
					fairerinfoService.updateFairer(info);
				}
				if(info.getIsvalidate()==null||info.getIsvalidate().equals("")||info.getIsvalidate().equals("N")){
					user.setUserstatus("002");
					userlistService.updateUser(user);
				}
				outputstr("", "注册成功", true,null);
			}else{
			    //没有注册的,生成随机密码,登录号为手机号
				UserList newuser=new UserList();
				newuser.setCellphone(cellphone);
				newuser.setUsername(info.getFairername());
				newuser.setUsergroup(UserGroupObject.GROUP_TWO[0]);
				newuser.setNickname(info.getNickname());
				newuser.setSex(info.getSex());
				if(info.getIsvalidate()==null||info.getIsvalidate().equals("")||info.getIsvalidate().equals("N")){
				newuser.setUserstatus("002");
				}
				//生成随机登录密码
				String code=CodeUtil.getRandomStr(6);
				//密码加密
				String secretpassword=MD5Util.MD5Encode(code, "utf-8");				
			
				newuser.setLoginpassword(secretpassword);				
				newuser.setDr((short)0);
				newuser.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				newuser=userlistService.save(newuser);	
				info.setPkUser(newuser.getPkUser());
				fairerinfoService.updateFairer(info);
				
				   HashMap<String, String> resultMap=new  HashMap<String, String>();
		    	   resultMap.put("code", code);
		           String content=TemplateUtil.invokeTpl(resultMap, "template/message", "registerapp.ftl");
		    	   obj.accumulate("code", code);
		    	   obj.accumulate("messagetype", MessageType.TYPE_THREE);
		    	   obj.accumulate("content", content);
				   obj.accumulate("receiver", cellphone);
				   obj.accumulate("usergroup", UserGroupObject.GROUP_TWO[0]);
		    	   String result=MessageUtil.execute(obj,messageservice,messagelistService);
		           if(result.equals("success")){

                    outputstr("", "注册成功,账号和密码已发送到手机", true,null);
                    
		           }else{

					outputstr("", "发送注册短信失败", false,null);
					
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
	 * 查询有效的理发师
	 * 
	 */
	@RequestMapping("findvalidate")
	public void findvalidate(HttpServletRequest request,HttpServletResponse response,Model model){
		 try {
			 JSONObject obj=RequestUtil.getPostString(request);
			 String pkShop=obj.getString("pkShop");
		 	 ArrayList<FairerInfo> totallist=(ArrayList<FairerInfo>) fairerinfoService.QueryValidate(pkShop);
		     if(totallist.size()>0){
		    	 String data=JSONArray.fromObject(totallist).toString();
		    	 outputstr(data, "查询理发师成功", true,null);
		     }else{
		    	outputstr("", "暂无可用理发师", true,null);
		     }
		 } catch (Exception e) {
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 查找理发师列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("findlist")
	public void findlist(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//省
			String province=(String)obj.get("province");
			//市
			String city=(String)obj.get("city");
			//区
			String county=(String)obj.get("county");
			//店铺主键
			String pkShop=(String)obj.get("pkShop");
			//查询条件
			String querylike=obj.get("querylike")==null?"":obj.getString("querylike");
			
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<FairerInfo> list=new ArrayList<FairerInfo>();
			ArrayList<FairerInfo> totallist=new ArrayList<FairerInfo>();
			if(!curpage.equals("")){
				list=(ArrayList<FairerInfo>) fairerinfoService.QueryByShop(province,city,county,pkShop,querylike,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				totallist=(ArrayList<FairerInfo>) fairerinfoService.QueryByShop(province,city,county,pkShop,querylike,null,null);
			}else{
				list=(ArrayList<FairerInfo>) fairerinfoService.QueryByShop(province,city,county,pkShop,querylike,null,null);
			}
			if(list.size()>0){
				String data=JSONArray.fromObject(list).toString();
				if(!curpage.equals("")){			
					outputstr(data, "查询理发师成功", true,totallist.size());
				}else{
					outputstr(data, "查询理发师成功", true,list.size());	
				}
			}else{
				outputstr("", "暂无理发师信息", true,null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 查询理发师信息详情
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("findbyid")
	public void findbyid(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkFairer=obj.getString("pkFairer");
			
			FairerInfo info=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
			if(info!=null){
				  JSONObject returnobj=JSONObject.fromObject(info);
				  ArrayList<FairerAttachment> attachements=(ArrayList<FairerAttachment>) fairerattachmentService.querybypkFairer(pkFairer);
				  if(attachements.size()>0){
					  JSONArray array=JSONArray.fromObject(attachements);
					  returnobj.element("attachment",array);
				  }
				  ArrayList<FairerSkill> skills=(ArrayList<FairerSkill>) fairerskillService.QueryByFairer(pkFairer);
				  if(skills.size()>0){
					  JSONArray array=JSONArray.fromObject(skills);
					  returnobj.element("skill",array);
				  }
				  ShopInfo shopinfo= shopinfoService.getShopById(info.getPkShop());
				  returnobj.element("shopname",shopinfo.getShopname());
				  returnobj.element("shopprovince",shopinfo.getProvince());
				  returnobj.element("shopprovincename",shopinfo.getProvincename());
				  returnobj.element("shopcity",shopinfo.getCity());
				  returnobj.element("shopcityname",shopinfo.getCityname());
				  returnobj.element("shopcounty",shopinfo.getCounty());
				  returnobj.element("shopcountyname",shopinfo.getCountyname());	
			      outputstr(returnobj.toString(), "查询理发师信息成功", true,null);
			}else{
				  outputstr("", "无此理发师信息", true,null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();			
		}
		
        output(response, pojo);			
	}
	
	/**
	 * 保存理发师信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String pkShop=getobj.getString("pkShop");
			String fairername=getobj.getString("fairername");//发型师名
			String cellphone=getobj.getString("cellphone");//手机号
			String nickname=getobj.getString("nickname");//昵称
			String sex=getobj.getString("sex");//性别
			String age=(String)getobj.get("age");//年龄
			String rankname=(String)getobj.get("rankname");//职称
			String province=getobj.getString("province");//省
			String city=getobj.getString("city");//市
			String county=getobj.getString("county");//区
			String provincename=getobj.getString("provincename");
			String cityname=getobj.getString("cityname");
			String countyname=getobj.getString("countyname");
			String addr=getobj.getString("addr");//街道
			String identityid=(String)getobj.get("identityid");//身份证号
			String nativeaddr=(String)getobj.get("nativeaddr");//家庭住址
			String urgencypeople=(String)getobj.get("urgency");//紧急联系人
			String islogin=(String)getobj.get("islogin");//是否禁止登陆
			String note=(String)getobj.get("note");//备注
			String attachment=(String)getobj.get("attachment");//判断是否存在附加图片
			String skill=(String)getobj.get("skill");//技能
			String headimageurl=(String)getobj.get("headimageurl");//头像
			String headshorturl=(String)getobj.get("headshorturl");//头像缩略图
			
			boolean iscontinue=true;
			
			if(fairername!=null&&!fairername.trim().equals("")){
				boolean flag=PatternUtil.validatename(fairername);
				if(!flag){
					iscontinue=false;
					outputstr("", "姓名不允许包含特殊字符", false,null);
				}
			}
			if(iscontinue){
				ArrayList<FairerInfo> infos=(ArrayList<FairerInfo>) fairerinfoService.QueryByCellandShop(cellphone, pkShop);
				if(infos.size()>0){
					outputstr("", "该手机号的持有者已经是本店的理发师,请查正",false,null);
				}else{
					ArrayList<Object> oldinfos=(ArrayList<Object>) basicservice.query(FairerInfo.class, " identitycard = ?  and pkShop = ? ", identityid,Long.parseLong(pkShop));
					if(oldinfos.size()>0){
						outputstr("", "身份证号重复，请查正",false,null);
					}else{
						FairerInfo info=new FairerInfo();
						info.setFairername(fairername);
						info.setFairerfrom(UserFrom.PLAT[0]);
						info.setCellphone(cellphone);
						info.setNickname(nickname);
						info.setRankname(rankname);
						info.setAge((age==null||age.equals(""))?null:Integer.parseInt(age));
						info.setPkShop(Long.parseLong(pkShop));
						info.setSex(sex);
						//info.setAccountmoney(Double.parseDouble(servicemoney));
						info.setRegistertime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						info.setDr((short)0);
						info.setRegistertime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						info.setProvincename(provincename);
						info.setCityname(cityname);
						info.setCountyname(countyname);
						info.setProvince(province);
						info.setCity(city);
						info.setCounty(county);
						info.setAddr(addr);
						info.setNote(note);
						info.setNativeplace(nativeaddr);
						info.setIdentitycard(identityid);
						info.setUrgencypeople(urgencypeople);
						info.setHeadimageurl(headimageurl);
						info.setHeadshorturl(headshorturl);
						//判断是否有效
						if(!islogin.equals("")&&islogin.equals("Y")){
							info.setIsvalidate("N");
						}else{
							info.setIsvalidate("Y");
						}
						
						String shopcode="";
						//查询店铺编码
						ShopInfo shopinfo=shopinfoService.getShopById(Long.parseLong(pkShop));
						if(shopinfo!=null){
							shopcode=shopinfo.getShopshortcode();
							String faircode="";
							//生成理发师编号
							String sql=UserCodeUtil.execute(UserGroupObject.GROUP_TWO[0],shopcode,3);
							List<Object> obj=customerinfoService.querybysql(sql);
							if(obj.get(0)==null){
								faircode=shopcode+UserGroupObject.GROUP_TWO[0]+"001";
							}else{
								faircode=shopcode+UserGroupObject.GROUP_TWO[0]+obj.get(0).toString();	
							}
							info.setFairercode(faircode);
						}
						  
						info=fairerinfoService.save(info);
						
						ArrayList<Object> addlist=new ArrayList<Object>();
						
						//判断是否存在附加图片，如果存在，则保存
						if(attachment!=null&&!attachment.equals("")){
							JSONArray arr=JSONArray.fromObject(attachment);
							Iterator<?> iter=arr.iterator();
							
							while(iter.hasNext()){
								JSONObject obj=(JSONObject) iter.next();
								String url=obj.getString("url");
								String shorturl=obj.getString("shorturl");
								FairerAttachment newattachment=new FairerAttachment();
								newattachment.setPkFairer(info.getPkFairer());
								newattachment.setAttachmenttype("IMAGE");
								newattachment.setAttachmenturl(url);
								newattachment.setAttachmentshorturl(shorturl);
								newattachment.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								newattachment.setDr((short)0);
								addlist.add(newattachment);
								
								//保存附件上传记录
								UserFile file=new UserFile();
								//保存美丽纪录
								file.setBemodel("平台上传理发师附件");
								file.setFilepath(url);
								file.setThumbnail(shorturl);
								file.setPkUser(info.getPkFairer());
								file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								file.setDr((short)0);
								
								addlist.add(file);
							}
						}	
						//判断是否存在技能，如果存在，则保存
						if(skill!=null&&!skill.equals("")){
							JSONArray arr=JSONArray.fromObject(skill);
							Iterator<?> iter=arr.iterator();
							while(iter.hasNext()){
								JSONObject obj=(JSONObject) iter.next();
								String skillname=obj.getString("skillname");
								FairerSkill newskill=new FairerSkill();
						        newskill.setSkillname(skillname);
						        newskill.setPkFairer(info.getPkFairer());
								newskill.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								newskill.setDr((short)0);
								addlist.add(newskill);
							}
						 }	
						//批量事务处理
						basicservice.batchoperate(addlist, null);
						outputstr("", "保存理发师信息成功", true,null);
					}
				}
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 修改理发师信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String pkShop=getobj.getString("pkShop");
			String islogin=(String)getobj.get("islogin");//是否禁止登陆
			String fairername=getobj.getString("fairername");//发型师名
			String cellphone=getobj.getString("cellphone");//手机号
			String nickname=getobj.getString("nickname");//昵称
			String sex=getobj.getString("sex");//性别
			String age=(String)getobj.get("age");//年龄
			String rankname=(String)getobj.getString("rankname");//职称
			String province=getobj.getString("province");//省
			String city=getobj.getString("city");//市
			String county=getobj.getString("county");//区
			String provincename=getobj.getString("provincename");
			String cityname=getobj.getString("cityname");
			String countyname=getobj.getString("countyname");
			String addr=getobj.getString("addr");//街道
			String identityid=(String)getobj.get("identityid");//身份证号
			String nativeaddr=(String)getobj.get("nativeaddr");//家庭住址
			String urgencypeople=(String)getobj.get("urgency");//紧急联系人
			String attachment=(String)getobj.get("attachment");//判断是否存在附加图片
			String note=(String)getobj.get("note");//备注
			String pkFairer=getobj.getString("pkFairer");
			String skill=(String)getobj.get("skill");//技能
			String headimageurl=(String)getobj.get("headimageurl");//头像
			String headshorturl=(String)getobj.get("headshorturl");//头像缩略图
			
			boolean iscontinue=true;
			
			if(fairername!=null&&!fairername.trim().equals("")){
				boolean flag=PatternUtil.validatename(fairername);
				if(!flag){
					iscontinue=false;
					outputstr("", "姓名不允许包含特殊字符", false,null);
				}
			}
			if(iscontinue){			
				ArrayList<FairerInfo> infos=(ArrayList<FairerInfo>) fairerinfoService.QueryByCellandShop(cellphone, pkShop);
			
				if(infos.size()>0&&!infos.get(0).getPkFairer().toString().equals(pkFairer)){	
					outputstr("", "该手机号的持有者已经是本店的理发师,请查正", false,null);
				}else{			
					ArrayList<Object> oldinfos=(ArrayList<Object>) basicservice.query(FairerInfo.class, " identitycard = ?  and pkShop = ? ", identityid,Long.parseLong(pkShop));
					if(oldinfos.size()>0&&!((FairerInfo)oldinfos.get(0)).getPkFairer().equals(Long.parseLong(pkFairer))){
						outputstr("", "身份证号重复，请查正",false,null);
					}else{
						ArrayList<Object> addlist=new ArrayList<Object>();
						ArrayList<Object> updatelist=new ArrayList<Object>();
							
						FairerInfo info=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
						info.setPkShop(Long.parseLong(pkShop));
						info.setFairername(fairername);
						info.setCellphone(cellphone);
						info.setRankname(rankname);
						info.setNickname(nickname);
						info.setAge((age==null||age.equals(""))?null:Integer.parseInt(age));
						info.setSex(sex);
						info.setProvincename(provincename);
						info.setCityname(cityname);
						info.setCountyname(countyname);
						info.setProvince(province);
						info.setCity(city);
						info.setCounty(county);
						info.setAddr(addr);
						info.setNote(note);
						info.setNativeplace(nativeaddr);
						info.setIdentitycard(identityid);
						info.setUrgencypeople(urgencypeople);
						info.setHeadimageurl(headimageurl);
						info.setHeadshorturl(headshorturl);
						//是否有效
						if(!islogin.equals("")&&islogin.equals("Y")){
							info.setIsvalidate("N");
						}else{
							info.setIsvalidate("Y");
						}
						//附件信息
						if(attachment!=null&&!attachment.equals("")){
							JSONArray arr=JSONArray.fromObject(attachment);
							Iterator<?> iter=arr.iterator();				
							while(iter.hasNext()){
								JSONObject obj=(JSONObject) iter.next();
								String url=obj.get("url")==null?"":obj.getString("url");
								String shorturl=obj.getString("shorturl")==null?"":obj.getString("shorturl");
								String isdelete=obj.get("isdelete")==null?"":obj.getString("isdelete");
								String pkAttachment=obj.get("pkAttachment")==null?"":obj.getString("pkAttachment");
								if(!isdelete.equals("")&&isdelete.equals("Y")){
									FairerAttachment oldsattach=fairerattachmentService.getAttachmentById(Long.parseLong(pkAttachment));
									oldsattach.setDr((short)1);
									oldsattach.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								    updatelist.add(oldsattach);
								}else if(pkAttachment.equals("")){
									FairerAttachment attach=new FairerAttachment();
									attach.setAttachmenttype("IMAGE");
									attach.setAttachmenturl(url);
									attach.setAttachmentshorturl(shorturl);
									attach.setPkFairer(Long.parseLong(pkFairer));
									attach.setDr((short)0);
									attach.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									addlist.add(attach);
									//保存附件上传记录
									UserFile file=new UserFile();
									 //保存美丽纪录
									file.setBemodel("平台上传理发师附件");
									file.setFilepath(url);
									file.setThumbnail(shorturl);
									file.setPkUser(info.getPkFairer());
									file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									file.setDr((short)0);
									addlist.add(file);
								}
							}
						}
						//技能信息
						if(skill!=null&&!skill.equals("")){
							JSONArray arr=JSONArray.fromObject(skill);
							Iterator<?> iter=arr.iterator();
							while(iter.hasNext()){
								JSONObject obj=(JSONObject) iter.next();
								String skillname=obj.getString("skillname");
								String isdelete=obj.get("isdelete")==null?"":obj.getString("isdelete");
								String pkSkill=obj.get("pkSkill")==null?"":obj.getString("pkSkill");
								if(!isdelete.equals("")&&isdelete.equals("Y")){
									FairerSkill oldskill=fairerskillService.getSkillById(Long.parseLong(pkSkill));
									oldskill.setDr((short)1);
									oldskill.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								    updatelist.add(oldskill);
								}else if(pkSkill.equals("")){
									FairerSkill newskill=new FairerSkill();
									newskill.setSkillname(skillname);
									newskill.setPkFairer(Long.parseLong(pkFairer));
									newskill.setDr((short)0);
									newskill.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									addlist.add(newskill);
			
								}
							}
						}
						//时间戳
						info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));		
						 
					    if(!islogin.equals("")&&islogin.equals("Y")){
							info.setIsvalidate("N");
							if(info.getPkUser()!=null&&!info.getPkUser().equals("")){
								UserList user=userlistService.getUserById(info.getPkUser());
								if(user!=null){
									user.setUserstatus("002");
									updatelist.add(user);
								}
							}
						}else{
							info.setIsvalidate("Y");
							if(info.getPkUser()!=null&&!info.getPkUser().equals("")){
								UserList user=userlistService.getUserById(info.getPkUser());
								if(user!=null){
									user.setUserstatus("001");
									updatelist.add(user);
								}
							}
						}
					    updatelist.add(info);
					    basicservice.batchoperate(addlist, updatelist);
					    outputstr("", "维护理发师信息成功", true,null);
					}
				}
			}
		} catch (Exception e) {
			dealexception(e);
		    outputexceptionstr();
		}
		output(response, pojo);
	}
	
     /**
     * 查询理发师业绩
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("findachieve")
	public void findachieve(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String begintime=obj.get("begintime")==null?"":obj.getString("begintime");
			String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
			String fairername=obj.get("fairername")==null?"":obj.getString("fairername");
			String paymode=obj.get("paymode")==null?"":obj.getString("paymode");
			String pkShop=obj.get("pkShop")==null?"":obj.getString("pkShop");
			
			ArrayList<Object> getobj=(ArrayList<Object>) fairerinfoService.Queryrank(begintime, endtime, pkShop,fairername,paymode);
			if(getobj.size()>0){
				String data=JSONArray.fromObject(getobj).toString();
				outputstr(data, "查询排名成功", true,null);
			}else{
				outputstr("", "无理发师信息", true,null);
			}
	  	} catch (Exception e) {
			dealexception(e);
	 		outputexceptionstr();
    	}
	  	output(response, pojo);			
	}
	
}
