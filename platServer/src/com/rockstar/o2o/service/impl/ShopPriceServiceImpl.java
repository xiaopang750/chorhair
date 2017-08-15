package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.service.ShopPriceService;

@Component
public class ShopPriceServiceImpl extends BaseServiceImpl implements ShopPriceService{

	@Override
	public ShopPrice save(ShopPrice price) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(price);
		baseDao.add(price);
		price.setPkPrice(Long.parseLong(pk.toString()));
		return price;
	}

	@Override
	public ShopPrice getPriceById(Long id) {
		// TODO Auto-generated method stub
		return (ShopPrice) baseDao.getById(ShopPrice.class,id);
	}

	@Override
	public void deletePriceById(Long id) {
		// TODO Auto-generated method stub
		ShopPrice price=(ShopPrice) baseDao.getById(ShopPrice.class, id);
		price.setDr((short) 1);
		baseDao.update(price);
	}

	@Override
	public int updatePrice(ShopPrice Price) {
		// TODO Auto-generated method stub
		int result=baseDao.update(Price);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopPrice> QueryByShop(String pk_shop,String fairtype) {
		// TODO Auto-generated method stub
		Long longpkshop=Long.parseLong(pk_shop);
		List<ShopPrice> pricelists=(List<ShopPrice>) baseDao.pageQuery("from ShopPrice a where IFNULL(a.dr,0)=0 and EXISTS (select 1 from ShopGroup where IFNULL(dr,0)=0 and pkShopGroup=a.pkShopPricegroup and pkShop = ? ) and a.fairtype = ?  ",longpkshop,fairtype);
		return pricelists;

	}


	@Override
	public void savelist(ArrayList<ShopPrice> prices) {
		// TODO Auto-generated method stub
		baseDao.batchsave(prices);
	}

	@Override
	public void updatelist(ArrayList<ShopPrice> prices) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(prices);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopPrice> QueryBygroup(String pk_shoppricegroup) {
		// TODO Auto-generated method stub
		Long longpkshoppricegroup=Long.parseLong(pk_shoppricegroup);
		List<ShopPrice> pricelists=(List<ShopPrice>) baseDao.pageQuery("from ShopPrice where IFNULL(dr,0)=0 and pkShopPricegroup = ? ",longpkshoppricegroup);
		return pricelists;
	}

}
