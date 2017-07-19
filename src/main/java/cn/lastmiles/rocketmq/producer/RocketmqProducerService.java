package cn.lastmiles.rocketmq.producer;

import cn.lastmiles.rocketmq.Topic;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 生产者
 */
public class RocketmqProducerService implements QueneProducerService {
    private DefaultMQProducer producer;

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public SendResult sendMessages(String msg, String tag) {
        SendResult sendResult = null;
        Message message = new Message(Topic.LM_TOPIC, tag, msg.getBytes());
        try {
            sendResult = producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    public void shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                producer.shutdown();
            }
        }));
        System.exit(0);
    }
}
