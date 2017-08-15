package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.PlatformArea;


public interface PlatformAreaService {
	
    /**
     * 添加一个区域
     * @param product
     * @return
     */
	PlatformArea save(PlatformArea area);
	/**
	 * 根据ID，获取区域
	 * @param id
	 * @return
	 */
	PlatformArea getareaById(Long id);

	/**
	 * 根据ID删除区域
	 * @param id
	 */
	void deleteAreaById(Long id);
	/**
	 * 修改区域
	 * @param id
	 */
	int updatearea(PlatformArea area);
	/**
	 * 根据所属层级查询
	 * @param PkCustomer
	 */
	List<PlatformArea> QueryByLevel(String level);
	/**
	 * 根据父级查询
	 * @param PkCustomer
	 */
	List<PlatformArea> QueryByFather(String pkFather);
	
}
