package name.seguri.java.tutorials.springmvcinterceptor;

import io.restassured.RestAssured;
import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class AbstractIntegrationTests {
  private static MockWebServer mockWebServer;

  @LocalServerPort private int port;

  @BeforeAll
  protected static void beforeAll() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
  }

  @AfterAll
  protected static void afterAll() throws IOException {
    mockWebServer.shutdown();
  }

  // TODO Download sources
  @BeforeEach
  protected void beforeEach() {
    RestAssured.port = port;
  }
}
