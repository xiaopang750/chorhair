package com.rockstar.o2o.util.jsonvalidator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

import eu.vahlas.json.schema.JSONSchema;
import eu.vahlas.json.schema.JSONSchemaProvider;
import eu.vahlas.json.schema.impl.JacksonSchemaProvider;

/**
 * json格式校验
 * @author xc
 *
 */
public class Validator {
	ObjectMapper mapper;
	JSONSchemaProvider schemaProvider;
	JSONSchema schema;
	
	/**
	 * 根据流实例化检验对象
	 * @param schemaName
	 */
	public Validator(InputStream in) {
	    mapper = new ObjectMapper();
	    schemaProvider = new JacksonSchemaProvider(mapper);
	    schema = schemaProvider.getSchema(in);
	}
	
	/**
	 * 根据字符串实例化校验对象
	 * @param content
	 * @param param
	 */
	public Validator(String content){
	    mapper = new ObjectMapper();
	    schemaProvider = new JacksonSchemaProvider(mapper);
	    schema = schemaProvider.getSchema(content);
	}
	
	/**
	 * 将文件中的内容转化成字符串
	 * @param schemaName
	 * @return
	 */
	public static String readFileToString(String schemaName){
		String returnValue="";
		try {
			InputStream in=Validator.class.getResourceAsStream(schemaName);
			StringBuffer sbuf=new StringBuffer();
			InputStreamReader reader=new InputStreamReader(in,"utf-8");
			Reader buf=new BufferedReader(reader);
			int ch;
			int iCharNum=0;
			while((ch=buf.read())>-1){
				iCharNum+=1;
				sbuf.append((char)ch);
			}
			buf.close();
			returnValue=sbuf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return returnValue;
	}
	
	public  int validate(String content){
		JSONObject obj=new JSONObject();
		obj.accumulate("datas", content);
		List<String> errors = schema.validate(obj.toString());
		if(errors!=null){
			return errors.size();
		}
		return 0;
	}
	
}
