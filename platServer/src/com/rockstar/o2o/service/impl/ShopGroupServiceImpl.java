package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public List<ShopGroup> QueryByShop(String pkShop,String type) {
		List<ShopGroup> grouplists = new ArrayList<ShopGroup>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" from ShopGroup  where IFNULL(dr,0)=0 and grouptype=:grouptype" );
		if(pkShop!=null&&!pkShop.equals("")){
			buffer.append(" and pkShop = :longpkShop   " );
			Long longpkShop=Long.parseLong(pkShop);
			map.put("longpkShop", longpkShop);
		}
		map.put("grouptype", type);
		grouplists=(List<ShopGroup>) baseDao.queryHqlListByConMap(buffer.toString(),map);
		return grouplists;
	}



}
