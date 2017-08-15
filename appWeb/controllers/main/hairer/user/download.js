/**
 *description: 用户app下载
 *author:fanwei
 *date:2015/2/9
 */

module.exports = function(app, route, preRoute) {
	
	var Class = app.get('Class');
	var baseController = require('../../../../lib/hairer/baseController')(app);

	var DownLoad = Class.create(baseController, {

		initialize: function() {
			
			this.root = 'download/';
			this.iosFile = 'iphone.txt';
			this.androidFile = 'android.txt';
			this.events();

		},
		events: function() {

			var _this = this;

			app.all(route, function(req, res){

				var uid = _this.getParam(req).uid;

				var fileName;

				if(uid) {

					_this.save(req, res, _this.inter.user.downLoadCount, {
						pkUser: uid
					}, function(data){

						var file = _this.judgeFile(req);

						_this.downLoad(file, req, res);
						
					}, function(data){

						_this.outJson({
							code: '002',
							msg: 'error',
							data: '',
							res: res
						});

					});

				}

			});

		},
		judgeFile: function(req) {

			var plat = this.judgePlat(req);
			var fileName;

			if(plat.iOS) {

				fileName =  this.iosFile;

			} else if(plat.Android) {

				fileName =  this.androidFile;

			} else {

				fileName =  this.androidFile;

			}

			return this.root + fileName;

		},
		downLoad: function(file, req, res) {

			res.download(file, function(err){

				if (!err) return; 
			    if (err && err.status !== 404) return; 
			   	
			    res.statusCode = 404;
			    res.send('Cant find that file, sorry!');
			});

		}	

	});

	var oDownLoad = new DownLoad();

}; 