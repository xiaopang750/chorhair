package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxAutoreplyKeyword entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "wx_autoreply_keyword")
public class WxAutoreplyKeyword implements java.io.Serializable {

	// Fields

	private Long pkKeywordId;
	private Long pkAutoreplyId;
	private String keyword;
	private String matchMode;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxAutoreplyKeyword() {
	}

	/** full constructor */
	public WxAutoreplyKeyword(Long pkKeywordId,Long pkAutoreplyId, String keyword,
			String matchMode, String ts, Short dr) {
		this.pkKeywordId = pkKeywordId;
		this.pkAutoreplyId = pkAutoreplyId;
		this.keyword = keyword;
		this.matchMode = matchMode;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_keyword_id", unique = true, nullable = false)
	public Long getPkKeywordId() {
		return this.pkKeywordId;
	}

	public void setPkKeywordId(Long pkKeywordId) {
		this.pkKeywordId = pkKeywordId;
	}

	@Column(name = "pk_autoreply_id")
	public Long getPkAutoreplyId() {
		return this.pkAutoreplyId;
	}

	public void setPkAutoreplyId(Long pkAutoreplyId) {
		this.pkAutoreplyId = pkAutoreplyId;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "match_mode")
	public String getMatchMode() {
		return this.matchMode;
	}

	public void setMatchMode(String matchMode) {
		this.matchMode = matchMode;
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