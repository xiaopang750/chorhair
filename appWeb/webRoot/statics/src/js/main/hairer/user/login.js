/**
 *description:登录
 *author:fanwei
 *date:2015/01/21
 */
define(function(require, exports, module){

	var global = require("../../../driver/global");

	var Login = R.Class.create(R.util, {
		
		initialize: function() {

			this.oName = $('[username]');
			this.oPass = $('[password]');
			this.oLoginBtn = $('[login-btn]');
			this.oQq = $('[qq]');
			this.oWeibo = $('[weibo]');
			this.oWeixin = $('[weixin]');
			
			this.events();

		},
		events: function() {

			var _this = this;

			this.oLoginBtn.on('click', function(){
				
				_this.submit();

				return false;

			});

			/*this.oQq.on('click', function(){
				_this.qqTodo();
			});	

			this.oWeibo.on('click', function(){
				_this.weiboTodo();
			});	

			this.oWeixin.on('click', function(){
				_this.weixinTodo();
			});	*/


		},
		qqTodo: function() {
			//qq
		},
		weiboTodo: function() {
			//weibo
		},
		weixinTodo: function() {
			//weixin
		},
		submit: function() {

			var sName = this.oName.val();
			var sPass = this.oPass.val();
			var _this = this;

			var result = this.judge(sName, sPass);

			if(result) {

				this.reqUrl = R.interfaces.user.login;
				this.reqParam = {
					cellphone: sName,
					loginpassword: sPass,
					usergroup: R.nowWayId
				};
				this.req(function(data){

					window.location = data.data;

				}, function(data){

					_this.uiInfo.tip({
						content: data.msg
					});

				});

			}	

		},
		judge: function(name, pass) {

			var re = /^(1[3|4|5|8][0-9]\d{8}$)/;

			if(!name) {

				this.uiInfo.tip({
					content: '手机号不能为空'
				});

				return;

			}

			if(!pass) {

				this.uiInfo.tip({
					content: '密码不能为空'
				});

				return;

			}

			if(!re.test(name)) {

				this.uiInfo.tip({
					content: '手机号格式不正确'
				});

				return;

			}

			return true;

		}

	});

	var oLogin = new Login();
	
});