package bowling;

import bowling.model.Lane;
import bowling.view.BowlingInput;
import bowling.view.BowlingView;

public class BowlingController {

  public static void main(String[] args) {
    Lane lane = Lane.createWith(BowlingInput.getPlayerNameInput());

    BowlingView.printScoreBoard(lane.getPlayerName(), lane.getFramesDTO());
    BowlingView.printScores(lane.getScores());

    while (lane.requiredNormalFrame()) {
      lane.roll(BowlingInput.getKnockDownNumberInput(lane.getCurrentFrameNumber()));
      BowlingView.printScoreBoard(lane.getPlayerName(), lane.getFramesDTO());
      BowlingView.printScores(lane.getScores());
    }
  }
}
