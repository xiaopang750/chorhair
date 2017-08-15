package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopComboEditrecord;
public interface ShopComboEditrecordService {
	/**
	 * 添加一条修改记录
	 * @param member
	 */
	ShopComboEditrecord save(ShopComboEditrecord edit);
	/**
	 * 批量添加奖励
	 * @param member
	 */
	void savelist(ArrayList<ShopComboEditrecord> records);
	/**
	 * 根据ID，获取修改记录
	 * @param id
	 * @return
	 */
	ShopComboEditrecord getRecordById(Long id);
	/**
	 * 根据ID删除修改记录
	 * @param id
	 */
	void deleteRecordById(Long id);
	/**
	 * 修改修改记录
	 * @param id
	 */
	int updateRecord(ShopComboEditrecord record);
	/**
	 * 根据店铺查询修改记录
	 * @param pk_shop
	 */
	List<ShopComboEditrecord> QueryByShop(String pk_shop);
	
	/**
	 * 根据套餐查询修改记录
	 * @param pk_shop
	 */
	List<ShopComboEditrecord> QueryByCombo(String pkCombo);
}
