package com.rockstar.o2o.constant;

public class Patternformat {
	public static final String ALL_NUM = "^[0-9]*$";
	public static final String LENGTH_LIMIT = ".{6,16}";
	public static final String CELLPHONE = "1[3|4|5|7|8][0-9]\\d{8}";
	public static final String FIXPHONE = "(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])d{8}$))"; 
    public static final String SPECIALSTR = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{1,10}$";
    public static final String SHOPCODE = "^[a-zA-Z0-9_]{1,8}$";
}
