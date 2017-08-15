package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.CustomerInfo;


public interface CustomerInfoService {
	/**
	 * 添加一条消费者
	 * @param member
	 */
	CustomerInfo save(CustomerInfo info);
	/**
	 * 根据ID，获取消费者
	 * @param id
	 * @return
	 */
	CustomerInfo getCustomerById(Long id);
	/**
	 * 获取所有会员
	 * @return 一个成员的泛型
	 */
	List<CustomerInfo> getAllCustomer();
	/**
	 * 根据ID删除会员
	 * @param id
	 */
	void deleteCustomerById(Long id);
	/**
	 * 修改会员
	 * @param id
	 */
	int updateGroup(CustomerInfo customer);
	/**
	 * 根据店铺查询会员
	 * @param pk_shop
	 */
	List<CustomerInfo> QueryByShop(String pk_shop,Integer curpage,Integer pagesize);
	
	/**
	 * 根据手机号查询会员
	 * @param cellhone
	 */
	CustomerInfo QueryByCellphone(String cellphone);
	
	/**
	 * 根据手机号、店铺查询会员
	 * @param cellhone
	 * @param pk_shop
	 */
	CustomerInfo QueryByCellandShop(String cellphone,String pk_shop);
	
	/**
	 * 查询会员的当前最大的会员编号
	 * @param 
	 */
	List<Object> querybysql(String sql);
	
	/**
	 * 根据姓名,手机号码模糊查询
	 * @param pkShop 
	 * @param likecontent 
	 * @param county 
	 * @param 
	 */
	List<CustomerInfo> querybynameandcell(String province,String city,String county, String likecontent, String pkShop, Integer curpage,Integer pagesize);
	
}
