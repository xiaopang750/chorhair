package com.rockstar.o2o.http;

public abstract interface IHttpClientService
{
  public abstract String sendGetRequset(String url)
    throws Exception;

  public abstract String sendPostRequset(String url, String data)
    throws Exception;
}
