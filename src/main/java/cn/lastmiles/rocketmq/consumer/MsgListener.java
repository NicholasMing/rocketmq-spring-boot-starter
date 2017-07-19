package cn.lastmiles.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

/**
 * 消息监听
 */
public class MsgListener implements MessageListenerConcurrently {
    @Autowired
    private ApplicationEventPublisher publisher;

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        //在push模式，list.size等于1
        for (MessageExt messageExt : list) {
            try {
                //将消息交给spring使用事件机制处理
                publisher.publishEvent(new RocketmqEvent(messageExt));
            } catch (Exception e) {
                if (messageExt.getReconsumeTimes() == 3) {
                    //多次重试消费失败后，持久化操作
                    System.out.println("重试结束");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                System.out.println("消费失败，当前次数：" + messageExt.getReconsumeTimes());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

