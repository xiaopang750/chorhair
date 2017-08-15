<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta property="qc:admins" content="1467265057650150456375" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>show</title>
<script type="text/javascript" src="../javascripts/jquery-1.8.2.js"></script>
<script type="text/javascript">
	function show() {
		var str="{"data":"{\"accountmoney\":0,\"addr\":\"1\",\"age\":0,\"birthday\":\"\",\"cellphone\":\"13114394408\",\"city\":\"北京市\",\"county\":\"昌平区\",\"dr\":0,\"fairercode\":\"rs002000003\",\"fairerfrom\":\"002\",\"fairername\":\"pangzi\",\"fixphone\":\"\",\"headimageurl\":\"\",\"headshorturl\":\"\",\"identitycard\":\"420106198910152010\",\"isvalidate\":\"Y\",\"nativeplace\":\"1\",\"nickname\":\"haha\",\"note\":\"add\",\"pkFairer\":3,\"pkShop\":1,\"pkUser\":0,\"province\":\"北京\",\"rankname\":\"\",\"registertime\":\"2015-01-31 14:10:58\",\"servicemoney\":0,\"servicenum\":0,\"sex\":\"1\",\"status\":\"\",\"superior\":\"\",\"ts\":\"2015-01-31 14:10:58\",\"urgencypeople\":\"[{\\\"name\\\":\\\"related2\\\",\\\"tel\\\":\\\"\\\",\\\"relate\\\":\\\"\\\"},{\\\"name\\\":\\\"related2\\\",\\\"tel\\\":\\\"\\\",\\\"relate\\\":\\\"\\\"}]\",\"attachment\":[{\"attachmentshorturl\":\"http://pic.rs07.com/group1/M00/00/6A/wKgKHVTMceaAFVYqAAAP6bnVUkk897.jpg\",\"attachmenttype\":\"IMAGE\",\"attachmenturl\":\"http://pic.rs07.com/group1/M00/00/6A/wKgKHVTMceaAEhxTAAD0VgdNi80607.jpg\",\"dr\":0,\"pkAttachment\":1,\"pkFairer\":3,\"ts\":\"2015-01-31 14:10:59\"},{\"attachmentshorturl\":\"http://pic.rs07.com/group1/M00/00/6A/wKgKHVTMceeABdaEAAAQDvmPVlE290.jpg\",\"attachmenttype\":\"IMAGE\",\"attachmenturl\":\"http://pic.rs07.com/group1/M00/00/6A/wKgKHVTMceaAMpadAAAfHyMI52U041.jpg\",\"dr\":0,\"pkAttachment\":2,\"pkFairer\":3,\"ts\":\"2015-01-31 14:10:59\"},{\"attachmentshorturl\":\"http://pic.rs07.com/group1/M00/00/6A/wKgKHVTMceeAaGt5AAAPBySYxBo060.jpg\",\"attachmenttype\":\"IMAGE\",\"attachmenturl\":\"http://pic.rs07.com/group1/M00/00/69/wKgKHlTMceeAcJSvAAEA4vBMpFs331.jpg\",\"dr\":0,\"pkAttachment\":3,\"pkFairer\":3,\"ts\":\"2015-01-31 14:10:59\"}],\"skill\":[{\"dr\":0,\"pkFairer\":3,\"pkSkill\":4,\"skillname\":\"1\",\"skillrank\":\"\",\"ts\":\"2015-01-31 14:10:59\"},{\"dr\":0,\"pkFairer\":3,\"pkSkill\":5,\"skillname\":\"2\",\"skillrank\":\"\",\"ts\":\"2015-01-31 14:10:59\"},{\"dr\":0,\"pkFairer\":3,\"pkSkill\":6,\"skillname\":\"tag1\",\"skillrank\":\"\",\"ts\":\"2015-01-31 14:10:59\"}]}","issuccess":true,"msg":"查询理发师信息成功"}";
		$.ajax({
			type:"post",
	  		url:"fairer/findbyid.php",
	  		cache:false,
	  		data:{"data":str},
	  		dataType:"json",
	  		success:function(data){
  	  			var title = '<table><tr><td>序号</td><td>主键</td><td>登录名</td><td>密码</td><td>昵称</td><td>等级</td><td>操作</td></tr>';
  	  			var context = '';
  	  			$.each(data,function(i,item){
  	  				if(i%2!=0){
  	  					context += '<tr style="background-color: #E5DBE2;"><td>'+i+'</td><td>'+item.id+'</td><td>'+item.loginName+'</td><td>'+item.loginPwd+'</td><td>'+item.nickName+'</td><td>'+item.roleRank+'</td><td><a href="javascript:void(0);" onclick="del(this,'+item.id+')" class="del">删除</a></td></tr>';
  	  				}else{
  	  					context += '<tr><td>'+i+'</td><td>'+item.id+'</td><td>'+item.loginName+'</td><td>'+item.loginPwd+'</td><td>'+item.nickName+'</td><td>'+item.roleRank+'</td><td><a href="javascript:void(0);" onclick="del(this,'+item.id+')" class="del">删除</a></td></tr>';
  	  				}
  	  			});
  	  			var display = title+context+'</table>';
  	  			
  	  		    $("#display").html(display);
  		  	},
  		  	error:function(data){
  		  		alert("faild");
  		  	}
		});
	}
	function del(param1,param2){
		if(param2==1){
			alert("本账号不可删除。。。(=^ ^=)");
			return;
		}
		$.ajax({
			type:"post",
			url:"../member/delById.php",
			cache:false,
	  		data:{"id":param2},
	  		dataType:"json",
	  		success:function(data){
	  		    //if(data==true) $(param1).parent().parent().remove();
		  		$.ajax({
		  			type:"post",
		  	  		url:"../member/showAll.php",
		  	  		cache:false,
		  	  		data: {},
		  	  		dataType:"json",
		  	  		success:function(data){
		  	  			var title = '<table><tr><td>序号</td><td>主键</td><td>登录名</td><td>密码</td><td>昵称</td><td>等级</td><td>操作</td></tr>';
		  	  			var context = '';
		  	  			$.each(data,function(i,item){
		  	  				if(i%2!=0){
		  	  					context += '<tr style="background-color: #E5DBE2;"><td>'+i+'</td><td>'+item.id+'</td><td>'+item.loginName+'</td><td>'+item.loginPwd+'</td><td>'+item.nickName+'</td><td>'+item.roleRank+'</td><td><a href="javascript:void(0);" onclick="del(this,'+item.id+')" class="del">删除</a></td></tr>';
		  	  				}else{
		  	  					context += '<tr><td>'+i+'</td><td>'+item.id+'</td><td>'+item.loginName+'</td><td>'+item.loginPwd+'</td><td>'+item.nickName+'</td><td>'+item.roleRank+'</td><td><a href="javascript:void(0);" onclick="del(this,'+item.id+')" class="del">删除</a></td></tr>';
		  	  				}
		  	  			});
		  	  			var display = title+context+'</table>';
		  	  			
		  	  		    $("#display").html(display);
		  		  	},
		  		  	error:function(data){
		  		  		alert("faild");
		  		  	}
		  		});
		  	},
		  	error:function(data){
		  		alert("faild");
		  	}
		});
		
	}
</script>
<style type="text/css">
	a {
		text-decoration: none;
	}
	table {
		border: 1px;
		border-style: solid;
		border-color: green;
		
		font-family: "微软雅黑";
		text-align: center;
		
		margin: 0px;
		padding: 0px;
		border-collapse: collapse;
		padding: 0px;
	}
	tr:first-child {
		background-color: #AEC7E1;
	}
	tr {
		cursor: default;
	}
	td {
		width: 100px;
		
		border: 1px;
		border-style: solid;
		border-color: green;
	}
	
</style>
</head>
<body>
	<h3>It's a test page.</h3>
	<div id="display">
		<table>
			<tr><td>序号</td><td>主键</td><td>登录名</td><td>密码</td><td>昵称</td><td>等级</td><td>操作</td></tr>
			<c:forEach var="member" items="${memberList }" varStatus="status">
			<tr <c:if test="${status.index%2!=0 }"> style="background-color: #E5DBE2;"</c:if>>
				<td><c:out value="${status.index+1 }" /></td>
				<td><c:out value="${member.id }" /></td>
				<td><c:out value="${member.loginName }" /></td>
				<td><c:out value="${member.loginPwd }" /></td>
				<td><c:out value="${member.nickName }" /></td>
				<td><c:out value="${member.roleRank }" /></td>
				<td><a href="javascript:void(0);" onclick='del(this,<c:out value="${member.id }" />);' class="del">删除</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<a href="javascript:void(0);" onclick="show();">show</a>
	<div id="show"></div>
</body>
</html>