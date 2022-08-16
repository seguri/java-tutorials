package name.seguri.java.tutorials;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum Flag {
  SIMPLE,
  WITH_UNDERSCORE;

  private static final Map<String, Flag> STRING_TO_ENUM =
      Stream.of(values()).collect(toMap(Object::toString, e -> e));

  public static Optional<Flag> from(String value) {
    var flag = Optional.ofNullable(STRING_TO_ENUM.get(value));
    System.out.println("Is there a flag for input '" + value + "'? " + flag.isPresent());
    return flag;
  }
}
