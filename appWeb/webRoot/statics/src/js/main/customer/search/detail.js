/**
 *description:搜索详细页
 *author:fanwei
 *date:2015/1/22
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");

	var SearchDetail = R.Class.create(R.util, {

		initialize: function() {
			
			var data = {

				searchList: [

					{
						title: '望京离子烫一年不限次1',
						img: ''+R.uri.imgPath+'lib/logo/xiaopang.jpg',
						good: 251
					},
					{
						title: '望京离子烫一年不限次2',
						img: ''+R.uri.imgPath+'lib/logo/xiaopang.jpg',
						good: 266
					},
					{
						title: '望京离子烫一年不限次3',
						img: ''+R.uri.imgPath+'lib/logo/xiaopang.jpg',
						good: 300
					}

				]

			};

			var tpl = require('../../../tpl/customer/search/detail');
			var oWrap = $('.result-list ul');

			this.render(oWrap, tpl, data);

		},
		events: function() {
			
			
		}

	});

	var oSearchDetail = new SearchDetail();

});
