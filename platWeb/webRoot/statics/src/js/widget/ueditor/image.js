/*
 *description:图片插入
 *author:wangwc
 *date:2015/05/21
 */

define(function(require, exports, module) {

	var Dialog = require('../dom/dialog');


	var InserImage = R.Class.create(R.util, {

		initialize: function(opts) {

			opts = opts || {};

			//property
			this.serverUrl = opts.serverUrl || R.interfaces.global.upload;
			this.oUploadBtn = opts.oUploadBtn || null;
			this.oConfirmBtn = opts.confirmBtn || null;
			this.ue = opts.ue || null;
			this.thumbWidth = opts.thumbWidth || 200;
			this.thumbHeight = opts.thumbHeight || 200;
			this.accept = opts.accept ||  {
				title: 'Images',
				extensions: 'gif,jpg,jpeg,png,JPG',
				mimeTypes: 'image/*'
			};
			this.maxFileSize = opts.maxFileSize || 1 * 1024 * 1024;
			this.firstInitUpload = true;

			//func
			this.initDialogBox();
			this.events();

		},
		events: function() {

			var self = this;

			baidu.editor.commands['myimageinsert'] = {
				execCommand: function() {

					$('[upload-box]').show();
					self.oUploadBox.show();

					if(self.firstInitUpload) {

						self.initUpload();
						self.uploader.onUploadSuccess = function(file, response){

							var imageUrl = response.data.url;

							// alert('上传成功:图片地址为:' + imageUrl);
							self.oUploadBox.close();

							self.ue.execCommand('insertHtml', '<img style="width:80%;" src='+ imageUrl +'>');
						};
						self.firstInitUpload = false;
					}
				}
			};


			this.oConfirmBtn.on('click', function(){
				self.uploader.upload();
			});
		},
		initDialogBox: function() {

			this.oUploadBox = new Dialog({
				boxSelector: '[upload-box]'
			});

		},
		initUpload: function() {

			this.uploader = WebUploader.create({
				auto: true,
				swf: R.uri.jsPath + 'widget/upload/Uploader.swf',
				server: this.serverUrl,
				pick: this.oUploadBtn,
				resize: false,
				thumb: {
					width: this.thumbWidth,
					height: this.thumbHeight
				},
				accept: this.accept,
				fileSingleSizeLimit: this.maxFileSize
			});

		}

	});

	module.exports = InserImage;
})