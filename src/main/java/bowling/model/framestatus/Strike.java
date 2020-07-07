package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.BonusFrame;
import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.NormalFrame;
import bowling.model.Score;

public class Strike implements FrameStatus {

  private final int currentIndex;

  private final Frame nextFrame;

  public Strike(int currentIndex) {
    this.currentIndex = currentIndex;

    nextFrame = createFrameBy(currentIndex);
  }

  private Frame createFrameBy(int currentIndex) {
    if (currentIndex == 9) {
      return new BonusFrame(Bonus.createHasNext());
    }

    return new NormalFrame(currentIndex + 1);
  }

  @Override
  public Frame getNextFrame() {
    return nextFrame;
  }

  @Override
  public Score getAdditionalScore() {
    Score result = new Score(nextFrame.getFirstKnockDownNumber());
    result.add(nextFrame.next().getFirstKnockDownNumber());

    return result;
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    return this;
  }

  @Override
  public boolean isOver() {
    return true;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    if (pins.getFirstKnockDownNumber() != KnockedDownPins.MAX_NUMBER_OF_PINS) {
      throw new IllegalArgumentException("스트라이크가 아닙니다.");
    }

    return STRIKE.toString();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String toString() {
    return "Strike{" +
        "currentIndex=" + currentIndex +
        ", nextFrame=" + nextFrame +
        '}';
  }
}
