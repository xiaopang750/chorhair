package com.rockstar.o2o.weixin.message.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("Video")
public class VideoUnit {


	  @XStreamAlias("MediaId")
	  private String mediaid;
	  

	  @XStreamAlias("Title")
	  private String title;
	  
	  

	  @XStreamAlias("Description")
	  private String description;



	public String getMediaid() {
		return mediaid;
	}



	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	  
	  
	  
}
