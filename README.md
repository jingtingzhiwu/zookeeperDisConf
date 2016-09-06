# zookeeperDisConf
基于zkclient.jar实现的分布式配置文件部署项目
publisher服务端：控制新版本配置发布

saver客户端：控制版本或分支更新

实现自动更新，而不是手动pull或定时pull

比disconf和淘宝Diamon更轻量

=====================================
ZK_ADDRESS=198.11.180.30:2181	#配置服务器的地址


服务端执行zkpublisher，将配置发布到198.11.180.30:2181

客户端执行client，自动从198.11.180.30:2181更新配置到本机


![image](https://github.com/jingtingzhiwu/zookeeperDisConf/blob/master/src/main/resources/snapshot.png)