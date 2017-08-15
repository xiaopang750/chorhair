/**
 *description: 头像上传
 *author:fanwei
 *date:2015/2/6
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/customer/baseController')(app);
	var Upload = require('./upload');
	var oUpload = Upload(app);

	var HeadUpload = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			var _this = this;

			app.all(route, function(req, res){

				var paramInfo = _this.getParam(req);

				oUpload.toDoUpload({
					param: paramInfo,
					suc: function(param, fileId, fileIdSmall) {

						var reqParam = {
							headurl: fileId,
							headshorturl: fileIdSmall
						};

						_this.save(req, res, _this.inter.user.editInfo, reqParam, function(data){

							_this.outJson({
								code: '001',
								msg: '上传成功',
								data: fileIdSmall,
								res: res
							});

						}, function(data){

							_this.outJson({
								code: '002',
								msg: '上传失败',
								data: '',
								res: res
							});

						});
					}

				}, res);

			});

		}	

	});

	var oHeadUpload = new HeadUpload();

}; 