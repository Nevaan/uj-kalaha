package mocks;

import interfaces.GameStateObserver;
import interfaces.KalahaState;

public class GameStateObserverImpl implements GameStateObserver {

    private final int id;

    public GameStateObserverImpl(int id) {
        this.id = id;
    }

    @Override
    public void currentState(KalahaState state) {
        System.out.println("Observer " + id + " received state: " + state.getPitsState());
    }
}
