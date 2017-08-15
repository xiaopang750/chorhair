package com.rockstar.o2o.util.datasource;

public  abstract class CustomerContextHolder {

	 public final static String DATA_SOURCE_MYSQL_TEST= "dataSourceA";
     public final static String DATA_SOURCE_MYSQL_PRO = "dataSourceB";
     
     private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
     
     public static void setCustomerType(String customerType) {  
         contextHolder.set(customerType);  
     }  
       
     public static String getCustomerType() {  
         return contextHolder.get();  
     }  
       
     public static void clearCustomerType() {  
         contextHolder.remove();  
     }  

     
}
