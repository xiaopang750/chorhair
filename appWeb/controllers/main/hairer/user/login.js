/**
 *description:登录
 *author:fanwei
 *date:2015/1/15
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/hairer/baseController')(app);
	var setCookie = require('../../../../lib/cookie');

	var User = Class.create(baseController, {

		initialize: function() {
			
			this.events();
		},
		events: function() {

			var _this = this;

			app.all(route, function(req, res){

				var param = _this.getParam(req);
				var name = param.cellphone;
				var pass = param.loginpassword;
				var result = _this.judge(name, pass, res);

				//console.log(_this.inter);

				_this.request(req, res, param);

			});

		},
		request: function(req, res, param) {

			var _this = this;

			this.save(req, res, this.inter.user.login, param, function(data){
				
				var info = JSON.parse(data.data);

				_this.saveUserSession(req, info);
				
				setCookie(res, 'pkUser', info.pkUser, '30day');
				
				_this.outJson({
					code: '001',
					msg: data.msg,
					data: _this.viewConfig['appoint/index'].url,
					res: res
				});

			});

		},
		judge: function(name, pass, res) {

			if(name && pass) {

				return true;

			} else {

				this.outJson({
					code: '002',
					msg: '用户名或密码不能为空',
					data: '',
					res: res
				});

				return;

			}
		}	

	});

	var oUser = new User();

};





