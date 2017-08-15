package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.WxQrcodeScancountB;
import com.rockstar.o2o.service.WxQrcodeScancountBService;
import com.rockstar.o2o.util.DateUtil;
@Component
public class WxQrcodeScancountBServiceImpl extends BaseServiceImpl implements WxQrcodeScancountBService {

	@Override
	public WxQrcodeScancountB save(WxQrcodeScancountB info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkQrcodeScancountB(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public int updateScancountB(WxQrcodeScancountB scancount) {
		// TODO Auto-generated method stub
		int result=baseDao.update(scancount);
	    return result;
	}
	@Override
	public void deleteScancountBById(Long id) {
		// TODO Auto-generated method stub
		WxQrcodeScancountB scancountB=(WxQrcodeScancountB) baseDao.getById(WxQrcodeScancountB.class, id);
		scancountB.setDr((short) 1);
		scancountB.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(scancountB);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public WxQrcodeScancountB queryByUserid(String scanUserId) {
		List<WxQrcodeScancountB> wxqrcodeScancountB=(List<WxQrcodeScancountB>) baseDao.pageQuery("from WxQrcodeScancountB a where IFNULL(a.dr,0)=0 and a.scanUserid = ?  ",scanUserId);
		if(wxqrcodeScancountB.size()>0){
			return wxqrcodeScancountB.get(0);
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getScancountdetail(String pkScancountid,Integer curpage, Integer pagesize) {
		Long pkScancountId=Long.parseLong(pkScancountid);
		StringBuffer sqlbuffer=new StringBuffer();
		sqlbuffer.append(" SELECT a.pk_scancountid,b.nickname as nickname,b.sex as sex,b.head_image_url,b.subscribe_flag,b.subscribe_time");
		sqlbuffer.append(" from  wx_qrcode_scancount_b a join wx_user b on a.scan_userid=b.wx_id");
		sqlbuffer.append(" WHERE IFNULL(a.dr,0)=0 and IFNULL(b.dr,0)=0 and a.pk_scancountid=?");
		List<Object> objlist=(ArrayList<Object>)baseDao.querybysqlMap(sqlbuffer.toString(),curpage,pagesize,pkScancountId);
	    return objlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getCountByPkScancountid(long pkScancountid) {
		List<WxQrcodeScancountB> wxqrcodeScancountB=(List<WxQrcodeScancountB>) baseDao.pageQuery("from WxQrcodeScancountB a where IFNULL(a.dr,0)=0  and a.pkScancountid = ?  ",pkScancountid);
		if(wxqrcodeScancountB.size()>0){
			return wxqrcodeScancountB.size();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getAttentionCount(long pkScancountid) {
		// TODO Auto-generated method stub
		List<WxQrcodeScancountB> wxqrcodeScancountB=(List<WxQrcodeScancountB>) baseDao.pageQuery("from WxQrcodeScancountB a where IFNULL(a.dr,0)=0  and a.pkScancountid = ?  ",pkScancountid);
		if(wxqrcodeScancountB.size()>0){
			return wxqrcodeScancountB.size();
		}
		return 0;
	}

}
