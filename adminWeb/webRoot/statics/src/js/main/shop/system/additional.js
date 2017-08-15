/**
 *description:附加项目管理
 *author:fanwei
 *date:2015/3/24
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var LeftBar = require('../../../sub/shop/system/leftBar');
	var RightBar = require('../../../sub/shop/system/rightTable');

	var oTplBar = require('../../../tpl/shop/system/addBar');
	var oTplSub = require('../../../tpl/shop/system/addBarSub');

	var oTplContent = require('../../../tpl/shop/system/addRight');
	var oTplList = require('../../../tpl/shop/system/addRightlist');

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
				onEdit: function(oThis){
				
					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var price = oSubList.attr('price');
					var param = {
						pkAddition: pid,
						type: 'edit',
						servicerank: pname
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
				onLook: function(oThis) {

					var oSubList = oThis.parents('[list-sub]');
					var pid = oSubList.attr('pid');
					var pname = oSubList.attr('pname');
					var price = oSubList.attr('price');
					var param = {
						pkAddition: pid,
						type: 'look',
						additionname: pname,
						price: price
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

		}
	});

	var oFileManage = new FileManage();

});
