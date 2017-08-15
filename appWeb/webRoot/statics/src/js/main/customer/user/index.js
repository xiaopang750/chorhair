/**
 *description:首页
 *author:fanwei
 *date:2015/01/12
 */
define(function(require, exports, module){
	
	require('../../../lib/touch/hammer');

	var global = require("../../../driver/global");
	var Focus = require('../../../widget/dom/focus2');
	var Falls = require('../../../widget/dom/falls');
	var detector = require('../../../util/platform/detector');
	var oDevice = require('../../../api/device');

	var oFocus = new Focus({
		oWrap: $('[widget-role = focus-wrap]')
	});

	var Index = R.Class.create(R.util, {
		
		initialize: function() {

			//图片轮播
			oFocus.init();

			//瀑布流
			var oFalls = new Falls({
				wrap: $('[falls-wrap]'),
				reqUrl: R.interfaces.goods.all
			});

			this.searchBtn = $('[search-btn]');
			this.searchArea = $('[search-area]');
			this.searchHead = $('[search-head]');
			this.searchInput = $('[search-input]');
			this.searchList = $('[search-list]');
			this.searchForm = $('[search-form]');

			this.events();

		},
		events: function(){

			var _this = this;

			this.searchArea.hammer().on('tap',function(){
				_this.startSearch();	
			});

			this.searchList.on('click', function(){
				_this.cancelSearch();
			});

			this.searchBtn.hammer().on('tap', function(){
				_this.search(_this.searchInput.val());
			});

			this.searchForm.submit(function(){
				_this.search(_this.searchInput.val());
				return false;
			});
		},
		searchTags: function(){

			var _this = this;

			this.searchList.find('[hot-tag]').hammer().on('tap', function(){
				_this.search($(this).html());
			});

		},
		startSearch: function(){

			var _this = this;

			this.searchInput.focus();
			this.searchList.show();
			this.searchHead.addClass('search');
			this.searchBtn.html('搜索');

			this.reqUrl = R.interfaces.search.searchTag;

			this.req(function(data){
				
				var tpl = require('../../../tpl/customer/search/list');
				var oWrap = _this.searchList.find('ul');

				_this.render(oWrap, tpl, data);
				_this.searchTags();

			},function(data){
				alert(data.msg);
			});

		},
		cancelSearch: function(){
			var _this = this;

			this.searchInput.blur();
			_this.searchList.hide();

			this.searchHead.removeClass('search');
			this.searchBtn.html('我要美');

		},
		search: function(searchText){
			window.location = R.route['search/index'].url+'?search='+searchText+'';
		}
	});

	var oIndex = new Index();
	
});