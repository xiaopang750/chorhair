package com.rockstar.o2o.weixin.conf;

import org.springframework.stereotype.Service;

@Service
public class SysConf
{
  private String baseAccessTokenUrl;
  private String baseUserinfoUrl;
  private String baseSendMsgUrl;
  private String baseCustomSendMsgUrl;
  private String baseQrcodeUrl;
  private String baseQrcodePicUrl;
  private String baseCreateMenuUrl;
  private String baseQueryMenuUrl;
  private String baseDeleteMenuUrl;
  private String baseSubscribeUserListUrl;
  private String baseOAuthUrl;
  private String baseOAuthTokenUrl;
  private String basetestLocalOAuthRedirectUrl;
  private String basebuildLocalOAuthRedirectUrl;
  private String baseDeliverNotifyUrl;
  private String baseShortUrl;
  private String baseUploadMediaUrl;//上传多媒体文件
  private String baseUploadNewsUrl;//上传图文
  private String baseSendNewsBylistUrl;//群发消息
  private String baseUploadVideoUrl;
  private String baseDownloadMediaUrl;//下载多媒体文件
  private String baseGetLongMaterialUrl;
  
  
  
  private String baseCarduploadlogoUrl;//卡券上传
  
  private String baseCardcolorlistUrl;//卡券颜色列表
  
  

  public String getBaseCardcolorlistUrl() {
	return baseCardcolorlistUrl;
}

public void setBaseCardcolorlistUrl(String baseCardcolorlistUrl) {
	this.baseCardcolorlistUrl = baseCardcolorlistUrl;
}

public String getBaseCarduploadlogoUrl() {
	return baseCarduploadlogoUrl;
}

public void setBaseCarduploadlogoUrl(String baseCarduploadlogoUrl) {
	this.baseCarduploadlogoUrl = baseCarduploadlogoUrl;
}

public String getBaseGetLongMaterialUrl() {
	return baseGetLongMaterialUrl;
}

public void setBaseGetLongMaterialUrl(String baseGetLongMaterialUrl) {
	this.baseGetLongMaterialUrl = baseGetLongMaterialUrl;
}

public String getBaseDownloadMediaUrl() {
	return baseDownloadMediaUrl;
}

public void setBaseDownloadMediaUrl(String baseDownloadMediaUrl) {
	this.baseDownloadMediaUrl = baseDownloadMediaUrl;
}

public String getBaseUploadNewsUrl() {
	return baseUploadNewsUrl;
}

public void setBaseUploadNewsUrl(String baseUploadNewsUrl) {
	this.baseUploadNewsUrl = baseUploadNewsUrl;
}

public String getBaseSendNewsBylistUrl() {
	return baseSendNewsBylistUrl;
}

public void setBaseSendNewsBylistUrl(String baseSendNewsBylistUrl) {
	this.baseSendNewsBylistUrl = baseSendNewsBylistUrl;
}

public String getBaseUploadMediaUrl() {
	return baseUploadMediaUrl;
}

public void setBaseUploadMediaUrl(String baseUploadMediaUrl) {
	this.baseUploadMediaUrl = baseUploadMediaUrl;
}

public String getBaseShortUrl() {
	return baseShortUrl;
}

public void setBaseShortUrl(String baseShortUrl) {
	this.baseShortUrl = baseShortUrl;
}

public String getBaseAccessTokenUrl()
  {
    return this.baseAccessTokenUrl;
  }

  public void setBaseAccessTokenUrl(String baseAccessTokenUrl) {
    this.baseAccessTokenUrl = baseAccessTokenUrl;
  }

  public String getBaseUserinfoUrl() {
    return this.baseUserinfoUrl;
  }

  public void setBaseUserinfoUrl(String baseUserinfoUrl) {
    this.baseUserinfoUrl = baseUserinfoUrl;
  }

  public String getBaseSendMsgUrl() {
    return this.baseSendMsgUrl;
  }

  public void setBaseSendMsgUrl(String baseSendMsgUrl) {
    this.baseSendMsgUrl = baseSendMsgUrl;
  }

  public String getBaseQrcodeUrl() {
    return this.baseQrcodeUrl;
  }

  public void setBaseQrcodeUrl(String baseQrcodeUrl) {
    this.baseQrcodeUrl = baseQrcodeUrl;
  }

  public String getBaseQrcodePicUrl() {
    return this.baseQrcodePicUrl;
  }

  public void setBaseQrcodePicUrl(String baseQrcodePicUrl) {
    this.baseQrcodePicUrl = baseQrcodePicUrl;
  }

  public String getBaseCustomSendMsgUrl() {
    return this.baseCustomSendMsgUrl;
  }

  public void setBaseCustomSendMsgUrl(String baseCustomSendMsgUrl) {
    this.baseCustomSendMsgUrl = baseCustomSendMsgUrl;
  }

  public String getBaseCreateMenuUrl() {
    return this.baseCreateMenuUrl;
  }

  public void setBaseCreateMenuUrl(String baseCreateMenuUrl) {
    this.baseCreateMenuUrl = baseCreateMenuUrl;
  }

  public String getBaseQueryMenuUrl() {
    return this.baseQueryMenuUrl;
  }

  public void setBaseQueryMenuUrl(String baseQueryMenuUrl) {
    this.baseQueryMenuUrl = baseQueryMenuUrl;
  }

  public String getBaseDeleteMenuUrl() {
    return this.baseDeleteMenuUrl;
  }

  public void setBaseDeleteMenuUrl(String baseDeleteMenuUrl) {
    this.baseDeleteMenuUrl = baseDeleteMenuUrl;
  }

  public String getBaseSubscribeUserListUrl() {
    return this.baseSubscribeUserListUrl;
  }

  public void setBaseSubscribeUserListUrl(String baseSubscribeUserListUrl) {
    this.baseSubscribeUserListUrl = baseSubscribeUserListUrl;
  }

  public String getBaseOAuthUrl() {
    return this.baseOAuthUrl;
  }

  public void setBaseOAuthUrl(String baseOAuthUrl) {
    this.baseOAuthUrl = baseOAuthUrl;
  }

  public String getBaseOAuthTokenUrl() {
    return this.baseOAuthTokenUrl;
  }

  public void setBaseOAuthTokenUrl(String baseOAuthTokenUrl) {
    this.baseOAuthTokenUrl = baseOAuthTokenUrl;
  }



public String getBasetestLocalOAuthRedirectUrl() {
	return basetestLocalOAuthRedirectUrl;
}

public void setBasetestLocalOAuthRedirectUrl(
		String basetestLocalOAuthRedirectUrl) {
	this.basetestLocalOAuthRedirectUrl = basetestLocalOAuthRedirectUrl;
}

public String getBasebuildLocalOAuthRedirectUrl() {
	return basebuildLocalOAuthRedirectUrl;
}

public void setBasebuildLocalOAuthRedirectUrl(
		String basebuildLocalOAuthRedirectUrl) {
	this.basebuildLocalOAuthRedirectUrl = basebuildLocalOAuthRedirectUrl;
}

public String getBaseDeliverNotifyUrl() {
	return baseDeliverNotifyUrl;
}

public void setBaseDeliverNotifyUrl(String baseDeliverNotifyUrl) {
	this.baseDeliverNotifyUrl = baseDeliverNotifyUrl;
}

public void setBaseUploadVideoUrl(String baseUploadVideoUrl) {
	this.baseUploadVideoUrl = baseUploadVideoUrl;
}

public String getBaseUploadVideoUrl() {
	return baseUploadVideoUrl;
}
  
  
}