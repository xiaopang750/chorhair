/**
 *description:推荐下载
 *author:fanwei
 *date:2015/1/23
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var jqcode = require('../../../widget/qcode/jqcode');
	var qcode = require('../../../widget/qcode/qcode');

	var DownLoad = R.Class.create(R.util, {

		initialize: function() {
			
			this.oCodeWrap = $('[qcode-wrap]');
			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');
			this.uid = this.oCodeWrap.attr('fairerid');
			this.showCode();
			this.showContent(this.pageLoading, this.loadingWrap);
		},
		showCode: function() {
			
			this.oCodeWrap.qrcode({
				render: 'canvas',
				text: window.location.host + '/main/hairer/user/download?uid=' + this.uid,
				width: 200,
				height: 200
			});



		}

	});

	var oDownLoad = new DownLoad();

});
