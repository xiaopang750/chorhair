package com.rockstar.o2o.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxQrcodeScan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_qrcode_scan")
public class WxQrcodeScan implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkScanid;
	private String wxId;
	private String accountId;
	private String sceneId;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxQrcodeScan() {
	}

	/** full constructor */
	public WxQrcodeScan(String wxId, String accountId, String sceneId,
			String ts, Short dr) {
		this.wxId = wxId;
		this.accountId = accountId;
		this.sceneId = sceneId;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_scanid", unique = true, nullable = false)
	public Long getPkScanid() {
		return this.pkScanid;
	}

	public void setPkScanid(Long pkScanid) {
		this.pkScanid = pkScanid;
	}

	@Column(name = "wx_id")
	public String getWxId() {
		return this.wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	@Column(name = "account_id")
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "scene_id")
	public String getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
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