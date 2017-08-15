/**
 *description:用户头像上传
 *author:fanwei
 *date:2015/2/6
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var UpLoad = require('../../../widget/dom/upload');
	var Camera = require('../../../api/camera');

	var UpLoadHeader = R.Class.create(R.util, {

		initialize: function(opts) {
				
			opts = opts || {};
			this.otherParam = opts.otherParam || {};
			this.subUrl = opts.subUrl || '';
			this.sucDo = opts.sucDo || null;
			this.trigger = opts.trigger || '';
			this.oImage = opts.oImage || null;
			this.uploadLock = false;

			this.oUpload = new UpLoad({
				uploadBtn: this.trigger
			});

			this.oUpload.init();
			this.events();

		},
		disable: function() {

			this.oUpload.disable();

		},
		showHeader: function(url) {

			this.oImage.attr('src', url);

		},
		events: function() {
			
			var _this = this;

			//拍照
			this.oUpload.onPhoto = function() {

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
			
		},
		upload: function(base64, oCamera) {

			var _this = this;

			//上传
			this.reqUrl = this.subUrl;
			this.reqParam = this.otherParam;
			this.reqParam.file = base64;

			this.req(function(data){

				if(_this.sucDo) {

					_this.sucDo(data.data);

				} else {

					data.data = data.data.replace(/\-/gi, '-');
					_this.showHeader(data.data);	
				}
				
				oCamera.loadingHide();
				_this.uploadLock = false;

			}, function(data){

				oCamera.loadingHide();

				_this.uiInfo.tip({
					content: data.msg
				});

				_this.uploadLock = false;

			});
		}

	});

	module.exports = UpLoadHeader;

});
