package name.seguri.java.springsyncdbkafkatx;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {
  private static final String TOPIC = "users";

  private final KafkaTemplate<String, User> kafkaTemplate;

  public UserProducer(final KafkaTemplate<String, User> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendUser(final User user) {
    final var unused = kafkaTemplate.send(TOPIC, user.getId().toString(), user);
  }
}
