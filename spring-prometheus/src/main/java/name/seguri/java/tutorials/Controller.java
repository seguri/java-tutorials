package name.seguri.java.tutorials;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

  private final AtomicLong sequenceGenerator = new AtomicLong();
  private final MeterRegistry registry;
  private final RestTemplate restTemplate;

  public Controller(final MeterRegistry registry, final RestTemplate restTemplate) {
    this.registry = registry;
    this.restTemplate = restTemplate;
  }

  @GetMapping("/greeting")
  @Timed(
      value = "greeting.time",
      description = "Time taken to return greeting",
      percentiles = {0.5, 0.9})
  public Greeting greeting(
      @RequestParam(value = "name", defaultValue = "World") final String name) {
    final var id = sequenceGenerator.incrementAndGet();
    final var content = String.format("Hello, %s!", name);
    registry.counter("greeting.name", "name", name).increment();
    return new Greeting(id, content);
  }

  /**
   * Example of an endpoint that calls an external service. This should increase the
   * `http.client.requests` metric.
   */
  @GetMapping("/status/{statusCode}")
  public ResponseEntity<Void> status(@PathVariable final String statusCode) {
    // Without UriBuilder params, Prometheus will have a different metric for each status code.
    final var url = "https://httpbin.org/status/" + statusCode;
    final var res = restTemplate.getForEntity(url, String.class);
    return ResponseEntity.status(res.getStatusCode()).build();
  }
}
