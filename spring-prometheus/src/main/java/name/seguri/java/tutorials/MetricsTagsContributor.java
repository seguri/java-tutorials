package name.seguri.java.tutorials;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsContributor;
import org.springframework.stereotype.Component;

@Component
public class MetricsTagsContributor implements WebMvcTagsContributor {

  @Override
  public Iterable<Tag> getTags(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler,
      final Throwable exception) {
    return Tags.of("foo", "bar");
  }

  @Override
  public Iterable<Tag> getLongRequestTags(final HttpServletRequest request, final Object handler) {
    return null;
  }
}
