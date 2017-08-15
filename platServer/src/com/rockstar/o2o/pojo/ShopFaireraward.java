package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopFaireraward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_faireraward")
public class ShopFaireraward implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkFaireraward;
	private Long pkShop;
	private Long pkKey;
	private String awardtype;
	private String awardname;
	private String israte;
	private Double awardmoney;
	private String operatename;
	private Long operatorid;
	private String operatetime;
	private String operatorfrom;
	private String isvalidate;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopFaireraward() {
	}

	/** full constructor */
	public ShopFaireraward(Long pkShop, Long pkKey, String awardtype,
			String awardname, String israte, Double awardmoney,
			String operatename, Long operatorid, String operatetime,String operatorfrom,
			String isvalidate, String ts, Short dr) {
		this.pkShop = pkShop;
		this.pkKey = pkKey;
		this.awardtype = awardtype;
		this.awardname = awardname;
		this.israte = israte;
		this.awardmoney = awardmoney;
		this.operatename = operatename;
		this.operatorid = operatorid;
		this.operatetime = operatetime;
		this.operatorfrom = operatorfrom;
		this.isvalidate = isvalidate;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_faireraward", unique = true, nullable = false)
	public Long getPkFaireraward() {
		return this.pkFaireraward;
	}

	public void setPkFaireraward(Long pkFaireraward) {
		this.pkFaireraward = pkFaireraward;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "pk_key")
	public Long getPkKey() {
		return this.pkKey;
	}

	public void setPkKey(Long pkKey) {
		this.pkKey = pkKey;
	}

	@Column(name = "awardtype", length = 50)
	public String getAwardtype() {
		return this.awardtype;
	}

	public void setAwardtype(String awardtype) {
		this.awardtype = awardtype;
	}

	@Column(name = "awardname", length = 100)
	public String getAwardname() {
		return this.awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	@Column(name = "israte", length = 1)
	public String getIsrate() {
		return this.israte;
	}

	public void setIsrate(String israte) {
		this.israte = israte;
	}

	@Column(name = "awardmoney", precision = 20)
	public Double getAwardmoney() {
		return this.awardmoney;
	}

	public void setAwardmoney(Double awardmoney) {
		this.awardmoney = awardmoney;
	}

	@Column(name = "operatename", length = 50)
	public String getOperatename() {
		return this.operatename;
	}

	public void setOperatename(String operatename) {
		this.operatename = operatename;
	}

	@Column(name = "operatorid")
	public Long getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	@Column(name = "operatetime", length = 19)
	public String getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	
	@Column(name = "operatorfrom")
	public String getOperatorfrom() {
		return operatorfrom;
	}

	public void setOperatorfrom(String operatorfrom) {
		this.operatorfrom = operatorfrom;
	}
	@Column(name = "isvalidate", length = 1)
	public String getIsvalidate() {
		return this.isvalidate;
	}

	public void setIsvalidate(String isvalidate) {
		this.isvalidate = isvalidate;
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