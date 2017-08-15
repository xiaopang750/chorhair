package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.pojo.ComboAward;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.pojo.PlatformComboProduct;
import com.rockstar.o2o.pojo.UserGroup;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.ReturnPojo;

/**
 * 用户分组controller
 * @author xc
 *
 */
@Controller
@RequestMapping("/usergroup")
public class UserGroupController extends BaseController{
	/**
	 * 添加用户分组
	 * @param model
	 * @return
	 */
	@RequestMapping("/savegroup")
	public void saveUsergroup(HttpServletRequest request,HttpServletResponse response,Model model) {
		try{
			JSONObject obj=RequestUtil.getPostString(request);
			//组名
			String groupname=obj.getString("groupname");
			//组编码
			String groupcode=(String)obj.get("groupcode");
			//组对象
			String groupobject=(String)obj.get("groupobject");
			//备注
			String groupnote=(String)obj.get("groupnote");		
			//备注
			String users=(String)obj.get("users");
			
			UserGroup group = new UserGroup();
			group.setGroupname(groupname);
			group.setGroupcode(groupcode);
			group.setGroupobject(groupobject);
			group.setGroupnote(groupnote);		
			group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));		
			group.setDr((short)0);		
			group=usergroupService.save(group);
			ArrayList<Object> editlist=new ArrayList<Object>();
			if(users!=null&&!users.equals("")){
				//消费者
				if(groupobject.equals(UserGroupObject.GROUP_ONE[0])){
					JSONArray arr=JSONArray.fromObject(users);
					Iterator<?> iter=arr.iterator();
					while(iter.hasNext()){
						JSONObject oneobj=(JSONObject) iter.next();
						String pkCustomer=oneobj.getString("pkKey");
						CustomerInfo customerInfo=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));		
						customerInfo.setPkGroup(group.getPkGroup());
						customerInfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));;
						//加入批量添加事务中
						editlist.add(customerInfo);
					}
				//发型师
				}else if(groupobject.equals(UserGroupObject.GROUP_TWO[0])){
					JSONArray arr=JSONArray.fromObject(users);
					Iterator<?> iter=arr.iterator();
					while(iter.hasNext()){
						JSONObject oneobj=(JSONObject) iter.next();
						String pkFairer=oneobj.getString("pkKey");
						FairerInfo fairerInfo=fairerinfoService.getFairerById(Long.parseLong(pkFairer));		
						fairerInfo.setPkGroup(group.getPkGroup());
						fairerInfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));;
						//加入批量添加事务中
						editlist.add(fairerInfo);
					}	
				}
				//批量操作
				basicservice.batchoperate(null, editlist);
			}
			outputstr("", "保存分组成功", true, null);
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 修改用户分组
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String EditUsergroup(HttpServletRequest request,Model model) {	
		UserGroup group = new UserGroup();
		JSONObject obj=RequestUtil.getPostString(request);
		group.setPkGroup(Long.parseLong(obj.getString("pkgroup")));
		group.setGroupname(obj.getString("groupname"));
		group.setGroupcode(obj.getString("groupcode"));
		group.setGroupnote(obj.getString("groupnote"));		
		group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));		
		group.setDr((short)0);		
		usergroupService.updateGroup(group);
		return "edit";
	}
	
	
	/**
	 * 获取用户分组
	 * @param model
	 * @return
	 */
	@RequestMapping("/showAll")
	public void getAllUserGroup(HttpServletResponse response,Model model) {
		List<UserGroup> groupList = usergroupService.getAllgroup();
		pojo.setMsg("查询成功");
		pojo.setIssuccess(true);
		pojo.setData(JSONArray.fromObject(groupList).toString());
		output(response,pojo);
		
	}
	
	/**
	 * 根据ID，获取一个用户分组，以json格式输出到页面
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/showById")
	public void getGrouprById(HttpServletRequest request,HttpServletResponse response,Model model) {
		String sid = RequestUtil.getString(request, "id", null);
		Long id = Long.parseLong(sid);
		UserGroup group = usergroupService.getGroupById(id);
		model.addAttribute("group", group);
		pojo.setMsg("查询成功");
		pojo.setIssuccess(true);
		pojo.setData(JSONObject.fromObject(group).toString());
		output(response,pojo);
	}
	
	@RequestMapping("/delById")
	public void deleteGroupById(HttpServletRequest request,HttpServletResponse response,Model model) {
		String sid = RequestUtil.getString(request, "id", null);
		if(sid==null){
		}
		try {
			usergroupService.deleteGroupById(Long.parseLong(sid));
			pojo.setMsg("删除成功");
			pojo.setIssuccess(true);
			output(response,pojo);
		} catch (Exception e) {
			pojo.setMsg("删除失败");
			pojo.setIssuccess(false);
			output(response,pojo);
		}

	}

	
}
