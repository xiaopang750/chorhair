/**
 *description:商品套餐修改详情
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var fenye = require('../../../widget/dom/fenye');
	var oTplList = require('../../../tpl/shop/goods/modifyDetail');

	var Detail = R.Class.create(R.util, {

		initialize: function() {
			
			this.defaultParam = {
				pagesize: 10,
				pkShopCombo: this.parse().gid
			};
			this.showPage();
			
		},
		showPage: function() {

			this.oPage = new fenye(R.interfaces.goods.modifyDetail, oTplList, this.defaultParam);
			
		},
		events: function() {
			
			
		}

	});

	var oDetail = new Detail();

});
