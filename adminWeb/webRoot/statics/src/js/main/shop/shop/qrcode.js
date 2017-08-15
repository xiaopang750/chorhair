/**
 *description:店铺列表
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Dialog = require('../../../widget/dom/dialog');
	var oTip = require('../../../widget/dom/tip');
	var oTplList = require('../../../tpl/shop/shop/qrcode');
	var ajaxForm = require('../../../widget/form/ajaxForm');
	var fenye = require('../../../widget/dom/fenye');
	var oPublishTpl = require('../../../tpl/shop/shop/publishCode');
	var oDownloadTpl = require('../../../tpl/shop/shop/downloadCode');

	var platCode = R.Class.create(R.util, {

		initialize: function() {

			this.defaultParam = {
				pagesize: 10
			};

			this.codeUrl = null;
			this.oWrap = $('[list-wrap]');
			this.initPublishBox();
			this.showPage();
			this.events();
		},
		showPage: function() {

			var reqUrl;

			if(R.userType == 2){
				reqUrl = R.interfaces.plat.platCode;
			}else if(R.userType == 1){
				reqUrl = R.interfaces.shop.qrCode;
			}

			this.oPage = new fenye(reqUrl, oTplList, this.defaultParam);

		},
		initPublishBox: function() {

			var _this = this;

			//发放二维码弹框
			this.oPublishBox = new Dialog({
				boxTpl: oPublishTpl,
				onClosed: function(){
					$('[user-defined]').find('input').val('');
				}
			});

			//下载二维码弹窗
			this.oDownloadBox = new Dialog({
				boxTpl: oDownloadTpl
			});

		},	
		events: function() {
				
			var _this = this;

			$(document).on('click', '[add-code]', function() {
				_this.oPublishBox.show();
				_this.placeHolder();
			});

			$(document).on('click', '[sc=publish-confirm]', function() {
				if(!$(this).attr('disabled')){
					_this.publishCode($(this));
				}
			});

			$(document).on('click', '[download-code]', function() {

				_this.codeUrl = $(this).parents('tr').find('[code-url]').attr('src');
				_this.oDownloadBox.show();
			});

			$(document).on('click', '[down-btn]', function() {

				var width = $(this).attr('width');
				var height = $(this).attr('height');
				_this.downloadCode(width, height, $(this));

			});
		 	
		},
		publishCode: function(oBtn) {

			var _this = this;
			var codeType;

			this.reqUrl = R.interfaces.plat.publishCode;

			oBtn.attr('disabled', 'disabled');

			this.reqParam = {
				codeobject: 6,
				userDefined: $('[user-defined]').find('input').val()
			};

			this.req(function(data) {
				oTip.say(data.msg);
				_this.oPublishBox.close();
				_this.showPage();
				oBtn.removeAttr('disabled');
			},function(data){
				oTip.say(data.msg);
				oBtn.removeAttr('disabled');
			});
		},
		downloadCode: function(w, h) {
			if(this.codeUrl){
				window.location = R.uri.downloadDomain + 'wxqrcode/getdownload.php?width='+w+'&height='+h+'&qrcodeUrl='+this.codeUrl+'';
			}else{
				oTip.say('二维码生成中');
			}
		}

	});

	var oplatCode = new platCode();

});
