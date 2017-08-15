/**
 *description:view-control-库存修改记录
 *author:fanwei
 *date:2015/1/25
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var StockRecord = Class.create(baseController, {
		
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

	var oStockRecord = new StockRecord();
	
};