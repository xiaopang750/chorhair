/**
 *description:base-util
 *author:fanwei
 *date:2013/11/01
 */

/**
	util模块提供
	1.解析search参数;
	  this.parse();

	2.ajaxload;
	  this.reqUrl = '地址';
	  this.reqParam = {} 参数;
	  this.req(suc, fail, error); 发送请求;

	3.模板渲染;
	  this.render(oWrap, oTpl, data, way);
	  oWrap: 容器
	  oTpl: require cmd格式的对象


	4.日志打印;
	  this.log('xxxx');

*/
define(function(require, exports, module) {

	var Class = require('../../lib/ooClass/class');
	var bodyParse = require('../../util/http/bodyParse');

	var Util = Class.create({

		initialize: function() {
			this.param = {};
		},
		parse: function() {
			//解析search参数
			return bodyParse();
		},
		checkLogin: function(){

			if(!R.pkUser){
				window.location = '../user/login.jsp';
			}

		},
		req: function(url, param, fn) {

			var param = param || {};

			param.accountid = R.accountId;
            param.openid = R.openId;
			param.pkUser = R.pkUser;
			param.usergroup = '001';
			
			var reqParam = {
				data: JSON.stringify(param),
				url: url
			};

			$.post('/middle/prehandle.php', reqParam, function(data) {
				var json = JSON.parse(data);

                fn && fn(json);
            });
            
		},
		render: function(oWrap, oTpl, data, way) {
			//模板渲染
			var html = oTpl(data);

			if(!way) {

				oWrap.html( html );

			} else if(way == 'prepend') {

				var oNew = $(html);
				oWrap.prepend(oNew);
				return oNew;

			} else if(way == 'append') {

				var oNew = $(html);
				oWrap.append(oNew);
				return oNew;
			}
		},
		log: function(str) {
			//打印调试日志
			if(window.console) {
				console.log(str);
			}
		}

	});
	
	module.exports = Util;

});