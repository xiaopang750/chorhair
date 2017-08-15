package com.rockstar.o2o.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.CustomerOrderDetail;
import com.rockstar.o2o.service.CustomerOrderDetailService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class CustomerOrderDetailServiceImpl extends BaseServiceImpl implements CustomerOrderDetailService{

	@Override
	public CustomerOrderDetail save(CustomerOrderDetail detail) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(detail);
		baseDao.add(detail);
		detail.setPkOrderDetail(Long.parseLong(pk.toString()));
		return detail;
	}

	@Override
	public void savelist(ArrayList<CustomerOrderDetail> details) {
		// TODO Auto-generated method stub
		baseDao.batchsave(details);
	}

	@Override
	public CustomerOrderDetail getDetailById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerOrderDetail) baseDao.getById(CustomerOrderDetail.class,id);
	}

	@Override
	public void deleteDetailById(Long id) {
		// TODO Auto-generated method stub
		CustomerOrderDetail message=(CustomerOrderDetail) baseDao.getById(CustomerOrderDetail.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public int updateGroup(CustomerOrderDetail detail) {
		// TODO Auto-generated method stub
		int result=baseDao.update(detail);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrderDetail> QueryByPkOrder(String pkOrder,String type) {
		// TODO Auto-generated method stub
		Long longorder=Long.parseLong(pkOrder);
		List<CustomerOrderDetail>  detaillist=(List<CustomerOrderDetail>) baseDao.pageQuery("from CustomerOrderDetail where IFNULL(dr,0)=0 and pkOrder = ? and IFNULL(detailtype,'') = ? ",longorder, type);
			return detaillist;
	}

	@Override
	public void updatelist(ArrayList<CustomerOrderDetail> details) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(details);
	}

}
