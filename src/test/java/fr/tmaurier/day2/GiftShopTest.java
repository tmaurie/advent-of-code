package fr.tmaurier.day2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GiftShopTest {

  private final GiftShop giftShop = new GiftShop();

  @Test
  void isInvalidId_should_detect_simple_repeated_digits() {
    assertThat(giftShop.isInvalidId(11)).isTrue();
    assertThat(giftShop.isInvalidId(22)).isTrue();
    assertThat(giftShop.isInvalidId(55)).isTrue();
  }

  @Test
  void isInvalidId_should_detect_repeated_sequences() {
    assertThat(giftShop.isInvalidId(6464)).isTrue();
    assertThat(giftShop.isInvalidId(123123)).isTrue();
  }

  @Test
  void isInvalidId_should_reject_non_repeated_or_odd_length() {
    assertThat(giftShop.isInvalidId(101)).isFalse();
    assertThat(giftShop.isInvalidId(1234)).isFalse();
    assertThat(giftShop.isInvalidId(7)).isFalse();
  }

  @Test
  void sumInvalidIdsInRange_should_match_example_small_ranges() {
    assertThat(giftShop.sumInvalidIdsInRange(11, 22)).isEqualTo(11 + 22);
    assertThat(giftShop.sumInvalidIdsInRange(95, 115)).isEqualTo(99);
  }

  @Test
  void parseRanges_should_parse_comma_separated_ranges() {
    String line = "11-22,95-115,998-1012,";

    var ranges = giftShop.parseRanges(line);

    assertThat(ranges)
        .hasSize(3)
        .containsExactly(
            new GiftShop.Range(11, 22), new GiftShop.Range(95, 115), new GiftShop.Range(998, 1012));
  }

  @Test
  void sumInvalidIdsOverRanges_should_match_example_from_statement() {
    var ranges =
        java.util.List.of(
            new GiftShop.Range(11, 22),
            new GiftShop.Range(95, 115),
            new GiftShop.Range(998, 1012),
            new GiftShop.Range(1188511880L, 1188511890L),
            new GiftShop.Range(222220, 222224),
            new GiftShop.Range(1698522, 1698528),
            new GiftShop.Range(446443, 446449),
            new GiftShop.Range(38593856L, 38593862L));

    long sum = giftShop.sumInvalidIdsOverRanges(ranges);

    assertThat(sum).isEqualTo(1227775554L);
  }
}
