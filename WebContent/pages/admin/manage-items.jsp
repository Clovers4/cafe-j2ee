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

<script src="${pageContext.request.contextPath}/js/verify-add-item.js"></script>
</head>
<body>

	<!-- 无itemsPage且没有操作失败,进入时，重新请求getUsersPagesServlet -->
	<c:if
		test="${empty itemsPage and empty operateSuccess and empty operateError}">
		<script>
			window.location.href = '${pageContext.request.contextPath}/servlet/getItemsPageServlet';
		</script>
	</c:if>

	<!-- 增删改弹框引入 -->
	<jsp:include page="/jspfragments/item/add.jsp" />
	<jsp:include page="/jspfragments/item/add-image.jsp" />
	<jsp:include page="/jspfragments/item/modify.jsp" />
	<jsp:include page="/jspfragments/item/delete.jsp" />

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
							<li class="active"><a
								href="${pageContext.request.contextPath}/pages/admin/manage-items.jsp"><span
									class="glyphicon glyphicon glyphicon-leaf"></span> 管理菜单</a></li>
							<li class="nav-divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/history-orders.jsp">
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
						<font size="3"><span
							class="glyphicon glyphicon glyphicon-leaf"></span> 管理菜单 </font>
					</div>
				</div>
				<!-- 下半部分 -->
				<div class="panel panel-default">
					<!-- 头部 -->
					<div class="panel-heading" style="height: 55px">
						<div class="pull-left">
							<font size="4" style="line-height: 35px">管理菜单</font>
						</div>
						<div class="pull-right">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#add-item"">
								<span class="glyphicon glyphicon-plus"></span>新&nbsp;增
							</button>
							<button class="btn btn-success"
								onclick="window.location.href='${itemsPage.url }&currentPage=${itemsPage.currentPage}'">
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
								<td>编辑</td>
								<td>上传图片</td>
							</tr>
							<c:forEach var="item" items="${requestScope.itemsPage.list}">
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
										<form method="post">
											<input type="hidden" name="modify" value="1" /> <input
												type="hidden" name="itemId" value="${item.itemId}" /><input
												type="hidden" name="name" value="${item.name}" /> <input
												type="hidden" name="type" value="${item.type}" /> <input
												type="hidden" name="stock" value="${item.stock}" /> <input
												type="hidden" name="price" value="${item.price}" /><input
												type="hidden" name="description" value="${item.description}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-pencil"></span>
											</button>
										</form>
									</td>
									<td><form method="post">
											<input type="hidden" name="addImage" value="1" /> <input
												type="hidden" name="itemId" value="${item.itemId}" /><input
												type="hidden" name="name" value="${item.name}" />
											<button class="btn btn-info" type="submit">
												<span class="glyphicon glyphicon-upload"></span>
											</button>
										</form></td>
								</tr>
							</c:forEach>
						</table>
						<center>第${itemsPage.currentPage}页/共${itemsPage.totalPage}页</center>
						<nav>
						<ul class="pager">
							<c:if test="${itemsPage.currentPage>1 }">
								<li class="previous"><a
									href="${itemsPage.url}&currentPage=1">首页</a></li>
								<li class="previous"><a
									href="${itemsPage.url}&currentPage=${itemsPage.currentPage-1}">上一页</a></li>
							</c:if>
							<c:if test="${itemsPage.currentPage<itemsPage.totalPage}">
								<li class="next"><a
									href="${itemsPage.url }&currentPage=${itemsPage.totalPage}">尾页</a></li>
								<li class="next"><a
									href="${itemsPage.url }&currentPage=${itemsPage.currentPage+1}">下一页</a></li>

							</c:if>
						</ul>
						</nav>
					</div>
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
						${operateSuccess} <br />
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/getItemsPageServlet?currentPage=${requestScope.itemsPage.currentPage}'"
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
				<div class="modal-title">
					<h1 class="text-center">操作失败</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<h3>${operateError}</h3>
						<button class="btn btn-primary"
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/getItemsPageServlet?currentPage=${requestScope.itemsPage.currentPage}'"
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