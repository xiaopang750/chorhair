package com.rockstar.o2o.weixin.http.service.impl;


import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rockstar.o2o.weixin.http.service.IHttpClientService;

@Service
public class HttpClientService
  implements IHttpClientService
{
  private static final Logger logger = Logger.getLogger(HttpClientService.class);

  @SuppressWarnings({ "rawtypes", "unchecked" })
public String sendGetRequset(String uri)
    throws Exception
  {
    if (uri == null) {
      throw new Exception("生成URL出错！");
    }

    String result = "";
    DefaultHttpClient httpClient = new DefaultHttpClient();
    try
    {
      HttpProtocolParams.setUserAgent(
        httpClient.getParams(), 
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
      HttpGet httpGet = new HttpGet(uri);
      logger.debug("本次请求的uri[get]:" + httpGet.getURI());
      httpGet.getParams().setParameter("http.conn-manager.timeout", Long.valueOf(2500L));
      ResponseHandler responseHandler = new BasicResponseHandler();
      result = (String)httpClient.execute(httpGet, responseHandler);
      result = new String(result.getBytes("iso-8859-1"), "UTF-8");
      logger.debug("result[final] is : " + new String(result));
    } catch (Exception e) {
      logger.error("http[get]请求出错！");
      e.printStackTrace();
      throw new Exception("http[get]请求出错");
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
    return result;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
public String sendPostRequset(String uri, String data) throws Exception
  {
    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(uri);
    String result = "";
    try {
      StringEntity entity = new StringEntity(new String(
        data.getBytes("UTF-8"), "ISO-8859-1"));
      entity.setContentEncoding("UTF-8");
      entity.setContentType("application/json");
      httpPost.setEntity(entity);

      ResponseHandler responseHandler = new BasicResponseHandler();
      result = (String)httpClient.execute(httpPost, responseHandler);

      logger.debug("result is : " + result);
    } catch (Exception e) {
      logger.error("http[post]请求出错！");
      e.printStackTrace();
      throw new Exception("http[post]请求出错");
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
    return result;
  }


}