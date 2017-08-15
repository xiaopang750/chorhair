/**
 *description: 密码重置
 *author:fanwei
 *date:2015/1/15
 */

/*
	需要验证是否能进入这个页面
*/

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/hairer/baseController')(app);

	var ResetPass = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			app.all(route, function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, param);

			});

		},
		request: function(req, res, param) {

			var _this = this;

			this.save(req, res, this.inter.user.resetPass, param, function(data){
				
				_this.outJson({
					code: '001',
					msg: data.msg,
					data: _this.viewConfig['user/login'].url,
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

	var oResetPass = new ResetPass();

}; 