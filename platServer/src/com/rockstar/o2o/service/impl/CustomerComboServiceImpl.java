package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.CustomerCombo;
import com.rockstar.o2o.service.CustomerComboService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class CustomerComboServiceImpl extends BaseServiceImpl implements CustomerComboService {

	@Override
	public CustomerCombo save(CustomerCombo combo) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(combo);
		baseDao.add(combo);
		combo.setPkCustomerCombo(Long.parseLong(pk.toString()));
		return combo;
	}

	@Override
	public void batchsave(ArrayList<CustomerCombo> combos) {
		// TODO Auto-generated method stub
		baseDao.batchsave(combos);
	}
	
	@Override
	public CustomerCombo getComboById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerCombo) baseDao.getById(CustomerCombo.class,id);
	}

	@Override
	public void deleteComboById(Long id) {
		// TODO Auto-generated method stub
		CustomerCombo combo=(CustomerCombo) baseDao.getById(CustomerCombo.class, id);
		combo.setDr((short) 1);
		combo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(combo);
	}

	@Override
	public int updateCombo(CustomerCombo combo) {
		// TODO Auto-generated method stub
		int result=baseDao.update(combo);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerCombo>  QueryByPkCustomer(String PkCustomer,Integer curpage,Integer pagesize) {
		Long longcustomer=Long.parseLong(PkCustomer);
		// TODO Auto-generated method stub
		List<CustomerCombo> combolist=new ArrayList<CustomerCombo>();
		if(curpage!=null){
			combolist=(List<CustomerCombo>) baseDao.pageQuery("from CustomerCombo w where IFNULL(dr,0)=0 and pkCustomer = ? and NOT EXISTS (select 1 from CustomerCombo where fairtype in ('5','7','8') and combotype='2' and pkCustomerCombo=w.pkCustomerCombo) and IFNULL(iscomplete,'N')='N' order by  pkCustomerCombo desc",curpage,pagesize,longcustomer);	
		}else{
			combolist=(List<CustomerCombo>) baseDao.pageQuery("from CustomerCombo w where IFNULL(dr,0)=0 and pkCustomer = ?  and NOT EXISTS (select 1 from CustomerCombo where fairtype in ('5','7','8')  and combotype='2' and pkCustomerCombo=w.pkCustomerCombo)  and IFNULL(iscomplete,'N')='N'  order by  pkCustomerCombo desc", longcustomer);	
		}
		
		return combolist;
	}

	@Override
	public void batchupdate(ArrayList<CustomerCombo> combos) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(combos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomerCombo QueryByCombo(String pkShopcombo,String pkCustomer) {
		// TODO Auto-generated method stub
		Long longpkShopcombo=Long.parseLong(pkShopcombo);
		Long longpkCustomer=Long.parseLong(pkCustomer);
		// TODO Auto-generated method stub
		List<CustomerCombo> combolist=(List<CustomerCombo>) baseDao.pageQuery("from CustomerCombo where IFNULL(dr,0)=0 and IFNULL(iscomplete,'N')='N' and pkCustomer = ? and pkShopCombo = ? ", longpkCustomer, longpkShopcombo);
		if(combolist.size()>0){
			return combolist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerCombo> querybyuser(String Pkuser,String fairtype) {
		Long longPkuser=Long.parseLong(Pkuser);
		List<CustomerCombo> combolist =  new ArrayList<CustomerCombo>();
		if(fairtype!=null&&!fairtype.equals("")){
			 combolist=(List<CustomerCombo>) baseDao.pageQuery("from CustomerCombo combo where IFNULL(combo.dr,0)=0 and EXISTS (select 1 from ShopCombo where pkShopCombo = combo.pkShopCombo and IFNULL(dr,0)=0 and combotype <> '2' ) and EXISTS (select 1 from CustomerInfo where IFNULL(dr,0)=0 and pkCustomer = combo.pkCustomer and pkUser = ? ) and fairtype = ? ", longPkuser,fairtype);	
		}else{
			 combolist=(List<CustomerCombo>) baseDao.pageQuery("from CustomerCombo combo where IFNULL(combo.dr,0)=0 and EXISTS (select 1 from ShopCombo where pkShopCombo = combo.pkShopCombo and IFNULL(dr,0)=0 and combotype <> '2' ) and EXISTS (select 1 from CustomerInfo where IFNULL(dr,0)=0 and pkCustomer = combo.pkCustomer and pkUser = ? ) ", longPkuser);
		}
		
		return combolist;
	}

	@SuppressWarnings("unchecked")
	public int queryByPkShopcombo(long pkShopcombo) {
		List<CustomerCombo> combolist=(List<CustomerCombo>) baseDao.pageQuery("from CustomerCombo where IFNULL(dr,0)=0 and pkShopCombo = ? ",pkShopcombo);
		if(combolist.size()>0){
			return combolist.size();
		}
		return 0;
	}

}
