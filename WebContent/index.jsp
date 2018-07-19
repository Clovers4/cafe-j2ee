<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>☆☆卡布奇诺咖啡馆☆☆</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
/*main区域居中*/
.main {
	text-align: center;
	background-color: #fff;
	border-radius: 20px;
	width: 1000px;
	height: 400px;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
}

.items {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}

.items img {
	width: 150px;
}

/*去除轮播两边阴影*/
#cl #cr {
	background-image: none;
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
	<!-- 轮播图  -->
	<div
		style="background: url(${pageContext.request.contextPath}/images/index/background1.png)">
		<!-- background-repeat:no-repeat  -->
		<div id="carousel-example-generic" class="main carousel slide "
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img
						src="${pageContext.request.contextPath}/images/index/carousel1.png">
				</div>
				<div class="item">
					<img
						src="${pageContext.request.contextPath}/images/index/carousel2.png">
				</div>
				<div class="item">
					<img
						src="${pageContext.request.contextPath}/images/index/carousel3.png">
				</div>
			</div>

			<!-- Controls -->
			<a class="carousel-control left" id="cl"
				href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>

			</a> <a class="carousel-control right" id="cr"
				href="#carousel-example-generic" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			</a>



		</div>
	</div>

	<!-- 3个产品展示 -->
	<div class="container items">
		<div class="row">
			<div class="col-xs-4 ">
				<a href="${pageContext.request.contextPath}/servlet/getItemServlet?itemId=13"> <img
					src="${pageContext.request.contextPath}/images/index/iced-shaken-mango-herbal-juiced-tea.png"
					class="img-circle">
					<div class="caption">
						<div class="text-muted">
							<br />冰摇红莓黑加仑茶®
						</div>
					</div></a>
			</div>

			<div class="col-xs-4 ">
				<a href="${pageContext.request.contextPath}/servlet/getItemServlet?itemId=17"> <img
					src="${pageContext.request.contextPath}/images/index/macaron.png"
					class="img-circle">
					<div class="caption">
						<div class="text-muted">
							<br />法式马卡龙
						</div>
					</div></a>
			</div>
			<div class="col-xs-4 ">
				<a href="${pageContext.request.contextPath}/servlet/getItemServlet?itemId=15"> <img
					src="${pageContext.request.contextPath}/images/index/vanilla-flavored-cream-frappuccino-blended-beverage.png"
					class="img-circle">
					<div class="caption">
						<div class="text-muted">
							<br />香草风味星冰乐
						</div>
					</div></a>
			</div>
		</div>
	</div>

	<div
		style="background: url(${pageContext.request.contextPath}/images/index/background3.png)">
		<div class="container">
			<div class="col-xs-2 "></div>
			<div class="col-xs-6 ">
				<br /> <br /> <font size="20"><strong>Coffee & Bar</strong></font>
				<br /> <font size="5"><strong>享受咖啡，享受生活</strong></font> <br /> <br />
				<br />
			</div>
			<div class="col-xs-3 ">
				<br /> <br />
				<ul>
					<li><button type="submit" class="btn btn-info navbar-btn"
							data-toggle="modal" data-target="#login" href="">
							<span class="glyphicon glyphicon-log-in"></span>&nbsp;登&nbsp;&nbsp;录
						</button></li>
					<!-- 形成简单间隔 -->
					<li>&nbsp;&nbsp;&nbsp;</li>
					<li><button type="submit" class="btn btn-success navbar-btn"
							data-toggle="modal" data-target="#register" href="">
							<span class="glyphicon glyphicon-user"></span>&nbsp;注&nbsp;&nbsp;册
						</button></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 页脚 -->
	<!--<jsp:include page="/jspfragments/footer.jsp" />-->

</body>
</html>