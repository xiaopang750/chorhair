package com.rockstar.o2o.controller.weixin;

public class Oauth {
	
	
	/**
	 * 打开微信授权
	 * @return
	 */
    public String openwx()
   {
    return (new StringBuilder(String.valueOf(WeixinConfig.getValue("openwxURL").trim()))).append("?appid=").append(WeixinConfig.getValue("client_ID").trim()).append("&scope=snsapi_userinfo").append("&state=rswechat").toString();
   }
    
}
