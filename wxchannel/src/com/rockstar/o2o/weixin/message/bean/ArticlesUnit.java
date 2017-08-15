package com.rockstar.o2o.weixin.message.bean;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

@XStreamAlias("Articles")
public class ArticlesUnit
{

  @XStreamImplicit(itemFieldName="item")
  private List<ArticleItem> item;

  public List<ArticleItem> getItem()
  {
    return this.item;
  }

  public void setItem(List<ArticleItem> item) {
    this.item = item;
  }
}