package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClientInfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "client_info")
public class ClientInfo implements java.io.Serializable {

	// Fields

	private Long pkClient;
	private Long pkUser;
	private String devicetype;
	private String devicefactory;
	private String deviceimei;
	private String deviceuuid;
	private String deviceimsi;
	private String devicescreenfbl;
	private String systemname;
	private String systemversion;
	private String systemlanguage;
	private String systemfactory;
	private String token;
	private String clientid;
	private String appid;
	private String appkey;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ClientInfo() {
	}

	/** full constructor */
	public ClientInfo(Long pkUser, String devicetype, String devicefactory,
			String deviceimei, String deviceuuid, String deviceimsi,
			String devicescreenfbl, String systemname, String systemversion,
			String systemlanguage, String systemfactory, String token,
			String clientid, String appid, String appkey, String ts, Short dr) {
		this.pkUser = pkUser;
		this.devicetype = devicetype;
		this.devicefactory = devicefactory;
		this.deviceimei = deviceimei;
		this.deviceuuid = deviceuuid;
		this.deviceimsi = deviceimsi;
		this.devicescreenfbl = devicescreenfbl;
		this.systemname = systemname;
		this.systemversion = systemversion;
		this.systemlanguage = systemlanguage;
		this.systemfactory = systemfactory;
		this.token = token;
		this.clientid = clientid;
		this.appid = appid;
		this.appkey = appkey;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_client", unique = true, nullable = false)
	public Long getPkClient() {
		return this.pkClient;
	}

	public void setPkClient(Long pkClient) {
		this.pkClient = pkClient;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "devicetype")
	public String getDevicetype() {
		return this.devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	@Column(name = "devicefactory")
	public String getDevicefactory() {
		return this.devicefactory;
	}

	public void setDevicefactory(String devicefactory) {
		this.devicefactory = devicefactory;
	}

	@Column(name = "deviceimei")
	public String getDeviceimei() {
		return this.deviceimei;
	}

	public void setDeviceimei(String deviceimei) {
		this.deviceimei = deviceimei;
	}

	@Column(name = "deviceuuid")
	public String getDeviceuuid() {
		return this.deviceuuid;
	}

	public void setDeviceuuid(String deviceuuid) {
		this.deviceuuid = deviceuuid;
	}

	@Column(name = "deviceimsi")
	public String getDeviceimsi() {
		return this.deviceimsi;
	}

	public void setDeviceimsi(String deviceimsi) {
		this.deviceimsi = deviceimsi;
	}

	@Column(name = "devicescreenfbl")
	public String getDevicescreenfbl() {
		return this.devicescreenfbl;
	}

	public void setDevicescreenfbl(String devicescreenfbl) {
		this.devicescreenfbl = devicescreenfbl;
	}

	@Column(name = "systemname")
	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	@Column(name = "systemversion")
	public String getSystemversion() {
		return this.systemversion;
	}

	public void setSystemversion(String systemversion) {
		this.systemversion = systemversion;
	}

	@Column(name = "systemlanguage")
	public String getSystemlanguage() {
		return this.systemlanguage;
	}

	public void setSystemlanguage(String systemlanguage) {
		this.systemlanguage = systemlanguage;
	}

	@Column(name = "systemfactory")
	public String getSystemfactory() {
		return this.systemfactory;
	}

	public void setSystemfactory(String systemfactory) {
		this.systemfactory = systemfactory;
	}

	@Column(name = "token")
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "clientid")
	public String getClientid() {
		return this.clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	@Column(name = "appid")
	public String getAppid() {
		return this.appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	@Column(name = "appkey")
	public String getAppkey() {
		return this.appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
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