/**
 *description:忘记密码
 *author:fanwei
 *date:2015/02/04
 */
define(function(require, exports, module){
	
	var global = require("../../../driver/global");

	var Forget = R.Class.create(R.util, {
		
		initialize: function() {
				
			this.oGetCheckCodeBtn = $('[get-check-code]');
			this.oTel = $('[telphone]');
			this.oCutDown = $('[cutdown]');
			this.oNextBtn = $('[next-btn]');
			this.oCode = $('[code]');
			this.oStep1 = $('[step1]');
			this.oStep2 = $('[step2]');
			this.oNewPass = $('[newPass]');
			this.oReNewPass = $('[reNew]');
			this.oConfirmPass = $('[confirm-pass]');
			this.events();
		},
		events: function() {

			var _this = this;

			this.oGetCheckCodeBtn.on('click', function(){
				_this.getCheck();
				return false;
			});

			this.oNextBtn.on('click', function(){
				_this.checkCode();
				return false;
			});

			this.oConfirmPass.on('click', function(){
				_this.reset();
				return false;
			});

		},
		pass: function() {

			//通过验证
			this.oStep1.hide();
			this.oStep2.show();

		},
		org: function() {

			//还原状态
			this.oStep1.show();
			this.oStep2.hide();
			this.passPhone = '';

		},
		reset: function() {

			//密码重置
			var _this = this;
			var sNew = this.oNewPass.val();
			var sReNew = this.oReNewPass.val();
			var result = this.judgePass(sNew, sReNew);
			if(!result) return;

			this.reqUrl = R.interfaces.user.resetPass;
			this.reqParam = {
				newpassword: sNew,
				cellphone: this.passPhone
			};
			this.req(function(data){

				_this.org();
				window.location = data.data;

			}, function(data){
				_this.uiInfo.tip({
					content: data.msg
				});
			});

		},
		checkCode: function() {

			var _this = this;
			var sTel = this.oTel.val();
			var sCode = this.oCode.val();
			var reuslt = this.judge(sTel);
			var resultCode = this.judgeCode(sCode);
			if(!reuslt) return;
			if(!resultCode) return;

			this.reqUrl = R.interfaces.user.checkCode;
			this.reqParam = {
				cellphone: sTel,
				code: sCode
			};
			this.req(function(data){

				_this.pass();
				_this.passPhone = sTel;

			}, function(data){

				_this.uiInfo.tip({
					content: data.msg
				});

			});

		},
		getCheck: function() {

			var _this = this;
			var sTel = this.oTel.val();
			var reuslt = this.judge(sTel);
			if(!reuslt) return;

			this.reqUrl = R.interfaces.user.forgetPass;
			this.reqParam = {
				cellphone: sTel
			};
			this.req(function(data){
				_this.uiInfo.tip({
					content: data.msg
				});
				_this.cutDown();
			}, function(data){
				_this.uiInfo.tip({
					content: data.msg
				});
			});
		},
		judgePass: function(newPass, reNewPass) {

			var re = /\w{6,16}/;

			if(!re.test(newPass)) {
				this.uiInfo.tip({
					content: '请填写6-16位的密码'
				});
				return;
			} 

			if(newPass != reNewPass) {
				this.uiInfo.tip({
					content: '两次密码输入不一致'
				});
				return;
			}

			if(!this.passPhone) {
				this.uiInfo.tip({
					content: '非法操作'
				});
				return;
			};

			return true;

		},
		judgeCode: function(sCode) {

			var re = /\w{6}/;
			if( !re.test(sCode) ) {
				this.uiInfo.tip({
					content: '验证码格式不正确'
				});
				return;
			}

			return true;

		},
		judge: function(sTel) {

			var re = /^(1[3|4|5|8][0-9]\d{8}$)/;
			if( !re.test(sTel) ) {
				this.uiInfo.tip({
					content: '手机号格式不正确'
				});
				return;
			}

			return true;

		},
		cutDown: function() {

			this.oGetCheckCodeBtn.addClass('disabled');

			var timer = null;
			var _this = this;
			var count = 60;

			clearInterval(timer);
			timer = setInterval(function(){

				count --;
				_this.oCutDown.html('('+ count +')');

				if(count == 0) {
					clearInterval(timer);
					_this.oGetCheckCodeBtn.removeClass('disabled');
					_this.oCutDown.html('(60)');
				}

			},1000);
		}

	});

	var oForget = new Forget();
	
});