package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.service.ShopUserService;

@Component
public class ShopUserServiceImpl extends BaseServiceImpl implements ShopUserService{

	@Override
	public ShopUser save(ShopUser user) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(user);
		baseDao.add(user);
		user.setPkShopuser(Long.parseLong(pk.toString()));
		return user;
	}

	@Override
	public ShopUser getUserById(Long id) {
		// TODO Auto-generated method stub
		return (ShopUser) baseDao.getById(ShopUser.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopUser> getAllUser() {
		// TODO Auto-generated method stub
		return (List<ShopUser>) baseDao.getAll(ShopUser.class);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		ShopUser user=(ShopUser) baseDao.getById(ShopUser.class, id);
		user.setDr((short) 1);
		baseDao.update(user);
	}

	@Override
	public int updateUser(ShopUser user) {
		// TODO Auto-generated method stub
		int result=baseDao.update(user);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShopUser login(String usercode, String password, String usergroup) {
		// TODO Auto-generated method stub
		List<ShopUser> userlist=(List<ShopUser>) baseDao.pageQuery("from ShopUser where IFNULL(dr,0)=0 and shopusercode = ? and loginpassword = ? ", usercode , password);
		if(userlist.size()>0){
			return userlist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybysql(String sql) {
		// TODO Auto-generated method stub
		return (List<Object>) baseDao.querybysql(sql);
	}

}
