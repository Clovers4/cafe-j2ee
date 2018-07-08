# coffee-javaweb
This is a java-web about cafe order system.The only one frame is Bootstrap.I use this mode in it——JSP+Filter+Servlet+Service+DAO+Domain+Mysql.Also use DBCP and some jar to simplify the code construction.

一个Java-Web，只使用了前端框架Boostrap。由于是第一次接触Java-Web，没有使用SpringBoot来进行快速搭建。使用的为较传统的MVC模式来做，JSP+Filter+Servlet+Service+DAO+Domain+Mysql，并使用了Apache的Common系列的包来简化JDBC的部分。

功能如下：

数据库建表，服务器平台搭建，网页界面设计

用户登录，包括管理员、用户  

修改个人信息，电话，邮箱，密码等

管理员可对用户进行管理：增加、修改、删除用户

菜单的录入

餐点信息包括：

  1.餐点名称		2.餐点类型（饮料、小吃、主食）
  
  3.库存数量 		4 .餐点单价   
  
菜单的修改、删除

根据类型或关键字筛选相关菜单

用户登录，选择商品加入购物车

购物车商品数量的修改、删除

订单提交，库存数量修改

历史订单查询，根据用户号或者用户信息查询用户历史订单

餐点欢迎度分析，统计各类餐点的选购频率，按频率排序

菜单可显示图片
