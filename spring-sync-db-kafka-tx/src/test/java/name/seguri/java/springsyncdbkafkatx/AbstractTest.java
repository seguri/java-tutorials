package name.seguri.java.springsyncdbkafkatx;

import static name.seguri.java.springsyncdbkafkatx.UserMother.john;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = PostgreSQLInitializer.class)
public abstract class AbstractTest {
  @Autowired protected UserRepository userRepository;

  @AfterEach
  void afterEach() {
    userRepository.deleteAllInBatch();
  }

  protected User givenJohn() {
    return userRepository.save(john());
  }
}
