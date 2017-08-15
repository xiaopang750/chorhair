package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.PlatformComboService;


public interface PlatformComboServiceService {
	/**
	 * 添加一条套餐服务
	 * @param member
	 */
	PlatformComboService save(PlatformComboService service);
	/**
	 * 根据ID，获取套餐服务
	 * @param id
	 * @return
	 */
	PlatformComboService getServiceById(Long id);

	/**
	 * 根据ID删除套餐服务
	 * @param id
	 */
	void deleteServiceById(Long id);
	/**
	 * 修改套餐使用记录
	 * @param id
	 */
	int updateCombo(PlatformComboService service);
	/**
	 * 根据套餐查询服务
	 * @param PkCustomer
	 */
	List<PlatformComboService> QueryByCombo(String pkCombo);
	
	
}
