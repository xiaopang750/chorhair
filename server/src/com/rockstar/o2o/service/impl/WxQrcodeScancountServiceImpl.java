package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.WxQrcodeScancount;
import com.rockstar.o2o.service.WxQrcodeScancountService;
import com.rockstar.o2o.util.DateUtil;
@Component
public class WxQrcodeScancountServiceImpl extends BaseServiceImpl implements WxQrcodeScancountService {
	@Override
	public WxQrcodeScancount save(WxQrcodeScancount info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkScancountid(Long.parseLong(pk.toString()));
		return info;
	}
	@Override
	public int updateScancount(WxQrcodeScancount scancount) {
		// TODO Auto-generated method stub
		int result=baseDao.update(scancount);
	    return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public WxQrcodeScancount getScancountInfo(String accountid,String pkkey,String codeobject) {
		Long pkKey=Long.parseLong(pkkey);
		List<WxQrcodeScancount> wxqrcodeScancount=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.accountId = ? and a.pkKey=? and a.operateobject = ?  ",accountid,pkKey,codeobject);
		if(wxqrcodeScancount.size()>0){
			return wxqrcodeScancount.get(0);
		}
		return null;
	}
	@Override
	public WxQrcodeScancount getCountInfo(String accountid,String sceneid,String actionname) {
		@SuppressWarnings("unchecked")
		List<WxQrcodeScancount> wxqrcodeScancount=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.accountId = ? and a.sceneId=? and a.actionName = ?  ",accountid,sceneid,actionname);
		if(wxqrcodeScancount.size()>0){
			return wxqrcodeScancount.get(0);
		}
		return null;
	}
	@Override
	public void deleteScancountById(Long id) {
		WxQrcodeScancount scancount=(WxQrcodeScancount) baseDao.getById(WxQrcodeScancount.class, id);
		scancount.setDr((short) 1);
		scancount.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(scancount);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<WxQrcodeScancount> querycancountList(String pkkey,String codeobject,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub
		Long pkKey=Long.parseLong(pkkey);
		List<WxQrcodeScancount> scancountlists = new ArrayList<WxQrcodeScancount>();
		if(curpage!=null){
			scancountlists=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.pkKey=? and a.operateobject=?",pkKey,codeobject,curpage,pagesize);
		}else{
			scancountlists=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.pkKey=? and a.operateobject=? ",pkKey,codeobject);	
		}
		return scancountlists;
	}
	@SuppressWarnings("unchecked")
	@Override
	public WxQrcodeScancount getCountInfoby(String accountid,String pkKey, String codeobject,
			String userDefined) {
		// TODO Auto-generated method stub
		List<WxQrcodeScancount> wxqrcodeScancount=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.accountId = ? and a.pkKey=? and a.operateobject = ? and a.userDefined = ?  ",accountid,Long.parseLong(pkKey),codeobject,userDefined);
		if(wxqrcodeScancount.size()>0){
			return wxqrcodeScancount.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxQrcodeScancount> querycanbyShop(String pkShop,
			Integer curpage, Integer pagesize) {
		// TODO Auto-generated method stub
		Long pkKey=Long.parseLong(pkShop);
		List<WxQrcodeScancount> scancountlists = new ArrayList<WxQrcodeScancount>();
		if(curpage!=null){
			scancountlists=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.pkKey=? ",curpage,pagesize,pkKey);
		}else{
			scancountlists=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0 and a.pkKey=?  ",pkKey);	
		}
		return scancountlists;
	}

}
