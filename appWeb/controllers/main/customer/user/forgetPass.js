/**
 *description: 忘记密码
 *author:fanwei
 *date:2015/1/15
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);

	var ForgetPass = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//获取忘记秘密吗验证码
			app.all(route, function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, param);

			});

		},
		request: function(req, res, param) {

			var _this = this;
			
			this.save(req, res, this.inter.user.forgetPass, param, function(data){
				
				_this.outJson({
					code: '001',
					msg: data.msg,
					data: '',
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

	var oForgetPass = new ForgetPass();

}; 