/**
 *description:库存修改记录
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/goods/stockRecord');

	var Record = R.Class.create(R.util, {

		initialize: function() {
			
			this.defaultParam = {
				pagesize: 10
			};

			this.showPage();
			
		},
		showPage: function() {

			this.defaultParam.pkProduct = this.parse().pkProduct;
			this.oPage = new fenye(R.interfaces.goods.platStockRecord, oTplList, this.defaultParam);

		}

	});

	var oRecord = new Record();

});
