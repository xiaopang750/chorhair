package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ContentHotsearch;

public interface ContentHotsearchService {

	/**
	 * 添加一个热门搜索
	 * @param member
	 */
	ContentHotsearch save(ContentHotsearch search);
	/**
	 * 根据ID，获取热门搜索
	 * @param id
	 * @return
	 */
	ContentHotsearch getContentById(Long id);
	/**
	 * 获取所有热门搜索
	 * @return 一个成员的泛型
	 */
	List<ContentHotsearch> getAllContent();
	/**
	 * 根据ID删除一个热门搜索
	 * @param id
	 */
	void deletecontentById(Long id);
	/**
	 * 修改热门搜索
	 * @param id
	 */
	int updatecontent(ContentHotsearch content);
	
	/**
	 * 根据类型搜索热门词
	 * @return
	 */
	List<Object> getbytype(String pkType);
	
	
}
