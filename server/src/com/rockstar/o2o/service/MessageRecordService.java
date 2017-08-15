package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.MessageRecord;


public interface MessageRecordService {
	/**
	 * 添加一条短信
	 * @param member
	 */
	MessageRecord save(MessageRecord message);
	
	/**
	 * 批量添加信息
	 * @param member
	 */
	void savelist(ArrayList<MessageRecord> messages);
	
	/**
	 * 根据ID，获取短信
	 * @param id
	 * @return
	 */
	MessageRecord getMessageById(Long id);
	/**
	 * 获取所有短信
	 * @return 一个成员的泛型
	 */
	List<MessageRecord> getAllmessage();
	/**
	 * 根据ID删除短信
	 * @param id
	 */
	void deleteMessageById(Long id);
	/**
	 * 修改短信
	 * @param id
	 */
	void updateGroup(MessageRecord message);
	/**
	 * 根据电话号码、用户分组,验证码查询是否成功
	 * @param cellphone,usergroup,messagetype,code
	 */
	MessageRecord QueryBysql(String cellphone,String usergroup,String messagetype,String code);
	/**
	 * 根据电话号码、用户分组、短信类型判断是否已经有发送过的验证码，存在的话，将dr置为1
	 * @param cellphone,usergroup,messagetype
	 */
	int UpdateOldMessage(String cellphone,String usergroup,String messagetype);
	/**
	 * 根据发送者，接受者查询
	 * @param sender,receiver,usergroup
	 */
	List<MessageRecord> querybysenderandreceiver(String sender,String receiver,String usergroup);
	
	/**
	 * 根据发送者查询和其他所有存在会话的列表
	 * @param 
	 */
	List<Object> querybysender(String sender,String receiver,String usergroup);
	
	
	/**
	 * 根据pkUser,messagetype,查询消息
	 * @param 
	 */
	List<Object> querybyuser(String pkUser,String messagetype,String status);
	
	
	
	/**
	 * 根据时间，用户查询消息
	 * @param 
	 */
	List<Object> queryBytime(String pkUser,String messagetype,String begintime,String endtime);
	
	
	
}
