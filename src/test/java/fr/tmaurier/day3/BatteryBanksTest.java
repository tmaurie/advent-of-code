package fr.tmaurier.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BatteryBanksTest {

  BatteryBanks banks = new BatteryBanks();

  @Test
  void maxJoltageForBank_should_handle_simple_cases() {
    assertThat(banks.maxJoltageForBank("12")).isEqualTo(12);
    assertThat(banks.maxJoltageForBank("21")).isEqualTo(21);
    assertThat(banks.maxJoltageForBank("919")).isEqualTo(99);
  }

  @Test
  void maxJoltageForBank_should_match_examples_from_statement() {
    assertThat(banks.maxJoltageForBank("987654321111111")).isEqualTo(98);
    assertThat(banks.maxJoltageForBank("811111111111119")).isEqualTo(89);
    assertThat(banks.maxJoltageForBank("234234234234278")).isEqualTo(78);
    assertThat(banks.maxJoltageForBank("818181911112111")).isEqualTo(92);
  }

  @Test
  void totalMaxJoltage_should_match_example() {
    var lines = List.of("987654321111111", "811111111111119", "234234234234278", "818181911112111");

    long total = banks.totalMaxJoltage(lines);

    assertThat(total).isEqualTo(357L);
  }
}
