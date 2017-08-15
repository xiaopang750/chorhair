package com.rockstar.o2o.constant;

/**
 * 用户分组{分组code,分组名称,url样式,用户编码,对应的数据库表名}
 * @author xc
 *
 */
public class UserGroup {
	public static final String[] GROUP_ONE = new String[]{"001","消费者","?usergroup=01","customercode","customer_info"};
	public static final String[] GROUP_TWO= new String[]{"002","发型师","?usergroup=02","fairercode","fairer_info"};
	public static final String[] GROUP_THREE = new String[]{"003","店家","?usergroup=03","shopusercode","shop_user"};
	public static final String[] GROUP_FOUR = new String[]{"004","平台","?usergroup=04","shopusercode","shop_user"};
}
