package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message_record")
public class MessageRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkMessage;
	private Long pkMessagelist;
	private String sender;
	private String receiver;
	private String content;
	private String code;
	private String messagetype;
	private String messagegroup;
	private String sendtime;
	private String sendresult;
	private String receivetime;
	private String receiveresult;
	private String messagestatus;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public MessageRecord() {
	}

	/** full constructor */
	public MessageRecord(Long pkMessagelist, String sender, String receiver,
			String content, String code, String messagetype,
			String messagegroup, String sendtime, String sendresult,
			String receivetime, String receiveresult, String messagestatus,
			String ts, Short dr) {
		this.pkMessagelist = pkMessagelist;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.code = code;
		this.messagetype = messagetype;
		this.messagegroup = messagegroup;
		this.sendtime = sendtime;
		this.sendresult = sendresult;
		this.receivetime = receivetime;
		this.receiveresult = receiveresult;
		this.messagestatus = messagestatus;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_message", unique = true, nullable = false)
	public Long getPkMessage() {
		return this.pkMessage;
	}

	public void setPkMessage(Long pkMessage) {
		this.pkMessage = pkMessage;
	}

	@Column(name = "pk_messagelist")
	public Long getPkMessagelist() {
		return this.pkMessagelist;
	}

	public void setPkMessagelist(Long pkMessagelist) {
		this.pkMessagelist = pkMessagelist;
	}

	@Column(name = "sender", length = 20)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "receiver", length = 20)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "code", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "messagetype", length = 30)
	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	@Column(name = "messagegroup", length = 20)
	public String getMessagegroup() {
		return this.messagegroup;
	}

	public void setMessagegroup(String messagegroup) {
		this.messagegroup = messagegroup;
	}

	@Column(name = "sendtime", length = 19)
	public String getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "sendresult", length = 1)
	public String getSendresult() {
		return this.sendresult;
	}

	public void setSendresult(String sendresult) {
		this.sendresult = sendresult;
	}

	@Column(name = "receivetime", length = 19)
	public String getReceivetime() {
		return this.receivetime;
	}

	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}

	@Column(name = "receiveresult")
	public String getReceiveresult() {
		return this.receiveresult;
	}

	public void setReceiveresult(String receiveresult) {
		this.receiveresult = receiveresult;
	}

	@Column(name = "messagestatus", length = 30)
	public String getMessagestatus() {
		return this.messagestatus;
	}

	public void setMessagestatus(String messagestatus) {
		this.messagestatus = messagestatus;
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