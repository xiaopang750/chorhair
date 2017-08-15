package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.MessageList;

public interface MessageListService {
	/**
	 * 添加一条消息列表
	 * @param member
	 */
	MessageList save(MessageList message);
	
	/**
	 * 批量添加信息
	 * @param member
	 */
	void savelist(ArrayList<MessageList> messages);
	/**
	 * 根据ID，获取信息
	 * @param id
	 * @return
	 */
	MessageList getMessageById(Long id);
	/**
	 * 获取所有信息
	 * @return 一个成员的泛型
	 */
	List<MessageList> getAllmessage();
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteMessageById(Long id);
	/**
	 * 修改信息
	 * @param id
	 */
	void updateMessage(MessageList message);

	/**
	 * 查询用户查询会话列表
	 * @param id
	 */
	List<MessageList> findlistbyUser(String PkUser);
	
	
	/**
	 * 查询所有的系统消息发送列表
	 * @param id
	 */
	List<MessageList> queryallplat(String messagetype,String pkShop);
	
}
