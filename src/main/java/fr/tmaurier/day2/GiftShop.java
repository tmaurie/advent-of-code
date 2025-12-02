package fr.tmaurier.day2;

import java.util.Arrays;
import java.util.List;

public class GiftShop {

  public boolean isInvalidId(long id) {
    String s = Long.toString(id);

    if (s.length() % 2 != 0) {
      return false;
    }

    int mid = s.length() / 2;
    String first = s.substring(0, mid);
    String second = s.substring(mid);

    return first.equals(second);
  }

  public long sumInvalidIdsInRange(long start, long end) {
    long sum = 0L;
    for (long id = start; id <= end; id++) {
      if (isInvalidId(id)) {
        sum += id;
      }
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
              long start = Long.parseLong(bounds[0]);
              long end = Long.parseLong(bounds[1]);
              return new Range(start, end);
            })
        .toList();
  }

  public long sumInvalidIdsOverRanges(List<Range> ranges) {
    long sum = 0L;
    for (Range r : ranges) {
      sum += sumInvalidIdsInRange(r.start, r.end);
    }
    return sum;
  }

  public record Range(long start, long end) {}
}
