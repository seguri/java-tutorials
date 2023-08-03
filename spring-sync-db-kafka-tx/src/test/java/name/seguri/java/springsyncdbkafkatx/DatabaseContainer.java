package name.seguri.java.springsyncdbkafkatx;

import org.testcontainers.containers.PostgreSQLContainer;

/**
 * An alternative to {@link PostgreSQLInitializer} that uses a singleton container. You need to add
 * the following to your test class:
 *
 * <pre> @BeforeAll static void beforeAll() { DatabaseContainer.getInstance().start(); }</pre>
 */
public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {
  private static final String IMAGE_VERSION = "postgres:15.3";
  private static DatabaseContainer container;

  private DatabaseContainer() {
    super(IMAGE_VERSION);
  }

  public static DatabaseContainer getInstance() {
    if (container == null) {
      container = new DatabaseContainer();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("spring.datasource.url", container.getJdbcUrl());
    System.setProperty("spring.datasource.username", container.getUsername());
    System.setProperty("spring.datasource.password", container.getPassword());
    System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
  }

  @Override
  public void stop() {
    // do nothing, JVM handles shut down
  }
}
