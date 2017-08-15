package com.rockstar.o2o.weixin.http.service;

public abstract interface IHttpClientService
{
  public abstract String sendGetRequset(String paramString)
    throws Exception;

  public abstract String sendPostRequset(String paramString1, String paramString2)
    throws Exception;
}