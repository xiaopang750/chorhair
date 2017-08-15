/**
 *description:view-control-附加项目管理
 *author:fanwei
 *date:2015/3/23
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var Additionnal = Class.create(baseController, {
		
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

	var oAdditionnal = new Additionnal();
	
};