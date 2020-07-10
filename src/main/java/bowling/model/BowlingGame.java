package bowling.model;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {

  List<Lane> lanes;
  private int currentLaneIndex;

  public BowlingGame(List<Lane> lanes) {
    this.lanes = lanes;
  }

  public static BowlingGame createWith(List<String> playerNames) {
    return new BowlingGame(playerNames.stream()
        .map(playerName -> Lane.createWith(playerName))
        .collect(Collectors.toList()));
  }

  public int getLaneSize() {
    return lanes.size();
  }

  public String getCurrentPlayerName() {
    return lanes.get(currentLaneIndex).getPlayerName();
  }

  public void roll(int knockDownNumber) {
    Lane currentLane = lanes.get(currentLaneIndex);

    currentLane.roll(knockDownNumber);

    if (Lane.MAX_NUMBER_OF_FRAMES < currentLane.getCurrentFrameNumber() && currentLane.requiredNormalFrame()) {
      return;
    }

    if (currentLane.isCurrentFrameOver()) {
      // 도메인 만들어서 이동
      currentLaneIndex++;
      if (lanes.size() <= currentLaneIndex) {
        currentLaneIndex = 0;
      }
    }
  }

  @Override
  public String toString() {
    return "BowlingGame{" +
        "lanes=" + lanes +
        ", currentLaneIndex=" + currentLaneIndex +
        '}';
  }
}
