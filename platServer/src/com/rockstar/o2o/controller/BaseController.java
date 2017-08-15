package com.rockstar.o2o.controller;

import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import com.rockstar.o2o.basicservice.BasicService;
import com.rockstar.o2o.http.IHttpClientService;
import com.rockstar.o2o.service.AchievementService;
import com.rockstar.o2o.service.BdCustomerAwardLimitService;
import com.rockstar.o2o.service.BdCustomerAwardService;
import com.rockstar.o2o.service.ClientInfoService;
import com.rockstar.o2o.service.ComboAwardService;
import com.rockstar.o2o.service.ContentHotsearchService;
import com.rockstar.o2o.service.ContentHottypeService;
import com.rockstar.o2o.service.ContentManageService;
import com.rockstar.o2o.service.ContentPhotoService;
import com.rockstar.o2o.service.ContentTagService;
import com.rockstar.o2o.service.CustomerComboService;
import com.rockstar.o2o.service.CustomerComboUserecordService;
import com.rockstar.o2o.service.CustomerInfoService;
import com.rockstar.o2o.service.CustomerOrderBeautyrecordService;
import com.rockstar.o2o.service.CustomerOrderDetailService;
import com.rockstar.o2o.service.CustomerOrderService;
import com.rockstar.o2o.service.CustomerOrderSpitslotService;
import com.rockstar.o2o.service.CustomerOwnawardService;
import com.rockstar.o2o.service.CustomerUseawardService;
import com.rockstar.o2o.service.FairerAttachmentService;
import com.rockstar.o2o.service.FairerCreditService;
import com.rockstar.o2o.service.FairerInfoService;
import com.rockstar.o2o.service.FairerSkillService;
import com.rockstar.o2o.service.FileService;
import com.rockstar.o2o.service.MessageListService;
import com.rockstar.o2o.service.MessageRecordService;
import com.rockstar.o2o.service.PlatProductTraceService;
import com.rockstar.o2o.service.PlatUserService;
import com.rockstar.o2o.service.PlatWxQrcodeScancountService;
import com.rockstar.o2o.service.PlatformAreaService;
import com.rockstar.o2o.service.PlatformComboProductService;
import com.rockstar.o2o.service.PlatformComboService;
import com.rockstar.o2o.service.PlatformComboServiceService;
import com.rockstar.o2o.service.PlatformCumboAdditionService;
import com.rockstar.o2o.service.PlatproductService;
import com.rockstar.o2o.service.ReportExportService;
import com.rockstar.o2o.service.ShopAdditionService;
import com.rockstar.o2o.service.ShopComboEditrecordService;
import com.rockstar.o2o.service.ShopComboService;
import com.rockstar.o2o.service.ShopFairerawardService;
import com.rockstar.o2o.service.ShopGroupService;
import com.rockstar.o2o.service.ShopInfoService;
import com.rockstar.o2o.service.ShopPriceService;
import com.rockstar.o2o.service.ShopProductBookBService;
import com.rockstar.o2o.service.ShopProductBookService;
import com.rockstar.o2o.service.ShopProductDeliveryBService;
import com.rockstar.o2o.service.ShopProductDeliveryService;
import com.rockstar.o2o.service.ShopProductInfoService;
import com.rockstar.o2o.service.ShopUserService;
import com.rockstar.o2o.service.UserFileService;
import com.rockstar.o2o.service.UserGroupService;
import com.rockstar.o2o.service.UserListService;
import com.rockstar.o2o.service.UserVerificationmodeService;
import com.rockstar.o2o.service.WxAutoreplyKeywordService;
import com.rockstar.o2o.service.WxAutoreplyMessageService;
import com.rockstar.o2o.service.WxAutoreplyService;
import com.rockstar.o2o.service.WxQrcodeDetailService;
import com.rockstar.o2o.service.WxQrcodeScancountBService;
import com.rockstar.o2o.service.WxQrcodeScancountService;
import com.rockstar.o2o.util.MailUtil;
import com.rockstar.o2o.util.ReturnPojo;

@Controller
public class BaseController {
	
	
	protected ReturnPojo pojo=new ReturnPojo();
	
	@Resource
	protected BasicService basicservice;
	
	@Resource
	protected IHttpClientService httpservice;
	
	//文件服务
	@Resource
	protected FileService fileservice;
	
	//消息服务
	@Resource
	protected MessageRecordService messageservice;

	
	//用户分组服务
	@Resource
	protected UserGroupService usergroupService;

	//用户表服务
	@Resource
	protected UserListService userlistService;
	
	//用户认证方式服务
	@Resource
	protected UserVerificationmodeService verificationservice;
	
	//手机客服端信息服务
	@Resource
	protected ClientInfoService clientinfoservice;
	
	//消费者信息服务
	@Resource
	protected CustomerInfoService customerinfoService;
	
	//店铺信息服务
	@Resource
	protected ShopInfoService shopinfoService;
	
	//店铺基本价格服务
	@Resource
	protected ShopPriceService shoppriceService;
	
	//店铺套餐服务
	@Resource 
	protected ShopComboService shopcomboService;
	
	//消费者持有套餐服务
	@Resource 
	protected CustomerComboService customercomboService;
	
	//后台店铺登录服务
	@Resource 
	protected ShopUserService shopuserService;
	
	//平台套餐服务
	@Resource 
	protected PlatformComboService platformcomboService;
	
	//消费者订单服务
	@Resource 
	protected CustomerOrderService customerorderService;
	
	//消费者订单明细服务
	@Resource 
	protected CustomerOrderDetailService customerorderdetailService;
	
	//平台套餐服务项目服务
	@Resource 
	protected PlatformCumboAdditionService platformadditionService;
	
	//理发师服务
	@Resource 
	protected FairerInfoService fairerinfoService;
	
	//店铺套餐修改记录服务
	@Resource 
	protected ShopComboEditrecordService comboeditrecordService;
	
	//消费者套餐使用记录服务
	@Resource 
	protected CustomerComboUserecordService customercomborecordService;
	
	//平台套餐包含产品服务
	@Resource 
	protected PlatformComboProductService platcomboprodcutService;
	
	//平台套餐包含服务服务
	@Resource 
	protected PlatformComboServiceService platcomboserviceService;
	
	//理发师技能服务
	@Resource 
	protected FairerSkillService fairerskillService;
	
	//内容管理服务
	@Resource 
	protected ContentManageService contentmanagerService;
	
	//内容管理图片服务
	@Resource 
	protected ContentPhotoService contentphotoService;
	
	//内容管理标签服务
	@Resource 
	protected ContentTagService contenttagService;
	
	//发型师附件服务
	@Resource 
	protected FairerAttachmentService fairerattachmentService;
	
		
	//区域档案服务
	@Resource 
	protected PlatformAreaService platformareaService;
	
	
	//美丽记录服务
	@Resource 
	protected CustomerOrderBeautyrecordService orderbeautyrecordService;
	
	
	//美丽记录服务吐槽记录
	@Resource 
	protected CustomerOrderSpitslotService orderspitslotService;
	
	//美丽记录服务吐槽记录
	@Resource 
	protected MessageListService messagelistService;
	
	//理发师收入明细
	@Resource 
	protected FairerCreditService fairercreditService;
	
	
	//热门类型
	@Resource 
	protected ContentHottypeService hottypeService;
	
	
	//热门词
	@Resource 
	protected ContentHotsearchService hotsearchService;
	
	
	//用户文件
	@Resource 
	protected UserFileService userfileService;
	
	//店铺商品表
	@Resource 
	protected ShopProductInfoService shopproductService;
	
	//商品订货
	@Resource 
	protected ShopProductBookService shopproductbookService;
	
	//商品订货明细表
	@Resource 
	protected ShopProductBookBService shopproductbookbService;
	
	
	//商品出库
	@Resource 
	protected ShopProductDeliveryService shopproductdeliveryService;
	
	
	//商品出库明细表
	@Resource 
	protected ShopProductDeliveryBService shopproductdeliverybService;
	
	
	//店铺组
	@Resource 
	protected ShopGroupService shopgroupService;
	
	
	//理发师提成项
	@Resource 
	protected ShopFairerawardService shopfairerawardService;
	
	//附加项目项
	@Resource 
	protected 	ShopAdditionService shopadditionService;
	
	
	//奖励
	@Resource 
	protected 	BdCustomerAwardService customerawardService;
	
	
	//奖励使用条件
	@Resource 
	protected 	BdCustomerAwardLimitService customerawardlimitService;
	
	
	//消费者持有的抵用券
	@Resource 
	protected 	CustomerOwnawardService customerownawardService;
	
	
	//消费者抵用券使用情况
	@Resource 
	protected 	CustomerUseawardService customeruseawardService;
	
	
	//套餐对应的抵用券
	@Resource 
	protected 	ComboAwardService comboawardService;
	

	//微信二维码详情
	@Resource 
	protected 	WxQrcodeDetailService wxQrcodeDetailService;
	//微信二维码统计
	@Resource 
	protected 	WxQrcodeScancountService wxQrcodeScancountService;
	
	//微信二维码统计详情
	@Resource 
	protected 	WxQrcodeScancountBService wxQrcodeScancountBService;
	//微信二维码统计详情--平台
	@Resource 
	protected 	PlatWxQrcodeScancountService platWxQrcodeScancountService;
	
	//微信自动回复
	@Resource 
	protected 	WxAutoreplyService wxAutoreplyService;
	
	//微信关键字自动回复（关键字管理）
	@Resource 
	protected 	WxAutoreplyKeywordService wxAutoreplyKeywordService;
	
	//微信关键字自动回复（回复内容管理）
	@Resource 
	protected 	WxAutoreplyMessageService wxAutoreplyMessageService;
	
	//统计报表导出
	@Resource 
	protected 	ReportExportService reportExportService;
	
	//平台登录服务
	@Resource 
	protected PlatUserService platuserService;
	
	//平台业绩
	@Resource 
	protected AchievementService achievementService;
	
	//平台库存管理
	@Resource 
	protected PlatproductService platproductService;
	//平台商品管理轨迹
	@Resource 
	protected PlatProductTraceService platProductTraceService;
	
	
	private static String env=System.getenv("CHORHAIR");
	protected static ResourceBundle bundle = ResourceBundle.getBundle("weixinconfig");
	protected static String account_id =(env==null||env.equals("test"))?bundle.getString("test_account_id")
			:bundle.getString("build_account_id"); 
	
	public void initpojo(){
		pojo=new ReturnPojo();
	}
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void output(HttpServletResponse response, ReturnPojo pojo) {
		try {
			String str=JSONObject.fromObject(pojo).toString();
			response.setContentType("text/html; charset=utf-8");
			//response.setCharacterEncoding("UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
		    response.getOutputStream().write(str.getBytes("UTF-8") );
		    response.getOutputStream().flush();	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 输出结果到response
	 * @param response
	 * @param str
	 */
	public void outputString(HttpServletResponse response, String str) {
		try {
			response.setContentType("text/html; charset=utf-8");
			//response.setCharacterEncoding("UTF-8");
		    response.getOutputStream().write(str.getBytes("UTF-8") );
		    response.getOutputStream().flush();	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 生成pojo
	 * @param response
	 * @param str
	 */
	public ReturnPojo outputstr(String data,String msg,boolean issuccess,Integer totalcount) {
           pojo.setData(data);
           pojo.setMsg(msg);
           pojo.setIssuccess(issuccess);
           pojo.setTotalcount(totalcount);
           return pojo;
	}
	
	
	/**
	 * 生成pojo
	 * @param response
	 * @param str
	 */
	public ReturnPojo outputexceptionstr() {
           pojo.setData("");
           pojo.setMsg("传入参数有误");
           pojo.setIssuccess(false);
           pojo.setTotalcount(null);
           return pojo;
	}
	
    /**
     * 异常捕获
     * @param e
     */
	public void dealexception(final Exception e) {
        e.printStackTrace();
	    //异步消息处理
//	    new Thread(){
//	    	public void run(){
//	    		sendmail(e);
//	    	}
//	    }.start();
	}
	

	public void sendmail(Exception e){
		StackTraceElement[] elements=e.getStackTrace();
        StringBuffer buffer=new StringBuffer();
        buffer.append(e.getLocalizedMessage());
    	buffer.append("\n");
        for(StackTraceElement element:elements){
        	
        	buffer.append("classname:"+element.getClassName()+",methodname:"+element.getMethodName()+",linenumber:"+element.getLineNumber());
        	buffer.append("\n");
        }
       //MailUtil.sendexception("SERVER项目:"+"虫二管理系统出现bug,请查看,并及时修改", buffer.toString());
	}
	
}
