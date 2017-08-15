/*
 *description:评论回复
 *author:wangwc
 *date:2015/1/30
 */
define(function(require, exports, module){
	
	var global = require("../../driver/global");
	var Ui = require('../../api/ui');
	var oUi = new Ui();

	var Comment = R.Class.create(R.util, {

		initialize: function(opts){

			opts = opts || {};

			this.pkOrder = opts.pkOrder;
			this.index = opts.index; //评论项目索引

			this.commentBtn = $('[comment-btn]').eq(this.index);
			this.input = $('[comment-input]').eq(this.index);
			this.sendBtn = $('[comment-send]').eq(this.index);
			this.list = $('[comment-list]').eq(this.index);
			this.form = $('[comment-form]').eq(this.index);
			this.replyTo = null;
			this.replyId = null;
		
			this.events();

		},
		events: function(){

			var _this = this;
			
			//点击评论按钮获得焦点
			this.commentBtn.on('click', function(){

				_this.replyTo = null;
				_this.input.focus();
				
			});

			//发送评论
			this.sendBtn.on('click', function(){
				_this.sendMsg();
			});

			//回复评论
			this.list.on('click', '[comment-item]', function(e){

				//回复自己无效
				if($(this).find('[spitslotor-id]').attr('spitslotor-id') == R.pkUser){
					return false;
				}else{
					_this.replyTo = $(this).find('[spitslotor-id]').html();
					_this.replyId = $(this).find('[spitslotor-id]').attr('spitslotor-id');
				}
				
				_this.input.attr('placeholder','回复:'+_this.replyTo+'').focus();

			});

			//表单提交
			this.form.submit(function(){
				_this.sendMsg();
				return false;
			});

			this.input.focus(function(){
				$('.index-footer').hide();
				$('.record-list-wrap').addClass('no-bottom');
			});

			this.input.blur(function(){
				$(this).removeAttr('placeholder');
				$('.index-footer').show();
				$('.record-list-wrap').removeClass('no-bottom');
			});

		},
		sendMsg: function(){

			var _this = this;
			var msg = this.input.val();
		
			if(msg == ''){
				alert('评论内容不能为空');
			}else{

				oUi.uiLoading.show();
				this.reqUrl = R.interfaces.appoint.arg;

				//判断是否是回复
				if(this.replyTo){
					this.reqParam = {
						pkOrder: this.pkOrder,
						replyman: this.replyTo,
						replymanid: this.replyId,
						spitslotcontent: msg
					};
				}else{
					this.reqParam = {
						pkOrder: this.pkOrder,
						spitslotcontent: msg
					};
				}

				this.req(function(data){
				
					//评论成功
					if(data.code == '001'){
					
						var tpl = require('../../tpl/customer/record/new');

						_this.render(_this.list, tpl, data.data, 'prepend');
						_this.input.val('').removeAttr('placeholder').blur();

					}	
					oUi.uiLoading.hide();

				},function(data){
					alert(data.msg);
					oUi.uiLoading.hide();
				});
		
			}

		}

	});
	
	module.exports = Comment;

});
