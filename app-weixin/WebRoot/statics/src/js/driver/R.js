/**
 *description: global-use
 *author:fanwei
 *date:2014/07/14
 */
define(function(require, exports, module){
	
	/*	
		全局R对象上暴露一些基础方法
		@param domain 请求的基础路径, 如果是同一域名reqBase domain唯一
		@param assets 在js中调用静态图片系统文件路径, 如头像
		@param css 在js中调用css路径
		@param interfaces 接口
	*/
	window.R = window.R || {};

	var Class = require('../lib/ooClass/class');
	var util = require('./base/base_util');
	var bodyParse = require('../util/http/bodyParse');

	R.Class = Class;
	R.util = util;

	//获取openid和accountid
	if(bodyParse().openid && bodyParse().accountid){
		sessionStorage.openId = bodyParse().openid;
		sessionStorage.accountId = bodyParse().accountid;
	}

	//获取pkUser
	if(bodyParse().pkUser){
		sessionStorage.pkUser = bodyParse().pkUser;
	}

	R.accountId = bodyParse().accountid || sessionStorage.accountId;
	R.openId = bodyParse().openid || sessionStorage.openId;
	R.pkUser = bodyParse().pkUser || sessionStorage.pkUser;

});