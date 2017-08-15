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
import com.rockstar.o2o.constant.ComboType;
import com.rockstar.o2o.constant.FairType;
import com.rockstar.o2o.pojo.ComboAward;
import com.rockstar.o2o.pojo.PlatUser;
import com.rockstar.o2o.pojo.PlatformCombo;
import com.rockstar.o2o.pojo.PlatformComboProduct;
import com.rockstar.o2o.pojo.PlatformComboService;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.pojo.ShopComboEditrecord;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopGroup;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.jsonvalidator.Validator;

@Controller
@RequestMapping("/platcombo")
public class PlatformComboController extends BaseController{

	/**
	 * 新增套餐
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addplatcombo")
    public void addplatcombo(HttpServletRequest request,HttpServletResponse response,Model model){
		 PlatformCombo combo=new PlatformCombo();
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			Validator validator=new Validator(Validator.class.getResourceAsStream("platcombo"));
	    	int i=validator.validate(obj.toString());		    
	    	boolean iscontinue=true;
	    	if(i>0){
	    		outputstr("", "传入的参数不符合规范", false, null);
	    		iscontinue=false;
	    	}
		    if(iscontinue){
		    	//套餐名
		    	String comboname=obj.getString("comboname");
		    	//套餐编码
		    	String combocode=obj.getString("combocode");
		    	//套餐价格
		    	String combomoney=obj.getString("combomoney");
		    	//发型类型（服务类型）
				String fairtype=obj.getString("fairtype");
				//套餐类型（限次，不限次 ，单次）
				String combotype=obj.getString("combotype");
				//适用人群（3.所有人）
				String fitgroup=obj.get("fitgroup")==null?"3":(String)obj.get("fitgroup");
				//备注
				String combonote=(String)obj.get("combonote");
				Integer combocount=null;
				if(combotype.equals("2")){
					 combocount=1;
				}else if(combotype.equals("3")){
					 combocount=obj.getInt("combocount");
				}
				//店铺集合
				String shoplist=(String)obj.get("shops");
				//产品集合
				String products=(String)obj.get("products");
				
	            combo.setCombocode(combocode);
	            combo.setComboname(comboname);
				combo.setCombomoney(Double.parseDouble(combomoney));
				combo.setCombocount(combocount);
				combo.setFairtype(fairtype);
				combo.setCombotype(combotype);
				combo.setFitgroup(fitgroup);		
				combo.setCombospy(CharUtil.getHeadChar(comboname));
				combo.setCombopy(CharUtil.chineseToPingyin(comboname));
				combo.setCombonote(combonote);
				combo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				combo.setDr((short)0);
				combo=platformcomboService.save(combo);
				
				ArrayList<Object> addlist=new ArrayList<Object>();
				//保存分配的店铺
				if(shoplist!=null&&!shoplist.equals("")){
					JSONArray arr=JSONArray.fromObject(shoplist);
					Iterator<?> iter=arr.iterator();
					while(iter.hasNext()){
						JSONObject oneobj=(JSONObject) iter.next();
						String pkShop=oneobj.getString("pkShop");
						
						ShopCombo newcombo=new ShopCombo();
						newcombo.setCombocode(combocode);
						newcombo.setComboname(comboname);
						newcombo.setStandardmoney(Double.parseDouble(combomoney));
						newcombo.setShopmoney(Double.parseDouble(combomoney));
						newcombo.setCurrentmoney(Double.parseDouble(combomoney));
						newcombo.setCombocount(combocount);
						newcombo.setFairtype(fairtype);
						newcombo.setCombotype(combotype);
						newcombo.setFitgroup(fitgroup);		
						newcombo.setCombospy(CharUtil.getHeadChar(comboname));
						newcombo.setCombopy(CharUtil.chineseToPingyin(comboname));
						newcombo.setCombonote(combonote);
						newcombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						newcombo.setDr((short)0);
						newcombo.setPkShop(Long.parseLong(pkShop));
						newcombo.setPkCombo(combo.getPkCombo());
						//加入批量添加中
						addlist.add(newcombo);
				    }	
				}
				//保存套餐的产品
				if(products!=null&&!products.equals("")){
					JSONArray arr=JSONArray.fromObject(products);
					Iterator<?> iter=arr.iterator();
					while(iter.hasNext()){
						JSONObject oneobj=(JSONObject) iter.next();
						String pkProduct=oneobj.getString("pkProduct");
						String productname=oneobj.getString("productname");
						String productunit=oneobj.getString("productunit");
						String productprice=oneobj.getString("productprice");
						
						PlatformComboProduct newproduct=new PlatformComboProduct();		
						newproduct.setPkProduct(Long.parseLong(pkProduct));
						newproduct.setProductname(productname);
						newproduct.setProductcount(1);
						newproduct.setProductprice(Double.parseDouble(productprice));
						newproduct.setProductunit(productunit);
						newproduct.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						newproduct.setDr((short)0);
						newproduct.setPkCombo(combo.getPkCombo());
						//加入批量添加中
						addlist.add(newproduct);
					}	
				}
				//批量保存店铺套餐和套餐产品
				basicservice.batchoperate(addlist, null);
				outputstr("", "保存平台套餐成功", true, null);
		    }
		}catch (Exception e) {
			e.printStackTrace();
			if(combo!=null&&combo.getPkCombo()!=null){
				platformcomboService.deleteComboById(combo.getPkCombo());
			}
		    outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 修改套餐
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editplatcombo")
    public void editplatcombo(HttpServletRequest request,HttpServletResponse response,Model model){
		 PlatformCombo combo=null;;
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			Validator validator=new Validator(Validator.class.getResourceAsStream("platcombo"));
	    	int i=validator.validate(obj.toString());		    
	    	boolean iscontinue=true;
	    	if(i>0){
	    		outputstr("", "传入的参数不符合规范", false, null);
	    		iscontinue=false;
	    	}
	    	
	    	ArrayList<Object> editlist=new ArrayList<Object>();
	    	ArrayList<Object> addlist=new ArrayList<Object>();
	    	//套餐id
	    	String  pkCombo=obj.getString("pkCombo");
	    	//删除店铺套餐集合
			String delshops=(String)obj.get("delshops");
			
			//删除不再选中的已分配店铺套餐
			if(delshops!=null&&!delshops.equals("")){
				JSONArray arr=JSONArray.fromObject(delshops);
				String shopstr="";
				Iterator<?> iter=arr.iterator();
				while(iter.hasNext()){
					JSONObject oneobj=(JSONObject) iter.next();
					String pkShop=oneobj.getString("pkShop");
					ShopCombo delcombo=shopcomboService.queryByShopAndCombo(Long.parseLong(pkShop),Long.parseLong(pkCombo));
					int result=customercomboService.queryByPkShopcombo(delcombo.getPkShopCombo());
					if(result==0){
						delcombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						delcombo.setDr((short)1);
						//加入批量删除中
						editlist.add(delcombo);
					}else{
						ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
						shopstr=shopstr+info.getShopname()+"，";
					}
			    }
				if(!shopstr.equals("")){
					 iscontinue=false;
					 shopstr=shopstr.substring(0, shopstr.length()-1);
					 outputstr("",shopstr+"套餐已在使用，不能去除勾选",false,null);
				}
			}
		    if(iscontinue){		    	
		    	//套餐名
		    	String comboname=obj.getString("comboname");
		    	//套餐编码
		    	String combocode=obj.getString("combocode");
		    	//套餐价格
		    	String combomoney=obj.getString("combomoney");
		    	//发型类型（服务类型）
				String fairtype=obj.getString("fairtype");
				//套餐类型（限次，不限次 ，单次）
				String combotype=obj.getString("combotype");
				//适用人群
				String fitgroup=obj.get("fitgroup")==null?"3":(String)obj.get("fitgroup");
				//备注
				String combonote=(String)obj.get("combonote");
				Integer combocount=null;
				if(combotype.equals("2")){
					 combocount=1;
				}else if(combotype.equals("3")){
					 combocount=obj.getInt("combocount");
				}
				//新增选中店铺集合
				String addshops=(String)obj.get("addshops");
				//修改之前已选中店铺集合
				String updateshops=(String)obj.get("updateshops");
				//产品集合
				String products=(String)obj.get("products");
				//删除产品集合
				String delproducts=(String)obj.get("delproducts");
				//根据id查询平台套餐信息
				combo=platformcomboService.getComboById(Long.parseLong(pkCombo));
				if(combo!=null){
		            combo.setCombocode(combocode);
		            combo.setComboname(comboname);
					combo.setCombomoney(Double.parseDouble(combomoney));
					combo.setCombocount(combocount);
					combo.setFairtype(fairtype);
					combo.setCombotype(combotype);
					combo.setFitgroup(fitgroup);		
					combo.setCombospy(CharUtil.getHeadChar(comboname));
					combo.setCombopy(CharUtil.chineseToPingyin(comboname));
					combo.setCombonote(combonote);
					combo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					//添加到批量修改处理
					editlist.add(combo);
				
					//保存分配的店铺
					if(addshops!=null&&!addshops.equals("")){
						JSONArray arr=JSONArray.fromObject(addshops);
						Iterator<?> iter=arr.iterator();
						while(iter.hasNext()){
							JSONObject oneobj=(JSONObject) iter.next();
							String pkShop=oneobj.getString("pkShop");
							ShopCombo newcombo=new ShopCombo();
							newcombo.setCombocode(combocode);
							newcombo.setComboname(comboname);
							newcombo.setStandardmoney(Double.parseDouble(combomoney));
							newcombo.setShopmoney(Double.parseDouble(combomoney));
							newcombo.setCurrentmoney(Double.parseDouble(combomoney));
							newcombo.setCombocount(combocount);
							newcombo.setFairtype(fairtype);
							newcombo.setCombotype(combotype);
							newcombo.setFitgroup(fitgroup);		
							newcombo.setCombospy(CharUtil.getHeadChar(comboname));
							newcombo.setCombopy(CharUtil.chineseToPingyin(comboname));
							newcombo.setCombonote(combonote);
							newcombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							newcombo.setDr((short)0);
							newcombo.setPkShop(Long.parseLong(pkShop));
							newcombo.setPkCombo(combo.getPkCombo());
							//加入批量添加中
							addlist.add(newcombo);
						}
					}
					//修改分配的店铺
					if(updateshops!=null&&!updateshops.equals("")){
						JSONArray arr=JSONArray.fromObject(updateshops);
						Iterator<?> iter=arr.iterator();
						while(iter.hasNext()){
							JSONObject oneobj=(JSONObject) iter.next();
							String pkShop=oneobj.getString("pkShop");
							ShopCombo editcombo=shopcomboService.queryByShopAndCombo(Long.parseLong(pkShop),Long.parseLong(pkCombo));
							if(editcombo!=null){
								editcombo.setCombocode(combocode);
								editcombo.setComboname(comboname);
								editcombo.setStandardmoney(Double.parseDouble(combomoney));
								editcombo.setShopmoney(Double.parseDouble(combomoney));
								editcombo.setCurrentmoney(Double.parseDouble(combomoney));
								editcombo.setCombocount(combocount);
								editcombo.setFairtype(fairtype);
								editcombo.setCombotype(combotype);
								editcombo.setFitgroup(fitgroup);		
								editcombo.setCombospy(CharUtil.getHeadChar(comboname));
								editcombo.setCombopy(CharUtil.chineseToPingyin(comboname));
								editcombo.setCombonote(combonote);
								editcombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								//加入批量修改
								editlist.add(editcombo);
							}
						}
				    }	
					//保存套餐的产品
					if(products!=null&&!products.equals("")){
						JSONArray arr=JSONArray.fromObject(products);
						Iterator<?> iter=arr.iterator();
						while(iter.hasNext()){
							JSONObject oneobj=(JSONObject) iter.next();
							String pkProduct=oneobj.getString("pkProduct");
							String productname=oneobj.getString("productname");
							String productunit=oneobj.getString("productunit");
							String productprice=oneobj.getString("productprice");
							PlatformComboProduct newproduct=new PlatformComboProduct();		
							newproduct.setPkProduct(Long.parseLong(pkProduct));
							newproduct.setProductname(productname);
							newproduct.setProductcount(1);
							newproduct.setProductprice(Double.parseDouble(productprice));
							newproduct.setProductunit(productunit);
							newproduct.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							newproduct.setDr((short)0);
							newproduct.setPkCombo(combo.getPkCombo());
							//加入批量添加事务中
							addlist.add(newproduct);
						}	
					}
					
					//删除不再选中的产品
					if(delproducts!=null&&!delproducts.equals("")){
						JSONArray arr=JSONArray.fromObject(delproducts);
						Iterator<?> iter=arr.iterator();
						while(iter.hasNext()){
							JSONObject oneobj=(JSONObject) iter.next();
							String pkProduct=oneobj.getString("pkProduct");
							PlatformComboProduct delProduct=platcomboprodcutService.queryByProductAndCombo(Long.parseLong(pkProduct),Long.parseLong(pkCombo));
							if(delProduct!=null){
								delProduct.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								delProduct.setDr((short)1);
								//加入批量添加中
								editlist.add(delProduct);
							}
					    }	
					}
					
					//批量保存店铺套餐和套餐产品
					basicservice.batchoperate(addlist, editlist);
					outputstr("", "修改平台套餐成功", true, null);
				}else{
					outputstr("", "此套餐不存在", false, null);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		    outputexceptionstr();
		}
		output(response, pojo);
	}
	 /**
     * 查询平台套餐
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("/queryplatcombo")
    public void queryplatcombo(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
	        String querylike=(String)obj.get("querylike");
	        String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			ArrayList<PlatformCombo> combos=new ArrayList<PlatformCombo>();
			ArrayList<PlatformCombo> totalcombolist=new ArrayList<PlatformCombo>();
			if(!curpage.equals("")&&!pagesize.equals("")){
				combos = (ArrayList<PlatformCombo>) platformcomboService.QueryByCon(querylike,Integer.parseInt(curpage),Integer.parseInt(pagesize));
				totalcombolist=(ArrayList<PlatformCombo>) platformcomboService.QueryByCon(querylike,null,null);
			}else{
				combos=(ArrayList<PlatformCombo>) platformcomboService.QueryByCon(querylike,null,null);
			}
			if(combos.size()>0){
				String data=JSONArray.fromObject(combos).toString();
				if(!curpage.equals("")){
				    outputstr(data, "查询成功", true, totalcombolist.size());
		        }else{
		        	outputstr(data, "查询成功", true, combos.size());
		        }
			}else{
				outputstr("", "暂无平台套餐信息", true, null);
			}	
		}catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
      	}
		output(response, pojo);
    }
	
	 /**
     * 查询平台套餐详情
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("/getbyid")
    public void getbyid(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
	        String pkCombo=obj.getString("pkCombo");
			PlatformCombo combo=platformcomboService.getComboById(Long.parseLong(pkCombo));
			if(combo!=null){
				JSONObject returnobj=JSONObject.fromObject(combo);
				ArrayList<PlatformComboProduct> products=(ArrayList<PlatformComboProduct>) platcomboprodcutService.QueryByCombo(pkCombo);
			    if(products.size()>0){
			    	returnobj.accumulate("products", JSONArray.fromObject(products).toString());
			    }
			    List<Object> shops=(List<Object>) shopcomboService.queryByPkCombo(Long.parseLong(pkCombo));
			    if(shops !=null){
			    	returnobj.accumulate("shops",shops);
			    }
		        outputstr(returnobj.toString(), "查询成功", true, null);
			}else{
				outputstr("", "暂无平台套餐信息", true, null);
			}	
		}catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
      	}
		output(response, pojo);
    }
	
	 /**
     * 查询平台服务和产品
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("/queryother")
    public void queryproductandservice(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			 JSONObject obj=RequestUtil.getPostString(request);	 
			 String  pkCombo=obj.getString("pkCombo");
			 ArrayList<PlatformComboProduct> products = (ArrayList<PlatformComboProduct>) platcomboprodcutService.QueryByCombo(pkCombo);
			 ArrayList<PlatformComboService> services = (ArrayList<PlatformComboService>) platcomboserviceService.QueryByCombo(pkCombo);
			 
			 JSONObject returnobj=new JSONObject();
			 if(products.size()>0){
				 String productdata=JSONArray.fromObject(products).toString();
				 returnobj.accumulate("products", productdata); 
			 }	 
			 if(services.size()>0){
				 String servicedata=JSONArray.fromObject(services).toString();
				 returnobj.accumulate("services", servicedata); 
			 }
			 if(returnobj.size()>0){
				 outputstr(returnobj.toString(), "查询产品和服务成功", true, null);   
			 }else{
				 outputstr("", "无产品和服务信息", false, null);   
			 }
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
      	}
		output(response, pojo);
    }

	/**
	 * 根据店铺主键和平台套餐主键查询信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/findbyshopid")
	public void findbyshopid(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCombo=obj.getString("pkCombo");
			String pkShop=obj.getString("pkShop");
			
			ShopCombo combo=shopcomboService.queryByShopAndCombo(Long.parseLong(pkShop),Long.parseLong(pkCombo));
			if(combo!=null){
				JSONObject returnobj=JSONObject.fromObject(combo);
				ArrayList<ShopFaireraward> awards=(ArrayList<ShopFaireraward>) shopfairerawardService.querybyshopandtype(pkShop, combo.getPkShopCombo().toString(), "COMBO");
			    if(awards.size()>0){
			    	returnobj.accumulate("awards", JSONArray.fromObject(awards).toString());
			    }
			    ArrayList<ShopGroup> pricegroup=(ArrayList<ShopGroup>) shopgroupService.QueryByShop(pkShop,"SERVICE");
				if(pricegroup.size()>0){
					returnobj.accumulate("pricegroup", JSONArray.fromObject(pricegroup).toString());
				}
				ArrayList<ShopGroup> additiongroup=(ArrayList<ShopGroup>) shopgroupService.QueryByShop(pkShop,"ADDITION");
				if(additiongroup.size()>0){
					returnobj.accumulate("additiongroup", JSONArray.fromObject(additiongroup).toString());
				}
			    outputstr(returnobj.toString(), "查询套餐信息成功", true, null);
			}else{
				outputstr("", "套餐不存在", false, null);
			}
		} catch (Exception e) {
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 修改店铺属性
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/editproperty")
	public void editproperty(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShopCombo=obj.getString("pkShopCombo");
			String pkShop=obj.getString("pkShop");
			String pkUser=obj.getString("pkUser");
	        String PkAdditiongroup=obj.get("pkAdditiongroup")==null?"":obj.getString("pkAdditiongroup");//附加项目组
	        String PkServicegroup=obj.get("pkServicegroup")==null?"":obj.getString("pkServicegroup");//服务组
	        String awards=obj.get("awards")==null?"":obj.getString("awards");//提成比例分摊
	        String combonote=obj.get("combonote")==null?"":obj.getString("combonote"); //备注
	        //根据店铺id和平台套餐主键查询店铺套餐
	        ShopCombo combo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));
	        String oldpkAdditiongroup=combo.getPkAdditiongroup()==null?"":combo.getPkAdditiongroup().toString();//原附加项目组
	        String oldpkServicegroup=combo.getPkServicegroup()==null?"":combo.getPkServicegroup().toString();//原附加项目组
	        String oldcombonote=combo.getCombonote()==null?"":combo.getCombonote(); 
	        //店铺套餐设置新值
	        combo.setCombonote(combonote);
	        combo.setPkAdditiongroup(PkAdditiongroup.equals("")?null:Long.parseLong(PkAdditiongroup));//附加项目组
	        combo.setPkServicegroup(PkServicegroup.equals("")?null:Long.parseLong(PkServicegroup)); //服务组
	        combo.setCombopy(CharUtil.chineseToPingyin(combo.getComboname()));
	        combo.setCombospy(CharUtil.getHeadChar(combo.getComboname()));
	        
	      	ArrayList<Object> updatelist=new ArrayList<Object>();
	    	ArrayList<Object> addlist=new ArrayList<Object>();
	      	//修改店铺套餐
	        shopcomboService.updateCombo(combo);
	        //如果是单次理发的话
	        if(combo.getFairtype()!=null&&combo.getFairtype().equals(FairType.TYPE_FIVE[0])
	        		&&combo.getCombotype()!=null&&combo.getCombotype().equals(ComboType.TYPE_TWO[0])){
	        	if(oldpkServicegroup!=null&&!oldpkServicegroup.equals("")&&PkServicegroup!=null&&!oldpkServicegroup.equals(PkServicegroup)){
	        		ArrayList<ShopPrice> pricelist= (ArrayList<ShopPrice>) shoppriceService.QueryBygroup(oldpkServicegroup);
	        	    if(pricelist.size()>0){
	        	    	for(ShopPrice price:pricelist){
	    	    			if(price!=null){
	    	     	    		price.setFairtype(null);
	            	    		updatelist.add(price);	
	    	    			}  
	        	    	}
	        	    }
	        	}
	        	if(PkServicegroup!=null&&!PkServicegroup.equals("")){
	        		ArrayList<ShopPrice> pricelist= (ArrayList<ShopPrice>) shoppriceService.QueryBygroup(PkServicegroup);
	        	    if(pricelist.size()>0){
        	    		for(ShopPrice price:pricelist){
        	    			if(price!=null){
        	     	    		price.setFairtype(FairType.TYPE_FIVE[0]);
                	    		updatelist.add(price);	
        	    			}           
	        	    	}
	        	    }
	        	}
	        }
	        //获取平台操作人信息
	        PlatUser user=platuserService.getUserById(Long.parseLong(pkUser));
	        //判断提成比例分摊
	        if(!awards.equals("")){
	        	JSONArray arr=JSONArray.fromObject(awards);
	        	Iterator<?> iter=arr.iterator();
	        	while(iter.hasNext()){
	        		JSONObject ontobj=(JSONObject) iter.next();
	        		String awardname=ontobj.getString("awardname")==null?"":ontobj.getString("awardname");
	        		String israte=ontobj.getString("israte")==null?"":ontobj.getString("israte");
	        		String awardmoney=ontobj.getString("awardmoney")==null?"":ontobj.getString("awardmoney");
	        		String pkFaireraward=ontobj.get("pkFaireraward")==null?"":ontobj.getString("pkFaireraward");
	        		String dr=ontobj.get("dr")==null?"0":ontobj.getString("dr");
	        		if(pkFaireraward.equals("")){
	        			ShopFaireraward newaward=new ShopFaireraward();
	        			newaward.setAwardname(awardname);
	        			newaward.setIsrate(israte);
	        			newaward.setAwardmoney(Double.parseDouble(awardmoney));
	        			newaward.setDr(Short.parseShort(dr));
	        			newaward.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        			newaward.setAwardtype("COMBO");
	        			newaward.setIsvalidate("Y");
	        			newaward.setPkShop(Long.parseLong(pkShop));
	        			newaward.setPkKey(Long.parseLong(pkShopCombo));
	        			newaward.setOperatorid(user.getPkPlatuser());
	        			newaward.setOperatename(user.getPlatusername());
	        			newaward.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        			//加入批量添加操作中
	        			addlist.add(newaward);
	        		}else{
	        			ShopFaireraward oldaward=shopfairerawardService.getawardById(Long.parseLong(pkFaireraward));
	        			oldaward.setAwardname(awardname);
	        			oldaward.setIsrate(israte);
	        			oldaward.setAwardmoney(Double.parseDouble(awardmoney));
	        			oldaward.setDr(Short.parseShort(dr));
	        			//加入批量修改操作中
	        			updatelist.add(oldaward);
	        		}
	        	}
	        	if(!oldpkAdditiongroup.equalsIgnoreCase(PkAdditiongroup)){
	        		ShopComboEditrecord record=new ShopComboEditrecord();
	        		record.setPkShop(Long.parseLong(pkShop));
	        		record.setPkShopCombo(Long.parseLong(pkShopCombo));
	        		record.setEditor(user.getPlatusername());
	        		record.setEditcontent("修改附加项目组,原:"+oldpkAdditiongroup+",现:"+PkAdditiongroup);
	        		record.setDr((short)0);
	        		record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        		record.setEdittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        		//加入批量添加操作中
	        		addlist.add(record);
	        	}
	        	if(!oldpkServicegroup.equalsIgnoreCase(PkServicegroup)){
	        		ShopComboEditrecord record=new ShopComboEditrecord();
	        		record.setPkShop(Long.parseLong(pkShop));
	        		record.setPkShopCombo(Long.parseLong(pkShopCombo));
	        		record.setEditor(user.getPlatusername());
	        		record.setEditcontent("修改服务价格组,原:"+oldpkServicegroup+",现:"+PkServicegroup);
	        		record.setDr((short)0);
	        		record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        		record.setEdittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        		//加入批量添加操作中
	        		addlist.add(record);
	        	}
	        	if(!oldcombonote.equalsIgnoreCase(combonote)){
	        		ShopComboEditrecord record=new ShopComboEditrecord();
	        		record.setPkShop(Long.parseLong(pkShop));
	        		record.setPkShopCombo(Long.parseLong(pkShopCombo));
	        		record.setEditor(user.getPlatusername());
	        		record.setEditcontent("修改备注信息,原:"+oldcombonote+",现:"+combonote);
	        		record.setDr((short)0);
	        		record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        		record.setEdittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
	        		//加入批量添加操作中
	        		addlist.add(record);
	        	}
	        	//批量操作
	        	basicservice.batchoperate(addlist, updatelist);
	        	outputstr("", "修改修改店铺属性成功", true, null);
	        }else{
	        	outputstr("", "修改修改店铺属性失败", false, null);
	        }        
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 查询抵用券
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/queryaward")
    public void queryaward(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCombo=obj.getString("pkCombo");
			ArrayList<Object> awards=(ArrayList<Object>)comboawardService.queryselect(pkCombo);
		    if(awards.size()>0){
		    	String data=JSONArray.fromObject(awards).toString();
		    	outputstr(data, "查询可用抵用券成功", true, null);
		    }else{
		    	outputstr("", "暂无可用抵用券", true, null);
		    }
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 保存抵用券
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/saveaward")
    public void saveaward(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCombo=obj.getString("pkCombo");//套餐主键
			String awards=obj.getString("awards");//抵用券
			
			JSONArray arr=JSONArray.fromObject(awards);
			Iterator<?> iter=arr.iterator();
			ArrayList<ComboAward> addlimitlists=new ArrayList<ComboAward>();
			ArrayList<ComboAward> updatelimitlists=new ArrayList<ComboAward>();
			while(iter.hasNext()){
				JSONObject oneobj=(JSONObject) iter.next();
				String dr=oneobj.get("dr")==null?"0":oneobj.getString("dr");
				String pkComboAward=oneobj.get("pkComboAward")==null?"":oneobj.getString("pkComboAward");
								
				if(pkComboAward.equals("")){
					String pkCustomeraward=oneobj.getString("pkCustomeraward");
					String awardname=oneobj.getString("awardname");
					String awardvalue=oneobj.getString("awardvalue");
					ComboAward award=new ComboAward();
					award.setCombofrom("PLAT");
					award.setAwardname(awardname);
					award.setAwardvalue(awardvalue);
					award.setPkCombo(Long.parseLong(pkCombo));
					award.setPkCustomeraward(Long.parseLong(pkCustomeraward));
					award.setDr((short)0);
					award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					
					addlimitlists.add(award);
				}else{
					ComboAward award=comboawardService.getAwardById(Long.parseLong(pkComboAward));					
					award.setDr(Short.parseShort(dr));
					award.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					updatelimitlists.add(award);
				}
			}
			if(addlimitlists.size()>0){
				comboawardService.savelist(addlimitlists);
			} 
			if(updatelimitlists.size()>0){
				comboawardService.updatelist(updatelimitlists);
			}
			outputstr("", "维护可发放抵用券成功", true, null);
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);		
	}
	
}
