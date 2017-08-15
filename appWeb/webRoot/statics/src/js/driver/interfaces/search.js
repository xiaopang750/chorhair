/**
 *description: 搜索接口
 *author:fanwei
 *date:2015/02/04
 */

define(function(require, exports, module){	

	module.exports = {

		list: R.uri.reqPrefix + 'search/list',
		searchTag: R.uri.reqPrefix + 'search/searchTag'
	};
	
}); 