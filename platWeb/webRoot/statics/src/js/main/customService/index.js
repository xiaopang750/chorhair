/**
 *description:客服管理
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var oTplMsg = require('../../tpl/customService/msg');
	var getNowTime = require('../../util/time/getNowTime');
	var oTip = require('../../widget/dom/tip');
	var Dialog = require('../../widget/dom/dialog');
	var oGroupTpl = require('../../tpl/customService/groupBox');
	var oHistroyTpl = require('../../tpl/customService/msg');
	var oGroupListTpl = require('../../tpl/customService/groupList');
	var Select = require('../../widget/dom/select');
	var Calendar = require('../../widget/form/calendar');

	var Service = R.Class.create(R.util, {

		initialize: function() {
		
			this.oHistory = $('[history-wrap]');
			this.oHistoryView = $('[history-view]');
			this.oSendArea = $('[send-area]');
			this.oSendBtn = $('[send-btn]');
			this.oUserList = $('[search-result]');
			this.oHistoryBtn = $('[history-btn]');
			this.oCreatGroupBtn = $('[create-group-btn]');
			this.groupList = $('[msg-group-list]');
			this.oSendTo = $('[msg-to]');
			this.searchHistory = $('[search-history]');

			this.initDialogBox();

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');
			this.oSearchUserBtn = $('[search-user-btn]');
			this.userList = $('[user-list]');
			this.userSelect = $('[user-select]');
			this.userType = $('[user-type]');
			this.groupName = $('[group-name]');
			this.begintime = $('[begintime]');
			this.endtime = $('[endtime]');

			this.oldMember = [];
			this.newMember = [];
			this.removeMember = [];

			this.areaRelated();
			this.findShop();
			this.showCalendar();
			this.getGroup();
			this.judge();
			this.events();

		},
		initDialogBox: function() {

			var _this = this;

			//沟通组弹框
			this.oGroupBox = new Dialog({
				boxTpl: oGroupTpl
			});

			this.oConfirmDelBox = new Dialog({
				content: '确认删除分组吗?'
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

			//新建分组
			this.oCreatGroupBtn.on('click', function(){
				_this.oGroupBox.show();

				$('[group-box]').attr('action', 'add');

				_this.groupName.val('');
				_this.province.val('');
				_this.city.val('');
				_this.area.val('');
				_this.shop.val('');
				_this.userList.html('');
				_this.userSelect.html('');
				$('[all-select]')[0].checked = false;

			});

			//查询用户
			this.oSearchUserBtn.on('click', function(){
				if(_this.userType.val() == '1'){
					_this.searchUser();
				}else{
					_this.searchFairer();
				}
			});

			//勾选分组成员
			$(document).on('click', '[member-item] input', function(ev){

				var uid = $(this).parent().find('span').attr('pkmember');
				var name = $(this).parent().find('span').text();
				var cellphone = $(this).parent().find('span').attr('memberphone');

				if($(this)[0].checked){
					_this.userAdd(uid, name, cellphone);
				}else{
					_this.userRemove(uid, name, cellphone);
				}

				_this.isAllSelect();

			});

			$(document).on('click', '[member-item] span', function(ev){
				
				var uid = $(this).attr('pkmember');
				var name = $(this).text();
				var cellphone = $(this).attr('memberphone');

				if($(this).parent().find('input')[0].checked){
					$(this).parent().find('input')[0].checked = false;
					_this.userRemove(uid, name, cellphone);
				}else{
					$(this).parent().find('input')[0].checked = true;
					_this.userAdd(uid, name, cellphone);
				}

				_this.isAllSelect();

			});

			//双击删除用户
			$(document).on('dblclick', '[user-select] li', function(){

				var uid = $(this).attr('pkmember');
				var name = $(this).text();
				var cellphone = $(this).attr('memberphone');

				//匹配左侧选框
				var oSpan = _this.userList.find('[pkmember='+uid+'][memberphone='+cellphone+']');
				
				oSpan.parent().find('input')[0].checked = false;
				_this.userRemove(uid, name, cellphone);

				_this.isAllSelect();

			});

			//消除双击时选中区域
			$(document).on('mousedown', '.user-select-wrap', function(){
				return false;
			});

			//全选分组成员
			$(document).on('click', '[all-select]', function(){

				if($(this)[0].checked){

					_this.userList.find('input').each(function(){
						this.checked = true;
					});

					_this.userAddAll();

				}else{

					_this.userList.find('input').each(function(){
						this.checked = false;
					});

					_this.userRemoveAll();
				}

			});

			$(document).on('click', '[all-select-text]', function(){

				if($(this).parent().find('input')[0].checked){
					$(this).parent().find('input')[0].checked = false;

					_this.userList.find('input').each(function(){
						this.checked = false;
					});

					_this.userRemoveAll();

				}else{
					$(this).parent().find('input')[0].checked = true;

					_this.userList.find('input').each(function(){
						this.checked = true;
					});

					_this.userAddAll();
				}		

			});

			//保存沟通组
			$(document).on('click', '[group-confirm]', function(){
				_this.groupConfirm();
			});

			//编辑沟通组
			$(document).on('click', '[group-edit]', function(){

				var pkGroup = $(this).parent().attr('pkGroup');

				_this.oGroupBox.show();
				$('[group-box]').attr('action', 'edit');
				_this.getGroupData(pkGroup);
			});

			//删除分组
			$(document).on('click', '[group-remove]', function(){
				_this.cancelBtn = $(this);
				_this.oConfirmDelBox.show();
			});

			this.oConfirmDelBox.onConfirm = function() {
				_this.removeGroup();
			};

			//发送信息初始化
			$(document).on('click', '[group-send]', function(){

				var pkGroup = $(this).parent().attr('pkGroup');
				var groupName = $(this).parent().find('p').text();
				var groupNum = $(this).parent().attr('groupnum');

				_this.sendInit(pkGroup, groupName, groupNum);

			});

			//发送信息
			this.oSendBtn.on('click', function() {
				_this.sendMsg($(this));
			});

			//查看历史消息
			this.oHistoryBtn.on('click', function() {

				if(!$(this).attr('pkGroup') && !_this.parse().uid){
					oTip.say('请选择沟通组或者用户');
					return;
				}

				_this.getHistory($(this));

			});

		},
		judge: function() {

			if(this.parse().uid){
				this.oSendTo.html(this.parse().username);
				this.oSendArea.focus();
				this.searchHistory.show();
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
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM-dd HH:mm'
			});

		},
		getGroup: function() {

			var _this = this;

			this.reqUrl = R.interfaces.customService.getGroup;
			this.reqParam = {};
			this.req(function(data) {
				
				_this.render(_this.groupList, oGroupListTpl, data.data);

			},function(data) {
				oTip.say(data.msg);
			});

		},
		getGroupData: function(pkGroup) {

			var _this = this;

			this.reqUrl = R.interfaces.customService.getGroupData;
			this.reqParam = {
				pkGroup: pkGroup	
			};
			this.req(function(data){
				
				var groupBox = $('[group-box]');
				
				groupBox.attr('pkGroup', data.data.pkGroup);
				groupBox.find('[group-name]').val(data.data.groupname);

				_this.userList.html('');
				_this.userSelect.html('');
				_this.oldMember = [];

				for(var i=0;i<data.data.groupmembers.length;i++){
					var oUser = $('<li member-item><input class="ba-mr-2" type="checkbox"><span pkGroupMember="'+data.data.groupmembers[i].pkGroupMember+'" pkMember="'+data.data.groupmembers[i].pkMember+'" memberphone="'+data.data.groupmembers[i].memberphone+'">'+data.data.groupmembers[i].membername+'</span></li>');
					_this.userList.append(oUser);

					var oLi = $('<li pkGroupMember="'+data.data.groupmembers[i].pkGroupMember+'" pkMember="'+data.data.groupmembers[i].pkMember+'" memberphone="'+data.data.groupmembers[i].memberphone+'">'+data.data.groupmembers[i].membername+'</li>');
					_this.userSelect.append(oLi);

					//保存已有的用户
					_this.oldMember.push(data.data.groupmembers[i].pkGroupMember);
				}

				_this.userList.find('span').each(function(){
					
					for(var i=0;i<data.data.groupmembers.length;i++){
						if($(this).attr('pkMember') == data.data.groupmembers[i].pkMember){
							$(this).parent().find('input')[0].checked = true;
						}
					}

				});

				_this.isAllSelect();


			},function(data){
				oTip.say(data.msg);
			});

		},
		isAllSelect: function() {

			var allcheckbox = this.userList.find('input');
			var totalCount = allcheckbox.length;
			var allSelect = true;

			for(var i=0;i<allcheckbox.length;i++){
				if(!allcheckbox[i].checked){
					allSelect = false;
					break;
				}
			}

			if(allSelect){
				$('[all-select]')[0].checked = true;
			}else{
				$('[all-select]')[0].checked = false;
			}

		},
		userAdd: function(uid, name, cellphone) {

			var selectList = this.userSelect.find('li');
			var noRepeat = true;

			selectList.each(function(){
				if($(this).attr('pkMember') == uid && $(this).text() == name && $(this).attr('memberphone') == cellphone){
					noRepeat = false;
					return;
				}	
			});

			if(noRepeat){
				var oLi = $('<li pkMember="'+uid+'" memberphone="'+cellphone+'">'+name+'</li>');
				this.userSelect.append(oLi);
			}

		},
		userRemove: function(uid, name, cellphone) {

			var selectList = this.userSelect.find('li');

			selectList.each(function(){
				if($(this).attr('pkMember') == uid && $(this).text() == name && $(this).attr('memberphone') == cellphone){
					$(this).remove();
				}	
			});

		},
		userAddAll: function() {

			var userList = this.userList.find('span');
			var selectList = this.userSelect.find('li');
			var oThis = this; 

			userList.each(function(){

				var noRepeat = true;
				var _this = $(this);

				selectList.each(function(){
					if($(this).attr('pkMember') == _this.attr('pkMember') && $(this).text() == _this.text() && $(this).attr('memberphone') == _this.attr('memberphone')){
						noRepeat = false;
						return;
					}
				});

				if(noRepeat){
					var oLi = $('<li pkMember="'+_this.attr('pkMember')+'" memberphone="'+_this.attr('memberphone')+'">'+_this.text()+'</li>');
					oThis.userSelect.append(oLi);
				}

			});

		},
		userRemoveAll: function() {

			var userList = this.userList.find('span');
			var selectList = this.userSelect.find('li');

			userList.each(function(){

				var _this = $(this);

				selectList.each(function(){
					if($(this).attr('pkMember') == _this.attr('pkMember') && $(this).text() == _this.text() && $(this).attr('memberphone') == _this.attr('memberphone')){
						$(this).remove();
					}
				});

			});

		},
		showAddedUser: function() {

			var userList = this.userList.find('span');
			var selectList = this.userSelect.find('li');

			userList.each(function(){

				var _this = $(this);

				selectList.each(function(){
					if($(this).attr('pkMember') == _this.attr('pkMember') && $(this).text() == _this.text() && $(this).attr('memberphone') == _this.attr('memberphone')){
						_this.parent().find('input')[0].checked = true;
					}
				});

			});

		},
		searchUser: function() {

			var _this = this;

			this.reqUrl = R.interfaces.customService.getCustomer;

			this.reqParam = {
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey'),
				likecontent: $('[user-name]').val()
			}

			this.req(function(data){

				_this.userList.html('');

				for(var i=0;i<data.data.list.length;i++){
					var oUser = $('<li member-item><input class="ba-mr-2" type="checkbox"><span pkMember="'+data.data.list[i].pkCustomer+'" memberphone="'+data.data.list[i].cellphone+'">'+data.data.list[i].customername+'</span></li>');
					_this.userList.append(oUser);
				}	

				//匹配已选择的用户
				_this.showAddedUser();
				_this.isAllSelect();

			});

		},
		searchFairer: function() {

			var _this = this;

			this.reqUrl = R.interfaces.service.list;

			this.reqParam = {
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey'),
				querylike: $('[user-name').val()
			}

			this.req(function(data){

				_this.userList.html('');

				for(var i=0;i<data.data.list.length;i++){
					var oFairer = $('<li member-item><input class="ba-mr-2" type="checkbox"><span pkMember="'+data.data.list[i].pkFairer+'" memberphone="'+data.data.list[i].cellphone+'">'+data.data.list[i].fairername+'</span></li>');
					_this.userList.append(oFairer);
				}

				//匹配已选择的用户
				_this.showAddedUser();
				_this.isAllSelect();	

			});

		},
		getGroupmember: function() {

			var memberArr = [];
			var memberList = this.userSelect.find('li');

			memberList.each(function(){
				var json = {
					membername: $(this).text(),
					pkMember: $(this).attr('pkMember'),
					memberphone: $(this).attr('memberphone'),
					pkGroupMember: $(this).attr('pkGroupMember')
				};

				memberArr.push(json);
			});

			return memberArr;

		},
		findRemove: function() {

			var selectList = this.userSelect.find('li');
			var _this = this;

			this.newMember = [];
			this.removeMember = [];

			selectList.each(function(){
				if($(this).attr('pkGroupMember')){
					_this.newMember.push(parseInt($(this).attr('pkGroupMember')));
				}
			});	

			//筛选删除的用户
			for(var i=0;i<this.oldMember.length;i++){

				if(this.newMember.indexOf(this.oldMember[i]) == -1){
					var json = {
						pkGroupMember: this.oldMember[i]+'',
						isdelete: 'Y'
					};
					this.removeMember.push(json);
				}
			}

			return this.removeMember;

		},
		removeGroup: function() {

			var _this = this;
			var pkGroup = this.cancelBtn.parent().attr('pkGroup');

			this.reqUrl = R.interfaces.customService.removeGroup;
			this.reqParam = {
				pkGroup: pkGroup
			};
			this.req(function(data){
				_this.oConfirmDelBox.close();
				oTip.say(data.msg);
				_this.getGroup();
			},function(data){
				oTip.say(data.msg);
			});

		},
		groupConfirm: function() {

			var groupmember = this.getGroupmember();
			var removemember = this.findRemove();
			var _this = this;
			var oBox = $('[group-box]');

			if(oBox.attr('action') == 'add'){

				this.reqUrl = R.interfaces.customService.createGroup;

				this.reqParam = {
					groupname: this.groupName.val(),
					groupfrom: '002',
					groupmember: JSON.stringify(groupmember)
				};

			}else{

				this.reqUrl = R.interfaces.customService.editGroup;

				var concatArr = groupmember.concat(removemember);

				this.reqParam = {
					pkGroup: oBox.attr('pkGroup'),
					groupname: this.groupName.val(),
					groupfrom: '002',
					groupmember: JSON.stringify(concatArr)
				};

			}

			if(this.checkGroup(this.reqParam)){
				this.req(function(data){

					oTip.say(data.msg);
					_this.getGroup();
					_this.oGroupBox.close();

				},function(data){
					oTip.say(data.msg);
				});
			}

		},
		checkGroup: function(param) {

			if($.trim(param.groupname) == ''){
				oTip.say('请输入沟通组名称');
				return false;
			}

			if(this.getGroupmember().length == 0){
				oTip.say('请选择沟通组成员');
				return false;
			}

			return true;

		},
		sendInit: function(pkGroup, groupName, groupnum) {

			this.oSendTo.html(groupName +'<span>('+ groupnum + '人)</span>');
			this.oSendArea.focus();
			this.oHistory.html('');
			this.oSendBtn.attr('pkGroup', pkGroup);
			this.oHistoryBtn.attr('pkGroup', pkGroup);
			this.searchHistory.show();

		},
		sendMsg: function(oThis) {

			var pkGroup = oThis.attr('pkGroup');
			var groupname = this.oSendTo.html();
			var content = $.trim(this.oSendArea.val());
			var _this = this;

			this.reqUrl = R.interfaces.customService.sendMsg;

			if(pkGroup){

				this.reqParam = {
					pkGroup: pkGroup,
					groupname: groupname,
					content: content
				};

			}else if(this.parse().uid){ //发送给个人

				this.reqParam = {
					pkMember: this.parse().uid,
					membername: this.parse().username,
					cellphone: this.parse().cellphone,
					usergroup: '001',
					content: content
				};

			}else{
				oTip.say('请选择沟通组或者用户');
				return;
			}

			if(!content){
				oTip.say('请填写内容');
				return;
			}
			
			this.req(function(data){
				oTip.say(data.msg);
				_this.oSendArea.val('').blur();
			}, function(data){
				oTip.say(data.msg);
			});

		},
		getHistory: function(oThis) {

			var _this = this;

			this.reqUrl = R.interfaces.customService.historyMsg;

			var pkGroup = oThis.attr('pkGroup');

			if(pkGroup){
				this.reqParam = {
					pkGroup: pkGroup,
					begintime: this.begintime.val(),
					endtime: this.endtime.val(),
				};
			}else{
				this.reqParam = {
					pkMember: this.parse().uid,
					begintime: this.begintime.val(),
					endtime: this.endtime.val(),
				};
			}

			this.req(function(data){
				_this.render(_this.oHistory, oHistroyTpl, data.data);
			},function(data){
				oTip.say(data.msg);
			});

		}
		
	});

	var oService = new Service();

});
