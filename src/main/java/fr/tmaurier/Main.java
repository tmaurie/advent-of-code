package fr.tmaurier;

import fr.tmaurier.day1.SafeDial;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {

    List<String> instructions = readLinesFromResources();

    SafeDial dial = new SafeDial();

    int part1 = dial.countZeros(instructions, 50);
    int part2 = dial.countZerosAllClicks(instructions, 50);

    System.out.println("Part 1 (final positions only) : " + part1);
    System.out.println("Part 2 (all clicks)           : " + part2);
  }

  private static List<String> readLinesFromResources() throws IOException {
    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("input");

    if (in == null) {
      throw new IllegalArgumentException("Resource not found: input");
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      return br.lines().map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
  }
}
