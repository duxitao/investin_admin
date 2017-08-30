<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>询问审核</title>
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
</script>
</head>
<body style="width: 780px;">

	<form id="formID" action="inquiry_large" method="post">
		<div class="row-fluid">

			<table id="dataTable" class="table table-bordered"
				style="width: 780px">
				<thead>
					<tr>
						<th style="width: 100px;">询问人邮件</th>
						<th style="width: 580px;">被询问信息的标题（title）</th>
						<th style="width: 100px;">询问内容翻译</th>					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr>
							<td>${info.inquiry_email}</td>
							<td style="text-align: left;">${info.title_en}</td>
							<td><a id="translate" href="javascript:void(0);"
								role="${info.id}">查看</a><c:if test="${info.status!='4'}">（新）</c:if></td>
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
			BUI.use([ 'bui/overlay', 'bui/mask' ], function(Overlay) {
				var dialog = new Overlay.Dialog({
					title : '查看',
					elStyle : {
						width : '600px'
					},
					mask : true,
					buttons : [],
					loader : {
						url : 'inquiryView',
						autoLoad : false,
						loadMask : {
							msg : '正在加载，请稍后。。'
						},
						lazyLoad : false,
						params : {
							id : 'id'
						}
					}
				});
				$('a#translate').on('click', function() {
					dialog.show();
					dialog.get('loader').load({
						id : $(this).attr("role")
					});

				});
			});
			window.onload = function() {
				/*
				$(window.parent.document).find("#large_project").load(function () {
				    var main = $(window.parent.document).find("#large_project");
				    var thisheight = $(document).height();
				    main.height(thisheight);
				});*/
			};
		</script>
	</form>
</body>
</html>
