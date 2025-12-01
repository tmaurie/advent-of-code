package fr.tmaurier.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class SafeDialTest {

  @Test
  void applyRotation_should_move_right_without_wrapping() {
    SafeDial dial = new SafeDial();

    int result = dial.applyRotation(11, "R8");

    assertThat(result).isEqualTo(19);
  }

  @Test
  void applyRotation_should_move_left_without_wrapping() {
    SafeDial dial = new SafeDial();

    int result = dial.applyRotation(20, "L3");

    assertThat(result).isEqualTo(17);
  }

  @Test
  void applyRotation_should_wrap_left_below_zero() {
    SafeDial dial = new SafeDial();

    int result = dial.applyRotation(5, "L10");

    assertThat(result).isEqualTo(95);
  }

  @Test
  void applyRotation_should_wrap_right_above_99() {
    SafeDial dial = new SafeDial();

    int result = dial.applyRotation(99, "R1");

    assertThat(result).isZero();
  }

  @Test
  void applyRotations_should_reach_expected_final_position() {
    SafeDial dial = new SafeDial();

    var instructions = List.of("L68", "L30");

    int finalPos = dial.applyRotations(50, instructions);

    assertThat(finalPos).isEqualTo(52);
  }

  @Test
  void countZeros_should_count_zero_hits_in_simple_sequence() {
    SafeDial dial = new SafeDial();

    var instructions = List.of("R50", "R10", "L10");

    int hits = dial.countZeros(instructions, 50);

    assertThat(hits).isEqualTo(2);
  }

  @Test
  void countZeros_should_match_official_example() {
    SafeDial dial = new SafeDial();

    var instructions =
        java.util.List.of("L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82");

    int hits = dial.countZeros(instructions, 50);

    assertThat(hits).isEqualTo(3);
  }

  @Test
  void countZerosAllClicks_should_count_zero_hit_when_passing_over_zero() {
    SafeDial dial = new SafeDial();

    var instructions = List.of("R5");

    int hits = dial.countZerosAllClicks(instructions, 98);

    assertThat(hits).isEqualTo(1);
  }

  @Test
  void countZerosAllClicks_should_count_multiple_zero_hits_in_long_rotation() {
    SafeDial dial = new SafeDial();

    var instructions = List.of("R1000");

    int hits = dial.countZerosAllClicks(instructions, 50);

    assertThat(hits).isEqualTo(10);
  }

  @Test
  void countZerosAllClicks_should_match_official_example() {
    SafeDial dial = new SafeDial();

    List<String> instructions =
        List.of("L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82");

    int hits = dial.countZerosAllClicks(instructions, 50);

    assertThat(hits).isEqualTo(6);
  }
}
