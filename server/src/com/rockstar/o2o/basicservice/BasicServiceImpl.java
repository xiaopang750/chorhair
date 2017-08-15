package com.rockstar.o2o.basicservice;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rockstar.o2o.dao.BaseDao;

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
		return	(List<Object>) basedao.pageQuery("from " + c.getName()+" w where IFNULL(dr,0)=0 and "+sql, objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> pageQuery(Class<?> c, String sql,Integer pageSize,Integer pageNum,Object... objects) {
		// TODO Auto-generated method stub
		return (List<Object>) basedao.pageQuery("from " + c.getName()+" where IFNULL(dr,0)=0 and "+sql,pageNum,pageSize,objects);
	}

	@Override
	public Object querybyid(Class<?> c, Serializable id) {
		// TODO Auto-generated method stub
		return basedao.getById(c, id);
	}

	@Override
	public void update(Object updateobject) {
		// TODO Auto-generated method stub
		basedao.update(updateobject);
	}

}
