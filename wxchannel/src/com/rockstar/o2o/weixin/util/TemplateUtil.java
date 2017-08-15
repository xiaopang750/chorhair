package com.rockstar.o2o.weixin.util;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 模板服务，决定调用哪一个模板tpl，动态给模板赋值，返回字符串值
 * @author xc
 * 
 */
public class TemplateUtil
{
  private static final Logger logger = Logger.getLogger(TemplateUtil.class);
  public static String invokeTpl(Map<String, String> resultMap, String tplBaseUrl, String tplName)
  {
    StringWriter writer = null;
    String content = "";
    Template template = null;
    try {
      Configuration configration = new Configuration();
      configration.setDefaultEncoding("UTF-8");
      configration.setObjectWrapper(new DefaultObjectWrapper());
      String[] allpath=TemplateUtil.class.getResource("/").getPath().split("WEB-INF");
      File file = new File(allpath[0]+tplBaseUrl);
      configration.setDirectoryForTemplateLoading(file);
      template = configration.getTemplate(tplName);
      writer = new StringWriter();
      template.process(resultMap, writer);
      content = writer.toString();
      writer.close();
      return content;
    } catch (Exception e) {
      logger.error("模板处理出错！");
      e.printStackTrace();
    }
    return content;
  }
}