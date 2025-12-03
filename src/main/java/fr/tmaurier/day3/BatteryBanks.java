package fr.tmaurier.day3;

import java.util.List;
import java.util.stream.IntStream;

public class BatteryBanks {

  public int maxJoltageForBank(String line) {
    if (line.length() < 2) {
      throw new IllegalArgumentException("Bank must contain at least 2 batteries");
    }

    int[] bestFirstDigit = {-1};

    return IntStream.range(1, line.length())
        .map(
            j -> {
              int prevDigit = line.charAt(j - 1) - '0';
              bestFirstDigit[0] = Math.max(bestFirstDigit[0], prevDigit);
              int currentDigit = line.charAt(j) - '0';
              return bestFirstDigit[0] * 10 + currentDigit;
            })
        .max()
        .orElseThrow();
  }

  public long totalMaxJoltage(List<String> banks) {
    return banks.stream().mapToLong(this::maxJoltageForBank).sum();
  }
}
