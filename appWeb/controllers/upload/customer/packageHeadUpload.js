/**
 *description: 套餐头像上传
 *author:fanwei
 *date:2015/2/11
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/customer/baseController')(app);
	var Upload = require('./upload');
	var oUpload = Upload(app);

	var PackageHeadUpload = Class.create(baseController, {

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
						
						_this.outJson({
							code: '001',
							msg: '上传成功',
							data: {
								userphoto: fileId,
								usershortphoto: fileIdSmall,
								pkOrder: paramInfo.pkOrder
							},
							res: res
						});
					}

				}, res);

			});

		}	

	});

	var oPackageHeadUpload = new PackageHeadUpload();

}; 