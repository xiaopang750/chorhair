package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.FairerInfo;

public interface FairerInfoService {
	/**
	 * 添加一名理发师
	 * @param member
	 */
	FairerInfo save(FairerInfo info);
	
	/**
	 * 批量更新理发师信息
	 * @param member
	 */
	void updatelist(ArrayList<FairerInfo> fairers);
	
	/**
	 * 根据ID，获取理发师
	 * @param id
	 * @return
	 */
	FairerInfo getFairerById(Long id);
	/**
	 * 根据ID删除理发师
	 * @param id
	 */
	void deleteFairerById(Long id);
	/**
	 * 修改理发师信息
	 * @param id
	 */
	int updateFairer(FairerInfo info);
	/**
	 * 根据店铺查询理发师
	 * @param pk_shop
	 * @param querylike 
	 */
	List<FairerInfo> QueryByShop(String pk_shop,String querylike, Integer curpage,Integer pagesize);
	/**
	 * 根据手机号、店铺查询理发师
	 * @param pk_shop
	 */
	List<FairerInfo> QueryByCellandShop(String cellphone,String pkShop);
	
	
	/**
	 * 查询理发师业绩
	 * @param fairername 
	 * @param paymode 
	 * @param pk_shop
	 */
	List<Object> Queryrank(String begintime,String endtime,String pkShop, String fairername, String paymode);
	
	/**
	 * 根据pkUser查询理发师信息
	 * @param pk_shop
	 */
	List<FairerInfo> QueryByPkuser(String pkUser);
	
	
	/**
	 * 根据店铺查询有效理发师
	 * @param pk_shop
	 */
	List<FairerInfo> QueryValidate(String pk_shop);
	
}
