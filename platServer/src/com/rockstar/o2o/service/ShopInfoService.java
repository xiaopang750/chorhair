package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.ShopInfo;

public interface ShopInfoService {
	/**
	 * 添加一家店铺
	 * @param member
	 */
	ShopInfo save(ShopInfo info);
	/**
	 * 根据ID，获取店铺
	 * @param id
	 * @return
	 */
	ShopInfo getShopById(Long id);
	/**
	 * 获取所有店铺
	 * @return 一个成员的泛型
	 */
	List<ShopInfo> getAllShop();
	/**
	 * 根据ID删除店铺
	 * @param id
	 */
	void deleteShopById(Long id);
	/**
	 * 修改店铺
	 * @param id
	 */
	int updateGroup(ShopInfo shop);
	/**
	 * 分页查询店铺
	 * @param street 
	 * @param area 
	 * @param city 
	 * @param province 
	 * @param pkShop 
	 * @param curpage
	 * @param pagesize
	 */
	List<ShopInfo> QueryShopByPagination(String province, String city, String county, String street, String pkShop, Integer curpage,Integer pagesize);
	/**
	 * 根据shopcode查询
	 * @param shopcode
	 * @param pkShop 
	 */
	int QueryByShopcode(String shopcode, String pkShop);
	/**
	 * 
	 * @param province
	 * @param 获取已有店铺地址下拉列表
	 * @return
	 */
	List<ShopInfo> getAllShopAddr(String fatherArea, String level);
}
