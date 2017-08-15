package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.WxQrcodeScancount;
import com.rockstar.o2o.service.PlatWxQrcodeScancountService;
@Component
public class PlatWxQrcodeScancountServiceImpl extends BaseServiceImpl implements
		PlatWxQrcodeScancountService {

	@SuppressWarnings("unchecked")
	@Override
	public List<WxQrcodeScancount> queryPlatScancountList(Integer curpage,
			Integer pagesize) {
		// TODO Auto-generated method stub
		List<WxQrcodeScancount> scancountlists = new ArrayList<WxQrcodeScancount>();
		if(curpage!=null){
			scancountlists=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0",curpage,pagesize);
		}else{
			scancountlists=(List<WxQrcodeScancount>) baseDao.pageQuery("from WxQrcodeScancount a where IFNULL(a.dr,0)=0");	
		}
		return scancountlists;
	}

}
