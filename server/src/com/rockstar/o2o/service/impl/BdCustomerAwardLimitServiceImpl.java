package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.BdCustomerAwardLimit;
import com.rockstar.o2o.service.BdCustomerAwardLimitService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class BdCustomerAwardLimitServiceImpl extends BaseServiceImpl implements BdCustomerAwardLimitService {

	@Override
	public BdCustomerAwardLimit save(BdCustomerAwardLimit award) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(award);
		baseDao.add(award);
		award.setPkCustomerawardLimit(Long.parseLong(pk.toString()));
		return award;
	}

	@Override
	public void savelist(ArrayList<BdCustomerAwardLimit> awards) {
		// TODO Auto-generated method stub
		baseDao.batchsave(awards);
	}

	@Override
	public void updatelist(ArrayList<BdCustomerAwardLimit> awards) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(awards);
	}

	@Override
	public BdCustomerAwardLimit getAwardById(Long id) {
		// TODO Auto-generated method stub
		return (BdCustomerAwardLimit) baseDao.getById(BdCustomerAwardLimit.class,id);
	}

	@Override
	public void deleteawardById(Long id) {
		// TODO Auto-generated method stub
		BdCustomerAwardLimit award=(BdCustomerAwardLimit) baseDao.getById(BdCustomerAwardLimit.class, id);
		award.setDr((short) 1);
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(award);
	}

	@Override
	public int updateAward(BdCustomerAwardLimit award) {
		// TODO Auto-generated method stub
		int result=baseDao.update(award);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BdCustomerAwardLimit> QueryByPkParent(String pkParent) {
		// TODO Auto-generated method stub
		Long longpkCustomeraward=Long.parseLong(pkParent);
		List<BdCustomerAwardLimit> awardlist=(List<BdCustomerAwardLimit>) baseDao.pageQuery("from BdCustomerAwardLimit where IFNULL(dr,0)=0 and pkCustomeraward = ?  ", longpkCustomeraward);
		return awardlist;
	}

}
