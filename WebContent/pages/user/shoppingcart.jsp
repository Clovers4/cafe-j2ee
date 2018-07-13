<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>☆☆卡布奇诺咖啡馆☆☆</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<button class="btn btn-success"
								onclick="window.location.href=''">
								<span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
							</button>
						</div>
					</div>
					<!-- 主列表 -->
					<div class="panel-body">
						<table class="table table-striped" style="text-align: center">
							<tr>
								<td>ID</td>
								<td>名称</td>
								<td>类型</td>
								<td>库存量</td>
								<td>单价</td>
								<td>删除</td>
								<td>保存</td>
							</tr>
							<c:forEach var="item" items="${requestScope.items}">
								<tr>
									<td>${item.itemId}</td>
									<td>${item.name}</td>
									<td>${item.type}</td>
									<td>${item.stock}</td>
									<td>${item.price}</td>
									<!-- 提交到本页面，激活修改 -->
									<td><form method="post">
											<input type="hidden" name="delete" value="1" /> <input
												type="hidden" name="itemId" value="${item.itemId}" /><input
												type="hidden" name="name" value="${item.name}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-remove"></span>
											</button>
										</form></td>
									<td>
									<td><form method="post">
											<input type="hidden" name="addImage" value="1" /> <input
												type="hidden" name="itemId" value="${item.itemId}" /><input
												type="hidden" name="name" value="${item.name}" /><input
												type="hidden" name="number" value="${item.number}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-upload"></span>
											</button>
										</form></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>