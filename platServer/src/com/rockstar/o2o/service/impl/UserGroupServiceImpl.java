package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.UserGroup;
import com.rockstar.o2o.service.UserGroupService;

@Component
public class UserGroupServiceImpl  extends BaseServiceImpl  implements UserGroupService{	
	@Override
	public UserGroup save(UserGroup group) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(group);
		baseDao.add(group);
		group.setPkGroup(Long.parseLong(pk.toString()));
		return group;
	}

	@Override
	public UserGroup getGroupById(Long id) {
		// TODO Auto-generated method stub
		return (UserGroup) baseDao.getById(UserGroup.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> getAllgroup() {
		// TODO Auto-generated method stub
		return (List<UserGroup>) baseDao.getAll(UserGroup.class);
	}

	@Override
	public void deleteGroupById(Long id) {
		// TODO Auto-generated method stub
		UserGroup group=(UserGroup) baseDao.getById(UserGroup.class, id);
		group.setDr((short) 1);
		baseDao.update(group);
	}

	@Override
	public void updateGroup(UserGroup group) {
		// TODO Auto-generated method stub
		baseDao.update(group);
	}

	
}
