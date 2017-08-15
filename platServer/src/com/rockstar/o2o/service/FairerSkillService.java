package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.FairerSkill;

public interface FairerSkillService {
	/**
	 * 添加一项理发师技能
	 * @param member
	 */
	FairerSkill save(FairerSkill skill);
	/**
	 * 批量添加理发师技能
	 * @param member
	 */
	void savelist(ArrayList<FairerSkill> skills);
	/**
	 * 根据ID，获取理发师技能
	 * @param id
	 * @return
	 */
	FairerSkill getSkillById(Long id);
	/**
	 * 根据ID删除理发师技能
	 * @param id
	 */
	void deleteSkillById(Long id);
	/**
	 * 修改理发师技能
	 * @param id
	 */
	int updateFairer(FairerSkill skill);
	/**
	 * 根据理发师查询理发师技能
	 * @param pk_shop
	 */
	List<FairerSkill> QueryByFairer(String pkFairer);
	
	
}
