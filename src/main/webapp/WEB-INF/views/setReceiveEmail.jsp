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
<title>邮箱设置</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 835px">
	<form id="formID" class="form-horizontal" method="post">
		<h3>测试上传</h3>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">选中国家：</label>
				<div class="controls">
					<select id="countryId" name="countryId"
						onchange="setReceiveEmail(this.value)">
						<option value="0">请选中国家……</option>
						<c:forEach items="${countryList}" var="info">
							<option value="${info.id}">${info.countryDesc_en}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">收件邮箱：</label>
				<div class="controls">
					<input id="receive_email" name="receive_email" type="text"
						style="width: 200px" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">是否启用：</label>
				<div class="controls">
					<select id="status" name="status">
						<option value="0">停用</option>
						<option value="1">启用</option>
					</select>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">&nbsp;</label>
				<div class="controls">
					<button id="submitBtn" type="button"
						class="button button-small button-primary">提交</button>
					<div id="msg" class="tips tips-small tips-warning tips-no-icon"
						style="display: none; width: 130px; margin-top: 10px; text-align: center; margin-left: auto; margin-right: auto;">
						<div id="msgContent" class="tips-content"
							style="text-align: center;">警告信息警</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="flag">
	</form>
</body>

<script src="assets/js/jquery-1.8.1.min.js"></script>
<script src="assets/js/sea.js"></script>
<script src="assets/js/config-1.1.21.js"></script>
<script type="text/javascript">
	function setReceiveEmail(id) {
		if ("0" == id) {
			$("#receive_email").val("");
			$("#status").val("0");
			return;
		}
		$.post("getReceiveEmail", {
			countryId : id
		}, function(data) {
			var flag = data.substring(0, 1);
			var email = data.substring(2);

			if ("2" == flag) {
				email = "";
				$("#flag").val("2");
				$("#status").val("0");
			}
			$("#receive_email").val(email);
			$("#status").val(flag);
			$("#flag").val(flag);
		});
	}
	$("#submitBtn").on('click', function(e) {

		var flag = $("#flag").val();
		var method = "updateReceiveEmail";
		if (flag == "2") {
			//新增
			method = "addReceiveEmail";
		}

		$.post(method, $("#formID").serialize(), function(data, status) {
			if (data == "000") {
				if(method == "addReceiveEmail")
					$("#flag").val("0");
				alert("操作成功");
			}else {
				alert("操作失败1");
			}
		});

	});
</script>
</html>
