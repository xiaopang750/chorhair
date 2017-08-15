package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ContentManage;

public interface ContentManageService {

	/**
	 * 添加一个内容
	 * @param member
	 */
	ContentManage save(ContentManage manage);
	/**
	 * 根据ID，获取内容信息
	 * @param id
	 * @return
	 */
	ContentManage getContentById(Long id);
	/**
	 * 获取所有客户端信息
	 * @return 一个成员的泛型
	 */
	List<ContentManage> getAllContent(String pkShop,Integer curpage,Integer pagesize);
	/**
	 * 根据ID删除一个content
	 * @param id
	 */
	void deletecontentById(Long id);
	/**
	 * 修改content
	 * @param id
	 */
	int updatecontent(ContentManage content);

	/**
	 * 根据类型主键和类型查询
	 * 
	 */
	List<ContentManage> querybytype(String pkType,String contenttype);
	
}
