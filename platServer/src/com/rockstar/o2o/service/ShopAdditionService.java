package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopAddition;

public interface ShopAdditionService {
	/**
	 * 添加一条附加项目项
	 * @param member
	 */
	ShopAddition save(ShopAddition addition);
	/**
	 * 批量添加附加项目
	 * @param member
	 */
	void savelist(ArrayList<ShopAddition> additions);
	/**
	 * 批量更新附加项目
	 * @param member
	 */
	void updatelist(ArrayList<ShopAddition> additions);
	/**
	 * 根据ID，获取具体附加项目
	 * @param id
	 * @return
	 */
	ShopAddition getAddtionById(Long id);
	/**
	 * 根据ID删除附加项目
	 * @param id
	 */
	void deleteAdditionById(Long id);
	/**
	 * 修改价格
	 * @param id
	 */
	int updateAddition(ShopAddition addition);
	/**
	 * 根据附加项目组查询附加项目
	 * @param pk_shop
	 */
	List<ShopAddition> QueryBygroup(String pkGroup);
	
	/**
	 * 根据套餐查询附加项目
	 * @param pk_shop
	 */
	List<Object> QueryBycombo(String pkshopcombo);
}
