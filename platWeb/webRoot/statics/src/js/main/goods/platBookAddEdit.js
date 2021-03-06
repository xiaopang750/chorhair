/**
 *description:平台订单添加编辑
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var fenye = require('../../widget/dom/fenye');
	var platBook = require('../../tpl/goods/platBook');
	var oTip = require('../../widget/dom/tip');
	var oDetailTpl = require('../../tpl/goods/platBookDetail');
	var oApproveTpl = require('../../tpl/goods/modifyApprove');

	var PlatBookAddEdit = R.Class.create(R.util, {

		initialize: function() {
			
			this.reqUrl = R.interfaces.goods.bookData;
			this.oWrap = $('[product-list]');
			this.amountMoney = $('[amount-money]');
			this.note = $('[book-note]');
			this.pkProductBook = this.parse().bid;
			
			this.currentApprove = null;
			this.currentReal = null;
			this.pkProductBookB = null;
			this.productprice = null;

			this.initEditBox();
			this.events();

		},
		events: function() {
				
			var _this = this;	

			//修改发货数量弹窗
			$(document).on('click', '[modify-approvenum]', function(){

				_this.currentApprove = $(this).parents('[list]').find('[data=approvenum]');
				_this.currentReal = $(this).parents('[list]').find('[data=realnum]');
				_this.pkProductBookB = $(this).parents('[list]').attr('pkProductBookB');
				_this.productprice = $(this).parents('[list]').find('[data=productprice]').html();
				var approvenum = _this.currentApprove.html();
				_this.oModifyBox.show();
				$('[modify-box]').find('[approvenum]').val(approvenum);

			});

			//校验数字
			$(document).on('keyup', '[approvenum]', function(){
				var sValue = $(this).val();
				var re = /^[0-9]*[1-9][0-9]*$/g;

				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写大于0的正整数');
					$(this).val('');
				}	
			});

			//确认发货数量
			$(document).on('click','[sc=approve-confirm]', function(){

				var num = $('[modify-box]').find('[approvenum]').val();
				_this.confirmPlatRealModify(num);

			});

			//驳回弹窗
			$(document).on('click', '[operation=reject]', function(){
				$('[reject-textarea]').val('');
				_this.oEditBox.show();
			});

			//确认驳回
			$(document).on('click', '[sc=confirm]', function(){
				var sText = $('[reject-textarea]').val();
				if(!sText){
					oTip.say('请填写驳回理由');
				}else{
					_this.confirmOperation('N', sText);
				}	
			});

			//确认通过
			$(document).on('click', '[operation=pass]', function(){
				_this.confirmOperation('Y');
			});

			//确认发货
			$(document).on('click', '[operation=send]', function(){
				_this.confirmSend();
			});

		},
		initEditBox: function() {
			//渲染弹框
			this.oEditBox = new Dialog({
				boxTpl: platBook
			});

			this.oModifyBox = new Dialog({
				boxTpl: oApproveTpl
			});
		},
		confirmSend: function() {

			var _this = this;

			this.reqUrl = R.interfaces.goods.sendGoods;
			this.reqParam = {
				pkProductBook: this.pkProductBook
			};

			this.req(function(data){
				oTip.say(data.msg);
				window.location = './platBookList';
			}, function(data){
				oTip.say(data.msg);
			});

		},
		confirmOperation: function(res, sText) {
			
			var _this = this;

			this.reqUrl = R.interfaces.goods.bookApprove;
			this.reqParam = {
				ispass: res,
				pkProductBook: this.pkProductBook
			};

			if(sText){
				this.reqParam.note = sText;
			}

			this.req(function(data){
				oTip.say(data.msg);
				_this.oEditBox.close();
				window.location = './platBookList';
			}, function(data){
				oTip.say(data.msg);
			});

		},
		confirmPlatRealModify: function(num) {

			var _this = this;

			this.reqUrl = R.interfaces.goods.bookPlatRealModify;

			var amountMoney = this.countAmount() - this.productprice * this.currentApprove.html() + this.productprice * num;

			this.reqParam = {
				pkProductBook: this.pkProductBook,
				pkProductBookB: this.pkProductBookB,
				bookmoney: amountMoney,
				approvenum: num
			};
			this.req(function(data){
				oTip.say(data.msg);
				_this.oModifyBox.close();
				_this.currentApprove.html(num);
				_this.amountMoney.html(amountMoney);
			},function(data){
				oTip.say(data.msg);
			});
		},
		countAmount: function() {
			
			var aGoods = this.oWrap.children();
			var count = 0;

			aGoods.each(function(){
				count += $(this).find('[data=approvenum]').html() * $(this).find('[data=productprice]').html();
			});

			this.amountMoney.html(count);
			return count;
		},

	});

	var oPlatBookAddEdit = new PlatBookAddEdit();

});
