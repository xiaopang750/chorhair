package com.rockstar.o2o.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rockstar.o2o.dao.BaseDao;

@Component
public class BaseServiceImpl {
	
	@Resource
	protected BaseDao baseDao;
}
