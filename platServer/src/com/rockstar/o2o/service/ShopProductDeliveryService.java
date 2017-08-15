package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ShopProductDelivery;


public interface ShopProductDeliveryService {
	/**
	 * 添加一条出库单
	 * @param member
	 */
	ShopProductDelivery save(ShopProductDelivery book);
	/**
	 * 根据ID，获取出库单
	 * @param id
	 * @return
	 */
	ShopProductDelivery getDeliveryById(Long id);
	/**
	 * 根据ID删除出库
	 * @param id
	 */
	void deleteDeliveryById(Long id);
	/**
	 * 修改订货信息
	 * @param id
	 */
	int updateInfo(ShopProductDelivery delivery);
	/**
	 * 根据店铺查询所有出库
	 * @param pk_shop
	 */
	List<ShopProductDelivery> QueryByShop(String pk_shop,Integer curpage,Integer pagesize);

	/**
	 * 查询当前最大的出库编号
	 * @param 
	 */
	List<Object> queryMaxNo(String code,String pkShop);
}
