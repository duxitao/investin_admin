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
<title>requestCall</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 835px">
	<form id="formID" action="">

		<input name="id" id="id" type="hidden">
		<div class="row" style="margin-left: 0px">


			<label class="control-label checkbox">只显示新<input
				id="checkIsNew" type="checkbox"
				<c:if test="${newFlag=='1'}">checked="checked" </c:if> /> <input
				id="newFlag" name="newFlag" type="hidden" value="${newFlag}">
			</label> <select name="countryId" id="countryId">
				<option value="-1">全部</option>
				<c:forEach items="${countryList}" var="info" varStatus="status">
					<option value="${info.id}"
						<c:if test="${info.id==countryId}">selected="selected"</c:if>>${info.countryDesc_en}</option>
				</c:forEach>
			</select>

			<button id="submitBtn" type="submit"
				class="button button-small button-primary">确定</button>

			<button id="callBtn" type="button"
				class="button button-small button-primary">call</button>
		</div>

		<hr>
		<div class="row-fluid">
			<table id="dataTable" class="table table-bordered"
				style="width: 700px">
				<thead>
					<tr>
						<th style="width: 100px;">用户名字</th>
						<th style="width: 100px;">注册时间</th>
						<th style="width: 100px;">电话号码</th>
						<th style="width: 150px;">公司名称</th>
						<th style="width: 100px;">所在国家</th>
						<th style="width: 100px;">确认电话</th>
						<th style="width: 50px;">状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr class="tr" id="selectedRow${info.id}"
							onclick="selectedRow(${info.id})">
							<td
								style="width: 100px; text-align: left; vertical-align: middle;">${info.userName}</td>

							<td style="text-align: center;"><fmt:formatDate
									value="${info.createTime}" pattern="yyyy-MM-dd" /></td>
							<td
								style="width: 100px; text-align: left; vertical-align: middle;">${info.tel}</td>
							<td
								style="width: 150px; text-align: left; vertical-align: middle;">${info.companyName}</td>
							<td
								style="width: 100px; text-align: left; vertical-align: middle;">${info.countryDesc_en}</td>
							<td
								style="width: 100px; text-align: left; vertical-align: middle;">${info.request_tel}</td>

							<td id="requestStatus${info.id}"
								style="width: 50px; text-align: center; vertical-align: middle;">
								<c:if test="${info.request_status=='1'}">
								新
								</c:if>

							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script src="assets/js/jquery-1.8.1.min.js"></script>
		<script src="assets/js/sea.js"></script>
		<script src="assets/js/config-1.1.21.js"></script>
		<%@ include file="pageBar.jsp"%>
		<script type="text/javascript">

			$("#checkIsNew").change(function() {
				
				if ($("#checkIsNew").attr('checked'))
					$("#newFlag").val("1");
				else
					$("#newFlag").val("0");
				$("#formID").submit();
			});


			function selectedRow(id) {
				$(".tr").css("background-color","#ffffff");
				$("#selectedRow" + id).css("background-color","#f4f4f4");
				$("#id").val(id);	
			}
			$("#callBtn").click(function() {
				var id=$("#id").val();			
				$.post("updateRequestStatus", {
					id : id
				}, function(result) {
					if ("000" == result) {
						$("#requestStatus" + id).html("");
						
					} else {
						alert("更新失败！");						
					}
				});
			});
			
			
		</script>
	</form>

</body>
</html>
