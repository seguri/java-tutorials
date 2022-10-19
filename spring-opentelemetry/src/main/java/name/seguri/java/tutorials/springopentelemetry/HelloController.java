package name.seguri.java.tutorials.springopentelemetry;

import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {
  private static final Logger L = LoggerFactory.getLogger(HelloController.class);

  @GetMapping("/hello")
  String hello() {
    final var ctx = Span.current().getSpanContext();
    L.info("spanId={} traceId={}", ctx.getSpanId(), ctx.getTraceId());
    return "Hello";
  }
}
