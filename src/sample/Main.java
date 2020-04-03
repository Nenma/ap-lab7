package sample;

import exceptions.IllegalTokenValueException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Board;
import model.Game;
import model.Player;
import model.Token;

import java.util.List;
import java.util.Random;

/**
 * Main class testing Compulsory functionalities
 */
public class Main extends Application {

    public static final int NUMBER_OF_TOKENS = 25; // This is the 'n' in the problem

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        primaryStage.setTitle("Lab7 - The Arithmetic Progression Game");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Board board = prepareBoard();
        System.out.println(board);
        Game game = new Game(new Player("Alex", board), new Player("Mark", board));
        new Thread(game.getPlayer1()).start();
        new Thread(game.getPlayer2()).start();
        //launch(args);
    }

    /**
     * Generates a Board with NUMBER_OF_TOKENS unique Token game pieces
     * @return Board object with a collection of NUMBER_OF_TOKENS unique tokens
     */
    private static Board prepareBoard() {
        Board board = new Board();
        Random rand = new Random();
        try {
            for (int i = 0; i < NUMBER_OF_TOKENS; ++i) {
                Token token = new Token(rand.nextInt(Token.NUMBER_LIMIT) + 1);
                while (board.getTokens().contains(token)) {
                    token = new Token(rand.nextInt(Token.NUMBER_LIMIT) + 1);
                }
                board.addToken(token);
            }
        } catch (IllegalTokenValueException itve) {
            itve.printStackTrace();
        }
        return board;
    }
}
