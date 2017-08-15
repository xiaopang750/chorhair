package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopAddition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_addition")
public class ShopAddition implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkAddition;
	private Long pkAdditionGroup;
	private String additionname;
	private Double additionmoney;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopAddition() {
	}

	/** full constructor */
	public ShopAddition(Long pkAdditionGroup, String additionname,
			Double additionmoney, String ts, Short dr) {
		this.pkAdditionGroup = pkAdditionGroup;
		this.additionname = additionname;
		this.additionmoney = additionmoney;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_addition", unique = true, nullable = false)
	public Long getPkAddition() {
		return this.pkAddition;
	}

	public void setPkAddition(Long pkAddition) {
		this.pkAddition = pkAddition;
	}

	@Column(name = "pk_addition_group")
	public Long getPkAdditionGroup() {
		return this.pkAdditionGroup;
	}

	public void setPkAdditionGroup(Long pkAdditionGroup) {
		this.pkAdditionGroup = pkAdditionGroup;
	}

	@Column(name = "additionname", length = 50)
	public String getAdditionname() {
		return this.additionname;
	}

	public void setAdditionname(String additionname) {
		this.additionname = additionname;
	}

	@Column(name = "additionmoney", precision = 20)
	public Double getAdditionmoney() {
		return this.additionmoney;
	}

	public void setAdditionmoney(Double additionmoney) {
		this.additionmoney = additionmoney;
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