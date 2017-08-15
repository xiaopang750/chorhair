/**
 *description:业绩管理首页
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Calendar = require('../../widget/form/calendar');
	var fenye = require('../../widget/dom/fenye');
	var oTip = require("../../widget/dom/tip");
	var Select = require('../../widget/dom/select');
	var Chart = require('../../widget/chart/highcharts');

	var Index = R.Class.create(R.util, {

		initialize: function() {
			
			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.areaRelated();
			this.findShop();
			this.showCalendar();
			this.events();
		},
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM'
			});
			
		},
		events: function() {
			
			var _this = this;

			//查询店铺
			$(document).on('change', '[area-wrap]', function() {
				_this.areaParam = {
					province: _this.province.children().eq(_this.province[0].selectedIndex).attr('code'),
					city: _this.city.children().eq(_this.city[0].selectedIndex).attr('code'),
					county: _this.area.children().eq(_this.area[0].selectedIndex).attr('code'),
				};		
				_this.findShop(_this.areaParam);
			});

			//查询店面收入
			$(document).on('click', '[search-income-btn]', function() {

				var pkShop = _this.shop.children().eq(_this.shop[0].selectedIndex).attr('pkkey');
				var month = $('[calendar]').val();

				if(!pkShop){
					oTip.say('请选择店铺');
					return;
				}
				if(!month){
					oTip.say('请选择月份');
					return;
				}
				_this.getIncome(pkShop, month);
				_this.getAvgincome(pkShop, month);

			});
		
		},
		getIncome: function(pkShop, month) {

			this.reqUrl = R.interfaces.performance.getIncome;
			this.reqParam = {
				pkShop: pkShop,
				month: month
			};
			this.req(function(data){

				var dates = [];
				var summoney = [];
				var sumcombomoney = [];
				var comboonesum = [];
				var servicemoney = [];

				for(var i=0;i<data.data.length;i++){
					summoney.push(data.data[i].summoney);
					sumcombomoney.push(data.data[i].sumcombomoney);
					comboonesum.push(data.data[i].comboonesum);
					servicemoney.push(data.data[i].servicemoney);
					dates.push('第'+data.data[i].dates+'周');
				}

				$('[weekincome]').highcharts({
					chart: {
			            type: 'column',
			            spacingTop: 30
			        },
					title: {
						text: null
					},
					xAxis: {
			            categories: dates
			        },
			        yAxis: {
			            min: 0,
			            title: null
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        credits: {
			        	enabled: false
			        },
			        series: [{
			            name: '本周现金流收入',
			            data: summoney

			        }, {
			            name: '本周套餐包收入',
			            data: sumcombomoney

			        }, {
			            name: '本周单次体验收入',
			            data: comboonesum

			        }, {
			            name: '本周服务费收入',
			            data: servicemoney

			        }]
				});
			});

		},
		getAvgincome: function(pkShop, month) {
			this.reqUrl = R.interfaces.performance.getAvgincome;
			this.reqParam = {
				pkShop: pkShop,
				month: month
			};
			this.req(function(data) {
				var dates = [];
				var avgmoney = [];
				var avgcombomoney = [];
				var avgallcombomoney = [];

				for(var i=0;i<data.data.length;i++){
					avgmoney.push(data.data[i].avgmoney);
					avgcombomoney.push(data.data[i].avgcombomoney);
					avgallcombomoney.push(data.data[i].avgallcombomoney);
					dates.push('第'+data.data[i].dates+'周');
				}

				$('[weekavgincome]').highcharts({
					chart: {
			            type: 'column',
			            spacingTop: 30
			        },
					title: {
						text: null
					},
					xAxis: {
			            categories: dates
			        },
			        yAxis: {
			            min: 0,
			            title: null
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        credits: {
			        	enabled: false
			        },
			        series: [{
			            name: '本周日均收入',
			            data: avgmoney

			        }, {
			            name: '本周平均套餐包价格',
			            data: avgcombomoney

			        }, {
			            name: '累计套餐包平均价格',
			            data: avgallcombomoney

			        }]
				});
			});
		},
		areaRelated: function() {

			//区域联动
			var _this = this;

			this.oProvince = new Select({
				ele: this.province,
				url: R.interfaces.global.getArea,
				param: {belonglevel:1},
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}',
				onChange: function(oSelect, oOption, nowIndex) {

					var nowCode = oOption.attr('code');

					if(nowCode) {

						_this.reqUrl = R.interfaces.global.getArea;
						_this.reqParam = {
							pkFather: nowCode
						};
						_this.req(function(data){

							_this.oCity.clear();
							_this.oCity.render(data);

						});

					} else {

						_this.oCity.clear();
						_this.oArea.clear();

					}

				},
				onReady: function() {
					_this.areaCb && _this.areaCb();
				}	
			});

			this.oCity = new Select({
				ele: this.city,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}',
				onChange: function(oSelect, oOption, nowIndex) {
					
					var nowCode = oOption.attr('code');

					if(nowCode) {
						
						_this.reqUrl = R.interfaces.global.getArea;
						_this.reqParam = {
							pkFather: nowCode
						};
						_this.req(function(data){

							_this.oArea.clear();
							_this.oArea.render(data);

						});

					} else {

						_this.oArea.clear();

					}

				}
			});

			this.oArea = new Select({
				ele: this.area,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}'
			});

		},
		findShop: function(param) {

			var _this = this;

			_this.shop.html('<option>请选择店铺</option>');

			this.reqUrl = R.interfaces.shop.shopList;
			this.reqParam = param || {};
			this.req(function(data) {
				var shopList = data.data.list;

				for(var i=0;i<shopList.length;i++){
					var option = $('<option pkKey="'+shopList[i].pkShop+'">'+shopList[i].shopname+'</option>');
					_this.shop.append(option);
				}
			});
		}

	});

	var oIndex = new Index();

});