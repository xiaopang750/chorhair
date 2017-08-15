/**
 *description:view-control- 搜索首页
 *author:fanwei
 *date:2015/1/22
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);

	var SearchIndex = Class.create(baseController, {
		
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

	var oSearchIndex = new SearchIndex();
	
};