package com.rockstar.o2o.weixin.message.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class WxReplyMsg {

	 @XStreamAlias("ToUserName")
	  private String toUserName;

	  @XStreamAlias("FromUserName")
	  private String fromUserName;

	  @XStreamAlias("CreateTime")
	  private String createTime;

	  @XStreamAlias("MsgType")
	  private String msgType;

	  @XStreamAlias("Content")
	  private String content;

	  @XStreamAlias("ArticleCount")
	  private String articleCount;

	  @XStreamAlias("Articles")
	  private ArticlesUnit articles;

	  @XStreamAlias("Music")
	  private MusicUnit musicunit;
	  
	  @XStreamAlias("Voice")
	  private VoiceUnit voiceunit;
	  
	  @XStreamAlias("Video")
	  private VideoUnit videounit;
	  
	  @XStreamAlias("Image")
	  private ImageUnit imageunit;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public ArticlesUnit getArticles() {
		return articles;
	}

	public void setArticles(ArticlesUnit articles) {
		this.articles = articles;
	}

	public MusicUnit getMusicunit() {
		return musicunit;
	}

	public void setMusicunit(MusicUnit musicunit) {
		this.musicunit = musicunit;
	}

	public VoiceUnit getVoiceunit() {
		return voiceunit;
	}

	public void setVoiceunit(VoiceUnit voiceunit) {
		this.voiceunit = voiceunit;
	}

	public VideoUnit getVideounit() {
		return videounit;
	}

	public void setVideounit(VideoUnit videounit) {
		this.videounit = videounit;
	}

	public ImageUnit getImageunit() {
		return imageunit;
	}

	public void setImageunit(ImageUnit imageunit) {
		this.imageunit = imageunit;
	}
	  
	  
}
