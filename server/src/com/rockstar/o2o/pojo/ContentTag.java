package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContentTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "content_tag")
public class ContentTag implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkTag;
	private Long pkContent;
	private String tagname;
	private String matchtype;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ContentTag() {
	}

	/** full constructor */
	public ContentTag(Long pkContent, String tagname, String matchtype,
			String ts, Short dr) {
		this.pkContent = pkContent;
		this.tagname = tagname;
		this.matchtype = matchtype;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_tag", unique = true, nullable = false)
	public Long getPkTag() {
		return this.pkTag;
	}

	public void setPkTag(Long pkTag) {
		this.pkTag = pkTag;
	}

	@Column(name = "pk_content")
	public Long getPkContent() {
		return this.pkContent;
	}

	public void setPkContent(Long pkContent) {
		this.pkContent = pkContent;
	}

	@Column(name = "tagname")
	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	@Column(name = "matchtype", length = 50)
	public String getMatchtype() {
		return this.matchtype;
	}

	public void setMatchtype(String matchtype) {
		this.matchtype = matchtype;
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