package bowling.model;

import bowling.model.framestatus.FrameStatus;
import bowling.model.framestatus.RequiredFirstRoll;
import java.util.Objects;

public class NormalFrame implements Frame {

  private KnockedDownPins pins = new KnockedDownPins();
  private FrameStatus frameStatus;

  public NormalFrame(int currentIndex) {
    this.frameStatus = new RequiredFirstRoll(currentIndex);
  }

  /**
   * 최대 두 번 투구 가능
   * <p>
   * - 스트라이크 시 첫 번째 핀 10, 두 번째 핀 0
   * <p>
   * - 이외의 경우 초구 투구 시 첫 번째 핀 넘어뜨린 수, 두 번째 핀.isNull() = true
   * <p>
   * - 세 번째 투구 혹은 스트라이크 후 두 번째 투구 시 FrameOverException
   *
   * @param KnockDownNumber 넘어뜨린 개수
   * @throws FrameOverException 새로운 인스턴스 생성하여 처리
   */
  @Override
  public void roll(int KnockDownNumber) throws FrameOverException {
    if (isOver()) {
      throw new FrameOverException();
    }

    pins = KnockedDownPinsFactory.createInstanceBy(pins, KnockDownNumber);

    frameStatus = frameStatus.createNextStatusBy(pins);
  }

  @Override
  public Frame next() {
    return frameStatus.getNextFrame();
  }

  @Override
  public Score getScore() {
    Score score = new Score(pins.getFirstKnockDownNumber() + pins.getSecondKnockDownNumber());

    score.add(frameStatus.getAdditionalScore());

    return score;
  }

  @Override
  public int getFirstKnockDownNumber() {
    return pins.getFirstKnockDownNumber();
  }

  @Override
  public boolean isOver() {
    return frameStatus.isOver();
  }

  @Override
  public boolean isFinished() {
    return frameStatus.isFinished();
  }

  @Override
  public FrameDTO createDTO() {
    return new FrameDTO(pins, frameStatus);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NormalFrame normalFrame = (NormalFrame) o;
    return pins.equals(normalFrame.pins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pins);
  }

  @Override
  public String toString() {
    return System.lineSeparator() + "Frame{" +
        "pins=" + pins +
        '}';
  }
}
