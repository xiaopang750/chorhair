package com.rockstar.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.rockstar.o2o.pojo.FairerAttachment;
import com.rockstar.o2o.service.FairerAttachmentService;
import com.rockstar.o2o.util.DateUtil;

@Component
public class FairerAttachmentServiceImpl extends BaseServiceImpl implements FairerAttachmentService{

	@Override
	public FairerAttachment save(FairerAttachment attachment) {
		// TODO Auto-generated method stub
		Object pk=baseDao.add(attachment);
		baseDao.add(attachment);
		attachment.setPkAttachment(Long.parseLong(pk.toString()));
		return attachment;
	}

	@Override
	public void savelist(ArrayList<FairerAttachment> attachments) {
		// TODO Auto-generated method stub
		baseDao.batchsave(attachments);
	}

	@Override
	public FairerAttachment getAttachmentById(Long id) {
		// TODO Auto-generated method stub
	   return (FairerAttachment) baseDao.getById(FairerAttachment.class,id);
	}

	@Override
	public void deleteattachmentById(Long id) {
		// TODO Auto-generated method stub
		FairerAttachment attachment=(FairerAttachment) baseDao.getById(FairerAttachment.class, id);
		attachment.setDr((short) 1);
		attachment.setTs(DateUtil.getCurrDate(DateUtil.FORMAT_ONE));
		baseDao.update(attachment);
	}

	@Override
	public int updateattachment(FairerAttachment attachment) {
		// TODO Auto-generated method stub
		int result=baseDao.update(attachment);
	    return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FairerAttachment> querybypkFairer(String pkFairer) {
		// TODO Auto-generated method stub
		Long longpkFairer=Long.parseLong(pkFairer);
		// TODO Auto-generated method stub
		List<FairerAttachment> fairattachmentlist=(List<FairerAttachment>) baseDao.pageQuery("from FairerAttachment where IFNULL(dr,0)=0 and pkFairer = ? ", longpkFairer);
		return fairattachmentlist;
	}

}
