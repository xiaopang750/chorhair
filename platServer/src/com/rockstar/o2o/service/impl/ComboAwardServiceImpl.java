package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ComboAward;
import com.rockstar.o2o.service.ComboAwardService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ComboAwardServiceImpl extends BaseServiceImpl implements ComboAwardService{

	@Override
	public ComboAward save(ComboAward award) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(award);
		baseDao.add(award);
		award.setPkComboAward(Long.parseLong(pk.toString()));
		return award;
	}

	@Override
	public void savelist(ArrayList<ComboAward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchsave(awards);
	}

	@Override
	public void updatelist(ArrayList<ComboAward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(awards);
	}

	@Override
	public ComboAward getAwardById(Long id) {
		// TODO Auto-generated method stub
		return (ComboAward) baseDao.getById(ComboAward.class,id);
	}

	@Override
	public void deleteawardById(Long id) {
		// TODO Auto-generated method stub
		ComboAward award=(ComboAward) baseDao.getById(ComboAward.class, id);
		award.setDr((short) 1);
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(award);	
	}

	@Override
	public int updateAward(ComboAward award) {
		// TODO Auto-generated method stub
		int result=baseDao.update(award);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComboAward> QueryBycombo(String pkcombo) {
		// TODO Auto-generated method stub
		Long longpkcombo=Long.parseLong(pkcombo);
		List<ComboAward> awardlist=(List<ComboAward>) baseDao.pageQuery("from ComboAward where IFNULL(dr,0)=0 and pkCombo = ?  ", longpkcombo);
		return awardlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryselect(String pkcombo) {
		// TODO Auto-generated method stub
		Long longpkcombo=Long.parseLong(pkcombo);
		StringBuffer sqlbuffer=new StringBuffer();
		sqlbuffer.append(" SELECT substr(a.pk_customeraward,1,LENGTH(pk_customeraward)) AS pkCustomeraward,awardname,awardvalue,");
		sqlbuffer.append(" (CASE WHEN selectaward IS NOT NULL THEN 'Y' ELSE 'N' END ) AS selectaward,substr(pk_combo_award,1,LENGTH(pk_combo_award)) AS pkComboAward");
		sqlbuffer.append(" FROM bd_customer_award a LEFT JOIN (SELECT pk_combo_award,pk_customeraward AS selectaward FROM combo_award WHERE IFNULL(dr, 0) = 0 AND pk_combo = ? ");
		sqlbuffer.append(" ) b ON a.pk_customeraward = b.selectaward WHERE IFNULL(a.dr, 0) = 0");
		List<Object> objlist=(ArrayList<Object>)baseDao.querybysqlMap(sqlbuffer.toString(),null,null,longpkcombo);
	    return objlist;
	}

}
