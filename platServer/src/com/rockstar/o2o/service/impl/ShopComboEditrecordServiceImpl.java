package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopComboEditrecord;
import com.rockstar.o2o.service.ShopComboEditrecordService;

@Component
public class ShopComboEditrecordServiceImpl extends BaseServiceImpl implements ShopComboEditrecordService{

	@Override
	public ShopComboEditrecord save(ShopComboEditrecord edit) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(edit);
		baseDao.add(edit);
		edit.setPkComboEditrecord(Long.parseLong(pk.toString()));
		return edit;
	}

	@Override
	public ShopComboEditrecord getRecordById(Long id) {
		// TODO Auto-generated method stub
		return (ShopComboEditrecord) baseDao.getById(ShopComboEditrecord.class,id);
	}

	@Override
	public void deleteRecordById(Long id) {
		// TODO Auto-generated method stub
		ShopComboEditrecord combo=(ShopComboEditrecord) baseDao.getById(ShopComboEditrecord.class, id);
		combo.setDr((short) 1);
		baseDao.update(combo);
	}

	@Override
	public int updateRecord(ShopComboEditrecord record) {
		// TODO Auto-generated method stub
		int result=baseDao.update(record);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopComboEditrecord> QueryByShop(String pk_shop) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		List<ShopComboEditrecord> editlists=(List<ShopComboEditrecord>) baseDao.pageQuery("from ShopComboEditrecord where IFNULL(dr,0)=0 and pkShop = ?  ",longcorp);
		return editlists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopComboEditrecord> QueryByCombo(String pkCombo) {
		// TODO Auto-generated method stub
		Long longpkCombo=Long.parseLong(pkCombo);
		List<ShopComboEditrecord> editlists=(List<ShopComboEditrecord>) baseDao.pageQuery("from ShopComboEditrecord where IFNULL(dr,0)=0 and pkShopCombo = ?  ",longpkCombo);
		return editlists;
	}

	@Override
	public void savelist(ArrayList<ShopComboEditrecord> records) {
		// TODO Auto-generated method stub
		baseDao.batchsave(records);
	}

}
