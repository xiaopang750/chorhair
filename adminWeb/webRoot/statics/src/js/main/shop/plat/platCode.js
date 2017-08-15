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
	var oTplList = require('../../../tpl/shop/plat/platCode');
	var fenye = require('../../../widget/dom/fenye');
	var Select = require('../../../widget/dom/select');
	var oPublishTpl = require('../../../tpl/shop/plat/publishCode');
	var oDownloadTpl = require('../../../tpl/shop/plat/downloadCode');


	var platCode = R.Class.create(R.util, {

		initialize: function() {

			this.defaultParam = {
				pagesize: 10
			};

			this.codeUrl = null;
			this.oWrap = $('[list-wrap]');
			this.initPublishBox();
			this.areaRelated();
			this.showPage();

			this.findShop();
			this.events();
		},
		showPage: function() {

			var reqUrl;

			if(R.userType == 2){
				reqUrl = R.interfaces.plat.platCode;
			}

			this.oPage = new fenye(reqUrl, oTplList, this.defaultParam);

		},
		initPublishBox: function() {

			var _this = this;

			//发放二维码弹框
			this.oPublishBox = new Dialog({
				boxTpl: oPublishTpl,
				onClosed: function(){
					_this.resetPublishBox();
				}
			});

			//下载二维码弹窗
			this.oDownloadBox = new Dialog({
				boxTpl: oDownloadTpl
			});

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');
		},	
		events: function() {
				
			var _this = this;

			$(document).on('click', '[add-code]', function() {
				_this.oPublishBox.show();
			});

			$(document).on('change', '[name=code-type]', function() {
				if($(this).attr('code-type') == 'shop'){
					$('.select-area').show();
				}else{
					$('.select-area').hide();
				}
				$('.field').show();
				$('.generate').show();
			});

			$(document).on('change', '[area-wrap]', function() {
				_this.areaParam = {
					province: _this.province.children().eq(_this.province[0].selectedIndex).attr('code'),
					city: _this.city.children().eq(_this.city[0].selectedIndex).attr('code'),
					county: _this.area.children().eq(_this.area[0].selectedIndex).attr('code'),
				};		
				_this.findShop(_this.areaParam);
			});

			$(document).on('click', '[sc=publish-confirm]', function() {
				_this.publishCode();
			});

			$(document).on('click', '[download-code]', function() {

				_this.codeUrl = $(this).parents('tr').find('[code-url]').attr('src');
				_this.oDownloadBox.show();
			});

			$(document).on('click', '[down-btn]', function() {

				var width = $(this).attr('width');
				var height = $(this).attr('height');
				_this.downloadCode(width, height, $(this));

			});
		 	
		},
		resetPublishBox: function() {
			$('[name=code-type]').attr('checked', false);
			$('.select-area').hide();
			$('.field').hide();
			$('.generate').hide();
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
		publishCode: function() {

			var _this = this;
			var codeType;

			this.reqUrl = R.interfaces.plat.publishCode;

			if($('[code-type=shop]')[0].checked){
				codeType = 1;

				this.reqParam = {
					pkKey: this.shop.children().eq(this.shop[0].selectedIndex).attr('pkKey'),
					codeobject: codeType,
					userDefined: $('[user-defined]').find('input').val()
				};

			}else if($('[code-type=plat]')[0].checked){
				codeType = 4;

				this.reqParam = {
					codeobject: codeType,
					userDefined: $('[user-defined]').find('input').val()
				};

			}

			this.req(function(data) {
				oTip.say(data.msg);
				_this.oPublishBox.close();
			},function(data){
				oTip.say(data.msg);
			});
		},
		downloadCode: function(w, h) {
			window.location = R.uri.downloadDomain + 'wxqrcode/getdownload.php?width='+w+'&height='+h+'&qrcodeUrl='+this.codeUrl+'';
		}

	});

	var oplatCode = new platCode();

});
