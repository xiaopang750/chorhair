/**
 *description:用户完善
 *author:fanwei
 *date:2015/01/15
 */

/*
	type: 
	001: qq
	002: weibo

	openid:
*/ 
define(function(require, exports, module){
	
	var global = require("../../../driver/global");
	var UploadHeader = require('./uploadHeader');
	var Ui = require('../../../api/ui');
	var oUi = new Ui();
	var Gallery = require('../../../widget/dom/gallery');

	require('../../../lib/touch/hammer');

	var Info = R.Class.create(R.util, {
		
		initialize: function() {

			this.reqUrl = R.interfaces.user.getInfo;

			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');
			this.oEditNameInput = $('[edit-nickname]').find('[name-edit]');
			this.oBackBtn = $('[back-btn]');

			this.loadData();
		},
		loadData: function(){

			var _this = this;

			this.req(function(data){
				
				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);
				
				var tpl = require('../../../tpl/customer/user/info');
				var oWrap = $('.main-list ul');

				_this.render(oWrap, tpl, data.data, 'prepend');
				_this.events();
				_this.orgName = $('.data').html();

				_this.UploadHeader = new UploadHeader({
					subUrl: R.interfaces.upload.uploadHead,
					trigger: '[user-header]',
					oImage: $('[header-img]')
				});

				//图片缩放
				var oGallery = new Gallery({
					galleryWrap: $('[gallery-wrap]')
				});

			},function(data){

				_this.uiInfo.tip({
					content: data.msg
				});

			});

		},
		events: function(){

			var _this = this;

			//查看套餐信息
			$('.main-list').hammer().on('tap', '[user-package]', function(){

				var url = R.route['user/package'].url;
				
				window.location = url;

			});

			//修改昵称
			$('.main-list').hammer().on('tap', '[nick-name]', function(){
				_this.startEdit();
			});

			//昵称修改返回
			this.oBackBtn.on('click', function(){
				_this.endEdit(_this.orgName);
			});

			//保存昵称
			$('[save]').on('click', function(e){

				if(_this.oEditNameInput.val() == ''){

					_this.uiInfo.tip({
						content: '昵称不能为空'
					});

				}else{

					oUi.uiLoading.show();
					_this.reqUrl = R.interfaces.user.editInfo;
					_this.reqParam = {nickname: _this.oEditNameInput.val()};

					_this.req(function(data){

						//console.log(JSON.stringify(data));
						if(data.code == '001'){

							var nowName = _this.oEditNameInput.val();

							_this.endEdit(nowName);
							_this.orgName = nowName;
						}
						oUi.uiLoading.hide();

					},function(data){

						_this.uiInfo.tip({
							content: data.msg
						});
						oUi.uiLoading.hide();

					});
				}

				return false;
			});


			//放弃编辑，返回
			/*$('[uilink]').hammer().on('tap', function(){

				$('[uilink]').removeAttr('href');

				var url = R.route['user/info'].url;
				window.location = url;

			});*/

		},
		startEdit: function() {

			$('[edit-nickname]').show();
			$('.main-list ul').hide();
			this.oBackBtn.show();

		},
		endEdit: function(sValue) {

			$('[nick-name]').find('.data').html(sValue);
			this.oEditNameInput.val('');
			$('[edit-nickname]').hide();
			$('.main-list ul').show();
			this.oBackBtn.hide();
		}

	});

	var oInfo = new Info();
	
});