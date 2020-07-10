package bowling.model;

import java.util.List;

public class BowlingGame {

  Lanes lanes;
  private final LaneIndex laneIndex;

  private BowlingGame(Lanes lanes) {
    this.lanes = lanes;
    this.laneIndex = new LaneIndex(lanes.getSize() - 1);
  }

  public static BowlingGame createWith(List<String> playerNames) {
    return new BowlingGame(Lanes.createWith(playerNames));
  }

  public int getNumberOfPlayers() {
    return lanes.getSize();
  }

  public String getCurrentPlayerName() {
    return lanes.getPlayerNameAt(laneIndex.getCurrentIndex());
  }

  public void roll(int knockDownNumber) {
    lanes.rollBy(knockDownNumber, laneIndex.getCurrentIndex());

    if (Lane.MAX_NUMBER_OF_FRAMES < lanes.getFrameNumberAt(laneIndex.getCurrentIndex()) && lanes
        .isNotFinishedAt(laneIndex.getCurrentIndex())) {
      return;
    }

    if (lanes.isFrameOverAt(laneIndex.getCurrentIndex())) {
      laneIndex.next();
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
        ", laneIndex=" + laneIndex +
        '}';
  }
}
