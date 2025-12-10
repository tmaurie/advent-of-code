package fr.tmaurier;

import fr.tmaurier.day1.SafeDial;
import fr.tmaurier.day2.GiftShop;
import fr.tmaurier.day3.BatteryBanks;
import fr.tmaurier.day4.PaperRolls;
import fr.tmaurier.day5.IngredientInventory;
import fr.tmaurier.day6.TrashCompactor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("--- Day 1: Safe Dial ---");
    day1();

    System.out.println("--- Day 2: Gift Shop ---");
    day2();

    System.out.println("--- Day 3: Battery Banks ---");
    day3();

    System.out.println("--- Day 4: Paper Rolls ---");
    day4();

    System.out.println("--- Day 5: Ingredient Inventory ---");
    day5();

    System.out.println("--- Day 6: Trash Compactor ---");
    day6();
  }

  private static void day1() throws IOException {
    List<String> instructions = ResourceReader.readNonBlankTrimmedLines("day1.txt");
    SafeDial dial = new SafeDial();

    System.out.println("Day 1 - Part 1 answer: " + dial.countZeros(instructions, 50));
    System.out.println("Day 1 - Part 2 answer: " + dial.countZerosAllClicks(instructions, 50));
  }

  private static void day2() throws IOException {
    String line = ResourceReader.readFirstLineTrimmed("day2.txt");
    GiftShop service = new GiftShop();
    List<GiftShop.Range> ranges = service.parseRanges(line);

    System.out.println("Day 2 - Part 1 answer: " + service.sumInvalidIdsOverRanges(ranges));
    System.out.println("Day 2 - Part 2 answer: " + service.sumInvalidIdsOverRangesPart2(ranges));
  }

  private static void day3() throws IOException {
    List<String> banks = ResourceReader.readNonBlankTrimmedLines("day3.txt");
    BatteryBanks service = new BatteryBanks();
    long total1 = service.totalMaxJoltage(banks, 2);
    long total2 = service.totalMaxJoltage(banks, 12);

    System.out.println("Day 3 - Part 1 answer: " + total1);
    System.out.println("Day 3 - Part 2 answer: " + total2);
  }

  private static void day4() throws IOException {
    List<String> grid = ResourceReader.readNonBlankTrimmedLines("day4.txt");
    PaperRolls rolls = new PaperRolls();
    int accessibleCount = rolls.countAccessibleRolls(grid);
    int removableCount = rolls.countRemovableRolls(grid);

    System.out.println("Day 4 - Part 1 answer: " + accessibleCount);
    System.out.println("Day 4 - Part 2 answer: " + removableCount);
  }

  private static void day5() throws IOException {
    List<String> allLines = ResourceReader.readAllLines("day5.txt");

    List<String> rangeLines = new ArrayList<>();
    List<String> idLines = new ArrayList<>();

    boolean afterBlank = false;
    for (String line : allLines) {
      if (!afterBlank && line.isBlank()) {
        afterBlank = true;
        continue;
      }
      if (!afterBlank) {
        rangeLines.add(line);
      } else {
        idLines.add(line);
      }
    }

    IngredientInventory inv = new IngredientInventory();

    var ranges = inv.parseRanges(rangeLines);
    var ids = inv.parseIds(idLines);

    long freshCount = inv.countFresh(ranges, ids);
    long totalFreshIds = inv.countFreshIdsInRanges(ranges);

    System.out.println("Day 5 - Part 1 answer: " + freshCount);
    System.out.println("Day 5 - Part 2 answer: " + totalFreshIds);
  }

  private static void day6() throws IOException {
    List<String> allLines = ResourceReader.readAllLines("day6.txt");

    TrashCompactor trash = new TrashCompactor();
    long total = trash.solveWorksheet(allLines);
    long totalPartTwo = trash.solveWorksheetRightToLeft(allLines);

    System.out.println("Day 6 - Part 1: grand total = " + total);
    System.out.println("Day 6 - Part 2: grand total = " + totalPartTwo);
  }
}
