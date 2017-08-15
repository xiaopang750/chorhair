package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.MessageList;
import com.rockstar.o2o.service.MessageListService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class MessageListServiceImpl extends BaseServiceImpl implements MessageListService{

	
	@Override
	public MessageList save(MessageList message) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(message);
		baseDao.add(message);
		message.setPkMessagelist(Long.parseLong(pk.toString()));
		return message;
	}

	@Override
	public MessageList getMessageById(Long id) {
		// TODO Auto-generated method stub
		return (MessageList) baseDao.getById(MessageList.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageList> getAllmessage() {
		// TODO Auto-generated method stub
		return (List<MessageList>) baseDao.getAll(MessageList.class);
	}

	@Override
	public void deleteMessageById(Long id) {
		// TODO Auto-generated method stub
		MessageList message=(MessageList) baseDao.getById(MessageList.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public void updateMessage(MessageList message) {
		// TODO Auto-generated method stub
		baseDao.update(message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageList> findlistbyUser(String PkUser) {
		// TODO Auto-generated method stub
		Long longPkUser=Long.parseLong(PkUser);
		List<MessageList> messagelist=(List<MessageList>) baseDao.pageQuery("from MessageList where IFNULL(dr,0)=0 and (sender = ? or receiver = ?) ",longPkUser,longPkUser);
        return messagelist;
	}

	@Override
	public void savelist(ArrayList<MessageList> messages) {
		// TODO Auto-generated method stub
		baseDao.batchsave(messages);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageList> queryallplat(String messagetype, String pkShop) {
		// TODO Auto-generated method stub
		Long longpkShop=Long.parseLong(pkShop);
		List<MessageList> messagelist=(List<MessageList>) baseDao.pageQuery("from MessageList where IFNULL(dr,0)=0 and messagetype = ? and pkShop = ? ",messagetype,longpkShop);
        return messagelist;
	}

}
