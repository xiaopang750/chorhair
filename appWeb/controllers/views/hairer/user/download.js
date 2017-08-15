/**
 *description:view-control- 推荐下载
 *author:fanwei
 *date:2015/1/23
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/hairer/baseController')(app);

	var DownLoad = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {
					
					commonData.uid = req.session.uid;
					_this.renderPage(req, res, page, commonData);
					
				}
			});
			
		}

	});

	var oDownLoad = new DownLoad();
	
};