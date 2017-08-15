package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopProductInfo;
import com.rockstar.o2o.service.ShopProductInfoService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ShopProductInfoServiceImpl extends BaseServiceImpl implements ShopProductInfoService{

	@Override
	public ShopProductInfo save(ShopProductInfo info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkShopProdcut(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public ShopProductInfo getInfoById(Long id) {
		// TODO Auto-generated method stub
		return (ShopProductInfo) baseDao.getById(ShopProductInfo.class,id);
	}

	@Override
	public void deleteInfoById(Long id) {
		// TODO Auto-generated method stub
		ShopProductInfo product=(ShopProductInfo) baseDao.getById(ShopProductInfo.class, id);
		product.setDr((short) 1);
		product.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(product);	
	}

	@Override
	public int updateInfo(ShopProductInfo info) {
		// TODO Auto-generated method stub
		info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(info);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopProductInfo> QueryByShop(String pk_shop, String content,Integer curpage,
			Integer pagesize) {
		// TODO Auto-generated method stub
		Long longpkshop=Long.parseLong(pk_shop);
		List<ShopProductInfo> productlists=new ArrayList<ShopProductInfo>();
		if(curpage!=null){
		productlists=(List<ShopProductInfo>) baseDao.pageQuery("from ShopProductInfo where IFNULL(dr,0)=0 and pkShop = ? and (productname like '%"+content+"%' or py like '%"+content+"%' or shortpy like '%"+content+"%'  ) ",curpage,pagesize,longpkshop);
		}else{
		productlists=(List<ShopProductInfo>) baseDao.pageQuery("from ShopProductInfo where IFNULL(dr,0)=0 and pkShop = ? and (productname like '%"+content+"%' or py like '%"+content+"%' or shortpy like '%"+content+"%'  ) ",longpkshop);	
		}
		return productlists;
	}

	@Override
	public void updatelist(ArrayList<ShopProductInfo> infos) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(infos);
	}

}
