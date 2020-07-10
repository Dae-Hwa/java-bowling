package bowling.model;

public class LaneIndex {

  private final int maxIndex;
  private int currentIndex = 0;

  public LaneIndex(int maxIndex) {
    if (maxIndex < 0) {
      throw new IllegalArgumentException("maxIndex는 0보다 작을 수 없습니다.");
    }

    this.maxIndex = maxIndex;
  }

  public void next() {
    currentIndex++;

    if (maxIndex < currentIndex) {
      currentIndex = 0;
    }
  }

  public int getCurrentIndex() {
    return currentIndex;
  }

  @Override
  public String toString() {
    return "LaneIndex{" +
        "maxIndex=" + maxIndex +
        ", currentIndex=" + currentIndex +
        '}';
  }
}
