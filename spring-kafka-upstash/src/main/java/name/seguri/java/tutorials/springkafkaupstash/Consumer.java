package name.seguri.java.tutorials.springkafkaupstash;

import static org.springframework.kafka.support.KafkaHeaders.OFFSET;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
  @KafkaListener(topics = "#{systemEnvironment['KAFKA_TOPIC']}")
  public void consume(String message, @Header(OFFSET) Long offset) {
    log.info("[{}] {}", offset, message);
  }
}
