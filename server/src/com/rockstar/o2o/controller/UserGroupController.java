package com.rockstar.o2o.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private static Logger log = Logger.getLogger(UserGroupController.class);
	
	public UserGroupController(){
		pojo=new ReturnPojo();
	}
	/**
	 * 添加用户分组
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
	public void saveUsergroup(HttpServletRequest request,HttpServletResponse response,Model model) {		
		JSONObject obj=RequestUtil.getPostString(request);
		UserGroup group = new UserGroup();
		group.setGroupname(obj.getString("groupname"));
		group.setGroupcode(obj.getString("groupcode"));
		group.setGroupnote(obj.getString("groupnote"));		
		group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));		
		group.setDr((short)0);		
		group=usergroupService.save(group);
		pojo.setMsg("查询成功");
		pojo.setIssuccess(true);
		pojo.setData(JSONObject.fromObject(group).toString());
		output(response,pojo);
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
			log.info("根据ID删除用户分组失败");
			log.error(e.getMessage(), e);
		}

	}

	
}
