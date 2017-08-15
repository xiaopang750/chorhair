package com.rockstar.o2o.constant;


public interface MessageType {
	public static final String TYPE_ONE = "MOBILEPASSWORDCODE";//忘记密码验证码
	public static final String TYPE_TWO = "MOBILECHECKCODE";//手机号注册验证码
	public static final String TYPE_THREE = "REGISTERAPP";//注册APP成功消息
	public static final String TYPE_FOUR = "USERTEXTMESSAGE";//用户会话文字消息
	public static final String TYPE_PLAT = "PLATFORM";//平台消息
	public static final String WXBIND = "WXBIND";//微信绑定
}
