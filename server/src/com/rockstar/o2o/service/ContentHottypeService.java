package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ContentHottype;

public interface ContentHottypeService {

	/**
	 * 添加一个热门类型
	 * @param member
	 */
	ContentHottype save(ContentHottype type);
	/**
	 * 根据ID，获取类型
	 * @param id
	 * @return
	 */
	ContentHottype getContentById(Long id);
	/**
	 * 获取所有客户端信息
	 * @return 一个成员的泛型
	 */
	List<ContentHottype> getAllContent();
	/**
	 * 根据ID删除一个content
	 * @param id
	 */
	void deletecontentById(Long id);
	/**
	 * 修改content
	 * @param id
	 */
	int updatecontent(ContentHottype content);
	
}
