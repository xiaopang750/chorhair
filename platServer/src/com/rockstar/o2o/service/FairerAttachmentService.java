package com.rockstar.o2o.service;

import java.util.ArrayList;
import java.util.List;

import com.rockstar.o2o.pojo.FairerAttachment;


public interface FairerAttachmentService {
	/**
	 * 添加一个附件
	 * @param member
	 */
	FairerAttachment save(FairerAttachment attachment);
	
	/**
	 * 批量添加附件
	 * @param member
	 */
	void savelist(ArrayList<FairerAttachment> attachments);
	/**
	 * 根据ID，获取附件
	 * @param id
	 * @return
	 */
	FairerAttachment getAttachmentById(Long id);
	/**
	 * 根据ID删除附件
	 * @param id
	 */
	void deleteattachmentById(Long id);
	/**
	 * 修改附件
	 * @param id
	 */
	int updateattachment(FairerAttachment attachment);
	/**
	 * 根据发型师主键查询
	 * @param id
	 */
	List<FairerAttachment> querybypkFairer(String pkFairer);
}
