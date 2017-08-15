package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ContentPhoto;
import com.rockstar.o2o.service.ContentPhotoService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ContentPhotoServiceImpl extends BaseServiceImpl implements ContentPhotoService{

	@Override
	public ContentPhoto save(ContentPhoto photo) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(photo);
		baseDao.add(photo);
		photo.setPkPhoto(Long.parseLong(pk.toString()));
		return photo;
	}

	@Override
	public ContentPhoto getPhotoById(Long id) {
		// TODO Auto-generated method stub
		return (ContentPhoto) baseDao.getById(ContentPhoto.class,id);
	}

	@Override
	public void deletephotoById(Long id) {
		// TODO Auto-generated method stub
		ContentPhoto photo=(ContentPhoto) baseDao.getById(ContentPhoto.class, id);
		photo.setDr((short) 1);
		photo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(photo);
	}

	@Override
	public int updatephoto(ContentPhoto photo) {
		// TODO Auto-generated method stub
		int result=baseDao.update(photo);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentPhoto> querybypkContent(String pkContent) {
		// TODO Auto-generated method stub
		Long longpkContent=Long.parseLong(pkContent);
		// TODO Auto-generated method stub
		List<ContentPhoto> photolist=(List<ContentPhoto>) baseDao.pageQuery("from ContentPhoto where IFNULL(dr,0)=0 and pkContent = ? ", longpkContent);
		return photolist;
	}

	@Override
	public void savelist(ArrayList<ContentPhoto> photos) {
		// TODO Auto-generated method stub
		baseDao.batchsave(photos);
	}

}
