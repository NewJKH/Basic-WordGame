package application;

import domain.game.Game;
import domain.game.GameStatus;

public class GameService {
    private final Game game;

    public GameService(int count) {
        WordService wordService = new WordService();
        this.game = new Game(wordService.getRandomWord().getWord(), count);
    }

    public void playGame(char letter) {
        try {
            game.processGuess(letter);
            System.out.println("현재 상태: " + game.getCurrentWordState());
            System.out.println("남은 시도 횟수: " + game.getRemainingAttempts());
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isGameOver() {
        return game.getStatus() != GameStatus.IN_PROGRESS;
    }

    public Game getGame() {
        return game;
    }

}
