package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public final class ResourceUtils {

  private ResourceUtils() {
    // Hide default constructor
  }

  public static List<String> readResourceAsStrings(String resourcePath) {
    try (InputStream input = ResourceUtils.class.getResourceAsStream(resourcePath)) {
      return new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.toList());
    } catch (IOException e) {
      return List.of();
    }
  }
}
