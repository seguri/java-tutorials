package name.seguri.java.tutorials.spring_mongo_embedded_id;

import org.apache.commons.lang3.RandomStringUtils;

public class SecondaryDocument {

  private final String name = RandomStringUtils.randomAlphabetic(12);

  public String getName() {
    return name;
  }
}
