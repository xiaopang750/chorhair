package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlatProductTrace entity
 */
@Entity
@Table(name = "plat_product_trace")
public class PlatProductTrace implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkProductTrace;
	private Long pkProduct;
	private String operatetime;
	private String operatecontent;
	private Long operatorid;
	private String operator;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public PlatProductTrace() {
	}

	/** full constructor */
	public PlatProductTrace(Long pkProduct, String operatetime,
			String operatecontent, Long operatorid,String operator,
			String ts, Short dr) {
		this.pkProduct = pkProduct;
		this.operatetime = operatetime;
		this.operatecontent = operatecontent;
		this.operatorid = operatorid;
		this.operator = operator;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors

	@Id
	@GeneratedValue
	@Column(name = "pk_product_trace", unique = true, nullable = false)
	public Long getPkProductTrace() {
		return pkProductTrace;
	}

	public void setPkProductTrace(Long pkProductTrace) {
		this.pkProductTrace = pkProductTrace;
	}
	
	@Column(name = "pk_product")
	public Long getPkProduct() {
		return pkProduct;
	}
	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
	}

	@Column(name = "operatetime", length = 19)
	public String getOperatetime() {
		return this.operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "operatecontent")
	public String getOperatecontent() {
		return this.operatecontent;
	}

	public void setOperatecontent(String operatecontent) {
		this.operatecontent = operatecontent;
	}
	@Column(name = "operatorid")
	public Long getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	@Column(name = "operator")
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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