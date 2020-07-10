package bowling.model;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BowlingGameTest {

  @ParameterizedTest
  @MethodSource("createBy")
  void createBy(List<String> playerNames) {
    BowlingGame bowlingGame = BowlingGame.createWith(playerNames);

    assertThat(bowlingGame.getNumberOfPlayers()).isEqualTo(playerNames.size());
  }

  static Stream<Arguments> createBy() {
    return Stream.of(
        arguments(Arrays.asList("a","b","c"))
    );
  }

  @ParameterizedTest
  @MethodSource("getCurrentPlayerName")
  void getCurrentPlayerName(List<String> playerNames,
      List<Integer> knockDownNumbers,
      List<String> expectedPlayerNames) {
    BowlingGame bowlingGame = BowlingGame.createWith(playerNames);

    for (int i = 0; i < knockDownNumbers.size(); i++) {
      assertThat(bowlingGame.getCurrentPlayerName())
          .isEqualTo(expectedPlayerNames.get(i));

      bowlingGame.roll(knockDownNumbers.get(i));
    }
  }

  static Stream<Arguments> getCurrentPlayerName() {
    return Stream.of(
        arguments(
            Arrays.asList("a","b","c"),
            Arrays.asList(1, 1, 10, 1, 9),
            Arrays.asList("a", "a", "b", "c", "c")
        )
    );
  }

  @ParameterizedTest
  @MethodSource("getCurrentPlayerName_bonus")
  void getCurrentPlayerName_bonus(List<String> playerNames,
      List<Integer> knockDownNumbers,
      String expectedPlayerName) {
    BowlingGame bowlingGame = BowlingGame.createWith(playerNames);

    for (int i = 0; i < knockDownNumbers.size(); i++) {
      bowlingGame.roll(knockDownNumbers.get(i));
    }

    assertThat(bowlingGame.getCurrentPlayerName()).isEqualTo(expectedPlayerName);
  }

  static Stream<Arguments> getCurrentPlayerName_bonus() {
    return Stream.of(
        arguments(
            Arrays.asList("a","b","c"),
            Arrays.asList(
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10
            ),
            "b"
        ),
        arguments(
            Arrays.asList("a","b","c"),
            Arrays.asList(
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                1, 1
            ),
            "c"
        )
    );
  }
}