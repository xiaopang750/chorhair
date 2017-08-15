/**
 *description: 订单
 *author:fanwei
 *date:2015/2/4
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);

	var Search = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			//获取订单列表
			app.all(preRoute + 'list', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.appoint.list, param, function(data){
					
					_this.ajaxCheckLogin(req, res);
					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//获取订单详情
			app.all(preRoute + 'detail', function(req, res){

				_this.ajaxCheckLogin(req, res);
				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.appoint.detail, param, function(data){
					
					var info = JSON.parse(data.data);

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//订单评论回复
			app.all(preRoute + 'arg', function(req, res){

				_this.ajaxCheckLogin(req, res);
				var param = _this.getParam(req);
				param.spitslotor = req.session.nickname;
				param.spitslotorid = req.session.uid;

				_this.save(req, res, _this.inter.appoint.arg, param, function(data){

					var info = JSON.parse(data.data);

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//订单评分
			app.all(preRoute + 'score', function(req, res){

				_this.ajaxCheckLogin(req, res);
				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.appoint.score, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//套餐头像上传
			app.all(preRoute + 'packageUpload', function(req, res){

				_this.ajaxCheckLogin(req, res);
				var param = _this.getParam(req);
			
				_this.save(req, res, _this.inter.appoint.packageUpload, param, function(data){

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

	var oSearch = new Search();

}; 