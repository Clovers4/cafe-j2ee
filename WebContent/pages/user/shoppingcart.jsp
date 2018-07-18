<%@page import="com.coffee.domain.Item"%>
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
	width: 30px;
}
</style>
</head>
<body>
	<!-- 网页头部 -->
	<jsp:include page="/jspfragments/header.jsp" />

	<!-- 网页正文 -->
	<br />
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-warning">
					<!-- 头部 -->
					<div class="panel-heading" style="height: 55px">
						<div class="pull-left">
							<font size="4" style="line-height: 35px">购物车</font>
						</div>
						<div class="pull-right">
							<button class="btn btn-success" onclick="window.location.href=''">
								<span class="glyphicon glyphicon-refresh"></span>&nbsp;刷&nbsp;新
							</button>
						</div>
					</div>
					<!-- 主列表 -->
					<div class="panel-body" id="item">
						<table class="table table-striped" style="text-align: center">
							<tr>
								<td>餐点</td>
								<td>餐点名</td>
								<td>类型</td>
								<td>数量</td>
								<td>单价</td>
								<td>小计</td>
								<td>保存</td>
								<td>删除</td>

							</tr>

							<c:forEach var="item" items="${requestScope.items}">

								<tr>
									<form
										action="${pageContext.request.contextPath}/servlet/modifyShoppingcartItemServlet"
										method="post">
										<td><img
											src="${pageContext.request.contextPath}${item.imageUrl}"></td>
										<td>${item.name}</td>
										<td>${item.type}</td>
										<td><input type="text" id="item-number" name="number"
											size="1" style="text-align: center;" value=${item.number } />
										</td>
										<td>${item.price}</td>
										<td>${item.number*item.price}</td>
										<td><input type="hidden" name="modify" value="1" /> <input
											type="hidden" name="userId" value="${user.userId}" /> <input
											type="hidden" name="itemId" value="${item.itemId}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-ok"></span>
											</button></td>
									</form>
									<td><form method="post">
											<input type="hidden" name="delete" value="1" /> <input
												type="hidden" name="itemId" value="${item.itemId}" /><input
												type="hidden" name="name" value="${item.name}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</form></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="panel-footer " style="height: 50px">
						<div class="pull-right">
							</font><font size="4"><strong>总价：${total}&nbsp;&nbsp;&nbsp;&nbsp;</strong></font>
							<button class="btn btn-danger" data-toggle="modal" data-target="#pay" ">
								<span class="glyphicon glyphicon-usd"></span>&nbsp;结&nbsp;算
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 确定删除弹框 -->
	<div id="delete" class="modal fade" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 右上角的叉 -->
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">移除餐点</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<h3>是否要从购物车中移除该餐点?</h3>
					</center>
					<form
						action="${pageContext.request.contextPath}/servlet/deleteShoppingcartItemServlet"
						method="post">
						<div class="form-group has-feedback">
							<label for="itemId">餐点ID</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-user"></span></span> <input id="itemId"
									name="itemId" class="form-control" readonly="readonly"
									type="text" value="${param.itemId}">
							</div>
						</div>
						<div class="form-group has-feedback">
							<label for="name">名称</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-leaf"></span></span> <input id="name"
									name="name" class="form-control" readonly="readonly"
									type="text" value="${param.name}">
							</div>
						</div>
						<div>
							<input type="hidden" name="userId" value="${user.userId}" />
							<center>
								<button class="btn btn-primary" type="submit">确&nbsp;&nbsp;定</button>
								<button class="btn btn-danger" data-dismiss="modal">取&nbsp;&nbsp;消</button>
							</center>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 根据修改param转入修改界面,有卡顿？？？ -->
	<c:if test="${not empty param.delete}">
		<script>
			$('#delete').modal("show");
		</script>
	</c:if>

	<!-- 确认订单弹框 -->
	<div id="pay" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-title">
					<h1 class="text-center">确认订单</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<font size="4"><strong>是否要完成结账操作</strong></font> <br />
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/addOrderServlet'"
							type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
						<button class="btn btn-danger" data-dismiss="modal">取&nbsp;&nbsp;消</button>
					</center>
				</div>
			</div>
		</div>
	</div>


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
						<font size="4"><strong>${operateSuccess}</strong></font> <br />
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/getShoppingcartServlet'"
							type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
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
	<div id="operate-error" class="modal fade" aria-hidden="true"
		data-backdrop="static">
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
						<font size="4"><strong>${operateError}</strong></font><br />
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/getShoppingcartServlet'"
							type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
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