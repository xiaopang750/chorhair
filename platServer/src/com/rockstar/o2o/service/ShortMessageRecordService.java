package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.ShortmessageRecord;


public interface ShortMessageRecordService {
	/**
	 * 添加一条短信
	 * @param member
	 */
	ShortmessageRecord save(ShortmessageRecord message);
	/**
	 * 根据ID，获取短信
	 * @param id
	 * @return
	 */
	ShortmessageRecord getMessageById(Long id);
	/**
	 * 获取所有短信
	 * @return 一个成员的泛型
	 */
	List<ShortmessageRecord> getAllmessage();
	/**
	 * 根据ID删除短信
	 * @param id
	 */
	void deleteMessageById(Long id);
	/**
	 * 修改短信
	 * @param id
	 */
	void updateGroup(ShortmessageRecord message);
	/**
	 * 根据电话号码、用户分组,验证码查询是否成功
	 * @param id
	 */
	ShortmessageRecord QueryBysql(String cellphone,String usergroup,String messagetype,String code);
	/**
	 * 根据电话号码、用户分组、短信类型判断是否已经有发送过的验证码，存在的话，将dr置为1
	 * @param id
	 */
	int UpdateOldMessage(String cellphone,String usergroup,String messagetype);
	
}
