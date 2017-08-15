package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.WxAutoreply;

public interface WxAutoreplyService {
	/**
	 * 保存微信自动回复信息（适用“关注回复”和“消息回复”保存）
	 * @param info
	 * @return
	 */
	WxAutoreply save(WxAutoreply info);
	/**
	 * 更新微信自动回复信息（适用“关注回复”和“消息回复”更新）
	 * @param info
	 * @return
	 */
	int update(WxAutoreply info);
	/**
	 * 根据自动回复类型获取信息
	 * @param replyType
	 * @return
	 */
	List<WxAutoreply> getInfoByReplytype(String replyType);
	/**
	 * 根据ID删除微信自动回复信息（适用关注回复和消息回复）
	 * @param id
	 */
	void deleteById(Long id);
	/**
	 * 根据主键id获取信息
	 * @param replyType
	 * @return
	 */
	WxAutoreply getInfoById(long pkAutoreplyId);
	
}
