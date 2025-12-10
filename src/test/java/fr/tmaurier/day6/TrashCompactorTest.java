package fr.tmaurier.day6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class TrashCompactorTest {

  private final TrashCompactor trash = new TrashCompactor();

  @Test
  void solveWorksheet_should_match_example() {
    List<String> lines =
        List.of("123 328  51 64 ", " 45 64  387 23 ", "  6 98  215 314", "*   +   *   +  ");

    long total = trash.solveWorksheet(lines);

    assertThat(total).isEqualTo(4_277_556L);
  }

  @Test
  void solveWorksheetRightToLeft_should_match_example_part2() {
    List<String> lines =
        List.of("123 328  51 64 ", " 45 64  387 23 ", "  6 98  215 314", "*   +   *   +  ");

    long total = trash.solveWorksheetRightToLeft(lines);

    assertThat(total).isEqualTo(3_263_827L);
  }
}
