package name.seguri.java.tutorials.springoptionalpathvariable;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeekdayControllerTest {
  @Value(value = "${local.server.port}")
  private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void weekday_noInput_today() {
    var res = restTemplate.getForObject(urlFor("/weekday"), String.class);

    assertThat(res).contains(getTodayWeekday());
  }

  @Test
  void weekday_validDate_success() {
    var res = restTemplate.getForObject(urlFor("/weekday/2022-01-28"), String.class);

    assertThat(res).contains("Friday");
  }

  @Test
  void weekday_invalidDate_fail() {
    var res = restTemplate.getForEntity(urlFor("/weekday/2000-02-40"), String.class);

    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  private String urlFor(String path) {
    return Optional.ofNullable(path)
        .filter(p -> p.startsWith("/"))
        .map(p -> "http://localhost:" + port + p)
        .orElseThrow(
            () -> new IllegalArgumentException("'" + path + "': path must start with a slash"));
  }

  private String getTodayWeekday() {
    return LocalDate.now(ZoneOffset.UTC).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
  }
}
