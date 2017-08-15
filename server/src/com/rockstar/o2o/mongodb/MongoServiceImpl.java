package com.rockstar.o2o.mongodb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


@Component
public class MongoServiceImpl implements MongoService{

	
	    @Resource
	    MongoTemplate mongoTemplate;

		@Override
		public void save(Object obj) {
			// TODO Auto-generated method stub
			mongoTemplate.save(obj);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Object> pageQuery(Class<?> c, Integer pageSize,
				Integer pageNum, HashMap<String, Object> map) {
			// TODO Auto-generated method stub
			Query query=new Query();
			Criteria criteria;
			if(map!=null&&map.size()>0){
				  Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		            while (iter.hasNext()) {
		                Entry<String, Object> entry = iter.next();
		                if (null != entry.getValue()) {
		                	criteria=Criteria.where(entry.getKey()).is(entry.getValue());
		                    query.addCriteria(criteria);
		                }
		            }
			}
          
            if (pageSize != null && pageNum != null)
            query.skip((pageNum - 1) * pageSize).limit(pageSize);
            
           return  (List<Object>) mongoTemplate.find(query, c);
		}

		@Override
		public void removeall(Class<?>  obj) {
			// TODO Auto-generated method stub
			Query query=new Query();
			mongoTemplate.remove(query,obj);
		}

		@Override
		public Object getbyid(Class<?> obj, Serializable id) {
			// TODO Auto-generated method stub
			return mongoTemplate.findById(id, obj);
		}

		@Override
		public void insertALl(ArrayList<Object> obj) {
			// TODO Auto-generated method stub
			mongoTemplate.insertAll(obj);
		}

		@Override
		public void removebyid(Class<?> obj, Serializable id) {
			// TODO Auto-generated method stub
			mongoTemplate.remove(mongoTemplate.findById(id, obj));
		}

}
