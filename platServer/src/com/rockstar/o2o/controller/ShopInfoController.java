package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.constant.UserGroupObject;
import com.rockstar.o2o.pojo.ProductInfo;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopProductInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.BaiduMapUtil;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.MD5Util;
import com.rockstar.o2o.util.PatternUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.usercode.UserCodeUtil;

/**
 * 店铺信息维护
 * @author hxx
 *
 */
@Controller
@RequestMapping("shop")
public class ShopInfoController extends BaseController {
	
	
	/**
	 * 维护店铺管理员
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addshopuser")
	public void addshopuser(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);			
			String cellphone=obj.getString("cellphone");
			String username=obj.getString("username");
			String loginpassword=obj.getString("loginpassword");
			String pkShop=obj.getString("pkShop");			
			String usercode="";
			String shopcode="";
	        boolean flag =PatternUtil.validatenum(loginpassword); 
	        if(flag){
	        	outputstr("", "密码不能全是数字", false, null);
	        }else{
	        	flag = PatternUtil.validatelength(loginpassword); 
		        if(flag){
					//获取店铺信息
					ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
					if(info==null){
						shopcode="AU";
					}else{
						shopcode=info.getShopshortcode();
					}
					//查询店铺信息SQL
					String sql=UserCodeUtil.execute(UserGroupObject.GROUP_THREE[0], shopcode,3);
					String secretpassword=MD5Util.MD5Encode(loginpassword, "utf-8");
					
					//获取用户编码
					List<Object> objectlist=shopuserService.querybysql(sql);
					if(objectlist.get(0)==null){
						usercode=shopcode+UserGroupObject.GROUP_THREE[0]+"001";
					}else{
						usercode=shopcode+UserGroupObject.GROUP_THREE[0]+objectlist.get(0).toString();	
					}
					ShopUser user=new ShopUser();
					user.setShopusercode(usercode);
					user.setShopusername(username);
					user.setCellphone(cellphone);
					user.setLoginpassword(secretpassword);
					user.setPkShop(info.getPkShop());
					user.setDr((short)0);
					user.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					user=shopuserService.save(user);
					outputstr("", "新增用户成功", true, null);
		        }else{
		        	 outputstr("", "密码长度必须在6到18位", false, null);	
		        }
	        }
		} catch (JSONException e) {
			dealexception(e);
            outputexceptionstr();
		}
		output(response,pojo);
    }
	
	
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
			//店铺 主键
			String pkShop=(String)obj.get("pkShop");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<ShopInfo> list=new ArrayList<ShopInfo>();
			ArrayList<ShopInfo> totallist=new ArrayList<ShopInfo>();
			if(!curpage.equals("")){
				list=(ArrayList<ShopInfo>) shopinfoService.QueryShopByPagination(province,city,county,street,pkShop,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				totallist=(ArrayList<ShopInfo>) shopinfoService.QueryShopByPagination(province,city,county,street,pkShop,null,null);
			}else{
				list=(ArrayList<ShopInfo>) shopinfoService.QueryShopByPagination(province,city,county,street,pkShop,null,null);
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
			//店铺名称
			String shopname=getobj.getString("shopname");
			//联系电话
			String fixphone=(String)getobj.get("fixphone");
			//店铺简码(拼音 数字  下划线  8位以内)
			String shopcode=(String)getobj.get("shopcode");			
			String shopowner=(String)getobj.get("shopowner");
			String cellphone=(String)getobj.get("cellphone");
			String shopinterface=(String)getobj.get("shopinterface");
			String interfacephone=(String)getobj.get("interfacephone");
			//营业时间
			String businessour=getobj.getString("businessour");
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
			//具体地址
			String addr=getobj.getString("addr");
			boolean iscontinue=true;
			Object[] obj=null;
			if(shopcode !=null&& !shopcode.equals("")){
				int size=shopinfoService.QueryByShopcode(shopcode,null);
				if(size>0){
					iscontinue=false;
					outputstr("", "店铺编码已存在，请重新输入", false,null);
				}else{
					//正则判断
					boolean result=PatternUtil.validateshopcode(shopcode);
					if(!result){
						iscontinue=false;
						outputstr("", "店铺编码格式不正确", false,null);
					}
				}
			}else{
				iscontinue=false;
				outputstr("", "店铺编码不能为空", false,null);
			}
			if(addr !=null){
				obj=BaiduMapUtil.getlngandlat(addr);
				if(obj[0].equals("")||obj[1].equals("")){
					iscontinue=false;
					outputstr("", "具体地址坐标无法定位", false,null);
				}
			}else{
				iscontinue=false;
				outputstr("", "具体地址不能为空", false,null);
			}
			if(iscontinue){
				ShopInfo info=new ShopInfo();
				info.setShopcode(shopcode);
				info.setShopshortcode(shopcode);
				info.setShopname(shopname);
				info.setShopowner(shopowner);
				info.setCellphone(cellphone);
				info.setFixphone(fixphone);
				info.setShopinterface(shopinterface);
				info.setInterfacephone(interfacephone);
				info.setBusinessour(businessour);
				info.setAddr(addr);
				info.setLocation(obj[0]+","+obj[1]);
				info.setProvince(province);
				info.setProvincename(provincename);
				info.setCity(city);
				info.setCityname(cityname);
				info.setCounty(county);
				info.setCountyname(countyname);
				info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				info.setDr((short)0);
				info=shopinfoService.save(info);
				
				//初始化库存
				ArrayList<ProductInfo> infos=(ArrayList<ProductInfo>) platproductService.findproduct(null, null,  null);
				ArrayList<Object> addlist=new ArrayList<Object>();
				for(int i=0;i<infos.size();i++){
					ShopProductInfo shopProduct=new ShopProductInfo();
					shopProduct.setPkShop(info.getPkShop());
					shopProduct.setPkProduct(infos.get(i).getPkProduct());
					shopProduct.setBrand(infos.get(i).getBrand());
					shopProduct.setSeries(infos.get(i).getSeries());
					shopProduct.setItemno(infos.get(i).getItemno());
					shopProduct.setProducttype(infos.get(i).getProducttype());
					shopProduct.setProductname(infos.get(i).getProductname());
					shopProduct.setProductprice(infos.get(i).getProductprice());
					shopProduct.setCapacity(infos.get(i).getCapacity());
					shopProduct.setUnit(infos.get(i).getUnit());
					shopProduct.setPy(CharUtil.chineseToPingyin(infos.get(i).getProductname()));
					shopProduct.setShortpy(CharUtil.getHeadChar(infos.get(i).getProductname()));
					addlist.add(shopProduct);
				}
				//批量保存店铺商品
			  	basicservice.batchoperate(addlist, null);
				outputstr("", "保存店铺信息成功", true,null);
			}
		} catch (Exception e) {
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
			//店铺名称
			String shopname=getobj.getString("shopname");
			//联系电话
			String fixphone=(String)getobj.get("fixphone");
			//店铺简码(拼音 数字  下划线  8位以内)
			String shopcode=(String)getobj.get("shopcode");	
			//营业时间
			String businessour=getobj.getString("businessour");
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
			//具体地址
			String addr=getobj.getString("addr");
			boolean iscontinue=true;
			Object[] obj=null;
			if(shopcode !=null&& !shopcode.equals("")){
				int size=shopinfoService.QueryByShopcode(shopcode,pkShop);
				if(size>0){
					iscontinue=false;
					outputstr("", "店铺简码已存在，请重新输入", false,null);
				}else{
					//正则判断
					boolean result=PatternUtil.validateshopcode(shopcode);
					if(!result){
						iscontinue=false;
						outputstr("", "店铺简码格式不正确", false,null);
					}
				}
			}else{
				iscontinue=false;
				outputstr("", "店铺简码不能为空", false,null);
			}
			if(addr !=null){
				obj=BaiduMapUtil.getlngandlat(addr);
				if(obj[0].equals("")||obj[1].equals("")){
					iscontinue=false;
					outputstr("", "具体地址坐标无法定位", false,null);
				}
			}else{
				iscontinue=false;
				outputstr("", "具体地址不能为空", false,null);
			}
			if(iscontinue){
				ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
				info.setPkShop(Long.parseLong(pkShop));
				info.setShopcode(shopcode);
				info.setShopshortcode(shopcode);
				info.setShopname(shopname);
				info.setFixphone(fixphone);
				info.setBusinessour(businessour);
				info.setAddr(addr);				
				info.setLocation(obj[0]+","+obj[1]);
				info.setProvince(province);
				info.setProvincename(provincename);
				info.setCity(city);
				info.setCityname(cityname);
				info.setCounty(county);
				info.setCountyname(countyname);
				info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				int result=shopinfoService.updateGroup(info);
				if(result==0){
				    outputstr("", "维护店铺信息成功,店铺信息重新登录系统后生效", true,null);
			    }else{
			    	outputstr("", "维护店铺信息失败", false,null);
			     }
			}
		} catch (Exception e) {
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
			dealexception(e);
            outputexceptionstr();			
		}
		
        output(response, pojo);			
	}
	
	/**
	 * 获取现有的店铺区域列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("getshopadrlist")
	public void getAllShopAdr(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//省
			String province=(String)obj.get("province");
			//市
			String city=(String)obj.get("city");
			//区
			String county=(String)obj.get("county");
			//级别
			String level=(String)obj.get("level");
			
			if(province!=null && level.equals("1")){
				ArrayList<ShopInfo> arealist=(ArrayList<ShopInfo>)shopinfoService.getAllShopAddr(province,level);
				if(arealist.size()>0){
			    	String data=JSONArray.fromObject(arealist).toString();
			    	outputstr(data, "查询市成功", true, null);
			    }
			}else if(city!=null && level.equals("2")){
				ArrayList<ShopInfo> arealist=(ArrayList<ShopInfo>)shopinfoService.getAllShopAddr(city,level);
			    if(arealist.size()>0){
			    	String data=JSONArray.fromObject(arealist).toString();
			    	outputstr(data, "查询区域成功", true, null);
			    }
			}else if(county!=null && level.equals("3")){	
				ArrayList<ShopInfo> arealist=(ArrayList<ShopInfo>)shopinfoService.getAllShopAddr(county,level);
			    if(arealist.size()>0){
			    	String data=JSONArray.fromObject(arealist).toString();
			    	outputstr(data, "查询店铺成功", true, null);
			    }
			}else if(level.equals("0")){
				ArrayList<ShopInfo> arealist=(ArrayList<ShopInfo>)shopinfoService.getAllShopAddr(null,level);
			    if(arealist.size()>0){
			    	String data=JSONArray.fromObject(arealist).toString();
			    	outputstr(data, "查询省成功", true, null);
			    }
			}else{
				
			}
		} catch (Exception e) {
			dealexception(e);
            outputexceptionstr();			
		}
        output(response, pojo);			
	}
	
}
