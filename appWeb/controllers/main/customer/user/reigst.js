/**
 *description:注册
 *author:fanwei
 *date:2015/1/15
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);

	var User = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			app.all(route, function(req, res){

				var param = _this.getParam(req);
				//var name = param.username;
				//var pass = param.password;
				//var result = _this.judge(name, pass, res);

				_this.request(req, res);

			});

		},
		request: function(req, res) {

			var _this = this;

			this.save(req, res, this.inter.user.info, param, function(data){

				_this.saveUserSession(req, data.data);

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
		},
		saveUserSession: function(req, data) {

			var json = {};
			req.session.uid = data.uid;
			json[data.uid] = req.cookies['connect.sid'];
			req.session.nowLoginUser = json;

		}

	});

	var oUser = new User();

}; 