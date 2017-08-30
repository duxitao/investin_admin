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
<title>contactInfo</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 900px">
	<form id="formID" action="contactInfo">

		<input name="id" id="id" type="hidden">
		<div class="row" style="margin-left: 0px">
			国家 <select name="countryId" id="countryId">
				<option value="-1">全部</option>
				<c:forEach items="${countryList}" var="info" varStatus="status">
					<option value="${info.id}"
						<c:if test="${info.id==countryId}">selected="selected"</c:if>>${info.countryDesc_en}</option>
				</c:forEach>
			</select>

			<button id="submitBtn" type="submit"
				class="button button-small button-primary">确定</button>

			<button id="excelBtn" type="button"
				class="button button-small button-primary">excel下载</button>
		</div>

		<hr>
		<div class="row-fluid">
			<table id="dataTable" class="table table-bordered"
				style="width: 900px">
				<thead>
					<tr>
						<th style="width: 50px;"><input id="selectAll" type="checkbox" /> </th>
						<th style="width: 100px;">country</th>
						<th style="width: 100px;">name</th>
						<th style="width: 150px;">phone</th>
						<th style="width: 100px;">email</th>
						<th style="width: 300px;">subject</th>
						<th style="width: 50px;">message</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr >
							<td><input alt="checkbox" id="${info.id}" value="${info.id}" type="checkbox" /></td>
							<td style="text-align: left;">${info.countryName}</td>
							<td style="text-align: left;">${info.name}</td>
							<td style="text-align: left;">${info.tel}</td>
							<td style="text-align: left;">${info.email}</td>
							<td style="text-align: left;">${info.subject}</td>
							<td style="text-align: center;"><a id="view" href="javascript:void(0);" role="${info.id}">查看</a> </td>
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
					title : 'message',
					elStyle : {
						width : '600px'
					},
					mask : true,
					buttons : [],
					loader : {
						url : 'viewContactInfo',
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
				$('a#view').on('click', function() {
					dialog.show();
					dialog.get('loader').load({
						id : $(this).attr("role")
					});

				});
			});
			

			$("#selectAll").change(function() {
				
				if ($("#selectAll").attr('checked'))
					 $("[alt='checkbox']").attr("checked", true);
				else
					$("[alt='checkbox']").attr("checked", false);
			});

			$("#excelBtn").click(function() {				
				var id="";
				$("[alt='checkbox']").each(function(i){
				    if($(this).attr("checked")) {
				    	id=id+","+$(this).val()
				   }
				});
				
				if(id=="")
					{
					alert("请选择要导出的内容！");
					return;
					}
				//去除多余的逗号
				id=id.substring(1);							
				window.open("exportContactInfo?IdList="+id); 
			});
			
			
		</script>
	</form>

</body>
</html>
