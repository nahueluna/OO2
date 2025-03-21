package io.github;

public class Papel implements Jugada{
    @Override
    public String jugarContra(Jugada otraJugada) {
        return otraJugada.jugarContraPapel();
    }

    @Override
    public String jugarContraPiedra() {
        return "Papel vs Piedra, gana Papel";
    }

    @Override
    public String jugarContraPapel() {
        return "Papel vs Papel, empate";
    }

    @Override
    public String jugarContraTijera() {
        return "Papel vs Tijera, gana Tijera";
    }

    @Override
    public String jugarContraLagarto() {
        return "Papel vs Lagarto, gana Lagarto";
    }

    @Override
    public String jugarContraSpock() {
        return "Papel vs Spock, gana Papel";
    }
}
