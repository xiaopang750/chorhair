package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.PlatUser;
import com.rockstar.o2o.service.PlatUserService;
@Component
public class PlatUserServiceImpl extends BaseServiceImpl implements
		PlatUserService {

	@SuppressWarnings("unchecked")
	@Override
	public PlatUser login(String usercode, String password) {
		List<PlatUser> platlist=(List<PlatUser>) baseDao.pageQuery("from PlatUser where IFNULL(dr,0)=0 and platusercode = ? and loginpassword = ? ", usercode , password);
		if(platlist.size()>0){
			return platlist.get(0);
		}
		return null;
	}

	@Override
	public PlatUser getUserById(long id) {
		return (PlatUser) baseDao.getById(PlatUser.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybysql(String sql) {
		return (List<Object>) baseDao.querybysql(sql);
	}

	@Override
	public PlatUser save(PlatUser user) {
		Object pk=baseDao.add(user);
		baseDao.add(user);
		user.setPkPlatuser(Long.parseLong(pk.toString()));
		return user;
	}

	@Override
	public int updateUser(PlatUser user) {
		int result=baseDao.update(user);
	    return result;
	}

}
