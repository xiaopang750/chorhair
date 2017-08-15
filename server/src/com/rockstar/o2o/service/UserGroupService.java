package com.rockstar.o2o.service;

import java.util.List;


import com.rockstar.o2o.pojo.UserGroup;

/**
 * 用户分组业务接口
 * @author xc
 *
 */
public interface UserGroupService {
	/**
	 * 添加一个用户分组
	 * @param member
	 */
	UserGroup save(UserGroup group);
	/**
	 * 根据ID，获取一个用户分组
	 * @param id
	 * @return
	 */
	UserGroup getGroupById(Long id);
	/**
	 * 获取所有用户分组
	 * @return 一个成员的泛型
	 */
	List<UserGroup> getAllgroup();
	/**
	 * 根据ID删除一个用户分组
	 * @param id
	 */
	void deleteGroupById(Long id);
	/**
	 * 修改用户分组信息
	 * @param id
	 */
	void updateGroup(UserGroup group);
}
