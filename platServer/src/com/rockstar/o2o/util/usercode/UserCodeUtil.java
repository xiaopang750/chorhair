package com.rockstar.o2o.util.usercode;

import com.rockstar.o2o.constant.UserGroupObject;

/**
 * 用户编码生成工具
 * @author hp
 *
 */
public class UserCodeUtil {
	public  static String execute(String usergroup,String shopcode,Integer length){
		String ColumnName="";
		String TableName="";
		/**
		 * 没有店铺信息，说明是自己注册的，自己注册的默认前缀为AU
		 */
		if(shopcode.equals("")){
			shopcode="AU";
		}
		/**
		 * 获取用户分组编码
		 */
		if(usergroup.equals(UserGroupObject.GROUP_ONE[0])){
			ColumnName=UserGroupObject.GROUP_ONE[3];
			TableName=UserGroupObject.GROUP_ONE[4];
		}else if(usergroup.equals(UserGroupObject.GROUP_TWO[0])){
			ColumnName=UserGroupObject.GROUP_TWO[3];	
			TableName=UserGroupObject.GROUP_TWO[4];
		}else if(usergroup.equals(UserGroupObject.GROUP_THREE[0])){
			ColumnName=UserGroupObject.GROUP_THREE[3];	
			TableName=UserGroupObject.GROUP_THREE[4];
		}else if(usergroup.equals(UserGroupObject.GROUP_FOUR[0])){
			ColumnName=UserGroupObject.GROUP_FOUR[3];	
			TableName=UserGroupObject.GROUP_FOUR[4];
		}
		/**
		 * 用户编码前缀组成
		 */
		String firstcode=shopcode+usergroup;
		
		String sql="select LPAD(max(SUBSTR("+ColumnName+",length("+ColumnName+")-"+(length-1)+",length("+ColumnName+")))+1,"+length+",0) from "+TableName+" where "+ColumnName+" like '"+firstcode+"%' and IFNULL(dr,0)=0";
		
		return sql;
	}
}
