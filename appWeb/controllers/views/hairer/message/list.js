/**
 *description:view-control- 消息列表页
 *author:fanwei
 *date:2015/1/21
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/hairer/baseController')(app);

	var MessageList = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {
					
					//从web服务获取7天前的时间	
					commonData.agoTime = _this.getAgoTime(7);

					_this.renderPage(req, res, page, commonData);
					
				}
			});
			
		},
		getAgoTime: function(num) {

			var oDate = new Date();
			oDate.setDate( oDate.getDate() - num );

			var year = oDate.getFullYear();
			var month = oDate.getMonth() + 1;
			var date = oDate.getDate();

			return year + '-' + this.toDobule(month) + '-' + this.toDobule(date);

		},
		toDobule: function(num) {

			if(num < 10) {
				return '0' + num;
			} else {
				return num;
			}

		}

	});

	var oMessageList = new MessageList();
	
};