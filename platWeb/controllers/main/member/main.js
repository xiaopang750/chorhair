/**
 *description: 平台注册登录
 *author:fanwei
 *date:2015/1/15
 */
module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../lib/baseController')(app);

	var Member = Class.create(baseController, {

		initialize: function() {
			
			this.events();

		},
		events: function() {

			//暂时未做验证
			var _this = this;

			//用户列表查询
			app.all(preRoute + 'query', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
                
				_this.request(req, res, _this.inter.member.getMemberInfo, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});
			
			//扫描下单会员
			app.all(preRoute + 'queryScan', function(req, res){

				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
                
				_this.request(req, res, _this.inter.member.queryScan, param, function(data){

					var info = data.data ? JSON.parse(data.data) : data.data;
				
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//添加新用户
			app.all(preRoute + 'add', function(req, res){

				var sessionInfo = _this.readSession(req);
				var param = _this.getParam(req);
				
				param.pkUser = sessionInfo.pkUser;
				/*var name = param.usercode;
				var pass = param.loginpassword;
				var result = _this.judge(name, pass, res);*/

				_this.request(req, res, _this.inter.member.add, param, function(data){
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: JSON.parse(data.data).pkCustomer,
						res: res
					});

				});

			});

			//获取会员编辑信息
			app.all(preRoute + 'getSinMemberInfo', function(req, res){

				var param = _this.getParam(req);
				
				_this.request(req, res, _this.inter.member.getSinMemberInfo, param, function(data){
						
					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//会员编辑
			app.all(preRoute + 'edit', function(req, res){

				var sessionInfo = _this.readSession(req);
				var param = _this.getParam(req);

				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.member.edit, param, function(data){
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: param.pkCustomer,
						res: res
					});

				});

			});


			//获取所有店铺套餐
			app.all(preRoute + 'packageInfo', function(req, res){

				var sessionInfo = _this.readSession(req);
				var param = _this.getParam(req);
				
				_this.request(req, res, _this.inter.member.packageInfo, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});


			//获取所有平台套餐
			app.all(preRoute + 'platPackageInfo', function(req, res){

				var sessionInfo = _this.readSession(req);
				var param = _this.getParam(req);
				
				_this.request(req, res, _this.inter.member.platPackageInfo, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//获取已有的套餐
			app.all(preRoute + 'hasedPackage', function(req, res){

				var param = _this.getParam(req);
				
				_this.request(req, res, _this.inter.member.hasedPackage, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;

					var nowUrl = _this.viewConfig['user/order'].url;
					info.data.forEach(function(data){

						data.orderUrl = nowUrl + '?pkName=' + encodeURIComponent(data.comboname) + '&pkCustomer=' + param.pkCustomer;

					});

					// console.log(info);

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info.data,
							curdate: info.curdate,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//套餐编辑
			app.all(preRoute + 'editPackage', function(req, res){
				
				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.member.editPackage, param, function(data){
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//套餐保存
			app.all(preRoute + 'savePackage', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.member.savePackage, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;
					var nowUrl = _this.viewConfig['user/order'].url;

					info.orderUrl = nowUrl + '?pkName=' + encodeURIComponent(info.comboname) + '&pkCustomer=' + param.pkCustomer;

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});


			//会员结算
			app.all(preRoute + 'sum', function(req, res){

				/*var sessionInfo = _this.readSession(req);
				var param = {};
				param.pkShop = sessionInfo.pkShop;*/
				var param = _this.getParam(req);
				
				_this.request(req, res, _this.inter.member.sum, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});


			//获取店铺理发师价格
			app.all(preRoute + 'getPrice', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.member.getPrice, param, function(data){
					
					var info = data.data ? JSON.parse(data.data) : data.data;
				
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//下单
			app.all(preRoute + 'order', function(req, res){
				
				var sessionInfo = _this.readSession(req);
				var param = _this.getParam(req);
				
				param.pkUser = sessionInfo.pkUser;

				_this.request(req, res, _this.inter.member.order, param, function(data){

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: _this.viewConfig['user/index'].url,
						res: res
					});

				});

			});

			//首页结算单查询
			app.all(preRoute + 'payList', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				
				_this.request(req, res, _this.inter.member.getIndexInfo, param, function(data){
				
					var info = data.data ? JSON.parse(data.data) : '';
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//首页预约单查询
			app.all(preRoute + 'reserveList', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);

				_this.request(req, res, _this.inter.member.getReserveInfo, param, function(data){
				
					var info = data.data ? JSON.parse(data.data) : '';
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//单条预约单查询
			app.all(preRoute + 'getEditReserveInfo', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				
				_this.request(req, res, _this.inter.member.getEditReserveInfo, param, function(data){
				
					var info = data.data ? JSON.parse(data.data) : '';
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//编辑预约单
			app.all(preRoute + 'editPreorder', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				
				_this.request(req, res, _this.inter.member.editPreorder, param, function(data){
				
					var info = data.data ? JSON.parse(data.data) : '';
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});



			//首页预约单取消
			app.all(preRoute + 'cancelReserve', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);

				_this.request(req, res, _this.inter.member.cancelReserve, param, function(data){
				
					var info = data.data ? JSON.parse(data.data) : '';
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//首页预约单生成订单
			app.all(preRoute + 'createOrder', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
				
				_this.request(req, res, _this.inter.member.createOrder, param, function(data){
				
					var info = data.data ? JSON.parse(data.data) : '';
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: {
							list: info,
							total: data.totalcount
						},
						res: res
					});

				});

			});

			//给用户发送密码
			app.all(preRoute + 'registApp', function(req, res){
				
				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.member.registApp, param, function(data){
					
					_this.outJson({
						code: '001',
						msg: data.msg,
						data: '',
						res: res
					});

				});

			});

			//快速下单
			app.all(preRoute + 'fastOrder', function(req, res){
				
				var param = _this.getParam(req);
				var sessionInfo = _this.readSession(req);
                
				_this.request(req, res, _this.inter.member.fastOrder, param, function(data){

					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//根据价格查询提成
			app.all(preRoute + 'getTicheng', function(req, res){
				
				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.member.getTicheng, param, function(data){

					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//获取附加项目组
			app.all(preRoute + 'getOther', function(req, res){
				
				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.member.getOther, param, function(data){

					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

			//根据套餐查询附加项目组
			app.all(preRoute + 'packageGetTicheng', function(req, res){
				
				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.member.packageGetTicheng, param, function(data){

					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});
			
			//根据套餐查询附优惠券
			app.all(preRoute + 'packageGetSale', function(req, res){
				
				var param = _this.getParam(req);

				_this.request(req, res, _this.inter.member.packageGetSale, param, function(data){

					var info = data.data ? JSON.parse(data.data) : '';

					_this.outJson({
						code: '001',
						msg: data.msg,
						data: info,
						res: res
					});

				});

			});

		}

	});

	var oMember = new Member();

};





