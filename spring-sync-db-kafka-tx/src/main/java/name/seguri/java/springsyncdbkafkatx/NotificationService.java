package name.seguri.java.springsyncdbkafkatx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
  private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

  public void sendNotification(final User user) {
    logger.info("Sending notification to {}", user);
  }
}
