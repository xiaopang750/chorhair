package com.rockstar.o2o.weixin.util;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	private static JedisPool pool;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[redis.properties] is not found!");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle.getString(
				"redis.pool.maxActive").trim())); // 最大连接数
		config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle").trim())); // 空闲个数
		config.setMaxWaitMillis(Long.valueOf(bundle.getString(
				"redis.pool.maxWait").trim())); // 最大等待时间
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString(
				"redis.pool.testOnBorrow").trim()));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString(
				"redis.pool.testOnReturn").trim()));
		String env=System.getenv("CHORHAIR");
		if(env==null||env.equals("")||env.equals("test")){
			pool = new JedisPool(config, bundle.getString("test.redis.ip").trim(), // ip
					Integer.valueOf(bundle.getString("redis.port").trim())); // 端口
		}else if(env.equals("build")){
		    pool = new JedisPool(config, bundle.getString("build.redis.ip").trim(), // ip
				Integer.valueOf(bundle.getString("redis.port").trim())); // 端口
		}
	}
	private Jedis jedis;

	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public static synchronized void setKey(String key, Object obj)
			throws Exception {
		if (key == null || key.trim().equals("")) {
			throw new IllegalArgumentException(
					"[redis  key] should not be null!");
          
		} else {
			
			Jedis jedis = pool.getResource();
			try {
				
				jedis.set(key.getBytes(), SerializeUtil.serialize(obj));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.returnResource(jedis);
			}
		}
	}

	public static synchronized Object getObject(String key) throws Exception {
		if (key == null || key.trim().equals("")) {
			throw new IllegalArgumentException(
					"[redis  key] should not be null!");

		}else{
		Jedis jedis = pool.getResource();
		try {

			byte[] obytes = jedis.get(key.getBytes());

			return SerializeUtil.unserialize(obytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return null;}
	}

	public static synchronized void delKey(String key) throws Exception {

		Jedis jedis = pool.getResource();
		try {

			jedis.del(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
	}

	public static void main(String[] args) throws Exception {
		
		Jedis jedis = pool.getResource();
		jedis.publish("roban:test:channel", "侧谁谁谁");
		
		// JsonInfo jsonInfo=new JsonInfo();
		// jsonInfo.setCode("code");
		// JSONObject jobj=new JSONObject();
		// jobj.put("test", "json...");
		// jsonInfo.setData(jobj);
		// jsonInfo.setMsg("msg");
		// jsonInfo.setType("type");
		//
		// RedisUtils.setKey("ss", jsonInfo);
		// jsonInfo =(JsonInfo) RedisUtils.getObject("ss");
		// System.out.println(jsonInfo.getData());
		// RedisUtils.delKey("ss");
		// jsonInfo =(JsonInfo) RedisUtils.getObject("ss");
		// if(jsonInfo==null){
		// System.out.println("dddddddddddddddd");
		// }
		// //
		//RedisUtils.setKey("ss", "12");
		//RedisUtils.setKey("", "123");
		//System.out.println(RedisUtils.getObject("12"));
//		RmrbCustomerserviceDetail detail=	new RmrbCustomerserviceDetail();
//		detail.setAccountid("1");
//		if(detail!=null){
//			JSONObject j=JSONObject.fromObject(detail);
//			System.out.println((j.toString()));
//		}

	}
}
