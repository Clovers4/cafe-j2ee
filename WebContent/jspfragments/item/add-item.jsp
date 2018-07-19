<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add-item</title>
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

</body>
</html>