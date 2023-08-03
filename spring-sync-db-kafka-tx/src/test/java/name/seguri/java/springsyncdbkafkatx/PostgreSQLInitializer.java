package name.seguri.java.springsyncdbkafkatx;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class PostgreSQLInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  private static final String IMAGE_VERSION = "postgres:15.3";
  private static final PostgreSQLContainer DATABASE_CONTAINER =
      new PostgreSQLContainer(IMAGE_VERSION);

  static {
    DATABASE_CONTAINER.start();
  }

  @Override
  public void initialize(final ConfigurableApplicationContext applicationContext) {
    TestPropertyValues.of(
            "spring.datasource.url=" + DATABASE_CONTAINER.getJdbcUrl(),
            "spring.datasource.username=" + DATABASE_CONTAINER.getUsername(),
            "spring.datasource.password=" + DATABASE_CONTAINER.getPassword(),
            "spring.datasource.driver-class-name=org.postgresql.Driver")
        .applyTo(applicationContext.getEnvironment());
  }
}
