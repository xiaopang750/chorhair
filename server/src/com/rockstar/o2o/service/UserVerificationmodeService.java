package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.UserVerificationmode;



public interface UserVerificationmodeService {
	/**
	 * 添加用户验证信息
	 * @param member
	 */
	UserVerificationmode save(UserVerificationmode mode);
	/**
	 * 根据ID，获取用户验证信息
	 * @param id
	 * @return
	 */
	UserVerificationmode getModeById(Long id);
	/**
	 * 获取所有用户信息
	 * @return 一个成员的泛型
	 */
	List<UserVerificationmode> getAllmode();
	/**
	 * 根据ID删除用户信息
	 * @param id
	 */
	void deleteModeById(Long id);
	/**
	 * 修改用户验证信息
	 * @param id
	 */
	int updateGroup(UserVerificationmode mode);
	/**
	 * 根据用户id查询用户所有的验证方式
	 * @param pkUser
	 */
	List<UserVerificationmode> QueryByPkuser(Long pkUser);
	/**
	 * 根据openid和验证方式查询
	 * @param openid,type
	 */
	UserVerificationmode QueryByTwo(String openid,String type);
}
