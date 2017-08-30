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
<title>国外用户</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 1100px">
	<form id="formID" action="">
		<div class="row" style="margin-left: 0px">

			用户名字<input name="userName" id="userName"
				style="margin-left: 5px; width: 80px;" value="${userName}">
			电话号码<input name="tel" id="tel" value="${tel}"
				style="margin-left: 5px; width: 80px;">
			<button id="searchBtn" type="button"
				class="button button-small button-primary">查找</button>

			<label class="control-label checkbox"> 只显示自动付和手动付用户<input
				id="checkIsPay" type="checkbox"
				<c:if test="${isPay=='1'}">checked="checked" </c:if> /> <input
				id="isPay" name="isPay" type="hidden" value="${isPay}">
			</label>
		</div>

		<div class="row" style="margin-left: 0px">
			<input id="dateFlag" name="dateFlag" type="hidden"
				value="${dateFlag}" /> <br> 显示自动付费用户 <select name="month"
				id="month" style="width: 50px">
				<option value="01"
					<c:if test="${month=='01'}">selected="selected"</c:if>>1月</option>
				<option value="02"
					<c:if test="${month=='02'}">selected="selected"</c:if>>2月</option>
				<option value="03"
					<c:if test="${month=='03'}">selected="selected"</c:if>>3月</option>
				<option value="04"
					<c:if test="${month=='04'}">selected="selected"</c:if>>4月</option>
				<option value="05"
					<c:if test="${month=='05'}">selected="selected"</c:if>>5月</option>
				<option value="06"
					<c:if test="${month=='06'}">selected="selected"</c:if>>6月</option>
				<option value="07"
					<c:if test="${month=='07'}">selected="selected"</c:if>>7月</option>
				<option value="08"
					<c:if test="${month=='08'}">selected="selected"</c:if>>8月</option>
				<option value="09"
					<c:if test="${month=='09'}">selected="selected"</c:if>>9月</option>
				<option value="10"
					<c:if test="${month=='10'}">selected="selected"</c:if>>10月</option>
				<option value="11"
					<c:if test="${month=='11'}">selected="selected"</c:if>>11月</option>
				<option value="12"
					<c:if test="${month=='12'}">selected="selected"</c:if>>12月</option>
			</select> <select name="year" id="year" style="width: 65px">
				<option value="2017">2017年</option>
			</select> <select name="countryId" id="countryId">
				<option value="-1">全部</option>
				<c:forEach items="${countryList}" var="info" varStatus="status">
					<option value="${info.id}"
						<c:if test="${info.id==countryId}">selected="selected"</c:if>>${info.countryDesc_en}</option>
				</c:forEach>
			</select>
			<button id="submitBtn" type="button"
				class="button button-small button-primary">确定</button>

		</div>
		<hr>
		<div class="row-fluid">
			<table id="dataTable" class="table table-bordered"
				style="width: 1100px">
				<thead>
					<tr>
						<th style="width: 150px;">用户名字</th>
						<th style="width: 100px;">注册时间</th>
						<th style="width: 150px;">邮箱</th>
						<th style="width: 100px;">电话号码</th>
						<th style="width: 150px;">公司名称</th>
						<th style="width: 100px;">所在国家</th>
						<th style="width: 100px;">询问数</th>
						<th style="width: 50px;">状态</th>
						<th style="width: 100px;">credit code</th>
						<c:if test="${user.id==1}"><th style="width: 50px;">删除</th></c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="info" varStatus="status">
						<tr id="tr${info.id}">
							<td style="width: 100px; text-align: left; vertical-align: middle;">${info.userName}</td>

							<td style="width: 100px;text-align: center;"><fmt:formatDate
									value="${info.createTime}" pattern="yyyy-MM-dd" /></td>
							<td style="width: 100px; text-align: left; vertical-align: middle;">${info.email}</td>
							<td style="width: 100px; text-align: left; vertical-align: middle;">${info.tel}</td>
							<td style="width: 150px; text-align: left; vertical-align: middle;">${info.companyName}</td>
							<td style="width: 100px; text-align: left; vertical-align: middle;">${info.countryDesc_en}</td>
							<td style="width: 100px; text-align: center; vertical-align: middle;">${info.inquiryNum}</td>
							<td style="width: 50px; text-align: center; vertical-align: middle;">

								<select id="payStatus${info.id}" style="width: 50px;"
								onchange="change_payStatus(${info.pay_status},${info.id})">
									<option value="0"
										<c:if test="${info.pay_status==0}">selected="selected"</c:if>>未付</option>
									<option value="1"
										<c:if test="${info.pay_status==1}">selected="selected"</c:if>>自动付</option>
									<option value="2"
										<c:if test="${info.pay_status==2}">selected="selected"</c:if>>手动付</option>
							</select>
							</td>
							<td
								style="width: 100px; text-align: center; vertical-align: middle;">
								<c:if test="${info.credit_code!=null}">
								是
								</c:if> <c:if test="${info.credit_code==null}">
								否
								</c:if>
							</td>
							<c:if test="${user.id==1}"><td>
							<a href="javascript:del(${info.id},'${info.email}');">删除</a>
							</td>
							</c:if>

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
			$("#searchBtn").click(function() {
				$("#dateFlag").val("0");//根据用户名称和电话号码查询，时间条件不可用
				$("#formID").submit();
			});

			$("#checkIsPay").change(function() {
				$("#dateFlag").val("0");//根据用户名称和电话号码查询，时间条件不可用
				if ($("#checkIsPay").attr('checked'))
					$("#isPay").val("1");
				else
					$("#isPay").val("0");
				$("#formID").submit();
			});
			$("#submitBtn").click(function() {
				$("#dateFlag").val("1");//根据时间条件查找
				$("#isPay").val("1");
				$("#userName").val("");
				$("#tel").val("");
				$("#formID").submit();
			});
			
			function change_payStatus(pay_status,id)
			{		
				var updateStatus=$("#payStatus"+id).val();//要更新的状态
				if(pay_status!='0'||updateStatus!='2')
				{
				alert("只能将未付状态改为手动付！");
				$("#payStatus"+id).val(pay_status);
				return;
				}
				
				
				$.post("updatePayStatus",{id:id}, function(result) {				
					if ("000" == result) {
						alert("更新成功！");						
					}
					else
					{
						alert("更新失败！");	
						$("#payStatus"+id).val(pay_status);
					}
				});
			}
			
			function del(id,email)
			{
				 show(id,email);

			}
		      function show (id,email) {		    	 
		          BUI.Message.Confirm('确认删除吗？',function(){
		            setTimeout(function(){
						$.post("delInfoAndAccount",{email:email,infoId:'-1'}, function(result) {				
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
