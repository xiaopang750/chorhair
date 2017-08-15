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
	public List<ShopInfo> QueryShopByPagination(String province, String city, String county, String street,String pkShop, Integer curpage, Integer pagesize) {
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
		if(pkShop!=null && !pkShop.equals("")){
			stb.append("and pkShop=:pkShop ");
			long pkshopL = Long.parseLong(pkShop);
			map.put("pkShop", pkshopL);
		}
		
		if(curpage!=null){
			shoplists=(List<ShopInfo>) baseDao.queryHqlListByConMap(stb.toString(),curpage,pagesize,map);
		}else{
			shoplists=(List<ShopInfo>) baseDao.queryHqlListByConMap(stb.toString(),map);
		}
		
        return shoplists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int QueryByShopcode(String shopcode, String pkShop) {
		List<ShopInfo> shoplists = new ArrayList<ShopInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stb=new StringBuffer();
		stb.append("from ShopInfo where IFNULL(dr,0)=0 and shopcode=:shopcode ");
		if(pkShop!=null && !pkShop.equals("")){
			stb.append(" and pkShop != :pkShop ");
			long pkshopL = Long.parseLong(pkShop);
			map.put("pkShop", pkshopL);
		}
		map.put("shopcode", shopcode);
		shoplists=(List<ShopInfo>) baseDao.queryHqlListByConMap(stb.toString(),map);
		return shoplists.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopInfo> getAllShopAddr(String fatherArea, String level) {
		List<ShopInfo> shoplists = new ArrayList<ShopInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer stb=new StringBuffer();
		if(fatherArea!=null){
			if(level!=null && level.equals("1")){
				stb.append(" select DISTINCT city,cityname from shop_info where IFNULL(dr,0)=0 and province is NOT NULL ");
				stb.append(" and province =:province");
				map.put("province", fatherArea);
			}else if(level!=null && level.equals("2")){
				stb.append(" select DISTINCT county,countyname from shop_info where IFNULL(dr,0)=0 and province is NOT NULL ");
				stb.append(" and city =:city");
				map.put("city", fatherArea);
			}else if(level!=null && level.equals("3")){
				stb.append(" select DISTINCT pk_shop as pkShop,shopname from shop_info where IFNULL(dr,0)=0 and province is NOT NULL ");
				stb.append(" and county =:county");
				map.put("county", fatherArea);
			}
		}else{
			stb.append("select DISTINCT province,provincename from shop_info where IFNULL(dr,0)=0 and province is NOT NULL ");
		}
		shoplists=(List<ShopInfo>) baseDao.querySqlListByConMap(stb.toString(),map);
		return shoplists;
	}

}
