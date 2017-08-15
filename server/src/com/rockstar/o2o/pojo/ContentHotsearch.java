package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContentHotsearch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "content_hotsearch")
public class ContentHotsearch implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkHotsearch;
	private Long pkHottype;
	private Long pkShop;
	private String hottype;
	private String hotword;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ContentHotsearch() {
	}

	/** full constructor */
	public ContentHotsearch(Long pkHottype, Long pkShop, String hottype,
			String hotword, String ts, Short dr) {
		this.pkHottype = pkHottype;
		this.pkShop = pkShop;
		this.hottype = hottype;
		this.hotword = hotword;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_hotsearch", unique = true, nullable = false)
	public Long getPkHotsearch() {
		return this.pkHotsearch;
	}

	public void setPkHotsearch(Long pkHotsearch) {
		this.pkHotsearch = pkHotsearch;
	}

	@Column(name = "pk_hottype")
	public Long getPkHottype() {
		return this.pkHottype;
	}

	public void setPkHottype(Long pkHottype) {
		this.pkHottype = pkHottype;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "hottype")
	public String getHottype() {
		return this.hottype;
	}

	public void setHottype(String hottype) {
		this.hottype = hottype;
	}

	@Column(name = "hotword")
	public String getHotword() {
		return this.hotword;
	}

	public void setHotword(String hotword) {
		this.hotword = hotword;
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