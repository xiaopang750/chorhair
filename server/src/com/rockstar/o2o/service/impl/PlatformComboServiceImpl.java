package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.PlatformCombo;
import com.rockstar.o2o.service.PlatformComboService;

@Component
public class PlatformComboServiceImpl extends BaseServiceImpl implements PlatformComboService{

	@Override
	public PlatformCombo save(PlatformCombo combo) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(combo);
		baseDao.add(combo);
		combo.setPkCombo(Long.parseLong(pk.toString()));
		return combo;
	}

	@Override
	public PlatformCombo getComboById(Long id) {
		// TODO Auto-generated method stub
		return (PlatformCombo) baseDao.getById(PlatformCombo.class,id);
	}

	@Override
	public void deleteComboById(Long id) {
		// TODO Auto-generated method stub
		PlatformCombo combo=(PlatformCombo) baseDao.getById(PlatformCombo.class, id);
		combo.setDr((short) 1);
		baseDao.update(combo);
	}

	@Override
	public int updateCombo(PlatformCombo combo) {
		// TODO Auto-generated method stub
		int result=baseDao.update(combo);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformCombo> QueryAll() {
		// TODO Auto-generated method stub
		return (List<PlatformCombo>) baseDao.getAll(PlatformCombo.class);
	}

}
