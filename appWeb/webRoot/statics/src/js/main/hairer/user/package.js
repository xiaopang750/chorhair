/**
 *description:套餐信息
 *author:fanwei
 *date:2015/1/23
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");

	var Package = R.Class.create(R.util, {

		initialize: function() {
			
			var _this = this;

			this.reqUrl = R.interfaces.user.getPackage;
			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');
			
			this.req(function(data){

				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);

				var tpl = require('../../../tpl/customer/user/package');
				var oWrap = $('.package-list-wrap ul');

				_this.render(oWrap, tpl, data);

			},function(data){
				_this.uiInfo.tip({
					content:data.msg
				});
			});

		},
		events: function() {
			
			
		}

	});

	var oPackage = new Package();

});
