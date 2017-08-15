/**
 *description: user接口地址
 *author:fanwei
 *date:2015/1/14
 */
module.exports = {

	login: '/user/login.php', //登录
	info: '/user/register.php', //获取用户信息
	getPackage: '/customercombo/querybyuser.php', //获取用户套餐
	getInfo: '/user/showById.php', //根据主键查询用户信息
	forgetPass: '/user/forget.php', //获取忘记密码验证码
	checkCode: '/user/checkcode.php', //验证是否可以重置密码
	resetPass: '/user/resetpassword.php', //重置密码
	editInfo: '/user/editinfo.php' //用户信息修改

};