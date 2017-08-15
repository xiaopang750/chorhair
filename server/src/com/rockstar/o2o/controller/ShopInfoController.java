package com.rockstar.o2o.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.util.BaiduMapUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 店铺信息维护
 * @author hxx
 *
 */
@Controller
@RequestMapping("shop")
public class ShopInfoController extends BaseController {
	/**
	 * 店铺信息列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("findshopinfolist")
	public void findShopInfolist(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//省
			String province=(String)obj.get("province");
			//市
			String city=(String)obj.get("city");
			//区
			String county=(String)obj.get("county");
			//街道
			String street=(String)obj.get("street");

			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<ShopInfo> list=new ArrayList<ShopInfo>();
			ArrayList<ShopInfo> totallist=new ArrayList<ShopInfo>();
			if(!curpage.equals("")){
				list=(ArrayList<ShopInfo>) shopinfoService.QueryShopByPagination(province,city,county,street,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				totallist=(ArrayList<ShopInfo>) shopinfoService.QueryShopByPagination(province,city,county,street,null,null);
			}else{
				list=(ArrayList<ShopInfo>) shopinfoService.QueryShopByPagination(province,city,county,street,null,null);
			}
			if(list.size()>0){
				String data=JSONArray.fromObject(list).toString();
				if(!curpage.equals("")){			
					outputstr(data, "查询店铺成功", true,totallist.size());
				}else{
					outputstr(data, "查询店铺成功", true,list.size());	
				}
			}else{
				outputstr("", "暂无店铺信息", true,null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 查询店铺详细信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("findshopbyid")
	public void findShopById(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");			
			ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
			if(info!=null){
				JSONObject returnobj=JSONObject.fromObject(info);
			    outputstr(returnobj.toString(), "查询店铺详情信息成功", true,null);
			}else{
				outputstr("", "无此店铺详情信息", true,null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();			
		}
		
        output(response, pojo);			
	}
	
	/**
	 * 保存店铺信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("saveshopinfo")
	public void saveShopInfo(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String shopshortcode=getobj.getString("shopshortcode");
			String shopcode=getobj.getString("shopcode");
			String shopname=getobj.getString("shopname");
			String shopowner=getobj.getString("shopowner");
			String cellphone=getobj.getString("cellphone");
			String fixphone=(String)getobj.get("fixphone");
			String shopinterface=getobj.getString("shopinterface");
			String interfacephone=getobj.getString("interfacephone");
			String businessour=getobj.getString("businessour");
			String addr=getobj.getString("addr");
			String location=getobj.getString("location");
			//省
			String province=(String)getobj.get("province");
			//省名
			String provincename=(String)getobj.get("provincename");
			//市
			String city=(String)getobj.get("city");
			//市名
			String cityname=(String)getobj.get("cityname");
			//区
			String county=(String)getobj.get("county");
			//区名
			String countyname=(String)getobj.get("countyname");
			//街道
			String street=(String)getobj.get("street");
			//街道名
			String streetname=(String)getobj.get("streetname");

			ShopInfo info=new ShopInfo();
			info.setShopcode(shopshortcode);
			info.setShopcode(shopcode);
			info.setShopname(shopname);
			info.setShopowner(shopowner);
			info.setCellphone(cellphone);
			info.setFixphone(fixphone);
			info.setShopinterface(shopinterface);
			info.setInterfacephone(interfacephone);
			info.setBusinessour(businessour);
			info.setAddr(addr);
			info.setLocation(location);
			info.setProvince(province);
			info.setProvincename(provincename);
			info.setCity(city);
			info.setCityname(cityname);
			info.setCounty(county);
			info.setCountyname(countyname);
			info.setStreet(street);
			info.setStreetname(streetname);
			info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			info.setDr((short)0);
			
			shopinfoService.save(info);
				
			outputstr("", "保存店铺信息成功", true,null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 修改店铺信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("editshopinfo")
	public void editShopInfo(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject getobj=RequestUtil.getPostString(request);
			String pkShop=getobj.getString("pkShop");
			String shopname=getobj.getString("shopname");
			String fixphone=(String)getobj.get("fixphone");
			String businessour=getobj.getString("businessour");
			String addr=getobj.getString("addr");
			//省
			String province=(String)getobj.get("province");
			//省名
			String provincename=(String)getobj.get("provincename");
			//市
			String city=(String)getobj.get("city");
			//市名
			String cityname=(String)getobj.get("cityname");
			//区
			String county=(String)getobj.get("county");
			//区名
			String countyname=(String)getobj.get("countyname");
			//街道
			String street=(String)getobj.get("street");
			//街道名
			String streetname=(String)getobj.get("streetname");

			ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
			info.setPkShop(Long.parseLong(pkShop));
			info.setShopname(shopname);
			info.setFixphone(fixphone);
			info.setBusinessour(businessour);
			info.setAddr(addr);
			Object[] obj=BaiduMapUtil.getlngandlat(addr);
			info.setLocation(obj[0]+","+obj[1]);
			info.setProvince(province);
			info.setProvincename(provincename);
			info.setCity(city);
			info.setCityname(cityname);
			info.setCounty(county);
			info.setCountyname(countyname);
			info.setStreet(street);
			info.setStreetname(streetname);
			info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));	  
			int result=shopinfoService.updateGroup(info);
				
			if(result==0){
			    outputstr("", "维护店铺信息成功", true,null);
		    }else{
		    	outputstr("", "维护店铺信息失败", false,null);
		     }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	/**
	 * 删除店铺
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("deleteshopbyid")
	public void deleteShopById(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");			
			shopinfoService.deleteShopById(Long.parseLong(pkShop));
			outputstr("", "删除店铺成功", true,null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();			
		}
		
        output(response, pojo);			
	}
	
	
}
