/**
 *description: view-baseAction
 *author:fanwei
 *date:2014/12/30 
 */
module.exports = function(app) {

	//公共数据
	var commonData =  {};
	var viewConfig = require('../../config/hairer/view');
	var allway = app.get('allway');
	var http = require('http');
	var qs = require('querystring');
	var siteNav = require('../../config/hairer/nav');
	var inter = require('../../config/hairer/api/index');
	var setCookie = require('../cookie');

	commonData.views = viewConfig;
	commonData.route = JSON.stringify(viewConfig);
	commonData.cssPath = app.get('cssPath');
	commonData.jsPath = app.get('jsPath');
	commonData.imgPath = app.get('imgPath');
	commonData.seaPath = app.get('seaPath');
	commonData.siteNav = siteNav;

	var baseView = app.get('Class').create({

		initialize: function() {

		},
		viewConfig: viewConfig,
		inter: inter,
		loadModel: function(app, route) {

			var modelDir = app.get('modelDir');
			var moduleLoc = '../' + modelDir + route;
			return require(moduleLoc)(app);

		},
		loadView: function(opts) {
			
			/**
			 * allway: 客户端类型
			 * 001: 消费者
			 * 002: 美发师
			 */

			opts = opts || {};
			var app = opts.app || '';
			var route = opts.route || '';
			var beforeRender = opts.beforeRender || null;
			var nowWayInfo = allway['002'];
			var nowWayName = nowWayInfo.name;
			var nowPageName = route.substring( route.indexOf(nowWayName) + nowWayName.length + 1 );
			
			commonData.pageInfo = viewConfig[ nowPageName ];
			commonData.pageLevel = viewConfig [ nowPageName ].pageLevel;
			commonData.title = viewConfig[ nowPageName ].title;
			commonData.nowWayName = nowWayName;
			commonData.nowWayId = nowWayInfo.id;
			
			// 渲染页面 -> view 与 controllers的view 对应
			// 一个视图控制器对应 view视图			
			var _this = this;

			app.get(route, function(req, res){

				//give-session-info
				var sessionInfo = req.session;

				commonData.username = sessionInfo.username;
				commonData.nickname = sessionInfo.nickname;
				commonData.cellphone = sessionInfo.cellphone;
				commonData.sex = sessionInfo.sex;
				commonData.headurl = sessionInfo.headurl;
				commonData.headshorturl = sessionInfo.headshorturl;
				commonData.pkUser = sessionInfo.uid;
				commonData.barBg = viewConfig [ nowPageName ].barBg;

				//_this.singleLogin(req, res);
				var result = _this.checkIsLogin(req, res);

				if(result) {
					
					var platForm = _this.judgePlat(req);
					var sIosVersion = platForm.iOS || 0;
					var nIosVersion = parseInt(sIosVersion);
					
					if(nIosVersion > 6.0) {
						commonData.iosNavFix = 'iosNavFix';
					}
					
					var page = _this.fixPage(route);
					beforeRender && beforeRender.call(_this, req, res, page, commonData);
				}

			});

		},
		fixPage: function(route) {

			var viewsDir = app.get('viewControllerName');
			var re = new RegExp('\/' + viewsDir + '\/');
			var page = route.replace(re, '');
			
			return page;

		},
		renderPage: function(req, res, page, result) {

			res.render(page, result);

		},
		concatData: function(data) {

			data = data || {};

			//合并单个数据与公共数据
			for (var attr in commonData) {

				data[attr] = commonData[attr];

			}

			return data;
		},
		outJson: function(opts) {

			/*
			 * {code:"001", data:"", msg: ""}	
			 * 用于ajax输出
			*/
			opts = opts || {};

			var result = {
				code: opts.code,
				data: opts.data,
				msg: opts.msg
			};

			var sResult = JSON.stringify(result);
			opts.res.end(sResult);

		},
		getParam: function(req) {

			//获取参数
			if(req.method == 'POST') {

				return req.body;

			} else if(req.method == 'GET') {

				return req.query;
				
			}

		},
		checkIsLogin: function(req, res) {

			var _this = this;
			
			if(req.cookies.pkUser && !req.session.uid ) {
				
				//自动登录
				this.save(req, res, this.inter.user.getInfo, {
					pkUser: req.cookies.pkUser

					}, function(data){
					
					var info = JSON.parse(data.data);

					_this.saveUserSession(req, info);

					res.redirect(viewConfig['appoint/index'].url);

				}, function(){

					setCookie(res, 'pkUser', 'xxx', '-30day');

					req.session.uid = '';
					req.session.cellphone = '';
					req.session.sex = '';
					req.session.username = '';
					req.session.nickname = '';
					req.session.headurl = '';
					req.session.headshorturl = '';
					res.redirect(viewConfig['user/login'].url);

				});

			} else {
				
				//有session的情况
				var reqUrl = req.url;
				var uid = req.session.uid;
				var loginUrl = viewConfig['user/login'].url;
				var indexUrl = viewConfig['appoint/index'].url;
				var nowShortUrl = req._parsedUrl.pathname.replace('/views/hairer/', '');
				var isCanlook = viewConfig[nowShortUrl].isCanLook;
				var isLoginUrl = ( reqUrl == loginUrl );
				
				//检测是否登录
				if(!uid && !isCanlook) {

					res.redirect(loginUrl);
					return false;

				} else if(uid && isLoginUrl ) {

					res.redirect(indexUrl);
					return false;

				}

				return true;
				
			}

		},
		sendReq: function(opts) {

			//发送http请求
			opts = opts || {};
			var host = opts.host || app.get('javaDomain');
			var param = opts.param || '';
			var type = opts.type || 'POST';
			var port = opts.port || app.get('javaPort');
			var path = opts.path || '';
			var cb = opts.cb || null;
			var content = qs.stringify(param);
			type = type.toUpperCase();
			
			var options = {  
			    hostname: host,
			    port: port,  
			    path: path,  
			    method: type,
			    headers: {  
			        "Content-Type": 'application/x-www-form-urlencoded',  
			        "Content-Length": content.length  
			    }  
			};

			if(type == 'GET') {

				if(content) {
					options.path = options.path + '?' + content; 
				}

				delete options.headers;
			}

			var req = http.request(options, function (res) {  

			    res.setEncoding('utf8'); 

			    var data = "";  
			    res.on('data', function (chunk) { 
			    	
			    	data += chunk; 

			    }).on('end', function(){

			    	cb && cb(null, data);

			    });  

			});  
	  
			req.on('error', function (e) { 

				cb && cb(e.message);
			});
	
			if(type == 'POST') {
				req.write(content + '\n');
			}
			
			req.end();
		},
		save: function(req, res, url, param, suc, fail) {
				
			var _this = this;

			if(req.session) {

				var uid = req.session.uid;

				if(uid) {
					param.pkUser = uid;
					
				}

			}

			param.usergroup = app.get('allway')['002'].id;

			this.sendReq({
				path: url,
				param: {data: JSON.stringify(param)},
				cb: function(err, data){

					if(!err) {
						
						try {
							
							data = JSON.parse( data );
							
							if(data.issuccess) {

								suc && suc(data);

							} else {

								if(fail) {

									fail(data);

								} else {

									res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
									_this.outJson({
										code: '002',
										msg: data.msg,
										data: '',
										res: res
									});
								}
							}

						}catch(e) {
							
							res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
							_this.outJson({
								code: '002',
								msg: '数据异常',
								data: e,
								res: res
							});

						}
			
					} else {

						res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
						_this.outJson({
							code: '002',
							msg: '服务端连接失败',
							data: '',
							res: res
						});
					}
			
				}
			});
				
		},
		saveUserSession: function(req, info) {
			
			req.session.uid = info.pkUser;
			req.session.cellphone = info.cellphone;
			req.session.sex = info.sex;
			req.session.username = info.username;
			req.session.nickname = info.nickname;
			req.session.headurl = info.headurl;
			req.session.pkShop = info.pkShop;
			req.session.headshorturl = info.headshorturl;

		},
		ajaxCheckLogin: function(req, res) {

			//ajax接口判断登录
			var uid = req.session.uid;
			
			if(!uid) {

				this.outJson({
					code: '003',
					msg: '请登录',
					data: '',
					res: res
				});

			}	

		},
		judgePlat: function(request) {

			var ua = request.headers['user-agent'],  
			    $ = {};  
			  
			if (/mobile/i.test(ua))  
			    $.Mobile = true;  
			  
			if (/like Mac OS X/.test(ua)) {  
			    $.iOS = /CPU( iPhone)? OS ([0-9\._]+) like Mac OS X/.exec(ua)[2].replace(/_/g, '.');  
			    $.iPhone = /iPhone/.test(ua);  
			    $.iPad = /iPad/.test(ua);  
			}  
			  
			if (/Android/.test(ua))  
			    $.Android = /Android ([0-9\.]+)[\);]/.exec(ua)[1];  
			  
			if (/webOS\//.test(ua))  
			    $.webOS = /webOS\/([0-9\.]+)[\);]/.exec(ua)[1];  
			  
			if (/(Intel|PPC) Mac OS X/.test(ua))  
			    $.Mac = /(Intel|PPC) Mac OS X ?([0-9\._]*)[\)\;]/.exec(ua)[2].replace(/_/g, '.') || true;  
			  
			if (/Windows NT/.test(ua))  
			    $.Windows = /Windows NT ([0-9\._]+)[\);]/.exec(ua)[1]; 

			return $;
		}


	});

	return baseView;

};	