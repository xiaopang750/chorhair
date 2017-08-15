package com.rockstar.o2o.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.WxAutoreply;
import com.rockstar.o2o.service.WxAutoreplyService;
import com.rockstar.o2o.util.DateUtil;
@Component
public class WxAutoreplyServiceImpl extends BaseServiceImpl implements
		WxAutoreplyService {

	@Override
	public WxAutoreply save(WxAutoreply info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkAutoreplyId(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public int update(WxAutoreply info) {
		// TODO Auto-generated method stub
		int result=baseDao.update(info);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxAutoreply> getInfoByReplytype(String replyType) {
		// TODO Auto-generated method stub
		List<WxAutoreply> autoreply=(List<WxAutoreply>) baseDao.pageQuery("from WxAutoreply a where IFNULL(a.dr,0)=0 and a.replyType=?",replyType);
		if(autoreply.size()>0){
			return autoreply;
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		WxAutoreply autoreply=(WxAutoreply) baseDao.getById(WxAutoreply.class, id);
		autoreply.setDr((short) 1);
		autoreply.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(autoreply);
	}
	@SuppressWarnings("unchecked")
	@Override
	public WxAutoreply getInfoById(long pkAutoreplyId) {
		// TODO Auto-generated method stub
		List<WxAutoreply> autoreply=(List<WxAutoreply>) baseDao.pageQuery("from WxAutoreply a where IFNULL(a.dr,0)=0 and a.pkAutoreplyId=?",pkAutoreplyId);
		if(autoreply.size()>0){
			return autoreply.get(0);
		}
		return null;
	}
}
