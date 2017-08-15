package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.constant.Vbillstatus;
import com.rockstar.o2o.pojo.ShopProductBook;
import com.rockstar.o2o.service.ShopProductBookService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ShopProductBookServiceImpl extends BaseServiceImpl implements ShopProductBookService{

	@Override
	public ShopProductBook save(ShopProductBook book) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(book);
		baseDao.add(book);
		book.setPkProductBook(Long.parseLong(pk.toString()));
		return book;
	}

	@Override
	public ShopProductBook getBookById(Long id) {
		// TODO Auto-generated method stub
		return (ShopProductBook) baseDao.getById(ShopProductBook.class,id);
	}

	@Override
	public void deleteInfoById(Long id) {
		// TODO Auto-generated method stub
		ShopProductBook book=(ShopProductBook) baseDao.getById(ShopProductBook.class, id);
		book.setDr((short) 1);
		book.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(book);	
	}

	@Override
	public int updateInfo(ShopProductBook book) {
		// TODO Auto-generated method stub
		book.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		int result=baseDao.update(book);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopProductBook> QueryByShop(String pk_shop, Integer curpage,
			Integer pagesize) {
		// TODO Auto-generated method stub
		Long longpkshop=Long.parseLong(pk_shop);
		List<ShopProductBook> booklists=new ArrayList<ShopProductBook>();
		if(curpage!=null){
			booklists=(List<ShopProductBook>) baseDao.pageQuery("from ShopProductBook where IFNULL(dr,0)=0 and pkShop = ? ",curpage,pagesize,longpkshop);
		}else{
			booklists=(List<ShopProductBook>) baseDao.pageQuery("from ShopProductBook where IFNULL(dr,0)=0 and pkShop = ? ",longpkshop);	
		}
		return booklists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryMaxNo(String code, String pkShop) {
		// TODO Auto-generated method stub
		Long longshop=Long.parseLong(pkShop);
		String sql="select LPAD(max(SUBSTR(bookno,length(bookno)-3,length(bookno)))+1,4,0) from shop_product_book where bookno like '"+code+"%' and IFNULL(dr,0)=0 and pk_shop = ? ";
		List<Object> list=(List<Object>) baseDao.querybysql(sql,longshop);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> Querywaitapprove(Integer curpage,Integer pagesize) {
		List<Object> booklists=new ArrayList<Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("select substr(pk_product_book,1,LENGTH(pk_product_book)) AS pkProductBook,(select shopname from shop_info where pk_shop=a.pk_shop) AS shopname,");
		buffer.append("bookno,booktime,operatorname,note,vbillstatus,apprivenote,bookmoney ");
		buffer.append(" from shop_product_book a where IFNULL(dr,0)=0 and vbillstatus = ? ");
		
		if(curpage!=null){
			booklists=(List<Object>) baseDao.querybysqlMap(buffer.toString(),curpage,pagesize,Vbillstatus.COMMIT_BILL[0]);

		}else{
			booklists=(List<Object>) baseDao.querybysqlMap(buffer.toString(),null,null,Vbillstatus.COMMIT_BILL[0]);
		}
		return booklists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> Queryapprove(String province, String city,
			String county, String pkShop, String begintime, String endtime,
			String vbillstatus, Integer curpage, Integer pagesize) {
		List<Object> booklists=new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("select substr(a.pk_product_book,1,LENGTH(a.pk_product_book)) AS pkProductBook,");
		buffer.append("a.bookno,");
		buffer.append("a.booktime,");
		buffer.append("a.operatorname,");
		buffer.append("a.note,");
		buffer.append("a.vbillstatus,");
		buffer.append("a.apprivenote,");
		buffer.append("a.bookmoney,");
		buffer.append("s.shopname ");
		buffer.append(" from shop_product_book a LEFT JOIN shop_info s on a.pk_shop=s.pk_shop ");
		buffer.append(" WHERE IFNULL(a.dr, 0) = 0 AND IFNULL(s.dr, 0) = 0");
		
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
		
		if(pkShop!=null&&!pkShop.equals("")){
			buffer.append(" and a.pk_shop = :longpkShop   " );
			Long longpkShop=Long.parseLong(pkShop);
			map.put("longpkShop", longpkShop);
		}
		 if(!begintime.equals("")){       	
			buffer.append(" and booktime >= :begintime ");
			map.put("begintime", begintime);	
         }
        
        if(!endtime.equals("")){
        	buffer.append(" and booktime <= :endtime ");
			map.put("endtime", endtime);
         }
		if(vbillstatus!=null&&!vbillstatus.trim().equals("")){
			buffer.append(" and  a.vbillstatus =:vbillstatus ");
			map.put("vbillstatus", vbillstatus);
		}else{
			buffer.append(" and  a.vbillstatus !='1' ");
		}
		buffer.append(" order by  a.booktime desc ");
		if(curpage!=null){
			booklists=(List<Object>) baseDao.querySqlListByConMap(buffer.toString(),curpage,pagesize,map);
		}else{
			booklists=(List<Object>) baseDao.querySqlListByConMap(buffer.toString(),map);
		}		
        return booklists;
	}

}
