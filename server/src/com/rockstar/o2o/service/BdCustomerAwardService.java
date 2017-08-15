package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.BdCustomerAward;

public interface BdCustomerAwardService {

	/**
	 * 添加一条奖励
	 * @param member
	 */
	BdCustomerAward save(BdCustomerAward award);
	/**
	 * 批量添加奖励
	 * @param member
	 */
	void savelist(ArrayList<BdCustomerAward> awards);
	
	/**
	 * 批量添加奖励
	 * @param member
	 */
	
	List<Object> savelists(ArrayList<BdCustomerAward> awards);
	/**
	 * 批量更新奖励
	 * @param member
	 */
	void updatelist(ArrayList<BdCustomerAward> awards);
	/**
	 * 根据ID，获取奖励
	 * @param id
	 * @return
	 */
	BdCustomerAward getAwardById(Long id);
	/**
	 * 根据ID删除奖励
	 * @param id
	 */
	void deleteawardById(Long id);
	/**
	 * 修改奖励
	 * @param id
	 */
	int updateAward(BdCustomerAward award);
	/**
	 * 查询店铺所有奖励
	 * @param pk_shop
	 */
	List<BdCustomerAward> QueryBypkShop(String pkshop,String type);
	
	/**
	 * 根据抵用券查询所有已经选中的套餐
	 * @param pkCustomeraward
	 * @return
	 */
	List<Object> queryselect(String pkCustomeraward);
	
	/**
	 * 根据组查询抵用券
	 * @param pk_shop
	 */
	List<BdCustomerAward> QueryByGroup(String pkshopGroup);
	
}
