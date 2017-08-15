package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BdCustomerAwardLimit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bd_customer_award_limit")
public class BdCustomerAwardLimit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCustomerawardLimit;
	private Long pkCustomeraward;
	private Long pkCombo;
	private String combofrom;
	private String comboname;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public BdCustomerAwardLimit() {
	}

	/** full constructor */
	public BdCustomerAwardLimit(Long pkCustomeraward, Long pkCombo,
			String combofrom, String comboname, String ts, Short dr) {
		this.pkCustomeraward = pkCustomeraward;
		this.pkCombo = pkCombo;
		this.combofrom = combofrom;
		this.comboname = comboname;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_customeraward_limit", unique = true, nullable = false)
	public Long getPkCustomerawardLimit() {
		return this.pkCustomerawardLimit;
	}

	public void setPkCustomerawardLimit(Long pkCustomerawardLimit) {
		this.pkCustomerawardLimit = pkCustomerawardLimit;
	}

	@Column(name = "pk_customeraward")
	public Long getPkCustomeraward() {
		return this.pkCustomeraward;
	}

	public void setPkCustomeraward(Long pkCustomeraward) {
		this.pkCustomeraward = pkCustomeraward;
	}

	@Column(name = "pk_combo")
	public Long getPkCombo() {
		return this.pkCombo;
	}

	public void setPkCombo(Long pkCombo) {
		this.pkCombo = pkCombo;
	}

	@Column(name = "combofrom", length = 50)
	public String getCombofrom() {
		return this.combofrom;
	}

	public void setCombofrom(String combofrom) {
		this.combofrom = combofrom;
	}

	@Column(name = "comboname")
	public String getComboname() {
		return this.comboname;
	}

	public void setComboname(String comboname) {
		this.comboname = comboname;
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