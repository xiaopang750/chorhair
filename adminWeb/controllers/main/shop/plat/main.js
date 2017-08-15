/**
 *description: 平台
 *author:wangwc
 *date:2015/4/16
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/shop/baseController')(app);

	var Plat = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//1.二维码列表
			app.all(preRoute + 'platCode', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.plat.platCode, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//2.发布二维码
			app.all(preRoute + 'publishCode', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.plat.publishCode, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//3.下载二维码
			app.all(preRoute + 'downloadCode', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.plat.downloadCode, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});
		}	

	});

	var oPlat = new Plat();

};





