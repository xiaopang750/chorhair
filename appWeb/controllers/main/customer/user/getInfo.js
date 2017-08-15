/**
 *description: 获取用户信息
 *author:fanwei
 *date:2015/1/15
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);
	var setCookie = require('../../../../lib/cookie');

	var GetInfo = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			app.all(route, function(req, res){

				var param = _this.getParam(req);
				//var result = _this.judge(name, pass, res);

				_this.request(req, res, param);

			});


			//获取用户套餐
			app.all(preRoute + 'getPackage', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.user.getPackage, param, function(data){
						
					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				}, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: [],
						res: res
					});

				});	

			});

			//退出
			app.all(preRoute + 'loginOut', function(req, res){

				req.session.uid = '';
				req.session.cellphone = '';
				setCookie(res, 'pkUser', 'xxx', '-30day');
				res.redirect(_this.viewConfig['user/index'].url);

			});

			//用户信息修改
			app.all(preRoute + 'editInfo', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.user.editInfo, param, function(data){
						
					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				}, function(data){

					_this.outJson({
						code: '002',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

		},
		request: function(req, res, param) {

			var _this = this;

			this.save(req, res, this.inter.user.getInfo, param, function(data){

				//_this.saveUserSession(req, data.data);
				var info = JSON.parse(data.data);

				if(!info.headshorturl) {

					info.headshorturl = app.get('defaultHeader');

				}

				_this.outJson({
					code: '001',
					msg: data.msg,
					data: info,
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

	var oGetInfo = new GetInfo();

}; 