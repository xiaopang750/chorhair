/**
 *description:新增套餐
 *author:wangwc
 *date:2015/6/2
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var oTip = require('../../widget/dom/tip');
	var Dialog = require('../../widget/dom/dialog');
	var goodsTpl = require('../../tpl/goods/selectGoods');
	var shopTpl = require('../../tpl/goods/selectShop');
	var oGoodsTpl = require('../../tpl/goods/bookGoods');
	var Select = require('../../widget/dom/select');

	var AddCombo = R.Class.create(R.util, {

		initialize: function() {

			this.initSelectBox();
			this.areaRelated();
			this.findShop();
			this.events();
			this.judge();

			this.selectedGoodsWrap = $('[sub-selected-goods]').find('[item-wrap]');
			this.selectedShopWrap = $('[sub-selected-shop]').find('[item-wrap]');
			this.shopListWrap = $('[shop-list-wrap]');

			this.goodsList = $('[goods-list]');
			this.goodsWrap = $('[goods-list-wrap]');
			this.selectGoodsList = $('[selected-goods-list]');

			this.selectGoodsArr = [];
			this.selectShopArr = [];
			this.shopListArr = [];
			this.oldGoods = [];
			this.delproducts = [];
			this.addproducts = [];
			this.oldShops = [];
			this.addshops = [];
			this.updateshops = [];
			this.delshops = [];
			this.goodsFirstLoad = true;
			this.shopFirstLoad = true;

			//编辑
			if(this.parse().gid){
				//展示已选择的商品
				this.showSelectGoods();
			}

		},
		judge: function() {

			//编辑
			if(this.parse().gid){
				this.getEditData();
			}

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

			//次数限制
			$(document).on('change', '[combotype]', function(){
				if($(this).val() == '3'){
					$('[combocount-wrap]').show();
				}else{
					$('[combocount-wrap]').hide();
				}
			});

			//选择店铺弹窗
			$(document).on('click', '[select-shop]', function(){
				_this.oShopBox.show();
			});

			//选择商品弹窗
			$(document).on('click', '[select-goods]', function(){
				_this.oGoodsBox.show();
			});

			//查询商品
			$(document).on('keyup', '[goods-list-input]', function(){
				var sValue = $(this).val();
				_this.searchGoods(sValue);
			});

			//选择商品
			$(document).on('click', '[goods-item]', function(){
				var oInfo = {
					pkProduct: $(this).attr('gid'),
					productname: $(this).text(),
					productunit: $(this).attr('unit'),
					productprice: $(this).attr('productprice') 
				};
				_this.selectGoods(oInfo);
			});

			//移除商品
			$(document).on('click', '[remove-goods]', function(){
				_this.removeGoods($(this));
			});

			//确认商品选择
			$(document).on('click', '[confirm-select-goods]', function(){
				_this.confirmGoods();
			});

			//店铺全选
			$(document).on('click', '[all-select] input', function(){
				var isSelect = this.checked;
				_this.allSelect(isSelect);
			});

			$(document).on('click', '[all-select] span', function(){
				var isSelect = $(this).parent().find('input')[0].checked;
				_this.allSelect(!isSelect);
			});

			//店铺选择
			$(document).on('click', '[shop-item] input', function(){
				var pkShop = $(this).parent().find('span').attr('pkShop');
				var shopname = $(this).parent().find('span').text();

				_this.selectShop(pkShop, shopname);
				_this.isAllSelect();
			});

			$(document).on('click', '[shop-item] span', function(){
				var pkShop = $(this).attr('pkShop');
				var shopname = $(this).text();

				if($(this).parent().find('input')[0].checked){
					$(this).parent().find('input')[0].checked = false;
				}else{
					$(this).parent().find('input')[0].checked = true;
				}

				_this.selectShop(pkShop, shopname);
				_this.isAllSelect();
			});

			//确认店铺选择
			$(document).on('click', '[confirm-select-shop]', function(){
				_this.confirmShop();
			});

			//添加商品提交
			$(document).on('click', '[confirm-add-combo]', function(){
				_this.subAddCombo();
			});

		},
		initSelectBox: function() {

			//选择产品弹框
			this.oGoodsBox = new Dialog({
				boxTpl: goodsTpl
			});

			//选择店铺弹框
			this.oShopBox = new Dialog({
				boxTpl: shopTpl
			});

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

		},
		getEditData: function() {

			$('[fairtype]').val($('[fairtype]').attr('fairtype'));
			$('[combotype]').val($('[combotype]').attr('combotype'));

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

				_this.showShop();
				//展示已选择的店铺
				_this.showSelectShop();
			});
		},
		showShop: function() {

			var _this = this;
			var shops = this.shop.children();

			this.shopListArr = [];
			this.shopListWrap.html('');

			shops.each(function(){
				if($(this).attr('pkKey')){
					var oShopInfo = {
						pkShop: $(this).attr('pkKey'),
						shopname: $(this).text()
					};
					_this.shopListArr.push(oShopInfo);
				}
			});

			for(var i=0;i<this.shopListArr.length;i++){
				var shopItem = $('<li shop-item>'+
							'<input class="ba-mr-5" type="checkbox"><span pkShop="'+this.shopListArr[i].pkShop+'">'+this.shopListArr[i].shopname+'</span>'+
						'</li>');

				this.shopListWrap.append(shopItem);

			}

			$('[all-select]').find('input')[0].checked = false;

		},
		allSelect: function(isSelect) {

			var allcheckbox = this.shopListWrap.find('input');
				
			this.selectShopArr = [];

			if(isSelect){
				$('[all-select]').find('input')[0].checked = true;
				for(var i=0;i<allcheckbox.length;i++){
					allcheckbox[i].checked = true;
				}

				for(var i=0;i<allcheckbox.length;i++){
					var oShopInfo = {
						pkShop: allcheckbox.eq(i).parent().find('span').attr('pkShop'),
						shopname: allcheckbox.eq(i).parent().find('span').text()
					};
					this.selectShopArr.push(oShopInfo);
				}

			}else{
				$('[all-select]').find('input')[0].checked = false;
				for(var i=0;i<allcheckbox.length;i++){
					allcheckbox[i].checked = false;
				}
			}
			
		},
		isAllSelect: function() {

			var allcheckbox = this.shopListWrap.find('input');
			var totalCount = allcheckbox.length;
			var allSelect = true;

			for(var i=0;i<allcheckbox.length;i++){
				if(!allcheckbox[i].checked){
					allSelect = false;
					break;
				}
			}

			if(allSelect){
				$('[all-select]').find('input')[0].checked = true;
			}else{
				$('[all-select]').find('input')[0].checked = false;
			}

		},
		selectShop: function(pkShop, shopname) {

			for(var i=0;i<this.selectShopArr.length;i++){
				if(this.selectShopArr[i].pkShop == pkShop){
					this.selectShopArr.splice(i, 1);
					return;
				}
			}

			var oShopInfo = {
				pkShop: pkShop,
				shopname: shopname
			};

			this.selectShopArr.push(oShopInfo);
		},
		showSelectShop: function() {

			var selectedShop = this.selectedShopWrap.find('span');
			var allcheckbox = this.shopListWrap.find('input');
			var totalCount = allcheckbox.length;
			var matchCount = 0;
			var _this = this;

			this.selectShopArr = [];

			selectedShop.each(function() {
				var pkShop = $(this).attr('pkShop');

				//第一次加载时保存已选择的店铺
				if(_this.shopFirstLoad){
					_this.oldShops.push(pkShop);
				}
				
				allcheckbox.each(function(){
					if(pkShop == $(this).parent().find('span').attr('pkShop')){
						$(this)[0].checked = true;

						var oShopInfo = {
							pkShop: pkShop,
							shopname: $(this).parent().find('span').text()
						};

						_this.selectShopArr.push(oShopInfo);

						matchCount++;
						if(matchCount == totalCount){
							$('[all-select]').find('input')[0].checked = true;
						}
					}
				});
			});

			this.shopFirstLoad = false;

		},
		showSelectGoods: function() {

			var selectedGoods = this.selectedGoodsWrap.find('span');
			var _this = this;

			this.selectGoodsList.html('');
			this.selectGoodsArr = [];

			selectedGoods.each(function() {

				var pkProduct = $(this).attr('pkProduct');

				//第一次加载时保存已选择的商品
				if(_this.goodsFirstLoad){
					_this.oldGoods.push(pkProduct);
				}

				var oSelectedGoods = $('<li>'+
					'<span class="goods-name">'+$(this).text()+'</span>'+
					'<button gid="'+$(this).attr('pkProduct')+'" class="btn btn-danger ba-fr" remove-goods><span class="fa fa-minus"></span></button>'+
				'</li>');
				_this.selectGoodsList.append(oSelectedGoods);

				var oInfo = {
					pkProduct: $(this).attr('pkProduct'),
					productname: $(this).text(),
					productunit: $(this).attr('productunit'),
					productprice: $(this).attr('productprice') 
				};
				_this.selectGoodsArr.push(oInfo);
			});

			this.goodsFirstLoad = false;

		},
		searchGoods: function(sValue) {

			var _this = this;

			if(sValue == ''){
				this.goodsList.html('');
				this.goodsWrap.hide();
				return;
			}

			this.reqUrl = R.interfaces.goods.platStockList;
			this.reqParam = {productname: sValue};
			this.req(function(data){

				_this.goodsWrap.show();
				_this.render(_this.goodsList, oGoodsTpl, data.data);

			},function(data){
				_this.goodsWrap.hide();
			});

		},
		selectGoods: function(oInfo) {

			var gid = oInfo.pkProduct;

			for(var i=0;i<this.selectGoodsArr.length;i++){
				if(this.selectGoodsArr[i].pkProduct == gid){
					oTip.say('您已添加过此商品');
					return;
				}
			}

			$('[goods-list-input]').val('');
			this.goodsList.html('');
			this.goodsWrap.hide();

			var oSelectedGoods = $('<li>'+
					'<span class="goods-name">'+oInfo.productname+'</span>'+
					'<button gid="'+oInfo.pkProduct+'" class="btn btn-danger ba-fr" remove-goods><span class="fa fa-minus"></span></button>'+
				'</li>');

			this.selectGoodsList.append(oSelectedGoods);
			this.selectGoodsArr.push(oInfo);

		},
		removeGoods: function(oThis) {
			var gid = oThis.attr('gid');

			oThis.parent().remove();

			for(var i=0;i<this.selectGoodsArr.length;i++){
				if(this.selectGoodsArr[i].pkProduct == gid){
					this.selectGoodsArr.splice(i, 1);
					break;
				}
			}
		},
		confirmGoods: function() {

			this.selectedGoodsWrap.html('');

			for(var i=0;i<this.selectGoodsArr.length;i++){
				var oSpan = $('<span pkProduct="'+this.selectGoodsArr[i].pkProduct+'" productprice="'+this.selectGoodsArr[i].productprice+'" productunit="'+this.selectGoodsArr[i].productunit+'">'+this.selectGoodsArr[i].productname+'</span>');
				
				this.selectedGoodsWrap.append(oSpan);
			}

			this.selectedGoodsWrap.show();

			if(this.selectedGoodsWrap.children().length == 0){
				this.selectedGoodsWrap.hide();
			}

			this.oGoodsBox.close();
		},
		confirmShop: function() {

			this.selectedShopWrap.html('');

			for(var i=0;i<this.selectShopArr.length;i++){
				var oSpan = $('<span pkShop="'+this.selectShopArr[i].pkShop+'">'+this.selectShopArr[i].shopname+'</span>');
				
				this.selectedShopWrap.append(oSpan);
			}

			this.selectedShopWrap.show();

			if(this.selectedShopWrap.children().length == 0){
				this.selectedShopWrap.hide();
			}

			this.oShopBox.close();
		},
		subAddCombo: function() {

			var productsArr = [];
			var shopsArr = [];

			var products = this.selectedGoodsWrap.children();
			var shops = this.selectedShopWrap.children();

			products.each(function() {
				var json = {
					pkProduct: $(this).attr('pkProduct'),
					productprice: $(this).attr('productprice'),
					productname: $(this).text(),
					productunit: $(this).attr('productunit')
				};
				productsArr.push(json);
			});

			shops.each(function() {
				var json = {
					pkShop: $(this).attr('pkShop')
				};
				shopsArr.push(json);
			});

			if(!this.parse().gid){
				this.reqUrl = R.interfaces.goods.platAddCombo;

				this.reqParam = {
					comboname: $('[comboname]').val(),
					combocode: $('[combocode]').val(),
					combomoney: $('[combomoney]').val(),
					fairtype: $('[fairtype]').val(),
					combotype: $('[combotype]').val(),
					combocount: $('[combocount]').val(),
					products: JSON.stringify(productsArr),
					shops: JSON.stringify(shopsArr),
					fitgroup: 3
				};
			}else{
				this.reqUrl = R.interfaces.goods.platEditCombo;

				//获取删除的店铺
				var shopIdArr = [];

				this.addshops = [];
				this.updateshops = [];
				this.delshops = [];

				for(var i=0;i<shopsArr.length;i++){
					shopIdArr.push(shopsArr[i].pkShop);
				}
				for(var i=0;i<this.oldShops.length;i++){
					if(shopIdArr.indexOf(this.oldShops[i]) == -1){
						var json = {
							pkShop: this.oldShops[i]
						};
						this.delshops.push(json);
					}
				}

				//获取新增的店铺
				for(var i=0;i<shopsArr.length;i++){
					if(this.oldShops.indexOf(shopsArr[i].pkShop) == -1){
						var json = {
							pkShop: shopsArr[i].pkShop
						};
						this.addshops.push(json);
					}
				}

				//获取不变的店铺
				for(var i=0;i<shopsArr.length;i++){
					if(this.oldShops.indexOf(shopsArr[i].pkShop) != -1){
						var json = {
							pkShop: shopsArr[i].pkShop
						};
						this.updateshops.push(json);
					}
				}

				//获取删除的商品
				var goodsIdArr = [];
				for(var i=0;i<productsArr.length;i++){
					goodsIdArr.push(productsArr[i].pkProduct);
				}

				for(var i=0;i<this.oldGoods.length;i++){
					if(goodsIdArr.indexOf(this.oldGoods[i]) == -1){
						var json = {
							pkProduct: this.oldGoods[i]
						};
						this.delproducts.push(json);
					}
				}

				//获取新增的商品
				for(var i=0;i<productsArr.length;i++){
					if(this.oldGoods.indexOf(productsArr[i].pkProduct) == -1){

						var productname = this.selectedGoodsWrap.find('[pkProduct='+productsArr[i].pkProduct+']').text();
						var productunit = this.selectedGoodsWrap.find('[pkProduct='+productsArr[i].pkProduct+']').attr('productunit');
						var productprice = this.selectedGoodsWrap.find('[pkProduct='+productsArr[i].pkProduct+']').attr('productprice');

						var json = {
							pkProduct: productsArr[i].pkProduct,
							productname: productname,
							productunit: productunit,
							productprice: productprice
						};
						this.addproducts.push(json);
					}
				}

				this.reqParam = {
					pkCombo: this.parse().gid,
					comboname: $('[comboname]').val(),
					combocode: $('[combocode]').val(),
					combomoney: $('[combomoney]').val(),
					fairtype: $('[fairtype]').val(),
					combotype: $('[combotype]').val(),
					combocount: $('[combocount]').val(),
					products: JSON.stringify(this.addproducts),
					delproducts: JSON.stringify(this.delproducts),
					shops: "[]",
					addshops: JSON.stringify(this.addshops),
					updateshops: JSON.stringify(this.updateshops),
					delshops: JSON.stringify(this.delshops),
					fitgroup: 3
				};
			}

			if(this.checkAddCombo(this.reqParam)){
				this.req(function(data) {
					oTip.say(data.msg);
					window.location = R.route['goods/list'].url;
				}, function(data){
					oTip.say(data.msg);
				});
			}

		},
		checkAddCombo: function(param) {

			if($.trim(param.comboname) == ''){
				oTip.say('请填写套餐名称');
				return false;
			}
			if($.trim(param.combocode) == ''){
				oTip.say('请填写套餐编码');
				return false;
			}
			if($.trim(param.combomoney) == ''){
				oTip.say('请填写套餐价格');
				return false;
			}
			if(!param.fairtype){
				oTip.say('请选择服务类型');
				return false;
			}
			if(!param.combotype){
				oTip.say('请选择次数限制');
				return false;
			}
			if(param.combotype == 3 && $.trim(param.combocount) == ''){
				oTip.say('请填写次数');
				return false;
			}
			if(!param.products.length){
				oTip.say('请选择匹配产品');
				return false;
			}
			if(!param.shops.length){
				oTip.say('请选择套餐使用店铺');
				return false;
			}

			return true;
		}

	});

	var oAddCombo = new AddCombo();

});
