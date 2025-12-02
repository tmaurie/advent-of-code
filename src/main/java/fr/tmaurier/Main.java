package fr.tmaurier;

import fr.tmaurier.day1.SafeDial;
import fr.tmaurier.day2.GiftShop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {

    System.out.println("--- Day 1: Safe Dial ---");
    day1();
    System.out.println("--- Day 2: Gift Shop ---");
    day2();
  }

  private static void day1() throws IOException {
    List<String> instructions = readLinesFromResources();

    SafeDial dial = new SafeDial();

    int part1 = dial.countZeros(instructions, 50);
    int part2 = dial.countZerosAllClicks(instructions, 50);

    System.out.println("Part 1 (final positions only) : " + part1);
    System.out.println("Part 2 (all clicks)           : " + part2);
  }

  private static List<String> readLinesFromResources() throws IOException {
    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("day1.txt");

    if (in == null) {
      throw new IllegalArgumentException("Resource not found: day1.txt");
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      return br.lines().map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
  }

  private static void day2() throws IOException {
    String line = readSingleLineFromResources();

    GiftShop service = new GiftShop();
    List<GiftShop.Range> ranges = service.parseRanges(line);

    long answer = service.sumInvalidIdsOverRanges(ranges);

    System.out.println("Day 2 - Part 1 answer: " + answer);
  }

  private static String readSingleLineFromResources() throws IOException {
    ClassLoader cl = Main.class.getClassLoader();
    InputStream in = cl.getResourceAsStream("day2.txt");

    if (in == null) {
      throw new IllegalArgumentException("Resource non trouvée : " + "day2.txt");
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      // On suppose une seule ligne dans l'day1.txt AoC
      String line = br.readLine();
      if (line == null) {
        throw new IllegalStateException("Fichier vide : " + "day2.txt");
      }
      return line.trim();
    }
  }
}
