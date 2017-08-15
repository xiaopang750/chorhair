package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_group")
public class ShopGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkShopGroup;
	private Long pkShop;
	private String fairtype;
	private String grouptype;
	private String groupname;
	private String operatetime;
	private String operatorname;
	private Long operatorid;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopGroup() {
	}

	/** full constructor */
	public ShopGroup(Long pkShop, String fairtype, String grouptype,
			String groupname, String operatetime, String operatorname,
			Long operatorid, String ts, Short dr) {
		this.pkShop = pkShop;
		this.fairtype = fairtype;
		this.grouptype = grouptype;
		this.groupname = groupname;
		this.operatetime = operatetime;
		this.operatorname = operatorname;
		this.operatorid = operatorid;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_shop_group", unique = true, nullable = false)
	public Long getPkShopGroup() {
		return this.pkShopGroup;
	}

	public void setPkShopGroup(Long pkShopGroup) {
		this.pkShopGroup = pkShopGroup;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "fairtype", length = 50)
	public String getFairtype() {
		return this.fairtype;
	}

	public void setFairtype(String fairtype) {
		this.fairtype = fairtype;
	}

	@Column(name = "grouptype", length = 50)
	public String getGrouptype() {
		return this.grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	@Column(name = "groupname")
	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "operatetime", length = 19)
	public String getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "operatorname", length = 50)
	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	@Column(name = "operatorid")
	public Long getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
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