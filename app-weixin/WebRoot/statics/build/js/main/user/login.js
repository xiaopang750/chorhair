define(function(e){e("../../driver/global");{var n=R.Class.create(R.util,{initialize:function(){this.isBind(),this.userName=$("[user-name]"),this.userPhone=$("[user-phone]"),this.userCode=$("[user-code]"),this.sendBtn=$("[send-code]"),this.submitBtn=$("[btn-submit]"),this.events()},isBind:function(){this.parse().pkUser&&(window.location="../appoint/index.jsp")},events:function(){var e=this;this.sendBtn.on("click",function(){e.sendCode()}),this.submitBtn.on("click",function(){e.submit()})},sendCode:function(){var e=this,n=this.userPhone.val();if(!this.checkCellphone(n))return void alert("手机号码格式不正确");var t={accountid:R.accountId,openid:R.openId,cellphone:n,usergroup:"001"};this.req("/weixin/getcode.php",t,function(n){n.issuccess?(alert("已经向您的手机发送验证码，请查收"),e.countDown()):alert(n.msg)})},countDown:function(){var e=this,n=60;this.sendBtn.removeClass("active"),this.sendBtn.html(n+"秒");var t=setInterval(function(){n--,e.sendBtn.html(n+"秒"),0==n&&(e.sendBtn.html("发送验证码").addClass("active"),clearInterval(t))},1e3)},submit:function(){var e=this.userPhone.val(),n=this.userCode.val();if(this.checkSubmit(e,n)){var t={accountid:R.accountId,openid:R.openId,cellphone:e,usergroup:"001",code:n};this.req("/weixin/bind.php",t,function(e){if(e.issuccess){alert(e.msg);var n=JSON.parse(e.data),s=n.pkUser;sessionStorage.pkUser=s,sessionStorage.userPhone=t.cellphone,window.location="../user/index.jsp"}else alert(e.msg)})}},checkCellphone:function(e){var n=/^(1[3|4|5|8][0-9]\d{8}$)/;return n.test(e)?!0:!1},checkSubmit:function(e,n){return e?n?!0:(alert("请输入验证码"),!1):(alert("请输入手机号"),!1)}});new n}});