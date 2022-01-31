package name.seguri.java.tutorials.spring_mongo_embedded_id;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MainDocument {

  private final String name = RandomStringUtils.randomAlphabetic(12);

  public String getName() {
    return name;
  }
}
