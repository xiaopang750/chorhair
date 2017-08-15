package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ShopUser;


public interface ShopUserService {

	/**
	 * 添加一个店铺用户
	 * @param member
	 */
	ShopUser save(ShopUser user);
	/**
	 * 根据ID，获取店铺用户
	 * @param id
	 * @return
	 */
	ShopUser getUserById(Long id);
	/**
	 * 获取所有店铺用户
	 * @return 一个成员的泛型
	 */
	List<ShopUser> getAllUser();
	/**
	 * 根据ID删除店铺用户
	 * @param id
	 */
	void deleteUserById(Long id);
	/**
	 * 修改店铺用户
	 * @param id
	 */
	int updateUser(ShopUser user);
	/**
	 * 用户登录验证
	 * @param id
	 */
	ShopUser login(String usercode,String password,String usergroup);
	
	/**
	 * 查询当前最大的用户编号
	 * @param 
	 */
	List<Object> querybysql(String sql);
}
