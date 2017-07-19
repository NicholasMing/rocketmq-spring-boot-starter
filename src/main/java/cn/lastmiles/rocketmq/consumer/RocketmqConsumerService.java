package cn.lastmiles.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * 消费者
 */
public class RocketmqConsumerService implements QueneConsumerService {
    private DefaultMQPushConsumer consumer;

    public void setConsumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;

    }
}
