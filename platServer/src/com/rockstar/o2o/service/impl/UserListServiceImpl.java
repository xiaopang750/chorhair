package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.UserGroup;
import com.rockstar.o2o.pojo.UserList;
import com.rockstar.o2o.service.UserListService;

@Component
public class UserListServiceImpl  extends BaseServiceImpl implements UserListService{
	@Override
	public UserList save(UserList user) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(user);
		baseDao.add(user);
		user.setPkUser(Long.parseLong(pk.toString()));
		return user;
	}

	@Override
	public UserList getUserById(Long id) {
		// TODO Auto-generated method stub
		return (UserList) baseDao.getById(UserList.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserList> getAllUser() {
		// TODO Auto-generated method stub
		return (List<UserList>) baseDao.getAll(UserList.class);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		UserGroup group=(UserGroup) baseDao.getById(UserGroup.class, id);
		group.setDr((short) 1);
		baseDao.update(group);
	}

	@Override
	public int updateUser(UserList user) {
		// TODO Auto-generated method stub
		int result=baseDao.update(user);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserList login(String cellphone, String password,String usergroup) {
		// TODO Auto-generated method stub		
		List<UserList> userlist=(List<UserList>) baseDao.pageQuery("from UserList where IFNULL(dr,0)=0 and cellphone = ? and loginpassword = ? and usergroup = ?", cellphone,password,usergroup);
		if(userlist.size()>0){
			return userlist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserList findbycellandgroup(String cellphone,String usergroup) {
		// TODO Auto-generated method stub
		List<UserList> userlist=(List<UserList>) baseDao.pageQuery("from UserList where IFNULL(dr,0)=0 and cellphone = ? and usergroup = ? ", cellphone,usergroup);
		if(userlist.size()>0){
			return userlist.get(0);
		}
		return null;
	}
	
}
