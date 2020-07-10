package bowling;

import bowling.model.BowlingGame;
import bowling.view.BowlingInput;
import bowling.view.BowlingView;

public class BowlingController {

  public static void main(String[] args) {

    BowlingGame bowlingGame = BowlingGame.createWith(BowlingInput.getPalyerNamesInput());

    BowlingView.printScoreBoardHeader();
    bowlingGame.getLanes().forEach(lane -> {
      BowlingView.printScoreBoard(lane.getPlayerName(), lane.getFramesDTO());
      BowlingView.printScores(lane.getScores());
    });

    BowlingView.printLineSeparator();

    while (bowlingGame.isNotFinished()) {
      bowlingGame.roll(BowlingInput.getKnockDownNumberInput(bowlingGame.getCurrentPlayerName()));

      BowlingView.printScoreBoardHeader();
      bowlingGame.getLanes().forEach(lane -> {
        BowlingView.printScoreBoard(lane.getPlayerName(), lane.getFramesDTO());
        BowlingView.printScores(lane.getScores());
      });

      BowlingView.printLineSeparator();
    }
  }
}
