package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ContentTag;
import com.rockstar.o2o.service.ContentTagService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ContentTagServiceImpl extends BaseServiceImpl implements ContentTagService{

	@Override
	public ContentTag save(ContentTag tag) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(tag);
		baseDao.add(tag);
		tag.setPkTag(Long.parseLong(pk.toString()));
		return tag;
	}

	@Override
	public ContentTag getTagById(Long id) {
		// TODO Auto-generated method stub
		return (ContentTag) baseDao.getById(ContentTag.class,id);
	}

	@Override
	public void deletetagById(Long id) {
		// TODO Auto-generated method stub
		ContentTag tag=(ContentTag) baseDao.getById(ContentTag.class, id);
		tag.setDr((short) 1);
		tag.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(tag);
	}

	@Override
	public int updatetag(ContentTag tag) {
		// TODO Auto-generated method stub
		int result=baseDao.update(tag);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentTag> querybypkContent(String pkContent) {
		// TODO Auto-generated method stub
		Long longpkContent=Long.parseLong(pkContent);
		// TODO Auto-generated method stub
		List<ContentTag> taglist=(List<ContentTag>) baseDao.pageQuery("from ContentTag where IFNULL(dr,0)=0 and pkContent = ? ", longpkContent);
		return taglist;
	}

	@Override
	public void savelist(ArrayList<ContentTag> tags) {
		// TODO Auto-generated method stub
		baseDao.batchsave(tags);
	}

}
