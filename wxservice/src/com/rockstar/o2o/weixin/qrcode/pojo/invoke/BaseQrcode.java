package com.rockstar.o2o.weixin.qrcode.pojo.invoke;

public class BaseQrcode
{
  private int expire_seconds;
  private String action_name;
  private ActionInfo action_info;

  public BaseQrcode()
  {
  }

  public int getExpire_seconds() {
    return this.expire_seconds;
  }

  public void setExpire_seconds(int expire_seconds) {
    this.expire_seconds = expire_seconds;
  }

  public String getAction_name() {
    return this.action_name;
  }

  public void setAction_name(String action_name) {
    this.action_name = action_name;
  }

  public ActionInfo getAction_info() {
    return this.action_info;
  }

  public void setAction_info(ActionInfo action_info) {
    this.action_info = action_info;
  }
}