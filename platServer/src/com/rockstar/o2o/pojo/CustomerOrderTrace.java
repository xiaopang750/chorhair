package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerOrderTrace entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_order_trace")
public class CustomerOrderTrace implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkTrace;
	private Long pkOrder;
	private String operatetime;
	private String operatecontent;
	private String operator;
	private String confirmer;
	private String confirmtime;
	private String confirmcontent;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerOrderTrace() {
	}

	/** full constructor */
	public CustomerOrderTrace(Long pkOrder, String operatetime,
			String operatecontent, String operator, String confirmer,
			String confirmtime, String confirmcontent, String ts, Short dr) {
		this.pkOrder = pkOrder;
		this.operatetime = operatetime;
		this.operatecontent = operatecontent;
		this.operator = operator;
		this.confirmer = confirmer;
		this.confirmtime = confirmtime;
		this.confirmcontent = confirmcontent;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_trace", unique = true, nullable = false)
	public Long getPkTrace() {
		return this.pkTrace;
	}

	public void setPkTrace(Long pkTrace) {
		this.pkTrace = pkTrace;
	}

	@Column(name = "pk_order")
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
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

	@Column(name = "operator", length = 20)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "confirmer", length = 20)
	public String getConfirmer() {
		return this.confirmer;
	}

	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
	}

	@Column(name = "confirmtime", length = 19)
	public String getConfirmtime() {
		return this.confirmtime;
	}

	public void setConfirmtime(String confirmtime) {
		this.confirmtime = confirmtime;
	}

	@Column(name = "confirmcontent")
	public String getConfirmcontent() {
		return this.confirmcontent;
	}

	public void setConfirmcontent(String confirmcontent) {
		this.confirmcontent = confirmcontent;
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