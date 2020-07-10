package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LaneIndexTest {

  @Test
  void init_fail() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      new LaneIndex(-1);
    });
  }

  @Test
  void getCurrentIndex() {
    LaneIndex laneIndex = new LaneIndex(2);

    assertThat(laneIndex.getCurrentIndex()).isEqualTo(0);
    laneIndex.next();
    assertThat(laneIndex.getCurrentIndex()).isEqualTo(1);
    laneIndex.next();
    assertThat(laneIndex.getCurrentIndex()).isEqualTo(2);
    laneIndex.next();
    assertThat(laneIndex.getCurrentIndex()).isEqualTo(0);
  }
}