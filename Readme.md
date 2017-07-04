# RocketMQ快速开始
## 一、添加依赖
```
<dependency>
        <groupId>cn.lastmiles</groupId>
        <artifactId>rocketmq-spring-boot-starter</artifactId>
        <version>1.0</version>
 </dependency>
```
## 二、在application.properties中添加配置
rocketmq.producer=true

rocketmq.consumer=true

rocketmq.name-server-add=192.168.82.99:9876;192.168.82.99:9877

rocketmq.msg-filter=*
>消息过滤默认为*,可选

## 三、如何使用
### 1、生产者
    注入QueneProducerService接口
    调用sendMessages方法

### 2、消费者
    添加 @EventListener 注解 
    通过MessageExt messageExt = event.getMsg();获取message
>注意异常处理，消费失败需要记录日志，默认重试3次