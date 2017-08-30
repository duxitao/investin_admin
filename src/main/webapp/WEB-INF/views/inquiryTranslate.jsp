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
		<input id="id" name="id" type="hidden" value="${info.id}"> <input
			id="status" name="status" type="hidden" value="${info.status}">
		<table id="dataTable" class="table table-bordered"
			style="width: 100%; margin: 0; table-layout: fixed;word-wrap:break-word;">

			<tr>
				<td style="width: 50%; text-align: left;" colspan="2">群发数：${email_group_num}</td>
			</tr>
			<tr>
				<td style="width: 50%; text-align: left;">${info.content}</td>
				<td style="width: 50%; text-align: left; padding: 0;"><textarea
						name="content_en" id="content_en"
						data-rules="{required : true}"
						data-messages="{required:'描述必须翻译才能提交'}"
						style="border: none; width: 100%; padding: 0; resize: none;">${info.content_en}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 0">
					<table style="width: 100%; border: none;">
						<tr>
							<td
								style="width: 15%; border-left: none; vertical-align: middle;"
								rowspan="3">附加信息</td>
							<td style="width: 85%; text-align: left; padding: 0"><input
								id="requires1_en" name="requires1_en"
								style="width: 100%; border: none;" value="${info.requires1_en}" /></td>
						</tr>
						<tr>
							<td style="width: 85%; text-align: left; padding: 0"><input
								id="requires2_en" name="requires2_en"
								style="width: 100%; border: none" value="${info.requires2_en}" /></td>
						</tr>
						<tr>
							<td style="width: 85%; text-align: left; padding: 0"><input
								id="requires3_en" name="requires3_en"
								style="width: 100%; border: none" value="${info.requires3_en}" /></td>
						</tr>
					</table>
				</td>

			</tr>
			<tr>
				<td colspan="2"><input type="button" id="btn_confirm"
					name="btn_confirm" class="button button-small" disabled="disabled"
					value="确认"> <input type="button" id="btn_confirm2"
					name="btn_confirm2" class="button button-small" disabled="disabled"
					value="再次确认" style="margin-left: 100px; margin-right: 100px">

					<input type="button" id="btn_upload" name="btn_upload"
					class="button button-small" disabled="disabled" value="发送"></td>
			</tr>
		</table>
		<div id="msg" class="tips tips-small tips-warning tips-no-icon"
			style="display: none;">
			<div id="msgContent" class="tips-content" style="text-align: center;">警告信息警</div>
		</div>
	</form>
	<script type="text/javascript">
		$(".bui-dialog.bui-overlay.bui-ext-position.x-align-cc-cc").css("top",
				"50px");
		$("textarea").each(function() {
			$(this).height($(this).parent().height());
		});

		var status = $("#status").val();

			
			if (status == '0')
				$("#btn_confirm").removeAttr("disabled").addClass(
						"button-primary");
			if (status == '1')
				$("#btn_confirm2").removeAttr("disabled").addClass(
						"button-primary");
			if (status == '2')
				$("#btn_upload").removeAttr("disabled").addClass(
						"button-primary");


		$("#btn_confirm").on('click', function(e) {
			translateSubmit();
		});
		$("#btn_confirm2").on('click', function(e) {
			translateSubmit();
		});
		$("#btn_upload").on('click', function(e) {
			translateSubmit();
		});

		function translateSubmit() {
			$("#msg").hide();
			if ("" == $("#content_en").val()) {
				$('#msgContent').text("请输入翻译内容");
				$("#msg").show();
				return false;
			}

			$.post("inquiryTranslateSubmit",$("#translateForm").serialize(),
							function(data) {
								if (data == "000") {
									var status = $("#status").val();

									if ("0" == status) {
										$("#btn_confirm").attr("disabled","disabled").removeClass("button-primary");
										$("#btn_confirm2").removeAttr("disabled").addClass("button-primary");
										$("#status").val("1");
									}
									if ("1" == status) {
										$("#btn_confirm2").attr("disabled","disabled").removeClass("button-primary");
										$("#btn_upload").removeAttr("disabled").addClass("button-primary");
										$("#status").val("2");
									}
									if ("2" == status) {
										$("#btn_upload").attr("disabled","disabled").removeClass("button-primary");
										$("#status").val("3");
									}

								} else if (data == "001") {
									$('#msgContent').html("登录超时，请<a href='login' target='_blank'>点击此处</a>重新登录，登录后重新提交！");
									$("#msg").show();
								} else if (data == "002") {
									$('#msgContent').text("请输入翻译内容");
									$("#msg").show();
								} else {
									$('#msgContent').text("系统异常");
									$("#msg").show();
								}
							});
		}
	</script>
</body>
</html>