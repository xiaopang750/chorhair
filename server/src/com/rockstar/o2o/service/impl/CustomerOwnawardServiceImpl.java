package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.CustomerOwnaward;
import com.rockstar.o2o.service.CustomerOwnawardService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class CustomerOwnawardServiceImpl extends BaseServiceImpl implements CustomerOwnawardService{

	@Override
	public CustomerOwnaward save(CustomerOwnaward award) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(award);
		baseDao.add(award);
		award.setPkCustomerOwnaward(Long.parseLong(pk.toString()));
		return award;
	}

	@Override
	public void savelist(ArrayList<CustomerOwnaward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchsave(awards);
	}

	@Override
	public void updatelist(ArrayList<CustomerOwnaward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(awards);
	}

	@Override
	public CustomerOwnaward getAwardById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerOwnaward) baseDao.getById(CustomerOwnaward.class,id);
	}

	@Override
	public void deleteawardById(Long id) {
		// TODO Auto-generated method stub
		CustomerOwnaward award=(CustomerOwnaward) baseDao.getById(CustomerOwnaward.class, id);
		award.setDr((short) 1);
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(award);
	}

	@Override
	public int updateAward(CustomerOwnaward award) {
		// TODO Auto-generated method stub
		int result=baseDao.update(award);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOwnaward> QueryBypkCustomer(String pkCustomer) {
		// TODO Auto-generated method stub
		Long longpkCustomer=Long.parseLong(pkCustomer);
		List<CustomerOwnaward> awardlist=(List<CustomerOwnaward>) baseDao.pageQuery("from CustomerOwnaward where IFNULL(dr,0)=0 and pkCustomer = ?  ", longpkCustomer);
		return awardlist;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOwnaward> QueryBycustomerandcombo(String pkCustomer,
			String pkcombo) {
		// TODO Auto-generated method stub
		Long longpkCustomer=Long.parseLong(pkCustomer);
		JSONObject obj=new JSONObject();
		obj.accumulate("pkCombo", pkcombo);
		pkcombo=obj.toString();
		String currentime=DateUtil.getCurrDate(DateUtil.FORMAT_ONE);
		List<CustomerOwnaward> awardlist=(List<CustomerOwnaward>) baseDao.pageQuery("from CustomerOwnaward where IFNULL(dr,0)=0 and pkCustomer = ?  and limits like '%"+pkcombo+"%'" +
				" and begintime <= ? and endtime >= ? and awardstatus = ? ", longpkCustomer,currentime,currentime,"001");
		return awardlist;
	}

}
