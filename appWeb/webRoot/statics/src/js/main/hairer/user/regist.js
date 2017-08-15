/**
 *description:注册
 *author:fanwei
 *date:2015/01/21
 */
define(function(require, exports, module){
	
	var global = require("../../../driver/global");

	var Regist = R.Class.create(R.util, {
		
		initialize: function() {
			
			this.aStep = $('[step]');
			this.nowStep = 0;

		},
		stepShow: function(num) {

			this.aStep.hide();
			this.aStep.eq(num).show();

		}

	});

	//step1
	var Step1 = R.Class.create(R.util, {
		
		initialize: function() {
			
			this.oTel = $('[telphone]');
			this.oGetCheckCode = $('[get-check-code]');	

			this.sOrgText = '获取验证码短信';
			this.nLimit = 60; //倒计时的时间,单位为s
			this.events();
		},
		events: function() {

			var _this = this;
			
			this.oGetCheckCode.on('click', function(){
				
				_this.next();

			});

		},
		next: function() {
			console.log(R);
			var sTel = this.getCode();
			var result = this.judge(sTel);
			
			if(result) {

				this.reqUrl = R.interfaces.user.sendRegistCode;
				this.reqParam = {

				};
				oRegist.nowStep ++;
				oRegist.stepShow(nowStep);

			}
		},
		getCode: function() {

			return this.oTel.val();

		},
		judge: function(number) {

			var re = /^(1[3|4|5|8][0-9]\d{8}$)/;

			if(!number) {

				alert('手机号不能为空');

				return;
			}

			if(!re.test(number)) {

				alert('手机格式不正确');

				return;
			}

			return true;

		},
		cutDown: function() {

			var timer = null;
			var count = 0;
			var _this = this;

			this.oGetCheckCode.addClass('disabled');
			clearInterval(timer);
			timer = setInterval(function(){

				count++;

				if(count > _this.nLimit) {

					clearInterval(timer);
					_this.oGetCheckCode.removeClass('disabled');
					_this.oGetCheckCode.text(_this.sOrgText);
					return;

				}

				_this.oGetCheckCode.text(count + 's后重新获取');

			}, 100);

		}

	});

	var oStep1 = new Step1();
	var oRegist = new Regist();
	
});