import implementation.KalahaStateImpl;
import interfaces.GameStateObserver;
import interfaces.KalahPlayer;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;

public class KalahaGame implements interfaces.Kalah {


    private KalahaStateImpl currentState;

    private int houses, seeds, activePlayerNumber;

    private List<GameStateObserver> observers = new ArrayList<>();

    private KalahPlayer player1;
    private KalahPlayer player2;


    public KalahaGame() {
    }

    @Override
    public void setVariant(int houses, int seeds) {
        this.houses = houses;
        this.seeds = seeds;
    }

    @Override
    public void registerPlayer(KalahPlayer player) {
        if (player1 == null) {
            player1 = player;
        } else {
            player2 = player;
        }
    }

    @Override
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void startGame() {

        currentState = new KalahaStateImpl(this.seeds, this.houses);
        notifyObservers(currentState);

        activePlayerNumber = 1;

        while (!KalahaState.GameStates.END_OF_GAME.equals(currentState.getGameState())) {
            KalahPlayer activePlayer = activePlayerNumber == 1 ? player1 : player2;
            int pitIndex = activePlayer.yourMove(currentState.getPitsState());

            while(pitIndex< 0 || pitIndex > this.houses) {
                pitIndex = activePlayer.yourMove(currentState.getPitsState());
            }

            boolean shouldPlayerChange = currentState.makeMove(pitIndex, activePlayerNumber);
            if(shouldPlayerChange) {
                if(activePlayerNumber==1) {
                    activePlayerNumber = 2;
                } else {
                    activePlayerNumber = 1;
                }
            }

            notifyObservers(currentState);
        }

        notifyObservers(currentState);

    }

    private void notifyObservers(KalahaState state) {
        for (GameStateObserver obs : observers) {
            obs.currentState(state);
        }
    }


}
