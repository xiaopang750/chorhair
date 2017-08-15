package com.rockstar.o2o.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MailUtil;
import com.rockstar.o2o.util.TemplateUtil;
import com.rockstar.o2o.util.file.FileManegeUtil;

@Controller
@RequestMapping("reportexport")
public class ReportExportController extends BaseController{
	@RequestMapping("/exportexcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,Model model){   

		String subject=DateUtil.befoDay()+"报表数据";
		String to="hxx@rockstars.com.cn,xucheng@rockstars.com.cn,wangye@rockstars.com.cn";
		String content="附件为："+DateUtil.befoDay()+"各店的报表数据，请注意查收！";
		String files="";
		List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		List<ShopInfo> shopinfo=shopinfoService.QueryShopByPagination(null, null, null, null, null, null);
		if(shopinfo.size()>0){
			for (int i = 0; i < shopinfo.size(); i++) {
				Map<String,String> map=new HashMap<String, String>();
				String[] allpath=TemplateUtil.class.getResource("/").getPath().split("WEB-INF");
				//利用模板导出excel2003
				//String filePath=allpath[0]+"template/export/统计报表数据.xls";
				
				// 利用模板导出excel2007
				String filePath=allpath[0]+"template/export/统计报表数据.xlsx";
				Long pkshop=shopinfo.get(i).getPkShop();
				reportExportService.exportReport(pkshop);
				File file = new File(filePath);
				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					String visiturl=FileManegeUtil.writeFileByStream(fis,"xlsx");
					String filename=DateUtil.befoDay()+shopinfo.get(i).getShopname()+"报表数据.xlsx";
					map.put("fileurl", visiturl);
					map.put("filename", filename);
					list.add(map);					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			}
			files=JSONArray.fromObject(list).toString();
		}
		
		boolean result=MailUtil.sendmoreremotefile(subject,to,content,files);
		if(result){
			outputstr("", "发送报表邮件成功", true, null);
		}else{
			outputstr("", "发送报表邮件失败", false, null);
		}
		output(response, pojo);
	}  
}
