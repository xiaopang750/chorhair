package com.rockstar.o2o.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_user")
public class WxUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkUser;
	private String wxId;
	private String accountId;
	private String nickname;
	private String sex;
	private String country;
	private String city;
	private String province;
	private String headImageUrl;
	private String subscribeFlag;
	private String subscribeTime;
	private String unsubscribeTime;
	private String bindFlag;
	private String bindTime;
	private String bingPhone;
	private String crmId;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxUser() {
	}

	/** full constructor */
	public WxUser(String wxId, String accountId,String nickname, String sex, String country,
			String city, String province, String headImageUrl,
			String subscribeFlag, String subscribeTime, String unsubscribeTime,
			String bindFlag, String bindTime, String bingPhone, String crmId,
			String ts, Short dr) {
		this.wxId = wxId;
		this.accountId = accountId;
		this.nickname = nickname;
		this.sex = sex;
		this.country = country;
		this.city = city;
		this.province = province;
		this.headImageUrl = headImageUrl;
		this.subscribeFlag = subscribeFlag;
		this.subscribeTime = subscribeTime;
		this.unsubscribeTime = unsubscribeTime;
		this.bindFlag = bindFlag;
		this.bindTime = bindTime;
		this.bingPhone = bingPhone;
		this.crmId = crmId;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_user", unique = true, nullable = false)
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "wx_id", length = 100)
	public String getWxId() {
		return this.wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}


	@Column(name = "account_id", length = 100)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "nickname")
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sex", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "country")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province")
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "head_image_url")
	public String getHeadImageUrl() {
		return this.headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	@Column(name = "subscribe_flag", length = 1)
	public String getSubscribeFlag() {
		return this.subscribeFlag;
	}

	public void setSubscribeFlag(String subscribeFlag) {
		this.subscribeFlag = subscribeFlag;
	}

	@Column(name = "subscribe_time")
	public String getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	@Column(name = "unsubscribe_time")
	public String getUnsubscribeTime() {
		return this.unsubscribeTime;
	}

	public void setUnsubscribeTime(String unsubscribeTime) {
		this.unsubscribeTime = unsubscribeTime;
	}

	@Column(name = "bind_flag")
	public String getBindFlag() {
		return this.bindFlag;
	}

	public void setBindFlag(String bindFlag) {
		this.bindFlag = bindFlag;
	}

	@Column(name = "bind_time", length = 19)
	public String getBindTime() {
		return this.bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	@Column(name = "bing_phone", length = 20)
	public String getBingPhone() {
		return this.bingPhone;
	}

	public void setBingPhone(String bingPhone) {
		this.bingPhone = bingPhone;
	}

	@Column(name = "crm_id")
	public String getCrmId() {
		return this.crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
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