package bowling.model;

import java.util.List;

public class BowlingGame {

  Lanes lanes;
  private int currentLaneIndex;

  private BowlingGame(Lanes lanes) {
    this.lanes = lanes;
  }

  public static BowlingGame createWith(List<String> playerNames) {
    return new BowlingGame(Lanes.createWith(playerNames));
  }

  public int getNumberOfPlayers() {
    return lanes.getSize();
  }

  public String getCurrentPlayerName() {
    return lanes.getPlayerNameAt(currentLaneIndex);
  }

  public void roll(int knockDownNumber) {
    lanes.rollBy(knockDownNumber, currentLaneIndex);

    if (Lane.MAX_NUMBER_OF_FRAMES < lanes.getFrameNumberAt(currentLaneIndex) && lanes
        .isNotFinishedAt(currentLaneIndex)) {
      return;
    }

    if (lanes.isFrameOverAt(currentLaneIndex)) {
      // 도메인 만들어서 이동
      currentLaneIndex++;
      if (lanes.getSize() <= currentLaneIndex) {
        currentLaneIndex = 0;
      }
    }
  }

  public boolean isNotFinished() {
    return lanes.isNotFinished();
  }

  public List<Lane> getLanes() {
    return lanes.getLanes();
  }

  @Override
  public String toString() {
    return "BowlingGame{" +
        "lanes=" + lanes +
        ", currentLaneIndex=" + currentLaneIndex +
        '}';
  }
}
