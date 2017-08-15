package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.CustomerUseaward;
import com.rockstar.o2o.service.CustomerUseawardService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class CustomerUseawardServiceImpl extends BaseServiceImpl implements CustomerUseawardService{

	@Override
	public CustomerUseaward save(CustomerUseaward award) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(award);
		baseDao.add(award);
		award.setPkCustomerUseaward(Long.parseLong(pk.toString()));
		return award;
	}

	@Override
	public void savelist(ArrayList<CustomerUseaward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchsave(awards);
	}

	@Override
	public void updatelist(ArrayList<CustomerUseaward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(awards);
	}

	@Override
	public CustomerUseaward getAwardById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerUseaward) baseDao.getById(CustomerUseaward.class,id);
	}

	@Override
	public void deleteawardById(Long id) {
		// TODO Auto-generated method stub
		CustomerUseaward award=(CustomerUseaward) baseDao.getById(CustomerUseaward.class, id);
		award.setDr((short) 1);
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(award);
	}

	@Override
	public int updateAward(CustomerUseaward award) {
		// TODO Auto-generated method stub
		int result=baseDao.update(award);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerUseaward> QueryBypkCustomer(String pkCustomer) {
		// TODO Auto-generated method stub
		Long longpkCustomer=Long.parseLong(pkCustomer);
		List<CustomerUseaward> awardlist=(List<CustomerUseaward>) baseDao.pageQuery("from CustomerUseaward where IFNULL(dr,0)=0 and pkCustomer = ?  ", longpkCustomer);
		return awardlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerUseaward> QueryBycustomerandaward(String pkCustomer,
			String pkaward) {
		// TODO Auto-generated method stub
		Long longpkCustomer=Long.parseLong(pkCustomer);
		Long longpkaward=Long.parseLong(pkaward);
		List<CustomerUseaward> awardlist=(List<CustomerUseaward>) baseDao.pageQuery("from CustomerUseaward where IFNULL(dr,0)=0 and pkCustomer = ? and pkCustomeraward = ?  ", longpkCustomer,longpkaward);
		return awardlist;
	}

}
