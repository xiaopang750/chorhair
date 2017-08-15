/**
 *description:我的预约首页
 *author:fanwei
 *date:2015/1/21
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");

	require('../../../lib/touch/hammer');

	var Index = R.Class.create(R.util, {

		initialize: function() {
		
			this.reqUrl = R.interfaces.appoint.list;
			this.reqParam = {orderstatus:'001'};

			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');

			this.loadData();
			this.events();

		},
		loadData: function(){

			var _this = this;

			this.wrap = $('.appoint-list ul');

			this.req(function(data){
				
				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);

				var tpl = require("../../../tpl/customer/appoint/list");

				_this.render(_this.wrap, tpl, data);

			},function(data){
				_this.uiInfo.tip({
					content:data.msg
				});
			});

		},
		events: function() {
			
			var _this = this;
			//切换服务状态
			$('.btn-group button').hammer().on('tap',function(){

				$(this).addClass('active').siblings().removeClass('active');

				if($(this).html() == '服务中'){
					_this.reqParam.orderstatus = '001';
				}else{
					_this.reqParam.orderstatus = '002';
				}

				_this.loadData();

			});
			//订单详情跳转
			$('.appoint-list').hammer().on('tap', 'dl', function(){

				var url = R.route['appoint/detail'].url+'?pkOrder='+$(this).attr('pkOrder');
				
				window.location = url;

			});
			
		}

	});

	var oIndex = new Index();

});
