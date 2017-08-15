package com.rockstar.o2o.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class IHttpClientServiceImpl implements IHttpClientService{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String sendGetRequset(String url) throws Exception {
		// TODO Auto-generated method stub
		 if (url == null) {
		      throw new Exception("生成URL出错！");
		    }

		    String result = "";
		    DefaultHttpClient httpClient = new DefaultHttpClient();
		    try
		    {
		      HttpProtocolParams.setUserAgent(
		        httpClient.getParams(), 
		        "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
		      HttpGet httpGet = new HttpGet(url);
		      httpGet.getParams().setParameter("http.conn-manager.timeout", Long.valueOf(2500L));
		      ResponseHandler responseHandler = new BasicResponseHandler();
		      result = (String)httpClient.execute(httpGet, responseHandler);
		    } catch (Exception e) {
		      e.printStackTrace();
		      throw new Exception("http[get]请求出错");
		    } finally {
		      httpClient.getConnectionManager().shutdown();
		    }
		    return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public String sendPostRequset(String url, String data) throws Exception {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
	    HttpPost httpPost = new HttpPost(url);
	    String result = "";
	    try {
	      StringEntity entity = new StringEntity(data,HTTP.UTF_8);
	      entity.setContentEncoding("UTF-8");
	      entity.setContentType("application/json");
	      httpPost.setEntity(entity);
	      ResponseHandler responseHandler = new BasicResponseHandler();
	      result = (String)httpClient.execute(httpPost, responseHandler);
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new Exception("http[post]请求出错");
	    } finally {
	      httpClient.getConnectionManager().shutdown();
	    }
	    return result;
	}

}
