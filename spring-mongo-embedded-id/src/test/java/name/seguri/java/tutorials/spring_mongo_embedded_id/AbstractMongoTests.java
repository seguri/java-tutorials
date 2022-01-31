package name.seguri.java.tutorials.spring_mongo_embedded_id;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Tag("integration-testing")
@Testcontainers
public class AbstractMongoTests {

  static final MongoDBContainer mongoDBContainer;

  static {
    mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.8"));
    mongoDBContainer.start();
  }

  @Autowired protected MongoTemplate mongoTemplate;

  @DynamicPropertySource
  static void mongoDbProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @AfterEach
  protected void afterEach() {
    mongoTemplate.dropCollection(MainDocument.class);
  }
}
