package name.seguri.java.springbuilderbean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

  private final User.Builder userBuilder;

  public FooController(final User.Builder userBuilder) {
    this.userBuilder = userBuilder;
  }

  @GetMapping("/foo")
  public String foo() {
    return userBuilder.withName("John Foo").withEmail("foo@example.com").build().toString();
  }
}
