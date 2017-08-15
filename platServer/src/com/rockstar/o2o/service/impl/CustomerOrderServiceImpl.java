package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.constant.OrderStatus;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.pojo.CustomerOrder;
import com.rockstar.o2o.service.CustomerOrderService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class CustomerOrderServiceImpl extends BaseServiceImpl implements CustomerOrderService{

	@Override
	public CustomerOrder save(CustomerOrder order) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(order);
		baseDao.add(order);
		order.setPkOrder(Long.parseLong(pk.toString()));
		return order;
	}

	@Override
	public CustomerOrder getOrderById(Long id) {
		// TODO Auto-generated method stub
		return (CustomerOrder) baseDao.getById(CustomerOrder.class,id);
	}

	@Override
	public void deleteOrderById(Long id) {
		// TODO Auto-generated method stub
		CustomerOrder order=(CustomerOrder) baseDao.getById(CustomerOrder.class, id);
		order.setDr((short) 1);
		order.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(order);
	}

	@Override
	public int updateOrder(CustomerOrder order) {
		// TODO Auto-generated method stub
		int result=baseDao.update(order);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> FindByCustomer(String PkCustomer) {
		// TODO Auto-generated method stub
		Long longcustomer=Long.parseLong(PkCustomer);
		List<CustomerOrder> orderlist=(List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder where IFNULL(dr,0)=0 and pkCustomer = ? ", longcustomer);
		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int FindByShopAndDate(String pkShop, String getdate) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pkShop);
		String sql="select count(pk_order)  from  customer_order where  IFNULL(dr,0)=0  and pk_shop = ? and substr(ordetime,1,10) = ? ";
		List<Object> obj= (List<Object>) baseDao.querybysql(sql, longcorp ,getdate);
		if(obj.size()>0){
			int count=Integer.parseInt(obj.get(0).toString());
			return count;
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> FindByShopAndDateAndPay(String province,String city,String county,String street,String pkShop, String begintime,String endtime,
			String orderstatus,String nameOrTellphone,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		
		List<CustomerOrder> orderlist=new ArrayList<CustomerOrder>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select c.*  " );
		buffer.append(" from customer_order c join shop_info s on c.pk_shop=s.pk_shop  " );
		buffer.append(" where IFNULL(c.dr,0)=0 and IFNULL(s.dr,0)=0   " );
		if(province!=null&&!province.equals("")){
			buffer.append(" and s.province = :province   " );
			map.put("province", province);
		}
		if(city!=null&&!city.equals("")){
			buffer.append(" and s.city = :city   " );
			map.put("city", city);
		}
		if(county!=null&&!county.equals("")){
			buffer.append(" and s.county = :county   " );
			map.put("county", county);
		}
		if(street!=null&&!street.equals("")){
			buffer.append(" and s.street = :street   " );
			map.put("street", street);
		}
		if(pkShop!=null&&!pkShop.equals("")){
			buffer.append(" and c.pk_shop = :longpkShop   " );
			Long longpkShop=Long.parseLong(pkShop);
			map.put("longpkShop", longpkShop);
		}
		if(nameOrTellphone!=null&&!nameOrTellphone.equals("")){
			buffer.append(" and (c.customername like :nameorphone or c.cellphone like :nameorphone ) " );
			map.put("nameorphone", "'%"+nameOrTellphone+"%'");
		}
		
		buffer.append(" and c.orderstatus = :orderStatus " );
		map.put("orderStatus", orderstatus);

		 if(begintime!=null&&!begintime.equals("")){
			 buffer.append(" and c.ordetime >= :orderbegintime ");
			 
			 map.put("orderbegintime", begintime);
		 }
		 if(endtime!=null&&!endtime.equals("")){
			 buffer.append(" and  c.ordetime <= :orderendtime ");			 
			 map.put("orderendtime", endtime);
		 }
		 
		 buffer.append(" order by c.ordetime desc ");
		 orderlist=(List<CustomerOrder>) baseDao.querySqlListByConMap(buffer.toString(),curpage,pagesize,map); 		
	     return orderlist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybysql(String code,String pkShop) {
		// TODO Auto-generated method stub
		Long longshop=Long.parseLong(pkShop);
		String sql="select LPAD(max(SUBSTR(orderno,length(orderno)-3,length(orderno)))+1,4,0) from customer_order where orderno like '"+code+"%' and IFNULL(dr,0)=0 and pk_shop = ? ";
		List<Object> list=(List<Object>) baseDao.querybysql(sql,longshop);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> FindByPkUser(String pkUser,String usergroup) {
		// TODO Auto-generated method stub
		Long longpkUser=Long.parseLong(pkUser);
		List<CustomerOrder> orderlist=new ArrayList<CustomerOrder> ();
		if(usergroup.equals(UserGroupObject.GROUP_ONE[0])){
			 orderlist=(List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder a where IFNULL(a.dr,0)=0 and EXISTS (select 1 from CustomerInfo where pkUser = ? and pkCustomer = a.pkCustomer ) ",longpkUser);
		}else if(usergroup.equals(UserGroupObject.GROUP_TWO[0])){
			 orderlist=(List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder a where IFNULL(a.dr,0)=0 and EXISTS (select 1 from FairerInfo where pkUser = ? and pkFairer = a.pkFairer ) ",longpkUser);
		}

		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> FindBeautyByPkUser(String pkUser) {
		// TODO Auto-generated method stub
		Long longpkUser=Long.parseLong(pkUser);
		List<CustomerOrder> orderlist=(List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder a where IFNULL(a.dr,0)=0 and IFNULL(a.isbeauty,'N')='Y' and EXISTS (select 1 from CustomerInfo where pkUser = ? and pkCustomer = a.pkCustomer ) ",longpkUser);		
		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> FindByComboAndPkcustomer(String pkCustomer,
			String pkShopcombo) {
		// TODO Auto-generated method stub
		Long longpkCustomer=Long.parseLong(pkCustomer);
		Long longpkShopcombo=Long.parseLong(pkShopcombo);
		List<CustomerOrder> orderlist=(List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder a where IFNULL(a.dr,0)=0 and paystatus='001' and IFNULL(iscombo,'N') = 'Y' and pkCustomer = ? and pkShopCumbo = ? ",longpkCustomer,longpkShopcombo);		
		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> FindByPkUserAndStatus(String pkUser,
			String usergroup, String status) {
		// TODO Auto-generated method stub
		Long longpkUser=Long.parseLong(pkUser);
		List<CustomerOrder> orderlist=new ArrayList<CustomerOrder> ();
		if(usergroup.equals(UserGroupObject.GROUP_ONE[0])){
			 orderlist=(List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder a where IFNULL(a.dr,0)=0 and orderstatus = ? and EXISTS (select 1 from CustomerInfo where pkUser = ? and pkCustomer = a.pkCustomer ) order by ordetime desc  ",status,longpkUser);
		}else if(usergroup.equals(UserGroupObject.GROUP_TWO[0])){
			 orderlist = (List<CustomerOrder>) baseDao.pageQuery("from CustomerOrder a where IFNULL(a.dr,0)=0 and orderstatus = ? and EXISTS (select 1 from FairerInfo where pkUser = ? and pkFairer = a.pkFairer ) order by ordetime desc  ",status,longpkUser);
		}

		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findbytime(String begintime, String endtime,
			String type, String pkShop,String paymode) {
		// TODO Auto-generated method stub
        StringBuffer buffer=new StringBuffer();
        Map<String, Object> map = new HashMap<String, Object>();
	    if(type.equals("1")){
	    	  buffer.append(" SELECT substr(ordetime,1,10) AS curdate,");
	    }else if(type.equals("2")){
	    	buffer.append(" SELECT DATE_FORMAT(ordetime, '%x年-第%v周') AS curdate,");
	    }else if(type.equals("3")){
	    	buffer.append(" SELECT DATE_FORMAT(ordetime,'%Y-%m') AS curdate,");
	    }else if(type.equals("4")){
	    	buffer.append(" SELECT DATE_FORMAT(ordetime,'%Y') AS curdate,");
	    }
		buffer.append(" sum(case when  IFNULL(iscombo,'N')='Y' then ordermoney else 0 end) AS combomoney,");
		buffer.append(" sum(case when  IFNULL(iscombo,'N')='N' then ordermoney else 0 end) AS servicemoney,");
		buffer.append(" sum(fairermoney) AS fairermoney ");
		buffer.append(" FROM customer_order ");
		buffer.append(" where ifnull(dr,0)=0 and pk_shop =:pkShop  ");
		Long longshop=Long.parseLong(pkShop);
		map.put("pkShop", longshop);
		
        if(!begintime.equals("")){       	
			buffer.append(" and ordetime >= :creditbegintime ");
			map.put("creditbegintime", begintime);	
         }
        
        if(!endtime.equals("")){
        	buffer.append(" and ordetime <= :creditendtime ");
			map.put("creditendtime", endtime);
         }
        if(!paymode.equals("")){
        	buffer.append(" and paymode = :paymode ");
			map.put("paymode", paymode);
         }
        buffer.append(" and orderstatus = :orderstatus ");
        map.put("orderstatus",OrderStatus.SERVICECOMPLETE[0] );
        buffer.append("	GROUP BY curdate order by curdate ");   		  	
		List<Object> list=new ArrayList<Object>();
		list=(List<Object>) baseDao.querySqlListByConMap(buffer.toString(),map);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> querybyshop(String pkShop,Integer curpage,Integer pagesize,String begintime,String endtime,String paystatus,String orderno) {
		// TODO Auto-generated method stub
		Long longpkShop=Long.parseLong(pkShop);
		List<CustomerOrder> orderlist=new ArrayList<CustomerOrder>();
		StringBuffer buffer=new StringBuffer();
		HashMap<String,Object> map=new HashMap<String,Object>();
		buffer.append("from CustomerOrder a where IFNULL(a.dr,0)=0 and pkShop = :pkshop ");
		map.put("pkshop", longpkShop);
		
		if(begintime!=null&&!begintime.equals("")){
			buffer.append(" and ordetime >= :begintime");	
			map.put("begintime", begintime);
		}
		if(endtime!=null&&!endtime.equals("")){
			buffer.append(" and ordetime <= :endtime ");	
			map.put("endtime", endtime);
		}
		if(paystatus!=null&&!paystatus.equals("")){
			buffer.append(" and paystatus =  :paystatus ");		
			map.put("paystatus", paystatus);
		}
		if(orderno!=null&&!orderno.equals("")){
			buffer.append(" and orderno =  :orderno ");		
			map.put("orderno", orderno);
		}
		buffer.append(" order by ordetime desc  ");
		orderlist=(List<CustomerOrder>) baseDao.queryHqlListByConMap(buffer.toString(),curpage,pagesize,map);					
		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> querybycon(String province,String city, String county,String pkShop, Integer curpage,
			Integer pagesize, String begintime, String endtime, String status, String likequery) {
		
		StringBuffer buffer=new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		buffer.append("from CustomerOrder  where IFNULL(dr,0)=0 and orderstatus = :Orderstatus  ");
		map.put("Orderstatus", status);
		
		if(province!=null&&!province.equals("")){
			buffer.append(" and province = :province  ");
			map.put("province", province);
		}
		if(city!=null&&!city.equals("")){
			buffer.append(" and city = :city  ");
			map.put("city", city);
		}
		
		if(county!=null&&!county.equals("")){
			buffer.append(" and county = :county  ");
			map.put("county", county);
		}
		if(pkShop!=null&&!pkShop.equals("")){
			buffer.append(" and pkShop = :Shop  ");
			Long longpkShop=Long.parseLong(pkShop);
			map.put("Shop", longpkShop);
		}
		
		if(likequery!=null&&!likequery.equals("")){
			buffer.append(" and (customername like '%"+likequery+"%' or cellphone like '%"+likequery+"%') ");
		}
		
		if(begintime!=null&&!begintime.equals("")){
			buffer.append(" and appointtime >= :ordebegintime ");	
			map.put("ordebegintime", begintime);
		}
		if(endtime!=null&&!endtime.equals("")){
			buffer.append(" and appointtime <= :ordeendtime  ");		
			map.put("ordeendtime", endtime);
		}
		buffer.append(" order by appointtime asc  ");
		
		ArrayList<CustomerOrder> orderlist=(ArrayList<CustomerOrder>) baseDao.queryHqlListByConMap(buffer.toString(),curpage,pagesize,map);				
		return orderlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int FindShopDayOrder(String getdate) {
		String sql="select count(pk_order)  from  customer_order where  IFNULL(dr,0)=0   and substr(ordetime,1,10) = ? and orderstatus in ('001','002') ";
		List<Object> obj= (List<Object>) baseDao.querybysql(sql, getdate);
		if(obj.size()>0){
			int count=Integer.parseInt(obj.get(0).toString());
			return count;
		}
		return 0;
	}


}
