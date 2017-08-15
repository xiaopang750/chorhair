package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.ProductInfo;

public interface PlatproductService {
	/**
	 * 查询平台商品信息
	 * @param productname
	 * @param curpage
	 * @param pagesize
	 * @return
	 */
	List<ProductInfo> findproduct(String productname, Integer curpage,Integer pagesize);
	/**
	 * 保存平台商品信息
	 * @param info
	 * @return
	 */
	ProductInfo save(ProductInfo info);
	/**
	 * 根据主键查询商品信息
	 * @param parseLong
	 * @return
	 */
	ProductInfo findById(long parseLong);
	/**
	 * 修改平台商品信息
	 * @param info
	 * @return
	 */
	int update(ProductInfo info);
	/**
	 * 根据id删除
	 * @param pkProduct
	 */
	void deleteById(Long pkProduct);

}
