package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.UserList;


public interface UserListService {
	/**
	 * 添加一个用户
	 * @param member
	 */
	UserList save(UserList user);
	/**
	 * 根据ID，获取一个用户
	 * @param id
	 * @return
	 */
	UserList getUserById(Long id);
	/**
	 * 获取所有用户
	 * @return 一个成员的泛型
	 */
	List<UserList> getAllUser();
	/**
	 * 根据ID删除一个用
	 * @param id
	 */
	void deleteUserById(Long id);
	/**
	 * 修改用户分组信息
	 * @param id
	 */
	int updateUser(UserList user);
	/**
	 * 用户登录验证
	 * @param id
	 */
	UserList login(String cellphone,String password,String usergroup);
	/**
	 * 根据电话和用户分组判断是否存在
	 * @param id
	 */
	UserList findbycellandgroup(String cellphone,String usergroup);
}
