<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<!-- 确定删除弹框 -->
	<div id="delete" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 右上角的叉 -->
				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">删除</h1>
				</div>

				<!-- 主体 -->
				<div class="modal-body">
					<center>
						<h3>是否要删除该商品?</h3>
					</center>
					<form
						action="${pageContext.request.contextPath}/servlet/deleteItemServlet"
						method="post">
						<div class="form-group has-feedback">
							<label for="itemId">商品ID</label>
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

	<!-- 根据修改user-account转入修改界面,有卡顿？？？ -->
	<c:if test="${not empty param.delete}">
		<script>
			$('#delete').modal("show");
		</script>
	</c:if>


</body>
</html>