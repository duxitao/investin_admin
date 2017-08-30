<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Investin后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/bs3/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bs3/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="header">
		<div class="dl-title">
			<span class="">Investin后台管理系统</span>
		</div>
		<form id="form1">
			<div class="dl-log">
				当前登录为<span id="txt_userName" class="dl-log-user">账号${user.id}</span>
				<input type="button" id="exittBtn" class="button button-mini button-danger"
					value="退出系统">
			</div>
		</form>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected">
					<div class="nav-item-inner  nav-home">首页</div>
				</li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/config.js"></script>
	<script type="text/javascript">
	$("#exittBtn").click(function() {		
		location.href = "exit";
	});
		BUI.use('common/main', function() {
			var config = [
				
				<c:if test="${user.id!=3}">
				
				{
				id : 'menu',
				homePage : '1',
				menu : [ {
					text : '信息管理',
					items : [ {
						id : '1',
						text : '已上传信息',
						href : 'uploaded'
					}, {
						id : '2',
						text : '未上传信息',
						href : 'notupload'
					}, {
						id : '3',
						text : '询问审核',
						href : 'inquiry'
					} 
					, {
						id : '10',
						text : '询问审核（大型）',
						href : 'inquiry_large'
					}]
				}, {
					text : '会员管理',
					items : [ {
		
							id : '4',
							text : '询问用户',
							href : 'inquiryAccount'
					}, {
						id : '5',
						text : '国外用户',
						href : 'account'
					} 
					<c:if test="${user.id==1}">
					, {
						id : '9',
						text : 'Request a call',
						href : 'requestCall'
					}
					</c:if>
					<c:if test="${user.id==1}">
					, {
						id : '11',
						text : 'contact us',
						href : 'contactInfo'
					}
					</c:if>
					]
				}, {
					text : '操作工具',
					items : [ 
					<c:if test="${user.id==1}">
						{
						id : '6',
						text : '信息管理/测试上传',
						href : 'setting'
					}, 
					{
						id : '12',
						text : '新用户通知',
						href : 'setReceiveEmail'
					}, 
					</c:if>
					{
						id : '7',
						text : '修改密码',
						href : 'password'
					}
					<c:if test="${user.id==1}">
					, 
					{
						id : '8',
						text : '基础数据管理',
						href : 'getBaseData'
					} 
					</c:if>
					]
				} ]
			} 
			</c:if>	
				
				
				];

			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>