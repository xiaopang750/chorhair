package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxAutoreply entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "wx_autoreply")
public class WxAutoreply implements java.io.Serializable {

	// Fields

	private Long pkAutoreplyId;
	private String replyType;
	private String msgType;
	private String content;
	private String ruleName;
	private String replyMode;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxAutoreply() {
	}

	/** full constructor */
	public WxAutoreply(Long pkAutoreplyId, String replyType,String msgType,
			String content, String ruleName, String replyMode,String keyword,
			String ts, Short dr) {
		this.pkAutoreplyId = pkAutoreplyId;
		this.replyType = replyType;
		this.msgType = msgType;
		this.content = content;
		this.ruleName = ruleName;
		this.replyMode = replyMode;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_autoreply_id", unique = true, nullable = false)
	public Long getPkAutoreplyId() {
		return this.pkAutoreplyId;
	}

	public void setPkAutoreplyId(Long pkAutoreplyId) {
		this.pkAutoreplyId = pkAutoreplyId;
	}

	@Column(name = "reply_type")
	public String getReplyType() {
		return this.replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	@Column(name = "msg_type")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "rule_name")
	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Column(name = "reply_mode")
	public String getReplyMode() {
		return this.replyMode;
	}

	public void setReplyMode(String replyMode) {
		this.replyMode = replyMode;
	}
	
	@Column(name = "ts", length = 19)
	public String getTs() {
		return this.ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Column(name = "dr")
	public Short getDr() {
		return this.dr;
	}

	public void setDr(Short dr) {
		this.dr = dr;
	}

}