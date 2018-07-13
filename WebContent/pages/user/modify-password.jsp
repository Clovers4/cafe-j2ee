<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>☆☆卡布奇诺咖啡馆☆☆</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/verify-modify-user.js"></script>

</head>
<body>
	<!-- 网页头部 -->
	<jsp:include page="/jspfragments/header.jsp" />

	<!-- 网页正文 -->
	<br />

	<div class="container">
		<div class="row">
			<!-- 左侧菜单栏 -->
			<div class="col-md-2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<ul class="nav nav-pills nav-stacked">
							<li class="disabled"><a href="#"><font size="3"
									style="font-weight: bold;"><span
										class="glyphicon glyphicon-th-large"></span>用户中心</font></a></li>
						</ul>
					</div>
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<li><a
								href="${pageContext.request.contextPath}/pages/user/modify-info.jsp">
									<span class="glyphicon glyphicon-user"></span> 修改信息
							</a></li>
							<li class="active"><a
								href="${pageContext.request.contextPath}/pages/user/modify-password.jsp"><span
									class="glyphicon glyphicon-lock"></span> 修改密码</a></li>
							<li class="nav-divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/user/order-history.jsp">
									<span class="glyphicon glyphicon-list-alt"></span> 历史订单
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- 右侧主体 -->
			<div class="col-md-10">
				<!-- 上半部分 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<font size="3"> <span class="glyphicon glyphicon-lock"></span>
							修改密码
						</font>
					</div>
				</div>
				<!-- 下半部分 -->
				<div class="panel panel-default">
					<div class="panel-heading">修改密码</div>
					<div class="panel-body">
						<form action="${pageContext.request.contextPath}/servlet/modifyPasswordServlet"
							onSubmit="return checkModifyPassword()"
							method="post">

							<div class="form-group has-feedback" id="modified-password-div">
								<label for="modified-password">密码</label>
								<div class="input-group">
									<span class="input-group-addon"><span
										class="glyphicon glyphicon-lock"></span></span> <input id="modified-password"
										name="password" class="form-control" placeholder="请输入密码"
										maxlength="20" type="password">
								</div>

								<span style="color: red; display: none;" class="tips"></span> <span
									style="display: none;"
									class="glyphicon glyphicon-remove form-control-feedback"></span>
								<span style="display: none;"
									class="glyphicon glyphicon-ok form-control-feedback"></span>
							</div>

							<div class="form-group has-feedback" id="modified-passwordConfirm-div">
								<label for="modified-passwordConfirm">确认密码</label>
								<div class="input-group">
									<span class="input-group-addon"><span
										class="glyphicon glyphicon-lock"></span></span> <input
										id="modified-passwordConfirm" name="passwordConfirm"
										class="form-control" placeholder="请再次输入密码" maxlength="20"
										type="password">
								</div>
								<span style="color: red; display: none;" class="tips"></span> <span
									style="display: none;"
									class="glyphicon glyphicon-remove form-control-feedback"></span>
								<span style="display: none;"
									class="glyphicon glyphicon-ok form-control-feedback"></span>
							</div>

							<div class="text-right">
								<button class="btn btn-primary" type="submit">提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</button>
								<button class="btn btn btn-warning orm-control" type="reset">重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置</button>
							</div>

						</form>
					</div>
				</div>
			</div>

		</div>
	</div>


	<!-- 修改成功弹框 -->
	<div id="modify-password-success" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">修改成功</h1>
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
	
	
	<!-- 修改成功后提示 -->
	<c:if test="${not empty requestScope.modifyPasswordSuccess}">
		<script>
			$('#modify-password-success').modal("show");
		</script>
	</c:if>

</body>
</html>