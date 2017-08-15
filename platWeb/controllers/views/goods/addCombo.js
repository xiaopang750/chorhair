/**
 *description:view-control-新增套餐
 *author:fanwei
 *date:2015/1/25
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var AddCombo = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {

					var param = _this.getParam(req);
					var gid = param.gid;

					if(gid){

						var param = {};
						param.pkCombo = gid;

						//套餐信息
						_this.reqData(req, res, param, function(data){

							commonData.pageInfo = data;
							commonData.title = "编辑套餐";
							commonData.pageInfo.nowWay = 'edit';
							_this.renderPage(req, res, page, commonData);

						});

					}else {

						commonData.title = "新增套餐";
						commonData.pageInfo = {};
						commonData.pageInfo.nowWay = 'add';
						_this.renderPage(req, res, page, commonData);

					}	

				}
			});
			
		},
		reqData: function(req, res, param, cb) {

			this.request(req, res, this.inter.goods.platGetCombo, param, function(data){

				var info = data.data ? JSON.parse(data.data) : data.data;

				cb && cb(info);

			});

		}

	});

	var oAddCombo = new AddCombo();
	
};