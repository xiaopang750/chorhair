package com.rockstar.o2o.util;

public class SysUtil {

	 public static String getpath() {
		 String path=System.getenv("CHORHAIR");
		 if(path==null||path.equals("test")){
			 return PubUtil.getServerURL("testpath");
		 }else if(path.equals("build")){
			 // return PubUtil.getServerURL("buildpath");
		 	return PubUtil.getServerURL("testpath");
		 }
		return ""; 
	 }
	 
	 public static String getjavapath() {
		 String path=System.getenv("CHORHAIR");
		 if(path==null||path.equals("")){
			 return PubUtil.getServerURL("localjavapath"); 
		 }else if(path!=null&&path.equals("test")){
			 return PubUtil.getServerURL("testjavapath");
		 }else if(path.equals("build")){
			 return PubUtil.getServerURL("buildjavapath");
		 }
		return ""; 
	 }
	 
	 public static String getassetspath() {
		 String path=System.getenv("CHORHAIR");
		 if(path==null||path.equals("test")){
			 return PubUtil.getServerURL("testassetspath");
		 }else if(path.equals("build")){
			 // return PubUtil.getServerURL("buildassetspath");
		 	return PubUtil.getServerURL("testassetspath");
		 }
		return ""; 
	 }
	 
}
