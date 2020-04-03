package model;

import sample.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Class simulating a player of the Game whose purpose is to create
 * a winning token collection
 */
public class Player implements Runnable {

    private String name;
    private Board board;
    private List<Token> tokenCollection;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        tokenCollection = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Token> getTokenCollection() {
        return tokenCollection;
    }

    public void setTokenCollection(List<Token> tokenCollection) {
        this.tokenCollection = tokenCollection;
    }

    @Override
    public void run() {
        while (!board.getTokens().isEmpty()) {
            Token token = board.selectRandomToken();
            board.extractToken(token);
        }
    }
}
