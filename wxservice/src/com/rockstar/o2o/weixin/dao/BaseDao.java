package com.rockstar.o2o.weixin.dao;

import java.io.Serializable;
import java.util.List;

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

	// 修改(在session中已存在这个对象的修�?
	public void merge(Object o) ;

	// 根据ID获取对象
	public Object getById(Class<?> c, Serializable id);

	// 物理删除对象
	public void delete(Object o);

	// 根据ID删除对象
	public void deleteById(Class<?> c, Serializable id);

	// 获取�?��的记�?	public List<?> getAll(Class<?> c) ;

		// 批量修改
	public void bulkUpdate(String hql, Object... objects);

		// 得到唯一�?		public Object getUnique(final String hql, final Object... objects);
		
		// 分页查询

		public List<?> pageQuery(final String hql, final Integer page, final Integer size, final Object... objects);

		// 不分页查�?		public List<?> pageQuery(String hql, Object... objects);

		public void save(Object o);
		
		public void update(final String hql, final Object... objects);
		
		public List<?> querybysql(final String hql, final Object... objects);
		
		public  List<?> querybysqlclass(Class<?> c,final String sql, final Object... objects);
		
		
		public   List<?> querybysqlMap(final String sql,  final Integer page, final Integer size,final Object... objects);
		
		

}
