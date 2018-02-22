1.安装zookeeper,端口：2181
2.安装rabbitmq,端口：5672,用户名：guest,密码：guest
3.启动zookeeper
cd D:\work\Redis-x64-3.0.504
d:
redis-server.exe
4.启动rabbitmq
cd D:\work\RabbitMQ\rabbitmq_server-3.6.12\sbin
d:
rabbitmq-server

项目启动顺序
1.spring-boot-eureka
2.spring-boot-config
3.spring-boot-gateway
4.spring-boot-dubbo-service
5.spring-boot-dubbo-custom
6.spring-boot-dubbo-app	

----------------测试---------------
#控制台地址
http://localhost:8761/

#刷新配置
(POST) http://10.211.240.120:9080/bus/refresh?destination=spring-boot-dubbo-app

#测试地址
http://10.211.240.120:9085/spring-boot-dubbo-app/hello/add
http://10.211.240.120:9085/spring-boot-dubbo-app/hello/properties

#查看动态配置
http://10.211.240.120:9080/spring-boot-dubbo-app/dev
{spring-boot-dubbo-app} 项目
{dev} 环境

#查看路由情况
http://10.211.240.120:9085/routes

#健康检查
http://10.211.240.120:9085/health

#动态监控
http://10.211.240.120:9085/hystrix.stream



