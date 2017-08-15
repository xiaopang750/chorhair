/**
 *description:操作台首页
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var oTip = require('../../widget/dom/tip');
	var enterDo = require('../../widget/dom/enterDo');
	var LayData = require('../../widget/dom/layData');
	var Dialog = require("../../widget/dom/dialog");
	var toDouble = require("../../util/string/toDouble");
	var Calendar = require('../../widget/form/calendar');
	var Select = require('../../widget/dom/select');

	var Index = R.Class.create(R.util, {

		initialize: function() {
			
			var _this = this;

			this.oPayWrap = $('[pay-wrap]');
			this.oAppointWrap = $('[appoint-time-wrap]');
			this.oOrderWrap = $('[order-time-wrap]');
			this.oPayNum = $('[pay-num]');
			this.oPayUl = this.oPayWrap.find('ul');
			this.defaultPagesize = 10;
			this.orderCurpage = 1;
			this.appointCurpage = 1;
			this.loadMore = $('[load-more]'); 
			this.paymode = $('[paymode]');

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.areaRelated();
			this.findShop();
			this.showCalendar();
			this.initDialog();
			this.reqReserveList();
			this.events();
			this.placeHolder();

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

			//按时间查询预约单
			$('[search-appoint-btn]').on('click', function() {
				_this.searchReserve();
			});

			enterDo($('[search-appoint-input]'), function(){
				_this.searchReserve();
			});

			//加载更多
			$('[load-more]').on('click', function() {
				_this.loadMoreAppoint();
			});

			//取消预约单
			this.oPayWrap.on('click', '[cancel-reserve-btn]', function() {
				_this.cancelBtn = $(this);
				_this.oConfirmCancelBox.show();
			});

			this.oConfirmCancelBox.onConfirm = function() {
				_this.cancelReserve();
			};

			//编辑预约单
			this.oPayWrap.on('click', '[edit-reserve-btn]', function() {
				window.location = R.route['user/appoint'].url + '?';
			});
			
		},
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM-dd HH:mm'
			});

		},
		initDialog: function() {

			this.oConfirmCancelBox = new Dialog({
				content: '确认取消预约单吗?'
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
		},
		reqFairerList: function() {

			//获取理发师
			var _this = this;

			this.reqUrl = R.interfaces.service.validate;
			this.req(function(data){

				_this.fairerList = data.data.list;
				_this.showHairerInfo();
				
			});

		},
		showHairerInfo: function() {
			
			var _this = this;
			var hairTpl = 
			'{{each list}}' +
				'<a class="ba-mr-10 ba-mb-10" lay-list fairername="{{$value.fairername}}" id="{{$value.pkFairer}}" href="javascript:;">'+
					'{{$value.fairername}}' +
				'</a>'+
			'{{/each}}';

			//理发师
			this.oFairer = new LayData({
				width: '300',
				ele: '[fairername]',
				data: {list: this.fairerList},
				sTpl: hairTpl,
				onClick: function(oTarget, oThis) {
					
					var fairername = oThis.attr('fairername');
					var fairerid = oThis.attr('id');

					oTarget.val(fairername);
					oTarget.attr('fairername', fairername);
					oTarget.attr('pkfairer', fairerid);

				}
			});

		},
		toadyDate: function() {

			var oDate = new Date();
			var todayBegin = oDate.getFullYear() + '-' + toDouble(oDate.getMonth()+1) + '-' + toDouble(oDate.getDate()) + ' ' + '00:00';
			var todayEnd = oDate.getFullYear() + '-' + toDouble(oDate.getMonth()+1) + '-' + toDouble(oDate.getDate()) + ' ' + '24:00';

			var json = {
				begintime: todayBegin,
				endtime: todayEnd
			};

			return json;

		},
		reqReserveList: function(opts) {

			var _this = this;
			var tpl = require("../../tpl/user/reserveList");

			opts = opts || {};
			
			this.reqUrl = R.interfaces.member.queryReserveList;

			this.reqParam = {
				begintime: opts.begintime || '',
				endtime: opts.endtime || '',
				pagesize: opts.pagesize || this.defaultPagesize,
				curpage: opts.curpage || this.appointCurpage,
				province: opts.province || '',
				city: opts.city || '',
				county: opts.county || '',
				pkShop: opts.pkShop || '',
				querylike: opts.querylike || ''
			};

			this.req(function(data) {

				var fenyeCount = Math.ceil(data.data.total/_this.defaultPagesize);

				if(_this.appointCurpage >= fenyeCount){
					_this.loadMore.hide();

					if(data.data.list == 0){
						oTip.say('暂无预约单');
					}

				}else{
					_this.loadMore.show();
				}

				for(var i=0;i<data.data.list.length;i++){
					var index = _this.getShort();
					var dd = data.data.list[i];
					_this.render(_this.oPayUl.eq(index), tpl, dd, 'append');
				}

			}, function(data){
				oTip.say(data.msg);
			});

		},
		loadMoreAppoint: function() {

			this.appointCurpage++;

			var begintime = $('[search=appoint-begintime]').val();
			var endtime = $('[search=appoint-endtime]').val();
			var province= $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code');
			var city= $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code');
			var county=$('[area]').children().eq($('[area]')[0].selectedIndex).attr('code');
			var pkShop=$('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey');
			var querylike = $('[search-appoint-input]').val();

			this.reqReserveList({
				begintime: begintime,
				endtime: endtime,
				province: province,
				city: city,
				county: county,
				pkShop: pkShop,
				querylike: querylike,
				curpage: this.appointCurpage
			});

		},
		cancelReserve: function() {

			var _this = this;
			var aid = this.cancelBtn.attr('aid');
			var reservelList = this.cancelBtn.parents('[reserve-list]');

			this.reqUrl = R.interfaces.member.cancelReserve;
			this.reqParam = {
				pkOrder: aid
			};
			this.req(function(data) {
				_this.oConfirmCancelBox.close();
				oTip.say(data.msg);
				reservelList.fadeOut();
			}, function(data) {
				_this.oConfirmCancelBox.close();
				oTip.say(data.msg);
			});

		},
		getShort: function() {

			var index = 0;
			var ih = this.oPayUl.eq(index).height();

			for(var i=1; i<this.oPayUl.length; i++){

				if(this.oPayUl.eq(i).height() < ih){
					index = i;
					ih = this.oPayUl.eq(i).height();
				}

			}
			
			return index;

		},
		searchReserve: function() {

			this.oPayUl.html('');
			this.appointCurpage = 1;

			this.reqReserveList({
				begintime: $('[search=appoint-begintime]').val(),
				endtime: $('[search=appoint-endtime]').val(),
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey'),
				querylike: $('[search-appoint-input]').val(),
				curpage: this.appointCurpage
			});

		}

	});

	var oIndex = new Index();

});
