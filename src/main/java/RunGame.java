import interfaces.GameStateObserver;
import interfaces.Kalah;
import interfaces.KalahPlayer;
import mocks.GameStateObserverImpl;
import mocks.KalahPlayerImpl;

public class RunGame {

    public void run() {
        // init
        Kalah gameObject = new KalahaGame();
        int houses = 4;
        int seeds = 4;

        gameObject.setVariant(houses, seeds);

        KalahPlayer player1 = new KalahPlayerImpl();
        KalahPlayer player2 = new KalahPlayerImpl();

        gameObject.registerPlayer(player1);
        gameObject.registerPlayer(player2);

        GameStateObserver observer = new GameStateObserverImpl(1);
        gameObject.addObserver(observer);

        // loop
        gameObject.startGame();
    }

}
