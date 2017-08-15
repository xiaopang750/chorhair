/**
 *description:档案管理
 *author:fanwei
 *date:2015/3/24
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var LeftBar = require('../../../sub/shop/system/leftBar');
	var RightBar = require('../../../sub/shop/system/rightTable');

	var oTplBar = require('../../../tpl/shop/system/fmBar');
	var oTplSub = require('../../../tpl/shop/system/fmBarSub');

	var oTplContent = require('../../../tpl/shop/system/fmRight');
	var oTplList = require('../../../tpl/shop/system/fmRightlist');

	var FileManage = R.Class.create(R.util, {

		initialize: function() {
			
			this.oBarWrap = $('[list-bar]');
			this.oGroupAddBtn = $('[group-add]');
			this.oTableWrap = $('[right-content-wrap]');
			var _this = this;

			var oLeftBar = new LeftBar({
				oWrap: this.oBarWrap,
				barTpl: oTplBar,
				barSubTpl: oTplSub,
				oGroupAdd: this.oGroupAddBtn,
				lookGroupUrl: R.interfaces.system.fmLookGroup,
				lookSubUrl: R.interfaces.system.fmFindSub,
				addGroupUrl: R.interfaces.system.fmAddGroup,
				editGroupUrl: R.interfaces.system.fmEditGroup,
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
				onEdit: function(oThis){
				
					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var price = oSubList.attr('price');
					var param = {
						pkPrice: pid,
						type: 'edit',
						servicerank: pname,
						price: price
					};

					oRightBar.getListData(param, function(data){

						var concatData = {data: 
							{
								servicerank: pname,
								price : price,
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
						pkPrice: pid
					};

				},
				onLook: function(oThis) {

					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var price = oSubList.attr('price');
					var param = {
						pkPrice: pid,
						type: 'look',
						servicerank: pname,
						price: price
					};
					oRightBar.getListData(param, function(data){

						var concatData = {data: 
							{
								servicerank: pname,
								price : price,
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
				getListUrl: R.interfaces.system.fmFindDetail,
				disableUrl: R.interfaces.system.fmDiable,
				addUrl: R.interfaces.system.fmAddList, 
				editUrl: R.interfaces.system.fmEditList,
				onFreshNew: function(oNew, id) {
					oNew.attr('disable', 'Y');
					oNew.html('禁用');
				},
				onAfterAdd: function(data) {

					var result = {
						data: [data]
					}
					_this.render(this.nowWrap, oTplSub, result, 'append');
				},
				onAfterEdit: function(data) {
					console.log(data);
				}
			});

		}
	});

	var oFileManage = new FileManage();

});
