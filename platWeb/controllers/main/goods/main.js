/**
 *description: 商品
 *author:fanwei
 *date:2015/1/26
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var Goods = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//1.编辑商品
			app.all(preRoute + 'goodsEdit', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkShop = sessionInfo.pkShop;
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.goods.goodsEdit, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//2.库存列表
			app.all(preRoute + 'stockList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.stockList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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

			//3.库存列表
			app.all(preRoute + 'platStockList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.platStockList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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

			//4.库存价格修改
			app.all(preRoute + 'stockPriceEdit', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.goods.stockPriceEdit, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//5.订单列表
			app.all(preRoute + 'bookList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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


			//6.订单添加
			app.all(preRoute + 'bookAdd', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookAdd, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: _this.viewConfig['goods/bookList'].url,
						res: res
					});

				});

			});

			//7.订单编辑
			app.all(preRoute + 'bookEdit', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookEdit, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: _this.viewConfig['goods/bookList'].url,
						res: res
					});

				});

			});

			//8.查询订单下的商品
			app.all(preRoute + 'bookData', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookData, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount,
							note: data.note
						},
						res: res
					});

				});

			});

			//9.确认收货
			app.all(preRoute + 'bookConfirm', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookConfirm, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//10.提交订单
			app.all(preRoute + 'bookSubmit', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookSubmit, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//11.修改实收
			app.all(preRoute + 'bookRealModify', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookRealModify, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//12.平台修改实收
			app.all(preRoute + 'bookPlatRealModify', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookPlatRealModify, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//13.出库列表
			app.all(preRoute + 'deliveryList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.deliveryList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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


			//14.确认出库
			app.all(preRoute + 'deliveryConfirm', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.deliveryConfirm, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//15.出库添加
			app.all(preRoute + 'deliveryAdd', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.deliveryAdd, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: _this.viewConfig['goods/deliveryList'].url,
						res: res
					});

				});

			});

			//16.出库编辑
			app.all(preRoute + 'deliveryEdit', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.deliveryEdit, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: _this.viewConfig['goods/deliveryList'].url,
						res: res
					});

				});

			});

			//17.获取出库数据
			app.all(preRoute + 'deliveryData', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.deliveryData, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount,
							note: data.note
						},
						res: res
					});

				});

			});

			//18.平台订货单列表
			app.all(preRoute + 'platBookList', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.goods.platBookList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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

			//19.通过驳回
			app.all(preRoute + 'bookApprove', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.bookApprove, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//20.根据套餐主键获取抵用券
			app.all(preRoute + 'getSale', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.getSale, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//21.确定选择抵用券
			app.all(preRoute + 'confirmSale', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.confirmSale, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//22.平台新增商品
			app.all(preRoute + 'platAddProduct', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.platAddProduct, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//23.新增平台商品数量
			app.all(preRoute + 'addProductNum', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.goods.addProductNum, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//24.平台库存修改记录
			app.all(preRoute + 'platStockRecord', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.platStockRecord, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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

			//25.店铺库存列表
			app.all(preRoute + 'shopStockList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.shopStockList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

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

			//26.平台新增套餐
			app.all(preRoute + 'platAddCombo', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.platAddCombo, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//27.获取平台套餐编辑数据
			app.all(preRoute + 'platGetCombo', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.platGetCombo, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//28.平台修改套餐
			app.all(preRoute + 'platEditCombo', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.platEditCombo, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//29.套餐店铺属性
			app.all(preRoute + 'shopComboAttr', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.shopComboAttr, param, function(data){

					var info = data.data ? JSON.parse(data.data) : "";

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//30.保存套餐店铺属性
			app.all(preRoute + 'saveShopCombo', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.saveShopCombo, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//31.平台订货单发货
			app.all(preRoute + 'sendGoods', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.goods.sendGoods, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

		}	

	});

	var oGoods = new Goods();

};





