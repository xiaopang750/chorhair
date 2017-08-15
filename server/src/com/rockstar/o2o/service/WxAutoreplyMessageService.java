package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.WxAutoreplyMessage;

public interface WxAutoreplyMessageService {
	/**
	 * 保存微信自动回复内容（关键字回复）
	 * @param info
	 * @return
	 */
	WxAutoreplyMessage saveMessageInfo(WxAutoreplyMessage info);
	/**
	 * 根据主表id获取回复信息
	 * @param pkAutoreplyId
	 * @return
	 */
	List<WxAutoreplyMessage> getMessageInfo(Long pkAutoreplyId);
	/**
	 * 根据id获取数据
	 * @param string
	 * @return
	 */
	WxAutoreplyMessage getInfoById(String pkKeywordId);
	/**
	 * 更新微信关键字自动回复信息内容
	 * @param messageInfo
	 */
	int update(WxAutoreplyMessage messageInfo);
	/**
	 * 随机获取一条回复信息
	 * @param pkAutoreplyId
	 * @return
	 */
	WxAutoreplyMessage getOneMessageInfo(Long pkAutoreplyId);
	/**
	 * 根据id逻辑删除
	 * @param pkReplymsgId
	 */
	void deleteById(Long pkReplymsgId);
}
