/**
 *description:view-control-店铺二维码
 *author:wangwc
 *date:2015/4/20
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/shop/baseController')(app);

	var ShopQrcode = Class.create(baseController, {
		
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

	var oShopQrcode = new ShopQrcode();
	
};