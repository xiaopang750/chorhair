/**
 *description: 上传接口
 *author:fanwei
 *date:2015/01/09
 */
define(function(require, exports, module){	

	module.exports = {

		record: R.uri.uploadPrefix + 'record', //美丽记录上传
		uploadHead: R.uri.uploadPrefix + 'uploadHead', //头像上传
		packageHead: R.uri.uploadPrefix + 'packageHeadUpload' //套餐头像上传

	};
	
});