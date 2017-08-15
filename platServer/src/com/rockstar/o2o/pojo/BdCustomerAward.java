package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BdCustomerAward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bd_customer_award")
public class BdCustomerAward implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCustomeraward;
	private Long pkFrom;
	private Long pkShopGroup;
	private String fairtype;
	private String faittypename;
	private String awardfrom;
	private String awardtype;
	private String awardname;
	private String awardvalue;
	private String awardstatus;
	private String awardurl;
	private String awardshorturl;
	private String isshare;
	private String begintime;
	private String endtime;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public BdCustomerAward() {
	}

	/** full constructor */
	public BdCustomerAward(Long pkFrom, Long pkShopGroup, String fairtype,
			String faittypename, String awardfrom, String awardtype,
			String awardname, String awardvalue, String awardstatus,
			String awardurl, String awardshorturl, String isshare,
			String begintime, String endtime, String ts, Short dr) {
		this.pkFrom = pkFrom;
		this.pkShopGroup = pkShopGroup;
		this.fairtype = fairtype;
		this.faittypename = faittypename;
		this.awardfrom = awardfrom;
		this.awardtype = awardtype;
		this.awardname = awardname;
		this.awardvalue = awardvalue;
		this.awardstatus = awardstatus;
		this.awardurl = awardurl;
		this.awardshorturl = awardshorturl;
		this.isshare = isshare;
		this.begintime = begintime;
		this.endtime = endtime;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_customeraward", unique = true, nullable = false)
	public Long getPkCustomeraward() {
		return this.pkCustomeraward;
	}

	public void setPkCustomeraward(Long pkCustomeraward) {
		this.pkCustomeraward = pkCustomeraward;
	}

	@Column(name = "pk_from")
	public Long getPkFrom() {
		return this.pkFrom;
	}

	public void setPkFrom(Long pkFrom) {
		this.pkFrom = pkFrom;
	}

	@Column(name = "pk_shop_group")
	public Long getPkShopGroup() {
		return this.pkShopGroup;
	}

	public void setPkShopGroup(Long pkShopGroup) {
		this.pkShopGroup = pkShopGroup;
	}

	@Column(name = "fairtype", length = 20)
	public String getFairtype() {
		return this.fairtype;
	}

	public void setFairtype(String fairtype) {
		this.fairtype = fairtype;
	}

	@Column(name = "faittypename", length = 50)
	public String getFaittypename() {
		return this.faittypename;
	}

	public void setFaittypename(String faittypename) {
		this.faittypename = faittypename;
	}

	@Column(name = "awardfrom", length = 30)
	public String getAwardfrom() {
		return this.awardfrom;
	}

	public void setAwardfrom(String awardfrom) {
		this.awardfrom = awardfrom;
	}

	@Column(name = "awardtype", length = 50)
	public String getAwardtype() {
		return this.awardtype;
	}

	public void setAwardtype(String awardtype) {
		this.awardtype = awardtype;
	}

	@Column(name = "awardname", length = 100)
	public String getAwardname() {
		return this.awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	@Column(name = "awardvalue", length = 20)
	public String getAwardvalue() {
		return this.awardvalue;
	}

	public void setAwardvalue(String awardvalue) {
		this.awardvalue = awardvalue;
	}

	@Column(name = "awardstatus", length = 50)
	public String getAwardstatus() {
		return this.awardstatus;
	}

	public void setAwardstatus(String awardstatus) {
		this.awardstatus = awardstatus;
	}

	@Column(name = "awardurl", length = 200)
	public String getAwardurl() {
		return this.awardurl;
	}

	public void setAwardurl(String awardurl) {
		this.awardurl = awardurl;
	}

	@Column(name = "awardshorturl", length = 200)
	public String getAwardshorturl() {
		return this.awardshorturl;
	}

	public void setAwardshorturl(String awardshorturl) {
		this.awardshorturl = awardshorturl;
	}

	@Column(name = "isshare", length = 1)
	public String getIsshare() {
		return this.isshare;
	}

	public void setIsshare(String isshare) {
		this.isshare = isshare;
	}

	@Column(name = "begintime", length = 19)
	public String getBegintime() {
		return this.begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	@Column(name = "endtime", length = 19)
	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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