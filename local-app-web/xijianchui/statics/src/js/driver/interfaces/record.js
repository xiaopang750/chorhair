/**
 *description: 美丽记录
 *author:fanwei
 *date:2015/02/04
 */
define(function(require, exports, module){	

	module.exports = {

		list: R.uri.reqPrefix + 'record/list', //美丽记录列表
		upload: R.uri.reqPrefix + 'record/upload', //美丽记录上传
	};
	
});
