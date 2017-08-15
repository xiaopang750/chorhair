package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.service.ShopFairerawardService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ShopFairerawardServiceImpl extends BaseServiceImpl implements ShopFairerawardService{

	@Override
	public ShopFaireraward save(ShopFaireraward award) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(award);
		baseDao.add(award);
		award.setPkFaireraward(Long.parseLong(pk.toString()));
		return award;
	}

	@Override
	public void savelist(ArrayList<ShopFaireraward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchsave(awards);
	}

	@Override
	public List<Object> savelists(ArrayList<ShopFaireraward> awards) {
		// TODO Auto-generated method stub
		return baseDao.batchsaves(awards);
	}
	
	@Override
	public void updatelist(ArrayList<ShopFaireraward> awards) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(awards);
	}

	@Override
	public ShopFaireraward getawardById(Long id) {
		// TODO Auto-generated method stub
		return (ShopFaireraward) baseDao.getById(ShopFaireraward.class,id);
	}

	@Override
	public void deleteawardById(Long id) {
		// TODO Auto-generated method stub
		ShopFaireraward award=(ShopFaireraward) baseDao.getById(ShopFaireraward.class, id);
		award.setDr((short) 1);
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(award);
	}

	@Override
	public int updateaward(ShopFaireraward award) {
		// TODO Auto-generated method stub
		award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(award);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopFaireraward> querybyshopandtype(String pkShop,
			String pkKey, String type) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pkShop);
		Long longpkKey=Long.parseLong(pkKey);
		List<ShopFaireraward> lists= (List<ShopFaireraward>) baseDao.pageQuery("from ShopFaireraward where IFNULL(dr,0)=0 and pkShop = ? and pkKey = ? and awardtype = ?  ", longcorp,longpkKey,type);
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopFaireraward> queryvalidate(String pkShop, String pkKey,
			String type) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pkShop);
		Long longpkKey=Long.parseLong(pkKey);
		List<ShopFaireraward> lists= (List<ShopFaireraward>) baseDao.pageQuery("from ShopFaireraward where IFNULL(dr,0)=0 and pkShop = ? and pkKey = ? and awardtype = ?  and IFNULL(isvalidate,'N') != ?", longcorp,longpkKey,type,"N");
		return lists;
	}



}
