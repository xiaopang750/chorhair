package com.rockstar.o2o.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao {
	// 添加
	Object add(Object o);
	// 批量
	void batchsave(Object o);
	// 批量事务操作
	void batchoperator(Object addo,Object updateo);
	// 批量
	List<Object> batchsaves(Object o);
	// 批量更新
	void batchupdate(Object o);

	// 修改
	public int update(Object o) ;

	// 修改(在session中已存在这个对象的修改)
	public void merge(Object o) ;

	// 根据ID获取对象
	public Object getById(Class<?> c, Serializable id);

	// 物理删除对象
	public void delete(Object o);

	// 根据ID删除对象
	public void deleteById(Class<?> c, Serializable id);

	// 获取所有的记录
	public List<?> getAll(Class<?> c) ;

		// 批量修改
	public void bulkUpdate(String hql, Object... objects);

		// 得到唯一值
		public Object getUnique(final String hql, final Object... objects);
		
		// 分页查询

		public List<?> pageQuery(final String hql, final Integer page, final Integer size, final Object... objects);

		// 不分页查询
		public List<?> pageQuery(String hql, Object... objects);

		public void save(Object o);
		
		public void update(final String hql, final Object... objects);
		
		public List<?> querybysql(final String hql, final Object... objects);
		
		public  List<?> querybysqlclass(Class<?> c,final String sql, final Object... objects);
		
		
		public   List<?> querybysqlMap(final String sql,  final Integer page, final Integer size,final Object... objects);
		//根据条件分页查询 objects是对象数组
		public List<?> pageQueryByCon(final String hql, final Integer page, final Integer size, final Object... objects);
		// 根据条件不分页查询objects是对象数组
		public List<?> pageQueryByCon(String hql, Object... objects);
		
		//HQL根据条件map集合不分页查询
		List<?> queryHqlListByConMap(final String hql,final Map<String, Object> map);
		//HQL根据条件map集合分页查询
		List<?> queryHqlListByConMap(final String hql,final Integer page, final Integer size,final Map<String, Object> map);
		
		
		//SQL根据条件map集合不分页查询
		List<?> querySqlListByConMap(final String sql,final Map<String, Object> map);
		//SQL根据条件map集合分页查询
		List<?> querySqlListByConMap(final String sql,final Integer page, final Integer size,final Map<String, Object> map);
		
		//SQL根据条件map集合不分页查询--返回list
		List<?> querySqlListByConMap2(final String sql,final Map<String, Object> map);
		//SQL根据条件map集合分页查询--返回list
		List<?> querySqlListByConMap2(String sql, Integer page, Integer size,Map<String, Object> map);
		
		

}
