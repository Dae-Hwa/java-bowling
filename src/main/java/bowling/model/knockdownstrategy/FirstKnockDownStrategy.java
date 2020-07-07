package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;

public class FirstKnockDownStrategy implements KnockDownStrategy {

  @Override
  public KnockedDownPins knockDown(int numberOfKnockedDown) {
    if (numberOfKnockedDown == KnockedDownPins.MAX_NUMBER_OF_PINS) {
      return KnockedDownPins.getBuilder()
          .firstKnockDownNumber(numberOfKnockedDown)
          .secondKnockDownNumber(KnockedDownPins.MIN_NUMBER_OF_PINS)
          .build();
    }

    return KnockedDownPins.getBuilder()
        .firstKnockDownNumber(numberOfKnockedDown)
        .build();
  }
}
