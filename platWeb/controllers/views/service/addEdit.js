/**
 *description:view-control-服务添加编辑
 *author:fanwei
 *date:2015/1/7
 */
module.exports = function(app, route, preRoute) {

	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var AddEdit = Class.create(baseController, {
		
		initialize: function() {
			
			var _this = this;

			this.loadView({
				app: app,
				route: route,
				beforeRender: function(req, res, page, commonData) {
					
					var param = _this.getParam(req);
					var cid = param.cid;
					
					if(param.cid) {

						commonData.title = '发型师编辑';
						_this.reqData(req, res, {pkFairer: cid}, function(data){
							
							commonData.nowAction = 'edit';

							if(data.urgencypeople) {
								if(JSON.parse(data.urgencypeople).length == 2){
									data.urgencypeople = JSON.parse(data.urgencypeople);
								}else if(JSON.parse(data.urgencypeople).length == 1) {
									data.urgencypeople = JSON.parse(data.urgencypeople).concat([{}]);
								}
							}else{
								data.urgencypeople = [{},{}];
							}	
							
							commonData.pageInfo = data;
							_this.renderPage(req, res, page, commonData);
						});
						
					} else if(param.lid) {

						commonData.title = '发型师详情';
						_this.reqData(req, res, {pkFairer: param.lid}, function(data){
							
							commonData.nowAction = 'detail';
							data.urgencypeople = data.urgencypeople ? JSON.parse(data.urgencypeople) : "";
							commonData.pageInfo = data;
							_this.renderPage(req, res, page, commonData);
						});

					} else {

						commonData.title = '发型师添加';
						commonData.nowAction = 'add';
						commonData.pageInfo = {
							urgencypeople: [
								{},{}
							],
							skill: [],
							attachment: []
						};
						_this.renderPage(req, res, page, commonData);
					}
					
					
				}
			});
			
		},
		reqData: function(req, res, param, cb) {

			//获取编辑数据

			this.request(req, res, this.inter.service.getData, param, function(data){
				
				var info = data.data ? JSON.parse(data.data) : data.data;

				cb && cb(info);

			});

		},

	});

	var oAddEdit = new AddEdit();
	
};