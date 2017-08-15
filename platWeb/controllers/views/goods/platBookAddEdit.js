/**
 *description:view-control-平台订货单添加编辑
 *author:fanwei
 *date:2015/3/11
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var GoodsList = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {

					var param = _this.getParam(req);
					var bid = param.bid;

					var param = {};
					param.pkProductBook = bid;

					//订货信息
					_this.reqData(req, res, param, function(data){

						// console.log(data);
						
						commonData.pageInfo = data;	
						_this.renderPage(req, res, page, commonData);

					});

				}
			});
			
		},
		reqData: function(req, res, param, cb) {

			this.request(req, res, this.inter.goods.bookData, param, function(data){

				var info = data.data ? JSON.parse(data.data) : data.data;

				cb && cb(info);

			});

		}

	});

	var oGoodsList = new GoodsList();
	
};