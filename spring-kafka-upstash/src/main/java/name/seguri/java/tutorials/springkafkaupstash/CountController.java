package name.seguri.java.tutorials.springkafkaupstash;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountController {
  private final MapRepository repository;

  public CountController(MapRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/count")
  public int count() {
    return repository.count();
  }
}
