/**
 *description:套餐修改
 *author:fanwei
 *date:2015/3/25
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../../lib/shop/baseController')(app);

	var PackageEdit = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {

					_this.getAddtionData(req, res, function(addtion){
						
						_this.getServiceData(req, res, function(service){
						
							_this.getEditData(req, res, function(pageData){
								
								commonData.pageInfo = pageData;
								commonData.pageInfo.addtion = addtion;
								commonData.pageInfo.service = service;
								
								if(!commonData.pageInfo.awards) {
									commonData.pageInfo.awards = ['tmp'];
								}

								_this.renderPage(req, res, page, commonData);

							});

						});

					});

					
				}
			});
			
		},
		getAddtionData: function(req, res, cb) {

			//获取附加项目数据
			var _this = this;

			this.request(req, res, this.inter.system.addLookGroup, {}, function(data){

				var info = data.data ? JSON.parse(data.data) : "";

				cb && cb(info);

			});

		},
		getServiceData: function(req, res, cb) {

			//获取服务价格
			var _this = this;

			this.request(req, res, this.inter.system.fmLookGroup, {}, function(data){

				var info = data.data ? JSON.parse(data.data) : "";

				cb && cb(info);

			});

		},	
		getEditData: function(req, res, cb) {

			//获取编辑数据

			var _this = this;

			var param = this.getParam(req);

			this.request(req, res, this.inter.goods.goodsEditFind, param, function(data){

				var info = data.data ? JSON.parse(data.data) : "";

				cb && cb(info);

			});

		}

	});

	var oPackageEdit = new PackageEdit();
	
};