package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlatformArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "platform_area")
public class PlatformArea implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkArea;
	private Long pkFatherarea;
	private String areaname;
	private Short belonglevel;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public PlatformArea() {
	}

	/** full constructor */
	public PlatformArea(Long pkFatherarea, String areaname, Short belonglevel,
			String ts, Short dr) {
		this.pkFatherarea = pkFatherarea;
		this.areaname = areaname;
		this.belonglevel = belonglevel;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_area", unique = true, nullable = false)
	public Long getPkArea() {
		return this.pkArea;
	}

	public void setPkArea(Long pkArea) {
		this.pkArea = pkArea;
	}

	@Column(name = "pk_fatherarea")
	public Long getPkFatherarea() {
		return this.pkFatherarea;
	}

	public void setPkFatherarea(Long pkFatherarea) {
		this.pkFatherarea = pkFatherarea;
	}

	@Column(name = "areaname")
	public String getAreaname() {
		return this.areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	@Column(name = "belonglevel")
	public Short getBelonglevel() {
		return this.belonglevel;
	}

	public void setBelonglevel(Short belonglevel) {
		this.belonglevel = belonglevel;
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