package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.BdCustomerAward;
import com.rockstar.o2o.service.BdCustomerAwardService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class BdCustomerAwardServiceImpl extends BaseServiceImpl implements BdCustomerAwardService{

	@Override
	public BdCustomerAward save(BdCustomerAward award) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(award);
		baseDao.add(award);
		award.setPkCustomeraward(Long.parseLong(pk.toString()));
		return award;
	}

	@Override
	public void savelist(ArrayList<BdCustomerAward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchsave(awards);
	}

	@Override
	public List<Object> savelists(ArrayList<BdCustomerAward> awards) {
		// TODO Auto-generated method stub
		return baseDao.batchsaves(awards);
	}
	
	@Override
	public void updatelist(ArrayList<BdCustomerAward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(awards);
	}

	@Override
	public BdCustomerAward getAwardById(Long id) {
		// TODO Auto-generated method stub
		return (BdCustomerAward) baseDao.getById(BdCustomerAward.class,id);
	}

	@Override
	public void deleteawardById(Long id) {
		// TODO Auto-generated method stub
		BdCustomerAward award=(BdCustomerAward) baseDao.getById(BdCustomerAward.class, id);
		award.setDr((short) 1);
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(award);
	}

	@Override
	public int updateAward(BdCustomerAward award) {
		// TODO Auto-generated method stub
		int result=baseDao.update(award);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BdCustomerAward> QueryBypkShop(String pkshop,String type) {
		// TODO Auto-generated method stub
		List<BdCustomerAward> awardlist=new ArrayList<BdCustomerAward>();
		if(!pkshop.equals("")){			
		Long longshop=Long.parseLong(pkshop);
		if(!type.equals("")){
			awardlist=(List<BdCustomerAward>) baseDao.pageQuery("from BdCustomerAward where IFNULL(dr,0)=0 and fairtype = ? and (awardfrom = 'PLAT' or (awardfrom = 'SHOP' and pkFrom = ? )) ",type,longshop);	
		}else{
			awardlist=(List<BdCustomerAward>) baseDao.pageQuery("from BdCustomerAward where IFNULL(dr,0)=0 and (awardfrom = 'PLAT' or (awardfrom = 'SHOP' and pkFrom = ? )) ",longshop);	
		}

		}else{
			if(!type.equals("")){
		awardlist=(List<BdCustomerAward>) baseDao.pageQuery("from BdCustomerAward where IFNULL(dr,0)=0 and fairtype = ? and awardfrom = 'PLAT' ",type);
			}else{
		awardlist=(List<BdCustomerAward>) baseDao.pageQuery("from BdCustomerAward where IFNULL(dr,0)=0 and awardfrom = 'PLAT' ");	
			}
		}
		
		
		return awardlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryselect(String pkCustomeraward) {
		// TODO Auto-generated method stub
		Long longcustomeraward=Long.parseLong(pkCustomeraward);
		StringBuffer sqlbuffer=new StringBuffer();
		sqlbuffer.append(" select pk_combo as pkCombo,comboname,combomoney,(case when selectcombo is not null then 'Y' else 'N' end) as selectcombo,pk_customeraward_limit as pkCustomerawardLimit ");
		sqlbuffer.append(" ,pk_customeraward as pkCustomeraward  from platform_combo a left join  (select c.pk_combo as selectcombo,c.pk_customeraward_limit,b.pk_customeraward  from bd_customer_award b inner join bd_customer_award_limit c ");
		sqlbuffer.append(" on b.pk_customeraward=c.pk_customeraward ");
		sqlbuffer.append(" where IFNULL(b.dr,0)=0 and IFNULL(c.dr,0)=0 ");
		sqlbuffer.append(" and b.pk_customeraward = ?) d");
        sqlbuffer.append(" on a.pk_combo=d.selectcombo ");
		sqlbuffer.append(" where IFNULL(a.dr,0)=0   ");
		List<Object> objlist=(ArrayList<Object>)baseDao.querybysqlMap(sqlbuffer.toString(),null,null,longcustomeraward);
	    return objlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BdCustomerAward> QueryByGroup(String pkshopGroup) {
		// TODO Auto-generated method stub
		Long longpkshopGroup=Long.parseLong(pkshopGroup);
		ArrayList<BdCustomerAward> awardlist=(ArrayList<BdCustomerAward>) baseDao.pageQuery("from BdCustomerAward where IFNULL(dr,0)=0 and pkShopGroup = ? ",longpkshopGroup);	
	      return awardlist;
	}

}
