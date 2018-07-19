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
	
<script src="${pageContext.request.contextPath}/js/fileinput.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/fileinput.min.css">


</head>
<body>

	<!-- 新增/修改图片弹框 -->
	<div id="add-image" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-body">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>

				<div class="modal-title">
					<h1 class="text-center">上传/修改图片</h1>
				</div>

				<!-- 表单 -->
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/servlet/uploadItemImageServlet"
						enctype="multipart/form-data" method="post">
						<div class="form-group has-feedback">
							<label for="item-itemId">ID</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-leaf"></span></span> <input
									id="item-itemId" name="itemId" class="form-control"
									readonly="readonly" type="text" value="${param.itemId}">
							</div>
						</div>

						<div class="form-group has-feedback">
							<label for="item-name">名称</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-leaf"></span></span> <input id="item-name"
									name="name" class="form-control" readonly="readonly"
									type="text" value="${param.name}">
							</div>
						</div>

						<div class="form-group has-feedback">
							<label for="item-image-url">图片URL</label>
							<div class="input-group">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-barcode"></span></span> <input
									id="item-image-url" name="imageUrl" class="file"
									data-show-preview="false" type="file" data-show-upload="false"
									data-allowed-file-extensions='["jpg","png","gif","bmp"]'>
							</div>
						</div>

						<div class="text-right">
							<span class="text-danger">${requestScope.addImageError}</span>
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
	<c:if test="${not empty param.addImage}">
		<script>
			$('#add-image').modal("show");
		</script>
	</c:if>
</body>
</html>