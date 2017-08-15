package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContentManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "content_manage")
public class ContentManage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkContent;
	private Long pkShop;
	private String firstpage;
	private Integer width;
	private Integer height;
	private String contenttype;
	private Long pkType;
	private String contenttopic;
	private String contentbody;
	private String contenturl;
	private String contentstatus;
	private String contentnote;
	private Integer praisecount;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ContentManage() {
	}

	/** full constructor */
	public ContentManage(Long pkShop, String firstpage, Integer width,
			Integer height, String contenttype, Long pkType,
			String contenttopic, String contentbody, String contenturl,
			String contentstatus, String contentnote, Integer praisecount,
			String ts, Short dr) {
		this.pkShop = pkShop;
		this.firstpage = firstpage;
		this.width = width;
		this.height = height;
		this.contenttype = contenttype;
		this.pkType = pkType;
		this.contenttopic = contenttopic;
		this.contentbody = contentbody;
		this.contenturl = contenturl;
		this.contentstatus = contentstatus;
		this.contentnote = contentnote;
		this.praisecount = praisecount;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_content", unique = true, nullable = false)
	public Long getPkContent() {
		return this.pkContent;
	}

	public void setPkContent(Long pkContent) {
		this.pkContent = pkContent;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "firstpage")
	public String getFirstpage() {
		return this.firstpage;
	}

	public void setFirstpage(String firstpage) {
		this.firstpage = firstpage;
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

	@Column(name = "contenttype", length = 30)
	public String getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	@Column(name = "pk_type")
	public Long getPkType() {
		return this.pkType;
	}

	public void setPkType(Long pkType) {
		this.pkType = pkType;
	}

	@Column(name = "contenttopic")
	public String getContenttopic() {
		return this.contenttopic;
	}

	public void setContenttopic(String contenttopic) {
		this.contenttopic = contenttopic;
	}

	@Column(name = "contentbody")
	public String getContentbody() {
		return this.contentbody;
	}

	public void setContentbody(String contentbody) {
		this.contentbody = contentbody;
	}

	@Column(name = "contenturl")
	public String getContenturl() {
		return this.contenturl;
	}

	public void setContenturl(String contenturl) {
		this.contenturl = contenturl;
	}

	@Column(name = "contentstatus", length = 30)
	public String getContentstatus() {
		return this.contentstatus;
	}

	public void setContentstatus(String contentstatus) {
		this.contentstatus = contentstatus;
	}

	@Column(name = "contentnote")
	public String getContentnote() {
		return this.contentnote;
	}

	public void setContentnote(String contentnote) {
		this.contentnote = contentnote;
	}

	@Column(name = "praisecount")
	public Integer getPraisecount() {
		return this.praisecount;
	}

	public void setPraisecount(Integer praisecount) {
		this.praisecount = praisecount;
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