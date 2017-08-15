package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product_info")
public class ProductInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkProduct;
	private String brand;
	private String series;
	private String itemno;
	private String capacity;
	private String producttype;
	private String productname;
	private Double productprice;
	private String unit;
	private Integer productnum;
	private String note;
	private Long operatorid;
	private String operatetime;
	private String operatorname;
	private String py;
	private String shortpy;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ProductInfo() {
	}

	/** full constructor */
	public ProductInfo(String brand, String series, String itemno,
			String capacity, String producttype, String productname,
			Double productprice, String unit,Integer productnum, String note, Long operatorid,
			String operatetime, String operatorname, String py,
			String shortpy, String ts, Short dr) {
		this.brand = brand;
		this.series = series;
		this.itemno = itemno;
		this.capacity = capacity;
		this.producttype = producttype;
		this.productname = productname;
		this.productprice = productprice;
		this.unit = unit;
		this.productnum = productnum;
		this.note = note;
		this.operatorid = operatorid;
		this.operatetime = operatetime;
		this.operatorname = operatorname;
		this.py = py;
		this.shortpy = shortpy;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_product", unique = true, nullable = false)
	public Long getPkProduct() {
		return this.pkProduct;
	}

	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
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

	@Column(name = "productprice", precision = 30)
	public Double getProductprice() {
		return this.productprice;
	}

	public void setProductprice(Double productprice) {
		this.productprice = productprice;
	}

	@Column(name = "unit", length = 50)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Column(name = "productnum")
	public Integer getProductnum() {
		return productnum;
	}

	public void setProductnum(Integer productnum) {
		this.productnum = productnum;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

	@Column(name = "operatorname", length = 50)
	public String getOperatorname() {
		return this.operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
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