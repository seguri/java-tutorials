package name.seguri.java.tutorials.springkafkaupstash;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
  private static final Logger logger = LoggerFactory.getLogger(DataController.class);
  private static final SimpleDateFormat EPOCH_TO_DATE = new SimpleDateFormat("MM-dd HH:mm:ss");
  private final MapRepository repository;

  public DataController(final MapRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/count")
  public int count() {
    return repository.count();
  }

  @GetMapping("/get/{offset}")
  public Request get(@PathVariable final Long offset) {
    requireNonNull(offset, "offset is required");
    return repository.get(offset);
  }

  @GetMapping("/show")
  public String show() {
    final var out = new StringBuilder();
    repository
        .values()
        .forEach(
            req ->
                out.append(EPOCH_TO_DATE.format(new Date(req.timestamp())))
                    .append(" ip=")
                    .append(req.paddedIp())
                    .append(" country=")
                    .append(req.country())
                    .append(" city=")
                    .append(req.paddedCity())
                    .append(" url=")
                    .append(req.url())
                    .append("\n"));
    return out.toString();
  }

  @GetMapping("/count-by-ip")
  public String countByIp(@RequestParam(required = false) final Long limit) {
    final var out = new StringBuilder();
    repository
        .countByIp(limit)
        .forEach((ip, count) -> out.append(count).append(" ").append(ip).append("\n"));
    return out.toString();
  }
}
