package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerUseaward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_useaward")
public class CustomerUseaward implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCustomerUseaward;
	private Long pkCustomeraward;
	private Long pkCustomerOwnaward;
	private Long pkCustomer;
	private Long pkOrder;
	private String usetime;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerUseaward() {
	}

	/** full constructor */
	public CustomerUseaward(Long pkCustomeraward, Long pkCustomerOwnaward,
			Long pkCustomer, Long pkOrder, String usetime, String ts, Short dr) {
		this.pkCustomeraward = pkCustomeraward;
		this.pkCustomerOwnaward = pkCustomerOwnaward;
		this.pkCustomer = pkCustomer;
		this.pkOrder = pkOrder;
		this.usetime = usetime;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_customer_useaward", unique = true, nullable = false)
	public Long getPkCustomerUseaward() {
		return this.pkCustomerUseaward;
	}

	public void setPkCustomerUseaward(Long pkCustomerUseaward) {
		this.pkCustomerUseaward = pkCustomerUseaward;
	}

	@Column(name = "pk_customeraward")
	public Long getPkCustomeraward() {
		return this.pkCustomeraward;
	}

	public void setPkCustomeraward(Long pkCustomeraward) {
		this.pkCustomeraward = pkCustomeraward;
	}

	@Column(name = "pk_customer_ownaward")
	public Long getPkCustomerOwnaward() {
		return this.pkCustomerOwnaward;
	}

	public void setPkCustomerOwnaward(Long pkCustomerOwnaward) {
		this.pkCustomerOwnaward = pkCustomerOwnaward;
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

	@Column(name = "usetime", length = 19)
	public String getUsetime() {
		return this.usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
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