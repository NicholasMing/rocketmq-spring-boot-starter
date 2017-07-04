package cn.lastmiles.rocketmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性配置
 */
@ConfigurationProperties(prefix = "rocketmq")
public class RocketmqProperties {
    private String nameServerAdd;
    private String producer;
    private String consumer;
    private String msgFilter;

    public String getNameServerAdd() {
        return nameServerAdd;
    }

    public void setNameServerAdd(String nameServerAdd) {
        this.nameServerAdd = nameServerAdd;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getMsgFilter() {
        return msgFilter;
    }

    public void setMsgFilter(String msgFilter) {
        this.msgFilter = msgFilter;
    }
}