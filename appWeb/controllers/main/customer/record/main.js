/**
 *description: 美丽记录
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

			//美丽记录
			app.all(preRoute + 'list', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.record.list, param, function(data){
					
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

			//上传
			app.all(preRoute + 'upload', function(req, res){

				var param = _this.getParam(req);

				_this.save(req, res, _this.inter.record.upload, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				}, function(data){

					_this.outJson({
						code: '002',
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