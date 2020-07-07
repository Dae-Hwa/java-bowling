package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import bowling.model.EmptyFrame;

public class RequiredFirstRoll implements FrameStatus {

  private final int currentIndex;

  public RequiredFirstRoll(int currentIndex) {
    this.currentIndex = currentIndex;
  }

  @Override
  public Frame getNextFrame() {
    return new EmptyFrame();
  }

  @Override
  public Score getAdditionalScore() {
    return Score.ofZere();
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    if (pins.getFirstKnockDownNumber() == KnockedDownPins.MAX_NUMBER_OF_PINS) {
      return new Strike(currentIndex);
    }

    return new RequiredSecondRoll(currentIndex);
  }

  @Override
  public boolean isOver() {
    return false;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    return BLANK.toString();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public String toString() {
    return "RequiredFirstRoll{" +
        "currentIndex=" + currentIndex +
        '}';
  }
}
