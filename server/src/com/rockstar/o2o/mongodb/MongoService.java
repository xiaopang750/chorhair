package com.rockstar.o2o.mongodb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * mongo基础服务类
 * @author xucheng
 *
 */
public interface MongoService {

	public void save(Object obj);
	
	public Object getbyid(Class<?>  obj, Serializable id);
	
	public List<Object> pageQuery(Class<?> c,Integer pageSize,Integer pageNum,HashMap<String,Object> map);
	
	public void removeall(Class<?>  obj);
	
	public void removebyid(Class<?>  obj,Serializable id);
	
	public void insertALl(ArrayList<Object>  obj);
}
