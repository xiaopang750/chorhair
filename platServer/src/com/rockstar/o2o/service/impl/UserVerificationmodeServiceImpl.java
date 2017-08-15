package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.UserVerificationmode;
import com.rockstar.o2o.service.UserVerificationmodeService;


@Component
public class UserVerificationmodeServiceImpl  extends BaseServiceImpl implements UserVerificationmodeService {

	@Override
	public UserVerificationmode save(UserVerificationmode mode) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(mode);
		baseDao.add(mode);
		mode.setPkVerification(Long.parseLong(pk.toString()));
		return mode;
	}

	@Override
	public UserVerificationmode getModeById(Long id) {
		// TODO Auto-generated method stub
		return (UserVerificationmode) baseDao.getById(UserVerificationmode.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVerificationmode> getAllmode() {
		// TODO Auto-generated method stub
		return (List<UserVerificationmode>) baseDao.getAll(UserVerificationmode.class);
	}

	@Override
	public void deleteModeById(Long id) {
		// TODO Auto-generated method stub
		UserVerificationmode mode=(UserVerificationmode) baseDao.getById(UserVerificationmode.class, id);
		mode.setDr((short) 1);
		baseDao.update(mode);
	}

	@Override
	public int updateGroup(UserVerificationmode mode) {
		// TODO Auto-generated method stub
		int result=baseDao.update(mode);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVerificationmode> QueryByPkuser(Long pkUser) {
		// TODO Auto-generated method stub
		List<UserVerificationmode> modelist=(List<UserVerificationmode>) baseDao.pageQuery("from UserVerificationmode where IFNULL(dr,0)=0 and pkUser = ? ",pkUser);
		return modelist;

	}

	@SuppressWarnings("unchecked")
	@Override
	public UserVerificationmode QueryByTwo(String openid, String type) {
		// TODO Auto-generated method stub
		List<UserVerificationmode> modelist=(List<UserVerificationmode>) baseDao.pageQuery("from UserVerificationmode where IFNULL(dr,0)=0 and openid = ? and verificationType = ?  ",openid,type);
		if(modelist.size()>0){
			return modelist.get(0);
		}
		return null;
	}

	
	
}
