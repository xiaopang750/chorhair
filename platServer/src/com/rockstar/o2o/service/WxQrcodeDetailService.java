package com.rockstar.o2o.service;

import com.rockstar.o2o.pojo.WxQrcodeDetail;

public interface WxQrcodeDetailService {

	/**
	 * 根据sceneid，actionname获取二维码信息
	 * @param sceneid
	 * @param actionname
	 * @return
	 */
	WxQrcodeDetail getInfoByRequirement(String sceneid,String actionname);

}
