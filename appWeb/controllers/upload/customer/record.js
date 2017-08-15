/**
 *description: 美丽记录上传
 *author:fanwei
 *date:2015/2/6
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/customer/baseController')(app);
	var Upload = require('./upload');
	var oUpload = Upload(app);

	var RecordUpload = Class.create(baseController, {

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
								url: fileId,
								shortUrl: fileIdSmall
							},
							res: res
						});

					}

				}, res);

			});

		}	

	});

	var oRecordUpload = new RecordUpload();

}; 