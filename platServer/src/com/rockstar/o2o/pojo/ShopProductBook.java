package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopProductBook entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "shop_product_book")
public class ShopProductBook implements java.io.Serializable {

	// Fields

	private Long pkProductBook;
	private Long pkShop;
	private String booktime;
	private String bookno;
	private String vbillstatus;
	private Double bookmoney;
	private String note;
	private Long operatorid;
	private String operatorname;
	private String operatortime;
	private Long vapprovid;
	private String approvename;
	private String approvetime;
	private String apprivenote;
	private String principal;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopProductBook() {
	}

	/** full constructor */
	public ShopProductBook(Long pkShop, String booktime, String bookno,
			String vbillstatus, Double bookmoney, String note, Long operatorid,
			String operatorname, String operatortime, Long vapprovid,
			String approvename, String approvetime, String apprivenote,
			String principal, String ts, Short dr) {
		this.pkShop = pkShop;
		this.booktime = booktime;
		this.bookno = bookno;
		this.vbillstatus = vbillstatus;
		this.bookmoney = bookmoney;
		this.note = note;
		this.operatorid = operatorid;
		this.operatorname = operatorname;
		this.operatortime = operatortime;
		this.vapprovid = vapprovid;
		this.approvename = approvename;
		this.approvetime = approvetime;
		this.apprivenote = apprivenote;
		this.principal = principal;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_product_book", unique = true, nullable = false)
	public Long getPkProductBook() {
		return this.pkProductBook;
	}

	public void setPkProductBook(Long pkProductBook) {
		this.pkProductBook = pkProductBook;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "booktime", length = 19)
	public String getBooktime() {
		return this.booktime;
	}

	public void setBooktime(String booktime) {
		this.booktime = booktime;
	}

	@Column(name = "bookno", length = 50)
	public String getBookno() {
		return this.bookno;
	}

	public void setBookno(String bookno) {
		this.bookno = bookno;
	}

	@Column(name = "vbillstatus", length = 10)
	public String getVbillstatus() {
		return this.vbillstatus;
	}

	public void setVbillstatus(String vbillstatus) {
		this.vbillstatus = vbillstatus;
	}

	@Column(name = "bookmoney", precision = 20)
	public Double getBookmoney() {
		return this.bookmoney;
	}

	public void setBookmoney(Double bookmoney) {
		this.bookmoney = bookmoney;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "operatorid")
	public Long getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	@Column(name = "operatorname")
	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	@Column(name = "operatortime", length = 19)
	public String getOperatortime() {
		return this.operatortime;
	}

	public void setOperatortime(String operatortime) {
		this.operatortime = operatortime;
	}

	@Column(name = "vapprovid")
	public Long getVapprovid() {
		return this.vapprovid;
	}

	public void setVapprovid(Long vapprovid) {
		this.vapprovid = vapprovid;
	}

	@Column(name = "approvename")
	public String getApprovename() {
		return this.approvename;
	}

	public void setApprovename(String approvename) {
		this.approvename = approvename;
	}

	@Column(name = "approvetime", length = 19)
	public String getApprovetime() {
		return this.approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}

	@Column(name = "apprivenote")
	public String getApprivenote() {
		return this.apprivenote;
	}

	public void setApprivenote(String apprivenote) {
		this.apprivenote = apprivenote;
	}

	@Column(name = "principal")
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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