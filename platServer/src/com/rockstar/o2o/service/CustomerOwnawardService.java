package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.CustomerOwnaward;

public interface CustomerOwnawardService {

	/**
	 * 添加一条奖励
	 * @param member
	 */
	CustomerOwnaward save(CustomerOwnaward award);
	/**
	 * 批量添加奖励
	 * @param member
	 */
	void savelist(ArrayList<CustomerOwnaward> awards);
	/**
	 * 批量更新奖励
	 * @param member
	 */
	void updatelist(ArrayList<CustomerOwnaward> awards);
	/**
	 * 根据ID，获取奖励
	 * @param id
	 * @return
	 */
	CustomerOwnaward getAwardById(Long id);
	/**
	 * 根据ID删除奖励
	 * @param id
	 */
	void deleteawardById(Long id);
	/**
	 * 修改奖励
	 * @param id
	 */
	int updateAward(CustomerOwnaward award);
	/**
	 * 根据消费者查询所有的抵用券
	 * @param pk_shop
	 */
	List<CustomerOwnaward> QueryBypkCustomer(String pkCustomer);
	
	/**
	 * 根据消费者、套餐适用的抵用券
	 * @param pk_shop
	 */
	List<CustomerOwnaward> QueryBycustomerandcombo(String pkCustomer,String pkcombo);
	
	
}
