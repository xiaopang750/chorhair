package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.CustomerOrderSpitslot;

public interface CustomerOrderSpitslotService {

	/**
	 * 添加一个吐槽记录
	 * @param member
	 */
	CustomerOrderSpitslot save(CustomerOrderSpitslot Spitslot);
	/**
	 * 根据ID，获取吐槽记录
	 * @param id
	 * @return
	 */
	CustomerOrderSpitslot getSpitslotById(Long id);
	/**
	 * 根据ID删除一个吐槽记录
	 * @param id
	 */
	void deleteSpitslotById(Long id);
	/**
	 * 修改订单吐槽记录
	 * @param id
	 */
	int updateSpitslot(CustomerOrderSpitslot Spitslot);
	/**
	 * 根据订单查询吐槽记录
	 * @param id
	 */
	List<CustomerOrderSpitslot> FindBypkOrder(String PkOrder);
	
	
	
}
