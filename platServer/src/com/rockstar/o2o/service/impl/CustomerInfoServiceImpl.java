package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.service.CustomerInfoService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class CustomerInfoServiceImpl extends BaseServiceImpl implements CustomerInfoService{

	@Override
	public CustomerInfo save(CustomerInfo info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkCustomer(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public CustomerInfo getCustomerById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerInfo) baseDao.getById(CustomerInfo.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerInfo> getAllCustomer() {
		// TODO Auto-generated method stub
		return (List<CustomerInfo>) baseDao.getAll(CustomerInfo.class);
	}

	@Override
	public void deleteCustomerById(Long id) {
		// TODO Auto-generated method stub
		CustomerInfo message=(CustomerInfo) baseDao.getById(CustomerInfo.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public int updateGroup(CustomerInfo customer) {
		// TODO Auto-generated method stub
		int result=baseDao.update(customer);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerInfo> QueryByShop(String pk_shop,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		List<CustomerInfo> customerlists = new ArrayList<CustomerInfo>();
		if(curpage!=null){
			customerlists=(List<CustomerInfo>) baseDao.pageQuery("from CustomerInfo where IFNULL(dr,0)=0 and pkShop = ?   order by  pkCustomer desc ",curpage,pagesize,longcorp);
		}else{
			customerlists=(List<CustomerInfo>) baseDao.pageQuery("from CustomerInfo where IFNULL(dr,0)=0 and pkShop = ?   order by  pkCustomer desc ",longcorp);
		}
		return customerlists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybysql(String sql) {
		// TODO Auto-generated method stub
		List<Object> list=(List<Object>) baseDao.querybysql(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerInfo QueryByCellphone(String cellphone) {
		// TODO Auto-generated method stub
		List<CustomerInfo> userlist=(List<CustomerInfo>) baseDao.pageQuery("from CustomerInfo where IFNULL(dr,0)=0 and cellphone = ? ", cellphone);
		if(userlist.size()>0){
			return userlist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerInfo QueryByCellandShop(String cellhone, String pk_shop) {
		// TODO Auto-generated method stub
		Long pkShop=Long.parseLong(pk_shop);
			List<CustomerInfo> userlist=(List<CustomerInfo>) baseDao.pageQuery("from CustomerInfo where IFNULL(dr,0)=0 and pkShop = ? and cellphone = ? ", pkShop,cellhone);
			if(userlist.size()>0){
				return userlist.get(0);
			}	
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerInfo> querybynameandcell(String province,String city,String county,String likecontent,String pkShop,Integer curpage,Integer pagesize) {
		List<CustomerInfo> customerlists = new ArrayList<CustomerInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" from CustomerInfo c where IFNULL(c.dr,0)=0 " );
		buffer.append(" and EXISTS (select 1 from ShopInfo  where  IFNULL(dr,0)=0 and pkShop = c.pkShop   " );
		if(province!=null&&!province.equals("")){
			buffer.append(" and province = :province   " );
			map.put("province", province);
		}
		if(city!=null&&!city.equals("")){
			buffer.append(" and city = :city   " );
			map.put("city", city);
		}
		if(county!=null&&!county.equals("")){
			buffer.append(" and county = :county   " );
			map.put("county", county);
		}
		buffer.append(")");
		if(pkShop!=null&&!pkShop.equals("")){
			buffer.append(" and c.pkShop = :longpkShop   " );
			Long longpkShop=Long.parseLong(pkShop);
			map.put("longpkShop", longpkShop);
		}
		if(likecontent!=null&&!likecontent.trim().equals("")){
			buffer.append(" and (c.cellphone like '%"+likecontent+"%' or c.customername like '%"+likecontent+"%' or c.py like '%"+likecontent+"%' or c.shortpy like '%"+likecontent+"%') ");
		}
		buffer.append(" order by  c.pkCustomer desc ");

		if(curpage!=null){
			customerlists=(List<CustomerInfo>) baseDao.queryHqlListByConMap(buffer.toString(),curpage,pagesize,map);
		}else{
			customerlists=(List<CustomerInfo>) baseDao.queryHqlListByConMap(buffer.toString(),map);
		}
		return customerlists;

	}

}
