package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlatformCumboAddition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "platform_cumbo_addition")
public class PlatformCumboAddition implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkAddition;
	private Long pkCumbo;
	private String additionname;
	private Double additionmoney;
	private Double fairermoney;
	private Double fairerrate;
	private String israte;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public PlatformCumboAddition() {
	}

	/** full constructor */
	public PlatformCumboAddition(Long pkCumbo, String additionname,
			Double additionmoney, Double fairermoney, Double fairerrate,
			String israte, String ts, Short dr) {
		this.pkCumbo = pkCumbo;
		this.additionname = additionname;
		this.additionmoney = additionmoney;
		this.fairermoney = fairermoney;
		this.fairerrate = fairerrate;
		this.israte = israte;
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

	@Column(name = "pk_cumbo")
	public Long getPkCumbo() {
		return this.pkCumbo;
	}

	public void setPkCumbo(Long pkCumbo) {
		this.pkCumbo = pkCumbo;
	}

	@Column(name = "additionname", length = 30)
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

	@Column(name = "fairermoney", precision = 20)
	public Double getFairermoney() {
		return this.fairermoney;
	}

	public void setFairermoney(Double fairermoney) {
		this.fairermoney = fairermoney;
	}

	@Column(name = "fairerrate", precision = 20)
	public Double getFairerrate() {
		return this.fairerrate;
	}

	public void setFairerrate(Double fairerrate) {
		this.fairerrate = fairerrate;
	}

	@Column(name = "israte", length = 1)
	public String getIsrate() {
		return this.israte;
	}

	public void setIsrate(String israte) {
		this.israte = israte;
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