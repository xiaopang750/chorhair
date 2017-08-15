package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.WxQrcodeScancountB;
public interface WxQrcodeScancountBService {
	/**
	 * 保存二维码统计信息详情
	 * @param info
	 * @return
	 */
	WxQrcodeScancountB save(WxQrcodeScancountB info);
	/**
	 * 更新二维码统计信息详情表
	 * @param scancount
	 * @return
	 */
	int updateScancountB(WxQrcodeScancountB scancount);
	
	/**
	 * 根据ID删除二维码统计信息详情
	 * @param id
	 */
	void deleteScancountBById(Long id);
	/**
	 * 根据scanUserId查询用户id是否已经在统计信息详情表中
	 * @param scanUserId
	 */
	WxQrcodeScancountB queryByUserid(String scanUserId);
	/**
	 * 获取扫描统计详情
	 * @param pkScancountid
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	List<Object> getScancountdetail(String pkScancountid, Integer curpage,Integer pagesize);
	/**
	 * 根据二维码统计主键统计详情数
	 * @param pkScancountid
	 * @return
	 */
	long getCountByPkScancountid(long pkScancountid);
	/**
	 * 根据二维码统计主键统计扫描并关注数（需要在统计在一定时间内不取消关注的会员数）
	 * @param pkScancountid
	 * @return
	 */
	long getAttentionCount(long pkScancountid);

}
