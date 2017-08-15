package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.rockstar.o2o.constant.Computetimetype;
import com.rockstar.o2o.constant.CustomerStatus;
import com.rockstar.o2o.constant.Detailtype;
import com.rockstar.o2o.constant.FairType;
import com.rockstar.o2o.constant.OrderFrom;
import com.rockstar.o2o.constant.OrderStatus;
import com.rockstar.o2o.constant.PayStatus;
import com.rockstar.o2o.mongodb.pojo.CommentList;
import com.rockstar.o2o.pojo.BdCustomerAward;
import com.rockstar.o2o.pojo.BdCustomerAwardLimit;
import com.rockstar.o2o.pojo.ComboAward;
import com.rockstar.o2o.pojo.CustomerCombo;
import com.rockstar.o2o.pojo.CustomerComboUserecord;
import com.rockstar.o2o.pojo.CustomerInfo;
import com.rockstar.o2o.pojo.CustomerOrder;
import com.rockstar.o2o.pojo.CustomerOrderBeautyrecord;
import com.rockstar.o2o.pojo.CustomerOrderDetail;
import com.rockstar.o2o.pojo.CustomerOrderSpitslot;
import com.rockstar.o2o.pojo.CustomerOrderTrace;
import com.rockstar.o2o.pojo.CustomerOwnaward;
import com.rockstar.o2o.pojo.FairerCredit;
import com.rockstar.o2o.pojo.FairerInfo;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopInfo;
import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.pojo.UserFile;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RedisUtils;
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
	 * 保存订单主表及明细表
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
		String isappointment=(String)obj.get("isappointment");//是否预约单
		String pkShopCombo=obj.getString("pkShopCombo");	
		String pkCustomerCombo=obj.getString("pkCustomerCombo");	
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
		//是否为预约单
		if(isappointment!=null&&isappointment.equals("Y")){
		
		String appointtime=obj.getString("appointtime");
		order.setOrderstatus(OrderStatus.APPOINTMENT[0]);
		order.setAppointtime(appointtime);
		
		}else{
			order.setOrderstatus(OrderStatus.SERVICEING[0]);
		}
		order.setPaystatus(PayStatus.NOPAY[0]);
		order.setPkShop(Long.parseLong(pkShop));
		order.setPkCustomerCombo(Long.parseLong(pkCustomerCombo));
		order.setShopname(shopname);
		order.setCommissionpeople(commissionpeople);
		//order.setOrdermoney(Double.parseDouble(ordermoney));
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
		
		order.setOrderfrom(OrderFrom.FROM_SHOP[0]);
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
		
//		if(!pkUser.equals("")){
//	    //保存消息列表
//      MessageList  message=new MessageList();
//      message.setSender("ROCKSTAR");
//      message.setMessagetype(MessageType.TYPE_PLAT);
//      message.setReceiver(pkUser);
//      message.setLastmessage("您的【"+order.getComboname()+"】服务订单已经生成了，记得拍一张留个念哦！【"+order.getShopname()+"】");
//      message.setSendtype("AUTO");
//      message.setPkShop(pkShop.equals("")?null:Long.parseLong(pkShop));
//      message.setMessagegroup("001");
//      message.setMessagestatus("001");
//      message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
//      
//      message=messagelistService.save(message);
//      //保存消息明细
//      MessageRecord record=new MessageRecord();
//      record.setSender("ROCKSTAR");
//      record.setSendtime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
//      record.setSendresult("Y");
//      record.setContent("您的【"+order.getComboname()+"】服务订单已经生成了，记得拍一张留个念哦！【"+order.getShopname()+"】");
//      record.setReceiver(pkUser);
//      record.setMessagestatus("001");   
//      record.setMessagegroup("001");
//      record.setMessagetype(MessageType.TYPE_PLAT);
//      record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
//      record.setDr((short)0);
//	    record.setPkMessagelist(message.getPkMessagelist());
//	    messageservice.save(record);
	    
	    //推送微信通知
//	    ArrayList<Object> mode=(ArrayList<Object>) basicservice.query(UserVerificationmode.class, " pkUser = ?  and verificationType = ? ", Long.parseLong(pkUser),VerificationMode.MODE_TWO[0]);	    
//	    if(mode.size()>0){
//	    	String openid=((UserVerificationmode)mode.get(0)).getOpenid();
//	    	JSONObject wxobj=new JSONObject();
//	    	wxobj.accumulate("touser", openid);
//	    	wxobj.accumulate("msgtype", "msgtype");
//	    	JSONObject textobj=new JSONObject();
//	    	textobj.accumulate("content", "您的【"+order.getComboname()+"】服务订单已经生成,订单金额："+order.getOrdermoney()+"订单编号："+order.getOrderno()+".【"+order.getShopname()+"】");
//	    	wxobj.accumulate("text", textobj.toString());
//			 //读取配置文件参数
//			String url = WxserviceUtil.GetUrl("sendmsg");
//			 //获取返回值
//		    String  jsonResult = this.httpservice.sendPostRequset(url, wxobj.toString());
//		    System.out.println(jsonResult);
//	       }
		//}
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
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		
		   output(response, pojo);
		
	}
	
	/**
	 * 查询当天订单数
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("querydayorder")
	public void querydayorder(HttpServletRequest request,HttpServletResponse response,Model model){
		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			String date=DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT);
			int count=customerorderService.FindShopDayOrder(pkShop, date);
			JSONObject returnobj=new JSONObject();
			returnobj.accumulate("count", count);
			
			outputstr(returnobj.toString(), "查询当天订单数成功", true,null);
			
		   } catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		  }
		     output(response, pojo);		
	   }
	
	/**
	 * 控制单查询订单,未支付订单
	 * @param request
	 * @param response
	 * @param model
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("queryunorder")
	public void queryunorder(HttpServletRequest request,HttpServletResponse response,Model model){		
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShop=obj.getString("pkShop");
			//String date=DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT);
			String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
			String pageSize=obj.get("pagesize")==null?"":obj.getString("pagesize");
			String begintime=(String)obj.get("begintime");
			String endtime=(String)obj.get("endtime");
			List<CustomerOrder> orders=new ArrayList<CustomerOrder>();
			List<CustomerOrder> totalorders=new ArrayList<CustomerOrder>();
			if(!curpage.equals("")){
			orders=customerorderService.FindByShopAndDateAndPay(pkShop, begintime,endtime, "001",Integer.parseInt(curpage),Integer.parseInt(pageSize));
			totalorders=customerorderService.FindByShopAndDateAndPay(pkShop, begintime,endtime, "001",null,null);
			}else{
			orders=customerorderService.FindByShopAndDateAndPay(pkShop, begintime,endtime, "001",null,null);
			}
			if(orders.size()>0){
				ArrayList<JSONObject> returnobj=new ArrayList<JSONObject>();
				for(int i=0;i<orders.size();i++){
					CustomerOrder order=orders.get(i);
					String pkShopCombo=order.getPkShopCombo().toString();
					String pkOrder=order.getPkOrder().toString();
					JSONObject ob = new JSONObject();
					
					List<CustomerOrderDetail> details=customerorderdetailService.QueryByPkOrder(pkOrder,Detailtype.ADDITION[0]);

					HashMap<Long,CustomerOrderDetail> map=new HashMap<Long, CustomerOrderDetail>();
					if(details.size()>0){
						for(int k=0;k<details.size();k++){
							CustomerOrderDetail detail=details.get(k);	
							map.put(detail.getPkDetail(),detail);
						}
					}

					
					ArrayList<Object> additions=(ArrayList<Object>) shopadditionService.QueryBycombo(pkShopCombo);
					
					ob.accumulate("pkOrder", pkOrder);
					ob.accumulate("cellphone", order.getCellphone());
					ob.accumulate("servicemoney", order.getServicemoney());
					ob.accumulate("customername", order.getCustomername());
					ob.accumulate("comboname", order.getComboname());
					ob.accumulate("fairername", order.getFairername());
					ob.accumulate("ordermoney", order.getOrdermoney());
					ob.accumulate("iscombo", order.getIscombo());
					ob.accumulate("discount", order.getDiscount());
					ob.accumulate("pkCombo", order.getPkCombo());
					
					ArrayList<JSONObject> list = new ArrayList<JSONObject> ();
					
					//先查询现在的附加项目
					if(additions.size()>0){						
						for(int j=0;j<additions.size();j++){
							JSONObject ooo=new JSONObject();
							HashMap a=(HashMap) additions.get(j);
							ooo.accumulate("pkAddition", a.get("pkAddition"));
							ooo.accumulate("additionname", a.get("additionname"));
                            
							ArrayList<ShopFaireraward>  awards=(ArrayList<ShopFaireraward>) shopfairerawardService.queryvalidate(pkShop,  a.get("pkAddition").toString(), "ADDITION");
							if(awards.size()>0){
							ooo.accumulate("awardsplit",JSONArray.fromObject(awards).toString());	
							}
                            
							Object oldobj=map.get(Long.parseLong(a.get("pkAddition").toString()));
							//如果已经维护该附加项目，则把之前维护的带过来
							if(oldobj!=null&&!oldobj.equals("")){
								CustomerOrderDetail olddetail=(CustomerOrderDetail) oldobj;
								ooo.accumulate("additionmoney",olddetail.getDetailmoney());
								ooo.accumulate("pkDetail",olddetail.getPkOrderDetail());
								ooo.accumulate("pkFairer",olddetail.getPkFairer());
								if((olddetail.getAwardsplit()!=null&&!olddetail.getAwardsplit().equals(""))){
									ooo.remove("awardsplit");
									ooo.accumulate("awardsplit",olddetail.getAwardsplit());
								}
												
								ooo.accumulate("fairername", olddetail.getFairername());
								
								map.remove(Long.parseLong(a.get("pkAddition").toString()));
							}
							
							list.add(ooo);
						}
						
					}
					
					//如果最新的附加项目中不包含原先维护的，把原先维护的查出来显示
					Iterator<?> iter=map.keySet().iterator();
					while(iter.hasNext()){
						JSONObject ooo=new JSONObject();
						Long key=(Long) iter.next();
						CustomerOrderDetail leftdetail=(CustomerOrderDetail) map.get(key);
						ooo.accumulate("pkAddition", leftdetail.getPkDetail());
						ooo.accumulate("additionname", leftdetail.getDetailname());
                        
						ArrayList<ShopFaireraward>  awards=(ArrayList<ShopFaireraward>) shopfairerawardService.queryvalidate(pkShop,  leftdetail.getPkDetail().toString(), "ADDITION");
						if(awards.size()>0){
						ooo.accumulate("awardsplit",JSONArray.fromObject(awards).toString());	
						}
						
							ooo.accumulate("additionmoney",leftdetail.getDetailmoney());
							ooo.accumulate("pkDetail",leftdetail.getPkOrderDetail());
							ooo.accumulate("pkFairer",leftdetail.getPkFairer());
							
							if((leftdetail.getAwardsplit()!=null&&!leftdetail.getAwardsplit().equals(""))){
								ooo.remove("awardsplit");
								ooo.accumulate("awardsplit",leftdetail.getAwardsplit());
							}
						
							ooo.accumulate("fairername", leftdetail.getFairername());
							
							list.add(ooo);
					}
					
					ob.accumulate("addition", JSONArray.fromObject(list).toString());
					
					returnobj.add(ob);
				}

				
				outputstr(JSONArray.fromObject(returnobj).toString(), "查询成功", true,totalorders.size()>0?totalorders.size():orders.size());
				
			}else{
				
				outputstr("", "暂无未支付订单", false,null);
			}

		   } catch (Exception e) {
			// TODO: handle exception
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
				List<CustomerOrderDetail> details=customerorderdetailService.QueryByPkOrder(pkOrder,Detailtype.ADDITION[0]);
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
			// TODO: handle exception
			dealexception(e);
			 outputexceptionstr();
		}
		output(response, pojo);
		
	}
	/**
	 * 理发师维护附加项目
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("faireraddition")
	public void faireraddition(HttpServletRequest request,HttpServletResponse response,Model model){
		
		try{
		JSONObject obj=RequestUtil.getPostString(request);
		String pkOrder=obj.getString("pkOrder");
		//String ordermoney=obj.getString("ordermoney");
		String addition=obj.get("addition")==null?"":obj.getString("addition");		
		CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
		if(order!=null){
		String paystatus=order.getPaystatus();
		if(paystatus!=null&&paystatus.equals("002")){
			  outputstr("", "订单已经结算,不允许维护附加项目", false,null);	
		}else{
		 Double sum=Double.parseDouble("0");
		 sum=sum+(order.getServicemoney()==null?Double.parseDouble("0"):order.getServicemoney());
		//判断是否存在附加项目
		if(!addition.equals("")){
			JSONArray array=JSONArray.fromObject(addition);
			Iterator<?> iter=array.iterator();
			ArrayList<CustomerOrderDetail> addlist=new ArrayList<CustomerOrderDetail> ();
			ArrayList<CustomerOrderDetail>  updatelist=new ArrayList<CustomerOrderDetail> ();
			while(iter.hasNext()){
				JSONObject jsonobj=(JSONObject) iter.next();
				String pkAddition=jsonobj.getString("pkAddition");
				String addtionname=jsonobj.getString("additionname");
				String additionmoney=(jsonobj.get("additionmoney")==null||jsonobj.get("additionmoney").equals(""))?"0":jsonobj.getString("additionmoney");
				String pkDetail=jsonobj.get("pkDetail")==null?"":jsonobj.getString("pkDetail");
				sum=sum+Double.parseDouble(additionmoney);
				if(pkDetail.equals("")){
					if(!additionmoney.equals("0")){
					CustomerOrderDetail addtiondetail=new CustomerOrderDetail();
					addtiondetail.setDetailtype("ADDITION");
					addtiondetail.setDetailmoney(Double.parseDouble(additionmoney));
					addtiondetail.setPkDetail(Long.parseLong(pkAddition));
					addtiondetail.setDetailname(addtionname);
					addtiondetail.setPkOrder(Long.parseLong(pkOrder));
					addtiondetail.setPkFairer(order.getPkFairer());
					addtiondetail.setFairername(order.getFairername());
					addlist.add(addtiondetail);
					}
				}else{
					CustomerOrderDetail detail=customerorderdetailService.getDetailById(Long.parseLong(pkDetail));
					detail.setDetailmoney(Double.parseDouble(additionmoney));
					detail.setPkFairer(order.getPkFairer());
					detail.setFairername(order.getFairername());
					updatelist.add(detail);
				}					
			}
			//批量更新订单明细
			if(addlist.size()>0){
				customerorderdetailService.savelist(addlist);
			}
			if(updatelist.size()>0){
				customerorderdetailService.updatelist(updatelist);
			}
			
		   }
		order.setOrdermoney(sum);
		customerorderService.updateOrder(order);
	    outputstr("", "维护附加项目费用成功", true,null);
		}
		}else{
	    outputstr("", "订单不存在", false,null);	
		}

		
    
	   }catch (Exception e) {
		// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
	    }
	    output(response, pojo);
	 }
	
	/**
	 * 订单结算,订单结算的时候需要将提成人的提成金额回写订单和提成人的信息中,还要更新套餐的使用记录
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("settle")
	public void settle(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			String operatorid=obj.getString("pkUser");
			String ordermoney=obj.getString("ordermoney");
			String paymode=(String)obj.get("paymode");
			String discount=(String)obj.get("discount");
			String addition=obj.get("addition")==null?"":obj.getString("addition");	
			String productlist=(String)obj.get("productlist");	
			
			CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));
			if(order==null){
			 outputstr("", "订单不存在", false, null);
			}else{
			CustomerCombo combo=customercomboService.getComboById(order.getPkCustomerCombo());
			if(combo==null){
			outputstr("", "套餐信息不存在", false, null);	
			}else{
				if((order.getIscombo()==null||order.getIscombo().equals("N"))&&combo.getIscomplete()!=null&&combo.getIscomplete().equals("Y")){
				 outputstr("", "套餐已经使用完毕", false, null);		
				}else{
				Long pkShopCombo=order.getPkShopCombo();
				if(pkShopCombo==null||pkShopCombo.equals("")){
				  outputstr("", "订单的套餐信息不完整", false, null);			
				}else{
					if(order.getOrderstatus().equals(OrderStatus.SERVICEING[0])){
						boolean iscontinue=true;
						if(order.getIscombo()==null||order.getIscombo().equals("")||order.getIscombo().equalsIgnoreCase("N")){
							Double servicemoney=order.getServicemoney()==null?Double.parseDouble("0"):order.getServicemoney()
									-(discount==null||discount.equals("")?Double.parseDouble("0"):Double.parseDouble(discount));//服务金额
							if(servicemoney<Double.parseDouble("0")){
								iscontinue=false;
								outputstr("", "优惠金额不能大于服务金额", false, null);			
							}
						}
						if(iscontinue){
					   //判断是否存在附加项目
					   if(!addition.equals("")){
						JSONArray array=JSONArray.fromObject(addition);
						Iterator<?> iter=array.iterator();
						ArrayList<CustomerOrderDetail> addlist=new ArrayList<CustomerOrderDetail> ();//新增
						ArrayList<CustomerOrderDetail>  updatelist=new ArrayList<CustomerOrderDetail> ();//更新
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
						
						//批量操作订单明细
						basicservice.batchoperate(addlist, updatelist);
					
					}			
						
					   //批量事务处理,防止异常时数据无法恢复
					   ArrayList<Object> batchaddlist=new ArrayList<Object>();			   
					   ArrayList<Object> batchupdatelist=new ArrayList<Object>();
					   
					   //产品用量
                       if(productlist!=null&&!productlist.equals("")){
                    	JSONArray array=JSONArray.fromObject(productlist);
   						Iterator<?> iter=array.iterator();
   						while(iter.hasNext()){
   							JSONObject jsonobj=(JSONObject) iter.next();
							String pkProduct=jsonobj.getString("pkProduct");
							String productname=jsonobj.getString("productname");
							String productnum=jsonobj.getString("productnum");
							String pkOrderDetail=jsonobj.get("pkOrderDetail")==null?"":jsonobj.getString("pkOrderDetail");							
							//新增产品信息
							if(pkOrderDetail.equals("")){
								CustomerOrderDetail productdetail=new CustomerOrderDetail();
								productdetail.setDetailtype(Detailtype.PRODUCT[0]);
								productdetail.setPkOrder(Long.parseLong(pkOrder));
								productdetail.setPkDetail(Long.parseLong(pkProduct));
								productdetail.setDetailname(productname);
								productdetail.setDetailcount(Double.parseDouble(productnum));
								productdetail.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								productdetail.setDr((short)0);
								batchaddlist.add(productdetail);
							}else{
							CustomerOrderDetail productdetail=customerorderdetailService.getDetailById(Long.parseLong(pkOrderDetail));
							productdetail.setPkDetail(Long.parseLong(pkProduct));
							productdetail.setDetailname(productname);
							productdetail.setDetailcount(Double.parseDouble(productnum));
							productdetail.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							productdetail.setDr((short)0);
							batchupdatelist.add(productdetail);
							}
   						  }
                       }
					   
					   //查询店铺套餐信息
					   ShopCombo shopcombo=shopcomboService.getComboById(order.getPkShopCombo());		
						HashMap<String,FairerInfo>  map=new HashMap<String,FairerInfo> ();//理发师信息更新
						//假如不是套餐订单更新服务次数和服务金额
						if(order.getIscombo()==null||order.getIscombo().equals("")||order.getIscombo().equalsIgnoreCase("N")){
							Double servicemoney=order.getServicemoney()==null?Double.parseDouble("0"):order.getServicemoney()
									-(discount==null||discount.equals("")?Double.parseDouble("0"):Double.parseDouble(discount));//服务金额
							Double sum=Double.parseDouble("0");//订单的提成金额
							Double addtionsum=Double.parseDouble("0");//附加项目的提成金额
							Double faireraward=Double.parseDouble("0");//理发师的服务提成金额金额		
							String fwFairer=order.getPkFairer().toString();
							
							//理发师增加服务次数和服务金额
							FairerInfo fairerinfo=fairerinfoService.getFairerById(Long.parseLong(fwFairer));
							
							fairerinfo.setServicenum((fairerinfo.getServicenum()==null?0:fairerinfo.getServicenum())+1);
							fairerinfo.setServicemoney((fairerinfo.getServicemoney()==null?Double.parseDouble("0"):fairerinfo.getServicemoney())
									+servicemoney);
							
						    map.put(fwFairer, fairerinfo);
							
								//服务费提成计算
								String commisionpeople=order.getCommissionpeople();//提成人分摊
								if(commisionpeople!=null&&!commisionpeople.equals("")){
									JSONArray arr=JSONArray.fromObject(commisionpeople);
									Iterator<?> iter=arr.iterator();
									while (iter.hasNext()) {
										JSONObject object = (JSONObject) iter.next();
										String pkFairer=object.getString("pkFairer");//理发师主键
										String fisrate=object.getString("israte");//提成方式
										String ffairermoney=object.getString("awardmoney");//提成比例或者固定金额
										Double getmoney=Double.parseDouble("0");
										//如果不按照比例按照固定金额提成
										if(fisrate.equals("1")){
											getmoney=Double.parseDouble(ffairermoney);
											faireraward+=getmoney;
		
										}else if(fisrate.equals("2")){
											//按照比例提成
											getmoney=servicemoney*
											Double.parseDouble(ffairermoney);
											faireraward+=getmoney;
			
										}
										
									
										//理发师收入明细
										FairerCredit credit=new FairerCredit();
										credit.setCredittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
										credit.setCreditmoney(getmoney);
										credit.setCreditnote("普通服务费："+servicemoney+"元,收益："+getmoney+"元");
										credit.setPkFairer(Long.parseLong(pkFairer));
										credit.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
										credit.setDr((short)0);
										credit.setPkOrder(order.getPkOrder());
										credit.setCreatetype("SERVICE");
										
										batchaddlist.add(credit);
										
										//更新理发师的服务金额和服务次数
										FairerInfo fairer=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
		//								fairer.setServicenum((fairer.getServicenum()==null?0:fairer.getServicenum())+1);
		//								fairer.setServicemoney((fairer.getServicemoney()==null?Double.parseDouble("0"):fairer.getServicemoney())
		//										+servicemoney);
										fairer.setAccountmoney((fairer.getAccountmoney()==null?Double.parseDouble("0"):fairer.getAccountmoney())
												+getmoney);
										
										if(map.containsKey(pkFairer)){
										 FairerInfo old=map.get(pkFairer);	
										 old.setAccountmoney((old.getAccountmoney()==null?Double.parseDouble("0"):old.getAccountmoney())
													+getmoney);
										 map.remove(pkFairer);
										 map.put(pkFairer, old);
										}else{
									     map.put(pkFairer, fairer);
										}
										
									}							
								}
								
							sum+=faireraward;
							
							//附加项目提成计算
							ArrayList<CustomerOrderDetail> details=(ArrayList<CustomerOrderDetail>) customerorderdetailService.QueryByPkOrder(pkOrder,Detailtype.ADDITION[0]);		
							for(int j=0;j<details.size();j++){					
							//附加项目的提成
							String split=details.get(j).getAwardsplit();
							if(split!=null&&!split.equals("")){
								JSONArray arr=JSONArray.fromObject(split);
								Iterator<?> iter=arr.iterator();
								while (iter.hasNext()) {
									JSONObject object = (JSONObject) iter.next();
									String pkFairer=object.getString("pkFairer");//理发师主键
									String fisrate=object.getString("israte");//提成方式
									String ffairermoney=object.getString("awardmoney");//提成比例或者固定金额
									Double getmoney=Double.parseDouble("0");
									//如果不按照比例按照固定金额提成
									if(fisrate.equals("1")){
										getmoney=Double.parseDouble(ffairermoney);
										addtionsum+=getmoney;
									}else if(fisrate.equals("2")){
										//按照比例提成
										getmoney=(details.get(j).getDetailmoney()==null?Double.parseDouble("0"):details.get(j).getDetailmoney())*
										Double.parseDouble(ffairermoney);
										addtionsum+=getmoney;
									}
			
									//理发师收入明细
									FairerCredit credit=new FairerCredit();
									credit.setCredittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									credit.setCreditmoney(getmoney);
									credit.setCreditnote("套餐："+shopcombo.getComboname()+",附加项目："+details.get(j).getDetailname()+",收益："+getmoney+"元");
									credit.setPkFairer(Long.parseLong(pkFairer));
									credit.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									credit.setDr((short)0);
									credit.setCreatetype("ADDITION");
									credit.setPkOrder(order.getPkOrder());
		
									
									batchaddlist.add(credit);
									
									//更新理发师的账户金额
									FairerInfo fairer=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
									fairer.setAccountmoney((fairer.getAccountmoney()==null?Double.parseDouble("0"):fairer.getAccountmoney())
											+getmoney);
									
									if(map.containsKey(pkFairer)){
									 FairerInfo old=map.get(pkFairer);	
									 old.setAccountmoney((old.getAccountmoney()==null?Double.parseDouble("0"):old.getAccountmoney())
												+getmoney);
									 map.remove(pkFairer);
									 map.put(pkFairer, old);
									}else{
								     map.put(pkFairer, fairer);
									 }					
								  }
							   }					
							}
							
							//提成总金额更新
							sum+=addtionsum;
												
						    //批量更新理发师账户信息
							ArrayList<FairerInfo> updatelist=new ArrayList<FairerInfo>();
							if(map.size()>0){					
								Iterator<String> iter= map.keySet().iterator();
								while(iter.hasNext()){
									String key=iter.next();
									FairerInfo info=map.get(key);
									updatelist.add(info);
									batchupdatelist.add(info);
								}
							}
							
							//更新订单支付状态,支付时间
							order.setPaystatus(PayStatus.PAYCOMPLETE[0]);
							order.setPaymode(paymode);
							order.setOrderstatus(OrderStatus.SERVICECOMPLETE[0]);
							order.setPaytime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							order.setOrdermoney(Double.parseDouble(ordermoney));//订单金额
							//order.setCombomoney(Double.parseDouble(ordermoney));//套餐金额
							order.setFairercomboservicemoney(faireraward);//普通服务的提成金额
							order.setFaireraddmoney(addtionsum);//订单附加项目的提成金额
							order.setFairermoney(sum);//订单的理发师金额是所有提成的合计，不区分附加项目是否是订单理发师本人
							order.setDiscount((discount==null||discount.equals(""))?null:Double.parseDouble(discount));
							//更新订单主表
							batchupdatelist.add(order);
							
						}else{										
							//套餐提成
							String commissionpeople=order.getCommissionpeople();
							//Double summoney=order.getOrdermoney();//订单总金额
							Double summoney=Double.parseDouble(ordermoney);//订单总金额
							Double sumsplit=Double.parseDouble("0");//总提成金额
							Double addtionsum=Double.parseDouble("0");//附加项目的提成金额
							if(commissionpeople!=null&&!commissionpeople.equals("")){
								JSONArray arr=JSONArray.fromObject(commissionpeople);
								Iterator<?> iter=arr.iterator();
								while(iter.hasNext()){
									JSONObject oneobj=(JSONObject) iter.next();
									String pkFairer=oneobj.getString("pkFairer");//理发师主键
									String fisrate=oneobj.getString("israte");//提成方式
									String ffairermoney=oneobj.getString("awardmoney");//提成比例或者固定金额
									
									Double getmoney=Double.parseDouble("0");
									//如果不按照比例按照固定金额提成
									if(fisrate.equals("1")){
										getmoney=Double.parseDouble(ffairermoney);
										sumsplit+=getmoney;
									}else if(fisrate.equals("2")){
										//按照比例提成
										getmoney=summoney*Double.parseDouble(ffairermoney);
										sumsplit+=getmoney;
									}
									
			
										//理发师收入明细
										FairerCredit credit=new FairerCredit();
										credit.setCredittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
										credit.setCreditmoney(getmoney);
										credit.setCreditnote("成功售出"+shopcombo.getComboname()+"套餐,收益"+getmoney+"元");
										credit.setPkFairer(Long.parseLong(pkFairer));
										credit.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
										credit.setDr((short)0);
										credit.setCreatetype("COMBO");
										credit.setPkOrder(order.getPkOrder());
										
										batchaddlist.add(credit);
										
										//更新理发师的账户金额
										FairerInfo fairer=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
										fairer.setAccountmoney((fairer.getAccountmoney()==null?Double.parseDouble("0"):fairer.getAccountmoney())
												+getmoney);
										
										if(map.containsKey(pkFairer)){
										 FairerInfo old=map.get(pkFairer);	
										 old.setAccountmoney((old.getAccountmoney()==null?Double.parseDouble("0"):old.getAccountmoney())
													+getmoney);
										 map.remove(pkFairer);
										 map.put(pkFairer, old);
										}else{
									     map.put(pkFairer, fairer);
										 }	
								}
							}
							
							//附加项目提成计算
							ArrayList<CustomerOrderDetail> details=(ArrayList<CustomerOrderDetail>) customerorderdetailService.QueryByPkOrder(pkOrder,Detailtype.ADDITION[0]);		
							for(int j=0;j<details.size();j++){					
							//附加项目的提成
							String split=details.get(j).getAwardsplit();
							if(split!=null&&!split.equals("")){
								JSONArray splitarr=JSONArray.fromObject(split);
								Iterator<?> splititer=splitarr.iterator();
								while (splititer.hasNext()) {
									JSONObject object = (JSONObject) splititer.next();
									String pkFairer=object.getString("pkFairer");//理发师主键
									String fisrate=object.getString("israte");//提成方式
									String ffairermoney=object.getString("awardmoney");//提成比例或者固定金额
									Double getmoney=Double.parseDouble("0");
									//如果不按照比例按照固定金额提成
									if(fisrate.equals("1")){
										getmoney=Double.parseDouble(ffairermoney);
										addtionsum+=getmoney;
									}else if(fisrate.equals("2")){
										//按照比例提成
										getmoney=(details.get(j).getDetailmoney()==null?Double.parseDouble("0"):details.get(j).getDetailmoney())*
										Double.parseDouble(ffairermoney);
										addtionsum+=getmoney;
									}
			
									//理发师收入明细
									FairerCredit credit=new FairerCredit();
									credit.setCredittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									credit.setCreditmoney(getmoney);
									credit.setCreditnote("套餐："+shopcombo.getComboname()+",附加项目："+details.get(j).getDetailname()+",收益："+getmoney+"元");
									credit.setPkFairer(Long.parseLong(pkFairer));
									credit.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
									credit.setDr((short)0);
									credit.setCreatetype("ADDITION");
									credit.setPkOrder(order.getPkOrder());
									
									batchaddlist.add(credit);
									
									//更新理发师的账户金额
									FairerInfo fairer=fairerinfoService.getFairerById(Long.parseLong(pkFairer));
									fairer.setAccountmoney((fairer.getAccountmoney()==null?Double.parseDouble("0"):fairer.getAccountmoney())
											+getmoney);
									
									if(map.containsKey(pkFairer)){
									 FairerInfo old=map.get(pkFairer);	
									 old.setAccountmoney((old.getAccountmoney()==null?Double.parseDouble("0"):old.getAccountmoney())
												+getmoney);
									 map.remove(pkFairer);
									 map.put(pkFairer, old);
									}else{
								     map.put(pkFairer, fairer);
									 }					
								  }
							   }					
							}
							
							  //批量更新理发师账户信息
							if(map.size()>0){					
								Iterator<String> splititer= map.keySet().iterator();
								while(splititer.hasNext()){
									String key=splititer.next();
									FairerInfo info=map.get(key);
									
									batchupdatelist.add(info);							
								}
													
							}
							
							
							//更新订单支付状态,支付时间
							order.setPaystatus(PayStatus.PAYCOMPLETE[0]);
							order.setPaymode(paymode);//支付方式
							order.setOrderstatus(OrderStatus.SERVICECOMPLETE[0]);
							order.setOrdermoney(Double.parseDouble(ordermoney));//订单金额
							order.setCombomoney(Double.parseDouble(ordermoney));//套餐金额
							order.setPaytime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							order.setFairermoney(sumsplit+addtionsum);//订单的理发师金额是所有提成的合计，不区分附加项目是否是订单理发师本人
							order.setFaireraddmoney(addtionsum);//附加项目金额
							order.setDiscount((discount==null||discount.equals(""))?null:Double.parseDouble(discount));//折扣
							
							//更新订单主表
							batchupdatelist.add(order);
							
						    //更新套餐金额
					        combo.setCombomoney(Double.parseDouble(ordermoney));//套餐金额
					        combo.setDiscount((discount==null||discount.equals(""))?null:Double.parseDouble(discount));//折扣
					        batchupdatelist.add(combo);
							
						}
		
						
					//更新用户历史消费金额
					CustomerInfo customer=customerinfoService.getCustomerById(order.getPkCustomer());
					if(customer!=null){
						customer.setAccountmoney((customer.getAccountmoney()==null?Double.parseDouble("0"):customer.getAccountmoney())
								+(order.getOrdermoney()==null?Double.parseDouble("0"):order.getOrdermoney()));
					
						//清空用户扫描下单标志
						if(customer.getCustomerstatus()!=null&&customer.getCustomerstatus().equals(CustomerStatus.SCANORDER[0])){
							customer.setCustomerstatus(null);
						}
						
						batchupdatelist.add(customer);
					}
		
					String iscombo=order.getIscombo();
					
					if(iscombo!=null&&iscombo.equals("Y")){					
						 //发放抵用券								
						String pkCombo=order.getPkCombo().toString();
						ArrayList<ComboAward> list=(ArrayList<ComboAward>) comboawardService.QueryBycombo(pkCombo);
						if(list.size()>0){
							for(int i=0;i<list.size();i++){
								ComboAward award=list.get(i);
								String pkCustomeraward=award.getPkCustomeraward().toString();
								BdCustomerAward getaward=customerawardService.getAwardById(award.getPkCustomeraward());
								
								String status=getaward.getAwardstatus();//抵用券状态
								
								if(status.equals("001")){
							    CustomerOwnaward newaward=new CustomerOwnaward();
							    newaward.setPkCustomer(order.getPkCustomer());
							    newaward.setPkCustomeraward(award.getPkCustomeraward());
							    newaward.setPkFrom(getaward.getPkFrom());
							    newaward.setAwardfrom(getaward.getAwardfrom());
							    newaward.setAwardname(getaward.getAwardname());
							    newaward.setAwardstatus("001");
							    newaward.setAwardshorturl(getaward.getAwardshorturl());
							    newaward.setOwntime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							    newaward.setAwardurl(getaward.getAwardurl());
							    newaward.setPkOrder(order.getPkOrder());
							    newaward.setAwardvalue(getaward.getAwardvalue());
							    newaward.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							    newaward.setDr((short)0);
							    newaward.setIsshare(getaward.getIsshare());
							    newaward.setAwardstatus("001");
							    newaward.setBegintime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));//开始日期是今天
							    newaward.setEndtime(DateUtil.DateToString(DateUtil.nextDay(new Date(), 6), DateUtil.FORMAT_ONE));//结束时间默认是7天内
							    
								ArrayList<BdCustomerAwardLimit> limits=(ArrayList<BdCustomerAwardLimit>) customerawardlimitService.QueryByPkParent(pkCustomeraward);
							    ArrayList<Object> lists=new ArrayList<Object>();
							    if(limits.size()>0){
							    	for(BdCustomerAwardLimit limit:limits){
							    		JSONObject getobj=new JSONObject();
							    		getobj.accumulate("pkCombo", limit.getPkCombo().toString());
							    		getobj.accumulate("comboname", limit.getComboname());
							    		lists.add(getobj);
							    	}
								newaward.setLimits(JSONArray.fromObject(lists).toString());
							    }
							    
							    
								batchaddlist.add(newaward);
								
								}
							}
						}
																						
					}
					
					//保存订单轨迹					
					CustomerOrderTrace trace=new CustomerOrderTrace();
					trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					trace.setDr((short)0);
					trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					trace.setOperator(operatorid);
					trace.setPkOrder(order.getPkOrder());
					trace.setOperatecontent("订单结算");
					batchaddlist.add(trace);
						
		
					if(iscombo==null||iscombo.equals("")||iscombo.equals("N")){				
						//更新套餐使用情况,如果套餐总次数和使用次数已经相等,则将套餐置为失效和已完成
						combo.setLastusetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						if(!combo.getCombotype().equals("1")){
						combo.setLeftcount((combo.getLeftcount()==null?0:combo.getLeftcount())-1);//剩余次数
				        //剩余次数小于0的话,将套餐的已完成置为Y,是否有效置为N
						if(combo.getLeftcount()<=0){
							combo.setIscomplete("Y");
							combo.setIsvalid("N");
						  }else{
							combo.setIsvalid("Y");
							combo.setIscomplete("N");
						  }
						}
				        combo.setLastmoney(order.getOrdermoney());//上次订单金额
				        combo.setLastfairer(order.getPkFairer());//上次理发师主键
				        combo.setLastfairername(order.getFairername());//上次理发师姓名
				        
						//判断套餐是不是按照第一次使用时间开始计算有效期
						if((combo.getCombobegintime()==null||combo.getCombobegintime().equals(""))&&(combo.getComboendtime()==null||combo.getComboendtime().equals(""))){
						  if(combo.getComputetimetype()!=null&&combo.getComputetimetype().equalsIgnoreCase(Computetimetype.FIRSTUSETIME[0])){
							  combo.setCombobegintime(DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT));
							  combo.setComboendtime(DateUtil.DateToString(DateUtil.getDateByday(365),DateUtil.LONG_DATE_FORMAT));
						   }
						}
						
						batchupdatelist.add(combo);
				        
						//插入套餐使用记录
						CustomerComboUserecord record=new CustomerComboUserecord();
						record.setPkCustomer(order.getPkCustomer());
						record.setCumboname(order.getComboname());
						record.setPkOrder(order.getPkOrder());
						record.setPkShopCumbo(order.getPkShopCombo());
						record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						record.setDr((short)0);
						record.setPkCustomerCombo(order.getPkCustomerCombo());
						record.setUseman(order.getCustomername());
						record.setUsermanphone(order.getCellphone());
						record.setUsetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
						record.setUsedetail(order.getOrdercontent());
		
						batchaddlist.add(record);
						
						//更新套餐服务次数
						shopcombo.setServicecount((shopcombo.getServicecount()==null?0:shopcombo.getServicecount())+1);
						
				}else{
					//更新套餐购买次数
				     shopcombo.setBuycount((shopcombo.getBuycount()==null?0:shopcombo.getBuycount())+1); 
				}
		
				    //更新套餐
				    batchupdatelist.add(shopcombo);
			        
					 //批量事务处理
					basicservice.batchoperate(batchaddlist, batchupdatelist);
						
					//推送消息
					CustomerInfo cusinfo=customerinfoService.getCustomerById(order.getPkCustomer());
					String pkUser=cusinfo.getPkUser()==null?"":cusinfo.getPkUser().toString();
					if(!pkUser.equals("")){
				    //保存消息列表
		//	        MessageList  message=new MessageList();
		//	        message.setSender("ROCKSTAR");
		//	        message.setMessagetype(MessageType.TYPE_PLAT);
		//	        message.setReceiver(pkUser);
		//	        if(order.getIscombo()!=null&&order.getIscombo().equals("Y")){
		//	        message.setLastmessage("您的【"+order.getComboname()+"】套餐订单已经结算完成了，一共消费了"+order.getOrdermoney()+"元，快去晒晒你的新发型吧。【"+order.getShopname()+"】");
		//	        }else{
		//	        message.setLastmessage("您的【"+order.getComboname()+"】服务订单已经结算完成了，一共消费了"+order.getOrdermoney()+"元，快去晒晒你的新发型吧。【"+order.getShopname()+"】");
		//	        }
		//	        message.setSendtype("AUTO");
		//	        message.setPkShop(order.getPkShop());
		//	        message.setMessagegroup("001");
		//	        message.setMessagestatus("001");
		//	        message.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		//	        
		//	        message=messagelistService.save(message);
		//	        //保存消息明细
		//	        MessageRecord record=new MessageRecord();
		//	        record.setSender("ROCKSTAR");
		//	        record.setSendtime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		//	        record.setSendresult("Y");
		//	        if(order.getIscombo()!=null&&order.getIscombo().equals("Y")){
		//	        	record.setContent("您的【"+order.getComboname()+"】套餐订单已经结算完成了，一共消费了"+order.getOrdermoney()+"元，快去晒晒你的新发型吧。【"+order.getShopname()+"】");
		//		        }else{
		//		       record.setContent("您的【"+order.getComboname()+"】服务订单已经结算完成了，一共消费了"+order.getOrdermoney()+"元，快去晒晒你的新发型吧。【"+order.getShopname()+"】");
		//		        }
		//	        record.setReceiver(pkUser);
		//	        record.setMessagestatus("001");   
		//	        record.setMessagegroup("001");
		//	        record.setMessagetype(MessageType.TYPE_PLAT);
		//	        record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		//	        record.setDr((short)0);
		//		    record.setPkMessagelist(message.getPkMessagelist());
		//		    messageservice.save(record);
				    
				    
				    //推送微信通知
		//		    ArrayList<Object> mode=(ArrayList<Object>) basicservice.query(UserVerificationmode.class, " pkUser = ?  and verificationType = ? ", Long.parseLong(pkUser),VerificationMode.MODE_TWO[0]);	    
		//		    if(mode.size()>0){
		//		    	String openid=((UserVerificationmode)mode.get(0)).getOpenid();
		//		    	JSONObject wxobj=new JSONObject();
		//		    	wxobj.accumulate("touser", openid);
		//		    	wxobj.accumulate("msgtype", "msgtype");
		//		    	JSONObject textobj=new JSONObject();
		//		    	textobj.accumulate("content", "您的【"+order.getComboname()+"】订单已经结算完成,消费金额："+order.getOrdermoney()+"订单编号："+order.getOrderno()+",欢迎下次光临.【"+order.getShopname()+"】");
		//		    	wxobj.accumulate("text", textobj.toString());
		//				 //读取配置文件参数
		//				String url = WxserviceUtil.GetUrl("sendmsg");
		//				 //获取返回值
		//			    String  jsonResult = this.httpservice.sendPostRequset(url, wxobj.toString());
		//			    System.out.println(jsonResult);
		//		       }
		//		    
					}
					
		             outputstr("", "结算成功", true,null);
					}
						  }else{
					   outputstr("", "当前订单状态不允许结算", false,null);  
				    }
				  }
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
			
			ArrayList<CustomerOrder> orders=(ArrayList<CustomerOrder>) customerorderService.FindByPkUser(pkUser, usergroup);
			
			if(orders.size()>0){
				String data=JSONArray.fromObject(orders).toString();
				
                outputstr(data, "查询订单成功", true,null);
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
			List<CustomerOrderDetail> details=customerorderdetailService.QueryByPkOrder(pkOrder,Detailtype.ADDITION[0]);
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
			JSONArray arrurls=JSONArray.fromObject(urls);
			Iterator<?> iter=arrurls.iterator();
			ArrayList<CustomerOrderBeautyrecord> recordlist=new ArrayList<CustomerOrderBeautyrecord>();
			
			//记录所有上传到FASTDFS的文件
			ArrayList<UserFile> filelist=new ArrayList<UserFile>();
			
			
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
				recordlist.add(record);
				
				//保存魅力纪录
				UserFile file=new UserFile();
				file.setBemodel("APP上传美丽记录");
				file.setFilepath(url);
				file.setThumbnail(shorturl);
				//file.setFilesize(FileManegeUtil.getsize("", url).getString("size"));
				file.setPkUser(Long.parseLong(pkUser));
				file.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
				file.setDr((short)0);
				filelist.add(file);
				
			}
			
			if(recordlist.size()>0){
				orderbeautyrecordService.savelist(recordlist);	
				if(order.getIsbeauty()==null||order.getIsbeauty().equals("")||order.getIsbeauty().equals("N")){
					//更新訂單是否生成美麗記錄的標誌
					order.setIsbeauty("Y");
					customerorderService.updateOrder(order);
				}
			   }

			if(filelist.size()>0){
				userfileService.batchsave(filelist);
			}
			
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
			String star=obj.getString("evaluategoal");	
			String evaluatenote=obj.get("evaluatenote")==null?null:obj.getString("evaluatenote");	
			String evaluatedetail=obj.get("evaluatedetail")==null?null:obj.getString("evaluatedetail");
			
			boolean iscontinue=true;
			
               if(evaluatedetail!=null&&!evaluatedetail.equals("")){
            		Validator validator=new Validator(Validator.class.getResourceAsStream("evaluatedetail"));
        	    	int i=validator.validate(obj.toString());		    
        	          	    	
        	    	if(i>0){
        	    		outputstr("", "评价得分不符合规范哦", false, null);
        	    		iscontinue=false;
        	    	}
                 }
               
               if(iscontinue){
            	   
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
               order.setEvaluatenote(evaluatenote);
               order.setEvaluatedetail(evaluatedetail);
               order.setEvaluatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
               int result=customerorderService.updateOrder(order);
               if(result==0){
            	   try {
            		   //更新套餐的评论次数
            		   Integer combocount=Integer.parseInt(RedisUtils.getObject("comment:"+"combo:"+order.getPkCombo().toString())==null?"0":RedisUtils.getObject("comment:"+"combo:"+order.getPkCombo().toString()).toString());
                	   RedisUtils.setKey("comment:"+"combo:"+order.getPkCombo().toString(), combocount+1);
                	   
            		   //更新店铺的评论次数
            		   Integer shopcount=Integer.parseInt(RedisUtils.getObject("comment:"+"shop:"+order.getPkShop().toString())==null?"0":RedisUtils.getObject("comment:"+"shop:"+order.getPkShop().toString()).toString());
                	   RedisUtils.setKey("comment:"+"shop:"+order.getPkShop().toString(), shopcount+1);
                	   
            		   //更新理发师的评论次数
            		   Integer fairercount=Integer.parseInt(RedisUtils.getObject("comment:"+"fairer:"+order.getPkFairer().toString())==null?"0":RedisUtils.getObject("comment:"+"fairer:"+order.getPkFairer().toString()).toString());
                	   RedisUtils.setKey("comment:"+"combo:"+order.getPkCombo().toString(), fairercount+1);
                	   
                	   
				   } catch (Exception e) {
				 	// TODO: handle exception
					   System.out.println("redis出错");
				   }
            	   
            	   //mongodb存储订单信息，供查询评论信息
            	   CommentList comment=new CommentList();
            	   comment.setCustomername(order.getCustomername());
            	   comment.setEvaluatedetail(order.getEvaluatedetail());
            	   comment.setEvaluategoal(order.getEvaluategoal());
            	   comment.setEvaluatenote(order.getEvaluatenote());
            	   comment.setEvaluatetime(order.getEvaluatetime());
            	   comment.setPkCombo(order.getPkCombo());
            	   comment.setPkFairer(order.getPkFairer());
            	   comment.setPkShop(order.getPkShop());
            	   
            	   mongoservice.save(comment);
            	   
            	   outputstr("", "评价成功", true,null);
                }else{
            	   outputstr("", "评价失败", false,null); 
                 }  	 
                 }
                 }
                 }
              }else{    
            	   outputstr("", "订单不存在", false,null);
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
			// TODO: handle exception
			 dealexception(e);
			 outputexceptionstr();
		}
		 output(response, pojo);
	 }
	 
	 /**
	  * 根据订单状态查询订单{001","服务中"},{"002","服务完成"},{"003","预约单"},{"004","订单取消"}
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("queryappointment")
	 public void queryappointment(HttpServletRequest request,HttpServletResponse response,Model model){	
		 try {
			 
			JSONObject receiveobj=RequestUtil.getPostString(request);            
			String pkShop=receiveobj.getString("pkShop");//店铺主键			
			String curpage=(String)receiveobj.get("curpage");			
			String pagesize=(String)receiveobj.get("pagesize");			
			String begintime=(String)receiveobj.get("begintime");			
			String endtime=(String)receiveobj.get("endtime");
			
			ArrayList<CustomerOrder>  orderlist=new ArrayList<CustomerOrder>();
			ArrayList<CustomerOrder> totalorderlist=new ArrayList<CustomerOrder>();
			if(curpage!=null&&!curpage.equals("")){
		    orderlist =(ArrayList<CustomerOrder>) customerorderService.querybycon(pkShop, Integer.parseInt(curpage), Integer.parseInt(pagesize), begintime, endtime, OrderStatus.APPOINTMENT[0]);
			totalorderlist=(ArrayList<CustomerOrder>) customerorderService.querybycon(pkShop,null,null, begintime, endtime, OrderStatus.APPOINTMENT[0]);
			}else{
			orderlist=(ArrayList<CustomerOrder>) customerorderService.querybycon(pkShop,null,null, begintime, endtime, OrderStatus.APPOINTMENT[0]);	
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
			 // TODO: handle exception
			  dealexception(e);
			  outputexceptionstr();
		   }
		  output(response, pojo);
	 }
	 
	 /**
	  * 订单转服务单
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("createorder")
	 public void createorder(HttpServletRequest request,HttpServletResponse response,Model model){	
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkOrder=obj.getString("pkOrder");
			String pkUser=obj.getString("pkUser");
			
			CustomerOrder order=customerorderService.getOrderById(Long.parseLong(pkOrder));

			if(order!=null){								
				String status=order.getOrderstatus();
				if(status.equals(OrderStatus.APPOINTMENT[0])){
					
					ArrayList<CustomerOrderTrace> addlist=new ArrayList<CustomerOrderTrace>();
					ArrayList<CustomerOrder> updatelist=new ArrayList<CustomerOrder>();
					
					order.setOrderstatus(OrderStatus.SERVICEING[0]);
					order.setOrdetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					updatelist.add(order);
					
					//保存订单轨迹					
					CustomerOrderTrace trace=new CustomerOrderTrace();
					trace.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					trace.setDr((short)0);
					trace.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
					trace.setOperator(pkUser.toString());
					trace.setPkOrder(Long.parseLong(pkOrder));
					trace.setOperatecontent("预约单转服务单");
					
					addlist.add(trace);
					
					basicservice.batchoperate(addlist, updatelist);
					outputstr("", "生成服务单成功", true, null);
					
				}else{
				 outputstr("", "当前订单状态无法生成订单", false, null);	
				}
			  }else{
				outputstr("", "订单不存在", false, null);
			 }
		} catch (Exception e) {
			// TODO: handle exception
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
				// TODO: handle exception
				  dealexception(e);
				  outputexceptionstr();
			}
			   output(response, pojo);
		 
	 }
	 
	 
	 /**
	  * 查询订单详情
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
				outputstr("", "订单不存在", false, null);
			}
			
		     } catch (Exception e) {
			// TODO: handle exception
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
		
	 
	 /**
	  * 根据订单查询使用产品信息
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("findproduct")
	 public void findproduct(HttpServletRequest request,HttpServletResponse response,Model model){	
            try {
				JSONObject obj=RequestUtil.getPostString(request);
				String pkOrder=obj.getString("pkOrder");
				
			    ArrayList<CustomerOrderDetail> details= (ArrayList<CustomerOrderDetail>) customerorderdetailService.QueryByPkOrder(pkOrder, Detailtype.PRODUCT[0]);
			    if(details.size()>0){
			    	String data=JSONArray.fromObject(details).toString();
			    	outputstr(data, "查询产品用量使用情况成功", true, null);
			    }else{
			    	outputstr("", "没有产品用量使用情况", true, null);
			    }
			    
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				dealexception(e);
			}
            
            output(response, pojo);
	 }
	 
	 /**
	  * 编辑产品用量
	  * @param request
	  * @param response
	  * @param model
	  */
	 @RequestMapping("editproduct")
	 public void editproduct(HttpServletRequest request,HttpServletResponse response,Model model){	
            try {        
            	   JSONObject obj=RequestUtil.getPostString(request);
    			   String productlist=(String)obj.get("productlist");	 
    			   String pkOrder=(String)obj.get("pkOrder");	
    			   ArrayList<Object>  batchaddlist=new ArrayList<Object>();
    			   ArrayList<Object>  batchupdatelist=new ArrayList<Object>();
            	  if(productlist!=null&&productlist.equals("")){
                  	JSONArray array=JSONArray.fromObject(productlist);
 						Iterator<?> iter=array.iterator();
 						while(iter.hasNext()){
 							JSONObject jsonobj=(JSONObject) iter.next();
							String pkProduct=jsonobj.getString("pkProduct");
							String productname=jsonobj.getString("productname");
							String productnum=jsonobj.getString("productnum");
							String pkOrderDetail=jsonobj.get("pkOrderDetail")==null?"":jsonobj.getString("pkOrderDetail");							
							//新增产品信息
							if(pkOrderDetail.equals("")){
								CustomerOrderDetail productdetail=new CustomerOrderDetail();
								productdetail.setDetailtype(Detailtype.PRODUCT[0]);
								productdetail.setPkOrder(Long.parseLong(pkOrder));
								productdetail.setPkDetail(Long.parseLong(pkProduct));
								productdetail.setDetailname(productname);
								productdetail.setDetailcount(Double.parseDouble(productnum));
								productdetail.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
								productdetail.setDr((short)0);
								batchaddlist.add(productdetail);
							}else{
							CustomerOrderDetail productdetail=customerorderdetailService.getDetailById(Long.parseLong(pkOrderDetail));
							productdetail.setPkDetail(Long.parseLong(pkProduct));
							productdetail.setDetailname(productname);
							productdetail.setDetailcount(Double.parseDouble(productnum));
							productdetail.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
							productdetail.setDr((short)0);
							batchupdatelist.add(productdetail);
							}
 						  }
                     }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				dealexception(e);
			}
            
            output(response, pojo);
	 }
	 
}
