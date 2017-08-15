package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.CustomerComboUserecord;

public interface CustomerComboUserecordService {
	/**
	 * 添加一条套餐使用记录
	 * @param member
	 */
	CustomerComboUserecord save(CustomerComboUserecord record);
	/**
	 * 根据ID，获取套餐使用记录
	 * @param id
	 * @return
	 */
	CustomerComboUserecord getComboById(Long id);

	/**
	 * 根据ID删除套餐使用记录
	 * @param id
	 */
	void deleteComboById(Long id);
	/**
	 * 修改套餐使用记录
	 * @param id
	 */
	int updateCombo(CustomerComboUserecord combo);
	/**
	 * 根据会员查询套餐使用记录
	 * @param PkCustomer
	 */
	List<CustomerComboUserecord> QueryByPkCustomer(String PkCustomer);
	
	/**
	 * 根据会员、套餐查询套餐使用记录
	 * @param PkCustomer
	 */
	List<CustomerComboUserecord> QueryByPkCustomerAndCombo(String PkCustomer,String pkShopCombo);
	
}
