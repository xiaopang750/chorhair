package com.rockstar.o2o.controller;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rockstar.o2o.constant.ComboType;
import com.rockstar.o2o.constant.FairType;
import com.rockstar.o2o.pojo.ShopCombo;
import com.rockstar.o2o.pojo.ShopComboEditrecord;
import com.rockstar.o2o.pojo.ShopFaireraward;
import com.rockstar.o2o.pojo.ShopPrice;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.util.CharUtil;
import com.rockstar.o2o.util.DateUtil;
import com.rockstar.o2o.util.RequestUtil;


/**
 * 店铺套餐
 * @author hp
 *
 */
@Controller
@RequestMapping("/shopcombo")
public class ShopComboController extends BaseController{
	
	
	/**
	 * 根据主键查询信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/findbyid")
	public void findbyid(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
			JSONObject obj=RequestUtil.getPostString(request);
			String pkShopCombo=obj.getString("pkShopCombo");
			String pkShop=obj.getString("pkShop");
			
			ShopCombo combo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));
			if(combo!=null){
				JSONObject returnobj=JSONObject.fromObject(combo);
				ArrayList<ShopFaireraward> awards=(ArrayList<ShopFaireraward>) shopfairerawardService.querybyshopandtype(pkShop, pkShopCombo, "COMBO");
			    if(awards.size()>0){
			    	returnobj.accumulate("awards", JSONArray.fromObject(awards).toString());
			    }
			     outputstr(returnobj.toString(), "查询套餐信息成功", true, null);
			     
			}else{
				
				outputstr("", "套餐不存在", false, null);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 查询店铺所属套餐
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/findbyshop")
	public void findbyshop(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
        String pkShop=obj.getString("pkShop");
        String sex=obj.get("sex")==null?"":obj.getString("sex");
        String querylike=(String)obj.get("querylike");
		String curpage=obj.get("curpage")==null?"":obj.getString("curpage");
		String pagesize=obj.get("pagesize")==null?"":obj.getString("pagesize");
		ArrayList<ShopCombo> combolist=new ArrayList<ShopCombo>();
		ArrayList<ShopCombo> totalcombolist=new ArrayList<ShopCombo>();
		if(!curpage.equals("")&&!pagesize.equals("")){
			if(sex.equals("")){
				combolist=(ArrayList<ShopCombo>) shopcomboService.QueryByShop(pkShop,querylike,Integer.parseInt(curpage),Integer.parseInt(pagesize));	
				totalcombolist=(ArrayList<ShopCombo>) shopcomboService.QueryByShop(pkShop,querylike,null,null);
			}else{
			    combolist=(ArrayList<ShopCombo>) shopcomboService.QueryByShopAndSex(pkShop,querylike,sex,Integer.parseInt(curpage),Integer.parseInt(pagesize));
			}
		}else{
			 if(sex.equals("")){
				 combolist=(ArrayList<ShopCombo>) shopcomboService.QueryByShop(pkShop,querylike,null,null);	
			}else{
				 combolist=(ArrayList<ShopCombo>) shopcomboService.QueryByShopAndSex(pkShop,querylike,sex,null,null);
			}
		}
	   
        if(combolist!=null){
        String result=JSONArray.fromObject(combolist).toString();
        if(!curpage.equals("")){
        outputstr(result, "查询成功", true, totalcombolist.size());
        }else{
         outputstr(result, "查询成功", true, combolist.size()) 	;
        }
        }else{
        outputstr("", "无店铺套餐信息", false, null);
        }
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
            outputexceptionstr();
		}
		output(response, pojo);
	}
	
	/**
	 * 保存店铺套餐信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/save")
	public void savecombo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
        String pkShop=obj.getString("pkShop");
        String pkCumbo=obj.getString("pkCumbo");
        String comboname=obj.getString("comboname");
        String combotype=obj.getString("combotype");
        String standardmoney=obj.getString("standardmoney");
        String shopmoney=obj.getString("shopmoney");
        String currentmoney=obj.getString("currentmoney");
        String fairermoney=obj.get("fairermoney")==null?"0":obj.getString("fairermoney");//理发师抽成
        String servicemoney=obj.get("servicemoney")==null?"0":obj.getString("servicemoney");//服务抽成
        String combonote=obj.get("combonote")==null?"":obj.getString("combonote"); 

        ShopCombo combo=new ShopCombo();
        combo.setPkShop(Long.parseLong(pkShop));
        combo.setPkCombo(Long.parseLong(pkCumbo));
        combo.setComboname(comboname);
        combo.setCombotype(combotype);
        combo.setStandardmoney(Double.parseDouble(standardmoney));
        combo.setShopmoney(Double.parseDouble(shopmoney));
        combo.setCurrentmoney(Double.parseDouble(currentmoney));
        combo.setFairermoney(Double.parseDouble(fairermoney));
        combo.setServicemoney(Double.parseDouble(servicemoney));
        combo.setCombonote(combonote);
        
        combo = shopcomboService.save(combo);
        outputstr("", "保存成功", true, null);
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
	/**
	 * 修改店铺套餐信息,只允许修改价格和说明
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/edit")
	public void editcombo(HttpServletRequest request,HttpServletResponse response,Model model) {
		try {
		JSONObject obj=RequestUtil.getPostString(request);
		String pkShopCombo=obj.getString("pkShopCombo");
		String pkShop=obj.getString("pkShop");
		String pkUser=obj.getString("pkUser");
        
        String PkAdditiongroup=obj.get("pkAdditiongroup")==null?"":obj.getString("pkAdditiongroup");//附加项目组
        String PkServicegroup=obj.get("pkServicegroup")==null?"":obj.getString("pkServicegroup");//服务组
        
        String awards=obj.get("awards")==null?"":obj.getString("awards");//提成比例分摊
        
        String combonote=obj.get("combonote")==null?"":obj.getString("combonote"); 

        ShopCombo combo=shopcomboService.getComboById(Long.parseLong(pkShopCombo));
        String oldpkAdditiongroup=combo.getPkAdditiongroup()==null?"":combo.getPkAdditiongroup().toString();//原附加项目组
        String oldpkServicegroup=combo.getPkServicegroup()==null?"":combo.getPkServicegroup().toString();//原附加项目组
        String oldcombonote=combo.getCombonote()==null?"":combo.getCombonote(); 
        
        combo.setCombonote(combonote);
        combo.setPkAdditiongroup(PkAdditiongroup.equals("")?null:Long.parseLong(PkAdditiongroup));//附加项目组
        combo.setPkServicegroup(PkServicegroup.equals("")?null:Long.parseLong(PkServicegroup)); //服务组
        combo.setCombopy(CharUtil.chineseToPingyin(combo.getComboname()));
        combo.setCombospy(CharUtil.getHeadChar(combo.getComboname()));
      	ArrayList<Object> updatelist=new ArrayList<Object>();
    	ArrayList<Object> addlist=new ArrayList<Object>();
      	
        
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
        
        ShopUser user=shopuserService.getUserById(Long.parseLong(pkUser));
        
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
        			newaward.setOperatorid(user.getPkShopuser());
        			newaward.setOperatename(user.getShopusername());
        			newaward.setOperatetime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        			
        			addlist.add(newaward);
        		}else{
        			ShopFaireraward oldaward=shopfairerawardService.getawardById(Long.parseLong(pkFaireraward));
        			oldaward.setAwardname(awardname);
        			oldaward.setIsrate(israte);
        			oldaward.setAwardmoney(Double.parseDouble(awardmoney));
        			oldaward.setDr(Short.parseShort(dr));
        			
        			updatelist.add(oldaward);
        		}
        	}
        	
        	                 
          if(!oldpkAdditiongroup.equalsIgnoreCase(PkAdditiongroup)){
        	  ShopComboEditrecord record=new ShopComboEditrecord();
        	  record.setPkShop(Long.parseLong(pkShop));
        	  record.setPkShopCombo(Long.parseLong(pkShopCombo));
        	  record.setEditor(user.getShopusername());
        	  record.setEditcontent("修改附加项目组,原:"+oldpkAdditiongroup+",现:"+PkAdditiongroup);
        	  record.setDr((short)0);
        	  record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        	  record.setEdittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        	  addlist.add(record);
          }
          
          if(!oldpkServicegroup.equalsIgnoreCase(PkServicegroup)){
        	  ShopComboEditrecord record=new ShopComboEditrecord();
        	  record.setPkShop(Long.parseLong(pkShop));
        	  record.setPkShopCombo(Long.parseLong(pkShopCombo));
        	  record.setEditor(user.getShopusername());
        	  record.setEditcontent("修改服务价格组,原:"+oldpkServicegroup+",现:"+PkServicegroup);
        	  record.setDr((short)0);
        	  record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        	  record.setEdittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        	  addlist.add(record);
          }
          
          
          if(!oldcombonote.equalsIgnoreCase(combonote)){
        	  ShopComboEditrecord record=new ShopComboEditrecord();
        	  record.setPkShop(Long.parseLong(pkShop));
        	  record.setPkShopCombo(Long.parseLong(pkShopCombo));
        	  record.setEditor(user.getShopusername());
        	  record.setEditcontent("修改备注信息,原:"+oldcombonote+",现:"+combonote);
        	  record.setDr((short)0);
        	  record.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        	  record.setEdittime(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
        	  addlist.add(record);
          }
          
          basicservice.batchoperate(addlist, updatelist);
         
          outputstr("", "修改套餐信息成功", true, null);
          
        }else{
        	
          outputstr("", "修改套餐信息失败", false, null);
        }        
		} catch (Exception e) {
			// TODO: handle exception
			dealexception(e);
			outputexceptionstr();
		}
		output(response, pojo);
	}
	
	
}
