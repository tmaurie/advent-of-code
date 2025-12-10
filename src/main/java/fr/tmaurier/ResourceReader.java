package fr.tmaurier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public final class ResourceReader {

  private ResourceReader() {}

  public static List<String> readNonBlankTrimmedLines(String resourceName) throws IOException {
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(requireResource(resourceName)))) {
      return br.lines().map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
  }

  public static String readFirstLineTrimmed(String resourceName) throws IOException {
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(requireResource(resourceName)))) {
      String line = br.readLine();
      if (line == null) throw new IllegalStateException("Empty file: " + resourceName);
      return line.trim();
    }
  }

  public static List<String> readAllLines(String resourceName) throws IOException {
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(requireResource(resourceName)))) {
      return br.lines().toList();
    }
  }

  private static InputStream requireResource(String resourceName) {
    InputStream in = ResourceReader.class.getClassLoader().getResourceAsStream(resourceName);
    if (in == null) throw new IllegalArgumentException("Resource not found: " + resourceName);
    return in;
  }
}
