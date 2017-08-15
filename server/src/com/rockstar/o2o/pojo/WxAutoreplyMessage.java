package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wx_autoreply_message")
public class WxAutoreplyMessage {
	private Long pkReplymsgId;
	private Long pkAutoreplyId;
	private String msgType;
	private String content;
	private String ts;
	private Short dr;
	
	// Constructors

	/** default constructor */
	public WxAutoreplyMessage() {
	}

	/** full constructor */
	public WxAutoreplyMessage(Long pkReplymsgId, Long pkAutoreplyId,
			String msgType, String content, String ts, Short dr) {
		super();
		this.pkReplymsgId = pkReplymsgId;
		this.pkAutoreplyId = pkAutoreplyId;
		this.msgType = msgType;
		this.content = content;
		this.ts = ts;
		this.dr = dr;
	}
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_replymsg_id", unique = true, nullable = false)
	public Long getPkReplymsgId() {
		return pkReplymsgId;
	}

	public void setPkReplymsgId(Long pkReplymsgId) {
		this.pkReplymsgId = pkReplymsgId;
	}
	
	@Column(name = "pk_autoreply_id")
	public Long getPkAutoreplyId() {
		return pkAutoreplyId;
	}

	public void setPkAutoreplyId(Long pkAutoreplyId) {
		this.pkAutoreplyId = pkAutoreplyId;
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

	@Column(name = "ts")
	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Column(name = "dr")
	public Short getDr() {
		return dr;
	}

	public void setDr(Short dr) {
		this.dr = dr;
	}
	
	
	
	
	
	
}
