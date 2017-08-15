package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.PlatformComboService;
import com.rockstar.o2o.service.PlatformComboServiceService;

@Component
public class PlatformComboServiceServiceImpl extends BaseServiceImpl implements PlatformComboServiceService{

	@Override
	public PlatformComboService save(PlatformComboService service) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(service);
		baseDao.add(service);
		service.setPkComboService(Long.parseLong(pk.toString()));
		return service;
	}

	@Override
	public PlatformComboService getServiceById(Long id) {
		// TODO Auto-generated method stub
		return (PlatformComboService) baseDao.getById(PlatformComboService.class,id);
	}

	@Override
	public void deleteServiceById(Long id) {
		// TODO Auto-generated method stub
		PlatformComboService service=(PlatformComboService) baseDao.getById(PlatformComboService.class, id);
		service.setDr((short) 1);
		baseDao.update(service);
	}

	@Override
	public int updateCombo(PlatformComboService service) {
		// TODO Auto-generated method stub
		int result=baseDao.update(service);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformComboService> QueryByCombo(String pkCombo) {
		// TODO Auto-generated method stub
		Long longcombo=Long.parseLong(pkCombo);
		List<PlatformComboService> servicelist=(List<PlatformComboService>) baseDao.pageQuery("from PlatformComboService where IFNULL(dr,0)=0 and pkCombo = ? ", longcombo);
		return servicelist;
	}

}
