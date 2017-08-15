package com.rockstar.o2o.service;

import java.util.List;
import com.rockstar.o2o.pojo.ShopProductBook;

public interface ShopProductBookService {
	/**
	 * 添加一条订货
	 * @param member
	 */
	ShopProductBook save(ShopProductBook book);
	/**
	 * 根据ID，获取订货主表
	 * @param id
	 * @return
	 */
	ShopProductBook getBookById(Long id);
	/**
	 * 根据ID删除订货
	 * @param id
	 */
	void deleteInfoById(Long id);
	/**
	 * 修改订货信息
	 * @param id
	 */
	int updateInfo(ShopProductBook book);
	/**
	 * 根据店铺查询所有订货
	 * @param pk_shop
	 */
	List<ShopProductBook> QueryByShop(String pk_shop,Integer curpage,Integer pagesize);

	/**
	 * 查询当前最大的订货编号
	 * @param 
	 */
	List<Object> queryMaxNo(String code,String pkShop);
	
	/**
	 * 平台查询待审批的订货单
	 * @param pk_shop
	 */
	List<Object> Querywaitapprove(Integer curpage,Integer pagesize);
	/**
	 * 平台查询所有订货单
	 * @param pk_shop
	 */
	List<Object> Queryapprove(String province, String city, String county,
			String pkShop, String begintime, String endtime,
			String vbillstatus, Integer curpage, Integer pagesize);
	
}
