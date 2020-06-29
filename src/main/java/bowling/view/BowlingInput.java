package bowling.view;

import java.util.Scanner;

public class BowlingInput {

  private final static Scanner scanner = new Scanner(System.in);

  public static String getPlayerNameInput() {
    BowlingView.printPlayerNameInputMsg();

    return scanner.nextLine();
  }

  public static int getKnockDownNumInput(int currentFrameNum) {
    BowlingView.printKnockDownNumInputMsg(currentFrameNum);

    int result = scanner.nextInt();
    scanner.nextLine();

    return result;
  }

  public static int getBonusFrameInput() {
    BowlingView.printBonusFrameInputMsg();

    int result = scanner.nextInt();
    scanner.nextLine();

    return result;
  }
}
