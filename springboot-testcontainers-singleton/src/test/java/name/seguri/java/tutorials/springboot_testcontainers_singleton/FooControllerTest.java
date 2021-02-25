package name.seguri.java.tutorials.springboot_testcontainers_singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FooControllerTest {

  @LocalServerPort private int port;

  @Autowired private FooController fooController;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void contextLoads() {
    assertThat(fooController).isNotNull();
  }

  @Test
  void getFoo() {
    final var url = "http://localhost:" + port + "/foo/";
    assertThat(restTemplate.getForObject(url, String.class)).isEqualTo("foo");
  }
}
