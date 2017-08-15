package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerOrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_order_detail")
public class CustomerOrderDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkOrderDetail;
	private Long pkOrder;
	private String detailtype;
	private Long pkDetail;
	private Integer detailcount;
	private String detailname;
	private Double detaildiscount;
	private Double detailmoney;
	private String detailnote;
	private String fairername;
	private Long pkFairer;
	private Double fairermoney;
	private String awardsplit;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerOrderDetail() {
	}

	/** full constructor */
	public CustomerOrderDetail(Long pkOrder, String detailtype, Long pkDetail,
			Integer detailcount, String detailname, Double detaildiscount,
			Double detailmoney, String detailnote, String fairername,
			Long pkFairer, Double fairermoney, String awardsplit, String ts,
			Short dr) {
		this.pkOrder = pkOrder;
		this.detailtype = detailtype;
		this.pkDetail = pkDetail;
		this.detailcount = detailcount;
		this.detailname = detailname;
		this.detaildiscount = detaildiscount;
		this.detailmoney = detailmoney;
		this.detailnote = detailnote;
		this.fairername = fairername;
		this.pkFairer = pkFairer;
		this.fairermoney = fairermoney;
		this.awardsplit = awardsplit;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_order_detail", unique = true, nullable = false)
	public Long getPkOrderDetail() {
		return this.pkOrderDetail;
	}

	public void setPkOrderDetail(Long pkOrderDetail) {
		this.pkOrderDetail = pkOrderDetail;
	}

	@Column(name = "pk_order")
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Column(name = "detailtype", length = 50)
	public String getDetailtype() {
		return this.detailtype;
	}

	public void setDetailtype(String detailtype) {
		this.detailtype = detailtype;
	}

	@Column(name = "pk_detail")
	public Long getPkDetail() {
		return this.pkDetail;
	}

	public void setPkDetail(Long pkDetail) {
		this.pkDetail = pkDetail;
	}

	@Column(name = "detailcount")
	public Integer getDetailcount() {
		return this.detailcount;
	}

	public void setDetailcount(Integer detailcount) {
		this.detailcount = detailcount;
	}

	@Column(name = "detailname")
	public String getDetailname() {
		return this.detailname;
	}

	public void setDetailname(String detailname) {
		this.detailname = detailname;
	}

	@Column(name = "detaildiscount", precision = 20)
	public Double getDetaildiscount() {
		return this.detaildiscount;
	}

	public void setDetaildiscount(Double detaildiscount) {
		this.detaildiscount = detaildiscount;
	}

	@Column(name = "detailmoney", precision = 20)
	public Double getDetailmoney() {
		return this.detailmoney;
	}

	public void setDetailmoney(Double detailmoney) {
		this.detailmoney = detailmoney;
	}

	@Column(name = "detailnote")
	public String getDetailnote() {
		return this.detailnote;
	}

	public void setDetailnote(String detailnote) {
		this.detailnote = detailnote;
	}

	@Column(name = "fairername", length = 30)
	public String getFairername() {
		return this.fairername;
	}

	public void setFairername(String fairername) {
		this.fairername = fairername;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "fairermoney", precision = 20)
	public Double getFairermoney() {
		return this.fairermoney;
	}

	public void setFairermoney(Double fairermoney) {
		this.fairermoney = fairermoney;
	}

	@Column(name = "awardsplit")
	public String getAwardsplit() {
		return this.awardsplit;
	}

	public void setAwardsplit(String awardsplit) {
		this.awardsplit = awardsplit;
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