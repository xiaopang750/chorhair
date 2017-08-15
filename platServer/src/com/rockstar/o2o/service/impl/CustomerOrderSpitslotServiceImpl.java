package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.CustomerOrder;
import com.rockstar.o2o.pojo.CustomerOrderSpitslot;
import com.rockstar.o2o.service.CustomerOrderSpitslotService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class CustomerOrderSpitslotServiceImpl extends BaseServiceImpl implements CustomerOrderSpitslotService{

	@Override
	public CustomerOrderSpitslot save(CustomerOrderSpitslot Spitslot) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(Spitslot);
		baseDao.add(Spitslot);
		Spitslot.setPkSpitslot(Long.parseLong(pk.toString()));
		return Spitslot;
	}

	@Override
	public CustomerOrderSpitslot getSpitslotById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerOrderSpitslot) baseDao.getById(CustomerOrder.class,id);
	}

	@Override
	public void deleteSpitslotById(Long id) {
		// TODO Auto-generated method stub
		CustomerOrderSpitslot Spitslot=(CustomerOrderSpitslot) baseDao.getById(CustomerOrderSpitslot.class, id);
		Spitslot.setDr((short) 1);
		Spitslot.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(Spitslot);
	}

	@Override
	public int updateSpitslot(CustomerOrderSpitslot Spitslot) {
		// TODO Auto-generated method stub
		int result=baseDao.update(Spitslot);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrderSpitslot> FindBypkOrder(String PkOrder) {
		// TODO Auto-generated method stub
		Long longPkOrder=Long.parseLong(PkOrder);
		List<CustomerOrderSpitslot> Spitslotlist=(List<CustomerOrderSpitslot>) baseDao.pageQuery("from CustomerOrderSpitslot where IFNULL(dr,0)=0 and pkOrder = ? order by spitslottime desc", longPkOrder);
		return Spitslotlist;
	}

}
