package name.seguri.java.tutorials;

import java.util.function.Function;

public enum Transformations {
  IDENTITY(Function.identity()),
  CASE_INSENSITIVE(String::toUpperCase),
  TO_SNAKE(s -> s.replace("-", "_")),
  TO_UPPER_SNAKE(CASE_INSENSITIVE.getTransformation().compose(TO_SNAKE.getTransformation()));

  private final Function<String, String> transformation;

  Transformations(Function<String, String> transformation) {
    this.transformation = transformation;
  }

  public Function<String, String> getTransformation() {
    return transformation;
  }
}
