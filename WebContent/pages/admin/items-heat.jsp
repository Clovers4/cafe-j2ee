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

</head>
<body>

	<!-- 未登录无法进入 -->
	<c:if test="${empty sessionScope.admin}">
		<script>
			document.location = "${pageContext.request.contextPath}/index.jsp";
		</script>
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
							<li><a
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
							<li class="active"><a
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
						<font size="3"> <span class="glyphicon glyphicon-fire"></span>
							餐点热度
						</font>
					</div>
				</div>
				<!-- 下半部分 -->
				<div class="panel panel-default">
					<div class="panel-heading">餐点热度</div>
					<div class="panel-body">
						<p>正文
					</div>
				</div>
			</div>

		</div>
	</div>


</body>
</html>