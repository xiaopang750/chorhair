package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.PlatformCumboAddition;
import com.rockstar.o2o.service.PlatformCumboAdditionService;

@Component
public class PlatformCumboAdditionServiceImpl extends BaseServiceImpl implements PlatformCumboAdditionService{

	@Override
	public PlatformCumboAddition save(PlatformCumboAddition addition) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(addition);
		baseDao.add(addition);
		addition.setPkAddition(Long.parseLong(pk.toString()));
		return addition;
	}

	@Override
	public PlatformCumboAddition getAdditionById(Long id) {
		// TODO Auto-generated method stub
		return (PlatformCumboAddition) baseDao.getById(PlatformCumboAddition.class,id);
	}

	@Override
	public void deleteAdditionById(Long id) {
		// TODO Auto-generated method stub
		PlatformCumboAddition addition=(PlatformCumboAddition) baseDao.getById(PlatformCumboAddition.class, id);
		addition.setDr((short) 1);
		baseDao.update(addition);
	}

	@Override
	public int updateAddition(PlatformCumboAddition addition) {
		// TODO Auto-generated method stub
		int result=baseDao.update(addition);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformCumboAddition> QueryByPkCombo(String pkCumbo) {
		Long longpkcumobo=Long.parseLong(pkCumbo);
		// TODO Auto-generated method stub
		List<PlatformCumboAddition> additionlist=(List<PlatformCumboAddition>) baseDao.pageQuery("from PlatformCumboAddition where IFNULL(dr,0)=0 and  pkCumbo = ? ", longpkcumobo);
		return additionlist;
	}

	

}
