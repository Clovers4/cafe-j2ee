<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.bundle.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.bundle.min.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.min.js"></script>

<script src="${pageContext.request.contextPath}/js/Chart.bundle.js"></script>
<script src="${pageContext.request.contextPath}/js/utils.js"></script>

</head>
<body>

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
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/manage-items.jsp"><span
									class="glyphicon glyphicon glyphicon-leaf"></span> 管理菜单</a></li>
							<li class="nav-divider"></li>
							<li class="active"><a
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
						<font size="3"> <span class="glyphicon glyphicon-list-alt"></span>
							历史订单
						</font>
					</div>
				</div>
				<!-- 下半部分 -->
				<div class="panel panel-default">
					<div class="panel-heading" style="height: 55px">
						<div class="pull-left">
							<font size="4" style="line-height: 35px"><strong>历史订单</strong></font>
						</div>
						<div class="pull-right">

							<!-- 刷新按钮 -->
							<button class="btn btn-success"
								onclick="window.location.href='${ordersPage.url }&currentPage=${ordersPage.currentPage}'">
								<span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
							</button>
						</div>
					</div>
					<!-- 饼图 -->
					<div class="panel-body">
						<div id="canvas-holder" style="width: 100%">
							<canvas id="chart-area"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			var config = {
				type : 'pie',
				data : {
					datasets : [ {
						data : [ 0.11, 0.12, 0.13, 0.14, 0.52, ],
						backgroundColor : [ window.chartColors.red,
								window.chartColors.orange,
								window.chartColors.yellow,
								window.chartColors.green,
								window.chartColors.blue, ],
						label : 'Dataset 1'
					} ],
					labels : [ '1', '2', '3', '4', '其他餐点' ]
				},
				options : {
					responsive : true
				}
			};

			window.onload = function() {
				var ctx = document.getElementById('chart-area')
						.getContext('2d');
				window.myPie = new Chart(ctx, config);
			};
		</script>
</body>
</html>