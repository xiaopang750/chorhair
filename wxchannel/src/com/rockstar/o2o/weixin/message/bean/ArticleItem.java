package com.rockstar.o2o.weixin.message.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class ArticleItem
{

  @XStreamAlias("Title")
  private String title;

  @XStreamAlias("Description")
  private String description;

  @XStreamAlias("PicUrl")
  private String picUrl;

  @XStreamAlias("Url")
  private String url;

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPicUrl() {
    return this.picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}