package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.service.ShopComboService;
import com.rockstar.o2o.vo.ShopUserCombo;

@Component
public class ShopComboServiceImpl extends BaseServiceImpl implements ShopComboService{

	@Override
	public ShopCombo save(ShopCombo combo) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(combo);
		combo.setPkShopCombo(Long.parseLong(pk.toString()));
		return combo;
	}

	@Override
	public ShopCombo getComboById(Long id) {
		// TODO Auto-generated method stub
		return (ShopCombo) baseDao.getById(ShopCombo.class,id);
	}

	@Override
	public void deleteComboById(Long id) {
		// TODO Auto-generated method stub
		ShopCombo combo=(ShopCombo) baseDao.getById(ShopCombo.class, id);
		combo.setDr((short) 1);
		baseDao.update(combo);
	}

	@Override
	public int updateCombo(ShopCombo combo) {
		// TODO Auto-generated method stub
		int result=baseDao.update(combo);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopCombo> QueryByShop(String pk_shop,String querylike,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		List<ShopCombo> combolists=new ArrayList<ShopCombo>();
		HashMap<String, Object> map=new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("from ShopCombo where IFNULL(dr,0)=0 and pkShop = :pkshop ");
		map.put("pkshop", longcorp);
		if(querylike!=null&&!querylike.trim().equals("")){
			buffer.append(" and (combocode like '%"+querylike+"%'  or comboname like '%"+querylike+"%' or combopy like '%"+querylike+"%'  or combospy like '%"+querylike+"%') ");
		}
		combolists=(List<ShopCombo>) baseDao.queryHqlListByConMap(buffer.toString(),curpage,pagesize,map);
		return combolists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopUserCombo> QueryByShopAndCustomer(String pk_shop,
			String pk_customer) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		Long longcustomer=Long.parseLong(pk_customer);
		String sql="select a.pk_combo pk_combo,a.pk_shop_combo pk_shop_combo,a.pk_shop pk_shop,a.comboname comboname,a.combotype combotype,IFNULL(b.combomoney,a.currentmoney) currentmoney,a.combocount combocount,b.pk_customer_combo pk_customer_combo,b.pk_customer pk_customer,IFNULL(b.combobegintime,' ') combobegintime,IFNULL(b.comboendtime,' ') comboendtime,b.leftcount leftcount,b.intruduceman intruduceman from shop_combo a left join (select * from customer_combo where IFNULL(dr,0)=0 and pk_customer  = ? )  b on a.pk_shop_combo=b.pk_shop_combo where  IFNULL(a.dr,0)=0  and a.pk_shop = ? ";
		List<ShopUserCombo> obj= (List<ShopUserCombo>) baseDao.querybysqlclass(ShopUserCombo.class,sql,longcustomer,longcorp);
		return obj;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopCombo> QueryByShopAndSex(String pk_shop, String querylike,String sex,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		HashMap<String, Object> map=new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("from ShopCombo w where IFNULL(dr,0)=0 and pkShop = :pkshop and NOT EXISTS (select 1 from ShopCombo where fairtype in ('5','7','8') and combotype='2' and pkShopCombo=w.pkShopCombo) and (fitgroup = :fitgroup or fitgroup = '3')");
		map.put("pkshop", longcorp);
		map.put("fitgroup", sex);
		if(querylike!=null&&!querylike.trim().equals("")){
			buffer.append(" and (combocode like '%"+querylike+"%'  or comboname like '%"+querylike+"%' or combopy like '%"+querylike+"%'  or combospy like '%"+querylike+"%') ");
		}
		List<ShopCombo> combolists=(List<ShopCombo>) baseDao.queryHqlListByConMap(buffer.toString(), curpage, pagesize, map);
	    return combolists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopCombo> QueryByShopAndtype(String pk_shop, String fairtype,
			String combotype) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		List<ShopCombo> combolists=(List<ShopCombo>) baseDao.pageQuery("from ShopCombo where IFNULL(dr,0)=0 and pkShop = ? and fairtype = ? and combotype = ? ",longcorp,fairtype,combotype);
	    return combolists;
	}

}
