package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerComboUserecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_combo_userecord")
public class CustomerComboUserecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCumboRecord;
	private Long pkShopCumbo;
	private Long pkCustomerCombo;
	private Long pkOrder;
	private Long pkCustomer;
	private String cumboname;
	private String usetime;
	private String useman;
	private String usedetail;
	private String usermanphone;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerComboUserecord() {
	}

	/** full constructor */
	public CustomerComboUserecord(Long pkShopCumbo, Long pkCustomerCombo,
			Long pkOrder, Long pkCustomer, String cumboname, String usetime,
			String useman, String usedetail, String usermanphone, String ts,
			Short dr) {
		this.pkShopCumbo = pkShopCumbo;
		this.pkCustomerCombo = pkCustomerCombo;
		this.pkOrder = pkOrder;
		this.pkCustomer = pkCustomer;
		this.cumboname = cumboname;
		this.usetime = usetime;
		this.useman = useman;
		this.usedetail = usedetail;
		this.usermanphone = usermanphone;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_cumbo_record", unique = true, nullable = false)
	public Long getPkCumboRecord() {
		return this.pkCumboRecord;
	}

	public void setPkCumboRecord(Long pkCumboRecord) {
		this.pkCumboRecord = pkCumboRecord;
	}

	@Column(name = "pk_shop_cumbo")
	public Long getPkShopCumbo() {
		return this.pkShopCumbo;
	}

	public void setPkShopCumbo(Long pkShopCumbo) {
		this.pkShopCumbo = pkShopCumbo;
	}

	@Column(name = "pk_customer_combo")
	public Long getPkCustomerCombo() {
		return this.pkCustomerCombo;
	}

	public void setPkCustomerCombo(Long pkCustomerCombo) {
		this.pkCustomerCombo = pkCustomerCombo;
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

	@Column(name = "cumboname", length = 100)
	public String getCumboname() {
		return this.cumboname;
	}

	public void setCumboname(String cumboname) {
		this.cumboname = cumboname;
	}

	@Column(name = "usetime", length = 19)
	public String getUsetime() {
		return this.usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	@Column(name = "useman", length = 50)
	public String getUseman() {
		return this.useman;
	}

	public void setUseman(String useman) {
		this.useman = useman;
	}

	@Column(name = "usedetail")
	public String getUsedetail() {
		return this.usedetail;
	}

	public void setUsedetail(String usedetail) {
		this.usedetail = usedetail;
	}

	@Column(name = "usermanphone", length = 11)
	public String getUsermanphone() {
		return this.usermanphone;
	}

	public void setUsermanphone(String usermanphone) {
		this.usermanphone = usermanphone;
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