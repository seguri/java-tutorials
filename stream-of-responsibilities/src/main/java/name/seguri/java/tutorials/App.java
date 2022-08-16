package name.seguri.java.tutorials;

import java.util.Optional;
import java.util.stream.Stream;

public class App {
  public static void main(String[] args) {
    String input = "with-underscore";
    Flag flag =
        Stream.of(Transformations.values())
            .map(Transformations::getTransformation)
            .map(f -> f.apply(input))
            .map(Flag::from)
            // From Stream<Optional<Flag>> to Stream<Stream<Flag>>, flattened to Stream<Flag>
            .flatMap(Optional::stream)
            .findFirst()
            .orElseThrow();
    System.out.println("Flag found: " + flag);
  }
}
