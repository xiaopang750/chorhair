package com.rockstar.o2o.weixin.message.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Voice")
public class VoiceUnit {

	  @XStreamAlias("MediaId")
	  private String mediaid;

	public String getMediaid() {
		return mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	  
	  
}
