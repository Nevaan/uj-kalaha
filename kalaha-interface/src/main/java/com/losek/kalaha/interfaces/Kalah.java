package com.losek.kalaha.interfaces;

public interface Kalah {
    /**
     * Metoda ustala wariant gry.
     *
     * @param houses liczba doĹkĂłw dla kaĹźdego z graczy
     * @param seeds  poczÄtkowa liczba kamieni w doĹkach gracza
     */
    public void setVariant(int houses, int seeds);

    /**
     * Metoda pozwala na rejestracje gracza. KolejnoĹÄ rejestracji jest znaczÄca.
     * Pierwsza rejestracja to gracz 1. Kolejna (2, 3, itd.) rejestracja gracz 2.
     *
     * @param player obiekt reprezentujÄcy gracza
     */
    public void registerPlayer(KalahPlayer player);

    /**
     * Metoda umoĹźliwia dodanie obserwatorĂłw gry. ObserwatorĂłw moĹźe byÄ wiÄcej niĹź
     * jeden.
     *
     * @param observer obserwator gry
     */
    public void addObserver(GameStateObserver observer);

    /**
     * Metoda zleca rozpoczÄcie gdy. Przed jej wykonaniem zostanie ustawiony wariant
     * gry, obiekty-gracze i obiekt (obiekty) obserwujÄce grÄ.
     */
    public void startGame();

}