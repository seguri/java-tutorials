package name.seguri.java.tutorials;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final AtomicLong sequenceGenerator = new AtomicLong();
  private final MeterRegistry registry;

  public Controller(MeterRegistry registry) {
    this.registry = registry;
  }

  @GetMapping("/greeting")
  @Timed(
      value = "greeting.time",
      description = "Time taken to return greeting",
      percentiles = {0.5, 0.9})
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    final var id = sequenceGenerator.incrementAndGet();
    final var content = String.format("Hello, %s!", name);
    registry.counter("greeting.name", Tags.of("name", name)).increment();
    return new Greeting(id, content);
  }
}
