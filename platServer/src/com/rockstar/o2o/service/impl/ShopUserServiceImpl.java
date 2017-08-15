package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.ShopUser;
import com.rockstar.o2o.service.ShopUserService;

@Component
public class ShopUserServiceImpl extends BaseServiceImpl implements ShopUserService{

	@Override
	public ShopUser save(ShopUser user) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(user);
		baseDao.add(user);
		user.setPkShopuser(Long.parseLong(pk.toString()));
		return user;
	}

	@Override
	public ShopUser getUserById(Long id) {
		// TODO Auto-generated method stub
		return (ShopUser) baseDao.getById(ShopUser.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopUser> getAllUser() {
		// TODO Auto-generated method stub
		return (List<ShopUser>) baseDao.getAll(ShopUser.class);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		ShopUser user=(ShopUser) baseDao.getById(ShopUser.class, id);
		user.setDr((short) 1);
		baseDao.update(user);
	}

	@Override
	public int updateUser(ShopUser user) {
		// TODO Auto-generated method stub
		int result=baseDao.update(user);
	    return result;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShopUser login(String usercode, String password, String usergroup) {
		// TODO Auto-generated method stub
		List<ShopUser> userlist=(List<ShopUser>) baseDao.pageQuery("from ShopUser where IFNULL(dr,0)=0 and shopusercode = ? and loginpassword = ? ", usercode , password);
		if(userlist.size()>0){
			return userlist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> querybysql(String sql) {
		// TODO Auto-generated method stub
		return (List<Object>) baseDao.querybysql(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getUserByCon(String province,String city, String county,String pkShop, Integer curpage,Integer pagesize) {
		List<Object> shoplists = new ArrayList<Object>();
		StringBuffer stb=new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		stb.append("select s.pk_shopuser as pkShopuser,");
		stb.append("s.pk_shop AS pkShop,");
		stb.append("s.shopusercode as shopusercode,");
		stb.append("t.shopname as shopname,");
		stb.append("s.shopusername as shopusername,");
		stb.append("s.cellphone as cellphone,");
		stb.append("substr(s.registertime,1,LENGTH(s.registertime))as registertime ");
		stb.append(" from shop_user s left join shop_info t on s.pk_shop=t.pk_shop where IFNULL(s.dr,0)=0 and s.usertype='1' ");
		
		if(province!=null&&!province.equals("")){
			stb.append(" and t.province = :province   " );
			map.put("province", province);
		}
		if(city!=null&&!city.equals("")){
			stb.append(" and t.city = :city   " );
			map.put("city", city);
		}
		if(county!=null&&!county.equals("")){
			stb.append(" and t.county = :county   " );
			map.put("county", county);
		}
		if(pkShop!=null && !pkShop.equals("")){
			stb.append("and s.pk_shop=:pkShop ");
			long pkshopL = Long.parseLong(pkShop);
			map.put("pkShop", pkshopL);
		}
		if(curpage!=null){
			shoplists=(List<Object>) baseDao.querySqlListByConMap(stb.toString(),curpage,pagesize,map);
		}else{
			shoplists=(List<Object>) baseDao.querySqlListByConMap(stb.toString(),map);
		}
        return shoplists;
	}

}
