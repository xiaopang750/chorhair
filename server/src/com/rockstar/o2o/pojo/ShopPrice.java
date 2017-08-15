package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_price")
public class ShopPrice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkPrice;
	private Long pkShopPricegroup;
	private String fairtype;
	private Double price;
	private String servicerank;
	private Double fairermoney;
	private String israte;
	private String note;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopPrice() {
	}

	/** full constructor */
	public ShopPrice(Long pkShopPricegroup, String fairtype, Double price,
			String servicerank, Double fairermoney, String israte, String note,
			String ts, Short dr) {
		this.pkShopPricegroup = pkShopPricegroup;
		this.fairtype = fairtype;
		this.price = price;
		this.servicerank = servicerank;
		this.fairermoney = fairermoney;
		this.israte = israte;
		this.note = note;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_price", unique = true, nullable = false)
	public Long getPkPrice() {
		return this.pkPrice;
	}

	public void setPkPrice(Long pkPrice) {
		this.pkPrice = pkPrice;
	}

	@Column(name = "pk_shop_pricegroup")
	public Long getPkShopPricegroup() {
		return this.pkShopPricegroup;
	}

	public void setPkShopPricegroup(Long pkShopPricegroup) {
		this.pkShopPricegroup = pkShopPricegroup;
	}

	@Column(name = "fairtype", length = 30)
	public String getFairtype() {
		return this.fairtype;
	}

	public void setFairtype(String fairtype) {
		this.fairtype = fairtype;
	}

	@Column(name = "price", precision = 20)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "servicerank", length = 50)
	public String getServicerank() {
		return this.servicerank;
	}

	public void setServicerank(String servicerank) {
		this.servicerank = servicerank;
	}

	@Column(name = "fairermoney", precision = 20)
	public Double getFairermoney() {
		return this.fairermoney;
	}

	public void setFairermoney(Double fairermoney) {
		this.fairermoney = fairermoney;
	}

	@Column(name = "israte", length = 1)
	public String getIsrate() {
		return this.israte;
	}

	public void setIsrate(String israte) {
		this.israte = israte;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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