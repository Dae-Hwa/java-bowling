package bowling.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lanes {

  private final List<Lane> lanes;

  private Lanes(List<Lane> lanes) {
    this.lanes = lanes;
  }

  public static Lanes createWith(List<String> playerNames) {
    return new Lanes(playerNames.stream()
        .map(Lane::createWith)
        .collect(Collectors.toList()));
  }


  public int getSize() {
    return lanes.size();
  }

  public String getPlayerNameAt(int i) {
    return lanes.get(i).getPlayerName();
  }

  public void rollBy(int knockDownNumber, int index) {
    lanes.get(index).roll(knockDownNumber);
  }

  public List<Lane> getLanes() {
    return Collections.unmodifiableList(lanes);
  }

  public boolean isNotFinished() {
    return lanes.stream().anyMatch(Lane::isNotFinished);
  }

  public int getFrameNumberAt(int index) {
    return lanes.get(index).getCurrentFrameNumber();
  }

  public boolean isNotFinishedAt(int index) {
    return lanes.get(index).isNotFinished();
  }

  public boolean isFrameOverAt(int index) {
    return lanes.get(index).isCurrentFrameOver();
  }
}
