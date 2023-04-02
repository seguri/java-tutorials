package name.seguri.java.tutorials.springkafkaupstash;

import static org.springframework.kafka.support.KafkaHeaders.OFFSET;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer implements ConsumerSeekAware {

  @KafkaListener(topics = "#{systemEnvironment['KAFKA_TOPIC']}")
  public void consume(String message, @Header(OFFSET) Long offset) {
    log.info("[{}] {}", offset, message);
  }

  @Override
  public void onPartitionsAssigned(
      Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
    callback.seekToBeginning(assignments.keySet());
  }
}
