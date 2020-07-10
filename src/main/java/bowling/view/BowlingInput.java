package bowling.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingInput {

  private final static Scanner scanner = new Scanner(System.in);

  public static List<String> getPalyerNamesInput() {
    int numberOfPlayer = getNumberOfPlayer();

    return IntStream.rangeClosed(1, numberOfPlayer)
        .mapToObj(BowlingInput::getPlayerNameInput)
        .collect(Collectors.toList());
  }

  private static int getNumberOfPlayer() {
    BowlingView.printPlayerNumberInputMsg();

    int numberOfPlayer = scanner.nextInt();
    scanner.nextLine();

    return numberOfPlayer;
  }

  public static String getPlayerNameInput(int numberOfPlayer) {
    BowlingView.printPlayerNameInputMsg(numberOfPlayer);

    return scanner.nextLine();
  }

  public static int getKnockDownNumberInput(String currentPlayer) {
    BowlingView.printKnockDownNumInputMsg(currentPlayer);

    int knockDownNumber = scanner.nextInt();
    scanner.nextLine();

    return knockDownNumber;
  }
}
