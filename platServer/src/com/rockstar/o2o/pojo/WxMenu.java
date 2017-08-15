package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_menu")
public class WxMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkMenu;
	private Long pkParent;
	private String accountId;
	private String menuorder;
	private String menuname;
	private String menutype;
	private String menukey;
	private String keycontent;
	private String url;
	private String wxurl;
	private String menustatus;
	private String needBind;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxMenu() {
	}

	/** full constructor */
	public WxMenu(Long pkParent, String accountId, String menuorder,
			String menuname, String menutype, String menukey,
			String keycontent, String url, String wxurl, String menustatus,
			String needBind, String ts, Short dr) {
		this.pkParent = pkParent;
		this.accountId = accountId;
		this.menuorder = menuorder;
		this.menuname = menuname;
		this.menutype = menutype;
		this.menukey = menukey;
		this.keycontent = keycontent;
		this.url = url;
		this.wxurl = wxurl;
		this.menustatus = menustatus;
		this.needBind = needBind;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_menu", unique = true, nullable = false)
	public Long getPkMenu() {
		return this.pkMenu;
	}

	public void setPkMenu(Long pkMenu) {
		this.pkMenu = pkMenu;
	}

	@Column(name = "pk_parent")
	public Long getPkParent() {
		return this.pkParent;
	}

	public void setPkParent(Long pkParent) {
		this.pkParent = pkParent;
	}

	@Column(name = "account_id", length = 50)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "menuorder", length = 20)
	public String getMenuorder() {
		return this.menuorder;
	}

	public void setMenuorder(String menuorder) {
		this.menuorder = menuorder;
	}

	@Column(name = "menuname", length = 50)
	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	@Column(name = "menutype", length = 50)
	public String getMenutype() {
		return this.menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	@Column(name = "menukey", length = 50)
	public String getMenukey() {
		return this.menukey;
	}

	public void setMenukey(String menukey) {
		this.menukey = menukey;
	}

	@Column(name = "keycontent")
	public String getKeycontent() {
		return this.keycontent;
	}

	public void setKeycontent(String keycontent) {
		this.keycontent = keycontent;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "wxurl")
	public String getWxurl() {
		return this.wxurl;
	}

	public void setWxurl(String wxurl) {
		this.wxurl = wxurl;
	}

	@Column(name = "menustatus")
	public String getMenustatus() {
		return this.menustatus;
	}

	public void setMenustatus(String menustatus) {
		this.menustatus = menustatus;
	}

	@Column(name = "need_bind", length = 1)
	public String getNeedBind() {
		return this.needBind;
	}

	public void setNeedBind(String needBind) {
		this.needBind = needBind;
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