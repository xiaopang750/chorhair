define(function(s){window.R=window.R||{};var o=s("../lib/ooClass/class"),e=s("./base/base_util"),n=s("../util/http/bodyParse");R.Class=o,R.util=e,n().openid&&n().accountid&&(sessionStorage.openId=n().openid,sessionStorage.accountId=n().accountid),n().pkUser&&(sessionStorage.pkUser=n().pkUser),R.accountId=n().accountid||sessionStorage.accountId,R.openId=n().openid||sessionStorage.openId,R.pkUser=n().pkUser||sessionStorage.pkUser});