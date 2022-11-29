package com.losek.kalaha.web.game;
import com.losek.kalaha.core.player.PlayerBoard;
import com.losek.kalaha.core.player.PlayerDecorator;
import com.losek.kalaha.core.state.ExtendedState;
import com.losek.kalaha.core.state.InitState;
import com.losek.kalaha.interfaces.GameStateObserver;
import com.losek.kalaha.interfaces.KalahPlayer;
import com.losek.kalaha.interfaces.KalahaState;
import com.losek.kalaha.interfaces.Kalah;

import java.util.ArrayList;
import java.util.List;

public class KalahaGame implements Kalah {

    private int houses;
    private int seeds;

    private List<GameStateObserver> observers = new ArrayList<>();

    private KalahPlayer player1;
    private KalahPlayer player2;


    public KalahaGame() {
        // default required constructor
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

        // PATTERN: stan
        ExtendedState currentState = new InitState(new PlayerBoard(seeds, houses), player1, player2, this.observers);

        while (!KalahaState.GameStates.END_OF_GAME.equals(currentState.getGameState())) {
            currentState = currentState.process();
        }

        currentState.process();

    }


}
