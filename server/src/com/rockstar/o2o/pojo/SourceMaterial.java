package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SourceMaterial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "source_material")
public class SourceMaterial implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkSourceMaterial;
	private Long pkShop;
	private String materialtype;
	private String materialcontent;
	private String createtime;
	private Long creatorid;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public SourceMaterial() {
	}

	/** full constructor */
	public SourceMaterial(Long pkShop, String materialtype,
			String materialcontent, String createtime, Long creatorid,
			String ts, Short dr) {
		this.pkShop = pkShop;
		this.materialtype = materialtype;
		this.materialcontent = materialcontent;
		this.createtime = createtime;
		this.creatorid = creatorid;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_source_material", unique = true, nullable = false)
	public Long getPkSourceMaterial() {
		return this.pkSourceMaterial;
	}

	public void setPkSourceMaterial(Long pkSourceMaterial) {
		this.pkSourceMaterial = pkSourceMaterial;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "materialtype")
	public String getMaterialtype() {
		return this.materialtype;
	}

	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}

	@Column(name = "materialcontent")
	public String getMaterialcontent() {
		return this.materialcontent;
	}

	public void setMaterialcontent(String materialcontent) {
		this.materialcontent = materialcontent;
	}

	@Column(name = "createtime", length = 19)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "creatorid")
	public Long getCreatorid() {
		return this.creatorid;
	}

	public void setCreatorid(Long creatorid) {
		this.creatorid = creatorid;
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