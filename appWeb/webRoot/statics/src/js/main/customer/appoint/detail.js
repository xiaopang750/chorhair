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
	var UploadHeader = require('../user/uploadHeader');
	var oUi = new Ui();

	var Comment = require('../../../widget/dom/comment');

	var Gallery = require('../../../widget/dom/gallery');
	
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

			this.nowPackageUpload = {};
		},
		uploadHeader: function() {

			var _this = this;

			//上传用户头像
			this.UploadHeader = new UploadHeader({
				subUrl: R.interfaces.upload.packageHead,
				otherParam: {
					pkOrder: this.urlInfo.pkOrder
				},
				sucDo: function(data){

					data.usershortphoto = data.usershortphoto.replace(/\-/gi, '-');
					this.oImage.attr('src', data.usershortphoto);
					_this.nowPackageUpload = data;

				},
				trigger: '[package-head]',
				oImage: $('[package-head]')
			});

		},
		loadData:  function(){

			var _this = this;

			this.req(function(data){

				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);

				var tpl = require('../../../tpl/customer/appoint/detail');
				var wrap = $('[order-info]');
				var isCombo = data.data.orderinfo.iscombo == 'Y' ? true : false;

				_this.render(wrap, tpl, data.data);

				//图片缩放
				var oGallery = new Gallery({
					galleryWrap: $('[gallery-wrap]')
				});

				_this.submitAprBox = $('[submit-apr-box]');
				_this.submitAprBtn = $('[submit-apr-btn]');
				_this.oHeaderUploadBtn = $('[header-upload-btn]');
				
				if(isCombo) {

					//头像上传
					_this.uploadHeader();
				} else {

					//美丽记录上传
					_this.showCamera();
				}
				
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

			$('[extend]').on('click',function(){
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

			//套餐头像上传
			this.oHeaderUploadBtn.on('click', function(){
				
				var result = _this.judgeAsyncHead();

				if(!result) return;

				oUi.uiConfirm.confirm({
					content:'确认上传么？确认后不能修改!',
					onConfrim: function() {

						_this.asyncHead();

					}
				});

			});
			
		},
		judgeAsyncHead: function() {

			var result = true;

			if(!this.nowPackageUpload.userphoto) {

				this.uiInfo.tip({
					content:'请上传用户头像'
				});

				result = false;
			}

			return result;

		},
		asyncHead: function() {

			var _this = this;

			this.reqUrl = R.interfaces.appoint.packageUpload;
			this.reqParam = this.nowPackageUpload;
			this.req(function(){

				_this.UploadHeader.disable();
				_this.oHeaderUploadBtn.hide();

			}, function(data){
				_this.uiInfo.tip({
					content:data.msg
				});
			});

		},
		appraise: function(obj){

			var _this = this;

			this.starNum = obj.index() + 1;

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
					'<a href="{{url}}"><img src="{{shortUrl}}"></a>' +
				'</li>'
			});

		}
	});

	var oAppointDetail = new AppointDetail();
		

});
