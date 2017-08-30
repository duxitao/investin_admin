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
<body style="width: 880px">

	<p>
		<span class="label label-info">大型项目</span>
	</p>
	<iframe id="small_project" onload="changeFrameHeight()" frameborder="0"
		name="Iframe1" src="inquiry_large" width="100%" height="445">
		您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。 </iframe>
	<hr>
	<p>
		<span class="label label-info">小型项目</span>
	</p>
	<iframe id="large_project" onload="changeFrameHeight()" frameborder="0"
		name="Iframe1" src="inquiry" width="100%" height="445">
		您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。 </iframe>

</body>
<script type="text/javascript">

</script>
</html>
