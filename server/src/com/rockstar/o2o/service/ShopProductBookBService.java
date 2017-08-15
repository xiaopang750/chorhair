package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.ShopProductBookB;

public interface ShopProductBookBService {

	/**
	 * 添加一条订货明细
	 * @param member
	 */
	ShopProductBookB save(ShopProductBookB book);
	
	/**
	 * 批量添加订货明细
	 * @param member
	 */
	void savelist(ArrayList<ShopProductBookB> details);
	/**
	 * 批量更新订单明细
	 * @param member
	 */
	void updatelist(ArrayList<ShopProductBookB> details);
	
	/**
	 * 根据ID，获取订货明细
	 * @param id
	 * @return
	 */
	ShopProductBookB getBookById(Long id);
	/**
	 * 根据ID删除订货
	 * @param id
	 */
	void deleteBookById(Long id);
	/**
	 * 修改订货信息
	 * @param id
	 */
	int updateBook(ShopProductBookB book);
	/**
	 * 根据主表主键查询子表
	 * @param pk_shop
	 */
	List<ShopProductBookB> QueryByPkBook(String pkBook,Integer curpage,Integer pagesize);

	
}
