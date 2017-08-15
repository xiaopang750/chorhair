package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.PlatformCumboAddition;


public interface PlatformCumboAdditionService {

	/**
	 * 添加一条平台套餐附加项目
	 * @param combo
	 */
	PlatformCumboAddition save(PlatformCumboAddition addition);
	/**
	 * 根据ID，获取具体套餐附加项目
	 * @param id
	 * @return
	 */
	PlatformCumboAddition getAdditionById(Long id);
	/**
	 * 根据ID删除附加项目
	 * @param id
	 */
	void deleteAdditionById(Long id);
	/**
	 * 修改平台附加项目
	 * @param combo
	 */
	int updateAddition(PlatformCumboAddition addition);
	/**
	 * 根据套餐查询附加项目
	 */
	List<PlatformCumboAddition> QueryByPkCombo(String pkCumbo);
}
