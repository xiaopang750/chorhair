package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.ShopAddition;
import com.rockstar.o2o.service.ShopAdditionService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ShopAdditionServiceImpl extends BaseServiceImpl implements ShopAdditionService{

	@Override
	public ShopAddition save(ShopAddition addition) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(addition);
		baseDao.add(addition);
		addition.setPkAddition(Long.parseLong(pk.toString()));
		return addition;
	}

	@Override
	public void savelist(ArrayList<ShopAddition> additions) {
		// TODO Auto-generated method stub
		baseDao.batchsave(additions);
	}

	@Override
	public void updatelist(ArrayList<ShopAddition> additions) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(additions);
	}

	@Override
	public ShopAddition getAddtionById(Long id) {
		// TODO Auto-generated method stub
		return (ShopAddition) baseDao.getById(ShopAddition.class,id);
	}

	@Override
	public void deleteAdditionById(Long id) {
		// TODO Auto-generated method stub
		ShopAddition addition=(ShopAddition) baseDao.getById(ShopAddition.class, id);
		addition.setDr((short) 1);
		addition.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(addition);	
	}

	@Override
	public int updateAddition(ShopAddition addition) {
		// TODO Auto-generated method stub
		addition.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(addition);
	    return result;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopAddition> QueryBygroup(String pkGroup) {
		// TODO Auto-generated method stub
		Long longpkgroup=Long.parseLong(pkGroup);
		List<ShopAddition> additionlists=(List<ShopAddition>) baseDao.pageQuery("from ShopAddition where IFNULL(dr,0)=0 and pkAdditionGroup = ? ",longpkgroup);
		return additionlists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> QueryBycombo(String pkshopcombo) {
		// TODO Auto-generated method stub
		Long longpkshopcombo=Long.parseLong(pkshopcombo);
		List<Object> additionlists=(List<Object>) baseDao.querybysqlMap("select * from additions where pkShopCombo = ? ",null,null,longpkshopcombo);
		return additionlists;
	}

}
