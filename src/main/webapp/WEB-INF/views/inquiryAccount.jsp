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
<title>国内用户</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 835px">
	<form id="formID" action="">
		<div class="row">
			<div class="control-group span12">
				用户邮箱<input name="emailtext" style="margin-left: 5px;margin-right: 20px" value="${emailtext}"> 
				
				电话号码<input
					name="tel" value="${tel}"  style="margin-left: 5px;">
				<button id="searchBtn" type="submit"
					class="button button-small button-primary">查找</button>
			</div>
		</div>
		<hr>
		<div class="row-fluid">
			<table id="dataTable" class="table table-bordered"
				style="width: 600px">
				<thead>
					<tr>
						<th style="width: 400px;">用户邮箱</th>
						<th style="width: 200px;">注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr>
							<td
								style="width: 30px; text-align: left; vertical-align: middle;">${info.email}</td>

							<td style="text-align: center;"><fmt:formatDate
									value="${info.reg_time}" pattern="yyyy-MM-dd" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script src="assets/js/jquery-1.8.1.min.js"></script>
		<script src="assets/js/sea.js"></script>
		<script src="assets/js/config-1.1.21.js"></script>
		<%@ include file="pageBar.jsp"%>	
	</form>

</body>
</html>
