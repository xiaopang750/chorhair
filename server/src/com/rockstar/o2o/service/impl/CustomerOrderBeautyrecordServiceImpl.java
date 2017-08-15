package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.CustomerOrderBeautyrecord;
import com.rockstar.o2o.service.CustomerOrderBeautyrecordService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class CustomerOrderBeautyrecordServiceImpl extends BaseServiceImpl implements CustomerOrderBeautyrecordService{

	@Override
	public CustomerOrderBeautyrecord save(CustomerOrderBeautyrecord record) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(record);
		baseDao.add(record);
		record.setPkBeautyrecord(Long.parseLong(pk.toString()));
		return record;
	}

	@Override
	public CustomerOrderBeautyrecord getRecordById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerOrderBeautyrecord) baseDao.getById(CustomerOrderBeautyrecord.class,id);
	}

	@Override
	public void deleteRecordById(Long id) {
		// TODO Auto-generated method stub
		CustomerOrderBeautyrecord record=(CustomerOrderBeautyrecord) baseDao.getById(CustomerOrderBeautyrecord.class, id);
		record.setDr((short) 1);
		record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(record);
	}

	@Override
	public int updateRecord(CustomerOrderBeautyrecord record) {
		// TODO Auto-generated method stub
		int result=baseDao.update(record);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrderBeautyrecord> QueryByOrder(String pkOrder) {
		// TODO Auto-generated method stub
		Long longpkOrder=Long.parseLong(pkOrder);
		List<CustomerOrderBeautyrecord> recordlist=(List<CustomerOrderBeautyrecord>) baseDao.pageQuery("from CustomerOrderBeautyrecord where IFNULL(dr,0)=0 and pkOrder = ? ", longpkOrder);
		return recordlist;
	}

	@Override
	public void savelist(ArrayList<CustomerOrderBeautyrecord> records) {
		// TODO Auto-generated method stub
		baseDao.batchsave(records);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> QueryByPkcustomer(String pkCustomer) {
		// TODO Auto-generated method stub
		Long longpkcustomer=Long.parseLong(pkCustomer);
		List<Object> recordlist=(List<Object>) baseDao.querybysqlMap("select pictureshorturl,pictureurl from customer_order_beautyrecord a where IFNULL(a.dr,0)=0 and EXISTS (select 1 from customer_order where IFNULL(dr,0)=0 and pk_order = a.pk_order and  pk_customer = ? ) order by pk_beautyrecord  desc limit 4 ", null,null,longpkcustomer);
		return recordlist;
	}

}
