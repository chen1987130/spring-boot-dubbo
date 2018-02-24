数据库脚本
见quartz_mysql.sql


--------------测试----------------
新增定时任务
http://localhost:8089/cronTask/addOrUpdate?type=Add&code=100&taskName=helloTask&clazz=com.feiniu.quartz.task.HelloTask
新增定时计划
http://localhost:8089/simplePlan/addOrUpdate?type=Add&planName=helloPlan&jobCode=100&repeatCount=-1&interval=5

