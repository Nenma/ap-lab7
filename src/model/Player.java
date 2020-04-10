package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class simulating a player of the Game whose purpose is to create
 * a winning token collection
 */
abstract class Player implements Runnable {

    private String name;
    private String opponent;
    private Board board;
    private List<Token> tokenCollection;

    public Player(String name, String opponent, Board board) {
        this.name = name;
        this.opponent = opponent;
        this.board = board;
        tokenCollection = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getOpponent() {
        return opponent;
    }

    public Board getBoard() {
        return board;
    }

    public List<Token> getTokenCollection() {
        return tokenCollection;
    }

}
