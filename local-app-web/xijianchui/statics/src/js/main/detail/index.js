/**
 *description:详情页模板
 *author:fanwei
 *date:2015/1/22
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Focus = require('../../../widget/dom/focus2');
	var oFocus = new Focus({
		oWrap: $('[widget-role = focus-wrap]')
	});

	var Comment = require('../../../widget/dom/comment');

	var Detail = R.Class.create(R.util, {

		initialize: function() {

			//图片轮播
			oFocus.init();

			//加载评论
			var oComment = new Comment({
				index: 0
			});
	
		},
		events: function() {
			
			
		}

	});

	var oDetail = new Detail();

});
