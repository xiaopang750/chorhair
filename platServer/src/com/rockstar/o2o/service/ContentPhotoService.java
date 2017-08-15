package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ContentPhoto;


public interface ContentPhotoService {

	/**
	 * 添加一个内容图片
	 * @param member
	 */
	ContentPhoto save(ContentPhoto photo);
	
	/**
	 * 批量添加内容图片
	 * @param member
	 */
	void savelist(ArrayList<ContentPhoto> photos);
	/**
	 * 根据ID，获取内容图片
	 * @param id
	 * @return
	 */
	ContentPhoto getPhotoById(Long id);
	/**
	 * 根据ID删除一个图片
	 * @param id
	 */
	void deletephotoById(Long id);
	/**
	 * 修改content
	 * @param id
	 */
	int updatephoto(ContentPhoto photo);
	/**
	 * 根据内容主键查询
	 * @param id
	 */
	List<ContentPhoto> querybypkContent(String pkContent);
	
}
