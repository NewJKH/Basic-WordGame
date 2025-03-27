package domain.game;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private final String answer;
    private final Set<Character> correctLetters = new HashSet<>();
    private final Set<Character> wrongLetters = new HashSet<>();
    private int remainingAttempts;
    private GameStatus status = GameStatus.IN_PROGRESS;

    public Game(String answer, int count) {
        this.remainingAttempts = count;
        this.answer = answer.toUpperCase();
    }

    /**
     * @param letter < word
     *   - 사용자의 입력을 처리하는 메서드
     */
    public void processGuess(char letter) {
        letter = Character.toUpperCase(letter);

        if (correctLetters.contains(letter) || wrongLetters.contains(letter)) {
            throw new IllegalArgumentException("이미 입력한 글자입니다.");
        }

        if (answer.contains(String.valueOf(letter))) {
            correctLetters.add(letter);
        } else {
            wrongLetters.add(letter);
            remainingAttempts--;
        }

        updateGameStatus();
    }

    /**
     * @return 현재 단어 상태를 리턴
     */
    public String getCurrentWordState() {
        StringBuilder display = new StringBuilder();
        for (char c : answer.toCharArray()) {
            if (correctLetters.contains(c)) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }
        return display.toString().trim();
    }

    /**
     * @see GameStatus 게임 상태를 업데이트
     */
    private void updateGameStatus() {
        if (remainingAttempts <= 0) {
            status = GameStatus.LOST;
        } else if (isWordCompleted()) {
            status = GameStatus.WON;
        }
    }
    /**
     * @return
     *  true < 모든 글자를 맞춤
     *  false < 아직 모든 글자를 맞추지 못함
     */
    private boolean isWordCompleted() {
        for (char c : answer.toCharArray()) {
            if (!correctLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return 현재 게임 상태
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * @return 남은 횟수
     */
    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public String getAnswer() {
        return answer;
    }
}
