package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;
import com.rockstar.o2o.pojo.CustomerOrderBeautyrecord;

public interface CustomerOrderBeautyrecordService {

	/**
	 * 添加一条美丽记录
	 * @param member
	 */
	CustomerOrderBeautyrecord save(CustomerOrderBeautyrecord record);
	/**
	 * 批量添加魅力纪录
	 * @param member
	 */
	void savelist(ArrayList<CustomerOrderBeautyrecord> records);
	/**
	 * 根据ID，获取一条美丽记录
	 * @param id
	 * @return
	 */
	CustomerOrderBeautyrecord getRecordById(Long id);
	/**
	 * 根据ID删除美丽记录
	 * @param id
	 */
	void deleteRecordById(Long id);
	/**
	 * 修改美丽记录
	 * @param id
	 */
	int updateRecord(CustomerOrderBeautyrecord record);
	/**
	 * 根据订单查询美丽纪录
	 * @param pk_shop
	 */
	List<CustomerOrderBeautyrecord> QueryByOrder(String pkOrder);	
	
	/**
	 * 根据消费者查询最近三条记录
	 * @param pk_shop
	 */
	List<Object> QueryByPkcustomer(String pkCustomer);	
	
}
