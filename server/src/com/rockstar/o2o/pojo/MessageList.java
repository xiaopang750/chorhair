package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "message_list")
public class MessageList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkMessagelist;
	private Long pkShop;
	private String sender;
	private String receiver;
	private String accesser;
	private String sendtype;
	private String messagegroup;
	private String messagestatus;
	private String messagetype;
	private String lastmessage;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public MessageList() {
	}

	/** full constructor */
	public MessageList(Long pkShop, String sender, String receiver,
			String accesser, String sendtype, String messagegroup,
			String messagestatus, String messagetype, String lastmessage,
			String ts, Short dr) {
		this.pkShop = pkShop;
		this.sender = sender;
		this.receiver = receiver;
		this.accesser = accesser;
		this.sendtype = sendtype;
		this.messagegroup = messagegroup;
		this.messagestatus = messagestatus;
		this.messagetype = messagetype;
		this.lastmessage = lastmessage;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_messagelist", unique = true, nullable = false)
	public Long getPkMessagelist() {
		return this.pkMessagelist;
	}

	public void setPkMessagelist(Long pkMessagelist) {
		this.pkMessagelist = pkMessagelist;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "sender", length = 30)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "receiver")
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "accesser", length = 30)
	public String getAccesser() {
		return this.accesser;
	}

	public void setAccesser(String accesser) {
		this.accesser = accesser;
	}

	@Column(name = "sendtype", length = 30)
	public String getSendtype() {
		return this.sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	@Column(name = "messagegroup", length = 30)
	public String getMessagegroup() {
		return this.messagegroup;
	}

	public void setMessagegroup(String messagegroup) {
		this.messagegroup = messagegroup;
	}

	@Column(name = "messagestatus", length = 30)
	public String getMessagestatus() {
		return this.messagestatus;
	}

	public void setMessagestatus(String messagestatus) {
		this.messagestatus = messagestatus;
	}

	@Column(name = "messagetype", length = 30)
	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	@Column(name = "lastmessage")
	public String getLastmessage() {
		return this.lastmessage;
	}

	public void setLastmessage(String lastmessage) {
		this.lastmessage = lastmessage;
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