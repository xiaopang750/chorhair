/**
 *description: 路径配置
 *author:fanwei
 *date:2014/11/20
 */
define(function(require, exports, module){
	
	/*
		@param: R.uri.reqPrefix ajax请求的公共前缀
		@param: R.uri.assets js中调用静态资源的路径
		@param: R.uri.css js中调用css的路径
	*/
	var nowWay = $('body').attr('nowWay');

	/*R.uri = {
		reqPrefix: "/main/" + nowWay + "/",
		uploadPrefix: "/upload/" + nowWay + "/",
		assets: "../statics/assets/",
		css: "../statics/src/css/",
		views: "../statics/src/views/"
	};*/
	
	var user = require('./interfaces/user');
	var search = require('./interfaces/search');
	var upload = require('./interfaces/upload');
	var appoint = require('./interfaces/appoint');
	var goods = require('./interfaces/goods');
	var message = require('./interfaces/message');
	var record = require('./interfaces/record');
	
	R.interfaces = {
		user: user,
		search: search,
		upload: upload,
		appoint: appoint,
		goods: goods,
		message: message,
		record: record
	};

	//当前渠道
	R.nowWay = nowWay;

});




