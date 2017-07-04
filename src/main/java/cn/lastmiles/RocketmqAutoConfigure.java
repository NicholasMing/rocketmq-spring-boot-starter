package cn.lastmiles;

import cn.lastmiles.rocketmq.Group;
import cn.lastmiles.rocketmq.RocketmqProperties;
import cn.lastmiles.rocketmq.Topic;
import cn.lastmiles.rocketmq.consumer.MsgListener;
import cn.lastmiles.rocketmq.consumer.RocketmqConsumerService;
import cn.lastmiles.rocketmq.producer.QueneProducerService;
import cn.lastmiles.rocketmq.producer.RocketmqProducerService;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置，按条件注入bean
 */

@Configuration
@EnableConfigurationProperties(RocketmqProperties.class)
public class RocketmqAutoConfigure {
    @Autowired
    private RocketmqProperties properties;

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq", value = "producer", havingValue = "true")
    DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(Group.PRODUCER_GROUP);
        producer.setNamesrvAddr(properties.getNameServerAdd());
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq", value = "producer", havingValue = "true")
    QueneProducerService queneService(DefaultMQProducer producer) {
        RocketmqProducerService queneService = new RocketmqProducerService();
        queneService.setProducer(producer);
        return queneService;
    }

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq", value = "consumer", havingValue = "true")
    MsgListener msgListener() {
        return new MsgListener();
    }

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq", value = "consumer", havingValue = "true")
    DefaultMQPushConsumer DefaultMQPushConsumer(MsgListener msgListener) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Group.CONSUMER_GROUP);
        consumer.setNamesrvAddr(properties.getNameServerAdd());
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        try {
            //消息过滤
            consumer.subscribe(Topic.LM_TOPIC, properties.getMsgFilter());

            //注册消息监听
            consumer.registerMessageListener(msgListener);

            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq", value = "consumer", havingValue = "true")
    RocketmqConsumerService consumerService(DefaultMQPushConsumer consumer) {
        RocketmqConsumerService consumerService = new RocketmqConsumerService();
        consumerService.setConsumer(consumer);
        return consumerService;
    }
}
