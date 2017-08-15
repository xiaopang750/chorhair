package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public List<ContentManage> getAllContent(String pkShop,String begintime, String endtime,Integer curpage,Integer pagesize) {
		List<ContentManage> contentlists = new ArrayList<ContentManage>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" from ContentManage where IFNULL(dr,0)=0 " );
		if(begintime!=null&&!begintime.equals("")){
			buffer.append(" and ts >= :begintime   " );
			map.put("begintime", begintime);
		}
		if(endtime!=null&&!endtime.equals("")){
			buffer.append(" and ts <= :endtime   " );
			map.put("endtime", endtime);
		}
		buffer.append(" order by pkContent desc " );
		if(curpage!=null){
			contentlists=(List<ContentManage>) baseDao.queryHqlListByConMap(buffer.toString(),curpage,pagesize,map);
		}else{
			contentlists=(List<ContentManage>) baseDao.queryHqlListByConMap(buffer.toString(),map);
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
