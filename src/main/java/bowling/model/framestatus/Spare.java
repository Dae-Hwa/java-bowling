package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.BonusFrame;
import bowling.model.BowlingGame;
import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.NormalFrame;
import bowling.model.Score;

public class Spare implements FrameStatus {

  private final int currentIndex;

  private final Frame nextFrame;

  public Spare(int currentIndex) {
    this.currentIndex = currentIndex;

    nextFrame = createFrameBy(currentIndex);
  }

  private Frame createFrameBy(int currentIndex) {
    if (currentIndex == BowlingGame.MAX_NUMBER_OF_FRAMES - 1) {
      return new BonusFrame(Bonus.createHasFinished());
    }

    return new NormalFrame(currentIndex + 1);
  }

  @Override
  public Frame getNextFrame() {
    return nextFrame;
  }

  @Override
  public Score getAdditionalScore() {
    return new Score(nextFrame.getFirstKnockDownNumber());
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
    if (pins.getRemainingNumber() != KnockedDownPins.MIN_NUMBER_OF_PINS) {
      throw new IllegalArgumentException("스페어가 아닙니다.");
    }

    return (String.valueOf(pins.getFirstKnockDownNumber()) + BAR + SPARE)
        .replace(String.valueOf(KnockedDownPins.MIN_NUMBER_OF_PINS), GUTTER.toString());
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
    return "Spare{" +
        "currentIndex=" + currentIndex +
        ", nextFrame=" + nextFrame +
        '}';
  }
}
