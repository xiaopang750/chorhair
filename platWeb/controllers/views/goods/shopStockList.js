/**
 *description:view-control-店铺库存列表
 *author:fanwei
 *date:2015/3/11
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var ShopGoodsList = Class.create(baseController, {
		
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

	var oShopGoodsList = new ShopGoodsList();
	
};