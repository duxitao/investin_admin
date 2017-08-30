<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>未上传信息</title>
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
</script>
</head>
<body style="width: 780px">

	<form id="formID" action="notupload" method="post">
		<div class="row">
			<div class="control-group span8">
				<input name="querytext" value="${querytext}"> <input
					id="payStatus" name="payStatus" type="hidden" value="">
				<button id="searchBtn" type="button"
					class="button button-small button-primary">查找</button>
			</div>
			<div class="control-group span8">
				<label class="control-label checkbox"> 只显示已付信息<input id="checkPaid"
					type="checkbox"
					<c:if test="${payStatus=='1'}">checked="checked" </c:if> />
					
				</label>
			</div>
		</div>

		<hr>
		<div class="row-fluid">

			<table id="dataTable" class="table table-bordered"
				style="width: 780px">
				<thead>
					<tr>
						<th style="width: 30px;"></th>
						<th style="width: 100px;">发布人</th>
						<th style="width: 600px;">标题</th>
						<th style="width: 50px;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr <c:if test="${info.tel=='000000111111'}">style='background: yellow;'</c:if> >
							<td>${status.count}</td>
							<td>${info.userName}</td>
							<td style="text-align: left;">${info.title_en}</td>
							<td><a id="translate" href="javascript:void(0);"
								role="${info.id}">翻译</a></td>
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
					title : '翻译',
					elStyle : {
						width : '600px'
					},
					mask : true,
					buttons : [],
					loader : {
						url : 'translate',
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

			$(document).ready(function() {
				$("#searchBtn").click(function() {
					if ($("#checkPaid").attr('checked'))
						$("#payStatus").val("1");
					else
						$("#payStatus").val("0");
					$("#formID").submit();
				});

				$("#checkPaid").change(function() {
					if ($("#checkPaid").attr('checked'))
						$("#payStatus").val("1");
					else
						$("#payStatus").val("0");
					$("#formID").submit();
				});
			});
		</script>
	</form>
</body>
</html>
