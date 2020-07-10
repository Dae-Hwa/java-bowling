package bowling.view;

import static bowling.model.Symbols.*;

import bowling.model.Lane;
import bowling.model.FrameDTO;
import bowling.model.Score;
import java.util.List;

public class BowlingView {

  private final static String SCORE_BOARD_UPPER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private final static int MAXLENGTH_PER_FRAME = 6;

  public static void printPlayerNumberInputMsg() {
    System.out.print("How many people? ");
  }

  public static void printPlayerNameInputMsg(int index) {
    System.out.print("플레이어 " + index + "의 이름은?(3 english letters): ");
  }

  public static void printKnockDownNumInputMsg(String currentPlayer) {
    System.out.print(currentPlayer + "'s turn : ");
  }

  public static void printScoreBoard(List<Lane> lanes) {
    printScoreBoardHeader();

    printScoreBoardBody(lanes);

    System.out.println();
  }

  private static void printScoreBoardHeader() {
    System.out.println(SCORE_BOARD_UPPER);
  }

  private static void printScoreBoardBody(List<Lane> lanes) {
    lanes.forEach(lane -> {
      printKnockDownNumberWithName(lane.getPlayerName(), lane.getFramesDTO());
      printScores(lane.getScores());
    });
  }


  private static void printKnockDownNumberWithName(String playerName, List<FrameDTO> frameDTOs) {
    StringBuilder sb = new StringBuilder();

    sb.append(BAR)
        .append(wrappingWithSpaces(playerName))
        .append(BAR);

    frameDTOs.stream()
        .limit(Lane.MAX_NUMBER_OF_FRAMES - 1)
        .forEach(frameDTO -> {
          sb.append(wrappingWithSpaces(frameDTO.getFrameResult()))
              .append(BAR);
        });

    if (10 <= frameDTOs.size()) {
      sb.append(getLastFrameMsg(frameDTOs))
          .append(BAR);
    }

    System.out.println(sb.toString());
  }

  private static String getLastFrameMsg(List<FrameDTO> frameDTOs) {
    StringBuilder sb = new StringBuilder(
        frameDTOs.get(Lane.MAX_NUMBER_OF_FRAMES - 1).getFrameResult());

    frameDTOs.stream()
        .filter(FrameDTO::isBonusFrame)
        .forEach(frameDTO -> {
          sb.append(BAR)
              .append(frameDTO.getFrameResult());
        });

    return wrappingWithSpaces(sb.toString());
  }

  private static void printScores(List<Score> scores) {
    StringBuilder sb = new StringBuilder();

    sb.append(BAR)
        .append(wrappingWithSpaces(BLANK.toString()))
        .append(BAR);

    Score scoreHolder = Score.ofZere();

    scores.forEach(score -> {
      scoreHolder.add(score);

      sb.append(wrappingWithSpaces(scoreHolder.toString()))
          .append(BAR);
    });

    System.out.println(sb);
  }

  private static String wrappingWithSpaces(String str) {
    StringBuilder sb = new StringBuilder();

    if (str.equals("0")) {
      str = BLANK.toString();
    }

    int space = MAXLENGTH_PER_FRAME - str.length();
    int leftSpace = space / 2;
    int rightSpace = space - leftSpace;

    for (int i = 0; i < leftSpace; i++) {
      sb.append(SPACE);
    }

    sb.append(str);

    for (int i = 0; i < rightSpace; i++) {
      sb.append(SPACE);
    }

    return sb.toString();
  }
}
