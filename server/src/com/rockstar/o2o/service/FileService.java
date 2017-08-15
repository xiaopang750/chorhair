package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.UserFile;


public interface FileService {
	/**
	 * 添加文件
	 * @param member
	 */
	UserFile save(UserFile file);
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
	 * 根据ID删除一个File
	 * @param id
	 */
	void deleteFileById(Long id);
	/**
	 * 修改file
	 * @param id
	 */
	int updateFile(UserFile file);
	/**
	 * 根据用户id获取所有File
	 * @param id
	 */
	List<UserFile> FindByPkUser(Long pkUser);

}
