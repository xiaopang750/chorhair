package com.rockstar.o2o.basicservice;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BasicService {

	public void batchoperate(Object addobject,Object updateobject);
	
	public Object save(Object addobject);
	
	public void update(Object updateobject);
	
	public List<Object> query(Class<?> c, String sql, Object... objects);
	
	public Object querybyid(Class<?> c, Serializable id);
	
	public List<Object> pageQuery(Class<?> c, String sql,Integer pageSize,Integer pageNum, Object... objects);
	
	public List<Object> queryByMap(Class<?> c, String sql, Integer page, Integer size,  Map<String, Object> map) ;
}
