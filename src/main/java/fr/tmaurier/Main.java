package fr.tmaurier;

import fr.tmaurier.day1.SafeDial;
import fr.tmaurier.day2.GiftShop;
import fr.tmaurier.day3.BatteryBanks;
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

    System.out.println("--- Day 3: Battery Banks ---");
    day3();
  }

  private static void day1() throws IOException {
    List<String> instructions = readLinesFromResource("day1.txt");
    SafeDial dial = new SafeDial();

    System.out.println("Day 1 - Part 1 answer: " + dial.countZeros(instructions, 50));
    System.out.println("Day 1 - Part 2 answer: " + dial.countZerosAllClicks(instructions, 50));
  }

  private static void day2() throws IOException {
    String line = readSingleLineFromResource("day2.txt");
    GiftShop service = new GiftShop();
    List<GiftShop.Range> ranges = service.parseRanges(line);

    System.out.println("Day 2 - Part 1 answer: " + service.sumInvalidIdsOverRanges(ranges));
    System.out.println("Day 2 - Part 2 answer: " + service.sumInvalidIdsOverRangesPart2(ranges));
  }

  private static void day3() throws IOException {
    List<String> banks = readLinesFromResource("day3.txt");
    BatteryBanks service = new BatteryBanks();
    long total1 = service.totalMaxJoltage(banks, 2);
    long total2 = service.totalMaxJoltage(banks, 12);

    System.out.println("Day 3 - Part 1 answer: " + total1);
    System.out.println("Day 3 - Part 2 answer: " + total2);
  }

  private static List<String> readLinesFromResource(String resourceName) throws IOException {
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(getResourceAsStream(resourceName)))) {
      return br.lines().map(String::trim).filter(s -> !s.isEmpty()).toList();
    }
  }

  private static String readSingleLineFromResource(String resourceName) throws IOException {
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(getResourceAsStream(resourceName)))) {
      String line = br.readLine();
      if (line == null) throw new IllegalStateException("Fichier vide : " + resourceName);
      return line.trim();
    }
  }

  private static InputStream getResourceAsStream(String resourceName) {
    InputStream in = Main.class.getClassLoader().getResourceAsStream(resourceName);
    if (in == null) throw new IllegalArgumentException("Ressource non trouvée : " + resourceName);
    return in;
  }
}
