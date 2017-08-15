package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.PlatUser;

public interface PlatUserService {
	/**
	 * 平台用户登录验证
	 * @param id
	 */
	PlatUser login(String usercode,String password);
	/**
	 * 根据ID，获取店铺用户
	 * @param id
	 * @return
	 */
	PlatUser getUserById(long id);
	/**
	 * 查询当前最大的用户编号
	 * @param 
	 */
	List<Object> querybysql(String sql);
	/**
	 * 添加一个平台用户
	 * @param member
	 */
	PlatUser save(PlatUser user);
	/**
	 * 修改平台用户
	 * @param user
	 * @return
	 */
	int updateUser(PlatUser user);
}
