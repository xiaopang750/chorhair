package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ClientInfo;


public interface ClientInfoService {
	/**
	 * 添加一个客户端信息
	 * @param member
	 */
	ClientInfo save(ClientInfo info);
	/**
	 * 根据ID，获取客户端信息
	 * @param id
	 * @return
	 */
	ClientInfo getClientById(Long id);
	/**
	 * 获取所有客户端信息
	 * @return 一个成员的泛型
	 */
	List<ClientInfo> getAllClienInfo();
	/**
	 * 根据ID删除一个ClientInfo
	 * @param id
	 */
	void deleteInfoById(Long id);
	/**
	 * 修改用户分组信息
	 * @param id
	 */
	int updateInfo(ClientInfo info);
	/**
	 * 根据用户id获取所有ClientInfo
	 * @param id
	 */
	List<ClientInfo> FindByPkUser(Long pkUser);
	/**
	 * 根据uuid获取ClientInfo
	 * @param id
	 */
	List<ClientInfo> FindByuuid(String uuid);
}
