package com.rockstar.o2o.weixin.service;

import java.util.List;

public interface BasicService {

	public void batchoperate(Object addobject,Object updateobject);
	
	public Object save(Object addobject);
	
	public List<Object> query(Class<?> c, String sql, Object... objects);
	
	public void update(Object updateobject);
	
}
