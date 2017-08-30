<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-Disposition", "inline; filename=message.xls");
%>
<!DOCTYPE html>
<html>
<head>
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form id="formID" action="contactInfo">

		<div class="row-fluid">
			<table border="1">
				<thead>
					<tr>
						<th style="width: 200px;">country</th>
						<th style="width: 200px;">name</th>
						<th style="width: 200px;">phone</th>
						<th style="width: 300px;">email</th>
						<th style="width: 400px;">subject</th>
						<th style="width: 500px;">message</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr>
							<td style="text-align: left;">${info.countryName}</td>
							<td style="text-align: left;">${info.name}</td>
							<td style="text-align: left;">${info.tel}</td>
							<td style="text-align: left;">${info.email}</td>
							<td style="text-align: left;">${info.subject}</td>
							<td style="text-align: left;">${info.message}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>
