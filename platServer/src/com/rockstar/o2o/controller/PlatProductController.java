package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rockstar.o2o.pojo.PlatProductTrace;
import com.rockstar.o2o.pojo.PlatUser;
import com.rockstar.o2o.pojo.ProductInfo;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopProductInfo;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.PatternUtil;
import com.rockstar.o2o.util.RequestUtil;

/**
 * 平台商品
 * @author xc
 *
 */
@Controller
@RequestMapping("/platproduct")
public class PlatProductController extends BaseController{
	
	/**
	 * 查询平台商品信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/findproduct")
	public void findproduct(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String productname=obj.get("productname")==null?"":obj.getString("productname");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			 ArrayList<ProductInfo> infos=new ArrayList<ProductInfo>();
			 ArrayList<ProductInfo> totalinfos=new ArrayList<ProductInfo>();
			 //分页查询
		    if(!curpage.equals("")){
			    infos=(ArrayList<ProductInfo>) platproductService.findproduct(productname,Integer.parseInt(curpage),  Integer.parseInt(pagesize));
			    totalinfos=(ArrayList<ProductInfo>) platproductService.findproduct(productname, null,  null); 
			    if(infos.size()>0){
			    	outputstr(JSONArray.fromObject(infos).toString(), "查询平台商品成功", true, totalinfos.size());
			    }else{
			    	outputstr("", "暂无商品", true,null);	
			    }
		    }else{//全部查询
		    	totalinfos=(ArrayList<ProductInfo>) platproductService.findproduct(productname,null,  null); 
		    	if(totalinfos.size()>0){
		    		outputstr(JSONArray.fromObject(totalinfos).toString(), "查询平台商品成功", true, totalinfos.size());
		    	}else{
		    		outputstr("", "暂无商品",true,null);		 
		    	}
		    }
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
	      output(response, pojo);
	}
	
	/**
	 * 添加平台商品信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addproduct")
	public void addproduct(HttpServletRequest request,HttpServletResponse response,Model model) {
		ProductInfo info=new ProductInfo();
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//品牌
			String brand=obj.getString("brand");
			//系列
			String series=obj.getString("series");
			//货号
			String itemno=(String)obj.get("itemno");
			//产品类型
			String producttype=(String)obj.get("producttype");
			//商品名称
			String productname=obj.getString("productname");
			//商品价格
			String productprice=obj.getString("productprice");
			//商品数量
			String productnum=obj.getString("productnum");
			//商品数量单位
			String productunit=obj.getString("productunit");
			//容量
			String capacity=obj.getString("capacity");
			//容量单位
			String unit=obj.getString("unit");
			//备注
			String note=(String)obj.get("note");
			//操作人ID
			String pkUser=obj.getString("pkUser");
			//店铺id集合
			String shoplist=(String)obj.get("shops");
			boolean iscontinue=true;
			boolean flag1 =PatternUtil.validatenum(capacity); 
			boolean flag2 =PatternUtil.validatenum(productnum);
		    boolean flag3 =PatternUtil.validatenum(productprice);
	        if(!flag1 || !flag2 || !flag3){
	        	outputstr("", "输入格式不正确，请输入纯数字", false, null);	        	
	        	iscontinue=false;
	        }
	        if(iscontinue){
				//查询操作人信息
				PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
				if(user!=null){	
					info.setBrand(brand);
					info.setSeries(series);
					info.setItemno(itemno);
					info.setProducttype(producttype);
					info.setProductname(productname);
					info.setProductprice(Double.parseDouble(productprice));
					info.setProductnum(Integer.parseInt(productnum));
					info.setProductunit(productunit);
					info.setCapacity(capacity);
					info.setUnit(unit);
					info.setNote(note);
					info.setPy(CharUtil.chineseToPingyin(info.getProductname()));
					info.setShortpy(CharUtil.getHeadChar(info.getProductname()));
					info.setOperatorid(Long.parseLong(pkUser));
					info.setOperatorname(user.getPlatusername());
					info.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					info.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					info.setDr((short)0);
					info=platproductService.save(info);
					//保存分配的店铺
					if(shoplist!=null&&!shoplist.equals("")){
						JSONArray arr=JSONArray.fromObject(shoplist);
						Iterator<?> iter=arr.iterator();
						ArrayList<Object> addlist=new ArrayList<Object>();
						while(iter.hasNext()){
							JSONObject oneobj=(JSONObject) iter.next();
							String pkShop=oneobj.getString("pkShop");						
							ShopProductInfo shopProduct=new ShopProductInfo();
							shopProduct.setPkShop(Long.parseLong(pkShop));
							shopProduct.setPkProduct(info.getPkProduct());
							shopProduct.setBrand(info.getBrand());
							shopProduct.setSeries(info.getSeries());
							shopProduct.setItemno(info.getItemno());
							shopProduct.setProducttype(info.getProducttype());
							shopProduct.setProductname(info.getProductname());
							shopProduct.setProductprice(info.getProductprice());
							shopProduct.setCapacity(info.getCapacity());
							shopProduct.setUnit(info.getUnit());
							shopProduct.setPy(CharUtil.chineseToPingyin(info.getProductname()));
							shopProduct.setShortpy(CharUtil.getHeadChar(info.getProductname()));						
							addlist.add(shopProduct);
					    }	
					  	//批量保存店铺商品
					  	basicservice.batchoperate(addlist, null);
				    }else{
				    	List<ShopInfo> list=shopinfoService.getAllShop();
				    	if(list.size()>0){
				    		ArrayList<Object> addlist=new ArrayList<Object>();
					    	for(int i=0;i<list.size();i++){
								long pkShop=list.get(i).getPkShop();						
								ShopProductInfo shopProduct=new ShopProductInfo();
								shopProduct.setPkShop(pkShop);
								shopProduct.setPkProduct(info.getPkProduct());
								shopProduct.setBrand(info.getBrand());
								shopProduct.setSeries(info.getSeries());
								shopProduct.setItemno(info.getItemno());
								shopProduct.setProducttype(info.getProducttype());
								shopProduct.setProductname(info.getProductname());
								shopProduct.setProductprice(info.getProductprice());
								shopProduct.setCapacity(info.getCapacity());
								shopProduct.setUnit(info.getUnit());
								shopProduct.setPy(CharUtil.chineseToPingyin(info.getProductname()));
								shopProduct.setShortpy(CharUtil.getHeadChar(info.getProductname()));						
								addlist.add(shopProduct);
						    }
					    	//批量保存店铺商品
						  	basicservice.batchoperate(addlist, null);
				    	}
				    }
					//保存商品操作记录					
					PlatProductTrace trace=new PlatProductTrace();
					trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					trace.setOperatorid(Long.parseLong(pkUser));
					trace.setOperator(user.getPlatusername());
					trace.setPkProduct(info.getPkProduct());
					trace.setOperatecontent("新增商品");
					trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					trace.setDr((short)0);
					platProductTraceService.save(trace);
					outputstr("", "保存平台商品信息成功", true, null);
				}else{
					outputstr("", "用户不存在或没有权限", false, null);
				}
	        }
		} catch (Exception e) {
			dealexception(e);
			if(info!=null&&info.getPkProduct()!=null){
				platproductService.deleteById(info.getPkProduct());
			}
			outputexceptionstr();
		}
		output(response, pojo);
	}
	

	/**
	 * 修改平台商品数量
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editproductnum")
	public void editproductnum(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//商品id
			String pkProduct=obj.getString("pkProduct");
			//商品数量
			String productnum=obj.getString("productnum");
			//操作人ID
			String pkUser=obj.getString("pkUser");
			//查询操作人信息
			PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
			//旧数量
			//Integer oldProductnum=0;
			if(user!=null){	
				ArrayList<Object> editlist=new ArrayList<Object>();
				ArrayList<Object> addlist=new ArrayList<Object>();
				ProductInfo info=platproductService.findById(Long.parseLong(pkProduct));
				Integer oldProductnum=info.getProductnum()==null?0:info.getProductnum();
				info.setProductnum(oldProductnum+Integer.parseInt(productnum));
				info.setOperatorid(user.getPkUser());
				info.setOperatorname(user.getPlatusername());
				info.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				//批量修改
				editlist.add(info);
				//保存商品操作记录					
				PlatProductTrace trace=new PlatProductTrace();
				trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				trace.setOperatorid(Long.parseLong(pkUser));
				trace.setOperator(user.getPlatusername());
				trace.setPkProduct(info.getPkProduct());
				trace.setOperatecontent("新增库存"+productnum+":原库存"+oldProductnum+"，现库存"+info.getProductnum());
				trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				trace.setDr((short)0);
				//批量保存
				addlist.add(trace);
				if(addlist.size()>0 ||editlist.size()>0){
					//批量执行事务
					basicservice.batchoperate(addlist,editlist);
				}
				outputstr("", "修改平台商品数量成功", true, null);
			}else{
				outputstr("", "用户不存在或没有权限", false, null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 修改平台商品价格
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editprice")
	public void editprice(HttpServletRequest request,HttpServletResponse response,Model model) {		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			//商品id
			String pkProduct=obj.getString("pkProduct");
			//商品价格
			String productprice=obj.getString("productprice");
			//操作人ID
			String pkUser=obj.getString("pkUser");
			//查询操作人信息
			PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
			//旧价格
			Double oldProductprice=null;
			if(user!=null){
				ArrayList<Object> editlist=new ArrayList<Object>();
				ArrayList<Object> addlist=new ArrayList<Object>();
				ProductInfo info=platproductService.findById(Long.parseLong(pkProduct));
				oldProductprice=info.getProductprice();
				info.setProductprice(Double.parseDouble(productprice));
				info.setOperatorid(user.getPkUser());
				info.setOperatorname(user.getPlatusername());
				info.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				editlist.add(info);
				List<ShopProductInfo> list=shopproductService.getByProductid(info.getPkProduct());
		    	if(list.size()>0){
			    	for(int i=0;i<list.size();i++){					
			    		ShopProductInfo shopProduct=list.get(i);
						shopProduct.setProductprice(info.getProductprice());												
						editlist.add(shopProduct);
				    }
		    	}
		    	//保存商品操作记录					
				PlatProductTrace trace=new PlatProductTrace();
				trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				trace.setOperatorid(Long.parseLong(pkUser));
				trace.setOperator(user.getPlatusername());
				trace.setPkProduct(info.getPkProduct());
				trace.setOperatecontent("修改商品价格：原价格"+oldProductprice+"，现价格"+info.getProductprice());
				trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				trace.setDr((short)0);
				addlist.add(trace);
				if(addlist.size()>0 ||editlist.size()>0){
					//批量修改店铺商品价格
				  	basicservice.batchoperate(addlist,editlist);
				}
				outputstr("", "修改平台商品价格成功", true, null);
		}else{
				outputstr("", "用户不存在或没有权限", false, null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
}
