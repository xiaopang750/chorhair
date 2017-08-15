package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserVerificationmode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_verificationmode")
public class UserVerificationmode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkVerification;
	private Long pkUser;
	private String verificationType;
	private String openid;
	private String sex;
	private String pathurl;
	private String nickname;
	private String otherjson;
	private String headurl;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public UserVerificationmode() {
	}

	/** full constructor */
	public UserVerificationmode(Long pkUser, String verificationType,
			String openid, String sex, String pathurl, String nickname,
			String otherjson, String headurl, String ts, Short dr) {
		this.pkUser = pkUser;
		this.verificationType = verificationType;
		this.openid = openid;
		this.sex = sex;
		this.pathurl = pathurl;
		this.nickname = nickname;
		this.otherjson = otherjson;
		this.headurl = headurl;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_verification", unique = true, nullable = false)
	public Long getPkVerification() {
		return this.pkVerification;
	}

	public void setPkVerification(Long pkVerification) {
		this.pkVerification = pkVerification;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "verification_type")
	public String getVerificationType() {
		return this.verificationType;
	}

	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
	}

	@Column(name = "openid")
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "pathurl")
	public String getPathurl() {
		return this.pathurl;
	}

	public void setPathurl(String pathurl) {
		this.pathurl = pathurl;
	}

	@Column(name = "nickname")
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "otherjson")
	public String getOtherjson() {
		return this.otherjson;
	}

	public void setOtherjson(String otherjson) {
		this.otherjson = otherjson;
	}

	@Column(name = "headurl")
	public String getHeadurl() {
		return this.headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
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