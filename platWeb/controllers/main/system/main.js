/**
 *description: 系统
 *author:fanwei
 *date:2015/3/25
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var System = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//1.档案管理查看组
			app.all(preRoute + 'fmLookGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmLookGroup, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//2.档案管理添加组
			app.all(preRoute + 'fmAddGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmAddGroup, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//3.档案管理编辑组
			app.all(preRoute + 'fmEditGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmEditGroup, param, function(data){

					//var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//4.档案管理根据组找子集列表
			app.all(preRoute + 'fmFindSub', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmFindSub, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//5.档案管理根据组找详情子集列表
			app.all(preRoute + 'fmFindDetail', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmFindDetail, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//6.档案管理添加详情
			app.all(preRoute + 'fmAddList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmAddList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//7.档案管理编辑详情
			app.all(preRoute + 'fmEditList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmEditList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//8.档案管理禁用
			app.all(preRoute + 'fmDiable', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.fmDiable, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//9.附加项目查看组
			app.all(preRoute + 'addLookGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addLookGroup, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//10.附加项目添加组
			app.all(preRoute + 'addAddGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addAddGroup, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//11.附加项目编辑组
			app.all(preRoute + 'addEditGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addEditGroup, param, function(data){

					//var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//12.附加项目根据组找子集列表
			app.all(preRoute + 'addFindSub', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addFindSub, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//13.附加项目根据组找详情子集列表
			app.all(preRoute + 'addFindDetail', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addFindDetail, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//14.附加项目添加详情
			app.all(preRoute + 'addAddList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addAddList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//15.附加项目编辑详情
			app.all(preRoute + 'addEditList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addEditList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//16.附加项目禁用
			app.all(preRoute + 'addDiable', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.addDiable, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//17.抵用券查看组
			app.all(preRoute + 'cusLookGroup', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.cusLookGroup, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//18.抵用券根据组查看详情
			app.all(preRoute + 'cusLookDetail', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.cusLookDetail, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//19.抵用券添加详情
			app.all(preRoute + 'cusAddList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.cusAddList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//20.抵用券编辑详情
			app.all(preRoute + 'cusEditList', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.cusEditList, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//21.抵用券获取适用套餐列表
			app.all(preRoute + 'lookAdapt', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.lookAdapt, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//22.抵用券适用套餐选择
			app.all(preRoute + 'cusAdapt', function(req, res){

				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.system.cusAdapt, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;

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

	var oSystem = new System();

};





