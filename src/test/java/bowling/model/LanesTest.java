package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LanesTest {

  public static Lanes lanes1;

  static {
    initLanesTest();
  }

  @AfterEach
  void tearDown() {
    initLanesTest();
  }

  private static void initLanesTest() {
    lanes1 = Lanes.createWith(Arrays.asList("a", "b", "c"));
  }

  @ParameterizedTest
  @MethodSource("getSize")
  void getSize(Lanes lanes, int numberOfPlayers) {
    assertThat(lanes.getSize()).isEqualTo(numberOfPlayers);
  }

  static Stream<Arguments> getSize() {
    return Stream.of(
        arguments(lanes1, 3)
    );
  }

  @ParameterizedTest
  @MethodSource("getPlayerNameAt")
  void getPlayerNameAt(Lanes lanes, List<String> expectedPlayerNames) {
    IntStream.range(0, lanes.getSize())
        .forEach(index -> assertThat(lanes.getPlayerNameAt(index))
            .isEqualTo(expectedPlayerNames.get(index))
        );
  }

  static Stream<Arguments> getPlayerNameAt() {
    return Stream.of(
        arguments(
            lanes1,
            Arrays.asList("a", "b", "c")
        )
    );
  }

  @Test
  void isNotFinished() {
    assertThat(lanes1.isNotFinished()).isTrue();

    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(1, 0);
    lanes1.rollBy(1, 0);

    assertThat(lanes1.isNotFinished()).isTrue();
  }

  @Test
  void getFrameNumberAt() {
    assertThat(lanes1.getFrameNumberAt(0)).isEqualTo(1);
    assertThat(lanes1.getFrameNumberAt(1)).isEqualTo(1);
    assertThat(lanes1.getFrameNumberAt(2)).isEqualTo(1);
  }

  @Test
  void isNotFinishedAt() {
    assertThat(lanes1.isNotFinishedAt(0)).isTrue();
    assertThat(lanes1.isNotFinishedAt(1)).isTrue();
    assertThat(lanes1.isNotFinishedAt(2)).isTrue();

    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(10, 0);
    lanes1.rollBy(1, 0);
    lanes1.rollBy(1, 0);

    assertThat(lanes1.isNotFinishedAt(0)).isFalse();
  }

  @Test
  void isFrameOverAt() {
    assertThat(lanes1.isFrameOverAt(0)).isFalse();

    lanes1.rollBy(10, 0);

    assertThat(lanes1.isFrameOverAt(0)).isTrue();
  }
}