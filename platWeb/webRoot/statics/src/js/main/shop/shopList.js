/**
 *description:店铺列表
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var oTip = require('../../widget/dom/tip');
	var ajaxForm = require('../../widget/form/ajaxForm');
	var oTplList = require('../../tpl/shop/shopList');
	var fenye = require('../../widget/dom/fenye');
	var Select = require('../../widget/dom/select');
	var oDownloadTpl = require('../../tpl/shop/downloadCode');
	var oShopEditTpl = require('../../tpl/shop/shopEdit');

	var ShopList = R.Class.create(R.util, {

		initialize: function() {
			
			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.areaRelated();
			this.findShop();

			this.codeUrl = null;
			this.oWrap = $('[list-wrap]');
			this.initDialogBox();
			this.searchShop();
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

			//店铺搜索
			$(document).on('click', '[search-shop-btn]', function(){
				_this.searchShop();
			});

			//新增店铺
			$(document).on('click', '[shop-add]', function() {
				_this.oShopEditBox.show();
				_this.addShop();
				_this.placeHolder();
			});

			//店铺信息编辑
			$(document).on('click', '[shop-edit]', function() {
				_this.oShopEditBox.show();
				_this.getEditData($(this));
			});

			//店铺信息保存
			$(document).on('click', '[shop-confirm]', function() {
				if(!$(this).attr('disabled')){
					_this.saveShopInfo();
				}
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
		addShop: function() {

			this.province1 = $('[province1]');
			this.city1 = $('[city1]');
			this.area1 = $('[area1]');
			
			var editBox = $('[edit-Box]');

			editBox.find('[shopname]').val('');
			editBox.find('[shopcode]').val('');
			editBox.find('[shopname]').attr('pkShop', '');
			editBox.find('[addr]').val('');
			editBox.find('[businessour]').val('');
			editBox.find('[fixphone]').val('');
			
			editBox.find('[areaname]').find('[code]').remove();
			this.areaRelated1(false);
		},
		getEditData: function(oThis) {

			this.province1 = $('[province1]');
			this.city1 = $('[city1]');
			this.area1 = $('[area1]');
			
			var editBox = $('[edit-Box]');

			editBox.find('[shopname]').val(oThis.attr('shopname'));
			editBox.find('[shopcode]').val(oThis.attr('shopcode'));
			editBox.find('[shopname]').attr('pkShop', oThis.attr('pkShop'));
			editBox.find('[addr]').val(oThis.attr('addr'));
			editBox.find('[businessour]').val(oThis.attr('businessour'));
			editBox.find('[fixphone]').val(oThis.attr('fixphone'));
			editBox.find('[province1]').attr('areaname', oThis.attr('provincename'));
			editBox.find('[city1]').attr('areaname', oThis.attr('cityname'));
			editBox.find('[area1]').attr('areaname', oThis.attr('countyname'));

			editBox.find('[areaname]').find('[code]').remove();
			this.areaRelated1(true);
			this.showAreaData();

		},
		saveShopInfo: function() {

			var _this = this;
			var oEditBox = $('.edit-box');
			var dataRow = $('[shop-info]');

			$('[shop-confirm]').attr('disabled', 'disabled');

			if(oEditBox.find('[shopname]').attr('pkShop')){
				this.reqUrl = R.interfaces.shop.editShop;
			}else{
				this.reqUrl = R.interfaces.shop.saveShop;
			}
			
			this.reqParam = {
				shopname: oEditBox.find('[shopname]').val(),
				shopcode: oEditBox.find('[shopcode]').val(),
				fixphone: oEditBox.find('[fixphone]').val(),
				businessour: oEditBox.find('[businessour]').val(),
				addr: oEditBox.find('[addr]').val(),
				province: _this.province1.children().eq(_this.province1[0].selectedIndex).attr('code'),
				city: _this.city1.children().eq(_this.city1[0].selectedIndex).attr('code'),
				county: _this.area1.children().eq(_this.area1[0].selectedIndex).attr('code'),
				provincename: _this.province1.val(),
				cityname: _this.city1.val(),
				countyname: _this.area1.val()
			};

			if(oEditBox.find('[shopname]').attr('pkShop')){
				this.reqParam.pkShop = oEditBox.find('[shopname]').attr('pkShop');
			}

			if(this.checkShopInfo(this.reqParam)) {
				this.req(function(data) {
					oTip.say(data.msg);
					_this.searchShop();
					_this.oShopEditBox.close();
					$('[shop-confirm]').removeAttr('disabled');
				},function(data) {
					oTip.say(data.msg);
					$('[shop-confirm]').removeAttr('disabled');
				});
			}else{
				$('[shop-confirm]').removeAttr('disabled');
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
		areaRelated1: function(edit) {

			//各店铺区域联动
			var _this = this;

			this.oProvince1 = new Select({
				ele: this.province1,
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

							_this.oCity1.clear();
							_this.oCity1.render(data);

						});

					} else {

						_this.oCity1.clear();
						_this.oArea1.clear();

					}

				},
				onReady: function() {
					if(edit){
						_this.areaCb1 && _this.areaCb1();
					}	
				}	
			});

			this.oCity1 = new Select({
				ele: this.city1,
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

							_this.oArea1.clear();
							_this.oArea1.render(data);

						});

					} else {

						_this.oArea1.clear();

					}

				}
			});

			this.oArea1 = new Select({
				ele: this.area1,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}'
			});
		},
		showAreaData: function() {

			var provinceName = this.province1.attr('areaname');
			var cityName = this.city1.attr('areaname');
			var areaName = this.area1.attr('areaname');
			//编辑时显示联动数据
			var _this = this;

			this.areaCb1 = function(){

				_this.oProvince1.match('value', provinceName);

				var nowCode = _this.oProvince1.getNowOption().attr('code');

				_this.reqUrl = R.interfaces.global.getArea;
				_this.reqParam = {
					pkFather: nowCode
				};
				_this.req(function(data){

					_this.oCity1.clear();
					_this.oCity1.render(data);
					_this.oCity1.match('value', cityName);

					var cityCode = _this.oCity1.getNowOption().attr('code');

					_this.reqUrl = R.interfaces.global.getArea;
					_this.reqParam = {
						pkFather: cityCode
					};					
					_this.req(function(data){

						_this.oArea1.clear();
						_this.oArea1.render(data);
						_this.oArea1.match('value', areaName);

					});

				});

			};

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
		searchShop: function() {
			var param = {
				pagesize: 10,
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey')
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.shop.shopList, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.shop.shopList, oTplList);
			}

		},
		checkShopInfo: function(param) {

			//店铺名称
			if($.trim(param.shopname) == ''){
				oTip.say('请填写店铺名称');
				return false;
			}

			if($.trim(param.shopname).length > 50){
				oTip.say('请填写50字以内的店铺名称');
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

			if($.trim(param.addr).length > 100){
				oTip.say('请填写100字以内的具体地址');
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
			window.location = R.uri.downloadDomain + 'wxqrcode/getdownload.php?width='+w+'&height='+h+'&qrcodeUrl='+this.codeUrl+'';
		}

	});

	var oShopList = new ShopList();

});
