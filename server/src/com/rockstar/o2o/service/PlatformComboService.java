package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.PlatformCombo;


public interface PlatformComboService {

	/**
	 * 添加一条平台套餐信息
	 * @param combo
	 */
	PlatformCombo save(PlatformCombo combo);
	/**
	 * 根据ID，获取具体套餐信息
	 * @param id
	 * @return
	 */
	PlatformCombo getComboById(Long id);
	/**
	 * 根据ID删除店铺套餐
	 * @param id
	 */
	void deleteComboById(Long id);
	/**
	 * 修改平台套餐
	 * @param combo
	 */
	int updateCombo(PlatformCombo combo);
	/**
	 * 查询所有套餐
	 */
	List<PlatformCombo> QueryAll();
	
}
