/**
 *description:店铺列表
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Dialog = require('../../../widget/dom/dialog');
	var oTip = require('../../../widget/dom/tip');
	var ajaxForm = require('../../../widget/form/ajaxForm');
	var oTplList = require('../../../tpl/shop/shop/shopList');
	var fenye = require('../../../widget/dom/fenye');
	var Select = require('../../../widget/dom/select');
	var oDownloadTpl = require('../../../tpl/shop/shop/downloadCode');
	var oShopEditTpl = require('../../../tpl/shop/shop/shopEdit');

	var ShopList = R.Class.create(R.util, {

		initialize: function() {

			this.defaultParam = {
				pagesize: 10
			};

			this.codeUrl = null;
			this.oWrap = $('[list-wrap]');
			this.initDialogBox();
			this.showPage();
			this.events();
			
		},
		initDialogBox: function() {

			var _this = this;

			//店铺信息编辑弹窗
			this.oShopEditBox = new Dialog({
				boxTpl: oShopEditTpl
			});

			//下载二维码弹窗
			this.oDownloadBox = new Dialog({
				boxTpl: oDownloadTpl
			});

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');

		},	
		showPage: function() {

			var reqUrl;

			if(R.userType == 1){
				reqUrl = R.interfaces.shop.shopInfo;
			}else if(R.userType == 2){
				reqUrl = R.interfaces.shop.shopList;
			}
		
			this.oPage = new fenye(reqUrl, oTplList, this.defaultParam, '', function(data) {
				$('[shop-info]').data('shop-info', data);
			});	

		},
		events: function() {
				
			var _this = this;

			//店铺信息编辑
			$(document).on('click', '[shop-edit]', function() {
				_this.oShopEditBox.show();
				_this.getEditData();
			});

			//店铺信息保存
			$(document).on('click', '[shop-confirm]', function() {
				_this.saveShopInfo();
			});

			// 订单二维码
			$(document).on('click', '[order-code]', function() {
				_this.orderCode();
				_this.oDownloadBox.show();
			});

			$(document).on('click', '[down-btn]', function() {

				var width = $(this).attr('width');
				var height = $(this).attr('height');
				_this.downloadCode(width, height, $(this));

			});
			
		},
		getEditData: function() {

			var oEditBox = $('.edit-box');
			var dataRow = $('[shop-info]');

			oEditBox.find('[shopname]').val(dataRow.data('shop-info').shopname);
			oEditBox.find('[addr]').val(dataRow.data('shop-info').addr);
			oEditBox.find('[businessour]').val(dataRow.data('shop-info').businessour);
			oEditBox.find('[fixphone]').val(dataRow.data('shop-info').fixphone);
			oEditBox.find('[province]').attr('pkArea', dataRow.data('shop-info').province);
			oEditBox.find('[province]').attr('areaname', dataRow.data('shop-info').provincename);
			oEditBox.find('[city]').attr('pkArea', dataRow.data('shop-info').city);
			oEditBox.find('[city]').attr('areaname', dataRow.data('shop-info').cityname);
			oEditBox.find('[area]').attr('pkArea', dataRow.data('shop-info').county);
			oEditBox.find('[area]').attr('areaname', dataRow.data('shop-info').countyname);

			this.areaRelated();
			this.showAreaData();
		},
		saveShopInfo: function() {

			var _this = this;

			this.reqUrl = R.interfaces.shop.editShop;

			var oEditBox = $('.edit-box');
			var dataRow = $('[shop-info]');

			this.reqParam = {
				pkShop: dataRow.data('shop-info').pkShop,
				shopname: oEditBox.find('[shopname]').val(),
				fixphone: oEditBox.find('[fixphone]').val(),
				businessour: oEditBox.find('[businessour]').val(),
				addr: oEditBox.find('[addr]').val(),
				province: _this.province.children().eq(_this.province[0].selectedIndex).attr('code'),
				city: _this.city.children().eq(_this.city[0].selectedIndex).attr('code'),
				county: _this.area.children().eq(_this.area[0].selectedIndex).attr('code'),
				provincename: _this.province.val(),
				cityname: _this.city.val(),
				countyname: _this.area.val()
			};

			if(this.checkShopInfo(this.reqParam)) {
				this.req(function(data) {
					oTip.say(data.msg);
					_this.showPage();
					_this.oShopEditBox.close();
				},function(data) {
					oTip.say(data.msg);
				});
			}

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
		showAreaData: function() {

			var provinceName = this.province.attr('areaname');
			var cityName = this.city.attr('areaname');
			var areaName = this.area.attr('areaname');
			//编辑时显示联动数据
			var _this = this;

			this.areaCb = function(){

				_this.oProvince.match('value', provinceName);

				var nowCode = _this.oProvince.getNowOption().attr('code');

				_this.reqUrl = R.interfaces.global.getArea;
				_this.reqParam = {
					pkFather: nowCode
				};
				_this.req(function(data){

					_this.oCity.clear();
					_this.oCity.render(data);
					_this.oCity.match('value', cityName);

					var cityCode = _this.oCity.getNowOption().attr('code');

					_this.reqUrl = R.interfaces.global.getArea;
					_this.reqParam = {
						pkFather: cityCode
					};					
					_this.req(function(data){

						_this.oArea.clear();
						_this.oArea.render(data);
						_this.oArea.match('value', areaName);

					});

				});

			};

		},
		checkShopInfo: function(param) {

			//店铺名称
			if($.trim(param.shopname) == ''){
				oTip.say('请填写店铺名称');
				return false;
			}

			//省市区
			if(param.province == undefined || param.city == undefined || param.county == undefined){
				oTip.say('请填写完整的所在位置');
				return false;
			}

			//具体地址
			if($.trim(param.addr) == ''){
				oTip.say('请填写具体地址');
				return false;
			}

			//营业时间
			if($.trim(param.businessour) == ''){
				oTip.say('请填写营业时间');
				return false;
			}

			//联系电话
			var reFixphone = /^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])d{8}$)/;
			if($.trim(param.fixphone) == ''){
				oTip.say('请填写联系电话');
				return false;
			}else if(!reFixphone.test(param.fixphone)){
				oTip.say('联系电话格式不正确');
				return false;
			}

			return true;

		},		
		orderCode: function() {

			var _this = this;

			this.reqUrl = R.interfaces.plat.publishCode;

			this.reqParam = {
				codeobject: 5
			};

			this.req(function(data) {
				_this.codeUrl = data.data.qrcodeurl;
			},function(data) {
				oTip.say(data.msg);
			});
		},
		downloadCode: function(w, h) {
			if(this.codeUrl){
				window.location = R.uri.downloadDomain + 'wxqrcode/getdownload.php?width='+w+'&height='+h+'&qrcodeUrl='+this.codeUrl+'';
			}else{
				oTip.say('二维码生成中');
			}
		}

	});

	var oShopList = new ShopList();

});
