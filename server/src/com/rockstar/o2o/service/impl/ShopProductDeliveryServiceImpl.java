package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopProductDelivery;
import com.rockstar.o2o.service.ShopProductDeliveryService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ShopProductDeliveryServiceImpl extends BaseServiceImpl implements ShopProductDeliveryService{

	@Override
	public ShopProductDelivery save(ShopProductDelivery book) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(book);
		baseDao.add(book);
		book.setPkProductDelivery(Long.parseLong(pk.toString()));
		return book;
	}

	@Override
	public ShopProductDelivery getDeliveryById(Long id) {
		// TODO Auto-generated method stub
		return (ShopProductDelivery) baseDao.getById(ShopProductDelivery.class,id);
	}

	@Override
	public void deleteDeliveryById(Long id) {
		// TODO Auto-generated method stub
		ShopProductDelivery Delivery=(ShopProductDelivery) baseDao.getById(ShopProductDelivery.class, id);
		Delivery.setDr((short) 1);
		Delivery.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(Delivery);	
	}

	@Override
	public int updateInfo(ShopProductDelivery delivery) {
		// TODO Auto-generated method stub
		delivery.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(delivery);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopProductDelivery> QueryByShop(String pk_shop,
			Integer curpage, Integer pagesize) {
		// TODO Auto-generated method stub
		Long longpkshop=Long.parseLong(pk_shop);
		List<ShopProductDelivery> Deliverylists=new ArrayList<ShopProductDelivery>();
		if(curpage!=null){
			Deliverylists=(List<ShopProductDelivery>) baseDao.pageQuery("from ShopProductDelivery where IFNULL(dr,0)=0 and pkShop = ? order by deliverytime desc",curpage,pagesize,longpkshop);
		}else{
			Deliverylists=(List<ShopProductDelivery>) baseDao.pageQuery("from ShopProductDelivery where IFNULL(dr,0)=0 and pkShop = ? order by deliverytime desc",longpkshop);	
		}
		return Deliverylists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryMaxNo(String code, String pkShop) {
		// TODO Auto-generated method stub
		Long longshop=Long.parseLong(pkShop);
		String sql="select LPAD(max(SUBSTR(deliveryno,length(deliveryno)-3,length(deliveryno)))+1,4,0) from shop_product_delivery where deliveryno like '"+code+"%' and IFNULL(dr,0)=0 and pk_shop = ? ";
		List<Object> list=(List<Object>) baseDao.querybysql(sql,longshop);
		return list;
	}

}
