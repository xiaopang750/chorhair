package com.rockstar.o2o.weixin.oauth.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxOauthEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_oauth_event")
public class WxOauthEvent implements java.io.Serializable {

	// Fields

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkEvent;
	private String accountId;
	private String eventId;
	private String redirectUrl;
	private String invalidFlag;
	private String param1Name;
	private String param1Value;
	private String param2Name;
	private String param2Value;
	private String param3Name;
	private String param3Value;
	private String param4Name;
	private String param4Value;
	private String param5Name;
	private String param5Value;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxOauthEvent() {
	}

	/** full constructor */
	public WxOauthEvent(String accountId, String eventId, String redirectUrl,
			String invalidFlag, String param1Name, String param1Value,
			String param2Name, String param2Value, String param3Name,
			String param3Value, String param4Name, String param4Value,
			String param5Name, String param5Value, String ts, Short dr) {
		this.accountId = accountId;
		this.eventId = eventId;
		this.redirectUrl = redirectUrl;
		this.invalidFlag = invalidFlag;
		this.param1Name = param1Name;
		this.param1Value = param1Value;
		this.param2Name = param2Name;
		this.param2Value = param2Value;
		this.param3Name = param3Name;
		this.param3Value = param3Value;
		this.param4Name = param4Name;
		this.param4Value = param4Value;
		this.param5Name = param5Name;
		this.param5Value = param5Value;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_event", unique = true, nullable = false)
	public Long getPkEvent() {
		return this.pkEvent;
	}

	public void setPkEvent(Long pkEvent) {
		this.pkEvent = pkEvent;
	}

	@Column(name = "account_id", length = 30)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "event_id", length = 30)
	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name = "redirect_url")
	public String getRedirectUrl() {
		return this.redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Column(name = "invalid_flag", length = 1)
	public String getInvalidFlag() {
		return this.invalidFlag;
	}

	public void setInvalidFlag(String invalidFlag) {
		this.invalidFlag = invalidFlag;
	}

	@Column(name = "param1_name")
	public String getParam1Name() {
		return this.param1Name;
	}

	public void setParam1Name(String param1Name) {
		this.param1Name = param1Name;
	}

	@Column(name = "param1_value")
	public String getParam1Value() {
		return this.param1Value;
	}

	public void setParam1Value(String param1Value) {
		this.param1Value = param1Value;
	}

	@Column(name = "param2_name")
	public String getParam2Name() {
		return this.param2Name;
	}

	public void setParam2Name(String param2Name) {
		this.param2Name = param2Name;
	}

	@Column(name = "param2_value")
	public String getParam2Value() {
		return this.param2Value;
	}

	public void setParam2Value(String param2Value) {
		this.param2Value = param2Value;
	}

	@Column(name = "param3_name")
	public String getParam3Name() {
		return this.param3Name;
	}

	public void setParam3Name(String param3Name) {
		this.param3Name = param3Name;
	}

	@Column(name = "param3_value")
	public String getParam3Value() {
		return this.param3Value;
	}

	public void setParam3Value(String param3Value) {
		this.param3Value = param3Value;
	}

	@Column(name = "param4_name")
	public String getParam4Name() {
		return this.param4Name;
	}

	public void setParam4Name(String param4Name) {
		this.param4Name = param4Name;
	}

	@Column(name = "param4_value")
	public String getParam4Value() {
		return this.param4Value;
	}

	public void setParam4Value(String param4Value) {
		this.param4Value = param4Value;
	}

	@Column(name = "param5_name")
	public String getParam5Name() {
		return this.param5Name;
	}

	public void setParam5Name(String param5Name) {
		this.param5Name = param5Name;
	}

	@Column(name = "param5_value")
	public String getParam5Value() {
		return this.param5Value;
	}

	public void setParam5Value(String param5Value) {
		this.param5Value = param5Value;
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