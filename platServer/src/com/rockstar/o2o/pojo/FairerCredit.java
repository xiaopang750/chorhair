package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FairerCredit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fairer_credit")
public class FairerCredit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCredit;
	private String createtype;
	private Long pkFairer;
	private Long pkOrder;
	private Double creditmoney;
	private String credittime;
	private String creditnote;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public FairerCredit() {
	}

	/** full constructor */
	public FairerCredit(String createtype, Long pkFairer, Long pkOrder,
			Double creditmoney, String credittime, String creditnote,
			String ts, Short dr) {
		this.createtype = createtype;
		this.pkFairer = pkFairer;
		this.pkOrder = pkOrder;
		this.creditmoney = creditmoney;
		this.credittime = credittime;
		this.creditnote = creditnote;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_credit", unique = true, nullable = false)
	public Long getPkCredit() {
		return this.pkCredit;
	}

	public void setPkCredit(Long pkCredit) {
		this.pkCredit = pkCredit;
	}

	@Column(name = "createtype", length = 30)
	public String getCreatetype() {
		return this.createtype;
	}

	public void setCreatetype(String createtype) {
		this.createtype = createtype;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "pk_order")
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Column(name = "creditmoney", precision = 20)
	public Double getCreditmoney() {
		return this.creditmoney;
	}

	public void setCreditmoney(Double creditmoney) {
		this.creditmoney = creditmoney;
	}

	@Column(name = "credittime", length = 19)
	public String getCredittime() {
		return this.credittime;
	}

	public void setCredittime(String credittime) {
		this.credittime = credittime;
	}

	@Column(name = "creditnote")
	public String getCreditnote() {
		return this.creditnote;
	}

	public void setCreditnote(String creditnote) {
		this.creditnote = creditnote;
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