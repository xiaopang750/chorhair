package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.PlatformComboProduct;
import com.rockstar.o2o.service.PlatformComboProductService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class PlatformComboProductServiceImpl extends BaseServiceImpl implements PlatformComboProductService{

	@Override
	public PlatformComboProduct save(PlatformComboProduct product) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(product);
		baseDao.add(product);
		product.setPkComboProduct(Long.parseLong(pk.toString()));
		return product;
	}

	@Override
	public PlatformComboProduct getproductById(Long id) {
		// TODO Auto-generated method stub
		return (PlatformComboProduct) baseDao.getById(PlatformComboProduct.class,id);
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		PlatformComboProduct service=(PlatformComboProduct) baseDao.getById(PlatformComboProduct.class, id);
		service.setDr((short) 1);
		service.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(service);
	}

	@Override
	public int updateproduct(PlatformComboProduct product) {
		// TODO Auto-generated method stub
		int result=baseDao.update(product);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatformComboProduct> QueryByCombo(String pkCombo) {
		// TODO Auto-generated method stub
		Long longcombo=Long.parseLong(pkCombo);
		List<PlatformComboProduct> productlist=(List<PlatformComboProduct>) baseDao.pageQuery("from PlatformComboProduct where IFNULL(dr,0)=0 and pkCombo = ? ", longcombo);
		return productlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlatformComboProduct queryByProductAndCombo(long pkProduct,long pkCombo) {
		List<PlatformComboProduct> productlist=(List<PlatformComboProduct>) baseDao.pageQuery("from PlatformComboProduct where IFNULL(dr,0)=0 and pkCombo = ? and pkProduct=? ", pkCombo,pkProduct);
		if(productlist.size()>0){
			return productlist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> queryByCombo(long pkCombo) {
		List<Integer> lists=(List<Integer>) baseDao.querybysql("select pk_product from platform_combo_product where IFNULL(dr,0)=0 and pk_combo = ? ",pkCombo);
		if(lists.size()>0){
			return lists;
		}
		return null;
	}

	

}
