package fr.tmaurier.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class IngredientInventoryTest {

  IngredientInventory inv = new IngredientInventory();

  @Test
  void isFresh_should_detect_id_inside_single_range() {
    var ranges = List.of(new IngredientInventory.Range(3, 5));

    assertThat(inv.isFresh(2, ranges)).isFalse();
    assertThat(inv.isFresh(3, ranges)).isTrue();
    assertThat(inv.isFresh(4, ranges)).isTrue();
    assertThat(inv.isFresh(5, ranges)).isTrue();
    assertThat(inv.isFresh(6, ranges)).isFalse();
  }

  @Test
  void isFresh_should_detect_id_inside_any_overlapping_range() {
    var ranges =
        List.of(
            new IngredientInventory.Range(10, 14),
            new IngredientInventory.Range(12, 18),
            new IngredientInventory.Range(16, 20));

    assertThat(inv.isFresh(11, ranges)).isTrue();
    assertThat(inv.isFresh(17, ranges)).isTrue();
    assertThat(inv.isFresh(9, ranges)).isFalse();
    assertThat(inv.isFresh(21, ranges)).isFalse();
  }

  @Test
  void countFresh_should_match_example_from_statement() {
    var ranges =
        List.of(
            new IngredientInventory.Range(3, 5),
            new IngredientInventory.Range(10, 14),
            new IngredientInventory.Range(16, 20),
            new IngredientInventory.Range(12, 18));

    var ids = List.of(1L, 5L, 8L, 11L, 17L, 32L);

    long fresh = inv.countFresh(ranges, ids);

    assertThat(fresh).isEqualTo(3L);
  }

  @Test
  void parseRanges_should_parse_lines_with_dash() {
    List<String> lines = List.of("3-5", "10-14", "16-20");

    var ranges = inv.parseRanges(lines);

    assertThat(ranges)
        .containsExactly(
            new IngredientInventory.Range(3, 5),
            new IngredientInventory.Range(10, 14),
            new IngredientInventory.Range(16, 20));
  }

  @Test
  void parseIds_should_parse_one_id_per_line() {
    List<String> lines = List.of("1", "5", "8");

    var ids = inv.parseIds(lines);

    assertThat(ids).containsExactly(1L, 5L, 8L);
  }

  @Test
  void countFreshIdsInRanges_should_handle_non_overlapping_ranges() {
    var ranges =
        List.of(new IngredientInventory.Range(3, 5), new IngredientInventory.Range(10, 12));

    long total = inv.countFreshIdsInRanges(ranges);

    assertThat(total).isEqualTo(6L);
  }

  @Test
  void countFreshIdsInRanges_should_merge_overlapping_ranges() {
    var ranges =
        List.of(
            new IngredientInventory.Range(10, 14),
            new IngredientInventory.Range(12, 18),
            new IngredientInventory.Range(16, 20));

    long total = inv.countFreshIdsInRanges(ranges);

    assertThat(total).isEqualTo(11L);
  }

  @Test
  void countFreshIdsInRanges_should_match_example_from_statement_part2() {
    var ranges =
        List.of(
            new IngredientInventory.Range(3, 5),
            new IngredientInventory.Range(10, 14),
            new IngredientInventory.Range(16, 20),
            new IngredientInventory.Range(12, 18));

    long total = inv.countFreshIdsInRanges(ranges);

    assertThat(total).isEqualTo(14L);
  }
}
