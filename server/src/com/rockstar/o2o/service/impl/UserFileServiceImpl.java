package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.service.UserFileService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class UserFileServiceImpl extends BaseServiceImpl implements UserFileService{

	@Override
	public UserFile save(UserFile file) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(file);
		baseDao.add(file);
		file.setPkFile(Long.parseLong(pk.toString()));
		return file;
	}

	@Override
	public UserFile getFileById(Long id) {
		// TODO Auto-generated method stub
		return (UserFile) baseDao.getById(UserFile.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFile> getAllFile() {
		// TODO Auto-generated method stub
		return (List<UserFile>) baseDao.getAll(UserFile.class);
	}

	@Override
	public void deleteFileById(Long id) {
		// TODO Auto-generated method stub
		UserFile file=(UserFile) baseDao.getById(UserFile.class, id);
		file.setDr((short) 1);
		file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(file);
	}

	@Override
	public void batchsave(ArrayList<UserFile> file) {
		// TODO Auto-generated method stub
		baseDao.batchsave(file);
	}

}
