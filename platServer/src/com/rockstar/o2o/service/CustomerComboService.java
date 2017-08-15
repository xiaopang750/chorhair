package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.CustomerCombo;


public interface CustomerComboService {

	/**
	 * 添加一条套餐信息
	 * @param member
	 */
	CustomerCombo save(CustomerCombo combo);
	/**
	 * 批量添加一条套餐信息
	 * @param member
	 */
	void batchsave(ArrayList<CustomerCombo> combos);
	
	/**
	 * 批量更新
	 * @param member
	 */
	void batchupdate(ArrayList<CustomerCombo> combos);
	/**
	 * 根据ID，获取套餐
	 * @param id
	 * @return
	 */
	CustomerCombo getComboById(Long id);

	/**
	 * 根据ID删除套餐
	 * @param id
	 */
	void deleteComboById(Long id);
	/**
	 * 修改会员
	 * @param id
	 */
	int updateCombo(CustomerCombo combo);
	/**
	 * 根据会员查询套餐
	 * @param pk_shop
	 */
	List<CustomerCombo> QueryByPkCustomer(String PkCustomer,Integer curpage,Integer pagesize);
	/**
	 * 根据客户、套餐查询有效的套餐
	 * @param pk_shop
	 */
	CustomerCombo QueryByCombo(String pkShopcombo,String pkCustomer);
	
	
	/**
	 * 根据APP的userid查询用户套餐
	 * @param 
	 */
	List<CustomerCombo> querybyuser(String Pkuser,String fairtype);
	/**
	 * 根据店铺套餐主键查看套餐是否存在
	 * @param pkShopcombo
	 * @return
	 */
	int queryByPkShopcombo(long pkShopcombo);
	
}
