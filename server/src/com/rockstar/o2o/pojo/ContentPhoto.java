package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContentPhoto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "content_photo")
public class ContentPhoto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkPhoto;
	private Long pkContent;
	private String photoshorturl;
	private Integer shortwidth;
	private Integer shortheigth;
	private String photourl;
	private Integer width;
	private Integer height;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ContentPhoto() {
	}

	/** full constructor */
	public ContentPhoto(Long pkContent, String photoshorturl,
			Integer shortwidth, Integer shortheigth, String photourl,
			Integer width, Integer height, String ts, Short dr) {
		this.pkContent = pkContent;
		this.photoshorturl = photoshorturl;
		this.shortwidth = shortwidth;
		this.shortheigth = shortheigth;
		this.photourl = photourl;
		this.width = width;
		this.height = height;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_photo", unique = true, nullable = false)
	public Long getPkPhoto() {
		return this.pkPhoto;
	}

	public void setPkPhoto(Long pkPhoto) {
		this.pkPhoto = pkPhoto;
	}

	@Column(name = "pk_content")
	public Long getPkContent() {
		return this.pkContent;
	}

	public void setPkContent(Long pkContent) {
		this.pkContent = pkContent;
	}

	@Column(name = "photoshorturl")
	public String getPhotoshorturl() {
		return this.photoshorturl;
	}

	public void setPhotoshorturl(String photoshorturl) {
		this.photoshorturl = photoshorturl;
	}

	@Column(name = "shortwidth")
	public Integer getShortwidth() {
		return this.shortwidth;
	}

	public void setShortwidth(Integer shortwidth) {
		this.shortwidth = shortwidth;
	}

	@Column(name = "shortheigth")
	public Integer getShortheigth() {
		return this.shortheigth;
	}

	public void setShortheigth(Integer shortheigth) {
		this.shortheigth = shortheigth;
	}

	@Column(name = "photourl")
	public String getPhotourl() {
		return this.photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	@Column(name = "width")
	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Column(name = "height")
	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
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