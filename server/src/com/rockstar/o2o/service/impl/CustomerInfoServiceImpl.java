package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<CustomerInfo> querybynameandcell(String likecontent,String pkShop,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		Long longpkshop=Long.parseLong(pkShop);
		List<CustomerInfo> customerlists = new ArrayList<CustomerInfo>();
		if(curpage!=null){
		customerlists=(List<CustomerInfo>) baseDao.pageQuery("from CustomerInfo where IFNULL(dr,0)=0 and (cellphone like '%"+likecontent+"%' or customername like '%"+likecontent+"%' or py like '%"+likecontent+"%' or shortpy like '%"+likecontent+"%') and pkShop = ?  order by  pkCustomer desc ",curpage,pagesize,longpkshop);
		}else{
		customerlists=(List<CustomerInfo>) baseDao.pageQuery("from CustomerInfo where IFNULL(dr,0)=0 and (cellphone like '%"+likecontent+"%' or customername like '%"+likecontent+"%' or py like '%"+likecontent+"%' or shortpy like '%"+likecontent+"%' ) and pkShop = ?  order by  pkCustomer desc ",longpkshop);	
		}
		return customerlists;

	}

}
