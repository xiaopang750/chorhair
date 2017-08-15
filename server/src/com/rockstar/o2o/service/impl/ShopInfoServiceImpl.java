package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.service.ShopInfoService;

@Component
public class ShopInfoServiceImpl extends BaseServiceImpl implements ShopInfoService{

	@Override
	public ShopInfo save(ShopInfo info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkShop(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public ShopInfo getShopById(Long id) {
		// TODO Auto-generated method stub
		return (ShopInfo) baseDao.getById(ShopInfo.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopInfo> getAllShop() {
		// TODO Auto-generated method stub
		return (List<ShopInfo>) baseDao.getAll(ShopInfo.class);
	}

	@Override
	public void deleteShopById(Long id) {
		// TODO Auto-generated method stub
		ShopInfo shop=(ShopInfo) baseDao.getById(ShopInfo.class, id);
		shop.setDr((short) 1);
		baseDao.update(shop);
	}

	@Override
	public int updateGroup(ShopInfo shop) {
		// TODO Auto-generated method stub
		int result=baseDao.update(shop);
	    return result;	
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopInfo> QueryShopByPagination(String province, String city, String county, String street, Integer curpage, Integer pagesize) {
		List<ShopInfo> shoplists = new ArrayList<ShopInfo>();
		StringBuffer stb=new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		stb.append("from ShopInfo where IFNULL(dr,0)=0 and shopcode!='plat' ");
		if(province!=null && !province.equals("")){
			stb.append("and province=:province ");
			map.put("province", province);
		}
		if(city!=null && !city.equals("")){
			stb.append("and city=:city ");
			map.put("city", city);
		}
		if(county!=null && !county.equals("")){
			stb.append("and county=:county ");
			map.put("county", county);
		}
		if(street!=null && !street.equals("")){
			stb.append("and street=:street ");
			map.put("street", street);
		}
		
		if(curpage!=null){
			shoplists=(List<ShopInfo>) baseDao.queryHqlListByConMap(stb.toString(),curpage,pagesize,map);
		}else{
			shoplists=(List<ShopInfo>) baseDao.queryHqlListByConMap(stb.toString(),map);
		}
		
        return shoplists;
	}

}
