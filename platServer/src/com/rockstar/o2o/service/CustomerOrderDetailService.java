package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.CustomerOrderDetail;


public interface CustomerOrderDetailService {
	/**
	 * 添加一条订单明细
	 * @param member
	 */
	CustomerOrderDetail save(CustomerOrderDetail detail);
	
	/**
	 * 批量添加订单明细
	 * @param member
	 */
	void savelist(ArrayList<CustomerOrderDetail> details);
	
	/**
	 * 批量更新订单明细
	 * @param member
	 */
	void updatelist(ArrayList<CustomerOrderDetail> details);
	/**
	 * 根据ID，获取订单明细
	 * @param id
	 * @return
	 */
	CustomerOrderDetail getDetailById(Long id);
	/**
	 * 根据ID删除订单明细
	 * @param id
	 */
	void deleteDetailById(Long id);
	/**
	 * 修改订单明细
	 * @param id
	 */
	int updateGroup(CustomerOrderDetail detail);
	/**
	 * 根据订单查询订单明细
	 * @param pk_shop
	 */
	List<CustomerOrderDetail> QueryByPkOrder(String pkOrder);
	
}
