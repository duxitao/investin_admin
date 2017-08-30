<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>翻译</title>
</head>
<body>
	<form id="translateForm" action="translate">
		
		<table id="dataTable" class="table table-bordered"
			style="width: 100%; margin: 0; table-layout: fixed;word-wrap:break-word;">


			<tr>
				<td style="width: 40%; text-align: left;">${info.title_cn}</td>
				<td style="width: 60%; text-align: left;">${info.description_cn}</td> 
			</tr>
			<tr>
				<td colspan="2" style="padding: 0;table-layout: fixed;word-wrap:break-word;">
					<table class="table table-bordered" style="width: 100%; border: none;margin: 0">
						<tr>
							<td	style="width: 15%;">留言</td>
							<td	style="width: 85%;">${info.content}</td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>