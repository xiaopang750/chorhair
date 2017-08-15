package com.rockstar.o2o.service.impl;

import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.WxAutoreplyMessage;
import com.rockstar.o2o.service.WxAutoreplyMessageService;
import com.rockstar.o2o.util.DateUtil;
@Component
public class WxAutoreplyMessageServiceImpl extends BaseServiceImpl implements
		WxAutoreplyMessageService {

	@Override
	public WxAutoreplyMessage saveMessageInfo(WxAutoreplyMessage info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkReplymsgId(Long.parseLong(pk.toString()));
		return info;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxAutoreplyMessage> getMessageInfo(Long pkAutoreplyId) {
		// TODO Auto-generated method stub
		List<WxAutoreplyMessage> messageInfo=(List<WxAutoreplyMessage>) baseDao.pageQuery("from WxAutoreplyMessage where IFNULL(dr,0)=0 and pkAutoreplyId=?",pkAutoreplyId);
		if(messageInfo.size()>0){
			return messageInfo;
		}
		return null;
	}

	@Override
	public WxAutoreplyMessage getInfoById(String pkKeywordId) {
		// TODO Auto-generated method stub
		long id=Long.parseLong(pkKeywordId);
		return (WxAutoreplyMessage) baseDao.getById(WxAutoreplyMessage.class,id);
	}

	@Override
	public int update(WxAutoreplyMessage messageInfo) {
		// TODO Auto-generated method stub
		int result=baseDao.update(messageInfo);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WxAutoreplyMessage getOneMessageInfo(Long pkAutoreplyId) {
		// TODO Auto-generated method stub
		List<WxAutoreplyMessage> messageInfo=(List<WxAutoreplyMessage>) baseDao.pageQuery("from WxAutoreplyMessage  where IFNULL(dr,0)=0 and pkAutoreplyId=?",pkAutoreplyId);
		if(messageInfo.size()>0){
			 Random rand = new Random();
			return messageInfo.get(rand.nextInt(messageInfo.size()));
		}
		return null;
	}

	@Override
	public void deleteById(Long pkReplymsgId) {
		// TODO Auto-generated method stub
		WxAutoreplyMessage messageInfo=(WxAutoreplyMessage) baseDao.getById(WxAutoreplyMessage.class, pkReplymsgId);
		messageInfo.setDr((short) 1);
		messageInfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(messageInfo);
	}

}
