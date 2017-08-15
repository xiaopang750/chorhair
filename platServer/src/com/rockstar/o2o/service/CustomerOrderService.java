package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.CustomerOrder;


public interface CustomerOrderService {
	/**
	 * 添加一个用户订单
	 * @param member
	 */
	CustomerOrder save(CustomerOrder order);
	/**
	 * 根据ID，获取一个用户订单
	 * @param id
	 * @return
	 */
	CustomerOrder getOrderById(Long id);
	/**
	 * 根据ID删除一个订单
	 * @param id
	 */
	void deleteOrderById(Long id);
	/**
	 * 修改订单信息
	 * @param id
	 */
	int updateOrder(CustomerOrder order);
	/**
	 * 根据用户查询订单列表
	 * @param id
	 */
	List<CustomerOrder> FindByCustomer(String PkCustomer);
	/**
	 * 根据店铺、日期查询订单数量
	 * @param id
	 */
	int FindByShopAndDate(String pkShop,String getdate);
	
	/**
	 * 查询店铺当天订单数
	 * @param id
	 */
	int FindShopDayOrder(String getdate);
	
	/**
	 * 根据店铺、日期、付款状态查询订单数量
	 * @param nameOrTellphone 
	 * @param id
	 */
	List<CustomerOrder> FindByShopAndDateAndPay(String province,String city,String county,String street,String pkShop,String begintime,String endtime,String orderstatus,String nameOrTellphone, Integer curpage,Integer pagesize);
	
	/**
	 * 查询订单号
	 * @param 
	 */
	List<Object> querybysql(String code,String pkShop);
	
	/**
	 * 根据pkUser查询所有订单
	 * @param id
	 */
	List<CustomerOrder> FindByPkUser(String pkUser,String usergroup);
	
	/**
	 * 根据pkUser查詢美麗記錄
	 * @param id
	 */
	List<CustomerOrder> FindBeautyByPkUser(String pkUser);
	
	
	/**
	 * 根据套餐、消费者查询订单
	 * @param id
	 */
	List<CustomerOrder> FindByComboAndPkcustomer(String pkCustomer,String pkShopcombo);
	
	/**
	 * 根据pkUser,状态查询所有订单
	 * @param id
	 */
	List<CustomerOrder> FindByPkUserAndStatus(String pkUser,String usergroup,String status);
	
	
	/**
	 * 根据日期查询业绩
	 * @param paymode 
	 * @param id
	 */
	List<Object> findbytime(String begintime,String endtime,String type,String pkShop, String paymode);
	
	/**
	 * 根据店铺查询所有订单
	 * @param orderno 
	 * @param id
	 */
	List<CustomerOrder> querybyshop(String pkShop,Integer curpage,Integer pagesize,String begintime,String endtime,String paystatus, String orderno);
	
	
	/**
	 * 根据店铺,状态,时间查询订单
	 * @param pkShop 
	 * @param county 
	 * @param city 
	 * @param likequery 
	 * @param id
	 */
	List<CustomerOrder> querybycon(String province,String city, String county, String pkShop, Integer curpage,Integer pagesize,String begintime,String endtime,String status, String likequery);
	
	
	
}
