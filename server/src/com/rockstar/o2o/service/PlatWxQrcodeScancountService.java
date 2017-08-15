package com.rockstar.o2o.service;

import java.util.List;

import com.rockstar.o2o.pojo.WxQrcodeScancount;

public interface PlatWxQrcodeScancountService {
	/**
	 * 平台查询二维码信息列表
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	List<WxQrcodeScancount> queryPlatScancountList(Integer curpage, Integer pagesize);
}
