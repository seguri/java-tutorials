package name.seguri.java.tutorials.springmvcinterceptor;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class MyInterceptorIntegrationTests extends AbstractIntegrationTests {

  @Autowired private TestRestTemplate testRestTemplate;

  @BeforeAll
  protected static void beforeAll() throws IOException {
    AbstractIntegrationTests.beforeAll();
  }

  @AfterAll
  protected static void afterAll() throws IOException {
    AbstractIntegrationTests.afterAll();
  }

  @Test
  public void contextLoads() {}
}
