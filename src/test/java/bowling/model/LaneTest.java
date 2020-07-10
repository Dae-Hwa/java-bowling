package bowling.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LaneTest {

  @Test
  void getPlayerName() {
    assertThat(Lane.createWith("abc").getPlayerName()).isEqualTo("abc");
  }
}