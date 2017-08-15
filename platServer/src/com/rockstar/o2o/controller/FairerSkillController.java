package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.FairerSkill;
import com.rockstar.o2o.util.RequestUtil;

@Controller
@RequestMapping("fairerskill")
public class FairerSkillController extends BaseController {
	/**
	 * 根据理发师查询所有技能
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("findskill")
	public void findlist(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkFairer=obj.getString("pkFairer");
			ArrayList<FairerSkill> list=(ArrayList<FairerSkill>) fairerskillService.QueryByFairer(pkFairer);
			if(list.size()>0){
				String data=JSONArray.fromObject(list).toString();
				outputstr(data, "查询理发师技能成功", true,null);
			}else{
				outputstr("", "暂无理发师技能信息", true,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
	   	}
		  output(response, pojo);
	}
	
	/**
	 * 新增理发师技能
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkFairer=obj.getString("pkFairer");
			String skillname=obj.getString("skillname");
			String skillrank=obj.getString("skillrank");

			FairerSkill skill=new FairerSkill();
			skill.setSkillname(skillname);
			skill.setSkillrank(skillrank);
			skill.setPkFairer(Long.parseLong(pkFairer));
			
			fairerskillService.save(skill);
	
			outputstr("", "添加技能成功", true,null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
	   	}
		  output(response, pojo);
	}
	
	/**
	 * 修改技能内容
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("edit")
	public void edit(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String skillname=obj.getString("skillname");
			String skillrank=obj.getString("skillrank");
			String pkSkill=obj.getString("pkSkill");

			FairerSkill skill=fairerskillService.getSkillById(Long.parseLong(pkSkill));
			skill.setSkillname(skillname);
			skill.setSkillrank(skillrank);
			
			int result=fairerskillService.updateFairer(skill);
			if(result==0){
				outputstr("", "维护技能成功", true,null);
			}else{
				outputstr("", "维护技能失败", false,null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
	   	}
		  output(response, pojo);
	}
	
	
}
