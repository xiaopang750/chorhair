/**
 *description: 套餐信息
 *author:fanwei
 *date:2015/1/23
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/customer/baseController')(app);

	var Package = Class.create(baseController, {
		
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

	var oPackage = new Package();
	
};