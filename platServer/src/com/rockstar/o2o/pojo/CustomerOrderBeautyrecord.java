package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerOrderBeautyrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_order_beautyrecord")
public class CustomerOrderBeautyrecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkBeautyrecord;
	private Long pkOrder;
	private Long pkCustomer;
	private Long pkFairer;
	private String pictureurl;
	private String pictureshorturl;
	private String picturenote;
	private Long operator;
	private String operatorgroup;
	private Integer praisecount;
	private String commentcount;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerOrderBeautyrecord() {
	}

	/** full constructor */
	public CustomerOrderBeautyrecord(Long pkOrder, Long pkCustomer,
			Long pkFairer, String pictureurl, String pictureshorturl,
			String picturenote, Long operator, String operatorgroup,
			Integer praisecount, String commentcount, String ts, Short dr) {
		this.pkOrder = pkOrder;
		this.pkCustomer = pkCustomer;
		this.pkFairer = pkFairer;
		this.pictureurl = pictureurl;
		this.pictureshorturl = pictureshorturl;
		this.picturenote = picturenote;
		this.operator = operator;
		this.operatorgroup = operatorgroup;
		this.praisecount = praisecount;
		this.commentcount = commentcount;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_beautyrecord", unique = true, nullable = false)
	public Long getPkBeautyrecord() {
		return this.pkBeautyrecord;
	}

	public void setPkBeautyrecord(Long pkBeautyrecord) {
		this.pkBeautyrecord = pkBeautyrecord;
	}

	@Column(name = "pk_order")
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Column(name = "pk_customer")
	public Long getPkCustomer() {
		return this.pkCustomer;
	}

	public void setPkCustomer(Long pkCustomer) {
		this.pkCustomer = pkCustomer;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "pictureurl")
	public String getPictureurl() {
		return this.pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	@Column(name = "pictureshorturl")
	public String getPictureshorturl() {
		return this.pictureshorturl;
	}

	public void setPictureshorturl(String pictureshorturl) {
		this.pictureshorturl = pictureshorturl;
	}

	@Column(name = "picturenote")
	public String getPicturenote() {
		return this.picturenote;
	}

	public void setPicturenote(String picturenote) {
		this.picturenote = picturenote;
	}

	@Column(name = "operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Column(name = "operatorgroup", length = 20)
	public String getOperatorgroup() {
		return this.operatorgroup;
	}

	public void setOperatorgroup(String operatorgroup) {
		this.operatorgroup = operatorgroup;
	}

	@Column(name = "praisecount")
	public Integer getPraisecount() {
		return this.praisecount;
	}

	public void setPraisecount(Integer praisecount) {
		this.praisecount = praisecount;
	}

	@Column(name = "commentcount")
	public String getCommentcount() {
		return this.commentcount;
	}

	public void setCommentcount(String commentcount) {
		this.commentcount = commentcount;
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