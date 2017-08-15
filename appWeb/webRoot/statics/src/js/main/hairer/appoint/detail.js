/**
 *description: 订单详情页
 *author:fanwei
 *date:2015/1/21
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var bodyParse = require('../../../util/http/bodyParse');
	var RecordUpload = require('./recordUpload');
	var Ui = require('../../../api/ui');
	var oUi = new Ui();

	var Comment = require('../../../widget/dom/comment');
	
	var AppointDetail = R.Class.create(R.util, {

		initialize: function() {
		
			this.urlInfo = bodyParse();
			this.reqUrl = R.interfaces.appoint.detail;
			this.reqParam = {pkOrder: this.urlInfo.pkOrder};

			this.bAppraise = false;
			this.submitAprBox = null;
			this.submitAprBtn = null;
			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');
			this.starNum = 0;
			
			this.loadData();
		},
		loadData:  function(){

			var _this = this;

			this.req(function(data){

				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);

				var tpl = require('../../../tpl/hairer/appoint/detail');
				var wrap = $('[order-info]');

				_this.render(wrap, tpl, data.data);
				_this.orgPrice = $('[orgPrice]').html() ? $('[orgPrice]').html() : 0;
				_this.sum();

				_this.submitAprBox = $('[submit-apr-box]');
				_this.submitAprBtn = $('[submit-apr-btn]');

				//美丽记录上传
				_this.showCamera();
				_this.oUploadConfirmBtn = $('[confirm-upload]');
				//confirm-upload
				_this.oUploadConfirmBtn.on('click', function(){
					
					_this.RecordUpload.save();

				});

				//评价星级
				var star = parseInt(data.data.orderinfo.evaluategoal);

				if(star == 0){
					_this.submitAprBox.show();
				}else{
					_this.bAppraise = true;
				}

				for(var i=0;i<star;i++){
					$('[star]').eq(i).addClass('active');
				}

				var comments = $('[comment-wrap]');
				
				comments.find('li').hide();
				comments.find('li').eq(0).show();
				comments.find('li').eq(1).show();

				var oComment = new Comment({
					pkOrder: bodyParse().pkOrder,
					index: 0
				});

				//显示更多按钮
				if(data.data.spitslots && data.data.spitslots.length > 2){		
					$('[extend]').show();
				}

				_this.events();

			},function(data){
				_this.uiInfo.tip({
					content:data.msg
				});
			});

		},
		events: function() {
			
			var _this = this;

			$('[extend]').hammer().on('tap',function(){
				_this.extend($(this));
			});

			//评价星级
			$('[star]').hammer().on('tap', function(){

				if(!_this.bAppraise){
					_this.appraise($(this));
				}
				
			});

			//提交评价
			this.submitAprBtn.hammer().on('tap',function(){

				if(_this.starNum == '0'){
					alert('请选择评价星级');
				}else{
					_this.submitApr();
				}
				
			});


			this.loadingWrap.on('click', '[addtion-plus]', function(){

				var oInput = $(this).parents('[addtion-list]').find('[addtion]');
				_this.operateMoney(oInput, 'plus');
				_this.sum();

			});


			this.loadingWrap.on('click', '[addtion-reduce]', function(){
				
				var oInput = $(this).parents('[addtion-list]').find('[addtion]');
				_this.operateMoney(oInput, 'reduce');
				_this.sum();

			});

			this.loadingWrap.on('keypress', '[addtion]', function(){
				_this.sum();
			});

			this.loadingWrap.on('keyup', '[addtion]', function(){
				_this.sum();
			});

			this.loadingWrap.on('click', '[addtion-confirm]', function(){
				
				_this.reqUrl = R.interfaces.appoint.addtion;
				_this.reqParam = _this.getModifyParam();
				
				_this.req(function(data){

					_this.uiInfo.tip({
						content:data.msg
					});

				}, function(data){

					_this.uiInfo.tip({
						content:data.msg
					});
					
				});

			});
			
		},
		getModifyParam: function() {

			//确认修改附加项
			var aAddtion = $('[addtion]');
			var oSum = $('[addtion-sum]');
			var oAddtion;
			var param;
			var arr = [];

			aAddtion.each(function(i){

				oAddtion = aAddtion.eq(i);
				param = {};
				param.pkDetail = oAddtion.attr('pkDetail');
				param.pkAddition = oAddtion.attr('pid');
				param.additionname = oAddtion.attr('name');
				param.additionmoney = isNaN( oAddtion.val() ) ? 0 : oAddtion.val();
				arr.push(param);

			});

			return {
				pkOrder: this.urlInfo.pkOrder,
				ordermoney: oSum.html(),
				addition: JSON.stringify(arr)
			}

		},
		operateMoney: function(oInput, way) {

			var sValue = oInput.val();
			var isNum = /\d+/gi.test(sValue);
			
			if(isNum) {

				var nValue = parseInt(sValue);

				if(way == 'plus') {
					nValue ++ ;
				} else {

					nValue --;

					if(nValue < 0) nValue = 0;
				}

				oInput.val(nValue);

			}


		},
		sum: function() {

			var aAddtion = $('[addtion]');
			var oSum = $('[addtion-sum]');
			var oAddtion;
			var count;
			var nMoney;

			count = 0;

			aAddtion.each(function(i){

				oAddtion = aAddtion.eq(i);
				nMoney = parseInt(oAddtion.val()) ? parseInt(oAddtion.val()) : 0;
				count += nMoney;

			});

			count += parseInt(this.orgPrice);

			oSum.html(count);

		},
		appraise: function(obj){

			var _this = this;

			this.starNum = obj.index()+1;

			$('[star]').removeClass('active');

			for(var i=0;i<this.starNum;i++){
				$('[star]').eq(i).addClass('active');
			}

		},
		submitApr: function(){

			var _this = this;

			oUi.uiLoading.show();

			this.reqUrl = R.interfaces.appoint.score;
			this.reqParam = {pkOrder: this.urlInfo.pkOrder, evaluategoal: this.starNum};

			this.req(function(data){
				alert(data.msg);
				_this.bAppraise = true;
				_this.submitAprBox.hide();
				oUi.uiLoading.hide();
			},function(data){
				alert(data.msg);
				oUi.uiLoading.hide();
			});

		},
		extend: function(obj){

			if(obj.html() == '展开评论'){

				obj.parent().find('[comment-list]').find('li').show();
				obj.html('收起评论');

			}else{

				obj.parent().find('[comment-list]').find('li').hide();
				obj.parent().find('[comment-list]').find('li').eq(0).show();
				obj.parent().find('[comment-list]').find('li').eq(1).show();
				obj.html('展开评论');

			}

		},
		showCamera: function() {

			this.RecordUpload = new RecordUpload({
				trigger: '[camera-btn]',
				subUrl: R.interfaces.record.upload,
				oWrap: $('[record-list-wrap]'),
				otherParam: {
					pkOrder: this.urlInfo.pkOrder
				},
				photoTpl: 
				'<li class="ba-fl" url="{{url}}" thumb="{{shortUrl}}" pkBeautyrecord="" photo-list>' +
					'<img src="{{shortUrl}}">' +
				'</li>'
			});

		}

	});

	var oAppointDetail = new AppointDetail();

});
