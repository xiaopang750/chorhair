package com.rockstar.o2o.service;



import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopPrice;

public interface ShopPriceService {
	/**
	 * 添加一条店铺价格
	 * @param member
	 */
	ShopPrice save(ShopPrice price);
	/**
	 * 批量添加订单明细
	 * @param member
	 */
	void savelist(ArrayList<ShopPrice> prices);
	/**
	 * 批量更新订单明细
	 * @param member
	 */
	void updatelist(ArrayList<ShopPrice> prices);
	/**
	 * 根据ID，获取具体价格
	 * @param id
	 * @return
	 */
	ShopPrice getPriceById(Long id);
	/**
	 * 根据ID删除价格明细
	 * @param id
	 */
	void deletePriceById(Long id);
	/**
	 * 修改价格
	 * @param id
	 */
	int updatePrice(ShopPrice Price);
	/**
	 * 根据店铺、发型类型查询价格
	 * @param pk_shop
	 */
	List<ShopPrice> QueryByShop(String pk_shop,String fairtype);
	

	/**
	 * 根据店铺、价格组查询价格
	 * @param pk_shop
	 */
	List<ShopPrice> QueryBygroup(String pk_shoppricegroup);
	
	
	
}
