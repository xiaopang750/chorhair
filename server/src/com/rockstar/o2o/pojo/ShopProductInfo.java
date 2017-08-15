package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopProductInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_product_info")
public class ShopProductInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkShopProdcut;
	private Long pkProduct;
	private Long pkShop;
	private String brand;
	private String series;
	private String itemno;
	private String capacity;
	private String producttype;
	private String productname;
	private Integer productnum;
	private Double productprice;
	private String productstatus;
	private Double shopprice;
	private String productbarcode;
	private String unit;
	private String note;
	private String principal;
	private Long operatorid;
	private String operatorname;
	private String operatetime;
	private String py;
	private String shortpy;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopProductInfo() {
	}

	/** full constructor */
	public ShopProductInfo(Long pkProduct, Long pkShop, String brand,
			String series, String itemno, String capacity, String producttype,
			String productname, Integer productnum, Double productprice,
			String productstatus, Double shopprice, String productbarcode,
			String unit, String note, String principal, Long operatorid,
			String operatorname, String operatetime, String py, String shortpy,
			String ts, Short dr) {
		this.pkProduct = pkProduct;
		this.pkShop = pkShop;
		this.brand = brand;
		this.series = series;
		this.itemno = itemno;
		this.capacity = capacity;
		this.producttype = producttype;
		this.productname = productname;
		this.productnum = productnum;
		this.productprice = productprice;
		this.productstatus = productstatus;
		this.shopprice = shopprice;
		this.productbarcode = productbarcode;
		this.unit = unit;
		this.note = note;
		this.principal = principal;
		this.operatorid = operatorid;
		this.operatorname = operatorname;
		this.operatetime = operatetime;
		this.py = py;
		this.shortpy = shortpy;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_shop_prodcut", unique = true, nullable = false)
	public Long getPkShopProdcut() {
		return this.pkShopProdcut;
	}

	public void setPkShopProdcut(Long pkShopProdcut) {
		this.pkShopProdcut = pkShopProdcut;
	}

	@Column(name = "pk_product")
	public Long getPkProduct() {
		return this.pkProduct;
	}

	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "brand", length = 50)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "series")
	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "itemno", length = 50)
	public String getItemno() {
		return this.itemno;
	}

	public void setItemno(String itemno) {
		this.itemno = itemno;
	}

	@Column(name = "capacity", length = 50)
	public String getCapacity() {
		return this.capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@Column(name = "producttype", length = 50)
	public String getProducttype() {
		return this.producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	@Column(name = "productname", length = 100)
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

	@Column(name = "productstatus", length = 50)
	public String getProductstatus() {
		return this.productstatus;
	}

	public void setProductstatus(String productstatus) {
		this.productstatus = productstatus;
	}

	@Column(name = "shopprice", precision = 20)
	public Double getShopprice() {
		return this.shopprice;
	}

	public void setShopprice(Double shopprice) {
		this.shopprice = shopprice;
	}

	@Column(name = "productbarcode", length = 100)
	public String getProductbarcode() {
		return this.productbarcode;
	}

	public void setProductbarcode(String productbarcode) {
		this.productbarcode = productbarcode;
	}

	@Column(name = "unit", length = 50)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "principal")
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "operatorid")
	public Long getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	@Column(name = "operatorname", length = 50)
	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	@Column(name = "operatetime", length = 19)
	public String getOperatetime() {
		return this.operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name = "py")
	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	@Column(name = "shortpy")
	public String getShortpy() {
		return this.shortpy;
	}

	public void setShortpy(String shortpy) {
		this.shortpy = shortpy;
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