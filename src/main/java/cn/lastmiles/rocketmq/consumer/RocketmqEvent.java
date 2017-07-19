package cn.lastmiles.rocketmq.consumer;

import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

/**
 * 基于spring事件传播机制
 */
public class RocketmqEvent extends ApplicationEvent {
    private MessageExt msg;
    private String topic;
    private String tag;

    public RocketmqEvent(MessageExt msg) throws Exception {
        super(msg);
        this.msg = msg;
        this.topic = msg.getTopic();
        this.tag = msg.getTags();
    }

    public MessageExt getMsg() {
        return msg;
    }

    public void setMsg(MessageExt msg) {
        this.msg = msg;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
