/**
 *description:view-control- 消息详情页
 *author:fanwei
 *date:2015/1/21
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/hairer/baseController')(app);

	var UserView = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {
					
					_this.renderPage(req, res, page, commonData);
					
				}
			});
			
		}

	});

	var oUserView = new UserView();
	
};