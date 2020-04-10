package sample;

import exceptions.IllegalTokenValueException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main class responsible with conferring functionality to the various FXML
 * elements in the application
 */
public class Controller {

    @FXML
    public GridPane grid;

    @FXML
    public Label timekeeper;

    public static List<Button> tokens = new ArrayList<>();

    @FXML
    public Label textPlayer1;

    @FXML
    public Label textPlayer2;

    public static final int NUMBER_OF_TOKENS = 25; // This is the 'n' in the problem

    public void initialize() {
        prepareButtons();
        prepareBoard();
        startTimer();
    }

    /**
     * Loads NUMBER_OF_TOKENS (25) Buttons onto the Grid later to
     * be populated with the randomly generated values
     */
    private void prepareButtons() {
        int number = 1;
        for (int i = 1; i <= 5; ++i) {
            for (int j = 1; j <= 5; ++j) {
                Button b = new Button(String.valueOf(number));
                tokens.add(b);
                grid.add(b, j, i + 1);
            }
        }
    }

    private void prepareBoard() {
        Board board = prepareTokens();
        System.out.println(board);
        Game game = new Game(new RandomPlayer("Alex", "Mark", board), new RandomPlayer("Mark", "Alex", board));

        Thread alex = new Thread(game.getPlayer1());
        alex.setName("Alex");
        Thread mark = new Thread(game.getPlayer2());
        mark.setName("Mark");

        alex.start();
        mark.start();
    }

    /**
     * Generates a Board with NUMBER_OF_TOKENS unique Token game pieces and also
     * initializes Button values on the GridPane
     * @return Board object with a collection of NUMBER_OF_TOKENS unique tokens
     */
    private Board prepareTokens() {
        Board board = new Board();
        Random rand = new Random();
        try {
            for (int i = 0; i < NUMBER_OF_TOKENS; ++i) {
                int randomValue = rand.nextInt(Token.NUMBER_LIMIT) + 1;
                Token token = new Token(randomValue);
                while (board.getTokens().contains(token)) {
                    randomValue = rand.nextInt(Token.NUMBER_LIMIT) + 1;
                    token = new Token(randomValue);
                }
                board.addToken(token);
                tokens.get(i).setText(String.valueOf(randomValue));
            }
        } catch (IllegalTokenValueException itve) {
            itve.printStackTrace();
        }
        return board;
    }

    /**
     * Creates a daemon thread that updates the timekeeper Label every second
     */
    private void startTimer() {
        Thread timer = new Thread(() -> {
            int counter = 1;
            while (true) {
                try{
                    updateTime(counter);
                    counter += 1;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timer.setDaemon(true);
        timer.start();
    }

    /**
     * Calls upon the special method runLater to momentarily occupy the JavaFX thread
     * in order to update the timekeeper Label
     * @param counter value of seconds needed to display on the timer
     */
    private void updateTime(int counter) {
        final int counterMinutes = counter / 60;
        final int counterSeconds = counter % 60;
        Platform.runLater(() -> {
            if (counterMinutes < 10 && counterSeconds < 10) {
                timekeeper.setText("0" + counterMinutes + ":0" + counterSeconds);
            } else if (counterMinutes < 10 && counterSeconds >=10) {
                timekeeper.setText("0" + counterMinutes + ":" + counterSeconds);
            } else if (counterMinutes >= 10 && counterSeconds < 10) {
                timekeeper.setText(counterMinutes + ":0" + counterSeconds);
            } else {
                timekeeper.setText(counterMinutes + ":" + counterSeconds);
            }
        });
    }
}
