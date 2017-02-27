# examsys
一个简单的考试应用

我的目的是有一个简单的需求，然后用各种各样的技术去实现它。
已达到快速了解各个技术的目的。

需求：

1.考试类别浏览画面，点击考试类别，进入考试画面。

2.考试画面分为倒计时，考题总览，考试内容区，最后交卷自动打分。

3.考试结构分析页面，按照试题类型维度划分出饼图，显示正确和错误的比例。

4.考试结果列表查询画面，可以按照用户名和考试类型进行查询。
  
第一个版本：

    后台：spring mvc，mybatis，spring cache
 
    前台：jquery，bootstrap, eCharts
    
    状态：初版完成
    
第二个版本：

	使用dubbo来进行异步远程rpc调用，用mangoDB记录每次提交试卷的状态
	
	状态：初版完成

第三个版本：

	前端使用Vue2来作出成绩列表画面，并使用websocket来和服务器进行通讯
	
	（从业务场景的角度来说，websocket长连接并不适合，只是从熟悉技术的角度来练练手）
	
	状态：未开始
	
第五个版本：

	缓存使用redis
	
	状态：未开始
	
第六个版本：

	保存并计算考试成绩时，使用kafka消息队列来实现，生产消息，由其他服务来消费消息
	
	状态：未开始

运行步骤：

1.启动mysql

2.启动mongoDB : ~/Documents/mongodb/bin/mongod --dbpath /Users/hongyuhang/Documents/data/db/

3.启动zookeeper : ~/Documents/zookeeper/bin/zkServer.sh start

4.启动examsys.logic工程

5.启动examsys.first工程