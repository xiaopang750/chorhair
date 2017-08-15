package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.UserFile;

public interface UserFileService {

	/**
	 * 添加一条文件
	 * @param member
	 */
	UserFile save(UserFile file);
	
	/**
	 * 添加一条文件
	 * @param member
	 */
	void batchsave(ArrayList<UserFile> file);
	
	
	/**
	 * 根据ID，获取文件
	 * @param id
	 * @return
	 */
	UserFile getFileById(Long id);
	/**
	 * 获取所有文件
	 * @return 一个成员的泛型
	 */
	List<UserFile> getAllFile();
	/**
	 * 根据ID删除文件
	 * @param id
	 */
	void deleteFileById(Long id);
	
}
