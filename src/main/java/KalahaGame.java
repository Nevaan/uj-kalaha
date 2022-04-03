import implementation.KalahaStateImpl;
import implementation.PlayerState;
import interfaces.GameStateObserver;
import interfaces.KalahPlayer;
import interfaces.KalahaState;

import java.util.ArrayList;
import java.util.List;

public class KalahaGame implements interfaces.Kalah {

    private PlayerState playerState;

    private KalahaStateImpl currentState;

    private int houses, seeds, activePlayerNumber;

    private List<GameStateObserver> observers = new ArrayList<>();

    public KalahaGame() {
        playerState = new PlayerState();
    }

    @Override
    public void setVariant(int houses, int seeds) {
        this.houses = houses;
        this.seeds = seeds;
    }

    @Override
    public void registerPlayer(KalahPlayer player) {
        playerState.addPlayer(player);
    }

    @Override
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void startGame() {

        currentState = new KalahaStateImpl(this.seeds, this.houses);
        playerState.initLegalMoves(this.houses);
        notifyObservers(currentState);

        activePlayerNumber = 1;

        while (!KalahaState.GameStates.END_OF_GAME.equals(currentState.getGameResult())) {
            KalahPlayer activePlayer = playerState.getPlayer(activePlayerNumber);
            int pitIndex = activePlayer.yourMove(currentState.getPitsState());

            while(!playerState.isLegalMove(pitIndex, activePlayerNumber)) {
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
