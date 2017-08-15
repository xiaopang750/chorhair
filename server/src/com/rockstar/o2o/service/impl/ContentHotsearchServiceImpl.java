package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ContentHotsearch;
import com.rockstar.o2o.service.ContentHotsearchService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ContentHotsearchServiceImpl extends BaseServiceImpl implements ContentHotsearchService{

	@Override
	public ContentHotsearch save(ContentHotsearch search) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(search);
		baseDao.add(search);
		search.setPkHotsearch(Long.parseLong(pk.toString()));
		return search;
	}

	@Override
	public ContentHotsearch getContentById(Long id) {
		// TODO Auto-generated method stub
		return (ContentHotsearch) baseDao.getById(ContentHotsearch.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentHotsearch> getAllContent() {
		// TODO Auto-generated method stub
		return (List<ContentHotsearch>) baseDao.getAll(ContentHotsearch.class);
	}

	@Override
	public void deletecontentById(Long id) {
		// TODO Auto-generated method stub
		ContentHotsearch message=(ContentHotsearch) baseDao.getById(ContentHotsearch.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public int updatecontent(ContentHotsearch content) {
		// TODO Auto-generated method stub
		int result=baseDao.update(content);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getbytype(String pkType) {
		// TODO Auto-generated method stub
		Long longtype=Long.parseLong(pkType);
		ArrayList<Object> obj=(ArrayList<Object>) baseDao.querybysqlMap("select hotword from content_hotsearch where IFNULL(dr,0)=0 and pk_hottype = ? ", null,null,longtype);
		return obj;
	}

}
