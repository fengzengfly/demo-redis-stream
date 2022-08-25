package com.xiaofengstu.demoredisstream.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;

/**
 * @ClassName RedisStreamConfig
 * @Author fengzeng
 * @Date 2022/8/23 0023 17:28
 */
@Configuration
public class RedisStreamConfig {

  @Autowired
  private ListenerMessage streamListener;

  @Bean
  public Subscription subscription(RedisConnectionFactory factory) {
    var options = StreamMessageListenerContainer
        .StreamMessageListenerContainerOptions
        .builder()
        .pollTimeout(Duration.ofSeconds(1))
        .build();

    var listenerContainer = StreamMessageListenerContainer.create(factory, options);

    var subscription = listenerContainer.receiveAutoAck(Consumer.from("mygroup", "fengzeng"),
        StreamOffset.create("mystream", ReadOffset.lastConsumed()), streamListener);

    listenerContainer.start();
    return subscription;
  }
}
