/**
 *description: 业绩管理
 *author:fanwei
 *date:2015/1/26
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var Performance = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//业绩管理主列表
			app.all(preRoute + 'getAllList', function(req, res){

				var param = _this.getParam(req);
				var sessinInfo = _this.readSession(req);
				param.pkShop = sessinInfo.pkShop;
				
				_this.request(req, res, _this.inter.performance.getAllList, param, function(data){

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

			//获取理发师业绩
			app.all(preRoute + 'getHairList', function(req, res){

				var param = _this.getParam(req);
				var sessinInfo = _this.readSession(req);
				param.pkShop = sessinInfo.pkShop;

				_this.request(req, res, _this.inter.performance.getHairList, param, function(data){

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

			//获取历史订单
			app.all(preRoute + 'getHistoryList', function(req, res){

				var param = _this.getParam(req);
				var sessinInfo = _this.readSession(req);
				param.pkShop = sessinInfo.pkShop;

				_this.request(req, res, _this.inter.performance.getHistoryList, param, function(data){

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

			//获取店面周收入
			app.all(preRoute + 'getIncome', function(req, res){

				var param = _this.getParam(req);
	
				_this.request(req, res, _this.inter.performance.getIncome, param, function(data){

						var info = data.data ? JSON.parse(data.data) : data.data;

						_this.outJson({
							code: '001',
							msg: data.msg,
                            data: info,
							res: res
						});

				});

			});

			//获取店面周日均收入
			app.all(preRoute + 'getAvgincome', function(req, res){

				var param = _this.getParam(req);
	
				_this.request(req, res, _this.inter.performance.getAvgincome, param, function(data){

						var info = data.data ? JSON.parse(data.data) : data.data;

						_this.outJson({
							code: '001',
							msg: data.msg,
                            data: info,
							res: res
						});

				});

			});

			//店面人流情况
			app.all(preRoute + 'getPeopleCount', function(req, res){

				var param = _this.getParam(req);
	
				_this.request(req, res, _this.inter.performance.getPeopleCount, param, function(data){

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

	var oPerformance = new Performance();

};





