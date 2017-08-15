package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopProductBookB entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_product_book_b")
public class ShopProductBookB implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkProductBookB;
	private Long pkProductBook;
	private Long pkProduct;
	private Long pkSupplier;
	private String productname;
	private Integer productnum;
	private Integer realnum;
	private String productunit;
	private String productcapacity;
	private Double productprice;
	private String productbarcode;
	private Integer approvenum;
	private Long approvid;
	private String note;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopProductBookB() {
	}

	/** full constructor */
	public ShopProductBookB(Long pkProductBook, Long pkProduct,
			Long pkSupplier, String productname, Integer productnum,
			Integer realnum, String productunit, String productcapacity,
			Double productprice, String productbarcode, Integer approvenum,
			Long approvid, String note, String ts, Short dr) {
		this.pkProductBook = pkProductBook;
		this.pkProduct = pkProduct;
		this.pkSupplier = pkSupplier;
		this.productname = productname;
		this.productnum = productnum;
		this.realnum = realnum;
		this.productunit = productunit;
		this.productcapacity = productcapacity;
		this.productprice = productprice;
		this.productbarcode = productbarcode;
		this.approvenum = approvenum;
		this.approvid = approvid;
		this.note = note;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_product_book_b", unique = true, nullable = false)
	public Long getPkProductBookB() {
		return this.pkProductBookB;
	}

	public void setPkProductBookB(Long pkProductBookB) {
		this.pkProductBookB = pkProductBookB;
	}

	@Column(name = "pk_product_book")
	public Long getPkProductBook() {
		return this.pkProductBook;
	}

	public void setPkProductBook(Long pkProductBook) {
		this.pkProductBook = pkProductBook;
	}

	@Column(name = "pk_product")
	public Long getPkProduct() {
		return this.pkProduct;
	}

	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
	}

	@Column(name = "pk_supplier")
	public Long getPkSupplier() {
		return this.pkSupplier;
	}

	public void setPkSupplier(Long pkSupplier) {
		this.pkSupplier = pkSupplier;
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

	@Column(name = "realnum")
	public Integer getRealnum() {
		return this.realnum;
	}

	public void setRealnum(Integer realnum) {
		this.realnum = realnum;
	}

	@Column(name = "productunit", length = 50)
	public String getProductunit() {
		return this.productunit;
	}

	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}

	@Column(name = "productcapacity", length = 50)
	public String getProductcapacity() {
		return this.productcapacity;
	}

	public void setProductcapacity(String productcapacity) {
		this.productcapacity = productcapacity;
	}

	@Column(name = "productprice", precision = 20)
	public Double getProductprice() {
		return this.productprice;
	}

	public void setProductprice(Double productprice) {
		this.productprice = productprice;
	}

	@Column(name = "productbarcode")
	public String getProductbarcode() {
		return this.productbarcode;
	}

	public void setProductbarcode(String productbarcode) {
		this.productbarcode = productbarcode;
	}

	@Column(name = "approvenum")
	public Integer getApprovenum() {
		return this.approvenum;
	}

	public void setApprovenum(Integer approvenum) {
		this.approvenum = approvenum;
	}

	@Column(name = "approvid")
	public Long getApprovid() {
		return this.approvid;
	}

	public void setApprovid(Long approvid) {
		this.approvid = approvid;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "ts")
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