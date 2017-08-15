package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ShopComboEditrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "shop_combo_editrecord")
public class ShopComboEditrecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkComboEditrecord;
	private Long pkShopCombo;
	private Long pkShop;
	private String edittime;
	private String editor;
	private String editcontent;
	private Double oldprice;
	private Double newprice;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ShopComboEditrecord() {
	}

	/** full constructor */
	public ShopComboEditrecord(Long pkShopCombo, Long pkShop, String edittime,
			String editor, String editcontent, Double oldprice,
			Double newprice, String ts, Short dr) {
		this.pkShopCombo = pkShopCombo;
		this.pkShop = pkShop;
		this.edittime = edittime;
		this.editor = editor;
		this.editcontent = editcontent;
		this.oldprice = oldprice;
		this.newprice = newprice;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_combo_editrecord", unique = true, nullable = false)
	public Long getPkComboEditrecord() {
		return this.pkComboEditrecord;
	}

	public void setPkComboEditrecord(Long pkComboEditrecord) {
		this.pkComboEditrecord = pkComboEditrecord;
	}

	@Column(name = "pk_shop_combo")
	public Long getPkShopCombo() {
		return this.pkShopCombo;
	}

	public void setPkShopCombo(Long pkShopCombo) {
		this.pkShopCombo = pkShopCombo;
	}

	@Column(name = "pk_shop")
	public Long getPkShop() {
		return this.pkShop;
	}

	public void setPkShop(Long pkShop) {
		this.pkShop = pkShop;
	}

	@Column(name = "edittime", length = 19)
	public String getEdittime() {
		return this.edittime;
	}

	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}

	@Column(name = "editor", length = 30)
	public String getEditor() {
		return this.editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Column(name = "editcontent")
	public String getEditcontent() {
		return this.editcontent;
	}

	public void setEditcontent(String editcontent) {
		this.editcontent = editcontent;
	}

	@Column(name = "oldprice", precision = 20)
	public Double getOldprice() {
		return this.oldprice;
	}

	public void setOldprice(Double oldprice) {
		this.oldprice = oldprice;
	}

	@Column(name = "newprice", precision = 20)
	public Double getNewprice() {
		return this.newprice;
	}

	public void setNewprice(Double newprice) {
		this.newprice = newprice;
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