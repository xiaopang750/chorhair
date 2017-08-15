package com.rockstar.o2o.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.WxQrcodeDetail;
import com.rockstar.o2o.service.WxQrcodeDetailService;
@Component
public class WxQrcodeDetailServiceImpl extends BaseServiceImpl implements WxQrcodeDetailService {

	@Override
	public WxQrcodeDetail getInfoByRequirement(String sceneid, String actionname) {
		@SuppressWarnings("unchecked")
		List<WxQrcodeDetail> wxqrcodeDetail=(List<WxQrcodeDetail>) baseDao.pageQuery("from WxQrcodeDetail a where IFNULL(a.dr,0)=0 and a.sceneId=? and a.actionName = ?  ",sceneid,actionname);
		if(wxqrcodeDetail.size()>0){
			return wxqrcodeDetail.get(0);
		}
		return null;
	}

}
