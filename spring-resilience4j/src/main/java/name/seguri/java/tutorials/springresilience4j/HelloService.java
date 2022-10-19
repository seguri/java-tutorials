package name.seguri.java.tutorials.springresilience4j;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  @RateLimiter(name = "hello")
  public String hello() {
    return "hello";
  }
}
