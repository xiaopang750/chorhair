package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FairerAttachment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fairer_attachment")
public class FairerAttachment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkAttachment;
	private Long pkFairer;
	private String attachmenturl;
	private String attachmentshorturl;
	private String attachmenttype;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public FairerAttachment() {
	}

	/** full constructor */
	public FairerAttachment(Long pkFairer, String attachmenturl,
			String attachmentshorturl, String attachmenttype, String ts,
			Short dr) {
		this.pkFairer = pkFairer;
		this.attachmenturl = attachmenturl;
		this.attachmentshorturl = attachmentshorturl;
		this.attachmenttype = attachmenttype;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_attachment", unique = true, nullable = false)
	public Long getPkAttachment() {
		return this.pkAttachment;
	}

	public void setPkAttachment(Long pkAttachment) {
		this.pkAttachment = pkAttachment;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "attachmenturl")
	public String getAttachmenturl() {
		return this.attachmenturl;
	}

	public void setAttachmenturl(String attachmenturl) {
		this.attachmenturl = attachmenturl;
	}

	@Column(name = "attachmentshorturl")
	public String getAttachmentshorturl() {
		return this.attachmentshorturl;
	}

	public void setAttachmentshorturl(String attachmentshorturl) {
		this.attachmentshorturl = attachmentshorturl;
	}

	@Column(name = "attachmenttype", length = 50)
	public String getAttachmenttype() {
		return this.attachmenttype;
	}

	public void setAttachmenttype(String attachmenttype) {
		this.attachmenttype = attachmenttype;
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