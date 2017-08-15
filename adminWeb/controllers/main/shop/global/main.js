/**
 *description: 全局接口
 *author:fanwei
 *date:2015/1/26
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/shop/baseController')(app);

	var Global = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//密码修改
			app.all(preRoute + 'modifyPass', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.global.modifyPass, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//区域联动
			app.all(preRoute + 'getArea', function(req, res){

				var param = _this.getParam(req);

				/*var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;*/

				_this.request(req, res, _this.inter.global.getArea, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});
			
			//获取微信签名
			app.all(preRoute + 'getSignature', function(req, res){

				var param = _this.getParam(req);

				/*var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;*/

				_this.request(req, res, '/wx/wxjs/getinfo.php', param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				}, function(){}, 'chwx.rs07.com', '80');

			});

		}	

	});

	var oGlobal = new Global();

};





