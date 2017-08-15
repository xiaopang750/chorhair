package com.rockstar.o2o.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.ClientInfo;
import com.rockstar.o2o.service.ClientInfoService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ClientInfoServiceImpl extends BaseServiceImpl implements ClientInfoService  {

	@Override
	public ClientInfo save(ClientInfo info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkClient(Long.parseLong(pk.toString()));
		return info;
	}

	@Override
	public ClientInfo getClientById(Long id) {
		// TODO Auto-generated method stub
		return (ClientInfo) baseDao.getById(ClientInfo.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientInfo> getAllClienInfo() {
		// TODO Auto-generated method stub
		return (List<ClientInfo>) baseDao.getAll(ClientInfo.class);
	}

	@Override
	public void deleteInfoById(Long id) {
		// TODO Auto-generated method stub
		ClientInfo message=(ClientInfo) baseDao.getById(ClientInfo.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public int updateInfo(ClientInfo info) {
		// TODO Auto-generated method stub
		int result=baseDao.update(info);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientInfo> FindByPkUser(Long pkUser) {
		// TODO Auto-generated method stub
		List<ClientInfo> infolist=(List<ClientInfo>) baseDao.pageQuery("from ClientInfo where IFNULL(dr,0)=0 and pkUser = ? ", pkUser);
		return infolist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientInfo> FindByuuid(String uuid) {
		// TODO Auto-generated method stub
		List<ClientInfo> infolist=(List<ClientInfo>) baseDao.pageQuery("from ClientInfo where IFNULL(dr,0)=0 and deviceuuid = ? ", uuid);
		return infolist;
	}

}
