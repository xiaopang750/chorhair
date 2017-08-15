/**
 *description:美丽记录上传
 *author:fanwei
 *date:2015/2/6
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var UpLoad = require('../../../widget/dom/upload');
	var Camera = require('../../../api/camera');
	var template = require('../../../lib/template/artTemplate');
	var Ui = require('../../../api/ui');
	var oUi = new Ui();
	var Gallery = require('../../../widget/dom/gallery');

	var RecordUpload = R.Class.create(R.util, {

		initialize: function(opts) {
				
			opts = opts || {};
			this.otherParam = opts.otherParam || {};
			this.max = opts.max || 8;
			this.trigger = opts.trigger || '';
			this.subUrl = opts.subUrl || '';
			this.photoTpl = opts.photoTpl || '';
			this.oWrap = opts.oWrap || null;
			this.uploadLock = false;

			this.oUpload = new UpLoad({
				uploadBtn: this.trigger
			});

			this.oUpload.init();
			this.events();

		},
		addPhoto: function(data) {

			data.shortUrl = data.shortUrl.replace(/\-/gi, '-');
			var render = template.compile(this.photoTpl);
			var oNew = $( render(data) );
			this.oWrap.append(oNew);
			
		},
		removePhoto: function(oList) {

			this.onRemove && this.onRemove(oList);

		},
		events: function() {
			
			var _this = this;

			//拍照
			this.oUpload.onPhoto = function() {

				var nowLen = _this.getNowLen();

				if(nowLen >= _this.max) {

					_this.uiInfo.tip({
						content: '最多上传' + _this.max + '张图片'
					});

					return;
				}

				if(!_this.uploadLock) {

					Camera.photo(function(base64){

						_this.upload(base64, this);

					}, function(msg){

						_this.uiInfo.tip({
							content: msg
						});

					});

				} else {

					_this.uiInfo.tip({
						content: '请先等待当前照片上传完毕'
					});

				}

			};

			//从相册选择
			this.oUpload.onSelect = function() {
				
				var nowLen = _this.getNowLen();

				if(nowLen >= _this.max) {

					_this.uiInfo.tip({
						content: '最多上传' + _this.max + '张图片'
					});

					return;
				}

				if(!_this.uploadLock) {

					Camera.pick(function(base64){

						_this.upload(base64, this);

					}, function(msg){

						_this.uiInfo.tip({
							content: msg
						});

					});

				} else {

					_this.uiInfo.tip({
						content: '请先等待当前照片上传完毕'
					});

				}

			};

			//删除
			this.oWrap.on('click', '[photo-remove]', function(){

				var oList = $(this).parents('[photo-list]');

				_this.removePhoto(oList);

			});
			
		},
		upload: function(base64, oCamera) {

			var _this = this;

			//上传
			this.reqUrl = R.interfaces.upload.record;
			this.reqParam = {
				file: base64
			};
			this.req(function(data){

				_this.addPhoto(data.data); // {url: shortUrl:}
				oCamera.loadingHide();
				_this.uploadLock = false;

			}, function(data){

				oCamera.loadingHide();

				_this.uiInfo.tip({
					content: data.msg
				});

				_this.uploadLock = false;

			});
		},
		getNowLen: function() {

			return this.oWrap.find('[photo-list]').length;

		},
		getPicData: function() {

			var _this = this;
			var aList = this.oWrap.find('[photo-list]');
			var arr = [];
			this.shouldUploadList = [];
			aList.each(function(i){

				var param = {};
				var oList = aList.eq(i);
				var pk = oList.attr('pkBeautyrecord');
				var isUploaded = oList.attr('uploaded');
				if(!pk && isUploaded != 'true') {
					param.url = oList.attr('url');
					param.shorturl = oList.attr('thumb');
					_this.shouldUploadList.push(oList);
					arr.push(param);
				}
			
			});

			return arr;

		},
		save: function() {

			var urls = this.getPicData();
			
			var _this = this;

			if(urls.length) {

				oUi.uiLoading.show();
				this.reqUrl = this.subUrl//R.interfaces.record.upload;
				this.reqParam = this.otherParam;
				this.reqParam.urls = JSON.stringify(urls);

				this.req(function(data){

					var num = urls.length;
					for (var i=0; i<num; i++) {
						_this.shouldUploadList[i].attr('uploaded', 'true');
					}

					_this.uiInfo.tip({
						content: data.msg
					});

					oUi.uiLoading.hide();

				}, function(data){

					_this.uiInfo.tip({
						content: data.msg
					});

					oUi.uiLoading.hide();

				});

			}

		}

	});

	module.exports = RecordUpload;

});
