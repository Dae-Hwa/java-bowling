package bowling;

import bowling.model.BowlingGame;
import bowling.model.Lane;
import bowling.model.PlayerName;
import bowling.view.BowlingInput;
import bowling.view.BowlingView;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingController {

  public static void main(String[] args) {
    int playerNumber = new Scanner(System.in).nextInt();

    List<PlayerName> playerNames = Stream
        .generate(() -> new PlayerName(BowlingInput.getPlayerNameInput()))
        .limit(playerNumber)
        .collect(Collectors.toList());

    BowlingGame bowlingGame = BowlingGame.createWith(playerNames);

    while (bowlingGame.isNotFinished()) {
      bowlingGame.roll(BowlingInput.getKnockDownNumberInput(bowlingGame.getCurrentFrameNumber()));

      bowlingGame.getLanes().forEach(lane -> {
        BowlingView.printScoreBoard(lane.getPlayerName(), lane.getFramesDTO());
        BowlingView.printScores(lane.getScores());
      });
    }
  }
}
