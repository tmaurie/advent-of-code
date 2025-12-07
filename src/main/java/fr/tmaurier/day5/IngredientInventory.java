package fr.tmaurier.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IngredientInventory {

  public record Range(long start, long end) {}

  public boolean isFresh(long id, List<Range> ranges) {
    for (Range r : ranges) {
      if (id >= r.start && id <= r.end) {
        return true;
      }
    }
    return false;
  }

  public long countFresh(List<Range> ranges, List<Long> ids) {
    long count = 0L;
    for (long id : ids) {
      if (isFresh(id, ranges)) {
        count++;
      }
    }
    return count;
  }

  public List<Range> parseRanges(List<String> lines) {
    List<Range> ranges = new ArrayList<>();
    for (String line : lines) {
      String trimmed = line.trim();
      if (trimmed.isEmpty()) continue;
      String[] parts = trimmed.split("-");
      long start = Long.parseLong(parts[0]);
      long end = Long.parseLong(parts[1]);
      ranges.add(new Range(start, end));
    }
    return ranges;
  }

  public List<Long> parseIds(List<String> lines) {
    List<Long> ids = new ArrayList<>();
    for (String line : lines) {
      String trimmed = line.trim();
      if (trimmed.isEmpty()) continue;
      ids.add(Long.parseLong(trimmed));
    }
    return ids;
  }

  public long countFreshIdsInRanges(List<Range> ranges) {
    if (ranges.isEmpty()) return 0L;

    List<Range> sorted = new ArrayList<>(ranges);
    sorted.sort(Comparator.comparingLong(Range::start).thenComparingLong(Range::end));

    long total = 0L;

    long currentStart = sorted.getFirst().start;
    long currentEnd = sorted.getFirst().end;

    for (int i = 1; i < sorted.size(); i++) {
      Range r = sorted.get(i);

      if (r.start > currentEnd + 1) {
        total += (currentEnd - currentStart + 1);
        currentStart = r.start;
        currentEnd = r.end;
      } else {
        if (r.end > currentEnd) {
          currentEnd = r.end;
        }
      }
    }

    total += (currentEnd - currentStart + 1);

    return total;
  }
}
