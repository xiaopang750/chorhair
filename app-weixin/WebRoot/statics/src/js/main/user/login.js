/**
 *description:登录
 *author:wangwc
 *date:2015/04/9
 */
define(function(require, exports, module){

	require("../../driver/global");

	var Login = R.Class.create(R.util, {

 		initialize: function() {

            this.isBind();
        	this.userName = $('[user-name]');
        	this.userPhone = $('[user-phone]');
        	this.userCode = $('[user-code]');
        	this.sendBtn = $('[send-code]');
 			this.submitBtn = $('[btn-submit]');

 			this.events();

        },
        isBind: function() {
            if(this.parse().pkUser) {
                window.location = '../appoint/index.jsp';
            }
        },
        events: function() {
        	var _this = this;

        	this.sendBtn.on('click', function() {
        		_this.sendCode();	
        	});

          	this.submitBtn.on('click', function() {
          		_this.submit();
          	});
        },
        sendCode: function() {
        	var _this = this;
        	var cellphone = this.userPhone.val();

        	if(!this.checkCellphone(cellphone)){
        		alert('手机号码格式不正确');
        		return;
        	}

        	var param = {
                accountid: R.accountId, 
                openid: R.openId, 
                cellphone: cellphone, 
                usergroup: "001"
            };

        	this.req('/weixin/getcode.php', param, function(data) {
        		if(data.issuccess){
                    alert('已经向您的手机发送验证码，请查收');
        			_this.countDown();
        		}else{
        			alert(data.msg);
        		}
        	});

        },
        countDown: function() {

        	var _this = this;
        	var count = 60;

        	this.sendBtn.removeClass('active');
        	this.sendBtn.html(count+'秒');

        	var timer = setInterval(function() {
        		count--;
        		_this.sendBtn.html(count+'秒');	
        		if(count == 0){
        			_this.sendBtn.html('发送验证码').addClass('active');
        			clearInterval(timer);
        		}
        	}, 1000);

        },
        submit: function() {

        	var userphone = this.userPhone.val();
        	var usercode = this.userCode.val();

        	if(!this.checkSubmit(userphone, usercode)){
        		return;
        	}

        	var param = {
                accountid: R.accountId, 
                openid: R.openId, 
                cellphone: userphone, 
                usergroup: "001", 
                code: usercode
            };

        	this.req('/weixin/bind.php', param, function(data) {
        		if(data.issuccess){
                    alert(data.msg);
                    var userData = JSON.parse(data.data);
                    var pkUser = userData.pkUser;

                    sessionStorage.pkUser = pkUser;
                    sessionStorage.userPhone = param.cellphone;
                    window.location = '../user/index.jsp';
                }else{
                    alert(data.msg);
                }
        	});

        },
        checkCellphone: function(num) {
			var re = /^(1[3|4|5|8][0-9]\d{8}$)/;

			if(!re.test(num)) {
				return false;
			}
			return true;
		},
		checkSubmit: function(num, code) {

			if(!num){
				alert('请输入手机号');
				return false;
			}
			if(!code){
				alert('请输入验证码');
				return false;
			}

			return true;
		}
        
 	});

	var oLogin = new Login();

});