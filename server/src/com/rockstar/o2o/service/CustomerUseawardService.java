package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.CustomerUseaward;

public interface CustomerUseawardService {

	/**
	 * 添加一条奖励
	 * @param member
	 */
	CustomerUseaward save(CustomerUseaward award);
	/**
	 * 批量添加奖励
	 * @param member
	 */
	void savelist(ArrayList<CustomerUseaward> awards);
	/**
	 * 批量更新奖励
	 * @param member
	 */
	void updatelist(ArrayList<CustomerUseaward> awards);
	/**
	 * 根据ID，获取奖励
	 * @param id
	 * @return
	 */
	CustomerUseaward getAwardById(Long id);
	/**
	 * 根据ID删除奖励
	 * @param id
	 */
	void deleteawardById(Long id);
	/**
	 * 修改奖励
	 * @param id
	 */
	int updateAward(CustomerUseaward award);
	/**
	 * 根据消费者查询所有的适用记录
	 * @param pk_shop
	 */
	List<CustomerUseaward> QueryBypkCustomer(String pkCustomer);
	
	/**
	 * 根据消费者、抵用券查询适用情况
	 * @param pk_shop
	 */
	List<CustomerUseaward> QueryBycustomerandaward(String pkCustomer,String pkaward);
	
}
