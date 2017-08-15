/**
 *description: 调用java接口地址
 *author:fanwei
 *date:2015/1/25
 */

var user = require('./user');
var member = require('./member');
var service = require('./service');
var performance = require('./performance');
var goods = require('./goods');
var content = require('./content');
var global = require('./global');
var customService = require('./customService');
var system = require('./system');
var shop = require('./shop');
var plat = require('./plat');
var prefix = '/platserver';

var data = {
	user: user,
	member: member,
	service: service,
	performance: performance,
	goods: goods,
	content: content,
	global: global,
	customService: customService,
	system: system,
	shop: shop,
	plat: plat
};

for (var i in data) {

	for (var k in data[i]) {
		data[i][k] = prefix + data[i][k];
	}

}

module.exports = data;