package com.rockstar.o2o.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.WxAutoreplyKeyword;
import com.rockstar.o2o.service.WxAutoreplyKeywordService;
import com.rockstar.o2o.util.DateUtil;
@Component
public class WxAutoreplyKeywordServiceImpl extends BaseServiceImpl implements WxAutoreplyKeywordService {

	@Override
	public WxAutoreplyKeyword saveKeywordInfo(WxAutoreplyKeyword info) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(info);
		baseDao.add(info);
		info.setPkKeywordId(Long.parseLong(pk.toString()));
		return info;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxAutoreplyKeyword> getKeywordInfo(Long pkAutoreplyId) {
		// TODO Auto-generated method stub
		List<WxAutoreplyKeyword> keywordInfo=(List<WxAutoreplyKeyword>) baseDao.pageQuery("from WxAutoreplyKeyword a where IFNULL(a.dr,0)=0 and a.pkAutoreplyId=?",pkAutoreplyId);
		if(keywordInfo.size()>0){
			return keywordInfo;
		}
		return null;
	}

	@Override
	public int update(WxAutoreplyKeyword info) {
		// TODO Auto-generated method stub
		int result=baseDao.update(info);
	    return result;
	}

	@Override
	public WxAutoreplyKeyword getInfoById(String pkKeywordId) {
		// TODO Auto-generated method stub
		long id=Long.parseLong(pkKeywordId);
		return (WxAutoreplyKeyword) baseDao.getById(WxAutoreplyKeyword.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public WxAutoreplyKeyword getInfoByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<WxAutoreplyKeyword> keywordInfo=(List<WxAutoreplyKeyword>) baseDao.pageQuery("from WxAutoreplyKeyword  where IFNULL(dr,0)=0 and  instr(?,keyword)>0 order by ts desc",keyword);
		if(keywordInfo.size()>0){
			String mathMode=keywordInfo.get(0).getMatchMode();
			if(mathMode.equals("EQUAL")){
				List<WxAutoreplyKeyword> Info=(List<WxAutoreplyKeyword>) baseDao.pageQuery("from WxAutoreplyKeyword  where IFNULL(dr,0)=0 and  keyword=? order by ts desc",keyword);
				if(Info.size()>0){
					return Info.get(0);
				}
			}else{
				return keywordInfo.get(0);
			}
		}
		return null;
	}
	
	@Override
	public void deleteById(Long pkKeywordId) {
		WxAutoreplyKeyword keywordInfo=(WxAutoreplyKeyword) baseDao.getById(WxAutoreplyKeyword.class, pkKeywordId);
		keywordInfo.setDr((short) 1);
		keywordInfo.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(keywordInfo);
		
	}

}
