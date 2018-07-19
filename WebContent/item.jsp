<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>☆☆卡布奇诺咖啡馆☆☆</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/item.js"></script>
<style>
#item img {
	width: 550px;
}
</style>

</head>
<body>
	<!-- 利用forwardUrl来确定(注册之后)返回的url -->
	<%
		request.setAttribute("forwardUrl", "/index.jsp"); //map
	%>

	<!-- 网页头部 -->
	<jsp:include page="/jspfragments/header.jsp" />

	<!-- 网页正文 -->
	<br />

	<div class="container">
		<div class="row">
			<!-- 左侧菜单栏 -->
			<div class="col-md-6" id="item">
				<img src="${pageContext.request.contextPath}${item.imageUrl}">
			</div>
			<!-- 右侧主体 -->
			<div class="col-md-6">
				<br /> <br /> <br /> <br /> <br /> <br /> <br />
				<div class="panel panel-default">
					<div class="panel-heading">
						<font size="3"><span
							class="glyphicon glyphicon glyphicon-leaf"></span> 商品详情 </font>
					</div>
					<div class="panel-body">
						<font size="8"><strong>${item.name}</strong></font>&nbsp;&nbsp;&nbsp;&nbsp;<font
							size="5"><strong>${item.type}</strong></font>&nbsp;&nbsp;&nbsp;&nbsp;<font
							size="4"><strong><span
								class="glyphicon glyphicon-usd">${item.price}</span></strong></font><br /> <br />
						<br /> <font size="4">${item.description}</font> <br /> <br />

						<c:if test="${empty sessionScope.user}">
							<center>
								<font size="5" style="width: 50%"><strong>如需购买，请先登录</strong></font><br />
								<button type="submit" class="btn btn-info navbar-btn"
									data-toggle="modal" data-target="#login" style="width: 50%">
									<span class="glyphicon glyphicon-log-in"></span>&nbsp;登&nbsp;&nbsp;录
								</button>
							</center>
						</c:if>

						<c:if test="${not empty sessionScope.user}">
							<form action="${pageContext.request.contextPath}/servlet/addShoppingcartItemServlet" method="post">
								<div class="form-group has-feedback">
									<label for="item-number"><font size="4">购买数量(库存量：${item.stock})</font></label>
									<div class="input-group">
										<span class="input-group-btn">
											<button class="btn btn-default" onclick="decreaseItemNum()"
												type="button">-</button>
										</span> <input id="item-number" name="number" type="text"
											class="form-control" value="1" style="text-align: center;">
										<span class="input-group-btn">
											<button class="btn btn-default"
												onclick="increaseItemNum(${item.stock})" type="button">+</button>
										</span>
									</div>
									<br /> <input type="hidden" name="itemId"
										value="${item.itemId}"><input type="hidden"
										name="userId" value="${user.userId}">
									<button type="submit" class="btn btn-danger"
										style="width: 100%">加入购物车</button>
								</div>
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>


	<!-- 操作成功弹框 -->
	<div id="operate-success" class="modal fade" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">操作成功</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						${operateSuccess} <br />
						<button class="btn btn-primary" type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
					</center>
				</div>
			</div>
		</div>
	</div>

	<!-- 操作成功后提示 -->
	<c:if test="${not empty operateSuccess}">
		<script>
			$('#operate-success').modal("show");
		</script>
	</c:if>

	<!-- 操作失败弹框 -->
	<div id="operate-error" class="modal fade" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">操作失败</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<h3>${operateError}</h3>
						<button class="btn btn-primary" type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
					</center>
				</div>
			</div>
		</div>
	</div>

	<!-- 操作失败后提示 -->
	<c:if test="${not empty operateError}">
		<script>
			$('#operate-error').modal("show");
		</script>
	</c:if>



</body>
</html>