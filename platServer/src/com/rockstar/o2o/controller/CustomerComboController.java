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
import com.rockstar.o2o.constant.Computetimetype;
import com.rockstar.o2o.pojo.CustomerCombo;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.CustomerOrder;
import com.rockstar.o2o.pojo.CustomerOwnaward;
import com.rockstar.o2o.pojo.CustomerUseaward;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;

@Controller
@RequestMapping("/customercombo")
public class CustomerComboController extends BaseController{

	 /**
     * 判断新增和取消套餐(Y)
     * @param request
     * @param response
     * @param model
     */
	@RequestMapping("/select")
    public void select(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			 JSONObject obj=RequestUtil.getPostString(request);
			 String isselect=obj.getString("isselect");
			 String pkCustomer=obj.get("pkCustomer")==null?"":obj.getString("pkCustomer");//消费者主键
			 String pkShop=obj.get("pkShop")==null?"":obj.getString("pkShop");//店铺主键
			 String pkShopCombo=obj.get("pkShopCombo")==null?"":obj.getString("pkShopCombo");//店铺套餐主键
			 String comboname=obj.get("comboname")==null?"":obj.getString("comboname");//店铺套餐名称
			 String combomoney=obj.get("currentmoney")==null?"0":obj.getString("currentmoney");//套餐价格
			 String totalcount=obj.get("totalcount")==null?"0":obj.getString("totalcount");//套餐次数
			 String pkFairer=obj.get("pkFairer")==null?"":obj.getString("pkFairer");//提成人主键
			 String fairername=obj.get("fairername")==null?"":obj.getString("fairername");//提成人姓名
			 String discount=obj.get("discount")==null?"0":obj.getString("discount");//优惠金额
			 //String leftcount=obj.getString("leftcount")==null?"0":obj.getString("leftcount");//剩余次数
			 String combobegintime=(String)obj.get("combobegintime");//开始时间
			 String comboendtime=(String)obj.get("comboendtime");//结束时间
			 String pkCustomercombo=obj.get("pkCustomerCombo")==null?"0":obj.getString("pkCustomerCombo");//行主键
			 String combotype=obj.get("combotype")==null?"":obj.getString("combotype");//套餐类型
			 String fairtype=obj.get("fairtype")==null?"":obj.getString("fairtype");//理发类型
			 String operateUser=obj.get("pkUser")==null?"":obj.getString("pkUser");//下单人;
			 String note=obj.get("note")==null?"":obj.getString("note");//备注;
			 String awards=obj.get("awards")==null?"":obj.getString("awards");	
			 String awardvalue=obj.get("awardvalue")==null?"0":obj.getString("awardvalue").equals("")?"0":obj.getString("awardvalue");	
			 String userphoto=obj.get("userphoto")==null?null:obj.getString("userphoto");	//用户头像
			 String usershortphoto=obj.get("usershortphoto")==null?"0":obj.getString("usershortphoto");	//用户头像缩略图
			 String commissionpeople=obj.get("commissionpeople")==null?"":obj.getString("commissionpeople");//提成人，多个人
			 
			 //新增套餐
			 if(isselect.equals("Y")){
		//		 //判断用户是否已经持有该套餐
		//		 CustomerCombo customershopcombo=customercomboService.QueryByCombo(pkShopCombo, pkCustomer);
		//		 if(customershopcombo!=null){
		//			 outputstr("", "用户已经持有该套餐,无需重复添加", false,null);
		//		 }else{
				 //根据消费者主键查询消费者信息
				 CustomerInfo cusinfo=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));
		
				 boolean istrue=true;		 
				 if(cusinfo.getCellphone()==null||cusinfo.getCellphone().equals("")){
					 outputstr("", "用户手机号码为空，不允许新增套餐", false, null);
					 istrue=false;
				 }
				 if(istrue){
					//根据店铺套餐主键查询店铺套餐信息
					 ShopCombo shopcombo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));
					 CustomerCombo combo=new CustomerCombo();
					 combo.setPkCustomer(Long.parseLong(pkCustomer));
					 combo.setPkShopCombo(Long.parseLong(pkShopCombo));
					 combo.setComboname(comboname);
					 combo.setCombomoney(Double.parseDouble(combomoney)-Double.parseDouble(discount)-Double.parseDouble(awardvalue));
					 combo.setTotalcount(Integer.parseInt(totalcount));
			         if(!shopcombo.getCombotype().equals("1")){
			        	 combo.setLeftcount(Integer.parseInt(totalcount));
			         }
			         combo.setFairtype(fairtype);
			         combo.setCombotype(combotype);
					 combo.setCombobegintime(combobegintime);
					 combo.setUserphoto(userphoto);
					 combo.setUsershortphoto(usershortphoto);
					 combo.setComboendtime(comboendtime);
					 combo.setPkFairer(pkFairer.equals("")?null:Long.parseLong(pkFairer));
					 combo.setFairername(fairername);
					 combo.setCommissionpeople(commissionpeople);
					 combo.setDiscount(Double.parseDouble(discount));
					 combo.setAwards(awards);
					 combo.setAwardvalue(Double.parseDouble(awardvalue));
					 combo.setNote(note);
					 combo.setCombobuytime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));//套餐购买时间
					 combo.setComputetimetype(Computetimetype.FIRSTUSETIME[0]);//套餐有效期开始时间计算，默认是第一次使用时间
					 combo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					 combo.setDr((short)0);
					 combo=customercomboService.save(combo);	
					 
					   //生成套餐订单
					   // if(!combotype.equals("2")){
					    CustomerOrder order=new CustomerOrder();
						order.setPkCustomer(Long.parseLong(pkCustomer));
						order.setCustomername(cusinfo.getCustomername());
						order.setCellphone(cusinfo.getCellphone());				
						order.setPkFairer(pkFairer.equals("")?null:Long.parseLong(pkFairer));
						order.setFairername(fairername);
						order.setOrderstatus("001");
						order.setPaystatus("001");
						order.setPkShop(Long.parseLong(pkShop));
						order.setDiscount(Double.parseDouble(discount));
						order.setPkCustomerCombo(combo.getPkCustomerCombo());
						order.setCombomoney(Double.parseDouble(combomoney)-Double.parseDouble(discount)-Double.parseDouble(awardvalue));
						order.setOrdermoney(Double.parseDouble(combomoney)-Double.parseDouble(awardvalue));
						//order.setFairermoney(shopcombo.getFairermoney()==null?null:order.getOrdermoney()*shopcombo.getFairermoney());
						order.setCommissionpeople(commissionpeople);
						order.setAwards(awards);
						order.setAwardvalue(Double.parseDouble(awardvalue));
						order.setOrdetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						order.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						order.setDr((short)0);		
						//下单人
						if(!operateUser.equals("")){
							order.setOperatorpk(Long.parseLong(operateUser));
							ShopUser shopuser=shopuserService.getUserById(Long.parseLong(operateUser));
							order.setOperator(shopuser==null?null:shopuser.getShopusername());
						}
						//ISCOMBO等于'Y'代表是订单套餐
						order.setIscombo("Y");
						//查询套餐名称		
						order.setPkShopCombo(Long.parseLong(pkShopCombo));
						order.setComboname(shopcombo.getComboname());
						order.setPkCombo(shopcombo.getPkCombo());
						//生成订单编号
						String date=DateUtil.getCurrDate(DateUtil.LONG_DATE_NOFORMAT);
						ShopInfo info=shopinfoService.getShopById(Long.parseLong(pkShop));
						String code=info.getShopshortcode()+date;
						List<Object> oo=customerorderService.querybysql(code,pkShop);
						if(oo.get(0)==null){
							code=code+"0001";
						}else{
							code=code+oo.get(0).toString();
						}
						order.setOrderno(code);
						order.setShopname(info.getShopname());
					   //保存套餐订单
						order=customerorderService.save(order);
					   //保存抵用券使用记录
					   if(!awards.equals("")){
						  ArrayList<CustomerUseaward> addlist=new ArrayList<CustomerUseaward>();
						  ArrayList<CustomerOwnaward> updatelist=new ArrayList<CustomerOwnaward>();
						  JSONArray arr=JSONArray.fromObject(awards);
						  Iterator<?> iter=arr.iterator();
						  while(iter.hasNext()){
							  JSONObject getobj=(JSONObject) iter.next();
							  String pkCustomeraward=getobj.getString("pkCustomeraward");//抵用券主键
							  String pkCustomerOwnaward=getobj.getString("pkCustomerOwnaward");//客户持有的抵用券主键			
							  CustomerUseaward useaward=new CustomerUseaward();
							  useaward.setPkCustomer(Long.parseLong(pkCustomer));
							  useaward.setPkCustomeraward(Long.parseLong(pkCustomeraward));
							  useaward.setPkCustomerOwnaward(Long.parseLong(pkCustomerOwnaward));
							  useaward.setPkOrder(order.getPkOrder());
							  useaward.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							  useaward.setDr((short)0);
							  useaward.setUsetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							  addlist.add(useaward);
							
							  CustomerOwnaward ownaward=customerownawardService.getAwardById(Long.parseLong(pkCustomerOwnaward));
							  ownaward.setAwardstatus("002");
							  //加入批量操作 
							  updatelist.add(ownaward);
						  }
						  basicservice.batchoperate(addlist, updatelist);
					   }
					   outputstr(JSONObject.fromObject(combo).toString(), "新增套餐成功", true,null);
					}
				// }
				 }else{
					 //取消套餐
					 if(pkCustomercombo!=null&&!pkCustomercombo.equals("0")){
						 CustomerCombo oldcombo=customercomboService.QueryByCombo(pkShopCombo,pkCustomer);
						 if(oldcombo.getLeftcount()!=null&&oldcombo.getLeftcount().compareTo(oldcombo.getTotalcount())!=0){
							 outputstr("", "套餐已开始使用,不允许取消", false,null);
						 }else{
							 oldcombo.setDr((short)1);
							 oldcombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							 customercomboService.updateCombo(oldcombo);
							 //取消套餐将生成的套餐的订单删除
							 ArrayList<CustomerOrder> oldorder = (ArrayList<CustomerOrder>) customerorderService.FindByComboAndPkcustomer(pkCustomer, pkShopCombo);
							 if(oldorder.size()>0){
								 CustomerOrder orderr=oldorder.get(0);
								 orderr.setDr((short)1);
								 orderr.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								 orderr.setOrdercontent("套餐取消");
								 customerorderService.updateOrder(orderr);
							 }
							 outputstr("", "取消套餐成功", true,null);
					     }
					 }
				}
			} catch (Exception e) {
				// TODO: handle exception
				  dealexception(e);
				  outputexceptionstr();
	      	}
			output(response, pojo);
	   }
	
	   /**
	    * 根据顾客id查询套餐信息--用户详情页列表(Y)
	    * @param request
	    * @param response
	    * @param model
	    */
	   @RequestMapping("/findbycustomer")
	   public void fingbycustomer(HttpServletRequest request,HttpServletResponse response,Model model){
			try {
		         JSONObject obj=RequestUtil.getPostString(request);
		         String PkCustomer=obj.getString("pkCustomer");
				 String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
				 String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
				 ArrayList<CustomerCombo> combolist=new ArrayList<CustomerCombo>();
				 ArrayList<CustomerCombo> totalcombolist=new ArrayList<CustomerCombo>();
				 if(!curpage.equals("")){
					  combolist=( ArrayList<CustomerCombo>) customercomboService.QueryByPkCustomer(PkCustomer,Integer.parseInt(curpage),Integer.parseInt(pagesize)); 
					  totalcombolist=( ArrayList<CustomerCombo>) customercomboService.QueryByPkCustomer(PkCustomer,null,null);
				 }else{
					  combolist=( ArrayList<CustomerCombo>) customercomboService.QueryByPkCustomer(PkCustomer,null,null);  
				 }
				 if(combolist.size()>0){
				 	  String data=JSONArray.fromObject(combolist).toString();
				 	  JSONObject returnobj=new JSONObject();
				 	  returnobj.accumulate("data", data);
				 	  returnobj.accumulate("curdate", DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT));
				 	  if(!curpage.equals("")){
				 		  outputstr(returnobj.toString(), "查询套餐信息成功", true,totalcombolist.size());
				 	  }else{
				 		  outputstr(returnobj.toString(), "查询套餐信息成功", true,combolist.size());	
				 	  }
				  }else{
					  outputstr("", "暂无套餐信息", true,null);
				  }
			} catch (Exception e) {
				// TODO: handle exception
				  dealexception(e);
	              outputexceptionstr();
			}
			output(response, pojo);
	   }
	   /**
	    * 根据消费者,套餐查询可用的抵用券(Y)
	    * @param request
	    * @param response
	    * @param model
	    */
	   @RequestMapping("/queryaward")
		public void queryaward(HttpServletRequest request,HttpServletResponse response,Model model) {		   
		   try {
			   JSONObject obj=RequestUtil.getPostString(request);
			   String pkCustomer=obj.getString("pkCustomer");
			   String pkCombo=obj.getString("pkCombo");
			
			   ArrayList<CustomerOwnaward> lists=(ArrayList<CustomerOwnaward>) customerownawardService.QueryBycustomerandcombo(pkCustomer, pkCombo);
			   if(lists.size()>0){
				   String data=JSONArray.fromObject(lists).toString();
				   outputstr(data, "查询可用抵用券成功", true, null);
			   }else{
				   outputstr("", "暂无可用抵用券", false, null);
			   }
		   } catch (Exception e) {
			   dealexception(e);
			   outputexceptionstr();
		   }
		   output(response, pojo);
	   }
	   
	   /**
	    * 查询套餐的提成项(Y)
	    * @param request
	    * @param response
	    * @param model
	    */
	   @RequestMapping("/querycomboaward")
	   public void querycomboaward(HttpServletRequest request,HttpServletResponse response,Model model) {	
		   	try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkShopCombo=obj.getString("pkShopCombo");
				String pkShop=obj.getString("pkShop");
				ArrayList<ShopFaireraward> awards=(ArrayList<ShopFaireraward>) shopfairerawardService.queryvalidate(pkShop, pkShopCombo, "COMBO");
				
				if(awards.size()>0){
					String data=JSONArray.fromObject(awards).toString();
					outputstr(data, "查询套餐提成项成功", true, null);
				}else{
					outputstr("", "暂无套餐提成项", true, null);
				}
			} catch (Exception e) {
				 dealexception(e);
				 outputexceptionstr();
			}
			output(response, pojo);
	    }
}
