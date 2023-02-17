package name.seguri.java.tutorials.springresilience4j;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.event.RateLimiterEvent;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.vavr.collection.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RateLimiterTest {
  private static final Logger logger = LoggerFactory.getLogger(RateLimiterTest.class);
  private static final int NUMBER_OF_REQUESTS = 10;
  private final List<RateLimiterEvent> events = new ArrayList<>();
  @Rule private final MockWebServer mockWebServer = new MockWebServer();
  @Autowired private RateLimiterRegistry rateLimiterRegistry;
  private RateLimiter sut;

  @BeforeEach
  void setUp() {
    sut = rateLimiterRegistry.rateLimiter("hello");
    captureEvents(sut.getEventPublisher());
    assertThat(events).isEmpty();
  }

  @Test
  public void test() throws InterruptedException {
    enqueue(NUMBER_OF_REQUESTS, "hello");

    final Runnable task = () -> get("/ratelimiter/hello");
    final var tasks =
        Stream.continually(task).map(Executors::callable).take(NUMBER_OF_REQUESTS).toJavaList();
    Executors.newFixedThreadPool(NUMBER_OF_REQUESTS).invokeAll(tasks, 10, SECONDS);

    assertThat(events.size()).isEqualTo(NUMBER_OF_REQUESTS);
  }

  private void captureEvents(final RateLimiter.EventPublisher eventPublisher) {
    eventPublisher.onEvent(events::add);
    eventPublisher.onSuccess(e -> logger.info(e.toString()));
    eventPublisher.onFailure(e -> logger.info(e.toString()));
  }

  private ExtractableResponse<Response> get(final String path) {
    return given().get(mockWebServer.url(path).toString()).then().extract();
  }

  private void enqueue(final int n, final String responseBody) {
    IntStream.range(0, n).forEach(i -> enqueue(responseBody));
  }

  private void enqueue(final String responseBody) {
    mockWebServer.enqueue(new MockResponse().setBody(responseBody));
  }
}
