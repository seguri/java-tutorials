package name.seguri.java.tutorials.springmvcinterceptor;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MyInterceptorTests {

  @LocalServerPort int port;

  @Autowired private TestRestTemplate testRestTemplate;

  @BeforeEach
  public void setup() {
    RestAssured.port = port;
  }

  @Test
  public void ok() {
    var response = testRestTemplate.getForEntity("/ok", String.class);

    assertThat(response.getBody()).isEqualTo("OK");
  }

  @Test
  public void ok_restassured() {
    get("/ok").then().body(equalTo("OK"));
  }
}
