/**
 *description:消息列表
 *author:fanwei
 *date:2015/1/21
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var oMsgList = require('../../../tpl/customer/message/list');

	var MessageList = R.Class.create(R.util, {

		initialize: function() {
			
			this.aType = $('[msg-type]');
			this.oMsgWrap = $('[msg-list-wrap]');

			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');
			this.oBackBtn = $('[back-btn]');

			this.search(this.aType.eq(0));
			this.makeReadLay();
			this.events();

		},
		events: function() {
			
			var _this = this;

			this.aType.on('click', function(){
	
				_this.search($(this));

			});

			this.oMsgWrap.on('click', '[ignore], [look]', function(){

				var pk = $(this).attr('pkMessage');
				var status = $(this).attr('tranParam');

				_this.look(status, pk, $(this));

			});

			this.oBackBtn.on('click', function(){
				_this.layHide();
			});
			
		},
		look: function(status, pk, oThis) {

			var _this = this;

			//消息查看
			this.reqUrl = R.interfaces.message.ignore;
			this.reqParam = {
				messagestatus: status,
				pkMessage: pk
			};
			this.req(function(data){

				_this.hideList(oThis);

			}, function(data){

				_this.uiInfo.tip({
					content: data.msg
				});

			});

		},
		hideList: function (oThis) {

			var oList = oThis.parents('[list]');

			if(oThis[0].hasAttribute('look')) {

				var fullMsg = oThis.attr('fullMsg');
				this.layShow();
				this.write(fullMsg);

			}

			if(this.nowType == 'messagestatus') {

				//如果是未读消息，隐藏当前消息
				oList.hide();

			} else {

				var oMsgStatus = oList.find('[msg-status]');
				var status = oMsgStatus.attr('msg-status');
				oMsgStatus.attr('msg-status', '002');
				
				if(status == '001') {
					oMsgStatus.html('(已读)');
				}				

			}

		},
		search: function(oBtn) {
			
			this.aType.removeClass('active');
			oBtn.addClass('active');

			var _this = this;
			var type = oBtn.attr('msg-type');
			var value = oBtn.attr('msg-value');

			if(this.nowType == type) {
				return;
			}
			
			this.nowType = type;

			this.reqUrl = R.interfaces.message.all;
			this.reqParam = {};
			this.reqParam[type] = value;
			this.req(function(data){
				
				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);

				_this.renderResult(data);

			});

		},
		renderResult: function(data) {

			if(this.nowType == 'messagestatus') {
				data.noShowStatus = 'yes';
			}

			if(!data.data) {
				this.oMsgWrap.html('<div class="ba-tc ba-pink ba-mt-20">暂无消息</div>');
			} else {
				this.render(this.oMsgWrap, oMsgList, data);
			}

		},
		makeReadLay: function() {

			this.oReadLay = $('<div></div>');
			var nHeaderTop = $('.header').height();
			this.oReadLay.css({
				position: 'fixed',
				left: 0,
				top: nHeaderTop,
				background:'#fff',
				width: '100%',
				height: '100%',
				padding: '10px',
				display: 'none'
			});
			$('body').append(this.oReadLay);

		},
		write: function(str) {
			this.oReadLay.html(str);
		},
		layShow: function() {
			this.oReadLay.show();
			this.oBackBtn.show();
		},
		layHide: function() {
			this.oReadLay.hide();
			this.oBackBtn.hide();
		}

	});

	var oMessageList = new MessageList();

});
