
----------------spring cloud (eureka + 配置服务器 + gateway)+ dubbo --------------- </br>
1.安装zookeeper,端口：2181 </br>
2.安装rabbitmq,端口：5672,用户名：guest,密码：guest </br>
3.启动zookeeper </br>
cd D:\work\zookeeper-3.4.8\bin </br>
d: </br>
zkServer.cmd </br>
4.启动rabbitmq </br>
cd D:\work\RabbitMQ\rabbitmq_server-3.6.12\sbin </br>
d: </br>
rabbitmq-server </br>
</br>
项目启动顺序 </br>
1.spring-boot-eureka </br>
2.spring-boot-config </br>
3.spring-boot-gateway </br>
4.spring-boot-dubbo-service </br>
5.spring-boot-dubbo-custom </br>
6.spring-boot-dubbo-app	 </br>
</br>
----------------测试--------------- </br>
#控制台地址 </br>
http://localhost:8761/ </br>
</br>
#刷新配置 </br>
(POST) http://localhost:9080/bus/refresh?destination=spring-boot-dubbo-app </br>
</br>
#测试地址 </br>
http://localhost:9085/spring-boot-dubbo-app/hello/add </br>
http://localhost:9085/spring-boot-dubbo-app/hello/properties </br>
</br>
#查看动态配置 </br>
http://localhost:9080/spring-boot-dubbo-app/dev </br>
{spring-boot-dubbo-app} 项目 </br>
{dev} 环境 </br>
</br>
#查看路由情况 </br>
http://localhost:9085/routes </br>
</br>
#健康检查 </br>
http://localhost:9085/health </br>
</br>
#动态监控 </br>
http://localhost:9085/hystrix.stream </br>
</br>
</br>
其他项目 </br>
1.spring-boot-data </br>
集成kafka、mongo、Redis,并提供简单的数据读取方式 </br>
2.spring-boot-data-rest </br>
Restful 风格访问数据库 </br>
3.spring-boot-framework </br>
基础工具 </br>
4.spring-boot </br>
集成 mybatis，依赖spring-boot-framework </br>
数据库脚本见resources/sql_data.sql </br>
<a href="https://github.com/chen1987130/generate-code">mybatis配置生产器</a> </br>
5.spring-boot-oauth </br>
安全验证 </br>
6.spring-boot-quartz </br>
集成quartz，前台界面未开发完 </br>
7.spring-boot-session </br>
通过Redis服务器，实现多服务器session同步 </br>
8.spring-boot-solr </br>
集成solr </br>

