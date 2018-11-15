# 一个简易的模拟咖啡厅点餐系统  
## 主要使用了JSP+Servlet+Mysql来进行开发，不含后端框架  
### 其他技术：  
    1. Bootstrap做前端美化工作  
    2. 使用Apache的Common系列的包来简化JDBC的部分  
    3. 还用到了JSTL，jQuery,Chart.js等来优化页面  
## 基本功能：  
    1. 用户注册，用户/管理员登录  
    2. 用户修改个人信息、密码  
    3. 管理员对用户进行增删改查（分页）  
    4. 管理员对餐点进行增删改查（分页），可上传图片  
    5. 根据餐点的类型/名称关键词进行搜索（分页）  
    6. 用户将餐点添加到购物车中  
    7. 在购物车中可对餐点的数量进行增加/删除餐点  
    8. 下订单（检查/修改库存）
    9. 历史订单查询（用户/管理员），管理员可以通过用户名/订单号进行查询
    10. 餐点欢迎度（点餐率）分析，生成图表显示
### 难点：
    1. 环境配置（Tomcat与JDK的版本使用问题，DBCP/C3P0数据库连接池的配置问题）
    2. 登录（会话）维持————session/cookies
    3. 分页显示————PageModel/jdbc-mysql查

### 实现图
  ![主页](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/index.jpg)
  ![登录](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/login.jpg)
  ![注册](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/register.jpg)
  ![修改个人信息](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/user-modify-info.jpg)
  ![搜索餐点](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/search.jpg)
  ![购物车](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/shoppingcart.jpg)
  ![历史订单查看详情](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/user-history-order-details.jpg)
  ![购买餐点](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/item-logined.jpg)
  ![查看餐点热度](https://github.com/Clovers4/coffee-javaweb/blob/master/resouce/sample/items-heat.jpg)


### 1. 准备工作  

#### 1.1 学习javaweb相关知识。  
  - 考虑到第一次制作，就不使用后端框架进行开发了，不过开发到后期，确实意识到使用Spring框架的AOP特性能更好地管理日志，MyBatis等ORM框架也能减少繁琐的JDBC的代码。  
#### 1.2 查询相关网站，寻找一些基本思路以及材料
  - 由于该项目是学习任务且缺少美工，可以利用[星巴克中国官网](https://www.starbucks.com.cn/) 来提取一些图片资料进行加快进度。
#### 1.3 绘制基本思路流程，考虑架构，分清各个层次的任务  
  - 对web开发、html知识尚有欠缺，一边学习html/css/js，同时弄清楚JSP/Servlet/Filter/Listner的用处以及DAO/Service层的职责。绘制流程图，使思路更加清晰。 
#### 1.4 配置相关环境
  - 搭建好jdk1.8，tomcat8.0，mysql8.0的环境，并进行测试可用  
  - 使用Navicat作为Mysql的可视化工具  
  - 使用GitHub作为版本控制的工具

  

### 2. 开发
#### 2.1 以域名反写的规则新建各个包，划清职责，确立代码规范
  - 项目的基本架构和代码规范非常重要，需要保证后期功能增多之后，尽量减少出现职责不一，debug困难的情况。
#### 2.2 利用Navicat生成模型，划分各个表的职责
  - 比较纠结的是各个字段的名字，规范。
#### 2.3 制作基本工具类（JDBCUtil），基本Filter
  - 使用了DBCP数据池连接池来优化连接（C3P0配置的过程出现问题，无法解决）  
  - 同时配合TransactionFilter来实现进入Servlet之前开启事务，退出Servlet之后Commit/Rollback的功能  
  - 使得Service层和Dao层能够更专注其自身的职责，不需要逐层获取Connection，而是通过将Connection利用ThreadLocal绑定的方式来获取。  
  - 此外，还有CharacterEncodingFilter/NoCacheFilter等。这里没有使用Spring提供的Filter。 
#### 2.4 前后端交替开发（1人）
  - 由于本身就没有进行前后端分离，且对web开发流程尚不熟悉，该项目是独自开发的。
  - 参考[孤傲苍狼的javaweb学习总结（教学）](https://www.cnblogs.com/xdp-gacl/category/574705.html)等资料逐步实现各模块/层次的功能。  
  - 基本流程为编写网页->PO/VO/FormBean->DAO->Service->Servlet  
  - 同时使用BootStrap开发各网页,js只用来做了前端表单验证/反馈的功能。  
  - 使用bootstrap fileinput插件美化了图片上传功能，使用Chart.js制作了网页图表显示的功能。

  

### 3. 完善/优化
#### 3.1 注释完善
#### 3.2 命名调整
#### 3.3 Debug  

  

### 不足与进阶
####  不足
  1. 命名与注释到后期略显不足，需要改善
  2. Service层与Controller层的设计有问题，方法和职责的分配都有点问题。
  3. 复杂度太高，且有大量冗余代码（如：日志等）根据Eclipse搜索"\n"的结果来看，除去引入的css/js文件，代码量似乎达到了1W行。
  4. 前后端耦合度太高，复用性太低。
  5. VO与entity层是不同的，此处本来应该用DTO来做。
#### 进阶
  1. 补充/系统地学习一遍html/css/js。可以考虑使用Vue.js对前端代码进行优化。
  2. 可以考虑使用Spring的AOP特性来对硬编码的日志记录代码进行优化。
  3. 可以考虑使用Maven来管理jar包
  4. 可以考虑使用Mybatis来完成数据持久化工作




