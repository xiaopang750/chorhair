package com.rockstar.o2o.constant;

/**
 * 二维码生成对象类型：生成店铺二维码：1,生成理发师二维码：2，生成消费者二维码：3，生成平台二维码：4
 * @author hxx
 *
 */
public class QrcodeOperateobject {
	public static final String[] OPERATEOBJECT_SHOP = new String[]{"1","店铺","QR_LIMIT_SCENE"};
	public static final String[] OPERATEOBJECT_FAIR= new String[]{"2","理发师","QR_LIMIT_SCENE"};
	public static final String[] OPERATEOBJECT_CUSTOMER = new String[]{"3","消费者","QR_SCENE"};
	public static final String[] OPERATEOBJECT_PLAT = new String[]{"4","平台","QR_LIMIT_SCENE"};
	public static final String[] OPERATEOBJECT_SHOPORDER = new String[]{"5","店铺订单","QR_SCENE"};
	public static final String[] OPERATEOBJECT_SHOPOTHER = new String[]{"6","店铺其他二维码","QR_SCENE"};
}
