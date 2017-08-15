package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.pojo.ClientInfo;
import com.rockstar.o2o.pojo.ContentManage;
import com.rockstar.o2o.service.ContentManageService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class ContentManageImpl extends BaseServiceImpl implements ContentManageService{

	@Override
	public ContentManage save(ContentManage manage) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(manage);
		baseDao.add(manage);
		manage.setPkContent(Long.parseLong(pk.toString()));
		return manage;
	}

	@Override
	public ContentManage getContentById(Long id) {
		// TODO Auto-generated method stub
		return (ContentManage) baseDao.getById(ContentManage.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentManage> getAllContent(String pkShop,Integer curpage,Integer pagesize) {
		// TODO Auto-generated method stub	

		StringBuffer buffer=new StringBuffer();
		List<ContentManage> contentlists = new ArrayList<ContentManage>();
		buffer.append("from ContentManage where IFNULL(dr,0)=0");
		if(pkShop!=null&&!pkShop.equals("")){
			Long longcorp=Long.parseLong(pkShop);
			buffer.append(" and pkShop = ? order by pkContent desc");
			contentlists=(List<ContentManage>) baseDao.pageQuery(buffer.toString(),curpage,pagesize,longcorp);	
		}else{
			buffer.append(" order by pkContent desc");
			contentlists=(List<ContentManage>) baseDao.pageQuery(buffer.toString(),curpage,pagesize);		
		}
				
        return contentlists;
        
        
	}

	@Override
	public void deletecontentById(Long id) {
		// TODO Auto-generated method stub
		ClientInfo message=(ClientInfo) baseDao.getById(ClientInfo.class, id);
		message.setDr((short) 1);
		message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(message);
	}

	@Override
	public int updatecontent(ContentManage content) {
		// TODO Auto-generated method stub
		int result=baseDao.update(content);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentManage> querybytype(String pkType, String contenttype) {
		// TODO Auto-generated method stub
		Long longpktype=Long.parseLong(pkType);		
		ArrayList<ContentManage> arr=(ArrayList<ContentManage>) baseDao.pageQuery("from ContentManage where IFNULL(dr,0)=0 and pkType = ? and contenttype = ? ",longpktype,contenttype);
		return arr;
	}


}
