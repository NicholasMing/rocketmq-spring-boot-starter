package cn.lastmiles.rocketmq.producer;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;

/**
 * 生产者
 */
public interface QueneProducerService {
    public void setProducer(DefaultMQProducer producer);

    public SendResult sendMessages(String msg, String tag);

    public void shutdown();
}
