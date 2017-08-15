package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.BdCustomerAwardLimit;

public interface BdCustomerAwardLimitService {

	/**
	 * 添加一条奖励限制
	 * @param member
	 */
	BdCustomerAwardLimit save(BdCustomerAwardLimit award);
	/**
	 * 批量添加奖励限制
	 * @param member
	 */
	void savelist(ArrayList<BdCustomerAwardLimit> awards);
	/**
	 * 批量更新奖励限制
	 * @param member
	 */
	void updatelist(ArrayList<BdCustomerAwardLimit> awards);
	/**
	 * 根据ID，获取奖励限制
	 * @param id
	 * @return
	 */
	BdCustomerAwardLimit getAwardById(Long id);
	/**
	 * 根据ID删除奖励限制
	 * @param id
	 */
	void deleteawardById(Long id);
	/**
	 * 修改奖励限制
	 * @param id
	 */
	int updateAward(BdCustomerAwardLimit award);
	/**
	 * 根据具体奖励查询奖励限制
	 * @param pk_shop
	 */
	List<BdCustomerAwardLimit> QueryByPkParent(String pkParent);
	
	
}
