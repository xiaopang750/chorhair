package com.rockstar.o2o.weixin.qrcode.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxQrcodeDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_qrcode_detail")
public class WxQrcodeDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkDetail;
	private String sceneId;
	private String accountId;
	private String actionName;
	private String ticket;
	private String qrcodeUrl;
	private String expireTime;
	private String operateobject;
	private String operatetype;
	private Long pkKey;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxQrcodeDetail() {
	}

	/** full constructor */
	public WxQrcodeDetail(String sceneId, String accountId, String actionName,
			String ticket, String qrcodeUrl, String expireTime,
			String operateobject, String operatetype, Long pkKey,
			String ts, Short dr) {
		this.sceneId = sceneId;
		this.accountId = accountId;
		this.actionName = actionName;
		this.ticket = ticket;
		this.qrcodeUrl = qrcodeUrl;
		this.expireTime = expireTime;
		this.operateobject = operateobject;
		this.operatetype = operatetype;
		this.pkKey = pkKey;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_detail", unique = true, nullable = false)
	public Long getPkDetail() {
		return this.pkDetail;
	}

	public void setPkDetail(Long pkDetail) {
		this.pkDetail = pkDetail;
	}

	@Column(name = "scene_id")
	public String getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	@Column(name = "account_id")
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "action_name")
	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "ticket")
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Column(name = "qrcode_url")
	public String getQrcodeUrl() {
		return this.qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	@Column(name = "expire_time", length = 19)
	public String getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	@Column(name = "operateobject")
	public String getOperateobject() {
		return this.operateobject;
	}

	public void setOperateobject(String operateobject) {
		this.operateobject = operateobject;
	}

	@Column(name = "operatetype")
	public String getOperatetype() {
		return this.operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	@Column(name = "pk_key")
	public Long getPkKey() {
		return this.pkKey;
	}

	public void setPkKey(Long pkKey) {
		this.pkKey = pkKey;
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