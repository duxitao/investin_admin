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
<title>系统参数设置</title>
<base href="<%=basePath%>" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body style="width: 835px">
	<form id="J_Form" class="form-horizontal" method="post">
		<h3>信息管理</h3>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">常规排列每日更新时间：</label>
				<div class="controls">
					<select name="update_frequency" id="update_frequency"
						class="control-text">
						<option value="1">12:00；24:00</option>
						<option value="2">24:00</option>
					</select>
							<div id="msg2" class="tips tips-small tips-warning tips-no-icon"
							style="display:none; width: 130px; margin-top: 10px; text-align: left; margin-left: 0; margin-right: auto;">
							<div id="msgContent2" class="tips-content" style="text-align: center;">警告信息警</div>
						</div>
					
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">群发设置：</label>
				<div class="controls" style="height: 100px;">
					<div class="row" style="margin: 0">
						<input name="option_group" id="option_group" value="0" type="radio"  <c:if test="${email_group_num=='0'}">checked="checked"</c:if>    />全部
					</div>
					<div class="row" style="margin: 0">
						<input name="option_group" id="option_group" value="" type="radio" <c:if test="${email_group_num!='0'}">checked="checked"</c:if> />自定义
					</div>
					<div class="row" style="margin: 0">					
						<input type="text" name="group_num" id="group_num" value="${email_group_num}"/>
						<button id="okBtn" type="button"
							class="button button-small button-primary">修改</button>
					</div>
					<div class="row" style="margin: 0">
						
						<div id="msg1" class="tips tips-small tips-warning tips-no-icon"
							style="display:none; width: 130px; margin-top: 10px; text-align: left; margin-left: 0; margin-right: auto;">
							<div id="msgContent1" class="tips-content" style="text-align: center;">警告信息警</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>

		<hr />
		<h3>测试上传</h3>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">输入标题：</label>
				<div class="controls">
					<input id="title_en" name="title_en" type="text"
						style="width: 500px" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">输入内容：</label>
				<div class="controls" style="height: 200px;">
					<textarea id="description_en" name="description_en"
						maxlength="1000" rows="3" style="width: 500px; height: 180px;"></textarea>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">选择金额：</label>
				<div class="controls">
					<select id="scaleId" name="scaleId">
						<c:forEach items="${listScale}" var="scale">
							<option value="${scale.id}">${scale.scaleDesc_en}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">选择类别：</label>
				<div class="controls">
					<select id="fieldId" name="fieldId">
						<c:forEach items="${listField}" var="field">
							<option value="${field.id}">${field.fieldDesc_en}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
				<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">选择国家：</label>
				<div class="controls">
					<select id="countryId" name="countryId">
						<c:forEach items="${listCountry}" var="country">
							<option value="${country.id}">${country.countryDesc_en}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">大型项目：</label>
				<div class="controls">
					<input id="is_large" type="checkbox">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span24">
				<label class="control-label" style="width: 150px;">&nbsp;</label>
				<div class="controls">
					<button id="submitBtn" type="button"
						class="button button-small button-primary">提交</button>
								<div id="msg" class="tips tips-small tips-warning tips-no-icon"
		style="display:none ;width: 130px; margin-top: 10px; text-align: center; margin-left: auto; margin-right: auto;">
		<div id="msgContent" class="tips-content" style="text-align: center;">警告信息警</div>
	</div>
				</div>
			</div>
		</div>

	</form>
</body>

<script src="assets/js/jquery-1.8.1.min.js"></script>
<script src="assets/js/sea.js"></script>
<script src="assets/js/config-1.1.21.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#update_frequency").val('${update_frequency}');
	
	var email_group_num=${email_group_num};
	if(email_group_num==0)
		$("#group_num").val("");
	
	
	
	$("#update_frequency").change(function() {	
		$.post("updateSetting", {key:"update_frequency",value:$("#update_frequency").val()}, function(data) {
			if (data == "000") {
				$('#msgContent2').text("更新排序时间成功！");
				$("#msg2").show();
				$('#msg2').delay(2000).hide(0);
			} else {
				$('#msgContent2').text("更新排序时间异常！");
				$("#msg2").show();
				$('#msg2').delay(2000).hide(0);
			}
		});
	});
	
	$("#okBtn").click(function(){
		
		var update_frequency=$("#update_frequency").val();	
		var email_group_num=0;
		if ($('input:radio[name=option_group]:checked').val() == "")
		{
		  if(!/^[0-9]+$/.test($("#group_num").val())){
			  $('#msgContent1').text("群发数为数字！");
				$("#msg1").show();
				return false;
		 }else
			 email_group_num=$("#group_num").val();
		}
		$.post("updateSetting", {key:"email_group_num",value:email_group_num}, function(data) {
				if (data == "000") {
					$('#msgContent1').text("更新群发数成功！");
					$("#msg1").show();
					$('#msg1').delay(2000).hide(0);
					if(email_group_num==0)
					$("#group_num").val("");
				} else {
					$('#msgContent1').text("更新群发数异常！");
					$("#msg1").show();
					$('#msg1').delay(2000).hide(0);
				}
			});

	});
	
	$("#submitBtn").click(function() {
		$("#msg").hide();
		if ("" == $("#title_en").val()|| "" == $("#description_en").val()) {
								$('#msgContent').text("标题和内容不能为空！");
								$("#msg").show();
								return false;
		}
		var title_en=$("#title_en").val();
		var description_en=$("#description_en").val();
		var scaleId=$("#scaleId").val();
		var fieldId=$("#fieldId").val();
		var countryId=$("#countryId").val();
		var is_large='0';
		
		if ($("#is_large").attr('checked'))
			is_large='1';
							
							$.post("createTestInfo", {title_en:title_en,description_en:description_en,scaleId:scaleId,fieldId:fieldId,countryId:countryId,is_large:is_large}, function(data) {
								if (data == "000") {
									$('#msgContent').text("提交测试数据成功！");
									$("#msg").show();
									$('#msg').delay(2000).hide(0);
								} else {
									$('#msgContent').text("系统异常");
									$("#msg").show();
									$('#msg').delay(2000).hide(0);
								}
							});
						});
			});
</script>
</html>
