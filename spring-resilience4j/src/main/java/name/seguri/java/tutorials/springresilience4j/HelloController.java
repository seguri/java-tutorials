package name.seguri.java.tutorials.springresilience4j;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratelimiter")
public class HelloController {

  private final HelloService service;

  public HelloController(HelloService service) {
    this.service = service;
  }

  /** Here it's the service to be rate-limited. */
  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    try {
      return ResponseEntity.ok(service.hello());
    } catch (RequestNotPermitted e) {
      return ResponseEntity.status(TOO_MANY_REQUESTS).build();
    }
  }

  /** Here we rate-limit the controller. */
  @RateLimiter(name = "hello")
  @GetMapping("/world")
  public String world() {
    return "world";
  }
}
