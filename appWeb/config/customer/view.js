var prefix = '/views/customer/';

module.exports = {	
	'user/login': {
		url : prefix + 'user/login',
		title : '登录',
		pageLevel: "-1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'user/regist': {
		url : prefix + 'user/regist',
		title : '注册',
		pageLevel: "-1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'user/forget': {
		url : prefix + 'user/forget',
		title : '忘记密码',
		pageLevel: "-1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/login',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'user/index': {
		url : prefix + 'user/index',
		title : '首页',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: true,
		barBg: '#f14c76'
	},
	'user/info': {
		url : prefix + 'user/info',
		title : '我的账户',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'user/package': {
		url : prefix + 'user/package',
		title : '套餐信息',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/info',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'message/list': {
		url : prefix + 'message/list',
		title : '消息',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'message/detail': {
		url : prefix + 'message/detail',
		title : '详细详情',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'appoint/index': {
		url : prefix + 'appoint/index',
		title : '我的订单',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'appoint/detail': {
		url : prefix + 'appoint/detail',
		title : '订单详情',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'appoint/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'search/index': {
		url : prefix + 'search/index',
		title : '搜索首页',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'search/detail': {
		url : prefix + 'search/detail',
		title : '搜索详情',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: '',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'detail/index': {
		url : prefix + 'detail/index',
		title : '详情页',
		pageLevel: "2",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: true,
		barBg: '#f5f6f6'
	},
	'record/index': {
		url : prefix + 'record/index',
		title : '美丽记录',
		pageLevel: "1",
		rightText: "",
		rightUrl: "",
		backUrl: prefix + 'user/index',
		isCanLook: false,
		barBg: '#f5f6f6'
	},
	'login/out': {
		url: '/main/customer/user/loginOut', //退出登录接口
		title: '',
		isCanLook: true,
		barBg: '#f5f6f6'
	}

};