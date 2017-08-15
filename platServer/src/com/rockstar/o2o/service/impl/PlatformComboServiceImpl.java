package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformCombo> QueryByCon(String querylike, Integer curpage,Integer pagesize) {
		
		List<PlatformCombo> combolists=new ArrayList<PlatformCombo>();
		StringBuffer buffer=new StringBuffer();	
		HashMap<String, Object> map=new HashMap<String, Object>();
		buffer.append("  SELECT                                                                                         ");
		buffer.append("  	p.pk_combo ,                                                                                ");
		buffer.append("  	p.combocode,                                                                                ");
		buffer.append("  	p.comboname,                                                                                ");
		buffer.append("  	p.combomoney,                                                                               ");
		buffer.append("  	IFNULL((                                                                                    ");
		buffer.append("  		SELECT sum(s.buycount) FROM shop_combo s WHERE	IFNULL(s.dr, 0) = 0 AND s.pk_combo = p.pk_combo      ");
		buffer.append("  	),0) AS buycount,                                                                                         ");
		buffer.append("  	p.combonote                                                                                 ");
		buffer.append("  FROM                                                                                                        ");
		buffer.append("  	platform_combo p                                                                                         ");
		buffer.append("  WHERE                                                                                                       ");
		buffer.append("  	IFNULL(p.dr, 0) = 0                                                                                      ");	
		
		if(querylike!=null&&!querylike.trim().equals("")){
			buffer.append(" and (p.combocode like '%"+querylike+"%'  or p.comboname like '%"+querylike+"%' or p.combopy like '%"+querylike+"%'  or p.combospy like '%"+querylike+"%') ");
		}
		combolists=(List<PlatformCombo>) baseDao.querySqlListByConMap(buffer.toString(),curpage,pagesize,map);
		return combolists;
	}

}
