package name.seguri.java.springbuilderbean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BazController {

  private final User.Builder userBuilder;

  public BazController(final User.Builder userBuilder) {
    this.userBuilder = userBuilder;
  }

  @GetMapping("/baz")
  public String foo() {
    return userBuilder.build().toString();
  }
}
