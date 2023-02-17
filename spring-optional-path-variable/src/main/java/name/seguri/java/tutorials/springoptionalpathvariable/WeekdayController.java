package name.seguri.java.tutorials.springoptionalpathvariable;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeekdayController {

  @GetMapping(value = {"/weekday", "/weekday/{dateVar}"})
  String weekday(@PathVariable Optional<String> dateVar) {
    var date = dateVar.map(LocalDate::parse).orElseGet(() -> LocalDate.now(ZoneOffset.UTC));
    return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
  }
}
