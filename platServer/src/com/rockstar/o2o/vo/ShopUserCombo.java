package com.rockstar.o2o.vo;

import java.math.BigDecimal;
import java.math.BigInteger;


public class ShopUserCombo {
	private BigInteger pk_shop_combo;
	private BigInteger pk_combo;
	private BigInteger pk_shop;
	private String comboname;
	private String combotype;
	private BigDecimal currentmoney;
	private Integer  combocount;
	private BigInteger pk_customer_combo;
	private BigInteger  pk_customer;
	private String combobegintime;
	private String comboendtime;
	private Integer leftcount;
	private String intruduceman;
	
	public String getIntruduceman() {
		return intruduceman;
	}
	public void setIntruduceman(String intruduceman) {
		this.intruduceman = intruduceman;
	}
	public BigInteger getPk_shop_combo() {
		return pk_shop_combo;
	}
	public void setPk_shop_combo(BigInteger pk_shop_combo) {
		this.pk_shop_combo = pk_shop_combo;
	}
	public BigInteger getPk_combo() {
		return pk_combo;
	}
	public void setPk_combo(BigInteger pk_combo) {
		this.pk_combo = pk_combo;
	}
	public BigInteger getPk_shop() {
		return pk_shop;
	}
	public void setPk_shop(BigInteger pk_shop) {
		this.pk_shop = pk_shop;
	}
	public String getComboname() {
		return comboname;
	}
	public void setComboname(String comboname) {
		this.comboname = comboname;
	}
	public String getCombotype() {
		return combotype;
	}
	public void setCombotype(String combotype) {
		this.combotype = combotype;
	}
	public BigDecimal getCurrentmoney() {
		return currentmoney;
	}
	public void setCurrentmoney(BigDecimal currentmoney) {
		this.currentmoney = currentmoney;
	}
	public Integer getCombocount() {
		return combocount;
	}
	public void setCombocount(Integer combocount) {
		this.combocount = combocount;
	}
	public BigInteger getPk_customer_combo() {
		return pk_customer_combo;
	}
	public void setPk_customer_combo(BigInteger pk_customer_combo) {
		this.pk_customer_combo = pk_customer_combo;
	}
	public BigInteger getPk_customer() {
		return pk_customer;
	}
	public void setPk_customer(BigInteger pk_customer) {
		this.pk_customer = pk_customer;
	}
	public String getCombobegintime() {
		return combobegintime;
	}
	public void setCombobegintime(String combobegintime) {
		this.combobegintime = combobegintime;
	}
	public String getComboendtime() {
		return comboendtime;
	}
	public void setComboendtime(String comboendtime) {
		this.comboendtime = comboendtime;
	}
	public Integer getLeftcount() {
		return leftcount;
	}
	public void setLeftcount(Integer leftcount) {
		this.leftcount = leftcount;
	}
	
	
	
	
}
