package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.PlatformArea;
import com.rockstar.o2o.service.PlatformAreaService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class PlatformAreaServiceImpl extends BaseServiceImpl implements PlatformAreaService{

	@Override
	public PlatformArea save(PlatformArea area) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(area);
		baseDao.add(area);
		area.setPkArea(Long.parseLong(pk.toString()));
		return area;
	}

	@Override
	public PlatformArea getareaById(Long id) {
		// TODO Auto-generated method stub
		return (PlatformArea) baseDao.getById(PlatformArea.class,id);
	}

	@Override
	public void deleteAreaById(Long id) {
		// TODO Auto-generated method stub
		PlatformArea area=(PlatformArea) baseDao.getById(PlatformArea.class, id);
		area.setDr((short) 1);
		area.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(area);
	}

	@Override
	public int updatearea(PlatformArea area) {
		// TODO Auto-generated method stub
		int result=baseDao.update(area);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformArea> QueryByLevel(String level) {
		// TODO Auto-generated method stub
		Short shortlevel=Short.parseShort(level);
		List<PlatformArea> arealist=(List<PlatformArea>) baseDao.pageQuery("from PlatformArea where IFNULL(dr,0)=0 and belonglevel = ? ", shortlevel);
		return arealist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformArea> QueryByFather(String pkFather) {
		// TODO Auto-generated method stub
		Long longpkFather=Long.parseLong(pkFather);
		List<PlatformArea> arealist=(List<PlatformArea>) baseDao.pageQuery("from PlatformArea where IFNULL(dr,0)=0 and pkFatherarea = ? ", longpkFather);
		return arealist;
	}

}
