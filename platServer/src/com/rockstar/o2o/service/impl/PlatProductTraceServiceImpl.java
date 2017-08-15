package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.PlatProductTrace;
import com.rockstar.o2o.service.PlatProductTraceService;
@Component
public class PlatProductTraceServiceImpl extends BaseServiceImpl implements PlatProductTraceService {

	@Override
	public PlatProductTrace save(PlatProductTrace trace) {
		Object pk=baseDao.add(trace);
		baseDao.add(trace);
		trace.setPkProductTrace(Long.parseLong(pk.toString()));
		return trace;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlatProductTrace> gettrace(String pkProduct, Integer curpage,Integer pagesize) {
		List<PlatProductTrace> productlists=new ArrayList<PlatProductTrace>();
		StringBuffer buffer=new StringBuffer();
		buffer.append(" from PlatProductTrace where IFNULL(dr,0)=0 and pkProduct=? " );
		buffer.append(" order by operatetime desc " );
		if(curpage!=null){
			productlists=(List<PlatProductTrace>) baseDao.pageQuery(buffer.toString(),curpage,pagesize,Long.parseLong(pkProduct));
		}else{
			productlists=(List<PlatProductTrace>) baseDao.pageQuery(buffer.toString(),Long.parseLong(pkProduct));
		}
		return productlists;
	}

}
