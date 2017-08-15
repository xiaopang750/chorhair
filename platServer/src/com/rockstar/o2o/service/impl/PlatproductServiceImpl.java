package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.ProductInfo;
import com.rockstar.o2o.service.PlatproductService;

@Component
public class PlatproductServiceImpl extends BaseServiceImpl implements PlatproductService {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInfo> findproduct(String productname, Integer curpage,Integer pagesize) {
		List<ProductInfo> productlists=new ArrayList<ProductInfo>();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" from ProductInfo where IFNULL(dr,0)=0 " );
		if(productname!=null&&!productname.equals("")){
			buffer.append("  and (productname like '%"+productname+"%' or py like '%"+productname+"%' or shortpy like '%"+productname+"%' ) " );
		}
		buffer.append(" order by pkProduct desc " );
		if(curpage!=null){
			productlists=(List<ProductInfo>) baseDao.pageQuery(buffer.toString(),curpage,pagesize);
		}else{
			productlists=(List<ProductInfo>) baseDao.pageQuery(buffer.toString());
		}
		return productlists;
	}

	@Override
	public ProductInfo save(ProductInfo info) {
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkProduct(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public ProductInfo findById(long id) {
		return (ProductInfo) baseDao.getById(ProductInfo.class,id);
	}

	@Override
	public int update(ProductInfo info) {
		int result=baseDao.update(info);
	    return result;
	}

	@Override
	public void deleteById(Long pkProduct) {
		ProductInfo info=(ProductInfo) baseDao.getById(ProductInfo.class, pkProduct);
		info.setDr((short) 1);
		baseDao.update(info);
	}

}
