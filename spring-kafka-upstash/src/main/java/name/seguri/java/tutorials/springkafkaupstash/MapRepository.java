package name.seguri.java.tutorials.springkafkaupstash;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.Objects.requireNonNullElse;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MapRepository {
  private static final Logger logger = LoggerFactory.getLogger(MapRepository.class);
  private final HTreeMap<Long, Request> db;

  public MapRepository(@Value("${app.dir}") final String appDir) {
    db =
        DBMaker.fileDB(new File(appDir, "requests.db"))
            .transactionEnable()
            .closeOnJvmShutdown()
            .make()
            .hashMap("requests", Serializer.LONG, Serializer.JAVA)
            .counterEnable()
            .createOrOpen();
  }

  public void save(final Long offset, final Request request) {
    db.put(offset, request);
  }

  public int count() {
    return db.size();
  }

  public Request get(final Long offset) {
    return db.get(offset);
  }

  public Set<Map.Entry<Long, Request>> entrySet() {
    return db.entrySet();
  }

  public Stream<Request> values() {
    return entrySet().stream().sorted(Map.Entry.comparingByKey()).map(Map.Entry::getValue);
  }

  public Map<String, Long> countByIp(final Long limit) {
    return db.values().stream()
        .filter(req -> !req.country().equals("IT"))
        .filter(req -> !req.country().equals("CH"))
        .map(Request::ip)
        .collect(groupingBy(identity(), counting()))
        .entrySet()
        .stream()
        .sorted(comparingByValue(reverseOrder()))
        .limit(requireNonNullElse(limit, Long.MAX_VALUE))
        .collect(toSortedMap());
  }

  @NotNull private static Collector<Map.Entry<String, Long>, ?, LinkedHashMap<String, Long>> toSortedMap() {
    return toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (oldValue, newValue) -> oldValue,
        LinkedHashMap::new);
  }

  @PostConstruct
  public void log() {
    logger.info("Current records: {}", count());
  }
}
