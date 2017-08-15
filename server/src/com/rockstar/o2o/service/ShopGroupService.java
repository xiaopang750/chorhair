package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ShopGroup;
public interface ShopGroupService {

	/**
	 * 添加一条店铺组
	 * @param member
	 */
	ShopGroup save(ShopGroup group);
	/**
	 * 根据ID，获取店铺组
	 * @param id
	 * @return
	 */
	ShopGroup getGroupById(Long id);
	/**
	 * 根据ID删除店铺组
	 * @param id
	 */
	void deleteGroupById(Long id);
	/**
	 * 修改店铺组
	 * @param id
	 */
	int updateGroup(ShopGroup group);
	/**
	 * 根据店铺,类型查询店铺组
	 * @param pk_shop
	 */
	List<ShopGroup> QueryByShop(String pk_shop,String type);
	
	
}
