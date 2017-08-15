package com.rockstar.o2o.weixin.message.bean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Music")
public class MusicUnit
{

  @XStreamAlias("Title")
  private String title;

  @XStreamAlias("Description")
  private String description;

  @XStreamAlias("MusicUrl")
  private String musicUrl;

  @XStreamAlias("HQMusicUrl")
  private String hQMusicUrl;

  @XStreamAlias("ThumbMediaId")
  private String thumbMediaId;
  
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

  public String getMusicUrl() {
    return this.musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }

  public String gethQMusicUrl() {
    return this.hQMusicUrl;
  }

  public void sethQMusicUrl(String hQMusicUrl) {
    this.hQMusicUrl = hQMusicUrl;
  }

public String getThumbMediaId() {
	return thumbMediaId;
}

public void setThumbMediaId(String thumbMediaId) {
	this.thumbMediaId = thumbMediaId;
}
  
  
}