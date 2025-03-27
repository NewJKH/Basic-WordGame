import application.GameService;
import domain.game.GameStatus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        GameService gameService = new GameService(10);

        System.out.println("단어 맞추기 게임을 시작합니다!");
        System.out.println(gameService.getGame().getCurrentWordState());

        while (!gameService.isGameOver()) {
            System.out.print("알파벳을 입력하세요: ");
            String input = scanner.nextLine().trim();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("A-Z 사이의 한 글자만 입력하세요!");
                continue;
            }

            gameService.playGame(input.charAt(0));
        }

        if (gameService.getGame().getStatus() == GameStatus.WON) {
            System.out.println("축하합니다! 정답을 맞췄습니다!");
        } else {
            System.out.println("게임 오버! 정답은 " + gameService.getGame().getAnswer() + " 였습니다.");
        }

        scanner.close();
    }
}