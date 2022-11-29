package com.losek.kalaha.interfaces;

import java.util.List;

public interface KalahPlayer {

    /**
     * Metoda uĹźywana do odpytania gracza o jego ruch.
     *
     * @param pitsState lista zawierajÄca iloĹÄ kamieni w poszczegĂłlnych doĹkach
     *                  (ĹÄcznie z bazowymi). Numeracja doĹkĂłw od 0. Widok danych z
     *                  punktu widzenia gracza wykonujÄcego ruch.
     *
     * @return numer doĹka, z ktĂłrego gracz chce wyjÄÄ kamienie.
     */
    public int yourMove(List<Integer> pitsState);
}