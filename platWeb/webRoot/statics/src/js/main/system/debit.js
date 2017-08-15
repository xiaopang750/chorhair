/**
 *description:抵用券管理
 *author:fanwei
 *date:2015/3/24
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var LeftBar = require('../../sub/system/leftBar');
	var RightBar = require('../../sub/system/rightTable');

	var oTplBar = require('../../tpl/system/dbBar');
	var oTplContent = require('../../tpl/system/dbRight');
	var oTplList = require('../../tpl/system/dbRightlist');

	var Dialog = require('../../widget/dom/dialog');
	var oTplAdaptBox = require('../../tpl/system/adaptBox');
	var template = require('../../lib/template/artTemplate');

	var oTip = require('../../widget/dom/tip');

	var Debit = R.Class.create(R.util, {

		initialize: function() {
			
			var _this = this;
			this.oBarWrap = $('[sub-wrap]');
			this.oGroupAddBtn = $('[group-add]');
			this.oTableWrap = $('[right-content-wrap]');

			this.packageStrTemp = 
			'{{each data}}' +
				'<span class="ba-mr-5">' +
					'<input package-item class="ba-mr-3" type="checkbox" id="p-{{$index}}" + pid="{{$value.pkCombo}}" selectId="{{$value.pkCustomerawardLimit}}" packname="{{$value.comboname}}" {{if $value.selectcombo == "Y"}}checked="checked"{{/if}}>' +
					'<label for="p-{{$index}}">' +
						'<span class="ba-mr-5">{{$value.combomoney}}</span>' +
						'<span>{{$value.comboname}}</span>' +
					'</label>' +
				'</span>' +
			'{{/each}}';

			this.oLeftBar = new LeftBar({
				oWrap: this.oBarWrap,
				barTpl: oTplBar,
				type: 'sin',
				barSubTpl: '',
				oGroupAdd: this.oGroupAddBtn,
				lookGroupUrl: R.interfaces.system.cusLookGroup,
				lookSubUrl: R.interfaces.system.addFindSub,
				addGroupUrl: R.interfaces.system.addAddGroup,
				editGroupUrl: R.interfaces.system.addEditGroup,
				onSubAdd: function(oThis){

					_this.oRightBar.addGroup();
					_this.oRightBar.nowType = 'add';
					var oListMain = oThis.parents('[list-main]');
					var gid = oListMain.attr('gid');
					_this.oRightBar.operateParam = {
						pkShopGroup: gid
					};

				},
				onEdit: function(oThis){
					
					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var param = {
						pkShopGroup: pid,
						type: 'edit',
						groupname: pname
					};
					_this.oRightBar.getListData(param, function(data){

						var concatData = {data: 
							{
								groupname: pname,
								list: data.data,
								type: param.type
							}
						}
						
						this.render(this.oWrap, this.oTplContent, concatData);
					});

					var oSublist = oThis.parents('[list-sub]');
					var pid = oSublist.attr('pid');
					_this.oRightBar.nowType = 'edit';
					_this.oRightBar.operateParam = {
						pkShopGroup: pid
					};

				},
				onLook: function(oThis) {

					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var param = {
						pkShopGroup: pid,
						type: 'look',
						groupname: pname
					};
					_this.oRightBar.getListData(param, function(data){

						var concatData = {data: 
							{
								groupname: pname,
								list: data.data,
								type: param.type
							}
						}

						this.render(this.oWrap, this.oTplContent, concatData);
					});
				}
			});	

			this.oLeftBar.init();

			this.oRightBar = new RightBar({
				oWrap: this.oTableWrap,
				oTplContent: oTplContent,
				oTplList: oTplList,
				awardsName: 'vouchers',
				sListWrapName: '[data-list]',
				getListUrl: R.interfaces.system.cusLookDetail,
				disableUrl: R.interfaces.system.addDiable,
				addUrl: R.interfaces.system.cusAddList, 
				editUrl: R.interfaces.system.cusEditList,
				onAfterAdd: function(data) {

					var result = {
						data: [data]
					}
					_this.render(_this.oBarWrap, oTplBar, result, 'append');
				}
			});

			this.oRightBar.nowWrap = this.oBarWrap;

			this.initBox();
			this.oPackageListWrap = $('[package-list-wrap]');
			this.packageRemoveArr = []; //套餐删除
			this.events();

		},
		initBox: function() {

			//初始化套餐弹框
			this.oDialogBox = new Dialog({
				boxTpl: oTplAdaptBox
			});

		},
		events: function() {

			var _this = this;

			this.oPackageListWrap.on('click', '[package-item]', function(){

				_this.removeItem($(this));
			});

			this.oRightBar.oWrap.on('click', '[adapt]', function(){

				_this.adapt($(this));

			});


			this.oDialogBox.onConfirm = function() {
				_this.confirmPackage();
			};

		},
		removeItem: function(oThis) {

			//已经选择过的删除
			var selectid = oThis.attr('selectid');
			var isSelect = oThis.attr('checked');
			var param;

			if(selectid && isSelect!='checked') {
				param = this.getPackageInfo(oThis);
				param.dr = 1;
				this.packageRemoveArr.push(param);
			}

		},
		confirmPackage: function() {

			//套餐选择确认
			var arrData = this.getAllPackInfo();
			var reuslt = this.checkPageInfo(arrData);
			var _this = this;

			if(reuslt) {

				arrData = arrData.concat(this.packageRemoveArr);
				this.reqUrl = R.interfaces.system.cusAdapt;
				this.reqParam = {
					pkCustomeraward: this.nowPackId,
					combos: JSON.stringify(arrData)
				};

				this.req(function(data){

					oTip.say(data.msg);
					_this.oDialogBox.close();

				}, function(data){
					oTip.say(data.msg);
				});
			}

		},
		checkPageInfo: function(arrData) {

			if(!arrData.length) {
				oTip.say('请至少选择一个套餐');
				return;
			}

			return true;

		},
		getAllPackInfo: function() {

			var allItem = $('[package-item]');
			var oItem;
			var isChecked;
			var arr = [];
			var param;
			var _this = this;

			allItem.each(function(i){

				oItem = allItem.eq(i);
				isChecked = oItem.attr('checked');

				if(isChecked == 'checked') {
					param = _this.getPackageInfo(oItem);
					arr.push(param);
				}

			});

			return arr;

		},
		getPackageInfo: function(oItem) {

			//获取单条套餐选择数据
			var packageId;
			var selectedId;
			var comboname;
			var isChecked;
			var param;

			param = {};
			packageId = oItem.attr('pid');
			selectedId = oItem.attr('selectid');
			comboname = oItem.attr('packname');
			param.pkCombo = packageId;
			param.pkCustomerawardLimit = selectedId;
			param.comboname = comboname;

			return param;

		},
		adapt: function(oThis) {

			var _this = this;

			this.oDialogBox.show();

			this.reqPackage(oThis, function(data){

				_this.renderPackageList(data);
			});

		},
		reqPackage: function(oThis, cb){

			//套餐适用
			var aid = oThis.attr('aid');
			this.nowPackId = aid;

			this.reqUrl = R.interfaces.system.lookAdapt;
			this.reqParam = {
				pkCustomeraward: aid
			};
			this.req(function(data){
				cb && cb(data);
			});
		},
		renderPackageList: function(data) {

			//渲染套餐列表
			var render = template.compile(this.packageStrTemp);
			var html = render(data);
			this.oPackageListWrap.html(html);

		}

	});

	var oDebit = new Debit();

});
