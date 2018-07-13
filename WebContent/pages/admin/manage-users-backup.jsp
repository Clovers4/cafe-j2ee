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

<script src="${pageContext.request.contextPath}/js/verify.js"></script>


<!-- 之前用来做无分页的用户管理，已弃用 -->
</head>
<body>
	<!-- 若之前没有拿到所有users，就请求去得到 -->
	<!-- 这里未做与数据库一直同步，需要注意————也就是说，用户注册时，管理员处的信息可能得不到更新 -->
	<c:if test="${sessionScope.users==null}">
		<jsp:forward page="/servlet/getAllUsersServlet"></jsp:forward>
	</c:if>

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
										class="glyphicon glyphicon-th-large"></span>管理中心</font></a></li>
						</ul>
					</div>

					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a
								href="${pageContext.request.contextPath}/pages/admin/manage-users.jsp">
									<span class="glyphicon glyphicon-user"></span> 管理用户
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/manage-items.jsp"><span
									class="glyphicon glyphicon glyphicon-leaf"></span> 管理菜单</a></li>
							<li class="nav-divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/order-history.jsp">
									<span class="glyphicon glyphicon-list-alt"></span> 历史订单
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/items-heat.jsp">
									<span class="glyphicon glyphicon-fire"></span> 餐点热度
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
						<font size="3"> <span class="glyphicon glyphicon-user"></span>
							管理用户
						</font>
					</div>
				</div>
				<!-- 下半部分 -->
				<div class="panel panel-default">
					<div class="panel-heading" style="height: 55px">
						<div class="pull-left">
							<font size="4" style="line-height: 35px">管理用户</font>
						</div>
						<div class="pull-right">
							<button class="btn btn-success"
								onclick="window.location.href='${pageContext.request.contextPath}/servlet/getAllUsersServlet'"
								type="submit" data-dismiss="modal">
								<span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
							</button>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-striped" style="text-align: center">
							<tr>
								<td>编号</td>
								<td>帐号</td>
								<td>密码</td>
								<td>手机号码</td>
								<td>邮箱</td>
								<td>删除</td>
								<td>编辑</td>
							</tr>
							<c:forEach var="user" items="${sessionScope.users}"
								varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${user.account}</td>
									<td>${user.password}</td>
									<td>${user.tel}</td>
									<td>${user.email}</td>
									<!-- 提交到本页面，激活修改 -->
									<td><form method="post">
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</form></td>
									<td>
										<form method="post">
											<input type="hidden" name="modify" value="1" /> <input
												type="hidden" name="account" value="${user.account}" /> <input
												type="hidden" name="password" value="${user.password}" /> <input
												type="hidden" name="tel" value="${user.tel}" /> <input
												type="hidden" name="email" value="${user.email}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>
										</form>
									</td>
								</tr>
							</c:forEach>

							<tr>
								<td colspan="6" style="text-align: left;"><a href="add.jsp">添加管理员</a></td>
							</tr>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 根据修改user-account转入修改界面 -->
	<!-- 修改弹框 -->
	<div id="modify" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">修改用户信息</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/servlet/modifyUserServlet"
						onSubmit="return checkModifyInfo()" method="post">

						<div class="form-group has-feedback">
							<label for="account">用户名</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span> <input id="account"
									name="account" class="form-control" readonly="readonly"
									type="text" value="${param.account}">
							</div>
						</div>

						<div class="form-group has-feedback" id="modified-password-div">
							<label for="modified-password">密码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock"></span></span> <input
									id="modified-password" name="password" class="form-control"
									placeholder="请输入密码" maxlength="20" type="password"
									value="${param.password}">
							</div>

							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback"
							id="modified-passwordConfirm-div">
							<label for="modified-passwordConfirm">确认密码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock"></span></span> <input
									id="modified-passwordConfirm" name="passwordConfirm"
									class="form-control" placeholder="请再次输入密码" maxlength="20"
									type="password" value="${param.password}">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback" id="modified-tel-div">
							<label for="modified-tel">手机号码</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-phone"></span></span> <input name="tel"
									id="modified-tel" class="form-control" placeholder="请输入手机号码"
									maxlength="11" type="text" value="${param.tel}">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback" id="modified-email-div">
							<label for="modified-email">邮箱</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-envelope"></span></span> <input
									name="email" id="modified-email" class="form-control"
									type="email" placeholder="例如:123@123.com"
									value="${param.email}">
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

	<!-- 不知为何要放在modify弹窗之后，否则无法弹出 -->
	<!-- 根据修改user-account转入修改界面,有卡顿？？？ -->
	<c:if test="${not empty param.modify}">
		<script>
			$('#modify').modal("show");
		</script>
	</c:if>

	<!-- 操作成功弹框 -->
	<div id="operate-success" class="modal fade" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-title">
					<h1 class="text-center">操作成功</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/getAllUsersServlet'"
							type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
					</center>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改成功后提示 -->
	<c:if test="${not empty requestScope.modifyUserSuccess}">
		<script>
			$('#operate-success').modal("show");
		</script>
	</c:if>

</body>
</html>