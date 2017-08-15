package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.CustomerComboUserecord;
import com.rockstar.o2o.service.CustomerComboUserecordService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class CustomerComboUserecordServiceImpl extends BaseServiceImpl implements CustomerComboUserecordService{

	@Override
	public CustomerComboUserecord save(CustomerComboUserecord record) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(record);
		baseDao.add(record);
		record.setPkCumboRecord(Long.parseLong(pk.toString()));
		return record;
	}

	@Override
	public CustomerComboUserecord getComboById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerComboUserecord) baseDao.getById(CustomerComboUserecord.class,id);
	}

	@Override
	public void deleteComboById(Long id) {
		// TODO Auto-generated method stub
		CustomerComboUserecord combo=(CustomerComboUserecord) baseDao.getById(CustomerComboUserecord.class, id);
		combo.setDr((short) 1);
		combo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(combo);
	}

	@Override
	public int updateCombo(CustomerComboUserecord combo) {
		// TODO Auto-generated method stub
		int result=baseDao.update(combo);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerComboUserecord> QueryByPkCustomer(String PkCustomer) {
		// TODO Auto-generated method stub
		Long longcustomer=Long.parseLong(PkCustomer);
		// TODO Auto-generated method stub
		List<CustomerComboUserecord> recordlist=(List<CustomerComboUserecord>) baseDao.pageQuery("from CustomerComboUserecord where IFNULL(dr,0)=0 and pkCustomer = ? ", longcustomer);
		return recordlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerComboUserecord> QueryByPkCustomerAndCombo(
			String PkCustomer, String pkShopCombo) {
		// TODO Auto-generated method stub
		Long longcustomer=Long.parseLong(PkCustomer);
		Long longcombo=Long.parseLong(pkShopCombo);
		// TODO Auto-generated method stub
		List<CustomerComboUserecord> recordlist=(List<CustomerComboUserecord>) baseDao.pageQuery("from CustomerComboUserecord where IFNULL(dr,0)=0 and pkCustomer = ? and pkShopCombo = ?  ", longcustomer,longcombo);
		return recordlist;
	}

}
