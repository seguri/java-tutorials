package name.seguri.java.tutorials;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

  /**
   * This is required so that we can use the @Timed annotation on methods that we want to time. See:
   * https://micrometer.io/docs/concepts#_the_timed_annotation
   */
  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }
}
