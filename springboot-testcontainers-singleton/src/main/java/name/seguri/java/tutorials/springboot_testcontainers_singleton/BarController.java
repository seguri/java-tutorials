package name.seguri.java.tutorials.springboot_testcontainers_singleton;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BarController {

  @GetMapping("/bar")
  String get() {
    return "bar";
  }
}
