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
<title>已上传信息</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 885px">
	<form id="formID" action="">
		<div class="row">
			<div class="control-group span6">
				<input name="querytext" value="${querytext}">
				<button id="searchBtn" type="button"
					class="button button-small button-primary">查找</button>
			</div>
			<div class="control-group span8">
				<label class="control-label checkbox"> 只显示询问为0 <input
					id="checkinquiry" type="checkbox"
					<c:if test="${inquiryStatus=='1'}">checked="checked" </c:if> />
					<input id="inquiryStatus" name="inquiryStatus"
					type="hidden">
				</label>
			</div>
		</div>
		<hr>
		<div class="row-fluid">
			<table id="dataTable" class="table table-bordered"
				style="width: 885px">
				<thead>
					<tr>
						<th colspan="2" style="width: 610px;">标题</th>
						<th style="width: 80px;">描述</th>
						<th style="width: 80px;">上传时间</th>
						<th style="width: 80px;">询问次数</th>
						<c:if test="${user.id==1}">
						<th style="width: 50px;">删除</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr id="tr${info.id}" <c:if test="${info.tel=='000000111111'}">style='background: yellow;'</c:if>>
							<td
								style="width: 30px; text-align: center; vertical-align: middle;">${status.count}</td>

							<td style="width: 580px; padding: 0">
								<table style="width: 100%;">
									<tr>
										<td style="border-left: none; width: 30px;">英文</td>
										<td style="width: 450px; text-align: left;">${info.title_en}</td>
									</tr>
									<tr>
										<td style="border-left: none; width: 30px;">中文</td>
										<td style="width: 450px; text-align: left;">${info.title_cn}</td>
									</tr>
								</table>
							</td>
							<td style="text-align: center; vertical-align: middle;"><a id="translate" href="javascript:void(0);"
								role="${info.id}">查看</a></td>
							<td style="text-align: center; vertical-align: middle;"><fmt:formatDate
									value="${info.dateConfirmed}" pattern="yyyy-MM-dd" /></td>
							<td style="text-align: center; vertical-align: middle;">${info.inquiryNum}</td>
							<c:if test="${user.id==1}"><td style="text-align: center; vertical-align: middle;"><a href="javascript:del(${info.id},'${info.email}');">删除</a></td></c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script src="assets/js/jquery-1.8.1.min.js"></script>
		<script src="assets/js/sea.js"></script>
		<script src="assets/js/config-1.1.21.js"></script>
		<script src="assets/js/bui-min.js"></script>
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
					submitFuc();
				});

				$("#checkinquiry").change(function() {
					submitFuc();
				});
			});

			function submitFuc() {
				if ($("#checkinquiry").attr('checked'))
					$("#inquiryStatus").val("1");
				else
					$("#inquiryStatus").val("0");
				$("#formID").submit();
			}
			function del(id,email)
			{
				 show(id,email);

			}
		      function show (id,email) {		    	 
		          BUI.Message.Confirm('确认删除吗？',function(){
		            setTimeout(function(){
						$.post("delInfoAndAccount",{email:email,infoId:id}, function(result) {				
							if ("000" == result) {
								$("#tr"+id).hide();				
							}
							else{
								alert("删除失败！");							
							}
						});
		            });
		            
		          },'warning');
		        }
		</script>
	</form>

</body>
</html>
