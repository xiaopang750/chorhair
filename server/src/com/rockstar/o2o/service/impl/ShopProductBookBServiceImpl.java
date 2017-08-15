package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.ShopProductBookB;
import com.rockstar.o2o.service.ShopProductBookBService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ShopProductBookBServiceImpl extends BaseServiceImpl implements ShopProductBookBService{

	@Override
	public ShopProductBookB save(ShopProductBookB book) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(book);
		baseDao.add(book);
		book.setPkProductBookB(Long.parseLong(pk.toString()));
		return book;
	}

	@Override
	public void savelist(ArrayList<ShopProductBookB> details) {
		// TODO Auto-generated method stub
		baseDao.batchsave(details);
	}

	@Override
	public void updatelist(ArrayList<ShopProductBookB> details) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(details);
	}

	@Override
	public ShopProductBookB getBookById(Long id) {
		// TODO Auto-generated method stub
		return (ShopProductBookB) baseDao.getById(ShopProductBookB.class,id);
	}

	@Override
	public void deleteBookById(Long id) {
		// TODO Auto-generated method stub
		ShopProductBookB book=(ShopProductBookB) baseDao.getById(ShopProductBookB.class, id);
		book.setDr((short) 1);
		book.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(book);	
	}

	@Override
	public int updateBook(ShopProductBookB book) {
		// TODO Auto-generated method stub
		book.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(book);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopProductBookB> QueryByPkBook(String pkBook, Integer curpage,
			Integer pagesize) {
		// TODO Auto-generated method stub
		Long longpkBook=Long.parseLong(pkBook);
		List<ShopProductBookB> booklists=new ArrayList<ShopProductBookB>();
		if(curpage!=null){
			booklists=(List<ShopProductBookB>) baseDao.pageQuery("from ShopProductBookB where IFNULL(dr,0)=0 and pkProductBook = ? ",curpage,pagesize,longpkBook);
		}else{
			booklists=(List<ShopProductBookB>) baseDao.pageQuery("from ShopProductBookB where IFNULL(dr,0)=0 and pkProductBook = ? ",longpkBook);	
		}
		return booklists;
	}

}
