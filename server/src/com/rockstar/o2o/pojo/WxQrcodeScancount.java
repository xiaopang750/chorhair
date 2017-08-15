package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxQrcodeScancount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_qrcode_scancount")
public class WxQrcodeScancount implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkScancountid;
	private Long pkKey;
	private String operateobject;
	private String accountId;
	private String openid;
	private String userDefined;
	private String qrcodeUrls;
	private String sceneId;
	private String actionName;
	private Long count;
	private String ts;
	private Short dr;
	private Long attentionCount;
	// Constructors

	/** default constructor */
	public WxQrcodeScancount() {
	}

	/** full constructor */
	public WxQrcodeScancount(Long pkKey, String operateobject,String accountId, String openid,String userDefined, String qrcodeUrls, String sceneId, String actionName,
			Long count, String ts, Short dr,Long attentionCount) {
		this.pkKey = pkKey;
		this.operateobject = operateobject;
		this.userDefined = userDefined;
		this.qrcodeUrls = qrcodeUrls;
		this.sceneId = sceneId;
		this.accountId = accountId;
		this.openid = openid;
		this.actionName = actionName;
		this.count = count;
		this.ts = ts;
		this.dr = dr;
		this.attentionCount = attentionCount;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_scancountid", unique = true, nullable = false)
	public Long getPkScancountid() {
		return this.pkScancountid;
	}

	public void setPkScancountid(Long pkScancountid) {
		this.pkScancountid = pkScancountid;
	}

	@Column(name = "pk_key")
	public Long getPkKey() {
		return this.pkKey;
	}

	public void setPkKey(Long pkKey) {
		this.pkKey = pkKey;
	}

	@Column(name = "operateobject")
	public String getOperateobject() {
		return this.operateobject;
	}

	public void setOperateobject(String operateobject) {
		this.operateobject = operateobject;
	}

	@Column(name = "account_id")
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "openid")
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "user_defined")
	public String getUserDefined() {
		return userDefined;
	}

	public void setUserDefined(String userDefined) {
		this.userDefined = userDefined;
	}
	@Column(name = "qrcode_url")
	public String getQrcodeUrls() {
		return qrcodeUrls;
	}

	public void setQrcodeUrls(String qrcodeUrls) {
		this.qrcodeUrls = qrcodeUrls;
	}
	@Column(name = "scene_id", unique = true)
	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	@Column(name = "action_name")
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "count")
	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
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

	@Column(name = "attention_count")
	public Long getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(Long attentionCount) {
		this.attentionCount = attentionCount;
	}

}