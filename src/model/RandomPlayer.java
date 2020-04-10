package model;

public class RandomPlayer extends Player {

    public RandomPlayer(String name, String opponent, Board board) {
        super(name, opponent, board);
    }

    @Override
    public void run() {
        while (!getBoard().getTokens().isEmpty()) {
            Token token = getBoard().extractToken(getOpponent());
            if (token != null) {
                getTokenCollection().add(token);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
