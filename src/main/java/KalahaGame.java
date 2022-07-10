import implementation.player.PlayerBoard;
import implementation.player.PlayerDecorator;
import implementation.state.ExtendedState;
import implementation.state.InitState;
import interfaces.GameStateObserver;
import interfaces.KalahPlayer;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;

public class KalahaGame implements interfaces.Kalah {

    private int houses, seeds;

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

        player1 = new PlayerDecorator(player1, 0, houses);
        player2 = new PlayerDecorator(player2, houses + 1, houses);

        ExtendedState currentState = new InitState(new PlayerBoard(seeds, houses), player1, player2);

        notifyObservers(currentState);
        currentState = currentState.process();

        while (!KalahaState.GameStates.END_OF_GAME.equals(currentState.getGameState())) {

            ExtendedState nextState = currentState.process();
            notifyObservers(currentState);

            currentState = nextState;
        }

        notifyObservers(currentState);

    }

    private void notifyObservers(KalahaState state) {
        for (GameStateObserver obs : observers) {
            obs.currentState(state);
        }
    }


}
