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
<title>基础数据关联</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 835px ;margin: 20px 10px;" >
	<form id="formID" action="">
		<div class="row" style="margin-bottom: 10px;">
			<div class="control-group span6">

				<button id="addBtn" type="button"
					class="button button-small button-primary">增加基础数据</button>
			</div>
		</div>

		<div class="row-fluid">
			<table id="dataTable" class="table table-bordered"
				style="width: 930px">
				<thead>
					<tr>
						<th style="width: 80px;">数据类型</th>
						<th style="width: 80px;">数据代码</th>
						<th style="width: 200px;">数据名称（英）</th>
						<th style="width: 200px;">数据名称（西班牙）</th>
						<th style="width: 150px;">数据名称（汉）</th>						
						<th style="width: 60px;">排序</th>
						<th style="width: 100px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${baseDataList}" var="info" varStatus="status">
						<tr>
							<td style="text-align: center;">
							<c:if test="${info.type=='1'}">
								国家
							</c:if> 
							<c:if test="${info.type=='2'}">
								行业
							</c:if> 
							<c:if test="${info.type=='3'}">
								代表
							</c:if>
							<c:if test="${info.type=='4'}">
								邮箱后缀
							</c:if>		
							</td>
							<td style="text-align: center;">${info.code}</td>
							<td style="text-align: left;">${info.name_en}</td>
							<td style="text-align: left;">${info.name_es}</td>
							<td style="text-align: left;">${info.name_zh}</td>
							<td style="text-align: center;">${info.order_num}</td>



							<td style="text-align: center;"><a id="edit"
								href="javascript:void(0);" role="${info.id}" style="margin-right: 20px;">编辑</a><a
								id="del" href="javascript:void(0);" role="${info.id}">删除</a></td>

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
					title : 'title',
					elStyle : {
						width : '700px'
					},
					mask : true,
					buttons : [],
					loader : {
						url : 'addOrEditBaseData',
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
				$('#addBtn').on('click', function() {
					dialog.show();
					dialog.get('loader').load({
						id : "-1"
					});
					$('.header-title').html("添加基础数据");
					

				});
				
				$('a#edit').on('click', function() {
					dialog.show();
					dialog.get('loader').load({
						id : $(this).attr("role")						
					});
					$('.header-title').html("修改基础数据");
				});
				$('a#del').on('click', function() {
					 BUI.Message.Alert('基础数据不能随意删除，否则会影响数据关联！','error');
					
					

				});
			});

		</script>
	</form>

</body>
</html>
