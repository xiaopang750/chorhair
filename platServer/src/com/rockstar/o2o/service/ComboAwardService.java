package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ComboAward;

public interface ComboAwardService {


	/**
	 * 添加一条奖励
	 * @param member
	 */
	ComboAward save(ComboAward award);
	/**
	 * 批量添加奖励
	 * @param member
	 */
	void savelist(ArrayList<ComboAward> awards);
	/**
	 * 批量更新奖励
	 * @param member
	 */
	void updatelist(ArrayList<ComboAward> awards);
	/**
	 * 根据ID，获取奖励
	 * @param id
	 * @return
	 */
	ComboAward getAwardById(Long id);
	/**
	 * 根据ID删除奖励
	 * @param id
	 */
	void deleteawardById(Long id);
	/**
	 * 修改奖励
	 * @param id
	 */
	int updateAward(ComboAward award);
	/**
	 * 根据套餐查询抵用券
	 * @param pk_shop
	 */
	List<ComboAward> QueryBycombo(String pkcombo);

	/**
	 * 根据套餐查询已经选中的抵用券
	 */
     List<Object> queryselect(String pkcombo);
}
