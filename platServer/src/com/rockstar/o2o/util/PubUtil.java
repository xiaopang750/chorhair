package com.rockstar.o2o.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PubUtil {
	public PubUtil() {
	}

	public static String getServerURL(String serviceType)
	{
		Properties prop = new Properties();
		InputStream in = PubUtil.class.getResourceAsStream("serverconfig.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) prop.get(serviceType);
	}
}
