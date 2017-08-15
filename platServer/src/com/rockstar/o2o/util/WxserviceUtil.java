package com.rockstar.o2o.util;

import java.util.ResourceBundle;

public class WxserviceUtil {

	static  ResourceBundle bundle = ResourceBundle.getBundle("wxservice");
	static  String env=System.getenv("CHORHAIR")==null?"local.":(System.getenv("CHORHAIR")
			.equals("test")?"test.":"build.");

	public static String GetUrl(String service){
		String url=bundle.getString(env+service);
		return url;
	}
	
}
