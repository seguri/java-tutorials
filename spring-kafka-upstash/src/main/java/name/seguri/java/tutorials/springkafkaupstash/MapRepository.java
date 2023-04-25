package name.seguri.java.tutorials.springkafkaupstash;

import java.io.File;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MapRepository {
  private final HTreeMap<Long, Request> db;

  public MapRepository(@Value("${app.dir}") String appDir) {
    db =
        DBMaker.fileDB(new File(appDir, "requests.db"))
            .transactionEnable()
            .closeOnJvmShutdown()
            .make()
            .hashMap("requests", Serializer.LONG, Serializer.JAVA)
            .counterEnable()
            .createOrOpen();
  }

  public void save(Long l, Request value) {
    db.put(l, value);
  }

  public int count() {
    return db.size();
  }
}
