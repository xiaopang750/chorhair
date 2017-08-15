package com.rockstar.o2o.weixin.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.rockstar.o2o.weixin.dao.BaseDao;

/**
 * 基础操作Dao
 * @author xc
 *
 */
@Component
public class BaseDaoImpl  implements BaseDao{
	
	@Resource
	private HibernateTemplate hibernateTemplate;



	// 添加
	public Object add(Object o) {
		Serializable pk = hibernateTemplate.save(o);
		return pk;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void batchsave(Object o) {
		// TODO Auto-generated method stub
		 Session session=this.hibernateTemplate.getSessionFactory().openSession();
		 Transaction tx=null;
		 try {
			tx=session.beginTransaction();
			ArrayList list=(ArrayList) o;
			for(int i=0;i<list.size();i++){
				session.save(list.get(i));
				if(i % 50 == 0 ){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> batchsaves(Object o) {
		// TODO Auto-generated method stub
		 Session session=this.hibernateTemplate.getSessionFactory().openSession();
		 Transaction tx=null;
		 ArrayList<Object> returnlist=new ArrayList<Object>();
		 try {
			tx=session.beginTransaction();
			ArrayList list=(ArrayList) o;
			for(int i=0;i<list.size();i++){
				Serializable pk=session.save(list.get(i));
				returnlist.add(pk);
				if(i % 50 == 0 ){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
		
		return returnlist;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void batchupdate(Object o) {
		// TODO Auto-generated method stub
		 Session session=this.hibernateTemplate.getSessionFactory().openSession();
		 Transaction tx=null;
		 try {
			tx=session.beginTransaction();
			ArrayList list=(ArrayList) o;
			for(int i=0;i<list.size();i++){
				session.update(list.get(i));
				if(i % 50 == 0 ){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
	// 修改
	public int update(Object o) {
		try {
			hibernateTemplate.update(o);	
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
		
	}

	// 修改(在session中已存在这个对象的修改)
	public void merge(Object o) {
		hibernateTemplate.merge(o);
	}

	// 根据ID获取对象
	public Object getById(Class<?> c, Serializable id) {
		return hibernateTemplate.get(c, id);
	}

	// 删除对象
	public void delete(Object o) {
		hibernateTemplate.delete(o);
	}

	// 根据ID删除对象
	public void deleteById(Class<?> c, Serializable id) {
		delete(getById(c, id));
	}

	// 获取所有的记录
	public List<?> getAll(Class<?> c) {
		return hibernateTemplate.find("from " + c.getName()+" where IFNULL(dr,0)=0");
	}

	// 批量修改
	public void bulkUpdate(String hql, Object... objects) {
		hibernateTemplate.bulkUpdate(hql, objects);
	}

	// 得到唯一值
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getUnique(final String hql, final Object... objects) {
		return hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (objects != null)
					for (int i = 0; i < objects.length; i++)
						query.setParameter(i, objects[i]);
				return query.uniqueResult();
			}
		});
	}

	// 分页查询
	@SuppressWarnings("rawtypes")
	public List<?> pageQuery(final String hql, final Integer page, final Integer size, final Object... objects) {
		return hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (objects != null)
					for (int i = 0; i < objects.length; i++){
						query.setParameter(i, objects[i]);
					}
				if (page != null && size != null)
					query.setFirstResult((page - 1) * size).setMaxResults(size);
				return query.list();
			}
		});
	}

	// 不分页查询
	public List<?> pageQuery(String hql, Object... objects) {
		return pageQuery(hql, null, null, objects);
	}

	public void save(Object o) {
		if (o != null)
			hibernateTemplate.saveOrUpdate(o);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update(final String hql, final Object... objects){
		hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (objects != null)
					for (int i = 0; i < objects.length; i++)
						query.setParameter(i, objects[i]);
				return query.executeUpdate();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public  List<?> querybysql(final String sql, final Object... objects){
		return hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (objects != null&&!objects.equals(""))
					for (int i = 0; i < objects.length; i++){
						query.setParameter(i, objects[i]);
					}
				return query.list();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	public  List<?> querybysqlclass(final Class<?> c,final String sql, final Object... objects){
		return hibernateTemplate.executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (objects != null&&!objects.equals(""))
					for (int i = 0; i < objects.length; i++){
						query.setParameter(i, objects[i]);
					}
				query.setResultTransformer(Transformers.aliasToBean(c));
				return query.list();
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<?> querybysqlMap(final String sql, final Integer page, final Integer size, final Object... objects) {
		// TODO Auto-generated method stub
		
            return hibernateTemplate.executeFind(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (objects != null&&!objects.equals(""))
					for (int i = 0; i < objects.length; i++){
						query.setParameter(i, objects[i]);
					}
				if (page != null && size != null)
				query.setFirstResult((page - 1) * size).setMaxResults(size);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void batchoperator(Object addo, Object updateo) {
		// TODO Auto-generated method stub
		 Session session=this.hibernateTemplate.getSessionFactory().openSession();
		 Transaction tx=null;
		 try {
			tx=session.beginTransaction();
			//新加项
			if(addo!=null){
				ArrayList addlist=(ArrayList) addo;
				for(int i=0;i<addlist.size();i++){
					session.save(addlist.get(i));
					if(i % 50 == 0 ){
						session.flush();
						session.clear();
					}
				}
			}
	
			//修改项
			if(updateo!=null){
				ArrayList updatelist=(ArrayList) updateo;
				for(int i=0;i<updatelist.size();i++){
					session.update(updatelist.get(i));
					if(i % 50 == 0 ){
						session.flush();
						session.clear();
					}
				}
			}
			
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	}
}
