package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopProductDeliveryB;
import com.rockstar.o2o.service.ShopProductDeliveryBService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ShopProductDeliveryBServiceImpl extends BaseServiceImpl implements ShopProductDeliveryBService{

	@Override
	public ShopProductDeliveryB save(ShopProductDeliveryB Delivery) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(Delivery);
		baseDao.add(Delivery);
		Delivery.setPkProductDeliveryB(Long.parseLong(pk.toString()));
		return Delivery;
	}

	@Override
	public void savelist(ArrayList<ShopProductDeliveryB> details) {
		// TODO Auto-generated method stub
		baseDao.batchsave(details);
	}

	@Override
	public void updatelist(ArrayList<ShopProductDeliveryB> details) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(details);
	}

	@Override
	public ShopProductDeliveryB getDeliveryById(Long id) {
		// TODO Auto-generated method stub
		return (ShopProductDeliveryB) baseDao.getById(ShopProductDeliveryB.class,id);
	}

	@Override
	public void deleteDeliveryById(Long id) {
		// TODO Auto-generated method stub
		ShopProductDeliveryB delivery=(ShopProductDeliveryB) baseDao.getById(ShopProductDeliveryB.class, id);
		delivery.setDr((short) 1);
		delivery.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(delivery);	
	}

	@Override
	public int updateDelivery(ShopProductDeliveryB Delivery) {
		// TODO Auto-generated method stub
		Delivery.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(Delivery);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopProductDeliveryB> QueryByPkBook(String pkDelivery,
			Integer curpage, Integer pagesize) {
		// TODO Auto-generated method stub
		Long longpkDelivery=Long.parseLong(pkDelivery);
		List<ShopProductDeliveryB> Deliverylists=new ArrayList<ShopProductDeliveryB>();
		if(curpage!=null){
			Deliverylists=(List<ShopProductDeliveryB>) baseDao.pageQuery("from ShopProductDeliveryB where IFNULL(dr,0)=0 and pkProductDelivery = ? ",curpage,pagesize,longpkDelivery);
		}else{
			Deliverylists=(List<ShopProductDeliveryB>) baseDao.pageQuery("from ShopProductDeliveryB where IFNULL(dr,0)=0 and pkProductDelivery = ? ",longpkDelivery);	
		}
		return Deliverylists;
	}

}
