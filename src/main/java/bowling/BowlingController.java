package bowling;

import bowling.model.BowlingGame;
import bowling.view.BowlingInput;
import bowling.view.BowlingView;

public class BowlingController {

  public static void main(String[] args) {

    BowlingGame bowlingGame = BowlingGame.createWith(BowlingInput.getPalyerNamesInput());

    BowlingView.printScoreBoard(bowlingGame.getLanes());

    while (bowlingGame.isNotFinished()) {
      bowlingGame.roll(BowlingInput.getKnockDownNumberInput(bowlingGame.getCurrentPlayerName()));

      BowlingView.printScoreBoard(bowlingGame.getLanes());
    }
  }
}
