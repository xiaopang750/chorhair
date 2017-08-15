package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.FairerCredit;
import com.rockstar.o2o.service.FairerCreditService;

@Component
public class FairerCreditServiceImpl extends BaseServiceImpl implements FairerCreditService{

	@Override
	public FairerCredit save(FairerCredit credit) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(credit);
		baseDao.add(credit);
		credit.setPkCredit(Long.parseLong(pk.toString()));
		return credit;
	}

	@Override
	public void savelist(ArrayList<FairerCredit> credits) {
		// TODO Auto-generated method stub
		baseDao.batchsave(credits);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FairerCredit> QueryByPkFairer(String pkFairer) {
		// TODO Auto-generated method stub
		Long longpkfairer=Long.parseLong(pkFairer);
		List<FairerCredit> creditlist=(List<FairerCredit>) baseDao.pageQuery("from FairerCredit where IFNULL(dr,0)=0 and pkFairer = ? ", longpkfairer);
		return creditlist;
	}

}
