package com.rockstar.o2o.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxAccessToken entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_access_token")
public class WxAccessToken implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkToken;
	private String accessToken;
	private String accountId;
	private String expiresIn;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxAccessToken() {
	}

	/** full constructor */
	public WxAccessToken(String accessToken, String accountId,
			String expiresIn, String ts, Short dr) {
		this.accessToken = accessToken;
		this.accountId = accountId;
		this.expiresIn = expiresIn;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_token", unique = true, nullable = false)
	public Long getPkToken() {
		return this.pkToken;
	}

	public void setPkToken(Long pkToken) {
		this.pkToken = pkToken;
	}

	@Column(name = "access_token")
	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "account_id")
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "expires_in")
	public String getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
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