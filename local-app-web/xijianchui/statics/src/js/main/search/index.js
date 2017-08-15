/**
 *description:搜索首页
 *author:fanwei
 *date:2015/1/22
 */
define(function(require, exports, module) {
	
	require('../../../lib/touch/hammer');

	var global = require("../../../driver/global");
	var Falls = require('../../../widget/dom/falls');

	var SearchIndex = R.Class.create(R.util, {

		initialize: function() {
			
			this.searchInput = $('[search-input]');
			this.searchBtn = $('[search-btn]');
			this.sarchList = $('[search-list]');
			this.searchForm = $('[search-form]');
			
			this.showResult();
			this.events();
		},
		showResult: function(){

			var searchText = this.parse().search;

			searchText && this.searchInput.val(searchText);

			var oFalls = new Falls({
				wrap: $('[falls-wrap]'),
				reqUrl: R.interfaces.search.list,
				search: searchText
			});

		},
		events: function() {
			
			var _this = this;

			this.searchBtn.hammer().on('tap', function(){
				_this.search(_this.searchInput.val());
			});

			this.searchForm.submit(function(){
				_this.search(_this.searchInput.val());
				return false;
			});

		},
		search: function(searchText){

			$('[falls-wrap]').html('');

			var oFalls = new Falls({
				wrap: $('[falls-wrap]'),
				reqUrl: R.interfaces.search.list,
				search: searchText
			});

		}

	});

	var oSearchIndex = new SearchIndex();

});
