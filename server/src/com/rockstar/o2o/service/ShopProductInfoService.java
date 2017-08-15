package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopProductInfo;


public interface ShopProductInfoService {
	/**
	 * 添加一条店铺商品
	 * @param member
	 */
	ShopProductInfo save(ShopProductInfo info);
	/**
	 * 批量更新商品
	 * @param member
	 */
	void updatelist(ArrayList<ShopProductInfo> infos);
	/**
	 * 根据ID，获取具体产品
	 * @param id
	 * @return
	 */
	ShopProductInfo getInfoById(Long id);
	/**
	 * 根据ID删除产品
	 * @param id
	 */
	void deleteInfoById(Long id);
	/**
	 * 修改商品信息
	 * @param id
	 */
	int updateInfo(ShopProductInfo info);
	/**
	 * 根据店铺查询产品
	 * @param pk_shop
	 */
	List<ShopProductInfo> QueryByShop(String pk_shop,String content,Integer curpage,Integer pagesize);

}
