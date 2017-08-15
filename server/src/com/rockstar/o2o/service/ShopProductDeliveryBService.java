package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopProductDeliveryB;

public interface ShopProductDeliveryBService {

	/**
	 * 添加一条订货明细
	 * @param member
	 */
	ShopProductDeliveryB save(ShopProductDeliveryB Delivery);
	
	/**
	 * 批量添加订货明细
	 * @param member
	 */
	void savelist(ArrayList<ShopProductDeliveryB> details);
	/**
	 * 批量更新订单明细
	 * @param member
	 */
	void updatelist(ArrayList<ShopProductDeliveryB> details);
	
	/**
	 * 根据ID，获取订货明细
	 * @param id
	 * @return
	 */
	ShopProductDeliveryB getDeliveryById(Long id);
	/**
	 * 根据ID删除订货
	 * @param id
	 */
	void deleteDeliveryById(Long id);
	/**
	 * 修改订货信息
	 * @param id
	 */
	int updateDelivery(ShopProductDeliveryB Delivery);
	/**
	 * 根据主表主键查询子表
	 * @param pk_shop
	 */
	List<ShopProductDeliveryB> QueryByPkBook(String pkDelivery,Integer curpage,Integer pagesize);
}
