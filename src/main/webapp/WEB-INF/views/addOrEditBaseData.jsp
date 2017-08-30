<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增基础数据</title>
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
</head>
<body>


	<form id="baseDataformID" name="baseDataformID" action="" class="form-horizontal">
	<input name="id" id="id" value="${id}" type="hidden" />
		<div class="row">
			<div class="control-group span8">
				<label class="control-label">数据类型：</label>
				<div class="controls">
					<select id="type" name="type" class="input-normal control-text">
						<option value="1" <c:if test="${baseData.type=='1'}">selected="selected"</c:if> >国家</option>
						<option value="2" <c:if test="${baseData.type=='2'}">selected="selected"</c:if> >行业</option>
						<option value="3" <c:if test="${baseData.type=='3'}">selected="selected"</c:if> >代表</option>
						<option value="4" <c:if test="${baseData.type=='4'}">selected="selected"</c:if> >邮箱后缀</option>
					</select>
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">数据代码：</label>
				<div class="controls">
					<input id="code" name="code" type="text" value="${baseData.code}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">数据名称（英）：</label>
				<div class="controls">
					<input id="name_en" name="name_en" type="text" value="${baseData.name_en}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">数据名称（汉）：</label>
				<div class="controls">
					<input id="name_zh" name="name_zh" type="text" value="${baseData.name_zh}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>
			<div id="tel_div" style="display: none;" class="control-group span8">
				<label class="control-label">Tel：</label>
				<div class="controls">
					<input id="tel" name="tel" type="text"   value="${baseData.tel}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>
			<div id="add_div"  style="display: none;" class="control-group span8">
				<label class="control-label">Add：</label>
				<div class="controls">
					<input id="add" name="add" type="text"  value="${baseData.add}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">数据名称（西）：</label>
				<div class="controls">
					<input id="name_es" name="name_es" type="text" value="${baseData.name_es}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label">排序：</label>
				<div class="controls">
					<input id="order_num" name="order_num" type="text" value="${baseData.order_num}"
						data-rules="{required:true,number : true}"
						class="input-normal control-text">
				</div>
			</div>

		</div>
		<div class="row" style="text-align: center;">
			<button id="submitBtn" type="button"
				class="button button-small button-primary">确定</button>
		</div>

	</form>
	<div id="msg" class="tips tips-small tips-warning tips-no-icon"
		style="display:none ;width: 120px; margin-top: 10px; text-align: center; margin-left: auto; margin-right: auto;">
		<div id="msgContent" class="tips-content" style="text-align: center;">警告信息警</div>
	</div>
	<script src="assets/js/jquery-1.8.1.min.js"></script>
	<script src="assets/js/sea.js"></script>
	<script src="assets/js/config-1.1.21.js"></script>
	<script type="text/javascript">
		$(".bui-dialog.bui-overlay.bui-ext-position.x-align-cc-cc").css("top",
				"50px");
		
		if($("#type").val()=="3"){
			$("#tel_div").show();
			$("#add_div").show();
			}
		else{
			$("#tel_div").hide();
			$("#add_div").hide();
			}

		$(document).ready(function() {

			$("#type").change(function() {	
				if($("#type").val()=="3"){
					$("#tel_div").show();
					$("#add_div").show();
					}
				else{
					$("#tel_div").hide();
					$("#add_div").hide();
					}
			});
			
			$("#submitBtn").click(function() {
				if ("" == $("#code").val()) {
					$('#msgContent').text("请输入数据代码！");
					$("#msg").show();
					return false;
					}
				if ("" == $("#name_en").val()|| "" == $("#name_zh").val()) {
					$('#msgContent').text("请输入中英文名称！");
					$("#msg").show();
					return false;
					}
				  if(!/^[0-9]+$/.test($("#order_num").val())){
					  $('#msgContent').text("排序处请输入数字！");
						$("#msg").show();
						return false;
				    }
				//防止重复提交
				$("#submitBtn").attr("disabled", "disabled");
				$.post("submitAddOrEditBaseData",
						$("#baseDataformID").serialize(),
						function(data) {
							if (data == "000") {
								location.href = "getBaseData";
								}else {
								$("#submitBtn").removeAttr("disabled"); 
								$('#msgContent').text("系统异常");
								$("#msg").show();
							}
						});
				});
				});
	</script>
</body>
</html>