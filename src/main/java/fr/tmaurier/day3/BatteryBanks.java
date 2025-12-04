package fr.tmaurier.day3;

import java.util.List;

public class BatteryBanks {

  public long maxJoltageForBank(String line, int k) {
    int n = line.length();
    int toRemove = n - k;

    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {
      char d = line.charAt(i);

      while (!sb.isEmpty() && toRemove > 0 && sb.charAt(sb.length() - 1) < d) {
        sb.setLength(sb.length() - 1); // pop
        toRemove--;
      }

      sb.append(d);
    }

    if (sb.length() > k) {
      sb.setLength(k);
    }

    return Long.parseLong(sb.toString());
  }

  public long totalMaxJoltage(List<String> banks, int k) {
    return banks.stream().mapToLong(bank -> maxJoltageForBank(bank, k)).sum();
  }
}
