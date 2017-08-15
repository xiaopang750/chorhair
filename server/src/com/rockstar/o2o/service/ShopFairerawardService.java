package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopFaireraward;

public interface ShopFairerawardService {
	/**
	 * 添加理发师提成
	 * @param member
	 */
	ShopFaireraward save(ShopFaireraward award);
	/**
	 * 批量添加理发师提成项
	 * @param member
	 */
	void savelist(ArrayList<ShopFaireraward> awards);
	/**
	 * 批量添加理发师提成项
	 * @param member
	 */
	List<Object> savelists(ArrayList<ShopFaireraward> awards);
	/**
	 * 批量更新理发师提成项
	 * @param member
	 */
	void updatelist(ArrayList<ShopFaireraward> awards);
	/**
	 * 根据ID，获取理发师提成
	 * @param id
	 * @return
	 */
	ShopFaireraward getawardById(Long id);
	/**
	 * 根据ID删除提成项
	 * @param id
	 */
	void deleteawardById(Long id);
	/**
	 * 修改理发师提成项目
	 * @param id
	 */
	int updateaward(ShopFaireraward award);
	/**
	 * 根据店铺，类型，类型主键查询
	 * @param pkShop
	 * @param pkKey
	 * @param type
	 * @return
	 */
	List<ShopFaireraward> querybyshopandtype(String pkShop,String pkKey,String type);
	
	/**
	 * 根据店铺，类型，类型主键查询有效的
	 * @param pkShop
	 * @param pkKey
	 * @param type
	 * @return
	 */
	List<ShopFaireraward> queryvalidate(String pkShop,String pkKey,String type);
	
}
