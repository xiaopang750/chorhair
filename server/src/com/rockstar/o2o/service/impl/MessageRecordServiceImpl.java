package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.MessageRecord;
import com.rockstar.o2o.service.MessageRecordService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class MessageRecordServiceImpl extends BaseServiceImpl implements MessageRecordService{	
	@Override
	public MessageRecord save(MessageRecord message) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(message);
		baseDao.add(message);
		message.setPkMessage(Long.parseLong(pk.toString()));
		return message;
	}

	@Override
	public MessageRecord getMessageById(Long id) {
		// TODO Auto-generated method stub
		return (MessageRecord) baseDao.getById(MessageRecord.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageRecord> getAllmessage() {
		// TODO Auto-generated method stub
		return (List<MessageRecord>) baseDao.getAll(MessageRecord.class);
	}

	@Override
	public void deleteMessageById(Long id) {
		// TODO Auto-generated method stub
		MessageRecord message=(MessageRecord) baseDao.getById(MessageRecord.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public void updateGroup(MessageRecord message) {
		// TODO Auto-generated method stub
		baseDao.update(message);
	}


	@SuppressWarnings("unchecked")
	@Override
	public MessageRecord QueryBysql(String cellphone, String usergroup, String messagetype,String code) {
		// TODO Auto-generated method stub
		List<MessageRecord> message=(List<MessageRecord>) baseDao.pageQuery("from MessageRecord where IFNULL(dr,0)=0 and receiver = ? and messagegroup = ? and messagetype = ? and code = ? ", cellphone,usergroup,messagetype,code);
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
			baseDao.update(" update MessageRecord set dr=1 where IFNULL(dr,0)=0 and receiver = ? and messagegroup = ? and messagetype = ?", cellphone,usergroup,messagetype);
		   return 0;
		 } catch (Exception e) {
			 e.printStackTrace();
			return -1;// TODO: handle exception
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageRecord> querybysenderandreceiver(String sender,
			String receiver, String usergroup) {
		// TODO Auto-generated method stub
		List<MessageRecord> messagelist=(List<MessageRecord>) baseDao.pageQuery("from MessageRecord where IFNULL(dr,0)=0 and ((sender = ? and receiver = ? ) or (sender = ? and receiver = ? )) order by sendtime", sender,receiver,receiver,sender );
		return messagelist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybysender(String sender,
			String receiver, String usergroup) {
		// TODO Auto-generated method stub
		List<Object> list=(List<Object>) baseDao.querybysqlMap("select distinct sender,receiver from message_record where IFNULL(dr,0)=0 and (sender = ? or receiver = ?)",null,null, sender,sender);
		return list;
	}

	@Override
	public void savelist(ArrayList<MessageRecord> messages) {
		// TODO Auto-generated method stub
		baseDao.batchsave(messages);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybyuser(String pkUser, String messagetype,String status) {
		// TODO Auto-generated method stub
		ArrayList<Object> obj=(ArrayList<Object>) baseDao.pageQuery("from MessageRecord where IFNULL(dr,0)=0 and receiver = ? and messagetype =  ? and messagestatus = ? ",pkUser,messagetype,status);		
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryBytime(String pkUser, String messagetype,
			String begintime, String endtime) {
		// TODO Auto-generated method stub
		StringBuffer buffer=new StringBuffer();
		buffer.append("from MessageRecord where IFNULL(dr,0)=0 and receiver = ? and messagetype =  ? ");
		if(!begintime.equals("")){
			buffer.append(" and substr(sendtime,1,10) >= ?");	
		}
		
		if(!endtime.equals("")){
			
			buffer.append(" and substr(sendtime,1,10) <= ?");		
		}
		
		ArrayList<Object> obj=new ArrayList<Object>();;	
		if(!begintime.equals("")&&!endtime.equals("")){
			 obj=(ArrayList<Object>) baseDao.pageQuery(buffer.toString(),pkUser,messagetype,begintime,endtime);	
		}else if(!begintime.equals("")&&endtime.equals("")){
			obj=(ArrayList<Object>) baseDao.pageQuery(buffer.toString(),pkUser,messagetype,begintime);	
		}else if(begintime.equals("")&&!endtime.equals("")){
			 obj=(ArrayList<Object>) baseDao.pageQuery(buffer.toString(),pkUser,messagetype,endtime);	
		}else{
			 obj=(ArrayList<Object>) baseDao.pageQuery(buffer.toString(),pkUser,messagetype);	
		}
		
		return obj;
	}



	
}
