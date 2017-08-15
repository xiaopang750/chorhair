/**
 *description:附加项目管理
 *author:fanwei
 *date:2015/3/24
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var LeftBar = require('../../sub/system/leftBar');
	var RightBar = require('../../sub/system/rightTable');
	var oTplBar = require('../../tpl/system/addBar');
	var oTplSub = require('../../tpl/system/addBarSub');
	var oTplContent = require('../../tpl/system/addRight');
	var oTplList = require('../../tpl/system/addRightlist');
	var Select = require('../../widget/dom/select');

	var FileManage = R.Class.create(R.util, {

		initialize: function() {
			
			this.oBarWrap = $('[list-bar]');
			this.oGroupAddBtn = $('[group-add]');
			this.oTableWrap = $('[right-content-wrap]');

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.areaRelated();
			this.findShop();

			this.events();
			
			var _this = this;

			var oLeftBar = new LeftBar({
				oWrap: this.oBarWrap,
				barTpl: oTplBar,
				barSubTpl: oTplSub,
				oGroupAdd: this.oGroupAddBtn,
				lookGroupUrl: R.interfaces.system.addLookGroup,
				lookSubUrl: R.interfaces.system.addFindSub,
				addGroupUrl: R.interfaces.system.addAddGroup,
				editGroupUrl: R.interfaces.system.addEditGroup,
				onSubAdd: function(oThis){

					oRightBar.addGroup();
					oRightBar.nowType = 'add';
					oRightBar.nowWrap = oThis.parents('[list-main]').find('[sub-wrap]');
					var oListMain = oThis.parents('[list-main]');
					var gid = oListMain.attr('gid');
					oRightBar.operateParam = {
						pkShopGroup: gid
					};

				},
				onEdit: function(oThis, pkShop){
				
					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var price = oSubList.attr('price');
					var param = {
						pkAddition: pid,
						type: 'edit',
						additionname: pname,
						pkShop: pkShop
					};

					oRightBar.getListData(param, function(data){

						var concatData = {data: 
							{
								additionname: pname,
								list: data.data,
								type: param.type
							}
						}

						this.render(this.oWrap, this.oTplContent, concatData);
					});

					var oSublist = oThis.parents('[list-sub]');
					var pid = oSublist.attr('pid');
					oRightBar.nowType = 'edit';
					oRightBar.operateParam = {
						pkAddition: pid
					};

				},
				onLook: function(oThis, pkShop) {

					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var price = oSubList.attr('price');
					var param = {
						pkAddition: pid,
						type: 'look',
						additionname: pname,
						price: price,
						pkShop: pkShop
					};

					oRightBar.getListData(param, function(data){

						var concatData = {data: 
							{
								additionname: pname,
								list: data.data,
								type: param.type
							}
						}

						this.render(this.oWrap, this.oTplContent, concatData);
					});
				}
			});	

			oLeftBar.init();



			var oRightBar = new RightBar({
				oWrap: this.oTableWrap,
				oTplContent: oTplContent,
				oTplList: oTplList,
				awardsName: 'awards',
				sListWrapName: '[data-list]',
				getListUrl: R.interfaces.system.addFindDetail,
				disableUrl: R.interfaces.system.addDiable,
				addUrl: R.interfaces.system.addAddList, 
				editUrl: R.interfaces.system.addEditList,
				onAfterAdd: function(data) {

					var result = {
						data: [data]
					}
					_this.render(this.nowWrap, oTplSub, result, 'append');
				}
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

					$('[list-bar]').html('');
					$('[right-content-wrap]').html('');

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

					$('[list-bar]').html('');
					$('[right-content-wrap]').html('');

				}
			});

			this.oArea = new Select({
				ele: this.area,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}',
				onChange: function() {
					$('[list-bar]').html('');
					$('[right-content-wrap]').html('');
				}
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

	var oFileManage = new FileManage();

});
