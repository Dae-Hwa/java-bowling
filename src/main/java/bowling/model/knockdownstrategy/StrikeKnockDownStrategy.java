package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;

public class StrikeKnockDownStrategy implements KnockDownStrategy {

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    if (numberOfKnockedDown != KnockedDownPins.MAX_NUMBER_OF_PINS) {
      throw new IllegalArgumentException(
          "스트라이크가 아닙니다. numberOfKnockedDown : " + numberOfKnockedDown);
    }

    return KnockedDownPins.getBuilder()
        .firstKnockDownNumber(numberOfKnockedDown)
        .secondKnockDownNumber(0)
        .build();
  }
}
