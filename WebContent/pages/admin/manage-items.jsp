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
</head>
<body>

	<!-- 未登录无法进入 -->
	<c:if test="${empty sessionScope.admin}">
		<script>
			document.location = "${pageContext.request.contextPath}/index.jsp";
		</script>
	</c:if>

	<!-- 无itemsPage进入时，重新请求getUsersPagesServlet -->
	<c:if test="${empty requestScope.itemsPage}">
		<script>
			window.location.href = '${pageContext.request.contextPath}/servlet/getItemsPageServlet';
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
							<li class="active"><a
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
												type="hidden" name="account" value="${item.itemId}" /><input
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

	<!-- 新增餐点弹框 -->
	<div id="add-item" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">新增餐点</h1>
				</div>

				<!-- 表单 -->
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/servlet/addItemServlet?"
						onSubmit="return checkAddItem()" method="post">

						<div class="form-group has-feedback" id="add-item-name-div">
							<label for="add-item-name">名称</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-leaf"></span></span> <input
									id="add-item-name" name="name" class="form-control"
									placeholder="请输入餐点名称" maxlength="20" type="text">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<!-- 下拉表单 -->
						<div class="form-group has-feedback" id="add-item-type-div">
							<label for="add-item-type">类型</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-tasks"></span></span> <select
									class="form-control" name="type">
									<option value="饮料">饮料</option>
									<option value="小吃">小吃</option>
									<option value="主食">主食</option>
								</select>
							</div>
						</div>

						<div class="form-group has-feedback" id="add-item-stock-div">
							<label for="add-item-stock">库存数量</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-folder-close"></span></span> <input
									id="add-item-stock" name="stock" class="form-control"
									placeholder="请输入库存数量" maxlength="10" type="text">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>


						<div class="form-group has-feedback" id="add-item-price-div">
							<label for="add-item-price">单价</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-usd"></span></span> <input
									id="add-item-price" name="price" class="form-control"
									placeholder="请输入单价" maxlength="10" type="text">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback" id="add-item-description-div">
							<label for="add-item-description">描述</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-list-alt"></span></span>
								<textarea id="add-item-description" name="description"
									class="form-control" rows="3" type="text"
									placeholder="请输入该商品的相关描述" style="resize: none"> </textarea>
							</div>
						</div>

						<div class="text-right">
							<span class="text-danger">${requestScope.addItemError}</span>
							<button class="btn btn-primary" type="submit">提&nbsp;&nbsp;交</button>
							<button class="btn btn btn-warning orm-control" type="reset">重&nbsp;&nbsp;置</button>
							<button class="btn btn-danger" data-dismiss="modal">取&nbsp;&nbsp;消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

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
					<h1 class="text-center">修改餐点信息</h1>
				</div>

				<!-- 表单 -->
				<div class="modal-body">
					<form action="" onSubmit="" method="post">
						<div class="form-group has-feedback" id="modify-item-itemId-div">
							<label for="modify-item-name">ID</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-leaf"></span></span> <input
									id="modify-item-name" name="name" class="form-control"
									readonly="readonly" type="text" value="${param.itemId}">
							</div>
						</div>
						<div class="form-group has-feedback" id="modify-item-name-div">
							<label for="modify-item-name">名称</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-leaf"></span></span> <input
									id="modify-item-name" name="name" class="form-control"
									readonly="readonly" type="text" value="${param.name}">
							</div>
						</div>

						<!-- 下拉表单 -->
						<div class="form-group has-feedback" id="modify-item-type-div">
							<label for="modify-item-type">类型</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-tasks"></span></span> <select
									class="form-control" name="type">
									<!-- 不是办法的办法，这样来造成默认选中效果 -->
									<option value="${param.type}">${param.type}(旧值)</option>
									<option value="饮料">饮料</option>
									<option value="小吃">小吃</option>
									<option value="主食">主食</option>
								</select>
							</div>
						</div>

						<div class="form-group has-feedback" id="modify-item-stock-div">
							<label for="modify-item-stock">库存数量</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-folder-close"></span></span> <input
									id="modify-item-stock" name="stock" class="form-control"
									placeholder="请输入库存数量" maxlength="10" type="text"
									value="${param.stock}">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>


						<div class="form-group has-feedback" id="modify-item-price-div">
							<label for="modify-item-price">单价</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-usd"></span></span> <input
									id="modify-item-price" name="price" class="form-control"
									placeholder="请输入单价" maxlength="10" type="text"
									value="${param.price}">
							</div>
							<span style="color: red; display: none;" class="tips"></span> <span
								style="display: none;"
								class="glyphicon glyphicon-remove form-control-feedback"></span>
							<span style="display: none;"
								class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback"
							id="modify-item-description-div">
							<label for="modify-item-description">描述</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-list-alt"></span></span>
								<textarea id="modify-item-description" name="description"
									class="form-control" rows="3" type="text"
									placeholder="请输入该商品的相关描述" style="resize: none"> ${param.description}</textarea>
							</div>
						</div>

						<div class="text-right">
							<span class="text-danger">${requestScope.modifyItemError}</span>
							<button class="btn btn-primary" type="submit">提&nbsp;&nbsp;交</button>
							<button class="btn btn btn-warning orm-control" type="reset">重&nbsp;&nbsp;置</button>
							<button class="btn btn-danger" data-dismiss="modal">取&nbsp;&nbsp;消</button>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 根据post信息弹出修改弹框 -->
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
							onclick="window.location.href='${pageContext.request.contextPath}/servlet/getItemsPageServlet?currentPage=${requestScope.itemsPage.currentPage}'"
							type="submit" data-dismiss="modal">确&nbsp;&nbsp;定</button>
					</center>
				</div>
			</div>
		</div>
	</div>

	<!-- 操作成功后提示 -->
	<c:if test="${not empty requestScope.operateSuccess}">
		<script>
			$('#operate-success').modal("show");
		</script>
	</c:if>




</body>
</html>