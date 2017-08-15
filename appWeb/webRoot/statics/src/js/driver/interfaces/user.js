/**
 *description: 用户接口
 *author:fanwei
 *date:2015/01/09
 */
define(function(require, exports, module){	

	module.exports = {

		login: R.uri.reqPrefix + 'user/login',
		forgetPass: R.uri.reqPrefix + 'user/forgetPass',
		checkCode: R.uri.reqPrefix + 'user/checkCode',
		resetPass: R.uri.reqPrefix + 'user/resetPass',
		getInfo: R.uri.reqPrefix + 'user/getInfo',
		getPackage: R.uri.reqPrefix + 'user/getPackage',
		editInfo: R.uri.reqPrefix + 'user/editInfo'
		/*sendRegistCode: R.uri.reqPrefix + 'user/sendRegistCode',
		checkRegistCode: R.uri.reqPrefix + 'user/checkRegistCode',
		saveRegistPass: R.uri.reqPrefix + 'user/saveRegistPass'*/
	};
	
});
