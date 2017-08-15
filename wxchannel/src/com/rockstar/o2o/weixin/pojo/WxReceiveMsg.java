package com.rockstar.o2o.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * WxReceiveMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_receive_msg")
@XStreamAlias("xml")
public class WxReceiveMsg implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields


	private Long pkReceiveMsg;
	
	@XStreamAlias("ToUserName")
	private String accountId;
	
	@XStreamAlias("FromUserName")
	private String wxId;
	
	@XStreamAlias("MsgType")
	private String msgType;
	
	@XStreamAlias("CreateTime")
	private String createtime;
	
	@XStreamAlias("Content")
	private String content;
	
	@XStreamAlias("MsgId")
	private String msgId;
	
	@XStreamAlias("PicUrl")
	private String picurl;
	
	@XStreamAlias("MediaId")
	private String mediaId;
	
	@XStreamAlias("Format")
	private String format;
	
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;
	
	@XStreamAlias("Location_X")
	private String locationX;
	
	@XStreamAlias("Location_Y")
	private String locationY;
	
	@XStreamAlias("Scale")
	private String scale;
	
	@XStreamAlias("Label")
	private String label;
	
	@XStreamAlias("Title")
	private String linkTitle;
	
	@XStreamAlias("Url")
	private String linkUrl;
	
	@XStreamAlias("Description")
	private String linkDescription;

	@XStreamAlias("Event")
	private String wxevent;
	
	@XStreamAlias("EventKey")
	private String eventkey;
	
	@XStreamAlias("Ticket")
	private String ticket;
	
	@XStreamAlias("Latitude")
	private String latitude;

	@XStreamAlias("Longitude")
	private String longitude;
	  
	@XStreamAlias("Recognition")
	private String recognition;
	
	@XStreamAlias("Precision")
	private String wxprecision;
	
	@XStreamAlias("ScanCodeInfo")
	private String scancodeinfo;
	
	@XStreamAlias("SendPicsInfo")
	private String sendpicsinfo;
	
	@XStreamAlias("SendLocationInfo")
	private String sendlocationinfo;
	
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public WxReceiveMsg() {
	}

	/** full constructor */
	public WxReceiveMsg(String accountId, String wxId, String msgType,
			String createtime, String content, String msgId, String picurl,
			String mediaId, String format, String thumbMediaId,
			String locationX, String locationY, String scale, String label,
			String linkTitle, String linkUrl, String linkDescription,
			String event, String eventkey, String ticket, String longitude,
			String latitude, String recognition, String precision, 
			String scancodeinfo, String sendpicsinfo, String sendlocationinfo,
			String ts,
			Short dr) {
		this.accountId = accountId;
		this.wxId = wxId;
		this.msgType = msgType;
		this.createtime = createtime;
		this.content = content;
		this.msgId = msgId;
		this.picurl = picurl;
		this.mediaId = mediaId;
		this.format = format;
		this.thumbMediaId = thumbMediaId;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		this.linkTitle = linkTitle;
		this.linkUrl = linkUrl;
		this.linkDescription = linkDescription;
		this.wxevent = event;
		this.eventkey = eventkey;
		this.ticket = ticket;
		this.longitude = longitude;
		this.latitude = latitude;
		this.recognition = recognition;
		this.wxprecision = precision;
		this.scancodeinfo = scancodeinfo;
		this.sendpicsinfo = sendpicsinfo;
		this.sendlocationinfo = sendlocationinfo;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_receive_msg", unique = true, nullable = false)
	public Long getPkReceiveMsg() {
		return this.pkReceiveMsg;
	}

	public void setPkReceiveMsg(Long pkReceiveMsg) {
		this.pkReceiveMsg = pkReceiveMsg;
	}

	@Column(name = "account_id")
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name = "wx_id")
	public String getWxId() {
		return this.wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	@Column(name = "msg_type", length = 50)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "createtime", length = 50)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "msg_id", length = 50)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Column(name = "picurl")
	public String getPicurl() {
		return this.picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	@Column(name = "media_id")
	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Column(name = "format")
	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(name = "thumb_media_id")
	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Column(name = "location_x", length = 100)
	public String getLocationX() {
		return this.locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	@Column(name = "location_y", length = 100)
	public String getLocationY() {
		return this.locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	@Column(name = "scale")
	public String getScale() {
		return this.scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@Column(name = "label")
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "link_title")
	public String getLinkTitle() {
		return this.linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	@Column(name = "link_url")
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "link_description")
	public String getLinkDescription() {
		return this.linkDescription;
	}

	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}

	@Column(name = "wxevent")
	public String getWxevent() {
		return this.wxevent;
	}

	public void setWxevent(String wxevent) {
		this.wxevent = wxevent;
	}

	@Column(name = "eventkey")
	public String getEventkey() {
		return this.eventkey;
	}

	public void setEventkey(String eventkey) {
		this.eventkey = eventkey;
	}

	@Column(name = "ticket")
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "recognition")
	public String getRecognition() {
		return this.recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	@Column(name = "wxprecision")
	public String getWxprecision() {
		return this.wxprecision;
	}

	public void setWxprecision(String wxprecision) {
		this.wxprecision = wxprecision;
	}

	@Column(name = "scancodeinfo")
	public String getScancodeinfo() {
		return scancodeinfo;
	}

	public void setScancodeinfo(String scancodeinfo) {
		this.scancodeinfo = scancodeinfo;
	}

	@Column(name = "sendpicsinfo")
	public String getSendpicsinfo() {
		return sendpicsinfo;
	}

	public void setSendpicsinfo(String sendpicsinfo) {
		this.sendpicsinfo = sendpicsinfo;
	}

	@Column(name = "sendlocationinfo")
	public String getSendlocationinfo() {
		return sendlocationinfo;
	}

	public void setSendlocationinfo(String sendlocationinfo) {
		this.sendlocationinfo = sendlocationinfo;
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