/**
 *description: 客服
 *author:fanwei
 *date:2015/2/8
 */
module.exports = {

	fairer: '/fairer/findlist.php', //获取理发师
	customer: '/customer/query.php', //获取所有消费者
	sendMsg: '/message/sendmessage.php', //发送消息
	historyMsg: '/message/querymessage.php', //历史消息
	createGroup: '/message/creategroup.php', //创建分组
	getGroup: '/message/querygroup.php', //获取分组
	getGroupData: '/message/querygroupbyid.php', //根据ID获取分组信息
	editGroup: '/message/editgroup.php', //编辑分组
	removeGroup: '/message/delgroup.php', //删除分组
};