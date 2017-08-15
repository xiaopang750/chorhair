package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.WxQrcodeScancount;

public interface WxQrcodeScancountService {
	/**
	 * 保存二维码统计信息
	 * @param info
	 * @return
	 */
	WxQrcodeScancount save(WxQrcodeScancount info);
	/**
	 * 更新二维码统计信息
	 * @param scancount
	 * @return
	 */
	int updateScancount(WxQrcodeScancount scancount);
	/**
	 * 根据pkkey，codeobject获取二维码统计信息
	 * @param pkkey
	 * @param codeobject
	 * @return
	 */
	WxQrcodeScancount getScancountInfo(String accountid,String pkkey,String codeobject);
	/**
	 * 根据sceneid，actionname获取二维码统计信息
	 * @param sceneid
	 * @param actionname
	 * @return
	 */
	WxQrcodeScancount getCountInfo(String accountid,String sceneid,String actionname);
	/**
	 * 根据ID删除二维码统计信息
	 * @param id
	 */
	void deleteScancountById(Long id);
	/**
	 * 根据对象类型查询二维码信息列表
	 * @param pkShop
	 * @param codeobject
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	List<WxQrcodeScancount> querycancountList(String pkShop, String codeobject, Integer curpage, Integer pagesize);
	/**
	 * 根据pkkey，codeobject,userDefined获取二维码统计信息
	 * @param sceneid
	 * @param actionname
	 * @return
	 */
	WxQrcodeScancount getCountInfoby(String accountid,String pkkey,String codeobject,String userDefined);
	
	/**
	 * 根据店铺查询二维码信息列表
	 * @param pkShop
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	List<WxQrcodeScancount> querycanbyShop(String pkShop,Integer curpage, Integer pagesize);
	
	
}
