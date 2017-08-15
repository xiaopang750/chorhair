package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.rockstar.o2o.constant.CustomerStatus;
import com.rockstar.o2o.constant.FairType;
import com.rockstar.o2o.constant.OrderFrom;
import com.rockstar.o2o.constant.OrderStatus;
import com.rockstar.o2o.constant.PayStatus;
import com.rockstar.o2o.pojo.CustomerCombo;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.CustomerOrder;
import com.rockstar.o2o.pojo.CustomerOrderBeautyrecord;
import com.rockstar.o2o.pojo.CustomerOrderDetail;
import com.rockstar.o2o.pojo.CustomerOrderSpitslot;
import com.rockstar.o2o.pojo.CustomerOrderTrace;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;
import com.rockstar.o2o.util.jsonvalidator.Validator;

/**
 * 用户下单操作
 * @author xc
 *
 */
@Controller
@RequestMapping("order")
public class CustomerOrderController extends BaseController{
	
	/**
	 * 平台查询当天所有店铺订单数（Y）
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("querydayorder")
	public void querydayorder(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			String date=DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT);
			int count=customerorderService.FindShopDayOrder(date);
			JSONObject returnobj=new JSONObject();
			returnobj.accumulate("count", count);
			outputstr(returnobj.toString(), "查询当天订单数成功", true,null);
		} catch (Exception e) {
		   	dealexception(e);
		   	outputexceptionstr();
		}
		output(response, pojo);		
	}
	
	/**
	 * 平台下预约单（Y）
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCustomer=obj.getString("pkCustomer");
			String cellphone=obj.get("cellphone")==null?"":obj.getString("cellphone");
			String pkFairer=obj.getString("pkFairer");
			String fairername=obj.getString("fairername");
			String fairermoney=(obj.get("fairermoney")==null||obj.get("fairermoney").equals(""))?"0":obj.getString("fairermoney");
			String fairprice=(obj.get("fairprice")==null||obj.get("fairprice").equals(""))?"0":obj.getString("fairprice");
			String pkPrice=obj.getString("pkPrice");
			String discount=(String)obj.get("discount");
			//String ordermoney=obj.getString("ordermoney");
			String pkShop=obj.getString("pkShop");
			String shopname=obj.getString("shopname");
			String operateUser=obj.get("pkUser")==null?"":obj.getString("pkUser");//下单人;
			String note=obj.get("note")==null?"":obj.getString("note");//备注
			String nocontainhair=obj.get("nocontainhair")==null?"N":obj.getString("nocontainhair");//是否包含理发
			String isfreeservice=obj.get("isfreeservice")==null?"N":obj.getString("isfreeservice");//是否免服务费
			//String isappointment=(String)obj.get("isappointment");//是否预约单
			String appointtime=obj.getString("appointtime");//预约时间			
			String pkShopCombo=obj.getString("pkShopCombo");	
			String pkCustomerCombo=obj.getString("pkCustomerCombo");	
			String addition=obj.get("addition")==null?"":obj.getString("addition");//附加项
			String commissionpeople=obj.get("commissionpeople")==null?"":obj.getString("commissionpeople");//提成人，多个人
				
			boolean iscontinue=true;
			if(commissionpeople!=null&&!commissionpeople.equals("")){
		    	Validator validator=new Validator(Validator.class.getResourceAsStream("commissionpeople"));
		    	int i=validator.validate(commissionpeople);		    	
		    	if(i>0){
		    		iscontinue=false;
		    		outputstr("", "提成人传入格式不正确", false, null);
		    	}
			}
			if(iscontinue){
				CustomerInfo cusinfo=customerinfoService.getCustomerById(Long.parseLong(pkCustomer)); 
				CustomerCombo combo=customercomboService.getComboById(Long.parseLong(pkCustomerCombo));
				if(combo!=null){
					if(combo.getIscomplete()!=null&&combo.getIscomplete().equals("Y")){
						outputstr("", "套餐已使用完毕", false,null);
					}else{
						boolean istrue=true; 
						if(combo.getCombobegintime()!=null&&!combo.getCombobegintime().equals("")){
							if(combo.getCombobegintime().compareTo(DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT))>0){
								outputstr("", "套餐不在有效期内", false,null);
								istrue=false;
							}
						}
					    if(combo.getComboendtime()!=null&&!combo.getComboendtime().equals("")){
							if(combo.getComboendtime().compareTo(DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT))<0){
						        outputstr("", "套餐不在有效期内", false,null);
								istrue=false;
							}
					    }	
						if(combo.getIsvalid()!=null&&combo.getIsvalid().equals("N")){
							outputstr("", "已在服务中，无需重复下单", false, null);
							istrue=false;
						}
						if(cusinfo.getCellphone()==null||cusinfo.getCellphone().equals("")){
							outputstr("", "用户手机号码为空，不允许下单", false, null);
							istrue=false;
						}
						
					    if(istrue){
						    String pkUser="";
						  	CustomerOrder order=new CustomerOrder();
							order.setPkCustomer(Long.parseLong(pkCustomer));
							order.setCustomername(cusinfo.getCustomername());
							pkUser=cusinfo.getPkUser()==null?"":cusinfo.getPkUser().toString();
							if(cellphone.equals("")){			
								order.setCellphone(cusinfo.getCellphone());			
							}else{
								order.setCellphone(cellphone);		
							}
							order.setPkFairer(Long.parseLong(pkFairer));
							order.setFairername(fairername);
							//预约单
							order.setOrderstatus(OrderStatus.APPOINTMENT[0]);
							//预约时间
							order.setAppointtime(appointtime);
							order.setPaystatus(PayStatus.NOPAY[0]);
							order.setPkShop(Long.parseLong(pkShop));
							order.setPkCustomerCombo(Long.parseLong(pkCustomerCombo));
							order.setShopname(shopname);
							order.setCommissionpeople(commissionpeople);
							order.setDiscount((discount!=null&&!discount.equals(""))?Double.parseDouble(discount):Double.parseDouble("0"));
							if(nocontainhair.equals("N")){
								order.setServicemoney(Double.parseDouble(fairermoney));
								order.setOrdermoney(Double.parseDouble(fairermoney));
							}else{
								order.setServicemoney(Double.parseDouble(fairermoney)-Double.parseDouble(fairprice));
								order.setOrdermoney(Double.parseDouble(fairermoney)-Double.parseDouble(fairprice));
							}
							
							//免服务费的话服务金额为0
							if(!isfreeservice.equals("N")){
								order.setServicemoney(Double.parseDouble("0"));
							}
							order.setPkPrice(Long.parseLong(pkPrice));
							order.setOrdercontent(note);
							order.setNocontainhair(nocontainhair);
							order.setIsfreeservice(isfreeservice);
							//下单人
							if(!operateUser.equals("")){
								order.setOperatorpk(Long.parseLong(operateUser));
								ShopUser shopuser=shopuserService.getUserById(Long.parseLong(operateUser));
								order.setOperator(shopuser==null?null:shopuser.getShopusername());
							}
							//查询普通服务的提成金额
							ShopPrice price=shoppriceService.getPriceById(order.getPkPrice());
							if(price!=null){
								order.setFairerservicemoney(price.getFairermoney());//普通服务的提成金额
							}
							order.setOrdetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							order.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							order.setDr((short)0);
								
							//查询套餐名称
							ShopCombo shopcombo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));			
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
							//保存下单店铺地址
							order.setProvince(info.getProvince());
							order.setProvincename(info.getProvincename());
							order.setCity(info.getCity());
							order.setCityname(info.getCityname());
							order.setCounty(info.getCounty());
							order.setCountyname(info.getCountyname());
							order.setAddr(info.getAddr());
							
							order.setOrderfrom(OrderFrom.FROM_PLAT[0]);
							order.setOrderno(code);
							order.setShopname(info.getShopname());
							
							//保存订单主表
							Object pk=basicservice.save(order);
							order.setPkOrder(Long.parseLong(pk.toString()));		
							
							ArrayList<Object> addlist=new ArrayList<Object>();		
							ArrayList<Object> updatelist=new ArrayList<Object>();
							
							//清空用户扫描下单标志
							if(cusinfo.getCustomerstatus()!=null&&cusinfo.getCustomerstatus().equals(CustomerStatus.SCANORDER[0])){
								cusinfo.setCustomerstatus(null);
								updatelist.add(cusinfo);
							}
							
							//判断是否存在附加项目
							if(!addition.equals("")){
								JSONArray array=JSONArray.fromObject(addition);
								Iterator<?> iter=array.iterator();
								while(iter.hasNext()){
									JSONObject jsonobj=(JSONObject) iter.next();
									String pkAddition=jsonobj.getString("pkAddition");
									String addtionname=jsonobj.getString("additionname");
									String additionmoney=jsonobj.getString("additionmoney");
									String tcpkFairer=jsonobj.get("pkFairer")==null?"":jsonobj.getString("pkFairer");
									String tcFairername=jsonobj.get("fairername")==null?"":jsonobj.getString("fairername");
									String awardsplit=jsonobj.get("awardsplit")==null?"":jsonobj.getString("awardsplit");//附加项目提成分摊
									CustomerOrderDetail addtiondetail=new CustomerOrderDetail();
									addtiondetail.setDetailtype("ADDITION");
									addtiondetail.setPkOrder(order.getPkOrder());
									addtiondetail.setDetailmoney(Double.parseDouble(additionmoney));
									addtiondetail.setPkDetail(Long.parseLong(pkAddition));
									addtiondetail.setDetailname(addtionname);
									addtiondetail.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									addtiondetail.setDr((short)0);
									addtiondetail.setAwardsplit(awardsplit);
									addtiondetail.setPkFairer(tcpkFairer.equals("")?null:Long.parseLong(tcpkFairer));
									addtiondetail.setFairername(tcFairername);
									addlist.add(addtiondetail);
								}
							}
							//保存订单轨迹					
							CustomerOrderTrace trace=new CustomerOrderTrace();
							trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							trace.setDr((short)0);
							trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							trace.setOperator(pkUser.toString());
							trace.setPkOrder(order.getPkOrder());
							trace.setOperatecontent("生成订单");
							addlist.add(trace);
							//批量保存订单明细及轨迹
							if(addlist.size()>0){
								basicservice.batchoperate(addlist, updatelist);
							}
					        if(combo.getCombotype().equals("2")){
					        	combo.setIsvalid("N");
					        	customercomboService.updateCombo(combo);
					        }
						    outputstr("", "订单录入成功", true,null);
						}
					}
				}else{
					outputstr("", "套餐信息不存在", false,null);
				}
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	
	
	/**
	 * 根据订单查询订单详情及美丽记录以及美丽记录的评论
	 * @param request
	 * @param response
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("querydetail")
	public void querydetail(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			
		    CustomerOrder order = customerorderService.getOrderById(Long.parseLong(pkOrder));
		    
		    if(order!=null){
		    	JSONObject returnobj=JSONObject.fromObject(order);
		    	String pkShopCombo=order.getPkShopCombo().toString();
				List<CustomerOrderDetail> details=customerorderdetailService.QueryByPkOrder(pkOrder);
				ArrayList<Object> additions=(ArrayList<Object>) shopadditionService.QueryBycombo(pkShopCombo);
				
				//查询附加项目
				if(additions.size()>0){
					ArrayList<JSONObject> list = new ArrayList<JSONObject> ();
					for(int j=0;j<additions.size();j++){
						JSONObject ooo=new JSONObject();
						HashMap a=(HashMap) additions.get(j);
						ooo.accumulate("pkAddition", a.get("pkAddition"));
						ooo.accumulate("additionname",a.get("additionname"));
						if(details.size()>0){
							for(int k=0;k<details.size();k++){
								CustomerOrderDetail detail=details.get(k);	
								if(detail.getDetailtype().equals("ADDITION")&&detail.getPkDetail().equals(a.get("pkAddition"))){
									ooo.accumulate("additionmoney",detail.getDetailmoney());
									ooo.accumulate("pkDetail",detail.getPkOrderDetail());
									break;
								}
							}
						}
						list.add(ooo);
					}
					returnobj.accumulate("addition", JSONArray.fromObject(list).toString());
				}
				
				//查询是否存在美丽记录
				ArrayList<CustomerOrderBeautyrecord> beautyrecords=(ArrayList<CustomerOrderBeautyrecord>) orderbeautyrecordService.QueryByOrder(pkOrder);
				if(beautyrecords.size()>0){
					String recorddatas=JSONArray.fromObject(beautyrecords).toString();
					returnobj.accumulate("records", recorddatas);
				}
				//查询美丽记录的评论情况
				ArrayList<CustomerOrderSpitslot> Spitslots=(ArrayList<CustomerOrderSpitslot>) orderspitslotService.FindBypkOrder(pkOrder);
				if(Spitslots.size()>0){
					String spitslotdatas=JSONArray.fromObject(Spitslots).toString();
					returnobj.accumulate("spitslots", spitslotdatas);
				}
				
                outputstr(returnobj.toString(), "查询订单详情成功", true,null);
		    }else{

		    	outputstr("", "无此订单", false,null);
		    	
		    }
		    
		} catch (Exception e) {
			dealexception(e);
			 outputexceptionstr();
		}
		output(response, pojo);
		
	}
	
	/**
	 * 根据用户查询所有订单
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("querybeauty")
	public void querybeauty(HttpServletRequest request,HttpServletResponse response,Model model){		
		try {			
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser=obj.getString("pkUser");//用户主键
			String usergroup=obj.getString("usergroup");//用户分组
			//根据条件查询
			ArrayList<CustomerOrder> orders=(ArrayList<CustomerOrder>) customerorderService.FindByPkUser(pkUser, usergroup);
			if(orders.size()>0){
				String data=JSONArray.fromObject(orders).toString();
                outputstr(data, "查询订单成功", true,null);
			}else{
				outputstr("", "暂无订单", true,null);
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 查询美丽记录列表
	 * 
	 */
	
	@RequestMapping("querybeautylist")
	public void querybeautylist(HttpServletRequest request,HttpServletResponse response,Model model){				
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser=obj.getString("pkUser");
			ArrayList<CustomerOrder> orders=(ArrayList<CustomerOrder>) customerorderService.FindBeautyByPkUser(pkUser);
		    if(orders.size()>0){
		    	ArrayList<JSONObject> lists=new ArrayList<JSONObject>();
		    	for(int i=0;i<orders.size();i++){
		    		CustomerOrder order=orders.get(i);
		            JSONObject returnobj=new JSONObject();
		            returnobj.accumulate("ordertime", order.getOrdetime());
		            returnobj.accumulate("pkOrder", order.getPkOrder());
		    		String pkOrder=order.getPkOrder().toString();
		    		ArrayList<CustomerOrderBeautyrecord> records=(ArrayList<CustomerOrderBeautyrecord>) orderbeautyrecordService.QueryByOrder(pkOrder);
		            ArrayList<CustomerOrderSpitslot> Spitslots=(ArrayList<CustomerOrderSpitslot>) orderspitslotService.FindBypkOrder(pkOrder);

		            if(records.size()>0){
				    	String recorddata=JSONArray.fromObject(records).toString();		    	
				    	returnobj.accumulate("records", recorddata);
				    }
		            if(Spitslots.size()>0){
		            	String Spitslotdata=JSONArray.fromObject(Spitslots).toString();		    	
				    	returnobj.accumulate("spitslots", Spitslotdata);
		            }
		            lists.add(returnobj);
		    	}
		    	String returndata=JSONArray.fromObject(lists).toString();
		    	
		    	outputstr(returndata, "查询美丽记录成功", true,null);
		    }else{

		    	outputstr("", "暂无美丽记录", true,null);
		    }
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		    output(response, pojo);
	}
	
	/**
	 * 根据订单查询美丽记录及评论
	 * @param request
	 * @param response
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("querybyorder")
	public void querybyorder(HttpServletRequest request,HttpServletResponse response,Model model){		
		try {			
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			String usergroup=obj.get("usergroup")==null?"":obj.getString("usergroup");
			CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
			if(order!=null){
			ArrayList<CustomerOrderBeautyrecord> records=(ArrayList<CustomerOrderBeautyrecord>) orderbeautyrecordService.QueryByOrder(pkOrder);
            ArrayList<CustomerOrderSpitslot> Spitslots=(ArrayList<CustomerOrderSpitslot>) orderspitslotService.FindBypkOrder(pkOrder);

            JSONObject returnobj=new JSONObject();
            returnobj.accumulate("orderinfo", JSONObject.fromObject(order));
            
            if(order.getIscombo()!=null&&order.getIscombo().equals("Y")){
          	  CustomerCombo combo = customercomboService.getComboById(order.getPkCustomerCombo());
          	  if(combo.getUserphoto()!=null&&!combo.getUserphoto().equals("")){
          		  returnobj.accumulate("isupload", "Y");
          		  returnobj.accumulate("userphoto", combo.getUserphoto());
          		  returnobj.accumulate("usershortphoto", combo.getUsershortphoto());
          	    }else{
          		 returnobj.accumulate("isupload", "N");  
          	    }
              }else{
            	  returnobj.accumulate("isupload", "N");  
              }
            
            //usergroup是002,需要查询附加项目
            if(!usergroup.equals("")&&usergroup.equals("002")){
			String pkCumbo=order.getPkShopCombo().toString();
			List<CustomerOrderDetail> details=customerorderdetailService.QueryByPkOrder(pkOrder);
			List<Object> additions=shopadditionService.QueryBycombo(pkCumbo);
			if(additions.size()>0){
				ArrayList<JSONObject> list = new ArrayList<JSONObject> ();
				for(int j=0;j<additions.size();j++){
					JSONObject ooo=new JSONObject();
					HashMap a=(HashMap) additions.get(j);
					ooo.accumulate("pkAddition", a.get("pkAddition"));
					ooo.accumulate("additionname", a.get("additionname"));

					ArrayList<ShopFaireraward>  awards=(ArrayList<ShopFaireraward>) shopfairerawardService.queryvalidate(order.getPkShop().toString(),  a.get("pkAddition").toString(), "ADDITION");
					if(awards.size()>0){
					ooo.accumulate("awardsplit",JSONArray.fromObject(awards).toString());	
					}
					
					if(details.size()>0){
						for(int k=0;k<details.size();k++){
							CustomerOrderDetail detail=details.get(k);	
							if(detail.getDetailtype().equals("ADDITION")&&detail.getPkDetail().equals(a.get("pkAddition"))){
								ooo.accumulate("additionmoney",detail.getDetailmoney());
								ooo.accumulate("pkDetail",detail.getPkOrderDetail());
								ooo.accumulate("pkFairer",detail.getPkFairer());
								ooo.remove("awardsplit");
								ooo.accumulate("awardsplit",detail.getAwardsplit()!=null?detail.getAwardsplit():JSONArray.fromObject(awards).toString());
								ooo.accumulate("fairername", detail.getFairername());
								break;
							}
						}
					}
					list.add(ooo);
				}
				returnobj.accumulate("addition", JSONArray.fromObject(list).toString());
			   }
            }	
			
            if(records.size()>0){
		    	String recorddata=JSONArray.fromObject(records).toString();		    	
		    	returnobj.accumulate("records", recorddata);
		    }
            if(Spitslots.size()>0){
            	String Spitslotdata=JSONArray.fromObject(Spitslots).toString();		    	
		    	returnobj.accumulate("spitslots", Spitslotdata);
            }

 
                outputstr(returnobj.toString(), "查询订单详情成功", true,null);
			}else{
				
		    	outputstr("", "订单不存在", true,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			 outputexceptionstr();
		}
				
		output(response, pojo);
	}
	
	
	 /**
	 * 插入吐槽详情
	 * @param request
	 * @param response
	 * @param model
	 */
	 @RequestMapping("spitslotbeauty")
	 public void spitslotbeauty(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			 JSONObject obj=RequestUtil.getPostString(request);
			 String pkOrder=obj.getString("pkOrder");//订单主键
			 String sendman=obj.getString("spitslotor");//吐槽人姓名
			 String sendmanid=obj.getString("spitslotorid");//吐槽人id
			 String replyman=obj.get("replyman")==null?"":obj.getString("replyman");//回复者姓名
			 String replymanid=obj.get("replymanid")==null?"":obj.getString("replymanid");//回复者id
			 String spitslotcontent=obj.getString("spitslotcontent");//吐槽内容
			 
			 CustomerOrderSpitslot spitslot=new CustomerOrderSpitslot();
			 spitslot.setPkOrder(Long.parseLong(pkOrder));
			 spitslot.setDr((short)0);
			 spitslot.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			 spitslot.setSpitslotor(sendman);
			 spitslot.setSpitslotorid(Long.parseLong(sendmanid));
			 spitslot.setSpitslottime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			 spitslot.setSpitslotcontent(spitslotcontent);
			 if(!replyman.equals("")){
				 spitslot.setReplyman(replyman);
				 spitslot.setReplymanid(Long.parseLong(replymanid));
				 spitslot.setReplytime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
			 }
			 spitslot=orderspitslotService.save(spitslot);
			 
			 outputstr(JSONObject.fromObject(spitslot).toString(), "吐槽成功咧", true,null);
			 
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			 outputexceptionstr();
		}
		 output(response, pojo);
	 }
	/**
	 * 根据订单批量插入美丽记录
	 * @param request
	 * @param response
	 * @param model
	 */
	 @RequestMapping("uploadbeauty")
	 public void uploadbeauty(HttpServletRequest request,HttpServletResponse response,Model model){		
		try {			
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			String pkUser=obj.getString("pkUser");
			String usergroup=obj.getString("usergroup");
			String urls=obj.getString("urls");
            
			CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
			
			if(order!=null){
		    ArrayList<Object> addlist=new ArrayList<Object>();
		    ArrayList<Object> updatelist=new ArrayList<Object>();
			JSONArray arrurls=JSONArray.fromObject(urls);
			Iterator<?> iter=arrurls.iterator();
			ArrayList<CustomerOrderBeautyrecord> recordlist=new ArrayList<CustomerOrderBeautyrecord>();
			
			while(iter.hasNext()){
				JSONObject urlobj=(JSONObject) iter.next();
				String url=urlobj.getString("url");
				String shorturl=urlobj.getString("shorturl");
				CustomerOrderBeautyrecord record=new CustomerOrderBeautyrecord();
				record.setPictureurl(url);
				record.setPictureshorturl(shorturl);
				record.setOperator(Long.parseLong(pkUser));
				record.setOperatorgroup(usergroup);
				record.setPkOrder(Long.parseLong(pkOrder));
				record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				record.setDr((short)0);
	
				addlist.add(record);
				
				//保存魅力纪录
				UserFile file=new UserFile();
				file.setBemodel("APP上传美丽记录");
				file.setFilepath(url);
				file.setThumbnail(shorturl);
				//file.setFilesize(FileManegeUtil.getsize("", url).getString("size"));
				file.setPkUser(Long.parseLong(pkUser));
				file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				file.setDr((short)0);

				
				addlist.add(file);
			}
			
			if(recordlist.size()>0){
				orderbeautyrecordService.savelist(recordlist);	
				if(order.getIsbeauty()==null||order.getIsbeauty().equals("")||order.getIsbeauty().equals("N")){
					//更新訂單是否生成美麗記錄的標誌
					order.setIsbeauty("Y");
					updatelist.add(order);
				}
			   }

		      basicservice.batchoperate(addlist, updatelist);
			  outputstr("", "您的美丽记录已经生成啦", true,null);
			
			}else{
				
			  outputstr("", "订单信息不存在", false,null);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			 outputexceptionstr();
		}
				
		output(response, pojo);
	}
	
	 /**
	  * 根据订单状态查询订单
	  * @param request
	  * @param response
	  * @param model
	  */
	 
	 @RequestMapping("findbystatus")
	 public void findbystatus(HttpServletRequest request,HttpServletResponse response,Model model){				 
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser=obj.getString("pkUser");
			String status=obj.getString("orderstatus");
			String usergroup=obj.getString("usergroup");			
			ArrayList<CustomerOrder> orderlist=(ArrayList<CustomerOrder>) customerorderService.FindByPkUserAndStatus(pkUser, usergroup, status);
		    
			if(orderlist.size()>0){
		
		    	 outputstr(JSONArray.fromObject(orderlist).toString(), "查询订单成功", true,orderlist.size());
		    }else{

		    	 outputstr("", "暂无订单", true,null);
		    }
	    } catch (Exception e) {
		// TODO: handle exception
		 dealexception(e);
		 outputexceptionstr();
	    }
	    output(response, pojo);
		 
	 }
	 
	 
	 /**
	  * 消费者评价
	  * @param request
	  * @param response
	  * @param model
	  */
	 
	 @RequestMapping("evaluate")
	 public void evaluate(HttpServletRequest request,HttpServletResponse response,Model model){				 
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkUser=obj.getString("pkUser");
			String pkOrder=obj.getString("pkOrder");
			//String usergroup=obj.getString("usergroup");	
			String star=obj.getString("evaluategoal");	
			
            CustomerOrder order = customerorderService.getOrderById(Long.parseLong(pkOrder));
           
             if(order!=null){
            	 CustomerInfo info=customerinfoService.getCustomerById(order.getPkCustomer());
                 if(info.getPkUser()==null||info.getPkUser().equals("")||(info.getPkUser()!=null&&!info.getPkUser().equals(Long.parseLong(pkUser)))){
                 	 outputstr("", "没有评价权限", false,null); 
                 }else{
                if(order.getEvaluategoal()!=null&&!order.getEvaluategoal().equals("")){
                	 outputstr("", "您已经评价过了,不用重复评价啦", false,null); 
                }else{
                  if(star.equals("0")){
                	 outputstr("", "请选择评价星级", false,null); 	  
                  }else{
               order.setEvaluategoal(Double.parseDouble(star));
               order.setEvaluatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
               int result=customerorderService.updateOrder(order);
               if(result==0){
                   outputstr("", "评价成功", true,null);
                }else{
            	   outputstr("", "评价失败", true,null); 
                 }  	 
                 }
                 }
                 }
              }else{    
            	   outputstr("", "订单不存在", false,null);
             }
		    } catch (Exception e) {
			// TODO: handle exception
			 dealexception(e);
			 outputexceptionstr();
		    }
		    output(response, pojo);
		 
	 }
	 
	 
	 /**
	  * 查询店铺业绩
	  * @param request
	  * @param response
	  * @param model
	  */
	 
	 @RequestMapping("queryrank")
	 public void queryrank(HttpServletRequest request,HttpServletResponse response,Model model){				 
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String type=obj.getString("type");
			String begintime=obj.get("begintime")==null?"":obj.getString("begintime");	
			String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
			String paymode=obj.get("paymode")==null?"":obj.getString("paymode");
             ArrayList<Object> arraylist=(ArrayList<Object>) customerorderService.findbytime(begintime, endtime, type, pkShop,paymode);
            if(arraylist.size()>0){	
               String data=JSONArray.fromObject(arraylist).toString();
           	   outputstr(data, "查询店铺业绩成功", true,null);
            }else{
               outputstr("", "暂无业绩", true,null);
              }
                   
		    } catch (Exception e) {
			// TODO: handle exception
			 dealexception(e);
			 outputexceptionstr();
		    }
		    
		    output(response, pojo);
		 
	 }
	 
	 /**
	  * 根据店铺查询所有订单
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("querybyshop")
	 public void querybyshop(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pageSize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			String begintime=obj.get("begintime")==null?"":obj.getString("begintime");
			String endtime=obj.get("endtime")==null?"":obj.getString("endtime");
			String paystatus=obj.get("paystatus")==null?"":obj.getString("paystatus");
			String orderno=obj.get("orderno")==null?"":obj.getString("orderno");
			ArrayList<CustomerOrder>  orderlist=new ArrayList<CustomerOrder>();
			ArrayList<CustomerOrder> totalorderlist=new ArrayList<CustomerOrder>();
			if(!curpage.equals("")){
			orderlist=(ArrayList<CustomerOrder>) customerorderService.querybyshop(pkShop,Integer.parseInt(curpage),Integer.parseInt(pageSize),begintime,endtime,paystatus,orderno);	
			totalorderlist=(ArrayList<CustomerOrder>) customerorderService.querybyshop(pkShop,null,null,begintime,endtime,paystatus,orderno);
			}else{
		    orderlist=(ArrayList<CustomerOrder>) customerorderService.querybyshop(pkShop,null,null,begintime,endtime,paystatus,orderno);
			}
			if(orderlist.size()>0){
		    	String returnstr=JSONArray.fromObject(orderlist).toString();
		    	int totalcount=totalorderlist.size();
		    	if(!curpage.equals("")){
		    	outputstr(returnstr, "查询订单成功",true,totalcount);
		    	}else{
		    	outputstr(returnstr, "查询订单成功",true,orderlist.size());	
		    	}
		    }else{
		    	outputstr("", "暂无订单",true,null);	
		    }
		 
		 } catch (Exception e) {
			// TODO: handle exception
			 dealexception(e);
			 outputexceptionstr();
		}
		  output(response, pojo);
	 }
	 
	 /**
	  * 快速理发下单
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("fastorder")
	 public void fastorder(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkCustomer=obj.getString("pkCustomer");
			CustomerInfo info=customerinfoService.getCustomerById(Long.parseLong(pkCustomer));
			String sevicename="";
			if(info!=null){		  
			  String pkShop=obj.getString("pkShop");			 
			  if(info.getPkShop().toString().equalsIgnoreCase(pkShop)){
				  String customername=info.getCustomername();
				  String fasttype=(String)obj.get("fasttype");
				  
				  String fairertype="";
				  //fasttype==1代表理发下单，2代表洗头下单，3代表造型下单
				  if(fasttype==null||fasttype.equals("1")){
					  fairertype= FairType.TYPE_FIVE[0];
					  sevicename="理发";
				  }else if(fasttype.equals("2")){
					  fairertype= FairType.TYPE_SEVEN[0];
					  sevicename="洗头";
				  }else if(fasttype.equals("3")){
					  fairertype= FairType.TYPE_EIGHT[0];
					  sevicename="造型";
				  }
				  
				  ArrayList<ShopCombo> combos=(ArrayList<ShopCombo>) shopcomboService.QueryByShopAndtype(pkShop, fairertype, ComboType.TYPE_TWO[0]);			
				  if(combos.size()>0){
					 ShopCombo combo=combos.get(0);
					 if(combo.getPkServicegroup()==null||combo.getPkServicegroup().equals("")){		
						outputstr("", "没有维护对应的价格组", false, null);		
					 }else{
						 CustomerCombo customercombo=new CustomerCombo();
						 customercombo.setPkCustomer(Long.parseLong(pkCustomer));
						 customercombo.setPkShopCombo(combo.getPkShopCombo());
						 customercombo.setComboname(combo.getComboname());
						 customercombo.setCombomoney(combo.getCurrentmoney());
				         customercombo.setFairtype(fairertype);
				         customercombo.setTotalcount(1);
				         customercombo.setLeftcount(1);
				         customercombo.setCombotype(ComboType.TYPE_TWO[0]);
				         customercombo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				         customercombo.setDr((short)0);
				         customercombo=customercomboService.save(customercombo);
				         JSONObject returnobj=new JSONObject();
				         returnobj.accumulate("pkCustomerCombo", customercombo.getPkCustomerCombo());
				         returnobj.accumulate("pkShopcombo", customercombo.getPkShopCombo());
				         returnobj.accumulate("pkCustomer", pkCustomer);
				         returnobj.accumulate("comboname", combo.getComboname());
				         returnobj.accumulate("customername", customername);
				         returnobj.accumulate("fairtype", combo.getFairtype());
				         outputstr(returnobj.toString(), "下单成功", true, null);
					}
				  }else{
					  outputstr("", sevicename+"服务不存在", false, null);
				  }
			  }else{
				 outputstr("", "会员和店铺信息不匹配", false, null);
			  }
			}else{
				 outputstr("", "会员不存在", false, null);
			}
		} catch (Exception e) {
			 dealexception(e);
			 outputexceptionstr();
		}
		 output(response, pojo);
	 }
	 
	 /**
	  * 根据订单状态查询订单{001","服务中"},{"002","服务完成"},{"003","预约单"},{"004","订单取消"}
	  * 
	  * 查询预约单
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("queryappointment")
	 public void queryappointment(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			 
			JSONObject receiveobj=RequestUtil.getPostString(request); 
			//省
			String province=(String)receiveobj.get("province");
			//市
			String city=(String)receiveobj.get("city");
			//区
			String county=(String)receiveobj.get("county");
			//店铺主键
			String pkShop=(String)receiveobj.get("pkShop");
			//输入查询条件
			//此项输入的是客户名或者手机号
			String likequery=(String)receiveobj.get("querylike");			
			
			String begintime=(String)receiveobj.get("begintime");			
			String endtime=(String)receiveobj.get("endtime");
			String curpage=(String)receiveobj.get("curpage");			
			String pagesize=(String)receiveobj.get("pagesize");			
			
			ArrayList<CustomerOrder>  orderlist=new ArrayList<CustomerOrder>();
			ArrayList<CustomerOrder> totalorderlist=new ArrayList<CustomerOrder>();
			if(curpage!=null&&!curpage.equals("")){
		    orderlist =(ArrayList<CustomerOrder>) customerorderService.querybycon(province,city,county,pkShop, Integer.parseInt(curpage), Integer.parseInt(pagesize), begintime, endtime, OrderStatus.APPOINTMENT[0],likequery);
			totalorderlist=(ArrayList<CustomerOrder>) customerorderService.querybycon(province,city,county,pkShop,null,null, begintime, endtime, OrderStatus.APPOINTMENT[0],likequery);
			}else{
			orderlist=(ArrayList<CustomerOrder>) customerorderService.querybycon(province,city,county,pkShop,null,null, begintime, endtime, OrderStatus.APPOINTMENT[0],likequery);	
			}
			 
			if(orderlist.size()>0){
				    String data=JSONArray.fromObject(orderlist).toString();
		    	    if(curpage!=null&&!curpage.equals("")){
			    	outputstr(data, "查询预约单成功",true,totalorderlist.size());
			    	}else{
			    	outputstr(data, "查询预约单成功",true,orderlist.size());	
			    	}
			       }else{
		    	    outputstr("", "暂无预约单",true,null);		
		           }
		    } catch (Exception e) {
			  dealexception(e);
			  outputexceptionstr();
		   }
		  output(response, pojo);
	 }
	 
	 /**
	  * 预约单取消
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("cancelappoint")
	 public void cancelappoint(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkOrder=obj.getString("pkOrder");
				String pkUser=obj.getString("pkUser");
				String cancelreason=(String)obj.get("cancelreason");
				
				CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));

				if(order!=null){								
					String status=order.getOrderstatus();
					if(status.equals(OrderStatus.APPOINTMENT[0])){
						ArrayList<Object> addlist=new ArrayList<Object>();
						ArrayList<Object> updatelist=new ArrayList<Object>();
						
						order.setOrderstatus(OrderStatus.CANCEL[0]);
						order.setCancelreason(cancelreason);
						updatelist.add(order);
						
						CustomerCombo combo=customercomboService.getComboById(order.getPkCustomerCombo());
				        if(combo.getCombotype().equals("2")){
				    	    combo.setIsvalid("Y");
				    	    updatelist.add(combo);
				        }
				        
						//保存订单轨迹					
						CustomerOrderTrace trace=new CustomerOrderTrace();
						trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						trace.setDr((short)0);
						trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						trace.setOperator(pkUser.toString());
						trace.setPkOrder(Long.parseLong(pkOrder));
						trace.setOperatecontent("预约单取消");
						addlist.add(trace);
						
						basicservice.batchoperate(addlist, updatelist);
					
						outputstr("", "取消预约单成功", true, null);
					}else{
						outputstr("", "当前订单状态无法取消", false, null);	
					}
			    }else{
					outputstr("", "订单不存在", false, null);
				}
			} catch (Exception e) {
				  dealexception(e);
				  outputexceptionstr();
			}
		   output(response, pojo);
	 }
	 
	 
	 /**
	  * 获取单条预约单信息
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("querybyid")
	 public void querybyid(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			
			CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
			
			if(order!=null){
				String data=JSONObject.fromObject(order).toString();
				outputstr(data, "查询预约单详情成功", true, null);
				
			}else{
				outputstr("", "预约单不存在", false, null);
			}
			
	     } catch (Exception e) {
	    	 dealexception(e);
	    	 outputexceptionstr();
	     }
	     output(response, pojo);
    }
	 
	 
	 /**
	  * 编辑预约单
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("editpreorder")
	 public void editorder(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			String pkUser=obj.getString("pkUser");
			String pkFairer=obj.getString("pkFairer");
			String ordermoney=obj.getString("ordermoney");
			String fairername=obj.getString("fairername");
			String appointtime=obj.getString("appointtime");
			String fairermoney=(obj.get("fairermoney")==null||obj.get("fairermoney").equals(""))?"0":obj.getString("fairermoney");
			String fairprice=(obj.get("fairprice")==null||obj.get("fairprice").equals(""))?"0":obj.getString("fairprice");
			String pkPrice=obj.get("pkPrice")==null?"0":obj.getString("pkPrice");
			String note=obj.get("note")==null?"":obj.getString("note");//备注
			String nocontainhair=obj.get("nocontainhair")==null?"N":obj.getString("nocontainhair");//是否包含理发
			String isfreeservice=obj.get("isfreeservice")==null?"N":obj.getString("isfreeservice");//是否免服务费
			String addition=obj.get("addition")==null?"":obj.getString("addition");	
			String commissionpeople=obj.get("commissionpeople")==null?"":obj.getString("commissionpeople");//提成人，多个人
				
			boolean iscontinue=true;
			if(commissionpeople!=null&&!commissionpeople.equals("")){
		    	Validator validator=new Validator(Validator.class.getResourceAsStream("commissionpeople"));
		    	int i=validator.validate(commissionpeople);		    	
		    	if(i>0){
		    		iscontinue=false;
		    		outputstr("", "提成人传入格式不正确", false, null);
		    	}
			}
			
			if(iscontinue){
				CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
				if(order==null){
					outputstr("", "订单不存在", false, null);
				}else{
					if(order.getOrderstatus().equals(OrderStatus.APPOINTMENT[0])){
						ArrayList<Object> addlist=new ArrayList<Object> ();//新增
						ArrayList<Object>  updatelist=new ArrayList<Object> ();//更新
					    //判断是否存在附加项目
					    if(!addition.equals("")){
							JSONArray array=JSONArray.fromObject(addition);
							Iterator<?> iter=array.iterator();
							while(iter.hasNext()){
								JSONObject jsonobj=(JSONObject) iter.next();
								String pkAddition=jsonobj.getString("pkAddition");
								String addtionname=jsonobj.getString("additionname");
								String additionmoney=jsonobj.get("additionmoney")==null?"0":jsonobj.getString("additionmoney");
								String tcpkFairer=jsonobj.get("pkFairer")==null?"":jsonobj.getString("pkFairer");
								String tcFairername=jsonobj.get("fairername")==null?"":jsonobj.getString("fairername");
								String pkDetail=jsonobj.get("pkDetail")==null?"":jsonobj.getString("pkDetail");
								String awardsplit=jsonobj.get("awardsplit")==null?"":jsonobj.getString("awardsplit");//附加项目提成分摊
								Double addmoney=Double.parseDouble(additionmoney);
								//pkDetail为空,代表新增附加项目,直接插入
								if(pkDetail.equals("")){					
									CustomerOrderDetail addtiondetail=new CustomerOrderDetail();
									addtiondetail.setDetailtype("ADDITION");//明细类型，ADDTION代表是附加项目
									addtiondetail.setDetailmoney(addmoney);
									addtiondetail.setPkDetail(Long.parseLong(pkAddition));//附加项目的主键
									addtiondetail.setDetailname(addtionname);
									addtiondetail.setPkOrder(Long.parseLong(pkOrder));
									addtiondetail.setPkFairer(tcpkFairer.equals("")?null:Long.parseLong(tcpkFairer));//提成人主键,后面计算需要
									addtiondetail.setFairername(tcFairername);//提成人姓名
									addtiondetail.setAwardsplit(awardsplit);//附加项目提成分摊					
									addlist.add(addtiondetail);
								}else{
									//pkDetail不为空,直接在原来的基础上更新信息即可
									CustomerOrderDetail detail=customerorderdetailService.getDetailById(Long.parseLong(pkDetail));
									detail.setDetailmoney(addmoney);											
									detail.setPkFairer(tcpkFairer.equals("")?null:Long.parseLong(tcpkFairer));//提成人主键,后面计算需要
									detail.setFairername(tcFairername);//提成人姓名
									detail.setAwardsplit(awardsplit);//提成人分摊
									updatelist.add(detail);
								}					
							}
						}
						//更新订单主表信息
						order.setPkFairer(Long.parseLong(pkFairer));
						order.setFairername(fairername);
						order.setCommissionpeople(commissionpeople);
						order.setOrdermoney(Double.parseDouble(ordermoney));
						if(nocontainhair.equals("N")){
							order.setServicemoney(Double.parseDouble(fairermoney));
						}else{
							order.setServicemoney(Double.parseDouble(fairermoney)-Double.parseDouble(fairprice));	
						}
						
						//免服务费的话服务金额为0
						if(!isfreeservice.equals("N")){
							order.setServicemoney(Double.parseDouble("0"));
						}
						order.setPkPrice(Long.parseLong(pkPrice));
						order.setOrdercontent(note);
						order.setAppointtime(appointtime);
						order.setNocontainhair(nocontainhair);
						order.setIsfreeservice(isfreeservice);
						//查询普通服务的提成金额
						ShopPrice price=shoppriceService.getPriceById(order.getPkPrice());
						if(price!=null){
							order.setFairerservicemoney(price.getFairermoney());//普通服务的提成金额
						}
						order.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						
						updatelist.add(order);
						
						//保存订单轨迹					
						CustomerOrderTrace trace=new CustomerOrderTrace();
						trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						trace.setDr((short)0);
						trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						trace.setOperator(pkUser);
						trace.setPkOrder(Long.parseLong(pkOrder));
						trace.setOperatecontent("预约单修改");
						addlist.add(trace);
						
						//批量操作订单
						basicservice.batchoperate(addlist, updatelist);	
						outputstr("", "编辑预约单成功", true, null);
			        }else{
			        	outputstr("", "当前订单不允许编辑", false, null);
			        }
			    }
			}
		} catch (Exception e) {
			dealexception(e);
			outputexceptionstr();
		}
     	output(response, pojo);
	 }
}
