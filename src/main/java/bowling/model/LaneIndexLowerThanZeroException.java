package bowling.model;

public class LaneIndexLowerThanZeroException extends RuntimeException {

  public LaneIndexLowerThanZeroException() {
    super("LaneIndex의 최대 크기는 0보다 작을 수 없습니다.");
  }
}
