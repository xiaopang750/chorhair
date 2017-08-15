package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.WxAutoreplyKeyword;

public interface WxAutoreplyKeywordService {
	/**
	 * 保存微信自动回复关键字（关键字回复）
	 * @param info
	 * @return
	 */
	WxAutoreplyKeyword saveKeywordInfo(WxAutoreplyKeyword info);
	/**
	 * 根据主表id获取关键字表中信息
	 * @param pkAutoreplyId
	 * @return
	 */
	List<WxAutoreplyKeyword> getKeywordInfo(Long pkAutoreplyId);
	
	/**
	 * 更新微信关键字自动回复信息
	 * @param info
	 * @return
	 */
	int update(WxAutoreplyKeyword info);
	/**
	 * 根据id获取数据
	 * @param string
	 * @return
	 */
	WxAutoreplyKeyword getInfoById(String pkKeywordId);
	/**
	 * 根据客户发送的关键字查询
	 * @param keyword
	 * @return
	 */
	WxAutoreplyKeyword getInfoByKeyword(String keyword);
	/**
	 * 根据id逻辑删除
	 * @param pkKeywordId
	 */
	void deleteById(Long pkKeywordId);

}
