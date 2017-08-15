package com.rockstar.o2o.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxSourceMaterial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_source_material")
public class WxSourceMaterial implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkSourceMaterial;
	private String materialtype;
	private String mediaId;
	private String updateTime;
	private String item;
	private String name;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxSourceMaterial() {
	}

	/** full constructor */
	public WxSourceMaterial(String materialtype, String mediaId,
			String updateTime, String item, String name, String ts, Short dr) {
		this.materialtype = materialtype;
		this.mediaId = mediaId;
		this.updateTime = updateTime;
		this.item = item;
		this.name = name;
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

	@Column(name = "materialtype")
	public String getMaterialtype() {
		return this.materialtype;
	}

	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}

	@Column(name = "media_id", length = 200)
	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Column(name = "update_time", length = 100)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "item")
	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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