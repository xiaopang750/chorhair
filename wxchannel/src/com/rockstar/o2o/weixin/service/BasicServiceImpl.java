package com.rockstar.o2o.weixin.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.rockstar.o2o.weixin.dao.BaseDao;


@Service
public class BasicServiceImpl implements BasicService{

	@Resource
	private BaseDao basedao;
	
	@Override
	public void batchoperate(Object addobject, Object updateobject) {
		// TODO Auto-generated method stub
		basedao.batchoperator(addobject, updateobject);
	}

	@Override
	public Object save(Object addobject) {
		// TODO Auto-generated method stub
		return basedao.add(addobject);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> query(Class<?> c, String sql, Object... objects) {
		// TODO Auto-generated method stub
		return	(List<Object>) basedao.pageQuery("from " + c.getName()+" where IFNULL(dr,0)=0 and "+sql,null,null,objects);
	}

	@Override
	public void update(Object updateobject) {
		// TODO Auto-generated method stub
		 basedao.update(updateobject);
	}
}
