package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.PlatformComboProduct;

public interface PlatformComboProductService {
	/**
	 * 添加一条套餐产品
	 * @param member
	 */
	PlatformComboProduct save(PlatformComboProduct product);
	/**
	 * 根据ID，获取套餐残品
	 * @param id
	 * @return
	 */
	PlatformComboProduct getproductById(Long id);

	/**
	 * 根据ID删除套餐产品
	 * @param id
	 */
	void deleteProductById(Long id);
	/**
	 * 修改套餐产品
	 * @param id
	 */
	int updateproduct(PlatformComboProduct product);
	/**
	 * 根据套餐查询产品
	 * @param PkCustomer
	 */
	List<PlatformComboProduct> QueryByCombo(String pkCombo);
}
