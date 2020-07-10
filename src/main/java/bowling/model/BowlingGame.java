package bowling.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {

  List<Lane> lanes;
  private int currentLaneIndex;

  public BowlingGame(List<Lane> lanes) {
    this.lanes = lanes;
  }

  public static BowlingGame createWith(List<PlayerName> playerNames) {
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

    if (Lane.MAX_NUMBER_OF_FRAMES < currentLane.getCurrentFrameNumber() && currentLane
        .isNotFinished()) {
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

  public boolean isNotFinished() {
    return lanes.stream().anyMatch(lane -> lane.isNotFinished());
  }

  public List<Lane> getLanes() {
    return Collections.unmodifiableList(lanes);
  }

  public int getCurrentFrameNumber() {
    return lanes.get(currentLaneIndex).getCurrentFrameNumber();
  }

  @Override
  public String toString() {
    return "BowlingGame{" +
        "lanes=" + lanes +
        ", currentLaneIndex=" + currentLaneIndex +
        '}';
  }
}
