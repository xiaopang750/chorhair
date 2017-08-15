package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopProductDelivery entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "shop_product_delivery")
public class ShopProductDelivery implements java.io.Serializable {

	// Fields

	private Long pkProductDelivery;
	private Long pkShop;
	private String deliveryno;
	private String deliverytime;
	private Double deliverymoney;
	private String vbillstatus;
	private String note;
	private Long operatorid;
	private String operatorname;
	private String operatetime;
	private Long vapprovorid;
	private String approvename;
	private String approvetime;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopProductDelivery() {
	}

	/** full constructor */
	public ShopProductDelivery(Long pkShop, String deliveryno,
			String deliverytime, Double deliverymoney, String vbillstatus,
			String note, Long operatorid, String operatorname,
			String operatetime, Long vapprovorid, String approvename,
			String approvetime, String ts, Short dr) {
		this.pkShop = pkShop;
		this.deliveryno = deliveryno;
		this.deliverytime = deliverytime;
		this.deliverymoney = deliverymoney;
		this.vbillstatus = vbillstatus;
		this.note = note;
		this.operatorid = operatorid;
		this.operatorname = operatorname;
		this.operatetime = operatetime;
		this.vapprovorid = vapprovorid;
		this.approvename = approvename;
		this.approvetime = approvetime;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_product_delivery", unique = true, nullable = false)
	public Long getPkProductDelivery() {
		return this.pkProductDelivery;
	}

	public void setPkProductDelivery(Long pkProductDelivery) {
		this.pkProductDelivery = pkProductDelivery;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "deliveryno", length = 50)
	public String getDeliveryno() {
		return this.deliveryno;
	}

	public void setDeliveryno(String deliveryno) {
		this.deliveryno = deliveryno;
	}

	@Column(name = "deliverytime", length = 19)
	public String getDeliverytime() {
		return this.deliverytime;
	}

	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}

	@Column(name = "deliverymoney", precision = 20)
	public Double getDeliverymoney() {
		return this.deliverymoney;
	}

	public void setDeliverymoney(Double deliverymoney) {
		this.deliverymoney = deliverymoney;
	}

	@Column(name = "vbillstatus", length = 50)
	public String getVbillstatus() {
		return this.vbillstatus;
	}

	public void setVbillstatus(String vbillstatus) {
		this.vbillstatus = vbillstatus;
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

	@Column(name = "operatetime", length = 0)
	public String getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "vapprovorid")
	public Long getVapprovorid() {
		return this.vapprovorid;
	}

	public void setVapprovorid(Long vapprovorid) {
		this.vapprovorid = vapprovorid;
	}

	@Column(name = "approvename")
	public String getApprovename() {
		return this.approvename;
	}

	public void setApprovename(String approvename) {
		this.approvename = approvename;
	}

	@Column(name = "approvetime", length = 0)
	public String getApprovetime() {
		return this.approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
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