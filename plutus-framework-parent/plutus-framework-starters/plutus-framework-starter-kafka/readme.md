# Kafka组件

## 1. 支持配置Kafka多集群

#### 1. 多集群配置示例：

```yaml
spring:
  kafka:
    dynamic:
      primary: cluster1
      kafka:
        cluster1:
          bootstrap-servers: localhost:9092
          producer:
            key-serializer: org.apache.kafka.common.serialization.StringSerializer
            value-serializer: org.apache.kafka.common.serialization.StringSerializer
          consumer:
            key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
            value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
        cluster2:
          bootstrap-servers: localhost:9092
          producer:
            key-serializer: org.apache.kafka.common.serialization.StringSerializer
            value-serializer: org.apache.kafka.common.serialization.StringSerializer
          consumer:
            key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
            value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
```

#### 2. 多集群使用示例：

2.1 生产者使用方式：

```java
class KafkaTest {

    /**
     * 通过@Resource需要指定名字获取对应的KafkaTemplate
     */
    @Resource(name = "cluster2KafkaTemplate")
    private KafkaTemplate<String, String> cluster2KafkaTemplate;

    /**
     * 不指定名字时，默认获取primary配置的KafkaTemplate
     */
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    
}
```

2.2 消费者使用方式：

```java
class KafkaTest {

    /**
     * 要消费哪一个kafka消息，containerFactory就需要配成上面相对应的消费者监听容器工厂
     */
    @KafkaListener(
            containerFactory = "cluster2KafkaListenerContainerFactory",
            topics = {"consumer-topic"},
            groupId = "consumer-group")
    public void testConsumer(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        //do something
    }
    
}
```

## 2. 支持Kafka延时消费策略
