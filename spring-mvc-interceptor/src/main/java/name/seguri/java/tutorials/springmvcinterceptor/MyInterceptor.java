package name.seguri.java.tutorials.springmvcinterceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class MyInterceptor implements HandlerInterceptor {
  public static final String TRACING_HEADER = "X-Segu-Trace-Id";
  private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

  @Override
  public boolean preHandle(
      final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    logger.info("[preHandle] {} {}", request.getMethod(), request.getRequestURI());
    request.setAttribute(TRACING_HEADER, UUID.randomUUID().toString());
    return true;
  }
}
