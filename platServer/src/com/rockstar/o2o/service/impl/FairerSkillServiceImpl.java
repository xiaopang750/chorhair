package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.FairerSkill;
import com.rockstar.o2o.service.FairerSkillService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class FairerSkillServiceImpl extends BaseServiceImpl implements FairerSkillService{

	@Override
	public FairerSkill save(FairerSkill skill) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(skill);
		baseDao.add(skill);
		skill.setPkSkill(Long.parseLong(pk.toString()));
		return skill;
	}

	@Override
	public FairerSkill getSkillById(Long id) {
		// TODO Auto-generated method stub
		return (FairerSkill) baseDao.getById(FairerSkill.class,id);
	}

	@Override
	public void deleteSkillById(Long id) {
		// TODO Auto-generated method stub
		FairerSkill skill=(FairerSkill) baseDao.getById(FairerSkill.class, id);
		skill.setDr((short) 1);
		skill.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(skill);
	}

	@Override
	public int updateFairer(FairerSkill skill) {
		// TODO Auto-generated method stub
		int result=baseDao.update(skill);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FairerSkill> QueryByFairer(String pkFairer) {
		// TODO Auto-generated method stub
		Long longpkFairer=Long.parseLong(pkFairer);
		List<FairerSkill> skilllists=(List<FairerSkill>) baseDao.pageQuery("from FairerSkill where IFNULL(dr,0)=0 and pkFairer = ?  ",longpkFairer);
        return skilllists;
	}

	@Override
	public void savelist(ArrayList<FairerSkill> skills) {
		// TODO Auto-generated method stub
		baseDao.batchsave(skills);
	}

}
