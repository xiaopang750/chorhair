/**
 *description:我的账户
 *author:fanwei
 *date:2015/1/23
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");

	var AppointList = R.Class.create(R.util, {

		initialize: function() {
			
			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');
			this.loadData();

		},
		events: function() {
			
			
		},
		loadData: function(){

			var _this = this;

			this.reqUrl = R.interfaces.user.getInfo;
			this.req(function(data){
				
				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);
				
				var tpl = require('../../../tpl/hairer/user/account');
				var oWrap = $('[oWrap]');

				_this.render(oWrap, tpl, data.data);

			},function(data){

				_this.uiInfo.tip({
					content: data.msg
				});

			});

		}

	});

	var oAppointList = new AppointList();

});
