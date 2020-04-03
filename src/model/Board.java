package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class simulating the playing board of the Game, containing a list
 * of Tokens - playable pieces
 */
public class Board {

    private List<Token> tokens;
    private boolean available = true;

    public Board() {
        tokens = new ArrayList<>();
    }

    public Board(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public synchronized Token selectRandomToken() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e);
            }
        }

        Random rand = new Random();
        Token token = tokens.get(rand.nextInt(tokens.size()));
        System.out.println(Thread.currentThread().getName() + " randomly selected token " + token);

        available = false;
        notifyAll();

        return token;
    }

    public synchronized void extractToken(Token token) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e);
            }
        }

        tokens.remove(token);
        System.out.println(Thread.currentThread().getName() + " extracted token " + token);

        available = true;
        notifyAll();
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
