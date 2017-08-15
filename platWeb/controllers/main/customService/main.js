/**
 *description: 获取列表
 *author:fanwei
 *date:2015/2/8
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

			//消息人
			app.all(preRoute + 'msgAll', function(req, res){

				var param = {};
				var sessionInfo = _this.readSession(req);
				param.pkShop = sessionInfo.pkShop;

				_this.request(req, res, _this.inter.customService.fairer, param, function(data){

					var fairer = data.data ? JSON.parse(data.data) : '';

					_this.request(req, res, _this.inter.customService.customer, param, function(data){

						var customer = data.data ? JSON.parse(data.data) : '';
						var newFairer = _this.filter(fairer);
						var newCuctomer = _this.filter(customer);

						_this.outJson({
							code: '001',
							msg: data.msg,
							data: {
								fairer: newFairer,
								customer: newCuctomer
							},
							res: res
						});

					});

				});

			});

			//快速查找用户下单
			app.all(preRoute + 'getCustomer', function(req, res){

				var param = _this.getParam(req);
			
				_this.request(req, res, _this.inter.customService.customer, param,function(data){

					var info = data.data ? JSON.parse(data.data) : '';

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

			//发送消息
			app.all(preRoute + 'sendMsg', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.customService.sendMsg, param,function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});	

			//查询历史消息
			app.all(preRoute + 'historyMsg', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.customService.historyMsg, param,function(data){

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

			//创建分组
			app.all(preRoute + 'createGroup', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.customService.createGroup, param,function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});	

			//获取全部分组
			app.all(preRoute + 'getGroup', function(req, res){

				var param = _this.getParam(req);
			
				_this.request(req, res, _this.inter.customService.getGroup, param,function(data){

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

			//获取分组信息
			app.all(preRoute + 'getGroupData', function(req, res){

				var param = _this.getParam(req);
			
				_this.request(req, res, _this.inter.customService.getGroupData, param,function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});	

			//编辑分组
			app.all(preRoute + 'editGroup', function(req, res){

				var param = _this.getParam(req);
			
				_this.request(req, res, _this.inter.customService.editGroup, param,function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});	

			//删除分组
			app.all(preRoute + 'removeGroup', function(req, res){

				var param = _this.getParam(req);
			
				_this.request(req, res, _this.inter.customService.removeGroup, param,function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});	

		},
		filter:function(arr) {

			var newArr = [];
			var i,
				num;

			num = arr.length;
			for (i=0; i<num; i++) {

				if(arr[i].pkUser != 0) {
					newArr.push(arr[i]);
				}

			}
			return newArr;	

		}	

	});

	var oGoods = new Goods();

};





