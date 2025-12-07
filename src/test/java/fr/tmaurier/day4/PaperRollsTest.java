package fr.tmaurier.day4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PaperRollsTest {

  PaperRolls rolls = new PaperRolls();

  @Test
  void countAccessibleRolls_should_handle_simple_isolated_roll() {
    List<String> grid = List.of("...", ".@.", "...");

    assertThat(rolls.countAccessibleRolls(grid)).isEqualTo(1);
  }

  @Test
  void countAccessibleRolls_should_handle_fully_surrounded_roll() {
    List<String> grid = List.of("@@@", "@@@", "@@@");

    assertThat(rolls.countAccessibleRolls(grid)).isEqualTo(4);
  }

  @Test
  void countAccessibleRolls_should_match_example_from_statement() {
    List<String> grid =
        List.of(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@.");

    assertThat(rolls.countAccessibleRolls(grid)).isEqualTo(13);
  }

  @Test
  void countRemovableRolls_should_match_example() {
    List<String> grid =
        List.of(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@.");

    int totalRemoved = rolls.countRemovableRolls(grid);

    assertThat(totalRemoved).isEqualTo(43);
  }
}
