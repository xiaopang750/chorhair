/**
 *description:页面公共js
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var Class = require("../lib/ooClass/class");
	var oTip = require('../widget/dom/tip');
	var Dialog = require('../widget/dom/dialog');
	var oTplPassBox = require('../tpl/global/modifyPass');
	var ajaxForm = require('../widget/form/ajaxForm');
	var enterDo = require('../widget/dom/enterDo');

	var oMessageTpl = require('../tpl/customService/msgAll');

	var GlobalPage = Class.create(R.util, {

		initialize: function() {
			
			this.oHeader = $('[header]');

			this.initDialog();
			this.modifyPass();
			this.aModifyInput = $('[modify-pass-input]');
			this.oLeftBar = $('[left-bar]');
			this.msgContent = $('[msg-content]');
			this.quickSearchUser = $('[quick-search-user]');
			this.searchResult = $('[search-result]');
			this.cancelSearch = $('[cancel-search]');
			this.userListArr = [];
				
			this.oMsgAllWrap = $('[msg-all-wrap]');
			this.actionBox = $('[select-action]');
			this.quickSendBtn = this.actionBox.find('[quick-send-btn]');
			this.quickOrderBtn = this.actionBox.find('[quick-order-btn]');
			this.firstGetCustomer = true;
			
			this.events();
		},
		addMessageEvent: function() {

			var _this = this;

			this.oMsgAllWrap.on('click', '[group]', function(e){

				if( $(e.target).attr('cancelbubble') == 'true' ) {
					return;
				}

				var oThis = $(this);
				var aOtherGroup = oThis.siblings();
				var aOtherCheck = aOtherGroup.find('[msgUid]');
				aOtherCheck.removeAttr('checked');
				
				if(oThis.hasClass('active')) {
					
					oThis.removeClass('active');

				} else {
					_this.aGroup.removeClass('active');
					oThis.addClass('active');
				}

			});

		},	
		initDialog: function() {

			//修改密码弹框
			this.oPassBox = new Dialog({
				boxTpl: oTplPassBox
			});

		},	
		events: function() {
			
			var _this = this;

			$('[loginOut]').on('click', function(){

				_this.loginOut();

			});	

			$('[modifypass]').on('click', function(){

				_this.oPassBox.show();

			});

			enterDo(this.aModifyInput, function(){

				_this.oModifyForm.subMit();

			});

			this.oHeader.on('mouseenter', '[block-item]', function(){

				var oNowMenu = $(this).find('[drop-menu]');
				_this.showHeaderMenu(oNowMenu);

			});

			this.oHeader.on('mouseleave', '[block-item]', function(){

				var oNowMenu = $(this).find('[drop-menu]');
				_this.hideHeaderMenu(oNowMenu);

			});

			this.oPassBox.onClosed = function() {

				_this.oModifyForm.clear();
				_this.oModifyForm.refresh();

			};

			this.oLeftBar.on('click', '[list-main-head]', function(e){

				_this.showSubNav($(this));

			});

			this.quickSearchUser.on('keyup', function() {

				_this.cancelSearch.show();

				if($(this).val() == ''){
					_this.cancelSearch.hide();
					_this.searchResult.html('');
				}

			});

			this.cancelSearch.on('click', function() {
				
				_this.quickSearchUser.val('');
				_this.searchResult.html('');
				
				$(this).hide();

			});

			$(document).on('click', '[quick-search-btn]', function() {
				_this.quickSearch();
			});

			enterDo(this.quickSearchUser, function(){
				_this.quickSearch();
			});

			$(document).on('click', '[quick-customer]', function() {
				_this.showAction($(this));
				return false;
			});

			$(document).on('click', '[quick-send-btn],[quick-order-btn]', function(){
				_this.quickSearchUser.val('');
			});

			$(document).on('click', function(){
				_this.actionBox.hide();
			});

		},
		showAction: function(oThis) {

			this.actionBox.show();
			this.actionBox.css({
				left: oThis.offset().left + 8,
				top: oThis.offset().top + 26
			});

			var sendUrl = R.route['customService/index'].url+'?uid='+oThis.attr('uid')+'&username='+oThis.text()+'&cellphone='+oThis.attr('cellphone')+'';
			var orderUrl = R.route['user/add'].url+'?uid='+oThis.attr('uid')+'';

			this.quickSendBtn.attr('href', sendUrl);
			this.quickOrderBtn.attr('href', orderUrl);

		},
		getCustomer: function(keyWords) {
			var _this = this;
			var resultArr = [];

			this.oMsgAllWrap.hide();
			this.searchResult.html('');

			this.reqUrl = R.interfaces.customService.getCustomer;

			this.req(function(data){
				_this.userListArr = data.data.list;

				for(var i=0;i<_this.userListArr.length;i++){
					if(_this.userListArr[i].cellphone.indexOf(keyWords) != -1 || _this.userListArr[i].customername.indexOf(keyWords) != -1) {
						var oLi = $('<li class="customer" quick-customer uid="'+_this.userListArr[i].pkCustomer+'" cellphone="'+_this.userListArr[i].cellphone+'">'+_this.userListArr[i].customername+'</li>');
						resultArr.push(oLi);
					}
				}
				for(var i=0;i<resultArr.length;i++){
					_this.searchResult.append(resultArr[i]);
				}
			});
			
		},
		quickSearch: function() {
			var keyWords = this.quickSearchUser.val();

			if(keyWords == ''){
				return;
			}
				
			this.getCustomer(keyWords);
		},
		showSubNav: function(oThis) {

			var oMainList = oThis.parents('[list-main]');
			var isHasDown = oMainList.hasClass('down');
			var oSub = oMainList.find('[list-sub]');

			if(isHasDown) {
				oMainList.removeClass('down');
			} else {
				oMainList.addClass('down');
			}

		},
		showHeaderMenu: function(oMenu) {
			oMenu.show();
		},
		hideHeaderMenu: function(oMenu) {
			oMenu.hide();
		},
		loginOut: function() {

			this.reqUrl = R.interfaces.user.loginOut;
			this.req(function(data){

				window.location = data.data.url;

			});

		},
		modifyPass: function() {
			
			var _this = this;
			var oNew = $('[name = newpassword]');

			this.oModifyForm = new ajaxForm({

				subUrl: R.interfaces.global.modifyPass,
				btnName: 'modify-pass-btn',
				boundName: 'modify-pass-wrap',
				otherCheck:{

					reNewPassWord:[

						function(ele){

							if ( !ele.val() ) {

								return false;

							} else {

								return true;	
							}
							
						},
						function(ele){

							if ( ele.val() != oNew.val() ) {

								return false;

							} else {


								return true;
							}

						}
					]
				},
				sucDo: function(data, oBtn, param) {

					oTip.say(data.msg);
					_this.oModifyForm.clear();
					_this.oPassBox.close();
					setTimeout(function(){
						_this.loginOut();
					},1000);
				},
				failDo: function(data) {

					oTip.say(data.msg);

				}

			});

			this.oModifyForm.upload();

		}

	});

	var oGlobalPage = new GlobalPage();

});
