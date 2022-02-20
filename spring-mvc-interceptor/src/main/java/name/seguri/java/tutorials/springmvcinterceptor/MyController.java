package name.seguri.java.tutorials.springmvcinterceptor;

import static name.seguri.java.tutorials.springmvcinterceptor.MyInterceptor.TRACING_HEADER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
  private static final Logger logger = LoggerFactory.getLogger(MyController.class);

  @GetMapping("/ok")
  public String ok(@RequestAttribute(TRACING_HEADER) String tracingHeader) {
    logger.info("[ok] {}={}", TRACING_HEADER, tracingHeader);
    return "OK";
  }
}
