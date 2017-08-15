package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ContentTag;

public interface ContentTagService {
	/**
	 * 添加一个内容标签
	 * @param member
	 */
	ContentTag save(ContentTag photo);
	/**
	 * 批量添加内容标签
	 * @param member
	 */
	void savelist(ArrayList<ContentTag> tags);
	/**
	 * 根据ID，获取内容图片
	 * @param id
	 * @return
	 */
	ContentTag getTagById(Long id);
	/**
	 * 根据ID删除一个图片
	 * @param id
	 */
	void deletetagById(Long id);
	/**
	 * 修改content
	 * @param id
	 */
	int updatetag(ContentTag tag);
	/**
	 * 根据内容主键查询
	 * @param id
	 */
	List<ContentTag> querybypkContent(String pkContent);
}
