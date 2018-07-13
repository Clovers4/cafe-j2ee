<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>☆☆卡布奇诺咖啡馆☆☆</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.items {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}

.items img {
	width: 150px;
}
</style>
</head>
<body>
	<!-- 利用forwardUrl来确定(注册之后)返回的url -->
	<%
		request.setAttribute("forwardUrl", "/index.jsp"); //map
	%>

	<!-- 无itemsPage进入时，重新请求getUsersPagesServlet -->
	<c:if test="${empty itemsPage}">
		<script>
			window.location.href = '${pageContext.request.contextPath}/servlet/searchItemPageServlet';
		</script>
	</c:if>

	<!-- 网页头部 -->
	<jsp:include page="/jspfragments/header.jsp" />

	<!-- 网页正文 -->
	<br />

	<div class="container">
		<div class="panel panel-default">
			<!-- 头部 -->
			<div class="panel-heading" style="height: 55px">
				<div class="pull-left">
					<font size="4" style="line-height: 35px">搜索结果</font>
				</div>
				<div class="pull-right">
					<button class="btn btn-success"
						onclick="window.location.href='${itemsPage.url}&currentPage=${itemsPage.currentPage}'">
						<span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
					</button>
				</div>
			</div>
			<!-- 主列表 -->
			<div class="panel-body items">
				<c:forEach var="item" items="${requestScope.itemsPage.list}">
					<div class="col-xs-4 ">
						<a href="${pageContext.request.contextPath}/servlet/getItemServlet?itemId=${item.itemId}" class="thumbnail"> <img
							src="${pageContext.request.contextPath}${item.imageUrl}"
							class="img-circle">
						<div class="caption">
							<div class="text-muted">
								<br />${item.name}
							</div>
						</div>
						</a>
					</div>
				</c:forEach>

				<div class="row"></div>

				<p>&nbsp;</p>
				<p>&nbsp;</p>
				<nav>
				<center>第${itemsPage.currentPage}页/共${itemsPage.totalPage}页</center>
				<ul class="pager">
					<c:if test="${itemsPage.currentPage>1 }">
						<li class="previous"><a href="${itemsPage.url}&currentPage=1">首页</a></li>
						<li class="previous"><a
							href="${itemsPage.url}&currentPage=${itemsPage.currentPage-1}">上一页</a></li>
					</c:if>
					<c:if test="${itemsPage.currentPage<itemsPage.totalPage}">
						<li class="next"><a
							href="${itemsPage.url}&currentPage=${itemsPage.totalPage}">尾页</a></li>
						<li class="next"><a
							href="${itemsPage.url }&currentPage=${itemsPage.currentPage+1}">下一页</a></li>

					</c:if>
				</ul>
				</nav>
			</div>
		</div>
	</div>


</body>
</html>