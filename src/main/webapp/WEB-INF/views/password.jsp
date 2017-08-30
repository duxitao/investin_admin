<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>密码修改</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 835px">
	<form id="formID" class="form-horizontal" method="post"
		action="addUser">
		<h3>修改密码</h3>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">旧密码：</label>
				<div class="controls">
					<input id="old_password" name="old_password" type="password" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">新密码：</label>
				<div class="controls">
					<input id="new_password1" name="new_password1" type="password" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">确认信密码：</label>
				<div class="controls">
					<input id="new_password2" name="new_password2" type="password" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">&nbsp;</label>
				<div class="controls" style="height: 100px">
					<button id="updatetBtn" type="button"
						class="button button-small button-primary">提交</button>
					<div id="msg" class="tips tips-small tips-warning tips-no-icon"
						style="display: none; width: 130px; margin-top: 10px; text-align: center; margin-left: auto; margin-right: auto;">
						<div id="msgContent" class="tips-content"
							style="text-align: center;">警告信息警</div>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${user.id==1}">
			<hr />
			<h3>账号管理</h3>

			<div class="row">
				<div class="control-group span24">
					<label class="control-label" style="width: 150px;"><button
							id="addUserBtn" type="submit"
							class="button button-small button-primary">增加账号</button></label>
					<div class="controls"></div>
				</div>
			</div>

			<c:forEach items="${userList}" var="user">
				<c:if test="${user.id!=1}">
					<div class="row">
						<div class="control-group span24">
							<label class="control-label" style="width: 150px;">账号${user.id}密码：</label>
							<div class="controls">${user.pwd}</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
	</form>
</body>

<script src="assets/js/jquery-1.8.1.min.js"></script>
<script src="assets/js/sea.js"></script>
<script src="assets/js/config-1.1.21.js"></script>
<script type="text/javascript">
	$("#updatetBtn").click(function() {

		var old_pwd = $("#old_password").val();
		var new_pwd = $("#new_password1").val();
		var new_pwd1 = $("#new_password2").val();

		if (old_pwd == "" || new_pwd == "" || new_pwd1 == "") {
			$('#msgContent').text("密码填写不完整！");
			$("#msg").show();
			$('#msg').delay(2000).hide(0);
			return;
		}
		if (new_pwd != new_pwd1) {
			$('#msgContent').text("新密码两次输入不一致！");
			$("#msg").show();
			$('#msg').delay(2000).hide(0);
			return;
		}

		$.post("updatePwd", {
			old_pwd : old_pwd,
			new_pwd : new_pwd
		}, function(data) {
			if (data == "000") {
				$('#msgContent').text("密码修改成功！");
				$("#msg").show();
				$('#msg').delay(2000).hide(0);
			} else if (data == "001") {
				$('#msgContent').text("请重新登录后再试！");
				$("#msg").show();
				$('#msg').delay(2000).hide(0);
			} else if (data == "002") {
				$('#msgContent').text("旧密码不正确！");
				$("#msg").show();
				$('#msg').delay(2000).hide(0);
			} else {
				$('#msgContent').text("系统异常，稍后再试！");
				$("#msg").show();
				$('#msg').delay(2000).hide(0);
			}
		});

	});
</script>
</html>
