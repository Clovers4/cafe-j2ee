<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/CAPTCHA.css">
<script src="${pageContext.request.contextPath}/js/verify.js"></script>


<style>
body {
	padding-top: 48px;
}
</style>

<title>header</title>
</head>
<body>
	<!-- 页头 -->
	<!--响应式导航栏-->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<a href="${pageContext.request.contextPath}/index.jsp"
				class="navbar-brand"><img alt="Brand"
				style="max-width: 200px; margin-top: -40px;"
				src="${pageContext.request.contextPath}/images/header/logo.png"></a>
			</a>
		</div>

		<div class="collapse navbar-collapse navbar-left"
			id="example-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/index.jsp"><span
						class="glyphicon glyphicon-home"></span>主页</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span
						class="glyphicon glyphicon-th-list"></span> 菜单 <b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#">全部</a></li>
						<li class="divider"></li>
						<li><a href="#">饮料</a></li>
						<li class="divider"></li>
						<li><a href="#">小吃</a></li>
						<li class="divider"></li>
						<li><a href="#">主食</a></li>
					</ul></li>
			</ul>
		</div>

		<!--搜索栏-->
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-primary">搜索</button>
		</form>

		<!--登录注册/用户中心、退出-->
		<c:if test="${empty sessionScope.user and empty sessionScope.admin}">
			<ul class="nav navbar-nav navbar-right">
				<li><button type="submit" class="btn btn-info navbar-btn"
						data-toggle="modal" data-target="#login" href="">
						<span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
					</button></li>
				<!-- 形成简单间隔 -->
				<li>&nbsp;&nbsp;&nbsp;</li>
				<li><button type="submit" class="btn btn-success navbar-btn"
						data-toggle="modal" data-target="#register" href="">
						<span class="glyphicon glyphicon-user"></span>&nbsp;注册
					</button></li>
			</ul>
		</c:if>
		<c:if
			test="${not empty sessionScope.user or not empty sessionScope.admin}">
			<ul class="nav navbar-nav navbar-right">

				<li><a href="#"><span class="text-primary">欢迎回来，${sessionScope.user.account}${sessionScope.admin.account}</span></a></li>
				<li><a href="#"><span
						class="glyphicon glyphicon-shopping-cart"></span> 购物车</a></li>
				<li><c:if test="${not empty sessionScope.user}">
						<a
							href="${pageContext.request.contextPath}/pages/user/modify-info.jsp"><span
							class="glyphicon glyphicon-user"></span> 个人中心</a>
					</c:if> <c:if test="${not empty sessionScope.admin}">
						<a
							href="${pageContext.request.contextPath}/pages/admin/manage-users.jsp"><span
							class="glyphicon glyphicon-user"></span> 管理中心</a>
					</c:if></li>
				<li><button type="submit" class="btn btn-danger navbar-btn"
						onclick="window.location.href='${pageContext.request.contextPath}/servlet/logoutServlet'">
						<span class="glyphicon glyphicon-log-out"></span> 退出
					</button></li>
			</ul>
		</c:if>
	</div>
	</nav>


	<!-- 注册弹框 -->
	<div id="register" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">注册</h1>
				</div>

				<!-- 注册表单 -->
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/servlet/registerServlet"
						onSubmit="return checkRegister()" method="post">

						<div class="form-group has-feedback" id="register-account-div">
							<label for="register-account">用户名</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span> <input
									id="register-account" name="account" class="form-control"
									placeholder="请输入用户名" maxlength="20" type="text">
							</div>

							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class=" glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback" id="register-password-div">
							<label for="register-password">密码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock"></span></span> <input
									id="register-password" name="password" class="form-control"
									placeholder="请输入密码" maxlength="20" type="password">
							</div>

							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback"
							id="register-passwordConfirm-div">
							<label for="register-passwordConfirm">确认密码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock"></span></span> <input
									id="register-passwordConfirm" name="passwordConfirm"
									class="form-control" placeholder="请再次输入密码" maxlength="20"
									type="password">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>


						<div class="form-group has-feedback" id="register-tel-div">
							<label for="register-tel">手机号码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-phone"></span></span> <input
									id="register-tel" name="tel" class="form-control"
									placeholder="请输入手机号码" maxlength="11" type="text"
									value="${sessionScope.user.tel}">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback" id="register-email-div">
							<label for="register-email">邮箱</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-envelope"></span></span> <input
									id="register-email" name="email" class="form-control"
									type="email" placeholder="例如:123@123.com"
									value="${sessionScope.user.email}">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="row">
							<div class="col-xs-7">
								<div class="form-group has-feedback" id="idcode-btn-div">
									<label for="idcode-btn">验证码</label>
									<div class="input-group">
										<span class="input-group-addon"><span
											class="glyphicon glyphicon-qrcode"></span></span> <input
											id="idcode-btn" class="form-control" placeholder="请输入验证码"
											maxlength="4" type="text">
									</div>
									<span style="color: red; display: none;" class="tips"></span> <span
										style="display: none;"
										class="glyphicon glyphicon-remove form-control-feedback"></span>
									<span style="display: none;"
										class="glyphicon glyphicon-ok form-control-feedback"></span>
								</div>
							</div>
							<!-- 验证码背景 -->
							<div class="col-xs-5" style="padding-top: 30px">
								<div id="idcode" style="background: transparent;"></div>
							</div>
						</div>

						<div class="text-right">
							<span class="text-danger">${requestScope.registerError}</span>
							<button class="btn btn-primary" type="submit">提&nbsp;&nbsp;交</button>
							<button class="btn btn btn-warning orm-control" type="reset">重&nbsp;&nbsp;置</button>
							<button class="btn btn-danger" data-dismiss="modal">取&nbsp;&nbsp;消</button>
						</div>
						<input type="hidden" name="orgUrl" value="${pageContext.request.requestURL}" />

						<a href="" data-toggle="modal" data-dismiss="modal"
							data-target="#login">已有账号？点我登录</a>

					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 注册失败之后返回，重新打开模态框 -->
	<c:if test="${not empty requestScope.registerError}">
		<script>
			$('#register').modal("show");
		</script>
	</c:if>

	<!-- 注册成功弹框 -->
	<div id="register-success" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">注册成功</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<div class="">
							<button class="btn btn-primary" type="submit"
								data-dismiss="modal">确&nbsp;&nbsp;定</button>
						</div>
					</center>
				</div>
			</div>
		</div>
	</div>

	<!-- 注册成功后提示 -->
	<c:if test="${not empty requestScope.registerSuccess}">
		<script>
			$('#register-success').modal("show");
		</script>
	</c:if>


	<!-- 登录窗口 -->
	<div id="login" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-title">
					<h1 class="text-center">登录</h1>
				</div>

				<!-- 登录表单 -->
				<div class="modal-body">
					<form class="form-group"
						action="${pageContext.request.contextPath}/servlet/loginServlet"
						method="post">
						<div class="form-group has-feedback">
							<label for="login-account">用户名</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span> <input
									id="login-account" name="account" class="form-control"
									placeholder="请输入用户名" maxlength="20" type="text">
							</div>
						</div>

						<div class="form-group has-feedback">
							<label for="login-password">密码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock"></span></span> <input
									id="login-password" name="password" class="form-control"
									placeholder="请输入密码" maxlength="20" type="password">
							</div>
						</div>

						<div class="btn-group pull-left" data-toggle="buttons">
							<label class="btn btn-default active"> <input
								name="status" value="user" type="radio" checked>用户(默认)
							</label> <label class="btn btn-default"> <input name="status"
								value="admin" type="radio"> 管理员
							</label>
						</div>

						<div class="text-right">
							<span class="text-danger">${requestScope.loginError}</span>
							<!-- 2*24*60*60= 172800-->
							<input type="checkbox" name="logintime" value="172800">记住密码&nbsp;&nbsp;
							<button class="btn btn-primary" type="submit">登录</button>
							<button class="btn btn-danger" data-dismiss="modal">取消</button>
						</div>

						<br /> <a href="" data-toggle="modal" data-dismiss="modal"
							data-target="#register">还没有账号？点我注册</a>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 登录失败之后返回，重新打开模态框 -->
	<c:if test="${not empty requestScope.loginError}">
		<script>
			$('#login').modal("show");
		</script>
	</c:if>


</body>



</html>