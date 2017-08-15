package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopProductDeliveryB entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_product_delivery_b")
public class ShopProductDeliveryB implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkProductDeliveryB;
	private Long pkProductDelivery;
	private Long pkProduct;
	private String productname;
	private Integer productnum;
	private Double productprice;
	private String productunit;
	private String productbarcode;
	private String note;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopProductDeliveryB() {
	}

	/** full constructor */
	public ShopProductDeliveryB(Long pkProductDelivery, Long pkProduct,
			String productname, Integer productnum, Double productprice,
			String productunit, String productbarcode, String note, String ts,
			Short dr) {
		this.pkProductDelivery = pkProductDelivery;
		this.pkProduct = pkProduct;
		this.productname = productname;
		this.productnum = productnum;
		this.productprice = productprice;
		this.productunit = productunit;
		this.productbarcode = productbarcode;
		this.note = note;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_product_delivery_b", unique = true, nullable = false)
	public Long getPkProductDeliveryB() {
		return this.pkProductDeliveryB;
	}

	public void setPkProductDeliveryB(Long pkProductDeliveryB) {
		this.pkProductDeliveryB = pkProductDeliveryB;
	}

	@Column(name = "pk_product_delivery")
	public Long getPkProductDelivery() {
		return this.pkProductDelivery;
	}

	public void setPkProductDelivery(Long pkProductDelivery) {
		this.pkProductDelivery = pkProductDelivery;
	}

	@Column(name = "pk_product")
	public Long getPkProduct() {
		return this.pkProduct;
	}

	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
	}

	@Column(name = "productname")
	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Column(name = "productnum")
	public Integer getProductnum() {
		return this.productnum;
	}

	public void setProductnum(Integer productnum) {
		this.productnum = productnum;
	}

	@Column(name = "productprice", precision = 20)
	public Double getProductprice() {
		return this.productprice;
	}

	public void setProductprice(Double productprice) {
		this.productprice = productprice;
	}

	@Column(name = "productunit", length = 50)
	public String getProductunit() {
		return this.productunit;
	}

	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}

	@Column(name = "productbarcode")
	public String getProductbarcode() {
		return this.productbarcode;
	}

	public void setProductbarcode(String productbarcode) {
		this.productbarcode = productbarcode;
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