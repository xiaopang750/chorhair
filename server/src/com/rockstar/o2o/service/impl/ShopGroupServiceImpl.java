package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopGroup;
import com.rockstar.o2o.service.ShopGroupService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ShopGroupServiceImpl extends BaseServiceImpl implements ShopGroupService  {

	@Override
	public ShopGroup save(ShopGroup group) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(group);
		baseDao.add(group);
		group.setPkShopGroup(Long.parseLong(pk.toString()));
		return group;
	}

	@Override
	public ShopGroup getGroupById(Long id) {
		// TODO Auto-generated method stub
		return (ShopGroup) baseDao.getById(ShopGroup.class,id);
	}

	@Override
	public void deleteGroupById(Long id) {
		// TODO Auto-generated method stub
		ShopGroup group=(ShopGroup) baseDao.getById(ShopGroup.class, id);
		group.setDr((short) 1);
		group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(group);
	}

	@Override
	public int updateGroup(ShopGroup group) {
		// TODO Auto-generated method stub
		group.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(group);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopGroup> QueryByShop(String pk_shop,String type) {
		// TODO Auto-generated method stub
		Long longpkshop=Long.parseLong(pk_shop);
		List<ShopGroup> grouplists=(List<ShopGroup>) baseDao.pageQuery("from ShopGroup where IFNULL(dr,0)=0 and pkShop = ? and grouptype = ? ",longpkshop,type);
		return grouplists;
	}



}
