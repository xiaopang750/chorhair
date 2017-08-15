/**
 *description: 店铺
 *author:fanwei
 *date:2015/3/25
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var Shop = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//1.店铺列表
			app.all(preRoute + 'shopList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.shopList, param, function(data){

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

			//2.店铺信息
			app.all(preRoute + 'shopInfo', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.shopInfo, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//3.店铺二维码列表
			app.all(preRoute + 'qrCode', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.qrCode, param, function(data){

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

			//4.下载店铺二维码
			app.all(preRoute + 'downloadQrcode', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.downloadQrcode, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//5.编辑店铺信息
			app.all(preRoute + 'editShop', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.editShop, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//6.保存店铺信息
			app.all(preRoute + 'saveShop', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.saveShop, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});
			
			//7.店铺账号
			app.all(preRoute + 'shopAccount', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.shopAccount, param, function(data){

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

			//8.店铺账号编辑
			app.all(preRoute + 'editAccount', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.editAccount, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//9.店铺账号新增
			app.all(preRoute + 'addAccount', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.shop.addAccount, param, function(data){

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

	var oShop = new Shop();

};





