package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerOwnaward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_ownaward")
public class CustomerOwnaward implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCustomerOwnaward;
	private Long pkCustomeraward;
	private Long pkCustomer;
	private Long pkOrder;
	private String awardname;
	private String awardvalue;
	private String awardstatus;
	private String owntime;
	private String begintime;
	private String endtime;
	private String awardurl;
	private String awardshorturl;
	private String isshare;
	private Long pkFrom;
	private String awardfrom;
	private String limits;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerOwnaward() {
	}

	/** full constructor */
	public CustomerOwnaward(Long pkCustomeraward, Long pkCustomer,
			Long pkOrder, String awardname, String awardvalue,
			String awardstatus, String owntime, String begintime,
			String endtime, String awardurl, String awardshorturl,
			String isshare, Long pkFrom, String awardfrom, String limits,
			String ts, Short dr) {
		this.pkCustomeraward = pkCustomeraward;
		this.pkCustomer = pkCustomer;
		this.pkOrder = pkOrder;
		this.awardname = awardname;
		this.awardvalue = awardvalue;
		this.awardstatus = awardstatus;
		this.owntime = owntime;
		this.begintime = begintime;
		this.endtime = endtime;
		this.awardurl = awardurl;
		this.awardshorturl = awardshorturl;
		this.isshare = isshare;
		this.pkFrom = pkFrom;
		this.awardfrom = awardfrom;
		this.limits = limits;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_customer_ownaward", unique = true, nullable = false)
	public Long getPkCustomerOwnaward() {
		return this.pkCustomerOwnaward;
	}

	public void setPkCustomerOwnaward(Long pkCustomerOwnaward) {
		this.pkCustomerOwnaward = pkCustomerOwnaward;
	}

	@Column(name = "pk_customeraward")
	public Long getPkCustomeraward() {
		return this.pkCustomeraward;
	}

	public void setPkCustomeraward(Long pkCustomeraward) {
		this.pkCustomeraward = pkCustomeraward;
	}

	@Column(name = "pk_customer")
	public Long getPkCustomer() {
		return this.pkCustomer;
	}

	public void setPkCustomer(Long pkCustomer) {
		this.pkCustomer = pkCustomer;
	}

	@Column(name = "pk_order")
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Column(name = "awardname", length = 200)
	public String getAwardname() {
		return this.awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	@Column(name = "awardvalue", length = 50)
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

	@Column(name = "owntime", length = 19)
	public String getOwntime() {
		return this.owntime;
	}

	public void setOwntime(String owntime) {
		this.owntime = owntime;
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

	@Column(name = "pk_from")
	public Long getPkFrom() {
		return this.pkFrom;
	}

	public void setPkFrom(Long pkFrom) {
		this.pkFrom = pkFrom;
	}

	@Column(name = "awardfrom", length = 30)
	public String getAwardfrom() {
		return this.awardfrom;
	}

	public void setAwardfrom(String awardfrom) {
		this.awardfrom = awardfrom;
	}

	@Column(name = "limits")
	public String getLimits() {
		return this.limits;
	}

	public void setLimits(String limits) {
		this.limits = limits;
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