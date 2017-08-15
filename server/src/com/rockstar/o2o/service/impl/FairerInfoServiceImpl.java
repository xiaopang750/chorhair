package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.service.FairerInfoService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class FairerInfoServiceImpl extends BaseServiceImpl implements FairerInfoService{

	@Override
	public FairerInfo save(FairerInfo info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkFairer(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public FairerInfo getFairerById(Long id) {
		// TODO Auto-generated method stub
		return (FairerInfo) baseDao.getById(FairerInfo.class,id);
	}

	@Override
	public void deleteFairerById(Long id) {
		// TODO Auto-generated method stub
		FairerInfo fairer=(FairerInfo) baseDao.getById(FairerInfo.class, id);
		fairer.setDr((short) 1);
		fairer.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(fairer);
	}

	@Override
	public int updateFairer(FairerInfo info) {
		// TODO Auto-generated method stub
		int result=baseDao.update(info);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FairerInfo> QueryByShop(String pk_shop,String querylike,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		List<FairerInfo> fairerlists = new ArrayList<FairerInfo>();
		HashMap<String, Object> map=new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("from FairerInfo where IFNULL(dr,0)=0 and pkShop = :pkshop ");
		map.put("pkshop", longcorp);
		if(querylike!=null&&!querylike.trim().equals("")){
			buffer.append(" and (fairername like '%"+querylike+"%'  or nickname like '%"+querylike+"%' or cellphone like '%"+querylike+"%') ");
		}
		buffer.append(" order by  pkFairer desc ");
		if(curpage!=null){
			 fairerlists=(List<FairerInfo>) baseDao.queryHqlListByConMap(buffer.toString(),curpage,pagesize,map);
		}else{
			fairerlists=(List<FairerInfo>) baseDao.queryHqlListByConMap(buffer.toString(),map);
		}
		
        return fairerlists;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FairerInfo> QueryByCellandShop(String cellphone, String pkShop) {
		// TODO Auto-generated method stub
		Long longpkShop=Long.parseLong(pkShop);
		List<FairerInfo> fairerlist=(List<FairerInfo>) baseDao.pageQuery("from FairerInfo where IFNULL(dr,0)=0 and pkShop = ? and cellphone = ? ", longpkShop,cellphone);
		return fairerlist;

	}

	@Override
	public void updatelist(ArrayList<FairerInfo> fairers) {
		// TODO Auto-generated method stub
		baseDao.batchupdate(fairers);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object> Queryrank(String begintime, String endtime,
//			String pkShop) {
//		// TODO Auto-generated method stub
//		StringBuffer buffer=new StringBuffer();
//		buffer.append(" select pk_fairer,fairername,headshorturl,(select sum(ordermoney) from customer_order where ");
//		buffer.append(" IFNULL(dr,0)=0 and pk_fairer=a.pk_fairer and IFNULL(iscombo,'N')='N' and paystatus='002' ");
//		if(!begintime.equals("")){
//			buffer.append(" and substr(ordetime,1,10) >= ? ");
//		}
//		if(!endtime.equals("")){
//			buffer.append(" and substr(ordetime,1,10) <= ? ");
//		}
//		
//		buffer.append(" ) servicemoney,");//服务金额
//		
//		buffer.append(" (select sum(creditmoney) from fairer_credit where ");
//		buffer.append(" IFNULL(dr,0)=0 and pk_fairer=a.pk_fairer");
//		if(!begintime.equals("")){
//			buffer.append(" and substr(credittime,1,10) >= ? ");
//		}
//		if(!endtime.equals("")){
//			buffer.append(" and substr(credittime,1,10) <= ? ");
//		}
//		
//		buffer.append(" ) accountmoney,");//提成金额
//		
//		buffer.append(" (select count(DISTINCT pk_order) from customer_order where ");
//		
//		buffer.append(" IFNULL(dr,0)=0 and pk_fairer=a.pk_fairer and IFNULL(iscombo,'N')='N' and paystatus='002' ");
//		
//		if(!begintime.equals("")){
//			buffer.append(" and substr(ordetime,1,10) >= ? ");
//		}
//		if(!endtime.equals("")){
//			buffer.append(" and substr(ordetime,1,10) <= ? ");
//		}
//		
//		buffer.append(" ) accountnum");//服务次数
//		
//		buffer.append(" from fairer_info a  where IFNULL(a.dr,0)=0 and a.pk_shop = ? ");
//		
//		buffer.append(" order by servicemoney desc");
//		
//		List<Object> list=new ArrayList<Object>();
//		
//		Long longshop=Long.parseLong(pkShop);
//		
//		if(!begintime.equals("")&&!endtime.equals("")){
//			list=(List<Object>) baseDao.querybysqlMap(buffer.toString(),null,null,begintime,endtime,begintime,endtime,begintime,endtime,longshop);
//		}else if(!begintime.equals("")&&endtime.equals("")){
//			list=(List<Object>) baseDao.querybysqlMap(buffer.toString(),null,null,begintime,begintime,begintime,longshop);
//		}else if(begintime.equals("")&&!endtime.equals("")){
//			list=(List<Object>) baseDao.querybysqlMap(buffer.toString(),null,null,endtime,endtime,endtime,longshop);
//		}else{
//			list=(List<Object>) baseDao.querybysqlMap(buffer.toString(),null,null,longshop);
//		}
//		return list;
//	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> Queryrank(String begintime, String endtime,String pkShop,String fairername,String paymode) {
		// TODO Auto-generated method stub
		StringBuffer buffer=new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		buffer.append(" select a.pk_fairer,a.fairername,a.headshorturl,(select sum(ordermoney) from customer_order where ");
		buffer.append(" IFNULL(dr,0)=0 and pk_fairer=a.pk_fairer and IFNULL(iscombo,'N')='N' and paystatus='002' ");
		if(!begintime.equals("")){
			buffer.append(" and ordetime >= :ordebegintime ");
			map.put("ordebegintime", begintime);
		}
		if(!endtime.equals("")){
			buffer.append(" and ordetime <= :ordeendtime ");
			map.put("ordeendtime", endtime);
		}
		if(!paymode.equals("")){
			buffer.append(" and paymode = :paymode ");
			map.put("paymode", paymode);
		}
		buffer.append(" ) servicemoney,");//服务金额
		
		buffer.append(" (select sum(creditmoney) from fairer_credit where ");
		buffer.append(" IFNULL(dr,0)=0 and pk_fairer=a.pk_fairer");
		if(!begintime.equals("")){
			buffer.append(" and credittime >= :creditbegintime ");
			map.put("creditbegintime", begintime);
		}
		if(!endtime.equals("")){
			buffer.append(" and credittime <= :creditendtime ");
			map.put("creditendtime", endtime);
		}		
		buffer.append(" ) accountmoney,");//提成金额
		
		buffer.append(" (select count(distinct pk_order) from customer_order where ");
		buffer.append(" IFNULL(dr,0)=0 and pk_fairer=a.pk_fairer");
		buffer.append("	and IFNULL(dr, 0) = 0 ");
		buffer.append("	AND IFNULL(iscombo, 'N') = 'N' ");
		buffer.append("	AND paystatus = '002' ");
		if(!begintime.equals("")){
			buffer.append(" and ordetime >= :creditbegintime ");
			map.put("creditbegintime", begintime);
		}
		if(!endtime.equals("")){
			buffer.append(" and ordetime <= :creditendtime ");
			map.put("creditendtime", endtime);
		}
		if(!paymode.equals("")){
			buffer.append(" and paymode = :paymodetwo ");
			map.put("paymodetwo", paymode);
		}
		buffer.append(" ) accountnum ");//服务次数
		
		buffer.append(" from fairer_info a ");		
		buffer.append("	where IFNULL(a.dr,0)=0  ");
		if(!fairername.equals("")){
			buffer.append(" and a.fairername like :fairername ");
			map.put("fairername", "%"+fairername+"%");
		}
		buffer.append("	and a.pk_shop = :pkShop group by pk_fairer,accountnum having accountnum>0");
		buffer.append(" order by servicemoney desc");
		
		List<Object> list=new ArrayList<Object>();
		
		Long longshop=Long.parseLong(pkShop);
		map.put("pkShop", longshop);
		list=(List<Object>) baseDao.querySqlListByConMap(buffer.toString(),map);
		
		return list;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<FairerInfo> QueryByPkuser(String pkUser) {
		// TODO Auto-generated method stub
		Long longpkuser=Long.parseLong(pkUser);
		ArrayList<FairerInfo> infos=(ArrayList<FairerInfo>)baseDao.pageQuery("from FairerInfo where IFNULL(dr,0)=0 and pkUser = ? ", longpkuser);
		return infos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FairerInfo> QueryValidate(String pk_shop) {
		// TODO Auto-generated method stub
		Long longcorp=Long.parseLong(pk_shop);
		ArrayList<FairerInfo> infos=(ArrayList<FairerInfo>)baseDao.pageQuery("from FairerInfo where IFNULL(dr,0)=0 and pkShop = ? and IFNULL(isvalidate,'N')= ?  ", longcorp,"Y");
		return infos;
	}


}
