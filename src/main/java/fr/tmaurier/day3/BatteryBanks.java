package fr.tmaurier.day3;

import java.util.List;

public class BatteryBanks {

  public int maxJoltageForBank(String line) {
    if (line.length() < 2) {
      throw new IllegalArgumentException("Bank must contain at least 2 batteries");
    }

    int bestFirstDigit = -1;
    int best = -1;

    for (int j = 1; j < line.length(); j++) {
      int prevDigit = line.charAt(j - 1) - '0';
      if (prevDigit > bestFirstDigit) {
        bestFirstDigit = prevDigit;
      }
      int currentDigit = line.charAt(j) - '0';
      int value = bestFirstDigit * 10 + currentDigit;
      if (value > best) {
        best = value;
      }
    }

    return best;
  }

  public long totalMaxJoltage(List<String> banks) {
    return banks.stream().mapToLong(this::maxJoltageForBank).sum();
  }
}
