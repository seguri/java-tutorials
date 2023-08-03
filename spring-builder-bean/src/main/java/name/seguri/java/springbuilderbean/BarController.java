package name.seguri.java.springbuilderbean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

  private final User.Builder userBuilder;

  public BarController(final User.Builder userBuilder) {
    this.userBuilder = userBuilder;
  }

  @GetMapping("/bar")
  public String foo() {
    return userBuilder.withName("John Bar").withEmail("bar@example.com").build().toString();
  }
}
