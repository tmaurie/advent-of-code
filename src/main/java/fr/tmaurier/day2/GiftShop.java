package fr.tmaurier.day2;

import java.util.Arrays;
import java.util.List;

public class GiftShop {

  public boolean isInvalidId(long id) {
    String s = Long.toString(id);
    if (s.length() % 2 != 0) return false;
    return s.substring(0, s.length() / 2).equals(s.substring(s.length() / 2));
  }

  public long sumInvalidIdsInRange(long start, long end) {
    long sum = 0L;
    for (long id = start; id <= end; id++) {
      if (isInvalidId(id)) sum += id;
    }
    return sum;
  }

  public List<Range> parseRanges(String line) {
    return Arrays.stream(line.split(","))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .map(
            part -> {
              String[] bounds = part.split("-");
              return new Range(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
            })
        .toList();
  }

  public long sumInvalidIdsOverRanges(List<Range> ranges) {
    return ranges.stream().mapToLong(r -> sumInvalidIdsInRange(r.start, r.end)).sum();
  }

  public long sumInvalidIdsOverRangesPart2(List<Range> ranges) {
    return ranges.stream().mapToLong(r -> sumInvalidIdsInRangePart2(r.start, r.end)).sum();
  }

  public long sumInvalidIdsInRangePart2(long start, long end) {
    long sum = 0L;
    for (long id = start; id <= end; id++) {
      if (isInvalidIdPart2(id)) sum += id;
    }
    return sum;
  }

  public boolean isInvalidIdPart2(long id) {
    String s = Long.toString(id);
    int n = s.length();
    for (int k = 1; k <= n / 2; k++) {
      if (n % k != 0) continue;
      String chunk = s.substring(0, k);
      if (chunk.repeat(n / k).equals(s)) return true;
    }
    return false;
  }

  public record Range(long start, long end) {}
}
