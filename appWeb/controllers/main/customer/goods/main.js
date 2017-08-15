/**
 *description: 商品
 *author:fanwei
 *date:2015/2/4
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);

	var Goods = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//获取商品列表
			app.all(preRoute + 'all', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.goods.all, param, function(data){
						
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

			//根据主键查询商品赞数
			app.all(preRoute + 'getGood', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.goods.getGood, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: data.data,
						res: res
					});

				});

			});


			//商品赞
			app.all(preRoute + 'good', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.goods.good, param, function(data){

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