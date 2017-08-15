package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.vo.ShopUserCombo;



public interface ShopComboService {
	/**
	 * 添加一条店铺套餐信息
	 * @param member
	 */
	ShopCombo save(ShopCombo combo);
	/**
	 * 根据ID，获取具体套餐信息
	 * @param id
	 * @return
	 */
	ShopCombo getComboById(Long id);
	/**
	 * 根据ID删除店铺套餐
	 * @param id
	 */
	void deleteComboById(Long id);
	/**
	 * 修改店铺套餐
	 * @param id
	 */
	int updateCombo(ShopCombo combo);
	/**
	 * 根据店铺查询套餐
	 * @param pk_shop
	 */
	List<ShopCombo> QueryByShop(String pk_shop,String querylike,Integer curpage,Integer pagesize);
	
	/**
	 * 根据店铺、用户查询查询套餐
	 * @param pk_shop
	 * @param pk_customer
	 */
	List<ShopUserCombo> QueryByShopAndCustomer(String pk_shop,String pk_customer);
	
	/**
	 * 根据店铺、性别查询查询套餐
	 * @param pk_shop
	 * @param pk_customer
	 */
	List<ShopCombo> QueryByShopAndSex(String pk_shop,String querylike,String sex,Integer curpage,Integer pagesize);
	
	/**
	 * 根据店铺、套餐类型、发型类型查询查询套餐
	 * @param pk_shop
	 * @param pk_customer
	 */
	List<ShopCombo> QueryByShopAndtype(String pk_shop,String fairtype,String combotype);
	/**
	 * 根据平台套餐主键查询店铺id集合
	 * @param pkCombo
	 * @return
	 */
	List<Object> queryByPkCombo(long pkCombo);
	/**
	 * 根据平台套餐主键和店铺id 查询套餐
	 * @param pkShop
	 * @param pkCombo
	 * @return
	 */
	ShopCombo queryByShopAndCombo(long pkShop, long pkCombo);
	
}
