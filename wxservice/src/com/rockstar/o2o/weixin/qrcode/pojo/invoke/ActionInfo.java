package com.rockstar.o2o.weixin.qrcode.pojo.invoke;

public class ActionInfo
{
  private Scene scene;

  public ActionInfo(Scene scene)
  {
    this.scene = scene;
  }

  public Scene getScene() {
    return this.scene;
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }
}