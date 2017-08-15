/**
 *description: upload
 *author:fanwei
 *date:2015/01/13 
 */
module.exports = function(app, route, preRoute) {

	var FdfsClient = require('fdfs-client');
	var gm = require('gm');
	var fastConnect = app.get('fastConnect');
	var fastDfsOverTime = app.get('fastDfsOverTime');
	var picDomain = app.get('picDomain');

	//fastDFS-client
	var fdfs = new FdfsClient({
	    trackers: fastConnect,
	    timeout: fastDfsOverTime,
	    charset: 'utf8'
	});

	var Class = app.get('Class');
	var baseController = require('../../../lib/hairer/baseController')(app);

	var Upload = Class.create(baseController, {

		initialize: function() {

			

		},
		toDoUpload: function(opts, res) {

			var _this = this;

			opts = opts || {};
			var param = opts.param;
			var suc = opts.suc || null;
			var thumbSize = opts.thumbSize || 200;
			var file = param.file;

			if(!file) {

				this.outJson({
					code: '002',
					msg: '文件为空',
					data: '',
					res: res
				});

			} else {

				var sStart = new Date().getTime();

				var oUpload = this.base64ToBuffer(file);
				var buffer = oUpload.buffer;
				var ext = oUpload.ext;

				this.thumb(buffer, thumbSize, function(thumbBuffer){

					var fs = require('fs');

					_this.uploadToFastDfs(buffer, ext, function(fileId){

						_this.uploadToFastDfs(thumbBuffer, ext, function(fileIdSmall){

							delete param.file;
							suc && suc(param, fileId, fileIdSmall);

						}, function(err){

							_this.outJson({
								code: '002',
								msg: err,
								data: '',
								res: res
							});

						}, res);

					}, function(err){

						_this.outJson({
							code: '002',
							msg: err,
							data: '',
							res: res
						});

					}, res);

				});
			}

		},
		thumb: function(buffer, dest, cb) {


			gm(buffer).size(function(err, info){

				var w,
					h;

				if(info.width > info.height) {

					w = '';
					h = dest;

				} else {

					w = dest;
					h = '';

				}

				gm(buffer).scale(w, h)
				.crop(dest, dest, 0, 0)
				.toBuffer(function(err, data){
				
					cb && cb(data);

				});

			});

		},
		base64ToBuffer: function(baseStr) {

			var ext = baseStr.match(/(jpeg|gif|png|jpg)/)[0];
			if (ext == 'jpeg') ext = 'jpg';
			var baseStr = baseStr.replace(/data\:image\/(jpeg|gif|png|jpg)\;base64\,/, "");
			var bitmap = new Buffer(baseStr, "base64");
			return {
				buffer: bitmap,
				ext: ext
			}

		},
		uploadToFastDfs: function(buffer, ext, suc, fail, res) {

			var _this = this;

			//把base64上传到fastDfs

			fdfs.upload(buffer, {ext: ext}, function(err, fileId) {
    			
				var picUrl = picDomain + fileId;

    			suc && suc(picUrl);

			}, function(err, fileId){

				fail && fail(err);

			});

			fdfs.on('error', function(err) {
			   	
				_this.outJson({
					code: '002',
					msg: 'fastdfs连接超时',
					data: '',
					res: res
				});

			});

		}


	});
	
	var oUplaod = new Upload();

	return oUplaod;


}; 