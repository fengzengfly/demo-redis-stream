package com.xiaofengstu.demoredisstream.mq;
/**
 * @ClassName ExceptionLogStreamListener
 * @Author fengzeng
 * @Date 2022/8/23 0023 17:25
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author Huhailong
 * @Description 监听消息
 * @Date 2021/3/10.
 */
@Slf4j
@Component
public class ListenerMessage implements StreamListener<String, MapRecord<String, String, String>> {

  @Override
  public void onMessage(MapRecord<String, String, String> entries) {
    log.info("接受到来自redis的消息");
    System.out.println("message id "+entries.getId());
    System.out.println("topic: "+entries.getStream());
    System.out.println("body:  "+entries.getValue());
  }

}


