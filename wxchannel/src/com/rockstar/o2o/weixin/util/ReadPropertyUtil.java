package com.rockstar.o2o.weixin.util;

import java.util.ResourceBundle;

public class ReadPropertyUtil {
	
	public ReadPropertyUtil() {
	}

	public static String getServerURL(String propertyname,String service)
	{
		ResourceBundle bundle = ResourceBundle.getBundle(propertyname);
		
		String env=System.getenv("CHORHAIR");
				
		String path="";;
		
		if(env==null||env.equals("")){
			path="local.";
		 }else if(env.equals("test")){
			 path="test."; 
		 }else if(env.equals("build")){
			path="build.";	
		 }
	    path=path+service;
	    System.out.println((String) bundle.getString(path));
		return (String) bundle.getString(path);
	}
}
