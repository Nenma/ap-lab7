package model;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import sample.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sample.Controller.NUMBER_OF_TOKENS;

/**
 * Class simulating the playing board of the Game, containing a list
 * of Tokens - playable pieces
 */
public class Board {

    private List<Token> tokens;
    private String whoseTurn;

    public Board() {
        tokens = new ArrayList<>();
    }

    public Board(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    /**
     * Current player thread will only extract a token from the board when it's
     * their turn
     * @param opponent current thread opponent's name
     */
    public synchronized Token extractToken(String opponent) {
        String player = Thread.currentThread().getName();
        if (whoseTurn == null) {
            whoseTurn = player;
            return null;
        }
        if (player.equals(whoseTurn)) {
            Token token = playerAction(player, opponent);
            notifyAll();
            return token;
        } else {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e);
            }
            return null;
        }
    }

    /**
     * Randomly extracts one Token from the Board and updates the application
     * GridPane using the runLater method
     * @param player current thread player's name
     * @param opponent current thread opponent's name
     * @return a random Token from the Board
     */
    private Token playerAction(String player, String opponent) {
        Random rand = new Random();
        Token token = tokens.get(rand.nextInt(tokens.size()));
        tokens.remove(token);
        for (int i = 0; i < NUMBER_OF_TOKENS; ++i) {
            if (Controller.tokens.get(i).getText().equals(String.valueOf(token.getValue()))) {
                final int index = i;
                Platform.runLater(() -> {
                    if (whoseTurn.equals("Alex")) {
                        Controller.tokens.get(index).setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    } else {
                        Controller.tokens.get(index).setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                });
            }
        }
        whoseTurn = opponent;
        return token;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "Board = {" + tokens + '}';
    }
}
