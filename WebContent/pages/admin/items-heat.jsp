<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>☆☆卡布奇诺咖啡馆☆☆</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.bundle.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.bundle.min.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.js"></script>
<script src="https://cdn.bootcss.com/Chart.js/2.7.2/Chart.min.js"></script>

<script src="${pageContext.request.contextPath}/js/Chart.bundle.js"></script>
<script src="${pageContext.request.contextPath}/js/utils.js"></script>
</head>
<body>
	<!-- 无items请求获得 -->
	<c:if test="${empty items}">
		<script>
			window.location.href = '${pageContext.request.contextPath}/servlet/getItemHeatServlet';
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
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/manage-items.jsp"><span
									class="glyphicon glyphicon glyphicon-leaf"></span> 管理菜单</a></li>
							<li class="nav-divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/pages/admin/history-orders.jsp">
									<span class="glyphicon glyphicon-list-alt"></span> 历史订单
							</a></li>
							<li class="active"><a
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
						<font size="3"> <span class="glyphicon glyphicon-fire"></span>
							餐点热度
						</font>
					</div>
				</div>
				<!-- 下半部分 -->
				<div class="panel panel-default">
					<div class="panel-heading" style="height: 55px">
						<div class="pull-left">
							<font size="4" style="line-height: 35px"><strong>餐点热度</strong></font>
						</div>
						<div class="pull-right">

							<!-- 刷新按钮 -->
							<button class="btn btn-success"
								onclick="window.location.href='${pageContext.request.contextPath}/servlet/getItemHeatServlet'">
								<span class="glyphicon glyphicon-refresh"></span>刷&nbsp;新
							</button>
						</div>
					</div>
					<!-- 饼图 -->
					<div class="panel-body">
						<center>
							<div id="canvas-holder" style="width: 70%">
								<canvas id="chart-area"></canvas>
							</div>
						</center>
					</div>

					<!-- 详情表格 -->
					<table class="table table-striped" style="text-align: center">
						<tr>
							<td>名称</td>
							<td>类型</td>
							<td>点餐率</td>
						</tr>
						<c:forEach var="item" items="${items}" begin="0" end="3">
							<tr>
								<td>${item.name}</td>
								<td>${item.type}</td>
								<td>${item.percent}</td>
							</tr>
						</c:forEach>
						<tr>
							<td>其他餐点</td>
							<td></td>
							<td>${1-items[0].percent-items[1].percent-items[2].percent-items[3].percent-items[4].percent}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- 饼图js -->
	<script>
		var config = {
			type : 'pie',
			data : {
				datasets : [ {
					data : [${items[0].percent},${items[1].percent},${items[2].percent},${items[3].percent},${items[4].percent},
						${1-items[0].percent-items[1].percent-items[2].percent-items[3].percent-items[4].percent},],
					backgroundColor : [ window.chartColors.red,
							window.chartColors.orange,
							window.chartColors.yellow,
							window.chartColors.green, window.chartColors.blue, window.chartColors.grey, ],
					label : 'Dataset 1'
				} ],
				labels : [  "${items[0].name}","${items[1].name}","${items[2].name}","${items[3].name}","${items[4].name}", '其他餐点' ]
			},
			options : {
				responsive : true
			} 
		};

		window.onload = function() {
			var ctx = document.getElementById('chart-area').getContext('2d');
			window.myPie = new Chart(ctx, config);
		};
	</script>

</body>
</html>