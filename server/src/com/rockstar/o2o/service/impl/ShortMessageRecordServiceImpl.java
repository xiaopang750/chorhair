package com.rockstar.o2o.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.dao.BaseDao;
import com.rockstar.o2o.pojo.ShortmessageRecord;
import com.rockstar.o2o.service.ShortMessageRecordService;

@Component
public class ShortMessageRecordServiceImpl implements ShortMessageRecordService{
	private BaseDao baseDao;
	
	@Override
	public ShortmessageRecord save(ShortmessageRecord message) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(message);
		baseDao.add(message);
		message.setPkMessage(Long.parseLong(pk.toString()));
		return message;
	}

	@Override
	public ShortmessageRecord getMessageById(Long id) {
		// TODO Auto-generated method stub
		return (ShortmessageRecord) baseDao.getById(ShortmessageRecord.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShortmessageRecord> getAllmessage() {
		// TODO Auto-generated method stub
		return (List<ShortmessageRecord>) baseDao.getAll(ShortmessageRecord.class);
	}

	@Override
	public void deleteMessageById(Long id) {
		// TODO Auto-generated method stub
		ShortmessageRecord message=(ShortmessageRecord) baseDao.getById(ShortmessageRecord.class, id);
		message.setDr((short) 1);
		baseDao.update(message);
	}

	@Override
	public void updateGroup(ShortmessageRecord message) {
		// TODO Auto-generated method stub
		baseDao.update(message);
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Resource
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShortmessageRecord QueryBysql(String cellphone, String usergroup, String messagetype,String code) {
		// TODO Auto-generated method stub
		List<ShortmessageRecord> message=(List<ShortmessageRecord>) baseDao.pageQuery("from ShortmessageRecord where IFNULL(dr,0)=0 and receiver = ? and messagegroup = ? and messagetype = ? and code = ? ", cellphone,usergroup,messagetype,code);
		if(message.size()>0){
			return message.get(0);
		}
		return null;
	}

	@Override
	public int UpdateOldMessage(String cellphone, String usergroup,
			String messagetype) {
		// TODO Auto-generated method stub
		try {
			baseDao.update(" update ShortmessageRecord set dr=1 where IFNULL(dr,0)=0 and receiver = ? and messagegroup = ? and messagetype = ?", cellphone,usergroup,messagetype);
		   return 0;
		 } catch (Exception e) {
			 e.printStackTrace();
			return -1;// TODO: handle exception
		}
		
		
	}


	
}
