package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContentHottype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "content_hottype")
public class ContentHottype implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkHottype;
	private Long pkShop;
	private String hottype;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ContentHottype() {
	}

	/** full constructor */
	public ContentHottype(Long pkShop, String hottype, String ts, Short dr) {
		this.pkShop = pkShop;
		this.hottype = hottype;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_hottype", unique = true, nullable = false)
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