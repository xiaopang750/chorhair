package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlatformComboProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "platform_combo_product")
public class PlatformComboProduct implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkComboProduct;
	private Long pkCombo;
	private Long pkProduct;
	private String productname;
	private Double productprice;
	private String productunit;
	private Integer productcount;
	private String productcontent;
	private String productnote;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public PlatformComboProduct() {
	}

	/** full constructor */
	public PlatformComboProduct(Long pkCombo, Long pkProduct,
			String productname, Double productprice, Integer productcount,String productunit,
			String productcontent, String productnote, String ts, Short dr) {
		this.pkCombo = pkCombo;
		this.pkProduct = pkProduct;
		this.productname = productname;
		this.productprice = productprice;
		this.productcount = productcount;
		this.productcontent = productcontent;
		this.productnote = productnote;
		this.ts = ts;
		this.dr = dr;
		this.productunit = productunit;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_combo_product", unique = true, nullable = false)
	public Long getPkComboProduct() {
		return this.pkComboProduct;
	}

	public void setPkComboProduct(Long pkComboProduct) {
		this.pkComboProduct = pkComboProduct;
	}

	@Column(name = "pk_combo")
	public Long getPkCombo() {
		return this.pkCombo;
	}

	public void setPkCombo(Long pkCombo) {
		this.pkCombo = pkCombo;
	}

	@Column(name = "pk_product")
	public Long getPkProduct() {
		return this.pkProduct;
	}

	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
	}

	@Column(name = "productname", length = 100)
	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Column(name = "productprice", precision = 20)
	public Double getProductprice() {
		return this.productprice;
	}

	public void setProductprice(Double productprice) {
		this.productprice = productprice;
	}

	@Column(name = "productcount")
	public Integer getProductcount() {
		return this.productcount;
	}

	public void setProductcount(Integer productcount) {
		this.productcount = productcount;
	}

	@Column(name = "productcontent")
	public String getProductcontent() {
		return this.productcontent;
	}

	public void setProductcontent(String productcontent) {
		this.productcontent = productcontent;
	}

	@Column(name = "productnote")
	public String getProductnote() {
		return this.productnote;
	}

	public void setProductnote(String productnote) {
		this.productnote = productnote;
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

	@Column(name = "productunit")
	public String getProductunit() {
		return productunit;
	}

	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}

	
}