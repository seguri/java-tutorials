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
  private final MapRepository mapRepository;

  public Consumer(MapRepository mapRepository) {
    this.mapRepository = mapRepository;
  }

  @KafkaListener(topics = "#{systemEnvironment['KAFKA_TOPIC']}")
  public void consume(Request data, @Header(OFFSET) Long offset) {
    log.info("[{}] {}", offset, data);
    mapRepository.save(offset, data);
  }

  @Override
  public void onPartitionsAssigned(
      Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
    callback.seekToBeginning(assignments.keySet());
  }
}
