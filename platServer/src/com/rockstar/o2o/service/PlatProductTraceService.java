package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.PlatProductTrace;

public interface PlatProductTraceService {
	/**
	 * 保存商品轨迹
	 * @param trace
	 * @return
	 */
	PlatProductTrace save(PlatProductTrace trace);

	/**
	 * 根据商品id获取轨迹
	 * @param pkProduct
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	List<PlatProductTrace> gettrace(String pkProduct, Integer curpage,Integer pagesize);

}
