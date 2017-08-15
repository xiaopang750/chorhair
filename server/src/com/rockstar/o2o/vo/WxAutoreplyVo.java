package com.rockstar.o2o.vo;

import java.util.ArrayList;
import java.util.List;
import com.rockstar.o2o.pojo.WxAutoreplyKeyword;
import com.rockstar.o2o.pojo.WxAutoreplyMessage;

public class WxAutoreplyVo {
	private Long autoreplyId;
	private String replyType;
	private String ruleName;
	private String replyMode;
	private List<WxAutoreplyKeyword> keywordData= new ArrayList<WxAutoreplyKeyword>();
	private List<WxAutoreplyMessage> messageData = new ArrayList<WxAutoreplyMessage>();
	private String ts;

	public Long getAutoreplyId() {
		return autoreplyId;
	}
	public void setAutoreplyId(Long autoreplyId) {
		this.autoreplyId = autoreplyId;
	}
	public String getReplyType() {
		return replyType;
	}
	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	public String getReplyMode() {
		return replyMode;
	}
	public void setReplyMode(String replyMode) {
		this.replyMode = replyMode;
	}
	public List<WxAutoreplyKeyword> getKeywordData() {
		return keywordData;
	}
	public void setKeywordData(List<WxAutoreplyKeyword> keywordData) {
		this.keywordData = keywordData;
	}
	public List<WxAutoreplyMessage> getMessageData() {
		return messageData;
	}
	public void setMessageData(List<WxAutoreplyMessage> messageData) {
		this.messageData = messageData;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	
}
