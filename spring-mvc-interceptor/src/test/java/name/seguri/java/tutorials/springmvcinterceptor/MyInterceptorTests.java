package name.seguri.java.tutorials.springmvcinterceptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MyInterceptorTests {

  @Autowired private TestRestTemplate testRestTemplate;

  @Test
  public void ok() {
    var response = testRestTemplate.getForEntity("/ok", String.class);

    assertThat(response.getBody()).isEqualTo("OK");
  }
}
