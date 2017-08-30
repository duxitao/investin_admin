<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
<link href="assets/css/dpl-min.css" rel="stylesheet">
<link href="assets/css/bui/bui.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<div class="row-fluid">
			<h1>后台管理系统登录</h1>
		</div>
	</div>
	<div class="content">
		<form id="formID" class="form-horizontal well">

			<div class="row-fluid">
				<div class="control-group">
					<label class="control-label">账号：</label>
					<div class="controls">
						<select id="id" name="id">
							<c:forEach items="${userList}" var="user">
								<option value="${user.id }">账号${user.id }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>


			<div class="row-fluid">
				<div class="control-group">
					<label class="control-label">密码：</label>
					<div class="controls">
						<input type="password" name="pwd" id="pwd" class="control-text">
					</div>
				</div>

			</div>
			<div class="row-fluid">
				<div id="msg" class="tips tips-small tips-warning tips-no-icon">
					<div class="tips-content">警告信息警</div>
				</div>
				<button id="submitBtn" type="button" class="button button-primary">登录</button>
			</div>

		</form>
	</div>
	<script src="assets/js/jquery-1.8.1.min.js"></script>

	<script type="text/javascript">
		$("#submitBtn").on(
				'click',
				function(e) {
					var msg = "";
					if ($("#pwd").val() == "") {
						msg += "密码不能为空";
					}
					if (msg != "") {
						$('#msg').text(msg);
						$('#msg').show();
						return;
					} else
						$('#info').hide();

					$.post("loginSubmit", $("#formID").serialize(), function(
							data, status) {
						if (data == "000") {
							$('#msg').hide();
							location.href = "index.html";
						} else if (data == "001") {
							$('#msg').text("用户名或密码错误！");
							$('#msg').show();
						} else {
							$('#msg').text("系统异常！");
							$('#msg').show();
						}
					});

				});
	</script>
</body>
</html>