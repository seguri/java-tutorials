package name.seguri.java.tutorials.springboot_testcontainers_singleton;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.MongoDBContainer;

@Slf4j
abstract class AbstractContainerBaseTest {

  static final MongoDBContainer mongoDBContainer;

  static {
    log.debug("Starting container: mongo:3.6.14");
    mongoDBContainer = new MongoDBContainer("mongo:3.6.14");
    mongoDBContainer.start();
  }
}
