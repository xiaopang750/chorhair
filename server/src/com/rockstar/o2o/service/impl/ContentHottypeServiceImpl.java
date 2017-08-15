package com.rockstar.o2o.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ContentHottype;
import com.rockstar.o2o.service.ContentHottypeService;
import com.rockstar.o2o.util.DateUtil;


@Component
public class ContentHottypeServiceImpl extends BaseServiceImpl implements ContentHottypeService{

	@Override
	public ContentHottype save(ContentHottype type) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(type);
		baseDao.add(type);
		type.setPkHottype(Long.parseLong(pk.toString()));
		return type;
	}

	@Override
	public ContentHottype getContentById(Long id) {
		// TODO Auto-generated method stub
		return (ContentHottype) baseDao.getById(ContentHottype.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentHottype> getAllContent() {
		// TODO Auto-generated method stub
		return (List<ContentHottype>) baseDao.getAll(ContentHottype.class);
	}

	@Override
	public void deletecontentById(Long id) {
		// TODO Auto-generated method stub
		ContentHottype message=(ContentHottype) baseDao.getById(ContentHottype.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public int updatecontent(ContentHottype content) {
		// TODO Auto-generated method stub
		int result=baseDao.update(content);
	    return result;
	}

}
