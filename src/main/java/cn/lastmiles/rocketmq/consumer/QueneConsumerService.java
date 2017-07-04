package cn.lastmiles.rocketmq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * 消费者
 */
public interface QueneConsumerService {
    public void setConsumer(DefaultMQPushConsumer consumer);
}
